package fr.istic.vv;
import java.util.Comparator;

public class Sorting {

    public static <T> T[] bubblesort(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        quicksort(array, 0, array.length - 1, comparator);
        return array;
    }

    private static <T> void quicksort(T[] array, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, comparator);
            quicksort(array, low, pivotIndex - 1, comparator);
            quicksort(array, pivotIndex + 1, high, comparator);
        }
    }

    private static <T> int partition(T[] array, int low, int high, Comparator<T> comparator) {
        T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        T temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        if (array.length <= 1) {
            return array;
        }
        int middle = array.length / 2;
        T[] left = (T[]) new Object[middle];
        T[] right = (T[]) new Object[array.length - middle];
        for (int i = 0; i < middle; i++) {
            left[i] = array[i];
        }
        for (int i = middle; i < array.length; i++) {
            right[i - middle] = array[i];
        }
        left = mergesort(left, comparator);
        right = mergesort(right, comparator);
        return merge(left, right, comparator);
    }

    private static <T> T[] merge(T[] left, T[] right, Comparator<T> comparator) {
        T[] result = (T[]) new Object[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                result[k] = left[i];
                i++;
            } else {
                result[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < left.length) {
            result[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length) {
            result[k] = right[j];
            j++;
            k++;
        }
        return result;
    }
}
