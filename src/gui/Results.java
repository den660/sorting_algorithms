package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Results {
    private Label comparisonsLabel;

    private HBox elements;

    public HBox getElements(){
        return elements;
    }

    public Results(){
        comparisonsLabel = new Label("Comparisons: ");
        elements = new HBox(comparisonsLabel);
        elements.setSpacing(10);
        elements.setPadding(new Insets(10,10,10,10));
        elements.setAlignment(Pos.BOTTOM_CENTER);
    }
}
