package gui;

import algorithms.SortingAlgorithm;
import sample.NumberArray;

public class FakeSortingAlgorithm implements SortingAlgorithm {
    @Override
    public boolean sort() {
        return false;
    }

    @Override
    public int getComparisons() {
        return 999;
    }

    @Override
    public int getArrayAccesses() {
        return 888;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void reset(NumberArray numberArray) {

    }

    @Override
    public void setInitialStates() {

    }
}
