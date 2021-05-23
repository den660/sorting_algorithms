package gui;

import javafx.application.Platform;

public class AutoSort{

    private Thread thread = new Thread();
    private boolean isRunning = false;
    private Runnable runnable;

    private int delay = 500;

    public AutoSort(GuiController guiController){
        runnable =
                new Runnable(){
                    public void run(){
                        while(isRunning){
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    guiController.sortArray();
                                }
                            });

                            try {
                                Thread.sleep(delay);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                };
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
}
