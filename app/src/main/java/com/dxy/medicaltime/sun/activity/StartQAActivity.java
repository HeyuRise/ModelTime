package com.dxy.medicaltime.sun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.constants.Common;
import com.dxy.medicaltime.sun.bean.Question;
import com.dxy.medicaltime.sun.bean.QuestionItem;
import com.dxy.medicaltime.sun.fragment.QuestionFragment;
import com.dxy.medicaltime.sun.utils.DBQuestionManager;
import com.dxy.medicaltime.sun.utils.DBQuestionWrongManager;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

public class StartQAActivity extends AppCompatActivity {
    private String baseurl = "http://www.dxy.cn/webservices/articlevote/qaList?type=0&pge=";
    private String baseurl2 = "http://www.dxy.cn/webservices/articlevote/voteinfo?articleid=";
    private int artical;
    private int voteId;
    private int page = 2;
    private String url;
    private String url2;
    private int number = 0;
    private ArrayList<QuestionItem.MessageBean.ListBean> data;
    private ArrayList<Question> questions;
    private int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_qa);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("question");
        a = bundle.getInt("which");
        if (a == 0) {
            initurl();
            initData();
        } else if (a == 1) {
            questions = (ArrayList<Question>) DBQuestionManager.getDbManager(this).queryAll();
            initfragment();
        } else {
            questions = (ArrayList<Question>) DBQuestionWrongManager.getDbManager(this).queryAll();
            initfragment();
        }

    }

    public void goback(View view) {
        finish();
    }

    public void initurl() {
        url = baseurl + page + "&limit=20&userName=" + Common.username + "&username=" + Common.username + "&u=" + Common.username + "&mc=" + Common.mc + "&hardName=" + Common.hardName + "&ac=" + Common.ac + "&vc=" + Common.vc + "&vs=" + Common.vs;
    }

    public void initurl2() {
        url2 = baseurl2 + artical + "&voteid=" + voteId + "&username=" + Common.username + "&u=" + Common.username + "&mc=" + Common.mc + "&hardName=" + Common.hardName + "&ac=" + Common.ac + "&vc=" + Common.vc + "&vs=" + Common.vs;
    }

    public void initData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.e("=====", json);
                Gson gson = new Gson();
                QuestionItem item = gson.fromJson(json, QuestionItem.class);
                data = (ArrayList<QuestionItem.MessageBean.ListBean>) item.getMessage().getList();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initfragment();
                    }
                });
            }
        });
    }

    public void initfragment() {
        if (questions == null || questions.size() == 0)
            return;
        if (a == 0) {
            artical = data.get(number).getArticleId();
            voteId = data.get(number).getVoteId();
            initurl2();
        } else {
            url2 = questions.get(number).getUrl();
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.question_fragment, QuestionFragment.newInstance(url2));
        ft.commit();
    }

    public void lastOne(View view) {
        if (number > 0) {
            number--;
            initfragment();
        }
    }

    public void nextOne(View view) {
        if (a == 0) {
            if (number == data.size() - 1) {
                number = 0;
                page++;
                initurl();
                initfragment();
            } else {
                number++;
                initfragment();
            }
        } else {
            if (number != questions.size() - 1) {
                number++;
                initfragment();
            } else {
                Toast.makeText(this, "已经到头了", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
