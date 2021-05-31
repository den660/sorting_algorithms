package gui;

import javafx.scene.Node;

public interface InputNode {
    Node getNode();

    void disableStartButton(boolean bool);

    void disableStepButton(boolean bool);

    void setStartButtonText(String text);
}
