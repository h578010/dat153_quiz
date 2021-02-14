package com.hvl.dat153.dogquiz;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface DogDao {

    @Insert
    void insertDog (Dog dog);

    @Query("SELECT * FROM dog_questions WHERE dogName = :name")
    List<Dog> findDog (String name);

    @Query("DELETE FROM dog_questions WHERE dogName = :name")
    void deleteDog (String name);

    @Query("SELECT * FROM dog_questions")
    LiveData<List<Dog>> getAllDogs();
}


