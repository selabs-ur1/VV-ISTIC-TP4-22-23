# Sorting algorithms

Implement [Bubble sort](https://en.wikipedia.org/wiki/Bubble_sort), [Quicksort](https://en.wikipedia.org/wiki/Quicksort) and [Merge sort](https://en.wikipedia.org/wiki/Merge_sort) in the `Sorting` class as indicated below. The three methods return a sorted version of the original array. The comparison between the elements of the arrays is specified with an instance of `Comparator`.

```java
class Sorting {

    public static T[] bubblesort(T[] array, Comparator<T> comparator) { ... }

    public static T[] quicksort(T[] array, Comparator<T> comparator)  { ... }

    public static T[] mergesort(T[] array, Comparator<T> comparator) { ... }

}
```

Using [jqwik](https://jqwik.net/) create a differential fuzzing strategy to test the three sorting algorithms at the same time. Create the test before any sorting implementation. Document any bug you find with the help of your tests.


**NOTE:** 
- Do not use any existing implementation, write your own code. 
- Use the provided project template as a starting point.
- In the project you can launch the tests with `mvn test`.


# ANSWERS
The bugs found with the help of the differential tests are the followings:

## BubbleSort vs QuickSort
Comparator was used to organize the elements in the reverse ordre for Bullbe and QuickSort (> vs <).

## QuickSort
Infinite loop in case of :
    - Multiple times the same element needed to be sorted.
    - When the middle element chosen is already the biggest number, all lower/equal elements were put in the same array.
Not all values were correctly sorted:
A mistake was made in the stopping condition which caused some values to be missing :
    - Bubble array : -4420694 -9 -2 -2 -2 -1 0 1 140 261007128
    - Quick array : -9 -4420694 -2 -2 -2 1 261007128

## BubbleSort vs MergeSort
At first, we tried using Stacks instead of a Queue. This led to some errors due to having to repeatedly verify the order of the elements.

During the merging process of the arrays, the condition checked to merge was sourceQueues.size(), in the if-else block, we processed the arrays and stored it in the variable innerQueue ; after the if-else block that variable was added to the resultQueues variable. In case the size was == 1, the current array was directly added instead of being saved in the variable innerQueue, thus there was constantly an empty Queue that kept being added to the end of the result.


# Conclusion on the bugs found
For many values tested, no bugs were found, but after jqwik did several tries, that's when the above bugs were located.
Using the fuzzing method instead of unit tests allowed us to find and fix these bugs where those laters might have escaped out attention otherwise.