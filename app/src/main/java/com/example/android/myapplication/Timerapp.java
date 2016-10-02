package com.example.android.myapplication;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;

public class Timerapp extends AppCompatActivity {

    SeekBar seekBar;
    TextView timertextView;
    Boolean countDown = false;
    Button controller;
    CountDownTimer countDownTimer;

    public void resetTimer(){

        timertextView.setText("00:30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        controller.setText("Go!");
        seekBar.setEnabled(true);
        countDown=false;
    }

    public void updateTimer(int secondsLeft) {
        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;
        String secondString = Integer.toString(seconds);
        if (seconds <= 9) {
            secondString = "0" + secondString;
        }
        String minuteString = Integer.toString(minutes);
        if (minutes <= 9) {
            minuteString = "0" + minuteString;
        }

        timertextView.setText(minuteString + ":" + secondString);


    }

    public void controlTimer(View view) {

        if (countDown == false) {

            countDown = true;
            seekBar.setEnabled(false);
            controller.setText("Stop");


            countDownTimer= new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    Log.i("Seconds left", String.valueOf(l / 1000));
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    resetTimer();
                    Log.i("Done", "Countdown done");
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();

                }
            }.start();

        }
        else{
            resetTimer();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timerapp);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        timertextView = (TextView) findViewById(R.id.timertextView);
        controller = (Button) findViewById(R.id.controller);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}
