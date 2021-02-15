package com.hvl.dat153.dogquiz;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {Dog.class}, version = 6, exportSchema = false)
public abstract class DogRoomDB extends RoomDatabase {
    public abstract DogDao dogDao();
    private static DogRoomDB INSTANCE;
    private static RoomDatabase.Callback rdc;


    public static synchronized DogRoomDB getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),
                            DogRoomDB.class,
                            "dog_database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
            if (INSTANCE.dogDao().count() == 0) {
                INSTANCE.populateInitialData();
            }
        }
        return INSTANCE;
    }

    private void populateInitialData() {
        Dog dog1 = new Dog("Golden Retriever", "android.resource://com.hvl.dat153.dogquiz/" + R.drawable.goldenretriever);
        dogDao().insertDog(dog1);
        Dog dog2 = new Dog("Border Collie", "android.resource://com.hvl.dat153.dogquiz/" + R.drawable.bordercollie);
        dogDao().insertDog(dog2);
        Dog dog3 = new Dog("Dalmatian", "android.resource://com.hvl.dat153.dogquiz/" + R.drawable.dalmatian);
        dogDao().insertDog(dog3);
        Dog dog4 = new Dog("Bichon Frisé", "android.resource://com.hvl.dat153.dogquiz/" + R.drawable.bichon);
        dogDao().insertDog(dog4);
        Dog dog5 = new Dog("Rottweiler", "android.resource://com.hvl.dat153.dogquiz/" + R.drawable.rottweiler);
        dogDao().insertDog(dog5);
        Dog dog6 = new Dog("Bearded Collie", "android.resource://com.hvl.dat153.dogquiz/" + R.drawable.beardedcollie);
        dogDao().insertDog(dog6);
        Dog dog7 = new Dog("German Shepherd", "android.resource://com.hvl.dat153.dogquiz/" + R.drawable.germanshepherd);
        dogDao().insertDog(dog7);
        Dog dog8 = new Dog("Saint Bernhard", "android.resource://com.hvl.dat153.dogquiz/" + R.drawable.bernhard);
        dogDao().insertDog(dog6);
    }

}
