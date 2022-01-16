package com.example.pomodorotimer;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LongBreakFragment extends Fragment {
    private TextView tvLongBreakTime;
    private View view;
    private Button longBreakBtn;

    private long startTimeInMillis;
    private final int minute = 0;
    private final int second = 9;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment3_layout, container, false);
        tvLongBreakTime = view.findViewById(R.id.tv_longbreak_timer);
        longBreakBtn = view.findViewById(R.id.btn_start_longbreak);

        if(minute <= 9 && second <= 9){
            tvLongBreakTime.setText("0"+minute + ":0" + second);
        }else if(minute <= 9){
            tvLongBreakTime.setText("0"+minute + ":" + second);
        }else if(second <= 9){
            tvLongBreakTime.setText(minute + ":0" + second);
        }else{
            tvLongBreakTime.setText(minute + ":" + second);
        }

        longBreakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimeInMillis = (long)((minute * 60) + second) * 1000;
                timeLeftInMillis = startTimeInMillis;
                StartTimer();
            }
        });
        return view;
    }

    private void StartTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                longBreakBtn.setClickable(false);
                longBreakBtn.setBackgroundColor(Color.GRAY);
            }

            @Override
            public void onFinish() {
                longBreakBtn.setClickable(true);
                longBreakBtn.setBackgroundColor(Color.rgb(98,0,238));
                resetTimer();
            }
        }.start();
    }

    @SuppressLint("SetTextI18n")
    private void updateCountDownText(){
        int minutes = (int)(timeLeftInMillis / 1000) / 60;
        int second = (int) (timeLeftInMillis / 1000) % 60;

        if(minutes <= 9 && second <= 9) {
            tvLongBreakTime.setText("0"+minutes + ":0" + second);
        }else if(minutes <= 9){
            tvLongBreakTime.setText("0"+minutes + ":" + second);
        }else if(second <= 9){
            tvLongBreakTime.setText(minutes + ":0" + second);
        }else{
            tvLongBreakTime.setText(minutes + ":" + second);
        }

    }

    private void resetTimer(){
        timeLeftInMillis = startTimeInMillis;
        updateCountDownText();
    }
}
