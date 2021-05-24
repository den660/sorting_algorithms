package algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sample.NumberArray;
import sample.NumberState;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BogosortTest {
    private NumberArray numberArray = new NumberArray(3);
    SortingAlgorithm sortingAlgorithm = new Bogosort();



    @Test
    public void testSort(){
        numberArray.init(3);
        numberArray.shuffle();
        sortingAlgorithm.reset(numberArray);
        while(!sortingAlgorithm.sort());
        assertArrayEquals(numberArray.getNumbers(), new int[]{1,2,3});
    }

    @Test
    public void testReset(){
        numberArray.init(3);
        numberArray.shuffle();
        sortingAlgorithm.reset(numberArray);
        while(!sortingAlgorithm.sort());
        numberArray.init(3);
        sortingAlgorithm.reset(numberArray);
        Assertions.assertEquals(sortingAlgorithm.getComparisons(), 0);
        Assertions.assertEquals(sortingAlgorithm.getArrayAccesses(), 0);
    }


    @Test
    public void testGetName(){
        Assertions.assertEquals(sortingAlgorithm.getName(), "Bogosort");
    }

}
