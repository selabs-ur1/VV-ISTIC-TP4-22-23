package fr.istic.vv;
import java.lang.reflect.Array;
import java.util.*;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) {
        boolean moved = true;
        T[] newArray = array.clone();

        while (moved) {
            moved = false;

            for (int i=0; i < newArray.length - 1; i++) {
                T a = newArray[i];
                T b = newArray[i+1];

                if (comparator.compare(a, b) > 0) {
                    newArray[i] = b;
                    newArray[i+1] = a;
                    moved = true;
                }
            }
        }
        return newArray;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator)  {
        T[] fullySorted;

        if (array.length > 1) {
            QuickSort<T> quickSort = new QuickSort<>(array, comparator);

            if (quickSort.allEqual) {
                fullySorted = quickSort.lowers;
            } else {
                T[] lowers = quickSort.lowers;
                T[] highers = quickSort.highers;

                T[] quickSortLowers = quicksort(lowers, comparator);
                T[] quickSortHighers = quicksort(highers, comparator);

                fullySorted = QuickSort.combine(quickSortLowers, quickSortHighers);
            }
        } else {
            fullySorted = array;
        }
        return fullySorted;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        Queue<Queue<T>> sourceQueues = splitArray(array);
        Queue<Queue<T>> resultQueue = fullMerge(sourceQueues, comparator);
        Class<?> usedType = array.getClass().getComponentType();

        return queueQueueToArray(resultQueue, usedType, array.length);
    }

    private static <T> T[] queueQueueToArray(Queue<Queue<T>> resultQueues, Class<?> usedType, int totalLength) {
        T[] newArray = newEmptyArrayTypeT(usedType, totalLength);
        int i = 0;

        while (resultQueues.size() > 0) {
            Queue<T> queue = resultQueues.remove();
            while (queue.size() > 0) {
                newArray[i] = queue.remove();
                i++;
            }
        }
        return newArray;
    }

    private static <T> Queue<Queue<T>> fullMerge(Queue<Queue<T>> sourceQueues, Comparator<T> comparator) {
        if (sourceQueues.size() < 2) {
            return sourceQueues;
        } else {
            Queue<Queue<T>> currentCycle = oneMergeCycle(sourceQueues, comparator);
            return fullMerge(currentCycle, comparator);
        }
    }

    private static <T> Queue<Queue<T>> oneMergeCycle(Queue<Queue<T>> sourceQueues, Comparator<T> comparator) {
        Queue<Queue<T>> resultQueues = new ArrayDeque<>();
        Queue<T> queue1;
        Queue<T> queue2;

        while (sourceQueues.size() > 0) {
            Queue<T> innerQueue = new ArrayDeque<>();

            if (sourceQueues.size() == 1) {
                // case : 1 queue remains in sourceQueues
                innerQueue = sourceQueues.remove();
            } else {
                queue1 = sourceQueues.remove();
                queue2 = sourceQueues.remove();
                while (queue1.size() > 0 || queue2.size() > 0) {
                    // set elements
                    T element1 = null;
                    T element2 = null;
                    if (queue1.size() > 0) {
                        element1 = queue1.peek();
                    }
                    if (queue2.size() > 0) {
                        element2 = queue2.peek();
                    }

                    // case only 1 element has a value
                    if (element1 == null && element2 != null) {
                        innerQueue.add(queue2.remove());
                        continue;
                    }
                    if (element1 != null && element2 == null) {
                        innerQueue.add(queue1.remove());
                        continue;
                    }

                    // case both elements have a value
                    if (comparator.compare(element1, element2) <= 0) {
                        innerQueue.add(queue1.remove());
                    } else {
                        innerQueue.add(queue2.remove());
                    }
                }
            }
            resultQueues.add(innerQueue);
        }

        return resultQueues;
    }

    private static <T> Queue<Queue<T>> splitArray(T[] array) {
        Queue<Queue<T>> mainDeque = new ArrayDeque<>();

        for (T element : array) {
            Queue<T> innerQueue = new ArrayDeque<>();
            innerQueue.add(element);
            mainDeque.add(innerQueue);
        }
        return mainDeque;
    }

    public static <T> void printElements(T[] array) {
        for (T t : array) {
            System.out.print(t + " ");
        }
    }

    public static <T> T[] newEmptyArrayTypeT(Class<?> type, int length) {
        return (T[]) Array.newInstance(type, length);
    }
}
