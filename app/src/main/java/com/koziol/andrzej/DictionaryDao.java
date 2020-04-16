package com.koziol.andrzej;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DictionaryDao {

    @Query("SELECT * FROM Dictionary")
    List<Dictionary> loadWords();

    @Insert
    void saveWord(Dictionary dictionary);
}
