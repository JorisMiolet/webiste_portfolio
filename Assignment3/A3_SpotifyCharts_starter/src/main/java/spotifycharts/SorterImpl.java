package spotifycharts;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class SorterImpl<E> implements Sorter<E> {

    /**
     * Sorts all items by selection or insertion sort using the provided comparator
     * for deciding relative ordening of two items
     * Items are sorted 'in place' without use of an auxiliary list or array
     * @param items
     * @param comparator
     * @return  the items sorted in place
     */
    public List<E> selInsBubSort(List<E> items, Comparator<E> comparator) {
        int n = items.size(); // Get the size of the list

        // Iterate through the list, starting from the second element
        for (int i = 1; i < n; i++) {
            E key = items.get(i); // Store the current element to be compared
            int j = i - 1; // Initialize j as the index before the current element

            // Shift elements of items[0..i-1], which are greater than key,
            // to one position ahead of their current position
            while (j >= 0 && comparator.compare(key, items.get(j)) < 0) {
                items.set(j + 1, items.get(j)); // Shift element to the right
                j--; // Move to the previous element
            }
            items.set(j + 1, key); // Place the key element at its correct position in the sorted part of the list
        }

        return items; // Return the sorted list
    }

    /**
     * Sorts all items by quick sort using the provided comparator
     * for deciding relative ordening of two items
     * Items are sorted 'in place' without use of an auxiliary list or array
     * @param items
     * @param comparator
     * @return  the items sorted in place
     */
    public List<E> quickSort(List<E> items, Comparator<E> comparator) {
        // Base case: If the list is null or contains 1 element, it is already sorted
        if (items == null || items.size() <= 1) {
            return items; // Return the list as it is
        }

        // Select a pivot element from the middle of the list
        int pivotIndex = items.size() / 2;
        E pivot = items.get(pivotIndex);

        // Create separate lists to hold elements less than, equal to, and greater than the pivot
        List<E> lesser = new ArrayList<>();
        List<E> equal = new ArrayList<>();
        List<E> greater = new ArrayList<>();

        // Partition the items into lesser, equal, and greater lists based on the pivot
        for (E item : items) {
            int cmp = comparator.compare(item, pivot);
            if (cmp < 0) {
                lesser.add(item); // Elements smaller than the pivot
            } else if (cmp > 0) {
                greater.add(item); // Elements greater than the pivot
            } else {
                equal.add(item); // Elements equal to the pivot
            }
        }

        // Recursively apply quicksort to the lesser and greater lists
        lesser = quickSort(lesser, comparator);
        greater = quickSort(greater, comparator);

        // Clear the original list and merge the sorted lesser, equal, and greater lists
        items.clear();
        items.addAll(lesser);
        items.addAll(equal);
        items.addAll(greater);

        return items; // Return the sorted list
    }


    /**
     * Identifies the lead collection of numTops items according to the ordening criteria of comparator
     * and organizes and sorts this lead collection into the first numTops positions of the list
     * with use of (zero-based) heapSwim and heapSink operations.
     * The remaining items are kept in the tail of the list, in arbitrary order.
     * Items are sorted 'in place' without use of an auxiliary list or array or other positions in items
     * @param numTops       the size of the lead collection of items to be found and sorted
     * @param items
     * @param comparator
     * @return              the items list with its first numTops items sorted according to comparator
     *                      all other items >= any item in the lead collection
     */
    public List<E> topsHeapSort(int numTops, List<E> items, Comparator<E> comparator) {

        // the lead collection of numTops items will be organised into a (zero-based) heap structure
        // in the first numTops list positions using the reverseComparator for the heap condition.
        // that way the root of the heap will contain the worst item of the lead collection
        // which can be compared easily against other candidates from the remainder of the list
        Comparator<E> reverseComparator = comparator.reversed();

        // initialise the lead collection with the first numTops items in the list
        for (int heapSize = 2; heapSize <= numTops; heapSize++) {
            // repair the heap condition of items[0..heapSize-2] to include new item items[heapSize-1]
            heapSwim(items, heapSize, reverseComparator);
        }

        // insert remaining items into the lead collection as appropriate
        for (int i = numTops; i < items.size(); i++) {
            // loop-invariant: items[0..numTops-1] represents the current lead collection in a heap data structure
            //  the root of the heap is the currently trailing item in the lead collection,
            //  which will lose its membership if a better item is found from position i onwards
            E item = items.get(i);
            E worstLeadItem = items.get(0);
            if (comparator.compare(item, worstLeadItem) < 0) {
                // item < worstLeadItem, so shall be included in the lead collection
                items.set(0, item);
                // demote worstLeadItem back to the tail collection, at the orginal position of item
                items.set(i, worstLeadItem);
                // repair the heap condition of the lead collection
                heapSink(items, numTops, reverseComparator);
            }
        }

        // the first numTops positions of the list now contain the lead collection
        // the reverseComparator heap condition applies to this lead collection
        // now use heapSort to realise full ordening of this collection
        for (int i = numTops-1; i > 0; i--) {
            // loop-invariant: items[i+1..numTops-1] contains the tail part of the sorted lead collection
            // position 0 holds the root item of a heap of size i+1 organised by reverseComparator
            // this root item is the worst item of the remaining front part of the lead collection

            //swap index zero and i
            swap(items, 0, i);
            //use heapsink to repair the heap property(invariant)
            heapSink(items, i, reverseComparator);
        }

        return items;
    }

    /**
     * Repairs the zero-based heap condition for items[heapSize-1] on the basis of the comparator
     * all items[0..heapSize-2] are assumed to satisfy the heap condition
     * The zero-bases heap condition says:
     *                      all items[i] <= items[2*i+1] and items[i] <= items[2*i+2], if any
     * or equivalently:     all items[i] >= items[(i-1)/2]
     * @param items
     * @param heapSize
     * @param comparator
     */
    protected void heapSwim(List<E> items, int heapSize, Comparator<E> comparator) {
        int k = heapSize - 1; // Start with the index of the last element in the heap
        while (k > 0) { // Continue while k is greater than 0 (not the root)
            int parent = (k - 1) / 2; // Calculate the index of the parent node

            // Compare the current element (at index k) with its parent node
            if (comparator.compare(items.get(k), items.get(parent)) >= 0) {
                // If the current element is greater than or equal to its parent, the heap condition is satisfied
                break; // Exit the loop as the heap property is preserved
            }

            // If the current element is smaller than its parent, swap them to maintain the heap property
            swap(items, k, parent); // Swap the elements at indices k and parent
            k = parent; // Move to the parent index for further comparison
        }
    }
    /**
     * Repairs the zero-based heap condition for its root items[0] on the basis of the comparator
     * all items[1..heapSize-1] are assumed to satisfy the heap condition
     * The zero-bases heap condition says:
     *                      all items[i] <= items[2*i+1] and items[i] <= items[2*i+2], if any
     * or equivalently:     all items[i] >= items[(i-1)/2]
     * @param items
     * @param heapSize
     * @param comparator
     */
    protected void heapSink(List<E> items, int heapSize, Comparator<E> comparator) {
        int k = 0; // Start from the root of the heap
        while (2 * k + 1 < heapSize) { // Check if the current node has at least one child
            int child = 2 * k + 1; // Calculate the index of the left child node

            // If the right child exists and is smaller than the left child, consider the right child
            if (child < heapSize - 1 && comparator.compare(items.get(child), items.get(child + 1)) > 0) {
                child++; // Move to the right child
            }

            // Compare the current element with its smaller child
            if (comparator.compare(items.get(k), items.get(child)) <= 0) {
                // If the current element is smaller than or equal to its smallest child, heap property is preserved
                break; // Exit the loop as the heap condition is satisfied
            }

            // If the current element is larger than its smallest child, swap them to maintain the heap property
            swap(items, k, child); // Swap the elements at indices k and child
            k = child; // Move to the child index for further comparison in the next iteration
        }
    }

    /**
     * Helper method to swap two items in the list.
     * @param items
     * @param i
     * @param j
     */
    private void swap(List<E> items, int i, int j) {
        E temp = items.get(i);
        items.set(i, items.get(j));
        items.set(j, temp);
    }
}
