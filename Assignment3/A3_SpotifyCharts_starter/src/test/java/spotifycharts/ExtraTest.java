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
        Song song1 = new Song("Artist 1", "Title 1", Song.Language.EN);
        song1.setStreamsCountOfCountry(Song.Country.UK, 100);

        Song sameSong = new Song("Artist 1", "Title 1", Song.Language.EN);
        sameSong.setStreamsCountOfCountry(Song.Country.UK, 100);

        assertEquals(0, song1.compareByHighestStreamsCountTotal(sameSong));
    }

    @Test
    public void testCompareForDutchNationalChart() {
        Song song1 = new Song("Artist 1", "Title 1", Song.Language.NL);
        song1.setStreamsCountOfCountry(Song.Country.NL, 200);

        Song sameSong = new Song("Artist 1", "Title 1", Song.Language.NL);
        sameSong.setStreamsCountOfCountry(Song.Country.NL, 200);

        assertEquals(0, song1.compareForDutchNationalChart(sameSong));
    }

    @Test
    public void testComparatorSymmetry() {
        Song song1 = new Song("Artist 1", "Title 1", Song.Language.EN);
        song1.setStreamsCountOfCountry(Song.Country.UK, 100);

        Song song2 = new Song("Artist 2", "Title 2", Song.Language.NL);
        song2.setStreamsCountOfCountry(Song.Country.NL, 200);

        int comparison1 = song1.compareByHighestStreamsCountTotal(song2);
        int comparison2 = song2.compareByHighestStreamsCountTotal(song1);

        assertEquals(-(comparison1), comparison2);
    }

    @Test
    public void testQuickSort() {
        Song song1 = new Song("beyonce", "s", Song.Language.EN);
        song1.setStreamsCountOfCountry(Song.Country.UK, 100);

        Song song2 = new Song("taylor swift", "sla", Song.Language.DE);
        song2.setStreamsCountOfCountry(Song.Country.DE, 200);

        Song song3 = new Song("bts", "peper", Song.Language.NL);
        song3.setStreamsCountOfCountry(Song.Country.NL, 50);

        Song song4 = new Song("loona", "banaan", Song.Language.EN);
        song4.setStreamsCountOfCountry(Song.Country.UK, 150);

        List<Song> songItems = new ArrayList<>();
        songItems.add(song1);
        songItems.add(song2);
        songItems.add(song3);
        songItems.add(song4);

        Comparator<Song> songComparator = Comparator.comparingInt(Song::getStreamsCountTotal).reversed();

        // Testing sorting songs based on stream counts
        songItems = songSorter.quickSort(songItems, songComparator);

        // Expected order after sorting based on streams count
        List<Song> expectedOrder = List.of(song2, song4, song1, song3);

        // Verify the ordering of songs
        assertEquals(expectedOrder, songItems);
    }

    @Test
    public void testTopsHeapSort() {
        Song song1 = new Song("beyonce", "s", Song.Language.EN);
        song1.setStreamsCountOfCountry(Song.Country.UK, 100);

        Song song2 = new Song("taylor swift", "sla", Song.Language.DE);
        song2.setStreamsCountOfCountry(Song.Country.DE, 200);

        Song song3 = new Song("bts", "peper", Song.Language.NL);
        song3.setStreamsCountOfCountry(Song.Country.NL, 50);

        Song song4 = new Song("loona", "banaan", Song.Language.EN);
        song4.setStreamsCountOfCountry(Song.Country.UK, 150);

        List<Song> songItems = new ArrayList<>();
        songItems.add(song1);
        songItems.add(song2);
        songItems.add(song3);
        songItems.add(song4);

        Comparator<Song> songComparator = Comparator.comparingInt(Song::getStreamsCountTotal).reversed();

        int numTops = 2;

        songItems = songSorter.topsHeapSort(numTops, songItems, songComparator);

        for (int i = 0; i < numTops; i++) {
            for (int j = numTops; j < songItems.size(); j++) {
                assertTrue(songComparator.compare(songItems.get(i), songItems.get(j)) <= 0);
            }
        }
    }




}
