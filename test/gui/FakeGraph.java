package gui;


import javafx.scene.Node;
import sample.NumberArray;
import sample.NumberState;

public class FakeGraph implements Graph{

    private NumberArray numberArray;

    @Override
    public void drawGraph(NumberArray numberArray) {
        this.numberArray = numberArray;
    }

    @Override
    public Node getNode() {
        return null;
    }

    public int[] getArray(){
        return numberArray.getNumbers();
    }
}
