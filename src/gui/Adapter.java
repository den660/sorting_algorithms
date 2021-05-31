package gui;

import sample.NumberArray;

public class Adapter {

    public NumberArray parseNumbers(int[] array){
        NumberArray numberArray = new NumberArray(array.length);
        numberArray.setNumbers(array);
        return numberArray;
    }
}
