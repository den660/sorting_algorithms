package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberArrayTest {

    @Test
    public void testGetLength(){
        NumberArray numberArray = new NumberArray(10);
        assertEquals(numberArray.getLength(),10);
    }

    @Test
    public void testGetNumbers(){
        NumberArray numberArray = new NumberArray(5);
        assertArrayEquals(numberArray.getNumbers(), new int[]{1,2,3,4,5});
    }

    @Test
    public void testGetNumbersStates(){
        NumberArray numberArray = new NumberArray(2);
        assertArrayEquals(numberArray.getNumberStates(), new NumberState[]{NumberState.UNDEFINED, NumberState.UNDEFINED});
    }

    @Test
    public void testLoadCopy(){
        NumberArray numberArray = new NumberArray(5);
        numberArray.getNumbers()[0] = 2;
        numberArray.loadCopy();
        assertArrayEquals(numberArray.getNumbers(), new int[]{1,2,3,4,5});
    }

    @Test
    public void testShuffle(){
        NumberArray numberArray = new NumberArray(5);
        numberArray.shuffle();
        assertArrayEquals(numberArray.getNumbers(), new int[]{1,2,3,4,5});
    }

    @Test
    public void testReverse(){
        NumberArray numberArray = new NumberArray(5);
        numberArray.reverse();
        assertArrayEquals(numberArray.getNumbers(), new int[]{5,4,3,2,1});
    }
}
