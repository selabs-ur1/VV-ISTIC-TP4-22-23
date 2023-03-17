package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {

    private ArrayList<T> heap;
    private Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }

    public T pop() throws Exception {
        T min = heap.get(0), last = heap.remove(heap.size() - 1);

        if (heap.size() == 0) {
            throw new NoSuchElementException();
        }

        if (heap.size() > 0) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return min;
    }

    public T peek() {
        if (heap.size() == 0) {
            throw new NoSuchElementException();
        }
        return heap.get(0);
    }


    public void push(T element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }


    public int count() {
        return heap.size();
    }

    private void heapifyUp(int i) {
        while (i > 0) {
            int p = (i - 1) / 2;
            if (comparator.compare(heap.get(i), heap.get(p)) >= 0) {
                break;
            }
            swap(i, p);
            i = p;
        }
    }

    private void heapifyDown(int i) {
        int left = 2 * i + 1, right = 2 * i + 2, smallest = i;
        if (left < heap.size() && comparator.compare(heap.get(left), heap.get(smallest)) < 0) {
            smallest = left;
        }

        if (right < heap.size() && comparator.compare(heap.get(right), heap.get(smallest)) < 0) {
            smallest = right;
        }

        if (smallest != i) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

}