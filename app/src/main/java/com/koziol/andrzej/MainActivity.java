package com.koziol.andrzej;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button quizButton = findViewById(R.id.quizButton);
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, QuizActivity.class));
            }
        });

        Button fiszkaButton = findViewById(R.id.fiszkaButton);
        fiszkaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FiszkaActivity.class));
            }
        });
        Button newWordButton = findViewById(R.id.addWordButton);
        newWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddWordActivity.class));
            }
        });

        Button sentenceButton = findViewById(R.id.sentencebutton);
        sentenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SentenceActivity.class));
            }
        });

        findViewById(R.id.pictureButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PictureActivity.class));
            }
        });
    }
}
