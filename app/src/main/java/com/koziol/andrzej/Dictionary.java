package com.koziol.andrzej;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Dictionary {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "ENG")
    public final String english;
    @ColumnInfo(name = "PL")
    public final String polish;

    public Dictionary(String english, String polish) {
        this.english = english.toLowerCase().trim();
        this.polish = polish.toLowerCase().trim();
    }
}
