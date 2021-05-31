package gui;
import algorithms.*;
import sample.NumberArray;

import javax.swing.*;
import java.io.File;


public class GuiController {

    private final ResultNode results;
    private final Graph graph;
    private NumberArray numberArray;
    private SortingAlgorithm chosenSortingAlgorithm;
    private AutoSort autoSort;
    private InputNode userInputs;
    private int[] numbers;

    public GuiController(ResultNode results, Graph graph, AutoSort autoSort){
        this.results = results;
        this.graph = graph;
        this.autoSort= autoSort;
        autoSort.setRunnable(this);
    }

    public void setUserInputs(InputNode userInputs){
        this.userInputs = userInputs;
    }

    public void changeArraySize(int arraySize){
        numberArray.init(arraySize);
        reset();
    }

    public void shuffleArray(){
        numberArray.shuffle();
        reset();
    }

    public void resetArray(){
        if(autoSort.isRunning()){
            autoSort.stop();
        }

        numberArray.loadCopy();
        reset();
    }

    public void sortArray(){
        if(chosenSortingAlgorithm.sort()){
            autoSort.stop();
            userInputs.disableStepButton(true);
            userInputs.disableStartButton(true);
        }

        graph.drawGraph(numberArray);
        results.update(chosenSortingAlgorithm.getComparisons(), chosenSortingAlgorithm.getArrayAccesses());
    }


    protected void reset(){
        chosenSortingAlgorithm.reset(numberArray);
        chosenSortingAlgorithm.setInitialStates();
        results.reset();
        graph.drawGraph(numberArray);
        userInputs.disableStepButton(false);
        userInputs.disableStartButton(false);
        userInputs.setStartButtonText("Start");
    }


    public void changeDelay(int delay){
        autoSort.setDelay(delay);
    }

    public void triggerAutoSort(){
        if(autoSort.isRunning()){
            userInputs.setStartButtonText("Start");
            autoSort.stop();
        }
        else{
            userInputs.setStartButtonText("Stop");
            autoSort.start();
        }
    }


    public void changeSortingAlgorithm(SortingAlgorithm sortingAlgorithm){
        chosenSortingAlgorithm = sortingAlgorithm;
    }

    public void reverseArray(){
        numberArray.reverse();
        reset();
    }

    public void initNumberArray(int n){
        numberArray = new NumberArray(n);
        chosenSortingAlgorithm.reset(numberArray);
        chosenSortingAlgorithm.setInitialStates();
        graph.drawGraph(numberArray);
    }

    public void stopThread(){
        if(autoSort.isRunning()){
            autoSort.stop();
        }
    }

    public void loadJson() {
        File path = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            path = chooser.getSelectedFile();
        }
        new JSONLoader(path);
        numberArray = JSONLoader.getNumberArray();
        reset();
    }
}
