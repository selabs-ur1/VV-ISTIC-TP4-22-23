package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class BinaryHeapTest {
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property
    boolean testPopReturnsMinimum(@ForAll List<Integer> elements){
        if(elements.isEmpty()){
            return true;
        }

        Comparator<Integer> comparator = Comparator.naturalOrder();
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);

        for(int element : elements){
            heap.push(element);
        }
        Collections.sort(elements);

        return elements.get(0).equals(heap.pop());
    }
}