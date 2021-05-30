package gui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GuiControllerTest {


    @Test
    void testSortArray(){
        FakeResultNode fakeResultNode = new FakeResultNode();
        FakeGraph fakeGraph = new FakeGraph();
        GuiController guiController = new GuiController(fakeResultNode, fakeGraph, new FakeAutoSort());
        guiController.changeSortingAlgorithm(new FakeSortingAlgorithm());
        guiController.initNumberArray(5);
        guiController.sortArray();
        Assertions.assertArrayEquals(fakeGraph.getArray(), new int[]{1,2,3,4,5});
        Assertions.assertEquals(fakeResultNode.getArrayAccesses(), 888);
        Assertions.assertEquals(fakeResultNode.getComparisons(), 999);
    }

    @Test
    void testChangeDelay(){
        FakeAutoSort fakeAutoSort = new FakeAutoSort();
        GuiController guiController = new GuiController(null, null, fakeAutoSort);
        guiController.changeDelay(999);
        Assertions.assertEquals(fakeAutoSort.getDelay(), 999);
    }

    @Test
    void testResetArray(){
        FakeInputNode fakeInputNode = new FakeInputNode();
        FakeResultNode fakeResultNode = new FakeResultNode();
        GuiController guiController = new GuiController(fakeResultNode, new FakeGraph(), new FakeAutoSort());
        guiController.changeSortingAlgorithm(new FakeSortingAlgorithm());
        guiController.initNumberArray(5);
        guiController.setUserInputs(fakeInputNode);
        guiController.resetArray();
        Assertions.assertEquals(fakeResultNode.isReseted(), true);
        Assertions.assertEquals(fakeInputNode.getStartButtonText(), "Start");
        Assertions.assertEquals(fakeInputNode.isStartButtonDisabled(), false);
        Assertions.assertEquals(fakeInputNode.isStepButtonDisabled(), false);
    }

    @Test
    void testTriggerAutoSort(){
        FakeAutoSort fakeAutoSort = new FakeAutoSort();
        GuiController guiController = new GuiController(null, null, fakeAutoSort);
        guiController.setUserInputs(new FakeInputNode());
        guiController.triggerAutoSort();
        Assertions.assertEquals(fakeAutoSort.isRunning(), true);
        guiController.triggerAutoSort();
        Assertions.assertEquals(fakeAutoSort.isRunning(), false);
    }

    @Test
    void testStopThread(){
        FakeAutoSort fakeAutoSort = new FakeAutoSort();
        fakeAutoSort.start();
        GuiController guiController = new GuiController(null, null, fakeAutoSort);
        guiController.stopThread();
        Assertions.assertEquals(fakeAutoSort.isRunning(), false);
    }

    @Test
    void testReset(){
        FakeInputNode fakeInputNode = new FakeInputNode();
        FakeResultNode fakeResultNode = new FakeResultNode();
        GuiController guiController = new GuiController(fakeResultNode, new FakeGraph(), new FakeAutoSort());
        guiController.changeSortingAlgorithm(new FakeSortingAlgorithm());
        guiController.setUserInputs(fakeInputNode);
        guiController.reset();
        Assertions.assertTrue(fakeResultNode.isReseted());
        Assertions.assertEquals("Start", fakeInputNode.getStartButtonText());
        Assertions.assertFalse(fakeInputNode.isStartButtonDisabled());
        Assertions.assertFalse(fakeInputNode.isStepButtonDisabled());
    }
}
