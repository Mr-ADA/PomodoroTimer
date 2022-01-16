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

public class StudyFragment extends Fragment {

    private TextView etStudyTimer;
    private View view;
    private Button studyStartBtn;

    private long startTimeInMillis;
    private final int minute = 0;
    private final int second = 9;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1_layout, container, false);
        etStudyTimer = view.findViewById(R.id.tv_study_timer);
        studyStartBtn = view.findViewById(R.id.btn_start_study);

        if(minute <= 9 && second <= 9){
            etStudyTimer.setText("0"+minute + ":0" + second);
        }else if(minute <= 9){
            etStudyTimer.setText("0"+minute + ":" + second);
        }else if(second <= 9){
            etStudyTimer.setText(minute + ":0" + second);
        }else{
            etStudyTimer.setText(minute + ":" + second);
        }

        studyStartBtn.setOnClickListener(new View.OnClickListener() {
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
                studyStartBtn.setClickable(false);
                studyStartBtn.setBackgroundColor(Color.GRAY);
            }

            @Override
            public void onFinish() {
                studyStartBtn.setClickable(true);
                studyStartBtn.setBackgroundColor(Color.rgb(98,0,238));
                resetTimer();
            }
        }.start();
    }

    @SuppressLint("SetTextI18n")
    private void updateCountDownText(){
        int minutes = (int)(timeLeftInMillis / 1000) / 60;
        int second = (int) (timeLeftInMillis / 1000) % 60;

        if(minutes <= 9 && second <= 9) {
            etStudyTimer.setText("0"+minutes + ":0" + second);
        }else if(minutes <= 9){
            etStudyTimer.setText("0"+minutes + ":" + second);
        }else if(second <= 9){
            etStudyTimer.setText(minutes + ":0" + second);
        }else{
            etStudyTimer.setText(minutes + ":" + second);
        }

    }

    private void resetTimer(){
        timeLeftInMillis = startTimeInMillis;
        updateCountDownText();
    }
}
