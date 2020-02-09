package com.me.temperature;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {temperature.class },version = 1,exportSchema = false)
public abstract class TempDatabase extends RoomDatabase {
    private static TempDatabase INSTANCE;
    public abstract TempDao getTempDao();
    static synchronized TempDatabase getTempDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),TempDatabase.class,"tp_database")
                    .build();
        }
        return INSTANCE;
    }
}
