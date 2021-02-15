package com.hvl.dat153.dogquiz;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dog_questions")
public class Dog {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "dogId")
    private int id;

    @ColumnInfo(name = "dogName")
    private String answer;

    @ColumnInfo(name = "imageUri")
    private String imageUri;

    public Dog() {

    }
    public Dog(String name, String imageString) {
        this.answer = name;
        this.imageUri = imageString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String name) {
        this.answer = name;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageString) {
        this.imageUri = imageString;
    }
}
