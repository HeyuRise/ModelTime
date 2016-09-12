package com.dxy.medicaltime.sun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.dxy.medicaltime.R;
import com.dxy.medicaltime.sun.bean.Question;
import com.dxy.medicaltime.sun.utils.DBQuestionManager;
import com.dxy.medicaltime.sun.utils.DBQuestionRightManager;

import java.util.ArrayList;

public class QuestionAnswerActivity extends AppCompatActivity {
    private TextView questionNumber,questionRightRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer);
        questionNumber= (TextView) findViewById(R.id.question_number);
        questionRightRate= (TextView) findViewById(R.id.question_rightRate);
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

    public void startAnswer(View view){
        Intent intent=new Intent(this,StartQAActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("which",0);
        intent.putExtra("question",bundle);
        startActivity(intent);
    }
    public void worldCount(View view){
        Intent intent=new Intent(this,WorldCountActivity.class);
        startActivity(intent);
    }
    public void isAnswered(View view){
        Intent intent=new Intent(this,StartQAActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("which",1);
        intent.putExtra("question",bundle);
        startActivity(intent);
    }
    public void doWrong(View view){
        Intent intent=new Intent(this,StartQAActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("which",2);
        intent.putExtra("question",bundle);
        startActivity(intent);
    }
    public void historyScore(View view){
        Intent intent=new Intent(this,HistoryScoreActivity.class);
        startActivity(intent);
    }
    public void goback(View view){
        finish();
    }
}
