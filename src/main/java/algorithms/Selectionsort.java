package algorithms;

import sample.NumberArray;
import sample.NumberState;

import java.util.Arrays;

public class Selectionsort implements SortingAlgorithm {

    private int i;
    private int j;
    private int position;
    private int counter;
    private int comparisons;
    private int arrayAccesses;
    private String name = "Selectionsort";
    private NumberArray numberArray;

    @Override
    public boolean sort() {
        int[] numbers = numberArray.getNumbers();
        int arrayLength = numbers.length;

        NumberState[] numberStates = numberArray.getNumberStates();
        Arrays.fill(numberStates, NumberState.UNDEFINED);

        if(counter+1<arrayLength) {
            j = numbers[counter];
            arrayAccesses++;
            position = counter;
            for (i = counter + 1; i < arrayLength; i++) {
                if(numbers[i]<j){
                    j = numbers[i];
                    arrayAccesses++;
                    position = i;
                }
                comparisons++;
            }
            int temp = numbers[counter];
            numbers[counter] = numbers[position];
            numbers[position] = temp;
            arrayAccesses+=3;

            j = 100;
            for (i = counter + 2; i < arrayLength; i++) {
                if(numbers[i]<j){
                    j = numbers[i];
                    position = i;
                }
            }
            numberStates[counter+1] = NumberState.NEXTCOMPARISON;
            numberStates[position] = NumberState.NEXTCOMPARISON;

            for(int k=0; k <= counter; k++) {
                numberStates[k] = NumberState.FIXED;
            }

            counter++;
            return false;

        }else{
            Arrays.fill(numberStates, NumberState.FIXED);
            return true;
        }
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
        i = 0;
        j = 1;
        position = 0;
        counter = 0;
        comparisons = 0;
        arrayAccesses = 0;
        this.numberArray = numberArray;
    }

    @Override
    public void setInitialStates() {
        NumberState[] numberStates = numberArray.getNumberStates();
        numberStates[0] = NumberState.NEXTCOMPARISON;
        int[] numbers = numberArray.getNumbers();
        int arrayLength = numbers.length;
        j = numbers[0];
        for (i = 1; i < arrayLength; i++) {
            if (numbers[i] < j) {
                j = numbers[i];
                position = i;
            }
        }
        numberStates[position] = NumberState.NEXTCOMPARISON;

    }

    public Selectionsort() {
        reset(null);
    }
}
