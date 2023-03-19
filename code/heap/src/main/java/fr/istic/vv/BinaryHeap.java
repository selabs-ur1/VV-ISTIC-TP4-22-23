package fr.istic.vv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {
    private List<T> heap;
    private Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    /**
     * is responsible for moving the new root element down the heap until it reaches the correct position.
     * @param i the element
     */
    private void shiftDown(int i) {
        int leftChildIndex = 2 * i + 1;
        int rightChildIndex = 2 * i + 2;
        int smallIndex = i;
        if ((leftChildIndex < heap.size()) && (comparator.compare(heap.get(leftChildIndex), heap.get(smallIndex)) < 0)) {
            smallIndex = leftChildIndex;
        }
        if ((rightChildIndex < heap.size()) && (comparator.compare(heap.get(rightChildIndex), heap.get(smallIndex)) < 0)) {
            smallIndex = rightChildIndex;
        }
        if (smallIndex != i) {
            // swapping
            T tmp = heap.get(i);
            heap.set(i, heap.get(smallIndex));
            heap.set(smallIndex, tmp);
            shiftDown(smallIndex);
        }
    }

    /**
     * responsible for moving a newly added element up the heap until it reaches the correct position.
     * @param i the element
     */
    public void shiftUp(int i) {
        int parentIndex = (i - 1) / 2;
        if ((parentIndex >= 0) && (comparator.compare(heap.get(i), heap.get(parentIndex)) < 0)) {
            // swapping
            T tmp = heap.get(i);
            heap.set(i, heap.get(parentIndex));
            heap.set(parentIndex, tmp);
            shiftUp(parentIndex);
        }
    }


    /**
     * Return and remove the minimum object in the heap
     * @return the minimum object de type T
     */
    public T pop() {
        if (this.heap.isEmpty()) {
            throw new NoSuchElementException("THe heap is empty");
        }

        // Get the minimum (head of heap)
        T min = this.heap.get(0);
        T last = this.heap.remove(this.heap.size() - 1);

        if (!this.heap.isEmpty()) {
            this.heap.set(0, last);
            shiftDown(0);
        }

        return min;
    }

    /**
     * Return the minimum object in the heap
     * @return
     */
    public T peek() {
        if (this.heap.isEmpty()) {
            throw new NoSuchElementException("The heap is empty");
        }

        return this.heap.get(0);
    }

    /**
     * Add new element to the heap
     * @param element
     */
    public void push(T element) {
        if (element == null) {
            throw new RuntimeException("Null values not allowed");
        }
        heap.add(element);
        shiftUp(heap.size() - 1);
    }

    /**
     * Return the number of elements in the heap
     * @return Integer
     */
    public int count() {
        return this.heap.size();
    }

    /**
     * Get heap values
     * @return
     */
    public List<T>  getHeap() {
        return new ArrayList<>(heap);
    }
}
