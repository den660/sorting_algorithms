package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class FakeResultNode implements ResultNode {

    private int comparisons;
    private int arrayAccesses;
    private boolean reseted = false;

    @Override
    public Node getNode() {
        return null;
    }

    @Override
    public void update(int comparisons, int arrayAccesses) {
        this.comparisons = comparisons;
        this.arrayAccesses = arrayAccesses;
    }

    @Override
    public void reset() {
        reseted = true;
    }

    public int getComparisons(){
        return comparisons;
    }

    public int getArrayAccesses(){
        return arrayAccesses;
    }

    public boolean isReseted(){
        return reseted;
    }
}

