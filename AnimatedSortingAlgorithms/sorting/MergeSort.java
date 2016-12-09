package sorting;


/**
 * Classes: SortDriver, SortFrame, SortPanels, SelectionSort, InsertionSort, BubbleSort, QuickSort, MergeSort
 * Abstract Class: MasterSort
 * MergeSort: Lays out the logic for an object using merge sort method.
 * @author Patricia Sipes, Garion Armoogam
 * @version 1.2
 */
public class MergeSort extends MasterSort {

    /**
     * Parameterized constructor for a merge sorted object
     * @param sortable the array to be sorted
     */
    MergeSort(int [] sortable)
    {
        super(sortable);
    }

    /**
     * merge sort setup
     * @return sorted array
     */
    @Override
    protected int[] sortMethod() {
        //holder array
        int aux[] = new int[currentArray.length];
        //begin the actual sort
        sort(aux, 0,currentArray.length -1);
        //once sort is done, setup the last bits
        steps.add(this.currentArray);
        booleanChange.add(this.changeArray);
        this.sortFinished = true;
        return currentArray;
    }

    /**
     * Merging algorithm
     * @param aux holder array
     * @param first first index
     * @param mid middle index
     * @param last last index
     */
    protected void merge(int[] aux, int first, int mid,
                              int last) {
        for (int ndx = first; ndx <= last; ndx++)
            aux[ndx] = currentArray[ndx];

        int leftTop = first, rightTop = mid+1;

        for(int ndx = first; ndx <= last; ndx++) {
            if (leftTop > mid)
                currentArray[ndx] = aux[rightTop++];
            else if(rightTop > last)
                currentArray[ndx] = aux[leftTop++];
            else if (less(aux[rightTop], aux[leftTop]))
                currentArray[ndx] =  aux[rightTop++];
            else
                currentArray[ndx] = aux[leftTop++];
        }
    }

    /**
     * sorter
     * @param aux the holder array
     * @param lo the start of array
     * @param hi the end of array portion
     */
    protected void sort(int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;

        saveIntoArrayLists();

        //SORT THE FIRST HALF
        sort(aux, lo, mid);

        saveIntoArrayLists();

        //SORT THE SECOND HALF
        sort(aux, mid + 1, hi);

        saveIntoArrayLists();

        //MERGE THE PIECES TOGETHER BACK INTO ORIGINAL ARRAY
        merge(aux, lo, mid, hi);
    }

    /**
     *
     * @param x integer value
     * @param y integer value
     * @return true if x < y false otherwise
     * */
    protected boolean less(int x, int y) {
        if (x < y)
            return true;
        return false;
    }
}
