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
    private String name;

    @ColumnInfo(name = "imageString")
    private String imageString;

    public Dog(int id, String name, String imageString) {
        this.id = id;
        this.name = name;
        this.imageString = imageString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }
}
