package com.me.temperature;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "temperature")
public class temperature {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "tp")
    private String tp;
    @ColumnInfo(name = "time")
    private String time;

    public temperature(String tp, String time) {
        this.tp = tp;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
