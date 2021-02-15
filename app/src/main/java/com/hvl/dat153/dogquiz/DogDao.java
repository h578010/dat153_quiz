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

    @Query("DELETE FROM dog_questions WHERE dogId = :id")
    void deleteDog (int id);

    @Query("SELECT * FROM dog_questions")
    List<Dog> getAllDogs();

    @Query("SELECT COUNT(*) FROM dog_questions")
    int count();
}


