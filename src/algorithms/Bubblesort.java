package algorithms;

import sample.NumberArray;
import sample.NumberState;

import java.util.Arrays;

public class Bubblesort implements SortingAlgorithm{


    private int i;
    private int j;
    private int comparisons;
    private int arrayAccesses;
    private String name = "Bubblesort";
    private int k;
    private NumberArray numberArray;



    @Override
    public void reset(NumberArray numberArray){
        i = 1;
        j = 0;
        k=0;
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
    public boolean sort(){

        int[] numbers = numberArray.getNumbers();
        if(numbers[j] > numbers[j+1]){
            int temp = numbers[j];
            numbers[j] = numbers[j+1];
            numbers[j+1] = temp;
            arrayAccesses += 4;
            k=0;
        }
        else{
            k++;
        }

        comparisons++;
        arrayAccesses += 2;
        j++;
        NumberState[] numberStates = numberArray.getNumberStates();
        if(j >= numbers.length-i){
            numberStates[j-1] = NumberState.UNDEFINED;
            numberStates[j] = NumberState.FIXED;
            i++;
            j = 0;
            if(i>=numbers.length){
                //End
                numberStates[j] = NumberState.FIXED;
                numberStates[j+1] = NumberState.FIXED;
            }
            else{
                numberStates[j] = NumberState.NEXTCOMPARISON;
                numberStates[j+1] = NumberState.NEXTCOMPARISON;
            }
        }
        else{
            numberStates[j-1] = NumberState.UNDEFINED;
            numberStates[j] = NumberState.NEXTCOMPARISON;
            numberStates[j+1] = NumberState.NEXTCOMPARISON;
        }

        if(k >= numberArray.getLength()-i){
            Arrays.fill(numberStates, NumberState.FIXED);
            return true;
        }
        return false;
    }


    public Bubblesort(){
        reset(null);
    }

}