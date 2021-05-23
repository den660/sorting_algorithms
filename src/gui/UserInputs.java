package gui;

import algorithms.*;
import algorithms.quicksort.Quicksort;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class UserInputs{
    private Label delaySliderLabel;
    private Button startButton;
    private Button stepButton;
    private Button resetButton;
    private HBox elements;
    private GuiController guiController;
    private int initialArraySize = 50;
    private boolean autoSortIsRunning = false;
    private SortingAlgorithm[] sortingAlgorithms;

    public Node getNode() {
        return elements;
    }

    public void disableStartButton(boolean b){
        startButton.setDisable(b);
    }

    public void disableStepButton(boolean b){
        stepButton.setDisable(b);
    }

    public void setStartButtonText(String text){
        startButton.setText(text);
    }


    public UserInputs(GuiController guiController){
        this.guiController = guiController;
        sortingAlgorithms = new SortingAlgorithm[]{
                new Bubblesort(),
                new Insertionsort(),
                new Heapsort(),
                new Quicksort(),
                new Shellsort(),
                new Mergesort(),
                new Selectionsort(),
                new Gnomesort()};


        VBox numberSliderBox = initNumberSlider();

        Button shuffleButton = initShuffleButton();

        Button reverseButton = initReverseButton();

        ChoiceBox choiceBox = initChoiceBox(sortingAlgorithms);

        VBox delaySliderBox = initDelaySlider();

        initStartButton();

        initStepButton();

        initResetButton();

        guiController.initNumberArray(initialArraySize);

        elements = new HBox(numberSliderBox, shuffleButton, reverseButton, choiceBox, delaySliderBox, startButton, stepButton, resetButton);
        elements.setSpacing(10);
        elements.setPadding(new Insets(10,10,10,10));
        elements.setAlignment(Pos.BOTTOM_CENTER);
    }

    private void initResetButton() {
        resetButton = new Button("Reset");
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startButton.setText("Start");
                guiController.resetArray();
            }
        });
    }

    private void initStepButton() {
        stepButton = new Button("Step");
        stepButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                guiController.sortArray();
            }
        });
    }

    private void initStartButton() {
        startButton = new Button("Start");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                guiController.triggerAutoSort();
            }
        });
    }

    private VBox initDelaySlider() {
        delaySliderLabel = new Label("Delay: 30");
        Slider delaySlider = new Slider(1,1000,500);
        VBox delaySliderBox = new VBox(delaySliderLabel, delaySlider);
        delaySlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                int sliderVal = new_val.intValue();
                int delay = (int)Math.ceil(0.993109*Math.exp(0.00691467*sliderVal));
                delaySliderLabel.setText("Delay: " + delay);
                guiController.changeDelay(delay);
            }
        });
        return delaySliderBox;
    }

    private ChoiceBox initChoiceBox(SortingAlgorithm[] sortingAlgorithms) {
        ChoiceBox choiceBox = new ChoiceBox();
        for(SortingAlgorithm sortingAlgorithm : sortingAlgorithms){
            choiceBox.getItems().add(sortingAlgorithm.getName());
        }
        choiceBox.setValue(sortingAlgorithms[0].getName());
        guiController.changeSortingAlgorithm(sortingAlgorithms[0]);
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                SortingAlgorithm sortingAlgorithm = sortingAlgorithms[newValue.intValue()];
                guiController.changeSortingAlgorithm(sortingAlgorithm);
                guiController.resetArray();
            }
        });
        return choiceBox;
    }

    private Button initReverseButton() {
        Button reverseButton = new Button("Reverse");
        reverseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                guiController.reverseArray();
            }
        });
        return reverseButton;
    }

    private Button initShuffleButton() {
        Button shuffleButton = new Button("Shuffle");
        shuffleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                guiController.shuffleArray();
            }
        });
        return shuffleButton;
    }

    private VBox initNumberSlider() {
        Label numberSliderLabel = new Label("Array Size: " + initialArraySize);
        Slider numberSlider = new Slider(2,100,initialArraySize);
        VBox numberSliderBox = new VBox(numberSliderLabel, numberSlider);
        numberSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {


                numberSliderLabel.setText("Array Size: " + new_val.intValue());
                guiController.changeArraySize(new_val.intValue());
            }
        });
        return numberSliderBox;
    }


}
