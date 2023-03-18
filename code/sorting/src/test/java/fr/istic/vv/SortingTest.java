package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.engine.properties.Range;
import org.assertj.core.api.Assertions;

import java.util.*;

public class SortingTest {

    @Property
    void testBubbleSort(@ForAll @Size(min = 1, max = 20)List<@IntRange(min = -114, max = 514) Integer> input) {
        Integer[] array = input.toArray(Integer[]::new);

        Comparator<Integer> comparator = Comparator.naturalOrder();

        Sorting.bubblesort(array, comparator);

        Assertions.assertThat(array)
                .isSorted()
                .isSortedAccordingTo(comparator)
                .containsExactlyInAnyOrderElementsOf(input);
    }

    @Property
    void testMergeSort(@ForAll @Size(min = 1, max = 20)List<@IntRange(min = -810, max = 1910) Integer> input) {
        Integer[] array = input.toArray(Integer[]::new);

        Comparator<Integer> comparator = Comparator.naturalOrder();

        Sorting.mergesort(array, comparator);

        Assertions.assertThat(array)
                .isSorted()
                .isSortedAccordingTo(comparator)
                .containsExactlyInAnyOrderElementsOf(input);
    }

    @Property
    void testQuickSort(@ForAll @Size(min = 1, max = 20)List<@IntRange(min = -492, max = 1033) Integer> input) {
        Integer[] array = input.toArray(Integer[]::new);

        Comparator<Integer> comparator = Comparator.naturalOrder();

        Sorting.quicksort(array, comparator);

        Assertions.assertThat(array)
                .isSorted()
                .isSortedAccordingTo(comparator)
                .containsExactlyInAnyOrderElementsOf(input);
    }

    @Property
    void testBubbleSortWithStrings(@ForAll @Size(min = 1, max = 20)List<String> input) {
        String[] array = input.toArray(String[]::new);

        Comparator<String> comparator = Comparator.naturalOrder();

        Sorting.bubblesort(array, comparator);

        Assertions.assertThat(array)
                .isSorted()
                .isSortedAccordingTo(comparator)
                .containsExactlyInAnyOrderElementsOf(input);
    }

    @Property
    void testMergeSortWithCharacters(@ForAll @Size(min = 1, max = 20)List<@IntRange(min = -810, max = 1910) Character> input) {
        Character[] array = input.toArray(Character[]::new);

        Comparator<Character> comparator = Comparator.naturalOrder();

        Sorting.mergesort(array, comparator);

        Assertions.assertThat(array)
                .isSorted()
                .isSortedAccordingTo(comparator)
                .containsExactlyInAnyOrderElementsOf(input);
    }

}