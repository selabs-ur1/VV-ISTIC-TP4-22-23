package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Arrays;
import java.util.Comparator;

public class SortingTest {
    @Property(shrinking = ShrinkingMode.OFF)
    public void testSortingAlgorithms(
            @ForAll("intArrays") Integer[] array,
            @ForAll("comparators") Comparator<Integer> comparator) {

        Integer[] expected = Arrays.copyOf(array, array.length);
        Arrays.sort(expected, comparator);

        Integer[] actual1 = Sorting.bubblesort(Arrays.copyOf(array, array.length), comparator);
        Integer[] actual2 = Sorting.quicksort(Arrays.copyOf(array, array.length), comparator);
        Integer[] actual3 = Sorting.mergesort(Arrays.copyOf(array, array.length), comparator);

        assert Arrays.equals(actual1, expected);
        assert Arrays.equals(actual2, expected);
        assert Arrays.equals(actual3, expected);
    }

    Arbitrary<Integer[]> intArrays() {
        Arbitrary<Integer> integerArbitrary = Arbitraries.integers().between(1, 10);
        return integerArbitrary.array(Integer[].class).ofMinSize(2).ofMaxSize(5);
    }
    Arbitrary<Comparator<Integer>> comparators() {
        return Arbitraries.of(
                Comparator.naturalOrder(),
                Comparator.reverseOrder(),
                Comparator.comparingInt(i -> i % 10),
                Comparator.comparingInt(i -> Integer.bitCount(i))
        );
    }
}