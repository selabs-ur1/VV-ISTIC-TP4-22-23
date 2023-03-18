package fr.istic.vv;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) {
        T tmp;
        for (var i = 0; i < array.length - 1; i++) {
            boolean identical = false;
            for (var j = 0; j < array.length - i - 1; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    identical = true;
                }
            }
            if (!identical) {
                break;
            }
        }
        return array;
    }


    public static <T> T[] quicksort(T[] array, Comparator<T> comparator)  {
        return quicksortHelper(array, 0, array.length - 1, comparator);
    }

    private static <T> T[] quicksortHelper(T[] array, int begin, int end, Comparator<T> comparator) {
        if (begin >= 0 && end >= 0 && begin < end) {
            int pivot = partition(array, begin, end, comparator);
            quicksortHelper(array, begin, pivot,comparator);
            quicksortHelper(array, pivot + 1, end, comparator);
        }
        return array;
    }

    private static <T> int partition(T[] array, int begin, int end, Comparator<T> comparator) {
        T pivot = array[(end - begin) / 2 + begin];
        int i = begin - 1;
        int j = end + 1;
        while (true) {
            do {
                i ++;
            } while (comparator.compare(array[i], pivot) < 0) ;
            do {
                j --;
            } while (comparator.compare(array[j], pivot) > 0) ;
            if (i >= j) return j;

            T tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        if (array.length <= 1) {
            return array;
        }
        var mid = array.length / 2;
        var left = Arrays.copyOfRange(array, 0, mid);
        var right = Arrays.copyOfRange(array, mid, array.length);
        mergesort(left, comparator);
        mergesort(right, comparator);
        merge(array, left, right, comparator);
        return array;
    }

    private static <T> void merge(T[] src, T[] left, T[] right, Comparator<T> comparator) {
        var leftPos = 0;
        var rightPos = 0;
        var mainPos = 0;
        while (leftPos < left.length && rightPos < right.length) {
            if (comparator.compare(left[leftPos], right[rightPos]) <= 0) {
                src[mainPos++] = left[leftPos++];
            } else {
                src[mainPos++] = right[rightPos++];
            }
        }
        while (leftPos < left.length) {
            src[mainPos++] = left[leftPos++];
        }
        while (rightPos < right.length) {
            src[mainPos++] = right[rightPos++];
        }
    }

}
