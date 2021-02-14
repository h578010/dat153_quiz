package com.hvl.dat153.dogquiz;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Dog.class}, version = 1)
public abstract class DogRoomDB extends RoomDatabase {
    public abstract DogDao dogDao();
    private static DogRoomDB INSTANCE;

    static DogRoomDB getDatabase (final Context context) {
        if (INSTANCE == null) {
            synchronized (DogRoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    DogRoomDB.class,
                                    "dog_database").build();
                }
            }
        }
        return INSTANCE;
    }

}
