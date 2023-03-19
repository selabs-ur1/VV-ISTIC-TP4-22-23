package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

import java.util.Comparator;
import java.util.Objects;

public class SortingTest {
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll @IntRange int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property
    void differentialFuzzing(@ForAll("generateIntArray") Integer[] ints, @ForAll("generateComparator") Comparator<Integer> comparator) {
        Integer[] sorted1 = Sorting.bubblesort(ints, comparator);
        Integer[] sorted2 = Sorting.mergesort(ints, comparator);
        Integer[] sorted3 = Sorting.quicksort(ints, comparator);

        for (int i = 0; i < Objects.requireNonNull(sorted1).length; i++) {
            boolean bubbleMerge = Objects.equals(sorted1[i], Objects.requireNonNull(sorted2)[i]);
            boolean bubbleQuick = Objects.equals(sorted1[i], Objects.requireNonNull(sorted3)[i]);
            if (!bubbleQuick) {
                Sorting.printElements(sorted1);
                System.out.println("");
                Sorting.printElements(sorted3);
                System.out.println("");
            }
            if (!bubbleMerge) {
                Sorting.printElements(sorted1);
                System.out.println("");
                Sorting.printElements(sorted2);
                System.out.println("");
            }
            assert bubbleQuick && bubbleMerge;
        }
    }

    @Property
    void check1Value() {
        Integer[] a = new Integer[]{105252, 54603, -52, 2114387143, 159619, -1646};
        Integer[] sorted1 = Sorting.bubblesort(a, Comparator.naturalOrder());
        Integer[] sorted2 = Sorting.mergesort(a, Comparator.naturalOrder());
        Integer[] sorted3 = Sorting.quicksort(a, Comparator.naturalOrder());

        Sorting.printElements(sorted1);
        System.out.println("Bubble array");
        Sorting.printElements(sorted2);
        System.out.println("merge array");
        //Sorting.printElements(sorted3);
        //System.out.println("Quick array");
    }

    @Provide
    Arbitrary<Integer[]> generateIntArray() {
        return Arbitraries.integers().array(Integer[].class).ofMinSize(1).ofMaxSize(10).unique();
    }

    @Provide
    Arbitrary<Comparator<Integer>> generateComparator() {
        return Arbitraries.of(
                Comparator.naturalOrder(),
                Comparator.reverseOrder()
        );
    }
}