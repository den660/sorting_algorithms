package sample;

import gui.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
    GuiController guiController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Results results = new Results();
        BarGraph barGraph = new BarGraph();
        AutoSort autoSortThread = new AutoSortThread();
        guiController = new GuiController(results, barGraph, autoSortThread);

        UserInputs userInputs = new UserInputs(guiController);
        guiController.setUserInputs(userInputs);

        VBox vBox = new VBox(userInputs.getNode(), results.getNode(), barGraph.getNode());
        stopThreadOnCloseRequest(primaryStage);
        Scene scene = new Scene(vBox, 1050, 650);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sorting Algorithms visualized");
        primaryStage.show();
    }

    private void stopThreadOnCloseRequest(Stage primaryStage) {
        primaryStage.setOnCloseRequest(e -> {
            guiController.stopThread();
            Platform.exit();
            System.exit(0);
        });
    }

    public static void main(String[] args) {

        launch(args);
    }
}





