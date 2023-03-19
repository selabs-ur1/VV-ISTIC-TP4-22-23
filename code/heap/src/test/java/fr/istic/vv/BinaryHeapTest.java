package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

import java.util.*;


public class BinaryHeapTest {
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll @IntRange int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property
    boolean differentialHeapStream(@ForAll("arbitraryOfIntegers") List<Integer> listIntegers) {
        Comparator<Integer> integerComparator = Comparator.naturalOrder();
        BinaryHeap<Integer> heap = new BinaryHeap<>(integerComparator);

        listIntegers.forEach(heap::push);
        Optional<Integer> optionalMin = listIntegers.stream().min(integerComparator);

        if (optionalMin.isPresent()) {
            return Objects.equals(heap.pop(), optionalMin.get());
        } else {
            boolean noSuchElementExceptionFound;
            try {
                heap.pop();
                noSuchElementExceptionFound = false;
            } catch (NoSuchElementException noSuchElementException) {
                noSuchElementExceptionFound = true;
            }
            return noSuchElementExceptionFound;
        }
    }

    @Provide
    Arbitrary<List<Integer>> arbitraryOfIntegers() {
        return Arbitraries.integers().list();
    }
}
