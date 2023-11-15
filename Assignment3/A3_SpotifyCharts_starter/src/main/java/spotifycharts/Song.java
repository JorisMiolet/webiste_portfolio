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
        //for each country in the enum add the country with a corresponding stream count set to a default of 0
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
        //set given stream count to the given country
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
        int totalStreams = 0;//set totalStreams at 0 for default
        //then loop through all countries in the map and add the streams count of the country to totalStreams
        for (Country country: getStreamsPerCountry().keySet()) {
            totalStreams += getStreamsCountOfCountry(country);
        }

        return totalStreams;//return total streams
    }


    /**
     * compares this song with the other song
     * ordening songs with the highest total number of streams upfront
     * @param other     the other song to compare against
     * @return  negative number, zero or positive number according to Comparator convention
     */
    public int compareByHighestStreamsCountTotal(Song other) {
        //returns -1 if this is lower than other, 1 if it is higher and 0 if it is equal
        return Integer.compare(other.getStreamsCountTotal(), this.getStreamsCountTotal());
    }

    /**
     * compares this song with the other song
     * ordening all Dutch songs upfront and then by decreasing total number of streams
     * @param other     the other song to compare against
     * @return  negative number, zero or positive number according to Comparator conventions
     */
    public int compareForDutchNationalChart(Song other) {
        // Check if the language of the current song is Dutch and the other song's language is not Dutch
        if (this.getLanguage() == Language.NL && other.getLanguage() != Language.NL) {
            return -1; // Current song should be placed before the other song in the Dutch national chart
        } else if (this.getLanguage() != Language.NL && other.getLanguage() == Language.NL) {
            return 1; // Other song should be placed before the current song in the Dutch national chart
        }

        // If both songs have the same language or both are non-Dutch, compare their total streams count
        return Integer.compare(other.getStreamsCountTotal(), this.getStreamsCountTotal());
        // Returns:
        // -1 if other song has more total streams count (higher priority)
        //  0 if both songs have the same total streams count
        //  1 if the current song has more total streams count (higher priority)
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


    @Override
    public String toString() {
        return String.format("%s/%s{%s}(%d)", getArtist(), getTitle(), getLanguage(), getStreamsCountTotal());
    }
}
