package algorithms;

import sample.NumberArray;

public interface SortingAlgorithm {

    boolean sort();
    int getComparisons();
    int getArrayAccesses();
    String getName();
    void reset(NumberArray numberArray);
    void setInitialStates();
}
