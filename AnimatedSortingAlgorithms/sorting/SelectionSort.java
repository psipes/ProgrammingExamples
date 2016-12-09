package sorting;

/**
 * Classes: SortDriver, SortFrame, SortPanels, SelectionSort, InsertionSort, BubbleSort, QuickSort, MergeSort
 * Abstract Class: MasterSort
 * SelectionSort: Lays out the logic for an object using selection sort method.
 * @author Patricia Sipes, Garion Armoogam
 * @version 1.2
 */
public class SelectionSort extends MasterSort {
    /**
     * index value
     */
    private int n;
    SelectionSort(int [] sortable)
    {
        super(sortable);
        n = 0;
    }

    /**
     * The selection sorting algorithm
     * NOTE: RECURSIVE
     * @return the sorted array
     */
    @Override
    protected int[] sortMethod() {

        //baseCase
        if (n == this.currentArray.length - 1 )
        {
            saveIntoArrayLists();
            return currentArray;
        }

        //a temp holder of the index
        int temp = n;
        //a holder for the lowest index
        int lowestNDX = n;

        //check throw the array against the value in lowest index, swap.
        for (int i = n + 1; i < currentArray.length; i++)
        {
            if(currentArray[i] < currentArray[lowestNDX])
                lowestNDX = i;
        }

        temp = currentArray[n];
        currentArray[n] = currentArray[lowestNDX];
        currentArray[lowestNDX] = temp;

        saveIntoArrayLists();
        n++;


        //recursive on self.
        return sortMethod();
    }
}
