package sample;

import algorithms.Bubblesort;
import algorithms.SortingAlgorithm;
import gui.BarGraph;
import gui.Results;
import gui.UserInputs;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
    private UserInputs userInputs;

    @Override
    public void start(Stage primaryStage) throws Exception{

        SortingAlgorithm[] sortingAlgorithms = {new Bubblesort()};
        Results results = new Results();
        BarGraph barGraph = new BarGraph();
        UserInputs userInputs = new UserInputs(barGraph, results, sortingAlgorithms);


        VBox vBox = new VBox(userInputs.getElements(), results.getElements(), barGraph.getElement());

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                userInputs.stopThread();
                Platform.exit();
                System.exit(0);
            }
        });

        Scene scene = new Scene(vBox, 1050, 650);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }







    public static void main(String[] args) {

        launch(args);
    }
}
