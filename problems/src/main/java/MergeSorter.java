import library.AssortedMethods;


/**
 * Merge sort implementation....
 *
 * Time complexity:  O(n log(n))
 *           Space:  orig array (n) + buffer (n) = 2n = O(n)
 */
public class MergeSorter implements IntegerSorter {
    @Override
    public void sort(int[] array) {
        int[] buffer = new int[array.length];
        mergeSort(array, buffer, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int[] buffer, int start, int end) {
        if (start == end) return; // ==> have a sub-array of 1 element, return to break recursion

        int mid = (start + end) / 2;

        mergeSort(array, buffer, start, mid);
        mergeSort(array, buffer, mid + 1, end);

        merge(array, buffer, start, mid, end);
    }

    private static void merge(int[] array, int[] buffer, int start, int mid, int end) {
        for (int c = start; c <= end; c++) {
            buffer[c] = array[c];
        }

        int current = start;
        int leftPointer = start;
        int rightPointer = mid + 1;

        while (leftPointer <= mid && rightPointer <= end) {
            if (buffer[leftPointer] <= buffer[rightPointer]) {
                array[current] = buffer[leftPointer];
                leftPointer++;
            } else {
                array[current] = buffer[rightPointer];
                rightPointer++;
            }

            current++;
        }

        int remaining = mid - leftPointer; // How many elements from left side not copied back into array?
        for (int r = 0; r <= remaining; r++) {
            array[current + r] = buffer[leftPointer + r];
        }
    }
}
