package algorithms;

import sample.NumberArray;
import sample.NumberState;

public class Heapsort implements SortingAlgorithm {

    private int lower;
    private int upper;
    private int i;
    private int j;
    private int comparisons;
    private int arrayAccesses;
    private String name = "Heapsort";
    private NumberArray numberArray;


    @Override
    public boolean sort() {

        int[] numbers = numberArray.getNumbers();
        int endOfArray = numbers.length-1;
        comparisons = 0;
        arrayAccesses = 0;

        while(endOfArray>0) {
            lower = endOfArray;
            while (lower > 1) {
                if (lower % 2 == 0) {
                    upper = (lower / 2) - 1;
                } else {
                    upper = (lower - 1) / 2;
                }

                i = numbers[upper];
                j = numbers[lower];
                comparisons++;

                if (j > i) {
                    int temp = i;
                    numbers[upper] = j;
                    numbers[lower] = temp;
                }
                lower--;
            }
            int temp = numbers[0];
            numbers[0] = numbers[endOfArray];
            numbers[endOfArray] = temp;
            endOfArray--;
        }



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
        lower = 0;
        upper = 0;
        i = 0;
        j = 0;

    }

    @Override
    public void setInitialStates() {

    }
}
