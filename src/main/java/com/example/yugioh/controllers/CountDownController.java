package com.example.yugioh.controllers;

import com.example.yugioh.models.player.CountDown;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;


public class CountDownController {
    private final CountDown countDownTimer = new CountDown();
    private boolean stopRunning = false;
    @FXML
    private Label timeRemaining;
    @FXML
    private ProgressBar countDownProgressBar;

    public void startTimer() {
        Thread timerThread = new Thread(() -> {
            decrement();
        });
        timerThread.setDaemon(true);
        timerThread.start();
    }

    public void stopTimer() {
        stopRunning = true;
    }

    private void resetCount(){
        countDownTimer.getRemainingTime().setValue(countDownTimer.getTimeToGive().getValue());
    }

    public void decrement()
    {
        while (countDownTimer.getRemainingTime().getValue() > 0 && !stopRunning) {
            Platform.runLater(() -> {
                countDownTimer.decrementTime();
                updateUi();
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("count stopped");
        resetCount();
        updateUi();
    }
    public void updateUi() {

        timeRemaining.setText(countDownTimer.getRemainingTime().getValue().toString());
        countDownProgressBar.setProgress((double) countDownTimer.getRemainingTime().getValue()/countDownTimer.getTimeToGive().getValue());
    }
}
