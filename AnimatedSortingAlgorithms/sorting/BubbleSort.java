package sorting;

/**
 * Classes: SortDriver, SortFrame, SortPanels, SelectionSort, InsertionSort, BubbleSort, QuickSort, MergeSort
 * Abstract Class: MasterSort
 * BubbleSort: Lays out the logic for an object using bubble sort method.
 * @author Patricia Sipes, Garion Armoogam
 * @version 1.2
 */
public class BubbleSort extends MasterSort {
    /**
     * The stepper
     */
    private int n;

    /**
     * The parameterized constructor for a bubble sorted object
     * @param sortable the array to be sorted
     */
    BubbleSort(int [] sortable)
    {
        super(sortable);
        //bubble sort counts backwards
        n = currentArray.length;
    }

    /**
     * The specific bubble sort algorithm NOTE: RECURSIVE
     * @return the sorted array
     */
    @Override
    protected int[] sortMethod() {

        //base case, if n has reached the front of the array
        if (n == 1 )
        {
            this.sortFinished = true;
            saveIntoArrayLists();
            return currentArray;
        }

        //a temp holder for switching values
        int temp;

        //start from the front, and look between the first and n-1
        for (int i = 0; i < n-1; i++)
        {
            if(currentArray[i+1]<currentArray[i])
            {
                temp = currentArray[i];
                currentArray[i] = currentArray[i+1];
                currentArray[i+1] = temp;
            }
        }

        saveIntoArrayLists();
        //n gets smaller
        n--;

        //recursion on itself
        return sortMethod();
    }
}
