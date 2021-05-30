package gui;

import javafx.scene.Node;

public class FakeInputNode implements InputNode{

    private String startButtonText;

    public boolean isStartButtonDisabled() {
        return startButtonDisabled;
    }

    public boolean isStepButtonDisabled() {
        return stepButtonDiabled;
    }

    private boolean startButtonDisabled = false;
    private boolean stepButtonDiabled = false;

    @Override
    public Node getNode() {
        return null;
    }

    @Override
    public void disableStartButton(boolean bool) {
        startButtonDisabled = bool;
    }

    @Override
    public void disableStepButton(boolean bool) {
        stepButtonDiabled = bool;
    }

    @Override
    public void setStartButtonText(String text) {
        startButtonText = text;
    }

    public String getStartButtonText(){
        return startButtonText;
    }

}
