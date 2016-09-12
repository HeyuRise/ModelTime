package com.dxy.medicaltime.sun.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.dxy.medicaltime.R;
import com.dxy.medicaltime.sun.adapter.FeedBackAdapter;
import com.dxy.medicaltime.sun.bean.Login;
import com.dxy.medicaltime.sun.utils.DBUserManager;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

public class FeedBackActivity extends AppCompatActivity {
    private ListView listView;
    private EditText editText;
    private ArrayList<String> data;
    private FeedBackAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_feed_back);
        data=new ArrayList<>();
        listView= (ListView) findViewById(R.id.feed_back_list);
        editText= (EditText) findViewById(R.id.feed_back_edit);
        init();
    }

    public void init(){
        adapter=new FeedBackAdapter(this,data);
        listView.setAdapter(adapter);
    }

    public void send(View view){
        ArrayList<Login> list= (ArrayList<Login>) DBUserManager.getDbManager(this).queryAll();
        if (list.size()==0){
            dialog();
        }else {
            String s=editText.getText().toString();
            if(s!=null){
                data.add(s);
                adapter.notifyDataSetChanged();
            }
        }
    }
    public void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setIcon(R.mipmap.icon);
        builder.setMessage("您还没有登陆，是否登陆");
        builder.setCancelable(true);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(FeedBackActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        // 设置消极按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void goback(View view){
        finish();
    }

}
