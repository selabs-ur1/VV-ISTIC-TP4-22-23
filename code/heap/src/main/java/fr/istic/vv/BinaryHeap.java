package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {

    private final ArrayList<T> elements;
    private final Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.elements = new ArrayList<>();
        this.comparator = comparator;
    }

    public T pop() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T min = elements.get(0);
        T last = elements.remove(elements.size() - 1);
        if (!elements.isEmpty()) {
            elements.set(0, last);
            downHeap(0);
        }
        return min;
    }

    public T peek() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return elements.get(0);
    }

    public void push(T element) {
        elements.add(element);
        upHeap(elements.size() - 1);
    }

    public int count() {
        return elements.size();
    }

    private void upHeap(int i) {
        T node = elements.get(i);
        int parent = (i - 1) / 2;
        while (i > 0 && comparator.compare(node, elements.get(parent)) < 0) {
            elements.set(i, elements.get(parent));
            i = parent;
            parent = (i - 1) / 2;
        }
        elements.set(i, node);
    }

    private void downHeap(int i) {
        T node = elements.get(i);
        int child = 2 * i + 1;
        while (child < elements.size()) {
            if (child + 1 < elements.size() && comparator.compare(elements.get(child + 1), elements.get(child)) < 0) {
                child++;
            }
            if (comparator.compare(node, elements.get(child)) <= 0) {
                break;
            }
            elements.set(i, elements.get(child));
            i = child;
            child = 2 * i + 1;
        }
        elements.set(i, node);
    }
}
