package com.example.mukul.braintrainer;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static com.example.mukul.braintrainer.R.mipmap.ic_light;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrect;
    TextView scoreTextview;
    TextView status;
    int score=0;
    int questions=0;
    Button firstButton;
    Button secondButton;
    Button thirdButton;
    Button forthButton;
    TextView sumTextview;
    TextView timerTextView;
    Button playagainButton;
    ConstraintLayout innerlayout;
    ImageView statusIcon;

    public void playagain(View view){
        questions=0;
        score=0;
        generateQuestions();
        playagainButton.setVisibility(View.INVISIBLE);
        firstButton.setEnabled(true);
        secondButton.setEnabled(true);
        thirdButton.setEnabled(true);
        forthButton.setEnabled(true);
        scoreTextview.setText("0/0");
        status.setText("");
        new CountDownTimer(31000,1000){
            public void onTick(long millis){
                timerTextView.setText(String.valueOf(millis/1000));
            }
            public void onFinish(){
                firstButton.setEnabled(false);
                secondButton.setEnabled(false);
                thirdButton.setEnabled(false);
                forthButton.setEnabled(false);
                playagainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0");
                status.setText("Your Score is "+Integer.toString(score)+"/"+Integer.toString(questions));
            }
        }.start();

    }

    public void start(View view){
        startButton.setVisibility(view.INVISIBLE);
        innerlayout.setVisibility(ConstraintLayout.VISIBLE);
        playagain(findViewById(R.id.playAgainbutton));
    }

    public void chooseAnswer(View view){
        questions++;
        Log.i("Button Pressed",String.valueOf(view.getId()));

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrect))){
            score++;
//            status.setText("Correct");
            statusIcon.setImageResource(R.mipmap.ic_light);
        }
        else{
//            status.setText("Incorrect");
            statusIcon.setImageResource(R.mipmap.ic_red);
        }
        scoreTextview.setText(Integer.toString(score)+"/"+Integer.toString(questions));
        generateQuestions();
    }

    public void generateQuestions(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        sumTextview.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrect = random.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++){
            if (i==locationOfCorrect){
                answers.add(a+b);
            }
            else{
                int inc = random.nextInt(40);
                while(inc==a+b){
                    inc = random.nextInt(40);
                }
                answers.add(inc);
            }
        }

        firstButton.setText(Integer.toString(answers.get(0)));
        secondButton.setText(Integer.toString(answers.get(1)));
        thirdButton.setText(Integer.toString(answers.get(2)));
        forthButton.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button)findViewById(R.id.startButton);
        sumTextview = (TextView)findViewById(R.id.sumTextview);
        scoreTextview = (TextView)findViewById(R.id.scoreTextview);
        firstButton = (Button)findViewById(R.id.firstOption);
        secondButton = (Button)findViewById(R.id.secondOption);
        thirdButton = (Button)findViewById(R.id.thirdOption);
        forthButton = (Button)findViewById(R.id.forthOption);
        status = (TextView)findViewById(R.id.statusTextview);
        timerTextView = (TextView)findViewById(R.id.timerTextview);
        playagainButton = (Button)findViewById(R.id.playAgainbutton);
        innerlayout = (ConstraintLayout)findViewById(R.id.innerlayout);
        statusIcon = (ImageView)findViewById(R.id.statusIcon);
        generateQuestions();

    }
}
