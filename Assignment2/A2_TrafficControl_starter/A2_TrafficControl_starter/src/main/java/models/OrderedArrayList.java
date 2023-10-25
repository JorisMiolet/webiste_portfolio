package models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class OrderedArrayList<E>
        extends ArrayList<E>
        implements OrderedList<E> {

    protected Comparator<? super E> sortOrder;   // the comparator that has been used with the latest sort
    protected int nSorted;                       // the number of sorted items in the first section of the list
    // representation-invariant
    //      all items at index positions 0 <= index < nSorted have been ordered by the given sortOrder comparator
    //      other items at index position nSorted <= index < size() can be in any order amongst themselves
    //              and also relative to the sorted section

    public OrderedArrayList() {
        this(null);
    }

    public OrderedArrayList(Comparator<? super E> sortOrder) {
        super();
        this.sortOrder = sortOrder;
        this.nSorted = 0;
    }

    public Comparator<? super E> getSortOrder() {
        return this.sortOrder;
    }

    @Override
    public void clear() {
        super.clear();
        this.nSorted = 0;
    }

    @Override
    public void sort(Comparator<? super E> c) {
        super.sort(c);
        this.sortOrder = c;
        this.nSorted = this.size();
    }


    @Override
    public void add(int index, E element) {
        if (index <= nSorted) {
            super.add(index, element);
            if (index == 0) index++;
            nSorted = index;
            return;
        }
        super.add(index, element);

    }


    @Override
    public E remove(int index) {
        E removed = null;
        removed = super.remove(index);

        if (index >= 0 && index < nSorted) nSorted--;

        return removed;
    }

    @Override
    public boolean remove(Object o) {

        if (indexOf(o) >= 0) {
            boolean removed = super.remove(o);
            if (indexOf(o) < nSorted) nSorted--;
            return removed;
        }
        return false;
    }

    @Override
    public void sort() {
        if (this.nSorted < this.size()) {
            this.sort(this.sortOrder);
        }
    }

    @Override
    public int indexOf(Object item) {
        // efficient search can be done only if you have provided an sortOrder for the list
        if (this.getSortOrder() != null) {
            return indexOfByIterativeBinarySearch((E) item);
        } else {
            return super.indexOf(item);
        }
    }

    @Override
    public int indexOfByBinarySearch(E searchItem) {
        if (searchItem != null) {
            // some arbitrary choice to use the iterative or the recursive version
            return indexOfByRecursiveBinarySearch(searchItem);
        } else {
            return -1;
        }
    }

    /**
     * finds the position of the searchItem by an iterative binary search algorithm in the
     * sorted section of the arrayList, using the this.sortOrder comparator for comparison and equality test.
     * If the item is not found in the sorted section, the unsorted section of the arrayList shall be searched by linear search.
     * The found item shall yield a 0 result from the this.sortOrder comparator, and that need not to be in agreement with the .equals test.
     * Here we follow the comparator for sorting items and for deciding on equality.
     *
     * @param searchItem the item to be searched on the basis of comparison by this.sortOrder
     * @return the position index of the found item in the arrayList, or -1 if no item matches the search item.
     */
    public int indexOfByIterativeBinarySearch(E searchItem) {
        int from = 0;
        int to = nSorted - 1;

        while (from <= to) {
            int midIndex = (from + to) / 2;
            int compared = getSortOrder().compare(get(midIndex), searchItem);

            if (compared == 0) {
                return midIndex;
            } else if (compared < 0) {
                from = midIndex + 1;
            } else {
                to = midIndex - 1;
            }
        }

        return linearSearch(searchItem, nSorted, size() - 1);  // nothing was found ???
    }

    /**
     * finds the position of the searchItem by a recursive binary search algorithm in the
     * sorted section of the arrayList, using the this.sortOrder comparator for comparison and equality test.
     * If the item is not found in the sorted section, the unsorted section of the arrayList shall be searched by linear search.
     * The found item shall yield a 0 result from the this.sortOrder comparator, and that need not to be in agreement with the .equals test.
     * Here we follow the comparator for sorting items and for deciding on equality.
     *
     * @param searchItem the item to be searched on the basis of comparison by this.sortOrder
     * @return the position index of the found item in the arrayList, or -1 if no item matches the search item.
     */
    public int indexOfByRecursiveBinarySearch(E searchItem) {

        int itemFound = binarySearchRecursive(searchItem, 0, nSorted - 1);
        if (itemFound == -1) {
            itemFound = linearSearch(searchItem, nSorted, size() - 1);
        }

        return itemFound;
    }

    private int binarySearchRecursive(E searchItem, int from, int to) {
        if (from > to) {
            return -1;
        }
        int midIndex = (from + to) / 2;

        int compared = getSortOrder().compare(get(midIndex), searchItem);

        if (compared == 0) {
            return midIndex;
        } else if (compared < 0) {
            return binarySearchRecursive(searchItem, midIndex + 1, to);
        } else {
            return binarySearchRecursive(searchItem, from, midIndex - 1);
        }
    }

    private int linearSearch(E searchItem, int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            if (getSortOrder().compare(get(i), searchItem) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * finds a match of newItem in the list and applies the merger operator with the newItem to that match
     * i.e. the found match is replaced by the outcome of the merge between the match and the newItem
     * If no match is found in the list, the newItem is added to the list.
     *
     * @param newItem
     * @param merger  a function that takes two items and returns an item that contains the merged content of
     *                the two items according to some merging rule.
     *                e.g. a merger could add the value of attribute X of the second item
     *                to attribute X of the first item and then return the first item
     * @return whether a new item was added to the list or not
     */
    @Override
    public boolean merge(E newItem, BinaryOperator<E> merger) {
        if (newItem == null) return false;
        int matchedItemIndex = this.indexOfByRecursiveBinarySearch(newItem);

        if (matchedItemIndex < 0) {
            this.add(newItem);
            return true;
        } else {
            E matchedItem = get(matchedItemIndex);
            E mergedItem = merger.apply(matchedItem, newItem);
            this.set(matchedItemIndex, mergedItem);


            return false;
        }
    }

    /**
     * calculates the total sum of contributions of all items in the list
     *
     * @param mapper a function that calculates the contribution of a single item
     * @return the total sum of all contributions
     */
    @Override
    public double aggregate(Function<E, Double> mapper) {
        double sum = 0.0;

        for (E item : this) {
            double contribution = mapper.apply(item);
            sum += contribution;
        }

        return sum;
    }
}
