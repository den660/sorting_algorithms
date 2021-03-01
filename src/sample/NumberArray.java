package sample;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class NumberArray {
    private int[] numbers;
    private NumberState[] numberStates;
    private int[] numbersCopy;

    public int getLength(){
        return numbers.length;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public NumberState[] getNumberStates() {
        return numberStates;
    }

    public void loadCopy(){
        numbers = Arrays.copyOf(numbersCopy, numbersCopy.length);
        Arrays.fill(numberStates, NumberState.UNDEFINED);
    }


    public void shuffle(){
        Random rnd = ThreadLocalRandom.current();
        for (int i = numbers.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            int a = numbers[index];
            numbers[index] = numbers[i];
            numbers[i] = a;
        }
        Arrays.fill(numberStates, NumberState.UNDEFINED);
        numbersCopy = Arrays.copyOf(numbers, numbers.length);
    }

    public void reverse(){
        for (int i = 0; i < numbers.length / 2; i++) {
            int temp = numbers[i];
            numbers[i] = numbers[numbers.length - 1 - i];
            numbers[numbers.length - 1 - i] = temp;
        }
        Arrays.fill(numberStates, NumberState.UNDEFINED);
        numbersCopy = Arrays.copyOf(numbers, numbers.length);
    }

    public void init(int n){
        numbers = new int[n];
        for(int i = 0; i < n; i++){
            numbers[i] = i+1;
        }
        numberStates = new NumberState[n];
        Arrays.fill(numberStates, NumberState.UNDEFINED);
    }

    public NumberArray(int n){
        init(n);
    }
}
