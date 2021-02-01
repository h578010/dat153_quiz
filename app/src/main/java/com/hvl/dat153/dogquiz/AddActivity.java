package com.hvl.dat153.dogquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.SortedMap;

public class AddActivity extends AppCompatActivity {
    public static final int PICK_IMAGE = 1;
    private Button addBtn;
    private ImageView imageView;
    private Button selectBtn;
    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    private String answer;
    private String option2;
    private String option3;
    private Uri imageUri;
    private boolean selected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addBtn = findViewById(R.id.add_btn);
        imageView = findViewById(R.id.image);
        selectBtn = findViewById(R.id.select_btn);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected && !editText.getText().toString().isEmpty()) {
                    makeQuestion();
                    finish();
                } else {
                    Toast.makeText(AddActivity.this, "Please select an image and fill in a name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK && data != null) {
                imageUri = data.getData();

                ContentResolver cr = getContentResolver();
                final int takeFlags = data.getFlags()
                        & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                cr.takePersistableUriPermission(imageUri,takeFlags);

                imageView.setImageURI(imageUri);
                selected = true;
                answer = editText.getText().toString();
                option2 = editText2.getText().toString();
                option3 = editText3.getText().toString();
            }
        }
    }

    private void makeQuestion() {
        Question q = new Question(imageUri.toString(), answer, option2, option3, 1);
        DbHelper db = new DbHelper(this);
        db.addQuestion(q, null);
    }
}