package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Results {


    private Label comparisonsLabel;
    private Label arrayAccessesLabel;
    private HBox elements;

    public Node getNode(){
        return elements;
    }

    public void reset(){
        comparisonsLabel.setText("comparisons: 0");
        arrayAccessesLabel.setText("array accesses: 0");
    }

    public void update(int comparisons, int arrayAccesses){
        comparisonsLabel.setText("comparisons: " + comparisons);
        arrayAccessesLabel.setText("array accesses: " + arrayAccesses);
    }

    public Results(){
        comparisonsLabel = new Label("comparisons: 0");
        arrayAccessesLabel = new Label("array accesses: 0");
        elements = new HBox(comparisonsLabel, arrayAccessesLabel);
        elements.setSpacing(10);
        elements.setPadding(new Insets(10,10,10,10));
        elements.setAlignment(Pos.BOTTOM_CENTER);
    }
}
