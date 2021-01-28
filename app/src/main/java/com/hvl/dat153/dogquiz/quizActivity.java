package com.hvl.dat153.dogquiz;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class quizActivity extends AppCompatActivity {
    private TextView question;
    private TextView score;
    private TextView count;
    private RadioGroup group;
    private RadioButton rBtn1;
    private RadioButton rBtn2;
    private RadioButton rBtn3;
    private Button answer;
    private List<Questions> questionList;


    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        question = findViewById(R.id.question_text);
        score = findViewById(R.id.score);
        count = findViewById(R.id.count);
        rBtn1 = findViewById(R.id.radio1);
        rBtn2 = findViewById(R.id.radio2);
        rBtn3 = findViewById(R.id.radio3);
        answer = findViewById(R.id.button_answer);

        DbHelper dbHelper = new DbHelper(this);
        questionList = dbHelper.getAllQuestions();

    }

    public void sendAnswer(View view) {

    }
}