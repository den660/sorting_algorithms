package gui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AutoSortTest {

    @Test
    void testStartStop(){
        AutoSortThread autoSortThread = new AutoSortThread();
        autoSortThread.start();
        Assertions.assertEquals(autoSortThread.isRunning(), true);
        autoSortThread.stop();
        Assertions.assertEquals(autoSortThread.isRunning(), false);
    }
}
