package fr.istic.vv;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Comparator;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Positive;
import net.jqwik.api.constraints.Size;

public class SortingTest {

    @Property
    void testBubblesort(@ForAll @Size(min = 1, max = 100) Integer[] input) {
        Comparator<Integer> comparator = Comparator.naturalOrder();

        Integer[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected, comparator);

        Sorting.bubblesort(input, comparator);
        assertArrayEquals(expected, input);
    }

    @Property
    void testQuicksort(@ForAll @Size(min = 1, max = 100) Integer[] input) {
        Comparator<Integer> comparator = Comparator.naturalOrder();

        Integer[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected, comparator);

        Sorting.quicksort(input, comparator);
        assertArrayEquals(expected, input);
    }

    @Property
    void testMergesort(@ForAll @Size(min = 1, max = 1000) @IntRange(min = Integer.MIN_VALUE, max = 1000) Integer[] input) {
        Comparator<Integer> comparator = Comparator.naturalOrder();

        Integer[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected, comparator);

        Sorting.mergesort(input, comparator);
        assertArrayEquals(expected, input);
    }
}
