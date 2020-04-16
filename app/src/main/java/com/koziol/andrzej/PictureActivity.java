package com.koziol.andrzej;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class PictureActivity extends AppCompatActivity {

    private Queue<Picture> pictures = new LinkedList<>();

    private Picture currentPicture;

    private TextView correctAnswerView;
    private TextView askedQuestionsView;

    private TextView questionView;
    private Button nextQuestionView;
    private RadioGroup radioChoice;
    private RadioButton choiceAView;
    private RadioButton choiceBView;
    private RadioButton choiceCView;

    private int correctAnswerCount;
    private int questionsAskedCount;

    private PictureHelper pictureHelper = new PictureHelper();

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        correctAnswerView = findViewById(R.id.correctAnswerCount);
        askedQuestionsView = findViewById(R.id.questionAskedCount);
        nextQuestionView = findViewById(R.id.nextQuestion);
        questionView = findViewById(R.id.question);
        radioChoice = findViewById(R.id.radioChoice);
        choiceAView = findViewById(R.id.choiceA);
        choiceBView = findViewById(R.id.choiceB);
        choiceCView = findViewById(R.id.choiceC);

        nextQuestionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        new AsyncTask<Void, Void, List<Picture>>() {

            @Override
            protected List<Picture> doInBackground(Void[] objects) {
                AppDatabase database = AppDatabase.getInstance(PictureActivity.this);
                PictureDao dao = database.pictureDao();
                List<Picture> list = dao.loadPictures();
                return list;
            }

            @Override
            protected void onPostExecute(List<Picture> sentences) {
                super.onPostExecute(sentences);
                onDataReady(sentences);
            }
        }.execute();
    }

    private void onDataReady(List<Picture> pictures) {
        Collections.shuffle(pictures);
        this.pictures.clear();
        this.pictures.addAll(pictures);
        nextQuestion();
    }

    private void nextQuestion() {
        currentPicture = pictures.poll();
        questionsAskedCount++;
        correctAnswerView.setText(correctAnswerCount + "/5");
        askedQuestionsView.setText(questionsAskedCount + "/5");
        if (currentPicture == null || questionsAskedCount == 5) {
            radioChoice.setVisibility(View.INVISIBLE);
            questionView.setVisibility(View.INVISIBLE);
            nextQuestionView.setText("Done");
            nextQuestionView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            return;
        }

        questionView.setText(currentPicture.name);
        choiceAView.setChecked(false);
        choiceBView.setChecked(false);
        choiceCView.setChecked(false);

        List<PictureHelper.Answer> randomChoices = pictureHelper.getRandomChoices(currentPicture.name);
        prepareChoiceView(choiceAView, randomChoices.get(0));
        prepareChoiceView(choiceBView, randomChoices.get(1));
        prepareChoiceView(choiceCView, randomChoices.get(2));
    }

    private void prepareChoiceView(RadioButton choiceView, PictureHelper.Answer answer) {
        choiceView.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getDrawable(answer.imageId), null);
        choiceView.setTag(answer);
    }

    private void checkAnswer() {
        int checkedRadioButtonId = radioChoice.getCheckedRadioButtonId();
        if (checkedRadioButtonId == -1) {
            Toast.makeText(this, "Please select picture first", Toast.LENGTH_SHORT).show();
            return;
        }
        PictureHelper.Answer answer = (PictureHelper.Answer) findViewById(checkedRadioButtonId).getTag();
        if (currentPicture.name.equalsIgnoreCase(answer.name)) {
            correctAnswerCount++;
        }
        correctAnswerView.setText(correctAnswerCount + "/5");
        askedQuestionsView.setText(questionsAskedCount + "/5");
        nextQuestion();
    }
}