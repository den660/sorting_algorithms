package algorithms;

import sample.NumberArray;
import sample.NumberState;

public class Heapsort implements SortingAlgorithm {

    private int lower;
    private int upper;
    private int i;
    private int j;
    private int stepCounter;
    private int roundCounter;
    private int comparisons;
    private int arrayAccesses;
    private String name = "Heapsort";
    private NumberArray numberArray;


    @Override
    public boolean sort() {
        NumberState[] numberStates = numberArray.getNumberStates();
        int[] numbers = numberArray.getNumbers();
        int arrayLength = numberArray.getLength();
        int endOfArray = numbers.length - 1;

        if (roundCounter + 3 <= arrayLength) {

            if ((stepCounter + 1) < (arrayLength - roundCounter)) {

                lower = endOfArray - stepCounter - roundCounter;

                if (lower % 2 == 0) {
                    upper = (lower / 2) - 1;
                } else {
                    upper = (lower - 1) / 2;
                }

                for (int i = 0; i <= endOfArray - roundCounter; i++) {
                    numberStates[i] = NumberState.UNDEFINED;
                }

                i = numbers[upper];
                j = numbers[lower];
                comparisons++;
                arrayAccesses += 2;

               numberStates[upper] = NumberState.NEXTCOMPARISON;
               numberStates[lower] = NumberState.NEXTCOMPARISON;

                if (j > i) {
                    int temp = i;
                    numbers[upper] = j;
                    numbers[lower] = temp;
                    arrayAccesses += 3;
                }
                stepCounter++;

            } else {
                int entryPoint = endOfArray - roundCounter;
                int temp = numbers[0];
                numbers[0] = numbers[entryPoint];
                numbers[entryPoint] = temp;
                numberStates[entryPoint] = NumberState.FIXED;
                stepCounter = 0;
                roundCounter++;
                arrayAccesses += 3;
            }

            return false;
        }else{
            numberStates[0] = NumberState.FIXED;
            numberStates[1] = NumberState.FIXED;
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
        upper = 0;
        lower = 0;
        i = 1;
        j = 0;
        stepCounter = 0;
        roundCounter = 0;
        comparisons = 0;
        arrayAccesses = 0;
        this.numberArray = numberArray;
    }

    @Override
    public void setInitialStates() {
        int middleOfArray;
        int endOfArray = numberArray.getLength()-1;
        if (endOfArray % 2 == 0) {
            middleOfArray = (endOfArray / 2) - 1;
        } else {
            middleOfArray = (endOfArray - 1) / 2;
        }
        NumberState[] numberStates = numberArray.getNumberStates();
        numberStates[endOfArray] = NumberState.NEXTCOMPARISON;
        numberStates[middleOfArray] = NumberState.NEXTCOMPARISON;
    }

    public Heapsort() {
        reset(null);
    }
}
