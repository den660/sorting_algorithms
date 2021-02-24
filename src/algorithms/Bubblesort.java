package algorithms;

public class Bubblesort implements SortingAlgorithm{


    private int i;
    private int j;
    private int comparisons;
    private int arrayAccesses;
    private String name = "Bubblesort";


    @Override
    public void init(){
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
    public int[] sort(int[] numberArray){
        if(j >= numberArray.length-i){
            i++;
            j = 0;
        }
        if(numberArray[j] > numberArray[j+1]){
            int temp = numberArray[j];
            numberArray[j] = numberArray[j+1];
            numberArray[j+1] = temp;
            comparisons++;
        }
        j++;
        return numberArray;
    }

    public Bubblesort(){
    }
}
