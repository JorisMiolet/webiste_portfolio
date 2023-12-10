package nl.hva.ict.ads.elections.models;

import nl.hva.ict.ads.utils.PathUtils;
import nl.hva.ict.ads.utils.xml.XMLParser;

import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Holds all election data per consituency
 * Provides calculation methods for overall election results
 */
public class Election {

    private String name;

    // all (unique) parties in this election, organised by Id
    // will be build from the XML
    protected Map<Integer, Party> parties;

    // all (unique) constituencies in this election, identified by Id
    protected Set<Constituency> constituencies;

    public Election(String name) {
        this.name = name;

        this.parties = new TreeMap<>(Comparator.naturalOrder());
        this.constituencies = new HashSet<>();
    }

    /**
     * finds all (unique) parties registered for this election
     * @return all parties participating in at least one constituency, without duplicates
     */
    public Collection<Party> getParties() {

        return getConstituencies()
                .stream()
                .flatMap(constituency -> constituency.getParties().stream())
                .collect(Collectors.toSet());
    }

    /**
     * finds the party with a given id
     * @param id
     * @return  the party with given id, or null if no such party exists.
     */
    public Party getParty(int id) {
        for (Party party : getParties()) {
            if (party.getId() == id) {
                return party;
            }
        }

        return null;
    }

    public Set<? extends Constituency> getConstituencies() {
        return this.constituencies;
    }

