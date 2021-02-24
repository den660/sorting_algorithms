package algorithms;

import sample.NumberArray;
import sample.NumberState;

public class Bubblesort implements SortingAlgorithm{


    private int i;
    private int j;
    private int comparisons;
    private int arrayAccesses;
    private String name = "Bubblesort";


    @Override
    public void reset(){
        i = 1;
        j = 0;
        comparisons = 0;
        arrayAccesses = 0;
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
    public void sort(NumberArray numberArray){
        int[] numbers = numberArray.getNumbers();
        if(numbers[j] > numbers[j+1]){
            int temp = numbers[j];
            numbers[j] = numbers[j+1];
            numbers[j+1] = temp;
            arrayAccesses += 4;
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
    }

    public Bubblesort(){
    }

}
