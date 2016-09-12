package com.dxy.medicaltime.sun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.constants.Common;
import com.dxy.medicaltime.sun.adapter.SectionListAdapter;
import com.dxy.medicaltime.sun.bean.SectionHeyu;
import com.dxy.medicaltime.sun.bean.SectionSub;
import com.dxy.medicaltime.sun.utils.DBManager;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

public class SectionSubActivity extends AppCompatActivity {
    private ListView listView;
    private DBManager dbManager;
    private SectionListAdapter adapter;
    private ArrayList<SectionSub.MessageBean.ChildrenBean> data;
    private String url = "http://www.dxy.cn/webservices/app-departmentV2?username=" + Common.username + "&u=" + Common.username + "&mc=" + Common.mc + "&hardName=" + Common.hardName + "&ac=" + Common.ac + "&vc=" + Common.vc + "&vs=" + Common.vs;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_sub);
        init();
        initData();
    }

    public void init() {
        dbManager = DBManager.getDbManager(this);
        data = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list_section_sub);
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
                Gson gson = new Gson();
                SectionSub sectionSub = gson.fromJson(json, SectionSub.class);
                data = (ArrayList<SectionSub.MessageBean.ChildrenBean>) sectionSub.getMessage().get(0).getChildren();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new SectionListAdapter(data, SectionSubActivity.this);
                        listView.setAdapter(adapter);
                        toGreenDao();
                    }
                });
            }
        });
    }

    public void toGreenDao(){
        adapter.setInterace(new SectionListAdapter.toCollect() {
            @Override
            public void collect(int id, String name, boolean isCollect) {
                if (isCollect){
                    SectionHeyu sectionHeyu = new SectionHeyu(id, name);
                    dbManager.insert(sectionHeyu);
                }else{
                    SectionHeyu sectionHeyu = new SectionHeyu(id, name);
                    dbManager.delete(sectionHeyu);
                }
            }
        });
    }

    public void keepSet(View view){
        Toast.makeText(this, "已保存", Toast.LENGTH_SHORT).show();
        ArrayList<SectionHeyu> a = (ArrayList<SectionHeyu>) dbManager.queryAll();

        String json = "{[{'dls':" + 0 + "},{'dls':" + 1 + "}]}";
        OkHttpClient client1 = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://www.dxy.cn/webservices/app-department/user?username=i8201098847&u=i8201098847&mc=ffffffffe7f5a547ffffffffc7b382a1&hardName=SM-N9109W&ac9432e53c-74f7-44f9-be13-15f6cc3cd4ba&vc=4.5.1&vs4.4.2")
                .post(body)
                .build();
        client1.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //判断请求是否成功
                if (response.isSuccessful()) {
                    //打印服务端返回结果
                    Log.e("====", response.body().string());

                }
            }
        });
    }

    public void goback(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        sendBroadcast(new Intent("info.fragment.refresh"));
        super.onBackPressed();
    }

}
