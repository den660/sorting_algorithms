package algorithms;

import sample.NumberArray;
import sample.NumberState;

import java.util.Arrays;

public class Insertionsort implements SortingAlgorithm{

    private int i;
    private int j;
    private int temp;
    private int comparisons;
    private int arrayAccesses;
    private String name = "Insertionsort";

    @Override
    public boolean sort(NumberArray numberArray) {
        int[] numbers = numberArray.getNumbers();
        NumberState[] numberStates = numberArray.getNumberStates();
        if(i < numbers.length){
            if(j == 0){
                temp = numbers[i];
                arrayAccesses++;
                j = i;
            }
            if(j > 0 && numbers[j-1] > temp){
                numbers[j] = numbers[j-1];
                arrayAccesses+=2;
                j--;
            }
            arrayAccesses++;
            if(j == 0 || numbers[j-1] <= temp){
                numbers[j] = temp;
                arrayAccesses++;
                i++;
                j=0;
            }
            Arrays.fill(numberStates, NumberState.UNDEFINED);
            numberStates[j] = NumberState.NEXTCOMPARISON;
        }
        else{
            Arrays.fill(numberStates, NumberState.FIXED);
            return true;
        }
        comparisons++;

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
    public void reset() {
        i = 1;
        j = 0;
        comparisons = 0;
        arrayAccesses = 0;
    }

    @Override
    public void setInitialStates(NumberArray numberArray) {

    }

    public Insertionsort(){
        reset();
    }
}
