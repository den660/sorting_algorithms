package gui;
import javafx.scene.Node;

public interface ResultNode {
    Node getNode();
    void update(int comparisons, int arrayAccesses);
    void reset();
}
