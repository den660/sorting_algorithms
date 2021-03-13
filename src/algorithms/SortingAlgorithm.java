package algorithms;

import sample.NumberArray;

public interface SortingAlgorithm {

    public boolean sort();
    public int getComparisons();
    public int getArrayAccesses();
    public String getName();
    public void reset(NumberArray numberArray);
    public void setInitialStates();
}
