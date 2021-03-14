package gui;

import algorithms.Bubblesort;
import algorithms.SortingAlgorithm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import sample.NumberArray;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class UserInputs{
    private int numberCount = 50;
    private TextField numberCountInput;
    private Label numberCountLabel;
    private VBox numberCountBox;
    private Button shuffleButton;
    private Button reverseButton;
    private ChoiceBox choiceBox;
    private Label delaySliderLabel;
    private Slider delaySlider;
    private VBox delaySliderBox;
    private Button startButton;
    private Button stepButton;
    private Button resetButton;
    private HBox elements;
    private BarGraph barGraph;
    private NumberArray numberArray;
    private Bubblesort bubblesort;
    private SortingAlgorithm sortingAlgorithm;
    private AutoSort autoSort = new AutoSort();
    private Results results;

    public HBox getElements() {
        return elements;
    }



    private void reset(){
        sortingAlgorithm.reset(numberArray);
        sortingAlgorithm.setInitialStates();
        results.reset();
        barGraph.drawGraph(numberArray);
        stepButton.setDisable(false);
        startButton.setDisable(false);
        startButton.setText("Start");
    }

    public void stopThread(){
        if(autoSort.isRunning()){
            startButton.setText("Start");
            autoSort.stop();
        }
    }



    public UserInputs(BarGraph barGraph, Results results, SortingAlgorithm[] sortingAlgorithms){
        this.results = results;

        Label numberSliderLabel = new Label("Array Size: 50");
        Slider numberSlider = new Slider(2,100,50);
        VBox numberSliderBox = new VBox(numberSliderLabel, numberSlider);
        numberSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                numberArray.init(new_val.intValue());
                numberSliderLabel.setText("Array Size: " + new_val.intValue());
                reset();
            }
        });



        shuffleButton = new Button("Shuffle");
        shuffleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberArray.shuffle();
                reset();
            }
        });

        reverseButton = new Button("Reverse");
        reverseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberArray.reverse();
                reset();
            }
        });

        choiceBox = new ChoiceBox();
        for(SortingAlgorithm sortingAlgorithm : sortingAlgorithms){
            choiceBox.getItems().add(sortingAlgorithm.getName());
        }
        choiceBox.setValue(sortingAlgorithms[0].getName());
        sortingAlgorithm = sortingAlgorithms[0];
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                sortingAlgorithm = sortingAlgorithms[newValue.intValue()];
                resetButton.fire();
            }
        });

        delaySliderLabel = new Label("Delay: 30");
        delaySlider = new Slider(1,1000,500);
        delaySliderBox = new VBox(delaySliderLabel, delaySlider);
        delaySlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                int sliderVal = new_val.intValue();
                sliderVal = (int)Math.ceil(0.993109*Math.exp(0.00691467*sliderVal));
                delaySliderLabel.setText("Delay: " + sliderVal);
                autoSort.setDelay(sliderVal);
            }
        });

        startButton = new Button("Start");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(autoSort.isRunning()){

                    startButton.setText("Start");
                    autoSort.stop();
                }
                else{
                    startButton.setText("Stop");
                    autoSort.start();
                }
            }
        });
        stepButton = new Button("Step");
        stepButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(sortingAlgorithm.sort()){
                    if(autoSort.isRunning()){   //todo ??
                        autoSort.stop();
                    }
                    stepButton.setDisable(true);
                    startButton.setDisable(true);
                }

                barGraph.drawGraph(numberArray);
                results.update(sortingAlgorithm.getComparisons(), sortingAlgorithm.getArrayAccesses());
            }
        });
        autoSort.setStepButton(stepButton);

        resetButton = new Button("Reset");
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(autoSort.isRunning()){
                    autoSort.stop();
                    startButton.setText("Start");
//                    while (autoSort.isRunning()){
//
//                    }
                }

                numberArray.loadCopy();
                reset();
            }
        });

        this.barGraph = barGraph;
        numberArray = new NumberArray(numberCount);
        sortingAlgorithm.reset(numberArray);
        sortingAlgorithm.setInitialStates();
        barGraph.drawGraph(numberArray);

        elements = new HBox(numberSliderBox, shuffleButton, reverseButton, choiceBox, delaySliderBox, startButton, stepButton, resetButton);
        elements.setSpacing(10);
        elements.setPadding(new Insets(10,10,10,10));
        elements.setAlignment(Pos.BOTTOM_CENTER);


    }


}
