package com.koziol.andrzej;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sentence {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "Sen1")
    public final String sentence1;
    @ColumnInfo(name = "Sen2")
    public final String sentence2;
    @ColumnInfo(name = "Sen3")
    public final String sentence3;
    @ColumnInfo(name = "ANS")
    public final int answer;

    public Sentence(String sentence1, String sentence2, String sentence3, int answer) {
        this.sentence1 = sentence1.trim();
        this.sentence2 = sentence2.trim();
        this.sentence3 = sentence3.trim();
        this.answer = answer;
    }
}
