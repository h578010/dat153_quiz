package com.hvl.dat153.dogquiz;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    private TextView textViewQuestion;
    private ImageView questionImage;
    private TextView textViewScore;
    private TextView textViewCount;
    private RadioGroup group;
    private RadioButton rBtn1;
    private RadioButton rBtn2;
    private RadioButton rBtn3;
    private Button answerBtn;
    private ColorStateList textColorDefault;
    private List<Dog> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Dog currentQuestion;
    private boolean answered;
    private int score;
    private Pair<Integer, Integer>  wrongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        textViewQuestion = findViewById(R.id.question_text);
        questionImage = findViewById(R.id.question_image);
        textViewScore = findViewById(R.id.textViewScore);
        textViewCount = findViewById(R.id.count);
        group = findViewById(R.id.radio_group);
        rBtn1 = findViewById(R.id.radio1);
        rBtn2 = findViewById(R.id.radio2);
        rBtn3 = findViewById(R.id.radio3);
        answerBtn = findViewById(R.id.button_answer);

        textColorDefault = rBtn1.getTextColors();

        DogRoomDB db = DogRoomDB.getDatabase(this);
        questionList = db.dogDao().getAllDogs();

        questionCountTotal = questionList.size();
        textViewScore.setText("Score: " + score + "/" + questionCountTotal);

        Collections.shuffle(questionList);

        if (questionCountTotal > 0) {
            showNextQuestion();
        } else {
            Toast.makeText(QuizActivity.this, "There are no questions, try adding some", Toast.LENGTH_SHORT).show();
        }


        answerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rBtn1.isChecked() || rBtn2.isChecked() || rBtn3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
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
            textViewQuestion.setText("Which type of breed is this?");
            Uri imageUri = Uri.parse(currentQuestion.getImageUri());
            questionImage.setImageURI(imageUri);

            wrongs = getRandomInts(questionCountTotal, questionCounter);
            rBtn1.setText(currentQuestion.getAnswer());
            rBtn2.setText(questionList.get(wrongs.first).getAnswer());
            rBtn3.setText(questionList.get(wrongs.second).getAnswer());

            questionCounter++;
            textViewCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            answerBtn.setText("Answer");
        } else {
            finishQuiz();
        }
    }

    private Pair<Integer, Integer> getRandomInts(int size, int current) {
        Random rand = new Random();

        int randNo1 = rand.nextInt(size-2);
        int randNo2 = rand.nextInt(size-2);

        if (randNo1 == randNo2) {
            randNo2++;
        }
        if (randNo1 >= current) {
            randNo1++;
        }
        if (randNo2 >= current) {
            randNo2++;
        }
        if (randNo1 == size) {
            randNo1 = 0;
            if (current == 0) {
                randNo1++;
                if (randNo1 == randNo2) {
                    randNo1++;
                }
            }
        }
        if (randNo2 == size) {
            randNo2 = 0;
            if (current == 0) {
                randNo2++;
                if (randNo1 == randNo2) {
                    randNo2++;
                }
            }
        }
        return new Pair<Integer, Integer>(randNo1, randNo2);
    }

    private void checkAnswer() {
        answered = true;
        RadioButton rBtnSelected = findViewById(group.getCheckedRadioButtonId());
        int answerNo = group.indexOfChild(rBtnSelected)+1;
        if (answerNo == 1) {
            score ++;
            textViewScore.setText("Score: " + score + "/" + questionCountTotal);
        }
        showSolution();
    }

    private void showSolution() {
        rBtn1.setTextColor(Color.RED);
        rBtn2.setTextColor(Color.RED);
        rBtn3.setTextColor(Color.RED);

        textViewQuestion.setText("The answer is " + currentQuestion.getAnswer());
        switch (1) {
            case 1:
                rBtn1.setTextColor(Color.GREEN);
                break;
            case 2:
                rBtn2.setTextColor(Color.GREEN);
                break;
            case 3:
                rBtn3.setTextColor(Color.GREEN);
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

    public int getScore() {
        return score;
    }

}