package gui;

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

    public UserInputs(BarGraph barGraph){
        this.barGraph = barGraph;
        numberArray = createNumberArray(defaultNumber);
        barGraph.drawGraph(numberArray);
        numbersInput = new NumberTextField(defaultNumber);
        numbersInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals("")){
                    newValue = "1";
                }
                numberArray = createNumberArray(Integer.parseInt(newValue));
                barGraph.drawGraph(numberArray);

            }
        });
        numbersLabel = new Label("Numbers:");
        numbersBox = new VBox(numbersLabel, numbersInput);

        shuffleButton = new Button("Shuffle");
        shuffleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberArray = shuffleArray(numberArray);
                barGraph.drawGraph(numberArray);
            }
        });

        choiceBox = new ChoiceBox();
        choiceBox.getItems().add("Bubblesort");
        choiceBox.getItems().add("Quicksort");

        sliderLabel = new Label("Delay: ");
        slider = new Slider(0,100,50);
        sliderBox = new VBox(sliderLabel, slider);

        startButton = new Button("auto");
        stepButton = new Button("step");

        elements = new HBox(numbersBox, shuffleButton, choiceBox, sliderBox, startButton, stepButton);
        elements.setSpacing(10);
        elements.setPadding(new Insets(10,10,10,10));
        elements.setAlignment(Pos.BOTTOM_CENTER);


    }


}
