package gui;

import javafx.application.Platform;
import javafx.scene.control.Button;

public class AutoSort{

    private Thread thread = new Thread();
    private boolean isRunning = false;
//    private boolean stopFlag = true;
    private Runnable runnable;
    private Button stepButton;
    private int delay = 500;

    public void setStepButton(Button stepButton) {
        this.stepButton = stepButton;
    }

    public AutoSort(){
        runnable =
                new Runnable(){
                    public void run(){
                        while(isRunning){
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    stepButton.fire();
                                }
                            });

                            try {
                                Thread.sleep(delay);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
//                        isRunning = false;
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
//        stopFlag = false;
        isRunning = true;
    }
    public void stop(){
        isRunning=false;
    }
}
