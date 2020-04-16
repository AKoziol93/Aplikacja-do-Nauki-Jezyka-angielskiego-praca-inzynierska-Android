package com.koziol.andrzej;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QuizActivity extends AppCompatActivity {

    private Queue<Dictionary> questions = new LinkedList<>();

    private Dictionary currentQuestion;

    private Button nextButton;
    private Button skipButton;
    private TextView questionView;
    private TextView questionLabelView;
    private TextView correctAnswerView;
    private TextView askedQuestionsView;
    private EditText answerView;

    private int correctAnswerCount;
    private int questionsAskedCount;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz);

        nextButton = findViewById(R.id.nextQuestion);
        skipButton = findViewById(R.id.skipQuestion);
        questionView = findViewById(R.id.question);
        questionLabelView = findViewById(R.id.questionLabel);
        answerView = findViewById(R.id.answerField);
        correctAnswerView = findViewById(R.id.correctAnswerCount);
        askedQuestionsView = findViewById(R.id.questionAskedCount);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });

        answerView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    checkAnswer();
                    handled = true;
                }
                return handled;
            }
        });


        new AsyncTask<Void, Void, List<Dictionary>>() {


            @Override
            protected List<Dictionary> doInBackground(Void[] objects) {
                return AppDatabase.getInstance(QuizActivity.this).dictionaryDao().loadWords();
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
        nextQuestion();
    }

    private void nextQuestion() {
        currentQuestion = questions.poll();
        questionsAskedCount++;
        correctAnswerView.setText(correctAnswerCount + "/10");
        askedQuestionsView.setText(questionsAskedCount + "/10");
        if (currentQuestion == null || questionsAskedCount == 10) {
            skipButton.setVisibility(View.INVISIBLE);
            answerView.setVisibility(View.INVISIBLE);
            questionView.setVisibility(View.INVISIBLE);
            questionLabelView.setVisibility(View.INVISIBLE);
            nextButton.setText("Done");
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            return;
        }
        questionView.setText(currentQuestion.polish);
    }

    private void checkAnswer() {
        String answer = answerView.getText().toString();
        if (answer.equalsIgnoreCase(currentQuestion.english)) {
            correctAnswerCount++;
            correctAnswerView.setText(correctAnswerCount + "/10");
            askedQuestionsView.setText(questionsAskedCount + "/10");
            answerView.setText("");
            nextQuestion();
        } else {
            Toast.makeText(QuizActivity.this, "Wrong answer, try again", Toast.LENGTH_SHORT).show();
            answerView.setText("");
        }
    }
}
