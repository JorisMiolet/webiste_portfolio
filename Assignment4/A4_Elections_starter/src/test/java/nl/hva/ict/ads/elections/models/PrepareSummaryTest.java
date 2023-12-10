package nl.hva.ict.ads.elections.models;

import nl.hva.ict.ads.utils.PathUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrepareSummaryTest {

    private Election election;

    @BeforeEach
    public void SetUp() throws XMLStreamException, IOException {
       this.election = Election.importFromDataFolder(PathUtils.getResourcePath("/EML_bestanden_TK2021_HvA_UvA"));

    }
    @Test
    public void testMostRepresentativePollingStation() {
        String summary = election.prepareSummary();
        String query = "Most representative polling station is:";
        int endIndex = summary.indexOf(query) + query.length();

        String dataToTest = summary.substring(endIndex);

        String pollingStation = dataToTest.split("\n")[1];

        assertEquals("PollingStation{id='0363::SB126',zipCode='1091GH',name='Stembureau Hogeschool van Amsterdam, Wibauthuis'}", pollingStation);
    }
    @Test
    public void testPartyToString() {
        String[] expectedPartyStrings = {
                "Party{id=4,name='D66'}",
                "Party{id=5,name='GROENLINKS'}",
        };

        List<Map.Entry<Party, Double>> parties = Election.sortedElectionResultsByPartyPercentage(
                election.getParties().size(), election.findMostRepresentativePollingStation().getVotesByParty());
        int i = 0;
        for (Map.Entry<Party, Double> entry : parties) {
            if (i == expectedPartyStrings.length) {
                break;
            }
            assertEquals(expectedPartyStrings[i], entry.getKey().toString());
            i++;
        }
    }
    @Test
    public void testOverallElectionResultsByPartyPercentage() {
        List<Map.Entry<Party, Double>> mockedResults = Election.sortedElectionResultsByPartyPercentage(election.getParties().size(),
                election.getVotesByParty());
        double[] values = {
                26.902173913043477,
                13.768115942028986,
                9.782608695652174
        };
        String electionSummary = election.prepareSummary();

        // Define the expected string that should be present in the generated summary
        String expectedSummarySubstring = "Overall election results by party percentage:";

        assertTrue(electionSummary.contains(expectedSummarySubstring));
        int i = 0;
        for (Map.Entry<Party, Double> entry : mockedResults) {
            if (i == values.length) {
                break;
            }
            assertEquals(values[i], entry.getValue());
            i++;
        }
    }
}
