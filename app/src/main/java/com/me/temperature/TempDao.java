package com.me.temperature;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface TempDao {
    @Insert
    void insertTp (temperature ... temperatures);
    @Update
    void updateTp (temperature ... temperatures);
    @Delete
    void deleteTp (temperature ... temperatures);
    @Query("DELETE From TEMPERATURE")
    void deleteAllTp ();
    @Query("SELECT * From TEMPERATURE ORDER BY ID DESC")
    LiveData<List<temperature>> getAllTp ();
}
