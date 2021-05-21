package gui;
import algorithms.*;
import algorithms.quicksort.Quicksort;
import javafx.scene.Node;


public class GuiController {

    private Results results;
    private BarGraph barGraph;
    private SortingAlgorithm[] sortingAlgorithms;
    private UserInputs userInputs;

    public GuiController(){
        sortingAlgorithms = new SortingAlgorithm[]{
                new Bubblesort(),
                new Insertionsort(),
                new Heapsort(),
                new Quicksort(),
                new Shellsort(),
                new Mergesort(),
                new Selectionsort(),
                new Gnomesort()};
        results = new Results();
        barGraph = new BarGraph();
        userInputs = new UserInputs(barGraph, results, sortingAlgorithms);
    }

    public Node[] getElements() {
        return new Node[]{userInputs.getElements(), results.getElements(), barGraph.getElement()};
    }

    public void stopThread(){

    }
}
