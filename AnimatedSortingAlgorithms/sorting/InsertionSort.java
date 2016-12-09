package sorting;

/**
 * Classes: SortDriver, SortFrame, SortPanels, SelectionSort, InsertionSort, BubbleSort, QuickSort, MergeSort
 * Abstract Class: MasterSort
 * InsertionSort: Lays out the logic for an object using insertion sort method.
 * @author Patricia Sipes, Garion Armoogam
 * @version 1.2
 */
public class InsertionSort extends MasterSort  {

    /**
     * The index to be moved along
     */
    private int ndx;

    /**
     * Paramaterized constructor for an Insertion Sorted object.
     * @param sortable An integer array to be sorted. MUST BE VALIDATED FIRST
     */
    InsertionSort(int [] sortable)
    {
        super(sortable);
        ndx = 0;
    }

    /**
     * This is the logic for the sort method with "saves" to the array lists for each step. NOTE: DONE RECURSIVELY.
     * @return the sorted array
     */
    @Override
    protected int[] sortMethod() {

        //Insertion Sort uses an index object starting from the beginning and traversing to the end
        if(ndx < currentArray.length)
        {
            //The traversal
            int j;
            //a temp int to hold the number to switch (from the index)
            int temp = currentArray[ndx];

            //using the traversal, set it to the ndx and if not 0, check if it's larger than the temp value.
            for (j = ndx; j > 0 && currentArray[j - 1] > temp; j--)
                currentArray[j] = currentArray[j-1];

            //whatever j has popped out at, set temp to its value
            currentArray[j] = temp;

            saveIntoArrayLists();
            //Increase the index before recursion
            ndx++;
            //recursion on itself will run this method again, ONLY the ndx will be increased by 1.
            return sortMethod();
        }
        //This is the base case.
        else
        {
            this.sortFinished = true;
            saveIntoArrayLists();
            return currentArray;
        }
    }
}
