package algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sample.NumberArray;
import sample.NumberState;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class InsertionsortTest {
    private NumberArray numberArray = new NumberArray(5);
    SortingAlgorithm sortingAlgorithm = new Insertionsort();

    @Test
    public void testSort(){
        numberArray.init(5);
        numberArray.shuffle();
        sortingAlgorithm.reset(numberArray);
        while(!sortingAlgorithm.sort());
        assertArrayEquals(numberArray.getNumbers(), new int[]{1,2,3,4,5});
    }

    @Test
    public void testReset(){
        numberArray.init(5);
        numberArray.shuffle();
        sortingAlgorithm.reset(numberArray);
        while(!sortingAlgorithm.sort());
        numberArray.init(5);
        sortingAlgorithm.reset(numberArray);
        Assertions.assertEquals(sortingAlgorithm.getComparisons(), 0);
        Assertions.assertEquals(sortingAlgorithm.getArrayAccesses(), 0);
    }

    @Test
    public void testResults(){
        numberArray.init(5);
        numberArray.reverse();
        sortingAlgorithm.reset(numberArray);
        while(!sortingAlgorithm.sort());
        Assertions.assertEquals(sortingAlgorithm.getComparisons(), 10);
        Assertions.assertEquals(sortingAlgorithm.getArrayAccesses(), 38);
    }

    @Test
    public void testGetName(){
        Assertions.assertEquals(sortingAlgorithm.getName(), "Insertionsort");
    }

    @Test
    public void testSetInitialStates(){
        numberArray.init(5);
        sortingAlgorithm.reset(numberArray);
        sortingAlgorithm.setInitialStates();
        NumberState[] numberStates = numberArray.getNumberStates();
        Assertions.assertEquals(numberStates[0], NumberState.NEXTCOMPARISON);
    }
}
