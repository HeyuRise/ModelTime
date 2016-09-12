package com.dxy.medicaltime.sun.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.dxy.medicaltime.R;
import com.dxy.medicaltime.sun.bean.Question;
import com.dxy.medicaltime.sun.utils.DBQuestionManager;
import com.dxy.medicaltime.sun.utils.DBQuestionRightManager;

import java.util.ArrayList;

public class HistoryScoreActivity extends AppCompatActivity {
    private TextView questionNumber,questionRightRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_score);
        questionNumber= (TextView) findViewById(R.id.history_number);
        questionRightRate= (TextView) findViewById(R.id.history_rate);
        ArrayList<Question> questions= (ArrayList<Question>) DBQuestionManager.getDbManager(this).queryAll();
        questionNumber.setText(questions.size()+"");
        if(questions.size()!=0){
            ArrayList<Question> questions1= (ArrayList<Question>) DBQuestionRightManager.getDbManager(this).queryAll();
            int a=questions1.size()*100/questions.size();
            questionRightRate.setText(a+"%");
        }else {
            questionRightRate.setText("0%");
        }
    }

    public void goback(View view){
        finish();
    }

}
