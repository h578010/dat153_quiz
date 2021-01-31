package com.hvl.dat153.dogquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

public class AddActivity extends AppCompatActivity {
    public static final int PICK_IMAGE = 1;
    private Button addBtn;
    private ImageView imageView;
    private Button selectBtn;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addBtn = findViewById(R.id.add_btn);
        imageView = findViewById(R.id.image);
        selectBtn = findViewById(R.id.select_btn);
        editText = findViewById(R.id.editText);

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK && data != null) {
                Uri imageUri = data.getData();
                imageView.setImageURI(imageUri);
                String imageString = imageUri.getPath();
                System.out.println(imageString);
            }
        }
    }

    private void makeQuestion(File image) {


    }
}