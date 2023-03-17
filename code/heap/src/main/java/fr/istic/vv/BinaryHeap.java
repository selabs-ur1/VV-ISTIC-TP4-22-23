package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {

    private List<T> heap;
    private Comparator<T> comparator ;

    public BinaryHeap(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    public T pop() {
        if(heap.isEmpty()){
            throw new NoSuchElementException();
        }
        T mimimumElement = heap.get(0);
        int indexMimimumElement = 0;
        for(int i = 0 ; i < heap.size() ; i++){
            if(comparator.compare(heap.get(i), mimimumElement) < 0){
                mimimumElement = heap.get(i);
                indexMimimumElement = i;
            }
        }
        return heap.remove(indexMimimumElement);
    }

    public T peek() {
        if(heap.isEmpty()){
            throw new NoSuchElementException();
        }
        T mimimumElement = heap.get(0);
        for(int i = 0 ; i < heap.size() ; i++){
            if(comparator.compare(heap.get(i), mimimumElement) < 0){
                mimimumElement = heap.get(i);
            }
        }
        return mimimumElement;
    }

    public void push(T element) {
        heap.add(element);
    }

    public int count() {
        return heap.size();
    }

}