    /**
     * finds all unique candidates across all parties across all constituencies
     * organised by increasing party-id
     * @return alle unique candidates organised by increasing party-id
     */
    public List<Candidate> getAllCandidates() {
        return getParties().stream()
                .flatMap(party -> party.getCandidates().stream())
                .distinct()
                .sorted(Comparator.comparingInt(candidate -> candidate.getParty().getId()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieve for the given party the number of Candidates that have been registered per Constituency
     * @param party
     * @return
     */
    public Map<Constituency, Integer> numberOfRegistrationsByConstituency(Party party) {
        return getConstituencies().stream()
                .collect(Collectors.toMap(
                        constituency -> constituency,
                        constituency -> constituency.getCandidates(party).stream()
                                .filter(candidate -> candidate.getParty().equals(party)).toList()
                                .size()
                ));
    }

    /**
     * Finds all Candidates that have a duplicate name against another candidate in the election
     * (can be in the same party or in another party)
     * @return
     */
    public Set<Candidate> getCandidatesWithDuplicateNames() {
        Map<String, Long> nameOccurrences = getParties().stream()
                .flatMap(party -> party.getCandidates().stream())
                .collect(Collectors.groupingBy(Candidate::getFullName, Collectors.counting()));

        return getParties().stream()
                .flatMap(party -> party.getCandidates().stream())
                .filter(candidate -> nameOccurrences.get(candidate.getFullName()) > 1)
                .collect(Collectors.toSet());
    }



    /**
     * Retrieve from all constituencies the combined sub set of all polling stations that are located
     * within the area of the specified zip codes
     * i.e. firstZipCode <= pollingStation.zipCode <= lastZipCode
     * All valid zip codes adhere to the pattern 'nnnnXX' with 1000 <= nnnn <= 9999 and 'AA' <= XX <= 'ZZ'
     * @param firstZipCode
     * @param lastZipCode
     * @return      the sub set of polling stations within the specified zipCode range
     */
    public Collection<PollingStation> getPollingStationsByZipCodeRange(String firstZipCode, String lastZipCode) {
        return getConstituencies().stream().flatMap(constituency ->
                constituency.getPollingStationsByZipCodeRange(firstZipCode, lastZipCode).stream())
                .collect(Collectors.toList());
    }



    /**
     * Retrieves per party the total number of votes across all candidates, constituencies and polling stations
     * @return
     */
    public Map<Party, Integer> getVotesByParty() {
        return getConstituencies().stream()
                .flatMap(constituency -> constituency.getPollingStations().stream())
                .flatMap(pollingStation -> pollingStation.getVotesByParty().entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Integer::sum
                ));
    }

    /**
     * Retrieves per party the total number of votes across all candidates,
     * that were cast in one out of the given collection of polling stations.
     * This method is useful to prepare an election result for any sub-area of a Constituency.
     * Or to obtain statistics of special types of voting, e.g. by mail.
     * @param pollingStations the polling stations that cover the sub-area of interest
     * @return
     */
    public Map<Party, Integer> getVotesByPartyAcrossPollingStations(Collection<PollingStation> pollingStations) {
        return pollingStations.stream()
                .flatMap(pollingStation -> pollingStation.getVotesByParty().entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Integer::sum
                ));
    }


    /**
     * Transforms and sorts decreasingly vote counts by party into votes percentages by party
     * The party with the highest vote count shall be ranked upfront
     * The votes percentage by party is calculated from  100.0 * partyVotes / totalVotes;
     * @return  the sorted list of (party,votesPercentage) pairs with the highest percentage upfront
     */
    public static List<Map.Entry<Party, Double>> sortedElectionResultsByPartyPercentage(int tops, Map<Party, Integer> votesCounts) {
        int totalVotes = votesCounts.values().stream().mapToInt(Integer::intValue).sum();

        return votesCounts.entrySet().stream()
                .sorted(Map.Entry.<Party, Integer>comparingByValue().reversed())
                .limit(tops)
                .map(entry -> Map.entry(entry.getKey(), 100.0 * entry.getValue() / totalVotes))
                .collect(Collectors.toList());
    }

    /**
     * Find the most representative Polling Station, which has got its votes distribution across all parties
     * the most alike the distribution of overall total votes.
     * A perfect match is found, if for each party the percentage of votes won at the polling station
     * is identical to the percentage of votes won by the party overall in the election.
     * The most representative Polling Station has the smallest deviation from that perfect match.
     *
     * There are different metrics possible to calculate a relative deviation between distributions.
     * You may use the helper method {@link #euclidianVotesDistributionDeviation(Map, Map)}
     * which calculates a relative least-squares deviation between two distributions.
     *
     * @return the most representative polling station.
     */
    public PollingStation findMostRepresentativePollingStation() {
        Map<Party, Integer> overallDistribution = calculateOverallVotesDistribution();

        return getConstituencies().stream()
                .flatMap(constituency -> constituency.getPollingStations().stream())
                .min(Comparator.comparingDouble(pollingStation ->
                        euclidianVotesDistributionDeviation(overallDistribution, calculateVotesDistribution(pollingStation))))
                .orElse(null);
    }

    private Map<Party, Integer> calculateOverallVotesDistribution() {
        int totalVotes = getVotesByParty().values().stream().mapToInt(Integer::intValue).sum();
        return getVotesByParty().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (int) Math.round(100.0 * entry.getValue() / totalVotes)
                ));
    }

    private Map<Party, Integer> calculateVotesDistribution(PollingStation pollingStation) {
        Map<Party, Integer> votesByParty = pollingStation.<Party, Integer>getVotesByParty();
        int totalVotes = integersSum(votesByParty.values());

        return votesByParty.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (int) Math.round(entry.getValue() * 100.0 / totalVotes)
                ));
    }


    /**
     * Calculates the Euclidian distance between the relative distribution across parties of two voteCounts.
     * If the two relative distributions across parties are identical, then the distance will be zero
     * If some parties have relatively more votes in one distribution than the other, the outcome will be positive.
     * The lower the outcome, the more alike are the relative distributions of the voteCounts.
     * ratign of votesCounts1 relative to votesCounts2.
     * see https://towardsdatascience.com/9-distance-measures-in-data-science-918109d069fa
     *
     * @param votesCounts1 one distribution of votes across parties.
     * @param votesCounts2 another distribution of votes across parties.
     * @return de relative distance between the two distributions.
     */
    private double euclidianVotesDistributionDeviation(Map<Party, Integer> votesCounts1, Map<Party, Integer> votesCounts2) {
        // calculate total number of votes in both distributions
        int totalNumberOfVotes1 = integersSum(votesCounts1.values());
        int totalNumberOfVotes2 = integersSum(votesCounts2.values());

        // we calculate the distance as the sum of squares of relative voteCount distribution differences per party
        // if we compare two voteCounts that have the same relative distribution across parties, the outcome will be zero

        return votesCounts1.entrySet().stream()
                .mapToDouble(e -> Math.pow(e.getValue()/(double)totalNumberOfVotes1 -
                        votesCounts2.getOrDefault(e.getKey(),0)/(double)totalNumberOfVotes2, 2))
                .sum();
    }

    /**
     * auxiliary method to calculate the total sum of a collection of integers
     * @param integers
     * @return
     */
    public static int integersSum(Collection<Integer> integers) {
        return integers.stream().reduce(Integer::sum).orElse(0);
    }

    public String prepareSummary(int partyId) {
        Party party = this.getParty(partyId);
        StringBuilder summary = new StringBuilder()
                .append("\nSummary of ").append(party).append(":\n");

        summary.append(String.format("Total number of candidates = %d\n", party.getCandidates().size()));

        if (!party.getCandidates().isEmpty()) {
            summary.append("Candidates: [");
            for (Candidate candidate : party.getCandidates()) {
                summary.append(String.format("Candidate{partyId=%d, name='%s'}, ", party.getId(), candidate.getFullName()));
            }

            summary.delete(summary.length() - 2, summary.length());
            summary.append("]\n");
        }

        int numberOfRegistrations = 0;
        Map<Constituency, Integer> registrationsByConstituency = numberOfRegistrationsByConstituency(party);

        for (Constituency constituency : registrationsByConstituency.keySet()) {
            numberOfRegistrations += registrationsByConstituency.get(constituency);
        }

        summary.append("\n").append(String.format("Total number of registrations = %d", numberOfRegistrations));

        if (!registrationsByConstituency.isEmpty()) {
            summary.append("\nNumber of registrations per constituency: {");
            for (Constituency constituency : registrationsByConstituency.keySet()) {
                summary.append(String.format("%s=%d, ", constituency, registrationsByConstituency.get(constituency)));
            }

            summary.delete(summary.length() - 2, summary.length());
            summary.append("}\n");
        }

        return summary.toString();
    }

    public String prepareSummary() {

        StringBuilder summary = new StringBuilder()
                .append("\nElection summary of ").append(this.name).append(":\n\n");

        // total number of parties in the election
        summary.append(String.format("%d Participating parties: \n", getParties().size()));

        // the list of all parties ordered by increasing party-Id
        List<Party> sortedParties = getParties().stream()
                .sorted(Comparator.comparingInt(Party::getId))
                .collect(Collectors.toList());
        summary.append(sortedParties).append("\n\n");

        // the total number of constituencies in the election
        summary.append("Total number of constituencies: ").append(getConstituencies().size()).append("\n");

        // the total number of polling stations in the election
        int totalPollingStations = getConstituencies().stream()
                .mapToInt(constituency -> constituency.getPollingStations().size())
                .sum();
        summary.append("Total number of polling stations: ").append(totalPollingStations).append("\n");

        // the total number of (different) candidates in the election
        Set<Candidate> allCandidates = getConstituencies().stream()
                .flatMap(constituency -> constituency.getAllCandidates().stream())
                .collect(Collectors.toSet());
        summary.append("Total number of candidates in the election: ").append(allCandidates.size()).append("\n\n");

        // the list with all candidates which have a counterpart with a duplicate name in a different party
        Set<Candidate> candidatesWithDuplicateNames = getCandidatesWithDuplicateNames();
        summary.append("Different candidates with duplicate names across different parties are: \n")
                .append(candidatesWithDuplicateNames).append("\n\n");

        // the sorted list of overall election results ordered by decreasing party percentage
        List<Map.Entry<Party, Double>> sortedResults = sortedElectionResultsByPartyPercentage(getParties().size(),
                getVotesByParty());
        summary.append("Overall election results by party percentage: \n").append(sortedResults).append("\n\n");

        // the polling stations within the Amsterdam Wibautstraat area with zipcodes between 1091AA and 1091ZZ
        String firstZipCode = "1091AA";
        String lastZipCode = "1091ZZ";
        Collection<PollingStation> pollingStationsInArea = getPollingStationsByZipCodeRange(firstZipCode, lastZipCode);
        summary.append("Polling stations in Amsterdam Wibautstraat area with zip codes 1091AA-1091ZZ: \n")
                .append(pollingStationsInArea).append("\n\n");

        // the top 10 sorted election results within the Amsterdam Wibautstraat
        List<Map.Entry<Party, Double>> top10ResultsInArea = sortedElectionResultsByPartyPercentage(
                10, getVotesByPartyAcrossPollingStations(pollingStationsInArea));
        summary.append("Top 10 election results by party percentage in Amsterdam area with zip codes 1091AA-1091ZZ: \n")
                .append(top10ResultsInArea).append("\n\n");

        // the most representative polling station across the election
        PollingStation mostRepresentativeStation = findMostRepresentativePollingStation();
        summary.append("Most representative polling station is: \n").append(mostRepresentativeStation).append("\n\n");

        // the sorted election results by decreasing party percentage of the most representative polling station
        List<Map.Entry<Party, Double>> resultsOfMostRepresentativeStation = sortedElectionResultsByPartyPercentage(
                getParties().size(), mostRepresentativeStation.getVotesByParty());
        summary.append(resultsOfMostRepresentativeStation).append("\n");

        return summary.toString();
    }

    /**
     * Reads all data of Parties, Candidates, Contingencies and PollingStations from available files in the given folder and its subfolders
     * This method can cope with any structure of sub folders, but does assume the file names to comply with the conventions
     * as found from downloading the files from https://data.overheid.nl/dataset/verkiezingsuitslag-tweede-kamer-2021
     * So, you can merge folders after unpacking the zip distributions of the data, but do not change file names.
     * @param folderName    the root folder with the data files of the election results
     * @return een Election met alle daarbij behorende gegevens.
     * @throws XMLStreamException bij fouten in een van de XML bestanden.
     * @throws IOException als er iets mis gaat bij het lezen van een van de bestanden.
     */
    public static Election importFromDataFolder(String folderName) throws XMLStreamException, IOException {
        System.out.println("Loading election data from " + folderName);
        Election election = new Election(folderName);
        int progress = 0;
        Map<Integer, Constituency> kieskringen = new HashMap<>();
        for (Path constituencyCandidatesFile : PathUtils.findFilesToScan(folderName, "Kandidatenlijsten_TK2021_")) {
            XMLParser parser = new XMLParser(new FileInputStream(constituencyCandidatesFile.toString()));
            Constituency constituency = Constituency.importFromXML(parser, election.parties);
            //election.constituenciesM.put(constituency.getId(), constituency);
            election.constituencies.add(constituency);
            showProgress(++progress);
        }
        System.out.println();
        progress = 0;
        for (Path votesPerPollingStationFile : PathUtils.findFilesToScan(folderName, "Telling_TK2021_gemeente")) {
            XMLParser parser = new XMLParser(new FileInputStream(votesPerPollingStationFile.toString()));
            election.importVotesFromXml(parser);
            showProgress(++progress);
        }
        System.out.println();
        return election;
    }

    protected static void showProgress(final int progress) {
        System.out.print('.');
        if (progress % 50 == 0) System.out.println();
    }

    /**
     * Auxiliary method for parsing the data from the EML files
     * This methode can be used as-is and does not require your investigation or extension.
     */
    public void importVotesFromXml(XMLParser parser) throws XMLStreamException {
        if (parser.findBeginTag(Constituency.CONSTITUENCY)) {

            int constituencyId = 0;
            if (parser.findBeginTag(Constituency.CONSTITUENCY_IDENTIFIER)) {
                constituencyId = parser.getIntegerAttributeValue(null, Constituency.ID, 0);
                parser.findAndAcceptEndTag(Constituency.CONSTITUENCY_IDENTIFIER);
            }

            //Constituency constituency = this.constituenciesM.get(constituencyId);
            final int finalConstituencyId = constituencyId;
            Constituency constituency = this.constituencies.stream()
                    .filter(c -> c.getId() == finalConstituencyId)
                    .findFirst()
                    .orElse(null);

            //parser.findBeginTag(PollingStation.POLLING_STATION_VOTES);
            while (parser.findBeginTag(PollingStation.POLLING_STATION_VOTES)) {
                PollingStation pollingStation = PollingStation.importFromXml(parser, constituency, this.parties);
                if (pollingStation != null) constituency.add(pollingStation);
            }

            parser.findAndAcceptEndTag(Constituency.CONSTITUENCY);
        }
    }

    /**
     * HINTS:
     * getCandidatesWithDuplicateNames:
     *  Approach-1: first build a Map that counts the number of candidates per given name
     *              then build the collection from all candidates, excluding those whose name occurs only once.
     *  Approach-2: build a stream that is sorted by name
     *              apply a mapMulti that drops unique names but keeps the duplicates
     *              this approach probably requires complex lambda expressions that are difficult to justify
     */

}
