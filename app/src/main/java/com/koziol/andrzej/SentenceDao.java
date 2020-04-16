package com.koziol.andrzej;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SentenceDao {
    @Query("SELECT * FROM Sentence")
    List<Sentence> loadSentence();

}
