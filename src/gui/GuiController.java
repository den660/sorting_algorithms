package gui;
import algorithms.*;
import sample.NumberArray;


public class GuiController {

    private final ResultNode results;
    private final Graph graph;
    private NumberArray numberArray;
    private SortingAlgorithm chosenSortingAlgorithm;
    private final AutoSort autoSortThread = new AutoSortThread(this);
    private UserInputs userInputs;

    public GuiController(ResultNode results, Graph graph){
        this.results = results;
        this.graph = graph;
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
        if(autoSortThread.isRunning()){
            autoSortThread.stop();
        }

        numberArray.loadCopy();
        reset();
    }

    public void sortArray(){
        if(chosenSortingAlgorithm.sort()){
            autoSortThread.stop();
            userInputs.disableStepButton(true);
            userInputs.disableStartButton(true);
        }

        graph.drawGraph(numberArray);
        results.update(chosenSortingAlgorithm.getComparisons(), chosenSortingAlgorithm.getArrayAccesses());
    }


    private void reset(){
        chosenSortingAlgorithm.reset(numberArray);
        chosenSortingAlgorithm.setInitialStates();
        results.reset();
        graph.drawGraph(numberArray);
        userInputs.disableStepButton(false);
        userInputs.disableStartButton(false);
        userInputs.setStartButtonText("Start");
    }


    public void changeDelay(int delay){
        autoSortThread.setDelay(delay);
    }

    public void triggerAutoSort(){
        if(autoSortThread.isRunning()){
            userInputs.setStartButtonText("Start");
            autoSortThread.stop();
        }
        else{
            userInputs.setStartButtonText("Stop");
            autoSortThread.start();
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
        if(autoSortThread.isRunning()){
            autoSortThread.stop();
        }
    }
}
