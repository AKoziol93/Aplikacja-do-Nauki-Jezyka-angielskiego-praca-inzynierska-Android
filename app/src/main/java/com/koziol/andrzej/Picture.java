package com.koziol.andrzej;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Picture {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "Name")
    public final String name;

    public Picture(String name) {
        this.name = name;
    }
}
