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
    private ChoiceBox choiceBox;
    private Label sliderLabel;
    private Slider slider;
    private VBox sliderBox;
    private Button startButton;
    private Button stepButton;
    private HBox elements;
    private BarGraph barGraph;
    private NumberArray numberArray;
    private Bubblesort bubblesort;
    private SortingAlgorithm sortingAlgorithm;

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


    public UserInputs(BarGraph barGraph, Results results, SortingAlgorithm[] sortingAlgorithms){

        numberCountInput = new NumberTextField(numberCount);
        numberCountInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals("")){
                    newValue = "1";
                }
                numberArray.init(Integer.parseInt(newValue));
                sortingAlgorithm.reset();
                results.reset();
                barGraph.drawGraph(numberArray);

            }
        });
        numberCountLabel = new Label("Numbers:");
        numberCountBox = new VBox(numberCountLabel, numberCountInput);

        shuffleButton = new Button("Shuffle");
        shuffleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberArray.shuffle();
                sortingAlgorithm.reset();
                results.reset();
                barGraph.drawGraph(numberArray);
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
                sortingAlgorithm.reset();
                results.reset();
            }
        });

        sliderLabel = new Label("Delay: ");
        slider = new Slider(0,100,50);
        sliderBox = new VBox(sliderLabel, slider);

        bubblesort = new Bubblesort(); //todo
        startButton = new Button("auto");
        stepButton = new Button("step");
        stepButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sortingAlgorithm.sort(numberArray);
                barGraph.drawGraph(numberArray);
                results.update(sortingAlgorithm.getComparisons(), sortingAlgorithm.getArrayAccesses());
            }
        });

        this.barGraph = barGraph;
        numberArray = new NumberArray(numberCount);
        barGraph.drawGraph(numberArray);

        elements = new HBox(numberCountBox, shuffleButton, choiceBox, sliderBox, startButton, stepButton);
        elements.setSpacing(10);
        elements.setPadding(new Insets(10,10,10,10));
        elements.setAlignment(Pos.BOTTOM_CENTER);


    }


}
