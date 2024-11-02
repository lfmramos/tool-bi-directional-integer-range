package io.codeforall.fanstatics;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A Range class that implements bidirectional iteration over integers.
 * Supports forward and reverse iteration through the same collection.
 * Features:
 * - Supports iteration over a range of integers (inclusive of min and max)
 * - Allows elements to be removed during iteration
 * - Maintains original order when switching between forward/reverse iteration
 * - Thread-safe due to immutable range boundaries
 */
public class Range implements Iterable<Integer> {

    // Immutable boundaries of the range
    private final int originalMin;
    private final int originalMax;

    // Controls iteration direction (forward/reverse)
    private boolean isInverted;

    // Tracks which numbers have been removed from iteration
    private boolean[] removed;

    /**
     * Creates a new Range instance with specified boundaries.
     * Automatically determines the true min/max regardless of parameter order.
     * @param min The lower or upper bound of the range
     * @param max The upper or lower bound of the range
     */
    public Range(int min, int max) {
        this.originalMin = Math.min(min, max);
        this.originalMax = Math.max(min, max);
        this.removed = new boolean[originalMax - originalMin + 1];
        this.isInverted = false;
    }

    /**
     * Sets the iteration direction.
     * @param isInverted true for reverse iteration, false for forward iteration
     */
    public void setInverted(boolean isInverted) {
        this.isInverted = isInverted;
    }

    /**
     * Creates an appropriate iterator based on current direction setting.
     * @return Iterator<Integer> for either forward or reverse iteration
     */
    @Override
    public Iterator<Integer> iterator() {
        if(isInverted) {
            return new ReverseRangeIterator();
        }
        return new FwdRangeIterator();
    }

    /**
     * Forward iterator implementation that traverses from min to max.
     * Supports element removal and handles gaps from removed elements.
     */
    private class FwdRangeIterator implements Iterator<Integer> {
        private int current;

        /**
         * Initializes iterator to start just before the first valid element
         */
        public FwdRangeIterator() {
            // start this iterator with the "current number" just before the min value.
            // You need to call next() to get the first element.
            current = originalMin - 1;
        }

        /**
         * Checks if more elements exist, accounting for removed elements
         * @return true if there are more elements to iterate over
         */
        @Override
        public boolean hasNext() {
            // check if the next number is removed.
            // if it is.... keep checking if the following numbers are removed.
            while (removedContains(current + 1)) {
                current++;
            }
            return current < originalMax;
        }

        /**
         * Checks if a number exists in the removed set
         * @param current number to check
         * @return true if number is in range and has been removed
         */
        private boolean removedContains(int current) {
            // First we need to check if the current number above the max value
            return current >= originalMin && current <= originalMax && removed[current - originalMin];
        }

        /**
         * Returns the next available number in the sequence
         * @return next integer in the range
         * @throws NoSuchElementException if no more elements exist
         */
        @Override
        public Integer next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            current++;
            return current;
        }

        /**
         * Removes the current element from the iteration
         * @throws IllegalStateException if called before next() or called repeatedly
         */
        @Override
        public void remove() {
            // if you try to remove an element before calling next(), you will get a runtime exception (see Iterable:remove() description)
            if (current < originalMin) {
                throw new IllegalStateException("You need to call next() at least once to remove an element.");
            }
            // if you try to call remove() consecutively you will get a runtime exception (see Iterable:remove() description)
            if (removedContains(current)) {
                throw new IllegalStateException("You cannot call remove() repeatedly");
            }
            removed[current - originalMin] = true;
        }
    }

    /**
     * Reverse iterator implementation that traverses from max to min.
     * Mirrors forward iterator functionality but in opposite direction.
     */
    private class ReverseRangeIterator implements Iterator<Integer>{
        private int current;

        /**
         * Initializes iterator to start just after the last valid element
         */
        public ReverseRangeIterator() {
            // start this iterator with the "current number" just before the min value.
            // You need to call next() to get the first element.
            this.current = originalMax + 1;
        }

        @Override
        public boolean hasNext() {
            // check if the next number is removed.
            // if it is.... keep checking if the following numbers are removed.
            while (removedContains(current - 1)) {
                current--;
            }
            return current > originalMin;
        }

        private boolean removedContains(int current) {
            // First we need to check if the current number above the max value
            return current >= originalMin && current <= originalMax && removed[current - originalMin];
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            current--;
            return current;
        }

        @Override
        public void remove() {
            // if you try to remove an element before calling next(), you will get a runtime exception (see Iterable:remove() description)
            if (current > originalMax) {
                throw new IllegalStateException("You need to call next() at least once to remove an element.");
            }
            // if you try to call remove() consecutively you will get a runtime exception (see Iterable:remove() description)
            if (removedContains(current)) {
                throw new IllegalStateException("You cannot call remove() repeatedly");
            }
            removed[current - originalMin] = true;
        }
    }
}