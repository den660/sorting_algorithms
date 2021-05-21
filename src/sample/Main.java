package sample;

import algorithms.*;
//import algorithms.quicksort.Quicksort;
import algorithms.quicksort.Quicksort;
import gui.BarGraph;
import gui.GuiController;
import gui.Results;
import gui.UserInputs;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
    GuiController guiController;

    @Override
    public void start(Stage primaryStage) throws Exception{

        guiController = new GuiController();
        VBox vBox = new VBox(guiController.getElements());
        stopThreadOnCloseRequest(primaryStage);
        Scene scene = new Scene(vBox, 1050, 650);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }

    private void stopThreadOnCloseRequest(Stage primaryStage) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                guiController.stopThread();
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {

        launch(args);
    }
}





