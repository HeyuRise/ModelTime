package com.dxy.medicaltime.sun.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dxy.medicaltime.R;
import com.dxy.medicaltime.sun.bean.Answer;
import com.dxy.medicaltime.sun.bean.Question;
import com.dxy.medicaltime.sun.utils.DBQuestionManager;
import com.dxy.medicaltime.sun.utils.DBQuestionRightManager;
import com.dxy.medicaltime.sun.utils.DBQuestionWrongManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class QuestionFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{
    private LinearLayout showIsAnswered;
    private RadioGroup radioGroup;
    private String url;
    private TextView title,notification,otherTitle,shareCount,time;
    private RadioButton a,b,c,d;
    private SimpleDraweeView imageView;
    private Answer answer;
    private String ans;

    public static QuestionFragment newInstance(String url){
        QuestionFragment q=new QuestionFragment();
        Bundle bundle=new Bundle();
        bundle.putString("url",url);
        q.setArguments(bundle);
        return q;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Fresco.initialize(getActivity());
        View view=inflater.inflate(R.layout.fragment_question, container, false);
        Bundle bundle=getArguments();
        url=bundle.getString("url");
        init(view);
        initData();
        return view;
    }
    public void init(View view){
        showIsAnswered= (LinearLayout) view.findViewById(R.id.show_if_isAnswered);
        showIsAnswered.setVisibility(View.GONE);
        radioGroup= (RadioGroup) view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        title= (TextView) view.findViewById(R.id.question_title);
        notification= (TextView) view.findViewById(R.id.question_notification);
        otherTitle= (TextView) view.findViewById(R.id.question_other_title);
        shareCount= (TextView) view.findViewById(R.id.question_other_share_count);
        time= (TextView) view.findViewById(R.id.question_other_time);
        a= (RadioButton) view.findViewById(R.id.question_a);
        b= (RadioButton) view.findViewById(R.id.question_b);
        c= (RadioButton) view.findViewById(R.id.question_c);
        d= (RadioButton) view.findViewById(R.id.question_d);
        a.setTag("A");b.setTag("B");c.setTag("C");d.setTag("D");
        imageView= (SimpleDraweeView) view.findViewById(R.id.question_other_img);
    }
    public void initData(){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json=response.body().string();
                Gson gson=new Gson();
                answer=gson.fromJson(json,Answer.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initUI();
                    }
                });
            }
        });
    }
    public void initUI(){
        title.setText(answer.getMessage().getSubject());
        otherTitle.setText(answer.getMessage().getExtendRead().getTitle());
        shareCount.setText(answer.getMessage().getExtendRead().getNumOfShared()+"分享");
        time.setText(answer.getMessage().getExtendRead().getArticleDate());
        a.setText(answer.getMessage().getVoteItems().get(0).getIndex()+"."+answer.getMessage().getVoteItems().get(0).getOption());
        b.setText(answer.getMessage().getVoteItems().get(1).getIndex()+"."+answer.getMessage().getVoteItems().get(1).getOption());
        c.setText(answer.getMessage().getVoteItems().get(2).getIndex()+"."+answer.getMessage().getVoteItems().get(2).getOption());
        d.setText(answer.getMessage().getVoteItems().get(3).getIndex()+"."+answer.getMessage().getVoteItems().get(3).getOption());
        imageView.setImageURI(answer.getMessage().getExtendRead().getImgpath());
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        showIsAnswered.setVisibility(View.VISIBLE);
        RadioButton view= (RadioButton) group.findViewById(checkedId);
        int a=new Random().nextInt(12)%4;

        if(a==0){
            ans="A";
        }else if (a==1){
            ans="B";
        }else if (a==2){
            ans="C";
        }else if(a==3){
            ans="D";
        }
        String answered= (String) view.getTag();
        ArrayList<Question> questions;
        boolean isManager=false;
        questions= (ArrayList<Question>) DBQuestionManager.getDbManager(getActivity()).queryAll();
        for(int i=0;i<questions.size();i++){
            if(questions.get(i).getUrl().equals(url)){
                isManager=true;
            }
        }
        if (!isManager){
            DBQuestionManager.getDbManager(getActivity()).insert(new Question(url,ans,answered));
        }

        if(ans.equals(answered)){
            notification.setText("恭喜你答对了");
            if(!isManager){
                DBQuestionRightManager.getDbManager(getActivity()).insert(new Question(url,ans,answered));
            }
        }else {
            notification.setText("很遗憾，你没有答对，正确答案为"+ans+"，再接再厉吧 ");
            if(!isManager){
                DBQuestionWrongManager.getDbManager(getActivity()).insert(new Question(url,ans,answered));
            }
        }
    }
}
