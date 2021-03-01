package algorithms;

import sample.NumberArray;

public interface SortingAlgorithm {

    public boolean sort(NumberArray numberArray);
    public int getComparisons();
    public int getArrayAccesses();
    public String getName();
    public void reset();
}
