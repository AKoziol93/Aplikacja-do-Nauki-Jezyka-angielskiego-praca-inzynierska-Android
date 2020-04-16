package com.koziol.andrzej;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SentenceActivity extends AppCompatActivity {

    private Queue<Sentence> questions = new LinkedList<>();

    private Sentence currentQuestion;

    private Button buttonAView;
    private Button buttonBView;
    private Button buttonCView;
    private TextView choiceAView;
    private TextView choiceBView;
    private TextView choiceCView;
    private TextView correctAnswerView;
    private TextView askedQuestionsView;

    private int correctAnswerCount;
    private int questionsAskedCount;


    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence);

        buttonAView = findViewById(R.id.sentenceAButton);
        buttonBView = findViewById(R.id.sentenceBButton);
        buttonCView = findViewById(R.id.sentenceCButton);
        choiceAView = findViewById(R.id.sentenceChoiceA);
        choiceBView = findViewById(R.id.sentenceChoiceBView);
        choiceCView = findViewById(R.id.sentenceChoiceC);
        correctAnswerView = findViewById(R.id.correctAnswerCount);
        askedQuestionsView = findViewById(R.id.questionAskedCount);

        buttonAView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(1);
            }
        });
        buttonBView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(2);
            }
        });
        buttonCView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(3);
            }
        });


        new AsyncTask<Void, Void, List<Sentence>>() {

            @Override
            protected List<Sentence> doInBackground(Void[] objects) {
                AppDatabase database = AppDatabase.getInstance(SentenceActivity.this);
                SentenceDao sentenceDao = database.sentenceDao();
                List<Sentence> list = sentenceDao.loadSentence();
                return list;
            }

            @Override
            protected void onPostExecute(List<Sentence> sentences) {
                super.onPostExecute(sentences);
                onDataReady(sentences);
            }
        }.execute();
    }

    private void onDataReady(List<Sentence> sentences) {
        Collections.shuffle(sentences);
        questions.addAll(sentences);
        nextQuestion();
    }

    private void nextQuestion() {
        currentQuestion = questions.poll();
        questionsAskedCount++;
        correctAnswerView.setText(correctAnswerCount + "/5");
        askedQuestionsView.setText(questionsAskedCount + "/5");
        if (currentQuestion == null || questionsAskedCount == 5) {
            buttonAView.setVisibility(View.INVISIBLE);
            buttonCView.setVisibility(View.INVISIBLE);
            choiceAView.setVisibility(View.INVISIBLE);
            choiceBView.setVisibility(View.INVISIBLE);
            choiceCView.setVisibility(View.INVISIBLE);
            buttonBView.setText("Done");
            buttonBView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            return;
        }
        choiceAView.setText(currentQuestion.sentence1);
        choiceBView.setText(currentQuestion.sentence2);
        choiceCView.setText(currentQuestion.sentence3);
    }

    private void checkAnswer(int a) {
        int ans = currentQuestion.answer;

        if (a == ans) {
            correctAnswerCount++;
            correctAnswerView.setText(correctAnswerCount + "/5");
            askedQuestionsView.setText(questionsAskedCount + "/5");
        } else {
            String ansString = null;
            if(ans==1){
                ansString="A";
            }
            if(ans==2){
                ansString="B";
            }
            if(ans==3){
                ansString="C";
            }

            Toast.makeText(SentenceActivity.this, "Wrong answer, correct answer is "+ansString, Toast.LENGTH_SHORT).show();
        }
        nextQuestion();
    }
}
