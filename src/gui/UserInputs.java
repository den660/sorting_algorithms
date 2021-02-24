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

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class UserInputs{
    private int defaultNumber = 50;
    private TextField numbersInput;
    private Label numbersLabel;
    private VBox numbersBox;
    private Button shuffleButton;
    private ChoiceBox choiceBox;
    private Label sliderLabel;
    private Slider slider;
    private VBox sliderBox;
    private Button startButton;
    private Button stepButton;
    private HBox elements;
    private BarGraph barGraph;
    private int[] numberArray;
    private Bubblesort bubblesort;
    private SortingAlgorithm sortingAlgorithm;

    public HBox getElements() {
        return elements;
    }

    private int[] createNumberArray(int n){
        int[] numbers = new int[n];
        for(int i = 0; i < n; i++){
            numbers[i] = i+1;
        }
        return numbers;
    }

    private int[] shuffleArray(int[] ar)
    {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
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
        this.barGraph = barGraph;
        numberArray = createNumberArray(defaultNumber);
        numbersInput = new NumberTextField(defaultNumber);
        numbersInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals("")){
                    newValue = "1";
                }
                numberArray = createNumberArray(Integer.parseInt(newValue));
                barGraph.drawGraph(numberArray, sortingAlgorithm.getNextComparisons());

            }
        });
        numbersLabel = new Label("Numbers:");
        numbersBox = new VBox(numbersLabel, numbersInput);

        shuffleButton = new Button("Shuffle");
        shuffleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberArray = shuffleArray(numberArray);
                sortingAlgorithm.init();
                results.update(sortingAlgorithm.getComparisons(), sortingAlgorithm.getArrayAccesses());
                barGraph.drawGraph(numberArray, sortingAlgorithm.getNextComparisons());
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
                sortingAlgorithm.init();
                results.update(sortingAlgorithm.getComparisons(), sortingAlgorithm.getArrayAccesses());
            }
        });

        sliderLabel = new Label("Delay: ");
        slider = new Slider(0,100,50);
        sliderBox = new VBox(sliderLabel, slider);

        bubblesort = new Bubblesort();
        startButton = new Button("auto");
        stepButton = new Button("step");
        stepButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberArray = sortingAlgorithm.sort(numberArray);
                System.out.println(Arrays.toString(numberArray));
                barGraph.drawGraph(numberArray, sortingAlgorithm.getNextComparisons());
                results.update(sortingAlgorithm.getComparisons(), sortingAlgorithm.getArrayAccesses());
            }
        });
        barGraph.drawGraph(numberArray, sortingAlgorithm.getNextComparisons());
        elements = new HBox(numbersBox, shuffleButton, choiceBox, sliderBox, startButton, stepButton);
        elements.setSpacing(10);
        elements.setPadding(new Insets(10,10,10,10));
        elements.setAlignment(Pos.BOTTOM_CENTER);


    }


}
