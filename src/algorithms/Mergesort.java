package algorithms;

import sample.NumberArray;
import sample.NumberState;

import java.util.List;

public class Mergesort implements SortingAlgorithm {

    private int sizeOfChunks;
    private int startOfChunk;
    private int reducedSize;
    private int counter;
    private int iterations;
    private int temp;
    private int lowest;
    private int position;
    private List<Integer> separations;
    private int comparisons;
    private int arrayAccesses;
    private String name = "Mergesort";
    private NumberArray numberArray;

    @Override
    public boolean sort() {
        int[] numbers = numberArray.getNumbers();
        int arrayLength = numbers.length;

        NumberState[] numberStates = numberArray.getNumberStates();

        if(arrayLength/2 == 1 | arrayLength == 4){
            iterations=1;
        }else if(arrayLength/4==1 | arrayLength == 8){
            iterations=2;
        }else if(arrayLength/8==1 | arrayLength == 16){
            iterations=3;
        }else if(arrayLength/16==1 | arrayLength == 32){
            iterations=4;
        }else if(arrayLength/32==1 | arrayLength == 64){
            iterations=5;
        }else if(arrayLength/64==1 | arrayLength == 128){
            iterations=6;
        }else if(arrayLength/128==1){
            iterations=7;
        }else{
            System.out.println("Fehler!");
            return true;
        }

        if(arrayLength %2 == 0){
            if(startOfChunk + sizeOfChunks <arrayLength+2){
                for(int k = startOfChunk; k< startOfChunk + reducedSize; k++){
                    if(numbers[k]<lowest){
                        lowest = numbers[k];
                        position = k;
                    }
                }


                System.out.println("lowest =" + lowest);
                System.out.println("position =" + position);


                if(position != startOfChunk) {
                    temp = numbers[position];
                    System.out.println("temp = "+temp);
                    for (int k = position; k > startOfChunk; k--) {
                        numbers[k] = numbers[k - 1];
                        System.out.println("numbers[" + k + "] = " + numbers[k]);
                    }
                    numbers[startOfChunk] = temp;
                    System.out.println("kleinstes = " + numbers[startOfChunk]);
                }
                counter++;
                if(counter<sizeOfChunks) {
                    startOfChunk++;
                    reducedSize--;
                    lowest = 100;
                }else {
                    startOfChunk = startOfChunk -counter + sizeOfChunks +1;
                    reducedSize = sizeOfChunks;
                    lowest = 100;
                    counter = 0;
                }
            }else{
                startOfChunk = 0;
                sizeOfChunks *= 2;
                reducedSize = sizeOfChunks;
                lowest = 100;

            }
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
        startOfChunk = 0;
        sizeOfChunks = 2;
        reducedSize = sizeOfChunks;
        counter = 0;
        lowest = 100;
        position = 0;
        comparisons = 0;
        arrayAccesses = 0;
        this.numberArray = numberArray;
    }

    @Override
    public void setInitialStates() {
        NumberState[] numberStates = numberArray.getNumberStates();
    }

    public Mergesort() {
        reset(null);
    }
}
