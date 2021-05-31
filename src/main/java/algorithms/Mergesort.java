package algorithms;

import sample.NumberArray;
import sample.NumberState;

import java.util.Arrays;

public class Mergesort implements SortingAlgorithm {

    private int startOfChunk;
    private int sizeOfChunks;
    private int counter;
    private int iterations;
    private int iterationCounter;
    private int lowest;
    private int starter;
    private int position;
    private int restCounter;
    private int comparisons;
    private int arrayAccesses;
    private final int maxInt = Integer.MAX_VALUE;
    private final String name = "Mergesort";
    private NumberArray numberArray;

    @Override
    public boolean sort() {
        if(starter==0){
            setMaxIterations();
            starter++;
        }
        NumberState[] numberStates = numberArray.getNumberStates();
        if (iterationCounter <= iterations) {
            Arrays.fill(numberStates, NumberState.UNDEFINED);
            if (iterationCounter % 2 == 0) {
                forwardSorting();
            } else {
                backwardSorting();
            }
            return false;
        } else {
            Arrays.fill(numberStates, NumberState.FIXED);
            return true;
        }
    }

    public void setMaxIterations(){
        int[] numbers = numberArray.getNumbers();
        int arrayLength = numbers.length;
        NumberState[] numberStates = numberArray.getNumberStates();

        if (arrayLength / 2 == 1 | arrayLength == 4) {
            iterations = 1;
        } else if (arrayLength / 4 == 1 | arrayLength == 8) {
            iterations = 2;
        } else if (arrayLength / 8 == 1 | arrayLength == 16) {
            iterations = 3;
        } else if (arrayLength / 16 == 1 | arrayLength == 32) {
            iterations = 4;
        } else if (arrayLength / 32 == 1 | arrayLength == 64) {
            iterations = 5;
        } else if (arrayLength / 64 == 1 | arrayLength == 128) {
            iterations = 6;
        } else if (arrayLength / 128 == 1) {
            iterations = 7;
        } else {
            System.out.println("Fehler!");
            Arrays.fill(numberStates, NumberState.NEXTCOMPARISON);
        }
    }

    public void findLowestForward(int startValue, int endValue){
        int[] numbers = numberArray.getNumbers();
        for (int k = startValue; k < endValue; k++) {
            comparisons++;
            if (numbers[k] < lowest) {
                arrayAccesses++;
                lowest = numbers[k];
                position = k;
            }
        }
    }

    public void findLowestBackward(int startValue, int endValue){
        int[] numbers = numberArray.getNumbers();
        for (int k = startValue; k > endValue; k--) {
            comparisons++;
            if (numbers[k] < lowest) {
                arrayAccesses++;
                lowest = numbers[k];
                position = k;
            }
        }
    }

    public void moveLowest(int position, int startOfChunk){
        int[] numbers = numberArray.getNumbers();
        if (position != startOfChunk) {
            arrayAccesses++;
            int temp = numbers[position];
            for (int k = position; k > startOfChunk; k--) {
                arrayAccesses+=2;
                numbers[k] = numbers[k - 1];
            }
            arrayAccesses++;
            numbers[startOfChunk] = temp;
        }
    }

    public void forwardSorting() {
        int[] numbers = numberArray.getNumbers();
        int arrayLength = numbers.length;
        NumberState[] numberStates = numberArray.getNumberStates();

        if (startOfChunk + sizeOfChunks - counter < arrayLength) {
            findLowestForward(startOfChunk, (startOfChunk+sizeOfChunks-counter));
            moveLowest(position, startOfChunk);

            numberStates[startOfChunk] = NumberState.NEXTCOMPARISON;
            startOfChunk++;
            counter++;
            lowest = maxInt;

            if (counter == sizeOfChunks - 1) {
                counter = 0;
                startOfChunk++;
            }
        } else {
            int startOfRest = startOfChunk + restCounter;
            findLowestForward(startOfRest, arrayLength);
            moveLowest(position, startOfRest);

            numberStates[startOfRest] = NumberState.NEXTCOMPARISON;
            restCounter++;
            lowest = maxInt;

            if (startOfRest + counter == arrayLength - 1) {
                iterationCounter++;
                counter = 0;
                restCounter = 0;
                sizeOfChunks *= 2;
                startOfChunk = arrayLength - 1;
            }
        }
    }

    public void backwardSorting() {
        NumberState[] numberStates = numberArray.getNumberStates();

        int endOfChunk = startOfChunk;
        if (endOfChunk - sizeOfChunks +1 >= 0) {
            findLowestBackward(endOfChunk, (endOfChunk -sizeOfChunks+counter));
            moveLowest(position, (endOfChunk - sizeOfChunks + counter));

            numberStates[endOfChunk - sizeOfChunks + counter + 1] = NumberState.NEXTCOMPARISON;
            counter++;
            lowest = maxInt;

            if (counter == sizeOfChunks - 1) {
                counter = 0;
                startOfChunk -= sizeOfChunks;
            }
        } else if(endOfChunk > 1){
            findLowestBackward(endOfChunk, (counter-1));
            moveLowest(position, counter);

            numberStates[counter] = NumberState.NEXTCOMPARISON;
            counter++;
            lowest = maxInt;

            if (counter == endOfChunk) {
                iterationCounter++;
                counter = 0;
                restCounter = 0;
                startOfChunk = 0;
                sizeOfChunks *= 2;
            }
        }else{
            iterationCounter++;
            counter = 0;
            restCounter = 0;
            startOfChunk = 0;
            sizeOfChunks *= 2;
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
        startOfChunk = 0;
        sizeOfChunks = 2;
        counter = 0;
        restCounter = 0;
        lowest = maxInt;
        position = 0;
        starter = 0;
        iterationCounter = 0;
        comparisons = 0;
        arrayAccesses = 0;
        this.numberArray = numberArray;
    }

    @Override
    public void setInitialStates() {
        int[] numbers = numberArray.getNumbers();
        NumberState[] numberStates = numberArray.getNumberStates();
        if (numbers[0] < numbers[1]) {
            numberStates[0] = NumberState.NEXTCOMPARISON;
        } else {
            numberStates[1] = NumberState.NEXTCOMPARISON;
        }
    }

    public Mergesort() {
        reset(null);
    }
}