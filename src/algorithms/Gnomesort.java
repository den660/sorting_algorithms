package algorithms;

import sample.NumberArray;
import sample.NumberState;

import java.util.Arrays;

public class Gnomesort implements SortingAlgorithm {

    private int i;
    private int j;
    private int comparisons;
    private int arrayAccesses;
    private String name = "Gnomesort";
    private NumberArray numberArray;

    @Override
    public boolean sort() {
        int[] numbers = numberArray.getNumbers();
        int arrayLength = numbers.length;

        NumberState[] numberStates = numberArray.getNumberStates();
        Arrays.fill(numberStates, NumberState.UNDEFINED);

        if(i + 1 < arrayLength){

            if (numbers[i + 1] >= numbers[i]) {
                i++;
                comparisons++;
                arrayAccesses += 2;
                numberStates[i] = NumberState.NEXTCOMPARISON;
                if(i+1<arrayLength) {
                    numberStates[i + 1] = NumberState.NEXTCOMPARISON;
                }else{
                    numberStates[i - 1] = NumberState.NEXTCOMPARISON;
                }
            } else if (numbers[i + 1] < numbers[i]) {
                numberStates[i] = NumberState.NEXTCOMPARISON;
                if(i>0) {
                    numberStates[i - 1] = NumberState.NEXTCOMPARISON;
                }else{
                    numberStates[i + 1] = NumberState.NEXTCOMPARISON;
                }
                comparisons++;
                int temp = numbers[i + 1];
                numbers[i + 1] = numbers[i];
                numbers[i] = temp;
                arrayAccesses+=3;
                if (i > 0) {
                    i--;
                }
            }
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
        comparisons = 0;
        arrayAccesses = 0;
        this.numberArray = numberArray;
    }

    @Override
    public void setInitialStates() {
        NumberState[] numberStates = numberArray.getNumberStates();
        numberStates[0] = NumberState.NEXTCOMPARISON;
        numberStates[1] = NumberState.NEXTCOMPARISON;
    }

    public Gnomesort() {
        reset(null);
    }
}
