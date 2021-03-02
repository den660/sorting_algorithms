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
    private Label sliderLabel;
    private Slider slider;
    private VBox sliderBox;
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


    private SortingAlgorithm getSortingAlgorithmByName(String name, SortingAlgorithm[] sortingAlgorithms){
        for(SortingAlgorithm sortingAlgorithm : sortingAlgorithms){
            if(sortingAlgorithm.getName().equals(name)){
                return sortingAlgorithm;
            }
        }
        return null;
    }

    private void reset(){
        sortingAlgorithm.reset();
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
        numberCountInput = new NumberTextField(numberCount);
        numberCountInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals("")){
                    newValue = "1";
                }
                numberArray.init(Integer.parseInt(newValue));
                reset();

            }
        });
        numberCountLabel = new Label("Numbers:");
        numberCountBox = new VBox(numberCountLabel, numberCountInput);

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
        sortingAlgorithm = getSortingAlgorithmByName(choiceBox.getItems().get(0).toString(), sortingAlgorithms);
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                sortingAlgorithm = getSortingAlgorithmByName(choiceBox.getItems().get(0).toString(), sortingAlgorithms);
                reset();
            }
        });

        sliderLabel = new Label("Delay: 500");
        slider = new Slider(1,1000,500);
        sliderBox = new VBox(sliderLabel, slider);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                int sliderVal = new_val.intValue();
                sliderLabel.setText("Delay: " + sliderVal);
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
                if(sortingAlgorithm.sort(numberArray)){
                    if(autoSort.isRunning()){
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
                    startButton.setText("Start");
                    autoSort.stop();
                }
                numberArray.loadCopy();
                reset();
            }
        });

        this.barGraph = barGraph;
        numberArray = new NumberArray(numberCount);
        barGraph.drawGraph(numberArray);

        elements = new HBox(numberCountBox, shuffleButton, reverseButton, choiceBox, sliderBox, startButton, stepButton, resetButton);
        elements.setSpacing(10);
        elements.setPadding(new Insets(10,10,10,10));
        elements.setAlignment(Pos.BOTTOM_CENTER);


    }


}
