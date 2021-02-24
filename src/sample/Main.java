package sample;

import algorithms.Bubblesort;
import algorithms.SortingAlgorithm;
import gui.BarGraph;
import gui.Results;
import gui.UserInputs;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        SortingAlgorithm[] sortingAlgorithms = {new Bubblesort()};
        Results results = new Results();
        BarGraph barGraph = new BarGraph();
        UserInputs userInputs = new UserInputs(barGraph, results, sortingAlgorithms);


        VBox vBox = new VBox(userInputs.getElements(), results.getElements(), barGraph.getElement());


        Scene scene = new Scene(vBox, 1050, 650);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }







    public static void main(String[] args) {

        launch(args);
    }
}
