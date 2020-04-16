package com.koziol.andrzej;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FiszkaActivity extends AppCompatActivity {

    private Queue<Dictionary> questions = new LinkedList<>();

    private Button nextButton;
    private TextView englishWordView;
    private TextView polishWordView;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fiszka);

        nextButton = findViewById(R.id.next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextWord();
            }
        });

        englishWordView = findViewById(R.id.englishWord);
        polishWordView = findViewById(R.id.polishWord);

        new AsyncTask<Void, Void, List<Dictionary>>() {

            @Override
            protected List<Dictionary> doInBackground(Void[] objects) {
                AppDatabase database = AppDatabase.getInstance(FiszkaActivity.this);
                DictionaryDao dictionaryDao = database.dictionaryDao();
                List<Dictionary> list = dictionaryDao.loadWords();
                return list;
            }

            @Override
            protected void onPostExecute(List<Dictionary> dictionaries) {
                super.onPostExecute(dictionaries);
                onDataReady(dictionaries);
            }
        }.execute();
    }

    private void onDataReady(List<Dictionary> dictionaryList) {
        Collections.shuffle(dictionaryList);
        questions.addAll(dictionaryList);
        nextWord();
    }

    private void nextWord() {
        Dictionary currentQuestion = questions.poll();

        if (currentQuestion == null) {
            Toast.makeText(this, "No more words, well done!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        englishWordView.setText(currentQuestion.english);
        polishWordView.setText(currentQuestion.polish);
    }
}
