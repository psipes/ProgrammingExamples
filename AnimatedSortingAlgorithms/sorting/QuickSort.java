package sorting;


/**
 * Classes: SortDriver, SortFrame, SortPanels, SelectionSort, InsertionSort, BubbleSort, QuickSort, MergeSort
 * Abstract Class: MasterSort
 * QuickSort: Lays out the logic for an object using quick sort method.
 * @author Patricia Sipes, Garion Armoogam
 * @version 1.2
 */
public class QuickSort extends MasterSort {
    /**
     * Index of the beginning
     */
    int startIdx;
    /**
     * Index of the end
     */
    int endIdx;

    /**
     * The parameterized constructor for an object to be quick sorted
     * @param sortable the array to be sorted
     */
    QuickSort(int [] sortable)
    {
        super(sortable);
        startIdx = 0;
        endIdx = currentArray.length-1;
    }


    /**
     * The upper level of the sort
     * @return the sorted array
     */
    @Override
    protected int[] sortMethod() {

        if (!this.sortFinished) {
            recursiveQuick(currentArray, startIdx, endIdx);
            for (int item : currentArray) {
                System.out.println(item);
            }
            this.sortFinished = true;
            steps.add(currentArray);
            booleanChange.add(changeArray);
        }
        return currentArray;
    }

    /**
     * The recursive quick sort inner algorithm
     * @param array the array to sort
     * @param start the start index
     * @param end the end index
     */
    protected void recursiveQuick(int[] array, int start, int end)
    {
       int idx = partition(array, start, end);
        if (start < idx -1)
        {
            saveIntoArrayLists();
            //recursively call itself.
            recursiveQuick(array, start, idx - 1);
        }

        if(end> idx)
        {
            saveIntoArrayLists();
            //recursively call itself
            recursiveQuick(array, idx, end);
        }

        this.sortFinished = true;
    }

    /**
     * Partitions the array to select the pivot
     * @param array wanted array
     * @param left start
     * @param right end
     * @return the mid/pivot point
     */
    protected int partition(int[] array, int left, int right)
    {
        int pivot = array[left];

        while (left <= right)
        {
            while (array[left] < pivot)
                left++;
            while (array[right] > pivot)
                right --;

            if (left <= right)
            {
                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
                left++;
                right--;
            }
        }
        return left;
    }
}
