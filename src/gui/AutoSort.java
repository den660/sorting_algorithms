package gui;

public interface AutoSort {
    void setDelay(int delay);
    boolean isRunning();
    void start();
    void stop();
    void setRunnable(GuiController guiController);
}
