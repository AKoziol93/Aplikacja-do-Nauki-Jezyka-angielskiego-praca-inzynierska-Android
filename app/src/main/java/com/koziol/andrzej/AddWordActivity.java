package com.koziol.andrzej;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddWordActivity extends AppCompatActivity {

    private TextView englishWordView;
    private TextView polishWordView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_word);

        englishWordView = findViewById(R.id.englishWord);
        polishWordView = findViewById(R.id.polishWord);

        polishWordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled;
                handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    check();
                    handled = true;
                }
                return handled;
            }
        });

        Button button = findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }

    private void check() {
        String engWord = englishWordView.getText().toString();
        String plWord = polishWordView.getText().toString();
        if (engWord.isEmpty()) {
            Toast.makeText(this, "English word has no value", Toast.LENGTH_SHORT).show();
            return;
        } else if (plWord.isEmpty()) {
            Toast.makeText(this, "Polish word has no value", Toast.LENGTH_SHORT).show();
            return;
        }

        save(engWord, plWord);
    }

    @SuppressLint("StaticFieldLeak")
    private void save(String eng, String pl) {
        new AsyncTask<Dictionary, Void, Void>() {

            @Override
            protected Void doInBackground(Dictionary... dictionaries) {
                AppDatabase
                        .getInstance(AddWordActivity.this)
                        .dictionaryDao()
                        .saveWord(dictionaries[0]);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
            }
        }.execute(new Dictionary(eng, pl));
    }
}
