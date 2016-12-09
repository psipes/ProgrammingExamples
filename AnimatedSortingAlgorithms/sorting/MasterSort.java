package sorting;

import java.util.ArrayList;

/**
 * Classes: SortDriver, SortFrame, SortPanels, SelectionSort, InsertionSort, BubbleSort, QuickSort, MergeSort
 * Abstract Class: MasterSort
 * MasterSort: Abstract class for representation of all sorting methods.
 * @author Patricia Sipes, Garion Armoogam
 * @version 2.1
 */
public abstract class MasterSort {
    /**
     * the array of the previous sort
     */
    protected int [] previousArray;
    /**
     * the array of the current sort
     */
    protected int [] currentArray;
    /**
     * the array of whether the index has changed numbers or not
     */
    protected boolean [] changeArray;
    /**
     * Stores all the steps via arrays
     */
    protected ArrayList<int[]> steps;
    /**
     * Stores all the change arrays
     */
    protected ArrayList<boolean[]> booleanChange;
    /**
     * has the sort been finished
     */
    protected boolean sortFinished;


    /**
     * The super constructor
     * @param sortingNumbers the numbers to be sorted
     */
    MasterSort(int[] sortingNumbers)
    {
        this.steps = new ArrayList<>(25);
        this.booleanChange = new ArrayList<>(25);
        this.previousArray = new int[10];
        this.currentArray = new int[10];
        this.changeArray = new boolean[10];
        this.sortFinished = false;

        for (int i = 0; i<currentArray.length; i++)
        {
            this.currentArray[i] = sortingNumbers[i];
            this.previousArray[i] = sortingNumbers[i];
            this.changeArray[i] = false;
        }
    }

    
    protected void saveIntoArrayLists()
    {
        //This is how we save the step. It creates a new array and clones current array into it, then stores that into steps.
        //You may be asking why we can't just save currentArray into things. The problem is that all the arrays
        //in the arrayList are referencing the same object, so when it changes, since it's only a reference,
        //everything changes. This means we have to create separate, new objects so they aren't changed, this is why
        //there is a new array being saved as a clone of the currentArray at this specific moment (never to change)

        int[] dest = currentArray.clone();
        steps.add(dest);



        //This is how we save the circle color status (Circles are colored based on whether they are true or false for change.
        for(int i = 0; i< changeArray.length; i++)
        {
            if(this.currentArray[i] == this.previousArray[i]) {
                if(this.changeArray[i])
                {
                    //doNothing
                }
                else
                    this.changeArray[i] = false;
            }
            else
                this.changeArray[i] = true;
        }


        //This works the same way that saving into the arraylist for the numbers does, however, since it isn't a
        //number, you can't quite use clone, instead we use arraycopy from System.
        boolean [] dest2 = new boolean[10];
        System.arraycopy(changeArray, 0, dest2, 0, changeArray.length);
        booleanChange.add(dest2);

    }

    /**
     * The sort method specified by the individual child class
     * @return the sorted array
     */
    protected abstract int[] sortMethod();

}
