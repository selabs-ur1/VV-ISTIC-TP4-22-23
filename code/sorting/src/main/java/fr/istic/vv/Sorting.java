package fr.istic.vv;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    public static <T> T[] bubblesort(T[] array, Comparator<T> comparator) {
        int n = array.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (comparator.compare(array[i - 1], array[i]) > 0) {
                    // Swap array[i-1] and array[i]
                    T temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
        return array;
    }


    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        return quicksort(array, comparator, 0, array.length - 1);
    }

    private static <T> T[] quicksort(T[] array, Comparator<T> comparator, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(array, comparator, left, right);
            quicksort(array, comparator, left, pivotIndex - 1);
            quicksort(array, comparator, pivotIndex + 1, right);
        }
        return array;
    }

    private static <T> int partition(T[] array, Comparator<T> comparator, int left, int right) {
        T pivot = array[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;
                // Swap array[i] and array[j]
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        // Swap array[i+1] and array[right]
        T temp = array[i + 1];
        array[i + 1] = array[right];
        array[right] = temp;
        return i + 1;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        int n = array.length;
        if (n < 2) {
            return array;
        }
        int mid = n / 2;
        T[] left = Arrays.copyOfRange(array, 0, mid);
        T[] right = Arrays.copyOfRange(array, mid, n);
        mergesort(left, comparator);
        mergesort(right, comparator);
        merge(array, left, right, comparator);
        return array;
    }

    private static <T> void merge(T[] array, T[] left, T[] right, Comparator<T> comparator) {
        int leftIndex = 0;
        int rightIndex = 0;
        int index = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (comparator.compare(left[leftIndex], right[rightIndex]) <= 0) {
                array[index++] = left[leftIndex++];
            } else {
                array[index++] = right[rightIndex++];
            }
        }

        while (leftIndex < left.length) {
            array[index++] = left[leftIndex++];
        }

        while (rightIndex < right.length) {
            array[index++] = right[rightIndex++];
        }
    }


}
