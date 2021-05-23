package algorithms.quicksort;

import algorithms.SortingAlgorithm;
import sample.NumberArray;
import sample.NumberState;

import java.util.Arrays;
import java.util.Stack;

public class Quicksort implements SortingAlgorithm {

    private int comparisons;
    private int arrayAccesses;
    private String name = "Quicksort";
    private Stack<Pair> stack;
    private int start;
    private int end;
    private NumberArray numberArray;
    private NumberState[] numberStates;

    private int partition(int a[], int start, int end)
    {
        int pivot = a[end];
        int pIndex = start;

        arrayAccesses++;

        for (int i = start; i < end; i++)
        {

            if (a[i] <= pivot)
            {
                swap(a, i, pIndex);
                pIndex++;
            }
            arrayAccesses++;
            comparisons++;
        }

        swap (a, pIndex, end);
        return pIndex;
    }

    private void swap (int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        arrayAccesses +=4;
    }

    @Override
    public boolean sort() {

        if(stack.empty()){
            Arrays.fill(numberStates, NumberState.FIXED);
            return true;
        }
        start = stack.peek().getX();
        end = stack.peek().getY();
        stack.pop();


        int pivot = partition(numberArray.getNumbers(),start,end);
        numberStates[pivot] = NumberState.FIXED;

        if(pivot - 1 > start){
            stack.push(new Pair(start, pivot - 1));
        }
        else if(pivot-1 > 0){
            numberStates[pivot-1] = NumberState.FIXED;
        }


        if(pivot + 1 < end){
            stack.push(new Pair(pivot +1, end));
        }
        else if(pivot + 1 < numberArray.getNumbers().length){
            numberStates[pivot+1] = NumberState.FIXED;
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
        this.numberArray = numberArray;
        comparisons = 0;
        arrayAccesses = 0;
        stack = new Stack<>();
        start = 0;
        end = numberArray.getLength() - 1;
        stack.push(new Pair(start, end));
        numberStates = numberArray.getNumberStates();

    }

    @Override
    public void setInitialStates() {

    }

    public Quicksort(){

    }
}
