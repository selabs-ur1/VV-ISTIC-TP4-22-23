package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class BinaryHeapTest {
    @Property
    void pop_returns_minimum_element(
            @ForAll @Size(min = 1, max = 100) List<Integer> elements
    ) throws Exception {
        BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.naturalOrder());
        elements.forEach(heap::push);
        Collections.sort(elements);
        for (int element : elements) {
            Assertions.assertEquals(element, heap.pop());
        }
    }
}
