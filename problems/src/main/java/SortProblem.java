import library.AssortedMethods;

public class SortProblem {
    public static void main(String[] args) {
        int[] array = AssortedMethods.randomArray(100, 1, 100000);
        AssortedMethods.printIntArray(array);

        IntegerSorter sorter = new MergeSorter();
        sorter.sort(array);

        AssortedMethods.printIntArray(array);
        System.out.println("Is sorted?  " + AssortedMethods.isSorted(array));
    }
}
