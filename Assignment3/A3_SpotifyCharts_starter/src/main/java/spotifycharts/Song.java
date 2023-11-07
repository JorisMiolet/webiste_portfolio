package spotifycharts;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Song {

    public enum Language {
        EN, // English
        NL, // Dutch
        DE, // German
        FR, // French
        SP, // Spanish
        IT, // Italian
    }

    public enum Country {
        UK, // United Kingdom
        NL, // Netherlands
        DE, // Germany
        BE, // Belgium
        FR, // France
        SP, // Spain
        IT  // Italy
    }

    private final String artist;
    private final String title;
    private final Language language;

    private Map<Country, Integer> streamsPerCountry;


    /**
     * Constructs a new instance of Song based on given attribute values
     */
    public Song(String artist, String title, Language language) {
        this.artist = artist;
        this.title = title;
        this.language = language;

        streamsPerCountry = new HashMap<>();
        for (Country country: Country.values()) {
            streamsPerCountry.put(country, 0);
        }
    }

    /**
     * Sets the given streams count for the given country on this song
     * @param country
     * @param streamsCount
     */
    public void setStreamsCountOfCountry(Country country, int streamsCount) {
        getStreamsPerCountry().put(country, streamsCount);
    }

    /**
     * retrieves the streams count of a given country from this song
     * @param country
     * @return
     */
    public int getStreamsCountOfCountry(Country country) {
        return getStreamsPerCountry().get(country);
    }
    /**
     * Calculates/retrieves the total of all streams counts across all countries from this song
     * @return
     */
    public int getStreamsCountTotal() {
        int totalStreams = 0;
        for (Country country: getStreamsPerCountry().keySet()) {
            totalStreams += getStreamsCountOfCountry(country);
        }

        return totalStreams;
    }


    /**
     * compares this song with the other song
     * ordening songs with the highest total number of streams upfront
     * @param other     the other song to compare against
     * @return  negative number, zero or positive number according to Comparator convention
     */
    public int compareByHighestStreamsCountTotal(Song other) {
        return Integer.compare(this.getStreamsCountTotal(), other.getStreamsCountTotal());// replace by proper result
    }

    /**
     * compares this song with the other song
     * ordening all Dutch songs upfront and then by decreasing total number of streams
     * @param other     the other song to compare against
     * @return  negative number, zero or positive number according to Comparator conventions
     */
    public int compareForDutchNationalChart(Song other) {



        return 0;    // replace by proper result
    }


    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public Language getLanguage() {
        return language;
    }

    public Map<Country, Integer> getStreamsPerCountry() {
        return streamsPerCountry;
    }
    // TODO provide a toString implementation to format songs as in "artist/title{language}(total streamsCount)"


}
