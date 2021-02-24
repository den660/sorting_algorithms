package sample;

import javafx.scene.paint.Color;

public enum NumberState {
    FIXED(Color.GREEN), NEXTCOMPARISON(Color.RED), UNDEFINED(Color.BLUE);

    Color color;

    NumberState(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
