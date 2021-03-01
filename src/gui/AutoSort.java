package gui;

import javafx.application.Platform;
import javafx.scene.control.Button;

public class AutoSort{

    private Thread thread = new Thread();
    private boolean isRunning = false;
    private Runnable runnable;
    private Button stepButton;

    public void setStepButton(Button stepButton) {
        this.stepButton = stepButton;
    }

    public AutoSort(){
        runnable =
                new Runnable(){
                    public void run(){
                        while(isRunning){
                            System.out.println("Runnable running");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    stepButton.fire();
                                }
                            });

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                };
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void start(){
        if(!thread.isAlive()){
            thread = new Thread(runnable);
            thread.start();
            System.out.println(Thread.currentThread().getId());
        }
        isRunning = true;

    }
    public void stop(){
        System.out.println(thread.isAlive());
        isRunning = false;
    }
}
