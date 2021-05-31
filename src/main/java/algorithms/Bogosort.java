package algorithms;

import sample.NumberArray;
import sample.NumberState;

import java.util.Arrays;

public class Bogosort implements SortingAlgorithm{

    private NumberArray numberArray;
    private String name = "Bogosort";
    private int comparisons;

    boolean isSorted(int[] array, int length) {
        if (array == null || length < 2)
            return true;
        if (array[length - 2] >= array[length - 1])
            return false;
        return isSorted(array, length - 1);
    }

    @Override
    public boolean sort() {
        comparisons++;
        numberArray.shuffle();
        if(isSorted(numberArray.getNumbers(), numberArray.getNumbers().length)){
            Arrays.fill(numberArray.getNumberStates(), NumberState.FIXED);
            return true;
        }
        return false;
    }

    @Override
    public int getComparisons() {
        return comparisons;
    }

    @Override
    public int getArrayAccesses() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void reset(NumberArray numberArray) {
        this.numberArray = numberArray;
        comparisons = 0;
    }

    @Override
    public void setInitialStates() {
        Arrays.fill(numberArray.getNumberStates(), NumberState.UNDEFINED);
    }
}
