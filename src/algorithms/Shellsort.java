package algorithms;

import sample.NumberArray;
import sample.NumberState;

import java.util.Arrays;

public class Shellsort implements SortingAlgorithm{

    private String name = "Shellsort";
    private int comparisons;
    private int arrayAccesses;
    private NumberArray numberArray;
    private int[] numbers;
    private int n;
    private int gap;
    private int i;
    private NumberState[] states;

    @Override
    public boolean sort() {

        int temp = numbers[i];
        arrayAccesses++;
        Arrays.fill(states, NumberState.UNDEFINED);
        states[i] = NumberState.NEXTCOMPARISON;

        int j;
        for (j = i; j >= gap && numbers[j - gap] > temp; j -= gap)
            numbers[j] = numbers[j - gap];
            arrayAccesses+=3;
            comparisons++;

        numbers[j] = temp;
        arrayAccesses++;


        i += 1;
        if(i >= n){


            gap /= 2;
            i = gap;
            if(gap <= 0) {
                Arrays.fill(states, NumberState.FIXED);
                return true;
            }
            return false;
        }


        return false;
    }

    @Override
    public int getComparisons() {
        return 0;
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
        states = numberArray.getNumberStates();
        numbers = numberArray.getNumbers();
        n = numbers.length;
        gap = n/2;
        i = gap;
        comparisons = 0;
        arrayAccesses = 0;
    }

    @Override
    public void setInitialStates() {

    }
}
