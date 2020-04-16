package com.koziol.andrzej;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Dictionary.class, Sentence.class, Picture.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance;

    public abstract DictionaryDao dictionaryDao();

    public abstract SentenceDao sentenceDao();

    public abstract PictureDao pictureDao();

    public static AppDatabase getInstance(final Context context) {
        if (sInstance != null) return sInstance;
        sInstance = Room
                .databaseBuilder(context, AppDatabase.class, "database.db")
                .createFromAsset("database/EngDb.db")
                .build();

        return sInstance;
    }
}