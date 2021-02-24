package gui;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.stream.IntStream;

public class BarGraph {
    private Canvas canvas;
    private StackPane canvasContainer;

    public StackPane getElement(){
        return canvasContainer;
    }

    public void drawGraph(int[] numberArray, int[] highlights){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,canvas.getWidth(), canvas.getHeight());
        gc.setLineWidth(9);
        for(int i = 0; i < numberArray.length; i++){
            final int fi = i;
            gc.setStroke(Color.BLUE);
            if(IntStream.of(highlights).anyMatch(x -> x == fi)){
                gc.setStroke(Color.RED);
            }
            gc.strokeLine((i*10)+5, canvas.getHeight()-0, (i*10)+5, canvas.getHeight()-(numberArray[i]*5));
        }

    }

    public BarGraph(){
        canvas = new Canvas(1000, 510);
        canvasContainer = new StackPane(canvas);
        canvasContainer.setPadding(new Insets(10,10,10,10));
    }
}
