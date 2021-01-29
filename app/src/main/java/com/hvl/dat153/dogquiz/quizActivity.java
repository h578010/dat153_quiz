package com.hvl.dat153.dogquiz;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Collections;
import java.util.List;

public class quizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewCount;
    private RadioGroup group;
    private RadioButton rBtn1;
    private RadioButton rBtn2;
    private RadioButton rBtn3;
    private Button answerBtn;
    private ColorStateList textColorDefault;
    private List<Questions> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Questions currentQuestion;
    private boolean answered;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        textViewQuestion = findViewById(R.id.question_text);
        textViewScore = findViewById(R.id.textViewScore);
        textViewCount = findViewById(R.id.count);
        group = findViewById(R.id.radio_group);
        rBtn1 = findViewById(R.id.radio1);
        rBtn2 = findViewById(R.id.radio2);
        rBtn3 = findViewById(R.id.radio3);
        answerBtn = findViewById(R.id.button_answer);

        textColorDefault = rBtn1.getTextColors();

        DbHelper dbHelper = new DbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        textViewScore.setText("Score: " + score + "/" + questionCountTotal);
        // Collections.shuffle(questionList);

        showNextQuestion();
        answerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rBtn1.isChecked() || rBtn2.isChecked() || rBtn3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(quizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {
        rBtn1.setTextColor(textColorDefault);
        rBtn2.setTextColor(textColorDefault);
        rBtn3.setTextColor(textColorDefault);
        group.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestion.getQuestion());
            rBtn1.setText(currentQuestion.getOption1());
            rBtn2.setText(currentQuestion.getOption2());
            rBtn3.setText(currentQuestion.getOption3());

            questionCounter++;
            textViewCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            answerBtn.setText("Answer");
        } else {
            finishQuiz();
        }
    }

    private void checkAnswer() {
        answered = true;
        RadioButton rBtnSelected = findViewById(group.getCheckedRadioButtonId());
        int answerNo = group.indexOfChild(rBtnSelected)+1;
        if (answerNo == currentQuestion.getAnswerNo()) {
            score ++;
            textViewScore.setText("Score: " + score + "/" + questionCountTotal);
        }
        showSolution();
    }

    private void showSolution() {
        rBtn1.setTextColor(Color.RED);
        rBtn2.setTextColor(Color.RED);
        rBtn3.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNo()) {
            case 1:
                rBtn1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is correct.");
                break;
            case 2:
                rBtn2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is correct.");
                break;
            case 3:
                rBtn3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is correct.");
                break;
        }

        if (questionCounter < questionCountTotal) {
            answerBtn.setText("Next");
        } else {
            answerBtn.setText("Finish");
        }
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }







}