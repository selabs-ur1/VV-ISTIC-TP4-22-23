package fr.istic.vv;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.DisplayName;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

@DisplayName("BinaryHeap Test")
public class BinaryHeapTest {

    Comparator<Integer> ascendingOrder = Comparator.naturalOrder();

    @Property
    void popReturnsMinimumElement(@ForAll @IntRange(min = 1, max = 100) List<Integer> elements) {
        BinaryHeap<Integer> heap = new BinaryHeap<>(ascendingOrder);

        for (Integer element : elements) {
            heap.push(element);
        }

        Collections.sort(elements);

        for (int i = 0; i < elements.size(); i++) {
            Integer min = heap.pop();
            assertNotNull(min);
            System.out.println("elements.get(i) = " + elements.get(i) + " | min = " + min);
            assertEquals(elements.get(i), min);
        }
    }
}
