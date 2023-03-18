package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


public class BinaryHeapTest {

    @Property
    void containsSameElementsInAnyOrderInSource(@ForAll @Size(min = 1, max = 20) List<@IntRange(min = -114, max = 514) Integer> input) {
        Comparator<Integer> order = Comparator.naturalOrder();

        BinaryHeap<Integer> heap = new BinaryHeap<>(order);

        List<Integer> stack = new ArrayList<>();

        var rnd = new Random();

        for (var v : input) {
            heap.push(v);
            if (rnd.nextBoolean()) {
                stack.add(heap.pop());
            }
        }

        while (heap.count() > 0) {
            stack.add(heap.pop());
        }

        Assertions.assertThat(stack)
                .containsExactlyInAnyOrderElementsOf(input);
    }

    @Property
    void ascendingOrderForMinimumHeap(@ForAll @Size(min = 1, max = 20) List<@IntRange(min = -114, max = 514) Integer> input) {
        Comparator<Integer> order = Comparator.naturalOrder();

        BinaryHeap<Integer> heap = new BinaryHeap<>(order);

        List<Integer> stack = new ArrayList<>();

        for (var v : input) {
            heap.push(v);
        }

        while (heap.count() > 0) {
            stack.add(heap.pop());
        }

        Assertions.assertThat(stack)
                .containsExactlyInAnyOrderElementsOf(input)
                .isSortedAccordingTo(order);
    }
}
