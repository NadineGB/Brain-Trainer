package com.example.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView resultTextView, scoreTextView, sumTextView, timerTextView;
    Button button0, button1, button2, button3;
    Button playAgainButton;
    GridLayout auswahlfelderGridView;
    LinearLayout toplLinearLayout;
    int score;
    int numberOfQuestions;
    int locationOfCorrectAnswer;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    public void playAgain(View view) {

        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        playAgainButton.setVisibility(View.INVISIBLE);
        auswahlfelderGridView.setVisibility(View.VISIBLE);
        resultTextView.setText("Los!");
        sumTextView.setVisibility(View.VISIBLE);
        newQuestion();

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Fertig!");
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                auswahlfelderGridView.setVisibility(View.INVISIBLE);
                sumTextView.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
        toplLinearLayout.setVisibility(View.VISIBLE);
        auswahlfelderGridView.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }

    public void chooseAnswerButton(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals((view.getTag().toString()))) {
            resultTextView.setText("Korrekt!");
            score++;
        } else {
            resultTextView.setText("Falsch!");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }

    public void newQuestion() {
        Random rnd = new Random();

        int a = rnd.nextInt(21);
        int b = rnd.nextInt(21);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswer = rnd.nextInt(4);

        answers.clear();

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                int wrongAnswer = rnd.nextInt(41);

                while (wrongAnswer == a + b) {
                    wrongAnswer = rnd.nextInt(41);
                }
                for (int answer : answers) {
                    if (wrongAnswer == answer) {
                        wrongAnswer = rnd.nextInt(41);
                    }
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainButton);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        sumTextView = findViewById(R.id.sumTextView);
        timerTextView = findViewById(R.id.timerTextView);
        auswahlfelderGridView = findViewById(R.id.auswahlfelderGridView);
        toplLinearLayout = findViewById(R.id.toplLinearLayout);
    }
}
