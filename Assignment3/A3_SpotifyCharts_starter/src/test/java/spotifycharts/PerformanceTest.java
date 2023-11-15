package spotifycharts;

import java.util.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PerformanceTest {

    //measures efficiency of sorting algorithms with different Problem sizes
    @Test
    void measureSortingEfficiency() {

        List<String> algorithms = new ArrayList<>(Arrays.asList("Insertion Sort", "Quick Sort", "Heap Sort", "Collection Sort"));

        System.gc();

        for (int currentSize = 100; currentSize < 5000000 && !algorithms.isEmpty(); currentSize *= 2) {

            System.out.println("Measuring efficiency for input size: " + currentSize);

            // Generate a shuffled list for each run
            List<Song> unsortedList = generateUnsortedList(currentSize);

            List<String> algorithmsCopy = new ArrayList<>(algorithms);


            for (String algorithm : algorithmsCopy) {
                try {
                    measureAlgorithm(unsortedList, algorithm);
                } catch (AssertionError e) {
                    System.out.println(algorithm + "algorithm took langer than 20 seconds");
                    algorithms.remove(algorithm); // if algorithm takes longer than 20 seconds, remove it from the list
                }
            }
        }
    }

    private void measureAlgorithm(List<Song> unsortedList, String algorithm) {
        assertTimeoutPreemptively(
                java.time.Duration.ofSeconds(20),
                () -> measureSortingTime(algorithm, unsortedList, Song::compareByHighestStreamsCountTotal));
    }
    private List<Song> generateUnsortedList(int size) {
        List<Song> shuffledList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            Song song = SongBuilder.createSample(5);
            song.setStreamsCountOfCountry(Song.Country.DE, random.nextInt(1500));
            song.setStreamsCountOfCountry(Song.Country.UK, random.nextInt(1500));
            song.setStreamsCountOfCountry(Song.Country.NL, random.nextInt(1500));
            shuffledList.add(song);
        }

        // Run gc
        System.gc();

        return shuffledList;
    }

    //measures time spent per algorithm
    private void measureSortingTime(String algorithmName, List<Song> unsortedList, Comparator<Song> comparator) {
        List<Song> copyList = new ArrayList<>(unsortedList);

        // Use the same seed for all sorting algorithms so that they have the same list to sort
        long seed = System.currentTimeMillis();

        Collections.shuffle(copyList, new Random(seed));

        long elapsedTime = measureTime(() -> {
            switch (algorithmName) {
                case "Insertion Sort" -> new SorterImpl<Song>().selInsBubSort(copyList, comparator);
                case "Quick Sort" -> new SorterImpl<Song>().quickSort(copyList, comparator);
                case "Heap Sort" -> new SorterImpl<Song>().topsHeapSort(copyList.size(), copyList, comparator);
                case "Collection Sort" -> copyList.sort(comparator);
                default -> throw new IllegalArgumentException("Invalid algorithm name: " + algorithmName);
            }
        });
            System.out.println(algorithmName + " " +
                    elapsedTime + " milliseconds");
    }

    private long measureTime(Runnable code) {
        long startTime = System.currentTimeMillis();
        code.run();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
