package fr.istic.vv;

import java.util.*;
import java.util.stream.Collectors;

public class BinaryHeap<T> {

    private List<T> array;

    private Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.array = new ArrayList<>();
        this.comparator = comparator;
    }

    private static int parent(int index) {
        return (index - 1) / 2;
    }

    private static int leftSubNode(int index) {
        return index * 2 + 1;
    }

    private static int rightSubNode(int index) {
        return (index + 1) * 2;
    }

    private void swap(int posa, int posb) {
        T value = array.get(posa);
        array.set(posa, array.get(posb));
        array.set(posb, value);
    }

    private void liftUp() {
        int index = this.array.size() - 1;
        for (;index > 0; index = parent(index)) {
            int parent = parent(index);
            if (comparator.compare(array.get(parent), array.get(index)) > 0) {
                swap(index, parent);
            } else {
                break;
            }
        }
    }

    private void sinkDown(int index) {
        int left = leftSubNode(index);
        int right = rightSubNode(index);
        int selected = index;
        if (left < array.size() &&
                comparator.compare(array.get(left), array.get(selected)) < 0) {
            selected = left;
        }
        if (right < array.size() &&
                comparator.compare(array.get(right), array.get(selected)) < 0) {
            selected = right;
        }
        if (selected != index) {
            swap(index, selected);
            sinkDown(selected);
        }
    }

    public T pop() {
        T value = array.get(0);
        swap(0, array.size() - 1);
        array.remove(array.size() - 1);
        sinkDown(0);
        return value;
    }

    public T peek() { return array.get(0); }

    public void push(T element) {
        this.array.add(element);
        liftUp();
    }

    public int count() { return array.size(); }

    @Override public String toString() {
        return String.join("", array.stream().map(Object::toString).collect(Collectors.joining(",")));
    }

}