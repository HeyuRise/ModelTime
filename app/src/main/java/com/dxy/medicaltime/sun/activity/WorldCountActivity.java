package com.dxy.medicaltime.sun.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.constants.Common;
import com.dxy.medicaltime.sun.bean.Most;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

public class WorldCountActivity extends AppCompatActivity {
    private String mosturl="http://www.dxy.cn/webservices/articlevote/rank?limit=3&pge=1&username="+Common.username+"&u="+Common.username+"&orderBy=completeNum&mc="+ Common.mc+"&hardName="+Common.hardName+"&ac="+Common.ac+"&vc="+Common.vc+"&vs="+Common.vs;
    private String rateurl="http://www.dxy.cn/webservices/articlevote/rank?limit=3&pge=1&username="+Common.username+"&u="+Common.username+"&orderBy=rightNum&mc="+ Common.mc+"&hardName="+Common.hardName+"&ac="+Common.ac+"&vc="+Common.vc+"&vs="+Common.vs;
    private ArrayList<Most.MessageBean.ListBean> data;
    private TextView most1,most2,most3,most1number,most2number,most3number,rate1,rate2,rate3,rate1number,rate2number,rate3number;
    private SimpleDraweeView mostimg1,mostimg2,mostimg3,rateimg1,rateimg2,rateimg3;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
                initUI();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_world_count);
        init();
        initData();
    }
    public void init(){
        most1= (TextView) findViewById(R.id.most1_name);
        most2= (TextView) findViewById(R.id.most2_name);
        most3= (TextView) findViewById(R.id.most3_name);
        most1number= (TextView) findViewById(R.id.most1_numbers);
        most2number= (TextView) findViewById(R.id.most2_numbers);
        most3number= (TextView) findViewById(R.id.most3_numbers);

        rate1= (TextView) findViewById(R.id.rate1_name);
        rate2= (TextView) findViewById(R.id.rate2_name);
        rate3= (TextView) findViewById(R.id.rate3_name);
        rate1number= (TextView) findViewById(R.id.rate1_numbers);
        rate2number= (TextView) findViewById(R.id.rate2_numbers);
        rate3number= (TextView) findViewById(R.id.rate3_numbers);

        mostimg1= (SimpleDraweeView) findViewById(R.id.most1);
        mostimg2= (SimpleDraweeView) findViewById(R.id.most2);
        mostimg3= (SimpleDraweeView) findViewById(R.id.most3);
        rateimg1= (SimpleDraweeView) findViewById(R.id.rate1);
        rateimg2= (SimpleDraweeView) findViewById(R.id.rate2);
        rateimg3= (SimpleDraweeView) findViewById(R.id.rate3);


    }
    public void initData(){
        data=new ArrayList<>();
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(mosturl).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json=response.body().string();
                Gson gson=new Gson();
                Most most=gson.fromJson(json,Most.class);
                data.addAll(most.getMessage().getList());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client1=new OkHttpClient();
                        Request request1=new Request.Builder().url(rateurl).build();
                        client1.newCall(request1).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String json=response.body().string();
                                Gson gson=new Gson();
                                Most most=gson.fromJson(json,Most.class);
                                data.addAll(most.getMessage().getList());
                                handler.sendEmptyMessage(Message.obtain().what);
                            }
                        });
                    }
                });
            }
        });

    }
    public void initUI(){
        mostimg1.setImageURI(data.get(0).getUserPhoto());
        mostimg2.setImageURI(data.get(1).getUserPhoto());
        mostimg3.setImageURI(data.get(2).getUserPhoto());
        rateimg1.setImageURI(data.get(3).getUserPhoto());
        rateimg2.setImageURI(data.get(4).getUserPhoto());
        rateimg3.setImageURI(data.get(5).getUserPhoto());

        most1.setText(data.get(0).getUsername());
        most2.setText(data.get(1).getUsername());
        most3.setText(data.get(2).getUsername());
        rate1.setText(data.get(3).getUsername());
        rate2.setText(data.get(4).getUsername());
        rate3.setText(data.get(5).getUsername());

        most1number.setText(data.get(0).getCompleteNum()+"题");
        most2number.setText(data.get(1).getCompleteNum()+"题");
        most3number.setText(data.get(3).getCompleteNum()+"题");

        rate1number.setText("100%");
        rate2number.setText("100%");
        rate3number.setText("100%");
    }
    public void goback(View view){
        finish();
    }
}
