package com.koziol.andrzej;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PictureDao {
    @Query("SELECT * FROM Picture")
    List<Picture> loadPictures();

}
