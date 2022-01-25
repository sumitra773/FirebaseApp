package com.example.android.firebaseapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.firebaseapp.R;

public class QuizActivity extends AppCompatActivity {

    private EditText mEditText;
    private TextView mView;
    Button mSubmit;
    CountDownTimer cTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mEditText = findViewById(R.id.edt_text);
        mEditText.setEnabled(true);
        mSubmit = findViewById(R.id.btn_submit);
        mSubmit.setVisibility(View.GONE);
        mView = findViewById(R.id.txt_timer);

        timer();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
    private void timer(){
        cTimer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {

                //String value = String.valueOf(seconds remaining: " + millisUntilFinished/1000);
                mView.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mSubmit.setVisibility(View.VISIBLE);
                mEditText.setEnabled(false);
                mView.setText("Time Over!");
            }
        }.start();
    }
}