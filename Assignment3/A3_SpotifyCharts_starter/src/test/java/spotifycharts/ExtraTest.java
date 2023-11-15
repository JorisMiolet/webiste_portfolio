package spotifycharts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExtraTest {
    private SongSorter songSorter;

    public static void main(String[] args) {

    }

    @BeforeEach
    public void setup(){
        this.songSorter = new SongSorter();
    }

    @Test
    public void testCompareByHighestStreamsCountTotal() {
        // Create a sample Song object with the English language and streams count for the UK
        Song song1 = new Song("Artist 1", "Title 1", Song.Language.EN);
        song1.setStreamsCountOfCountry(Song.Country.UK, 100);

        // Create a similar Song object with the same artist, title, language, and streams count for the UK
        Song sameSong = new Song("Artist 1", "Title 1", Song.Language.EN);
        sameSong.setStreamsCountOfCountry(Song.Country.UK, 100);

        // Compare the songs based on the highest total streams count
        int comparisonResult = song1.compareByHighestStreamsCountTotal(sameSong);

        // Assert that both songs are considered equal based on the comparison method
        assertEquals(0, comparisonResult); // Ensure that the comparison result is equal to 0, indicating equality
    }


    @Test
    public void testCompareForDutchNationalChart() {
        // Create a sample Song object with the Dutch language and streams count for the Netherlands
        Song song1 = new Song("Artist 1", "Title 1", Song.Language.NL);
        song1.setStreamsCountOfCountry(Song.Country.NL, 200);

        // Create a similar Song object with the same artist, title, language, and streams count for the Netherlands
        Song sameSong = new Song("Artist 1", "Title 1", Song.Language.NL);
        sameSong.setStreamsCountOfCountry(Song.Country.NL, 200);

        // Compare the songs using a method specific for the Dutch national chart
        int comparisonResult = song1.compareForDutchNationalChart(sameSong);

        // Assert that both songs are considered equal based on the comparison method
        assertEquals(0, comparisonResult); // Ensure that the comparison result is equal to 0, indicating equality
    }


    @Test
    public void testComparatorSymmetry() {
        // Create sample Song objects with their streams count for different countries
        Song song1 = new Song("Artist 1", "Title 1", Song.Language.EN);
        song1.setStreamsCountOfCountry(Song.Country.UK, 100);

        Song song2 = new Song("Artist 2", "Title 2", Song.Language.NL);
        song2.setStreamsCountOfCountry(Song.Country.NL, 200);

        // Compare the songs using a method that compares by highest total streams count
        int comparison1 = song1.compareByHighestStreamsCountTotal(song2); // Comparison from song1 to song2
        int comparison2 = song2.compareByHighestStreamsCountTotal(song1); // Comparison from song2 to song1

        // Assert that the comparisons are symmetric
        assertEquals(-(comparison1), comparison2); // Ensure that the comparison results are opposite in sign
    }


    @Test
    public void testQuickSort() {
        // Create sample Song objects with their streams count for different countries
        Song song1 = new Song("beyonce", "s", Song.Language.EN);
        song1.setStreamsCountOfCountry(Song.Country.UK, 100);

        Song song2 = new Song("taylor swift", "sla", Song.Language.DE);
        song2.setStreamsCountOfCountry(Song.Country.DE, 200);

        Song song3 = new Song("bts", "peper", Song.Language.NL);
        song3.setStreamsCountOfCountry(Song.Country.NL, 50);

        Song song4 = new Song("loona", "banaan", Song.Language.EN);
        song4.setStreamsCountOfCountry(Song.Country.UK, 150);

        // Create a list and add the sample songs
        List<Song> songItems = new ArrayList<>();
        songItems.add(song1);
        songItems.add(song2);
        songItems.add(song3);
        songItems.add(song4);

        // Define a comparator based on the total streams count of songs in descending order
        Comparator<Song> songComparator = Comparator.comparingInt(Song::getStreamsCountTotal).reversed();

        // Perform quickSort operation on the songItems list
        songItems = songSorter.quickSort(songItems, songComparator);

        // Define the expected order after sorting based on streams count
        List<Song> expectedOrder = List.of(song2, song4, song1, song3);

        // Verify if the sorting result matches the expected order
        assertEquals(expectedOrder, songItems);
    }


    @Test
    public void testTopsHeapSort() {
        // Create sample Song objects with their streams count for different countries
        Song song1 = new Song("beyonce", "s", Song.Language.EN);
        song1.setStreamsCountOfCountry(Song.Country.UK, 100);

        Song song2 = new Song("taylor swift", "sla", Song.Language.DE);
        song2.setStreamsCountOfCountry(Song.Country.DE, 200);

        Song song3 = new Song("bts", "peper", Song.Language.NL);
        song3.setStreamsCountOfCountry(Song.Country.NL, 50);

        Song song4 = new Song("loona", "banaan", Song.Language.EN);
        song4.setStreamsCountOfCountry(Song.Country.UK, 150);

        // Create a list and add the sample songs
        List<Song> songItems = new ArrayList<>();
        songItems.add(song1);
        songItems.add(song2);
        songItems.add(song3);
        songItems.add(song4);

        // Define a comparator based on the total streams count of songs in descending order
        Comparator<Song> songComparator = Comparator.comparingInt(Song::getStreamsCountTotal).reversed();

        int numTops = 2; // Define the number of top elements to be sorted

        // Perform the topsHeapSort operation on the songItems list
        songItems = songSorter.topsHeapSort(numTops, songItems, songComparator);

        // Check if the sorted elements satisfy the heap property (top elements should have higher streams count)
        for (int i = 0; i < numTops; i++) {
            for (int j = numTops; j < songItems.size(); j++) {
                assertTrue(songComparator.compare(songItems.get(i), songItems.get(j)) <= 0);
                // Assert that the streams count of the top elements is greater than or equal to the remaining elements
            }
        }
    }





}
