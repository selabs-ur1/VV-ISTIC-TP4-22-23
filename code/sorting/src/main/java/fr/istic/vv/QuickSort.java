package fr.istic.vv;

import java.lang.reflect.Array;
import java.util.Comparator;

import static fr.istic.vv.Sorting.newEmptyArrayTypeT;

public class QuickSort<T> {
    public T[] lowers;
    public T[] highers;
    public boolean allEqual = false;

    public QuickSort(T[] source, Comparator<T> comparator) {
        sortOnce(source, comparator);
    }

    public static <T> T[] combine(T[] newLowers, T[] newHighers) {
        T[] finalArray = (T[]) Array.newInstance(newLowers.getClass().getComponentType(), newLowers.length + newHighers.length);

        System.arraycopy(newLowers, 0, finalArray, 0, newLowers.length);
        System.arraycopy(newHighers, 0, finalArray, newLowers.length, newHighers.length);

        return finalArray;
    }

    private void sortOnce(T[] source, Comparator<T> comparator) {
        int middleIndex = source.length/2;
        Class<?> usedType = source.getClass().getComponentType();

        T[] lowersTemp = newEmptyArrayTypeT(usedType, source.length);
        T[] equalsTemp = newEmptyArrayTypeT(usedType, source.length);
        T[] highersTemp = newEmptyArrayTypeT(usedType, source.length);
        int lowerLength = 0;
        int equalLength = 0;
        int higherLength = 0;

        if (source.length == 2) {
            T firstElement = source[0];
            T secondElement = source[1];
            if (comparator.compare(firstElement, secondElement) < 0) {
                lowersTemp[lowerLength] = firstElement;
                highersTemp[higherLength] = secondElement;
            } else {
                lowersTemp[lowerLength] = secondElement;
                highersTemp[higherLength] = firstElement;
            }
            lowerLength++;
            higherLength++;
        } else {
            T middleElement = source[middleIndex];
            for (T currentElement : source) {
                if (comparator.compare(currentElement, middleElement) < 0) {
                    lowersTemp[lowerLength] = currentElement;
                    lowerLength++;
                } else if (comparator.compare(currentElement, middleElement) > 0) {
                    highersTemp[higherLength] = currentElement;
                    higherLength++;
                } else {
                    equalsTemp[equalLength] = currentElement;
                    equalLength++;
                }
            }
        }

        if (lowerLength == 0 && higherLength == 0) allEqual = true;

        if (lowerLength <= higherLength) {
            lowers = newEmptyArrayTypeT(usedType, lowerLength + equalLength);
            highers = newEmptyArrayTypeT(usedType, higherLength);
            System.arraycopy(lowersTemp, 0, lowers, 0, lowerLength);
            System.arraycopy(equalsTemp, 0, lowers, lowerLength, equalLength);
            System.arraycopy(highersTemp, 0, highers, 0, higherLength);
        } else {
            lowers = newEmptyArrayTypeT(usedType, lowerLength);
            highers = newEmptyArrayTypeT(usedType, higherLength + equalLength);
            System.arraycopy(lowersTemp, 0, lowers, 0, lowerLength);
            System.arraycopy(equalsTemp, 0, highers, 0, equalLength);
            System.arraycopy(highersTemp, 0, highers, equalLength, higherLength);
        }
    }
}
