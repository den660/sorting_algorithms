package gui;

import javafx.application.Platform;

public class AutoSortThread implements AutoSort{

    private Thread thread = new Thread();
    private boolean isRunning = false;
    private Runnable runnable;

    private int delay = 500;

    public AutoSortThread(){

    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void start(){
        if(!thread.isAlive()){
            thread = new Thread(runnable);
            thread.start();
        }
        isRunning = true;
    }
    public void stop(){
        if(isRunning){
            isRunning=false;
        }

    }

    @Override
    public void setRunnable(GuiController guiController) {
        runnable =
                () -> {
                    while(isRunning){
                        Platform.runLater(() -> guiController.sortArray());

                        try {
                            Thread.sleep(delay);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                };
    }
}
