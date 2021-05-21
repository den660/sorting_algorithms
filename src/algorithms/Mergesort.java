package algorithms;

import sample.NumberArray;
import sample.NumberState;

public class Mergesort implements SortingAlgorithm {

    private int i;
    private int j;
    private int comparisons;
    private int arrayAccesses;
    private String name = "Mergesort";
    private NumberArray numberArray;

    @Override
    public boolean sort() {
        return false;
    }

    @Override
    public int getComparisons() {
        return comparisons;
    }

    @Override
    public int getArrayAccesses() {
        return arrayAccesses;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void reset(NumberArray numberArray) {
        i = 1;
        j = 0;
        comparisons = 0;
        arrayAccesses = 0;
        this.numberArray = numberArray;
    }

    @Override
    public void setInitialStates() {
        NumberState[] numberStates = numberArray.getNumberStates();
    }

    public Mergesort() {
        reset(null);
    }
}
