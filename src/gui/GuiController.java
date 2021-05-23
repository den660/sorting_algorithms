package gui;
import algorithms.*;
import sample.NumberArray;


public class GuiController {

    private Results results;
    private BarGraph barGraph;
    private NumberArray numberArray;
    private SortingAlgorithm chosenSortingAlgorithm;
    private AutoSort autoSort = new AutoSort(this);
    private UserInputs userInputs;

    public GuiController(Results results, BarGraph barGraph){
        this.results = results;
        this.barGraph = barGraph;


    }

    public void setUserInputs(UserInputs userInputs){
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

        barGraph.drawGraph(numberArray);
        results.update(chosenSortingAlgorithm.getComparisons(), chosenSortingAlgorithm.getArrayAccesses());
    }


    private void reset(){
        chosenSortingAlgorithm.reset(numberArray);
        chosenSortingAlgorithm.setInitialStates();
        results.reset();
        barGraph.drawGraph(numberArray);
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
        barGraph.drawGraph(numberArray);
    }

    public void stopThread(){
        if(autoSort.isRunning()){
            autoSort.stop();
        }
    }
}
