package io.codeforall.fanstatics;

import java.util.Iterator;

/**
 * Test class demonstrating the functionality of the Range class
 * Shows both forward and reverse iteration over the same range
 */
public class RangeTest {

    /**
     * Main method to demonstrate Range class capabilities
     * Creates a range from 3 to 10 and iterates over it in both directions
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {

        // Create a new Range instance with boundaries 3 to 10 (inclusive)
        Range r = new Range(3, 10);

        // Demo Part 1: Forward Iteration
        System.out.println("--- USING FORWARD ITERATOR ---");
        // Get iterator (by default in forward mode)
        Iterator<Integer> it = r.iterator();

        // Iterate through the range from 3 to 10
        while (it.hasNext()) {
            Integer i = it.next();
            System.out.println(i);
        }

        // Demo Part 2: Reverse Iteration
        System.out.println("--- USING REVERSE ITERATOR ---");
        // Switch to reverse iteration mode
        r.setInverted(true);
        // Get a new iterator (now in reverse mode)
        it = r.iterator();

        // Iterate through the range from 10 to 3
        while (it.hasNext()) {
            Integer i = it.next();
            System.out.println(i);
        }
    }
}