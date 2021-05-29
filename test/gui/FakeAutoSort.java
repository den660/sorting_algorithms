package gui;

import javafx.application.Platform;

public class FakeAutoSort implements AutoSort{

    private Thread thread = new Thread();
    private boolean isRunning = false;
    private Runnable runnable;

    private int delay = 500;

    public FakeAutoSort(){

    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDelay(){
        return delay;
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

                        try {
                            Thread.sleep(delay);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                };
    }
}
