package com.dxy.medicaltime.chen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dxy.medicaltime.R;
import com.dxy.medicaltime.chen.adapter.AllBooksAdapter;
import com.dxy.medicaltime.chen.bean.EditAndPopBooks;
import com.dxy.medicaltime.chen.bean.NewBook;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecommendBooksActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    String mc="0000000038ff13b4ffffffffe9f0e5e8&hardName=R6007";
    //热门推荐
    String url1="http://d.dxy.cn/book/api/read?action=Recommend&appType=1&tpg=1&size=20&ac=9432e53c-74f7-44f9-be13-15f6cc3cd4ba&vc=4.5.1&vs=4.4.2&mc="+mc;
    //编辑推荐
    String url2="http://d.dxy.cn/book/api/read?action=EditorRecommend&appType=1&tpg=1&size=20&ac=9432e53c-74f7-44f9-be13-15f6cc3cd4ba&vc=4.5.1&vs=4.4.2&mc="+mc;
    Toolbar toolbar;
    ActionBar actionBar;
    ListView listView;
    String num;

    //点击推荐进入的书籍简介集合（ListView展示）

    List<NewBook> list ;
    AllBooksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_books);
        Intent intent = getIntent();
        num = intent.getStringExtra("type");
        listView = (ListView) findViewById(R.id.RecommendBooks_listView);
        toolbar = (Toolbar) findViewById(R.id.RecommendBooks_toolBar);
        list=new ArrayList<>();
        if (num.equals("a")) {
                toolbar.setTitle("编辑推荐");
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder()
                        .url(url2)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }


                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String json=response.body().string();
                        Gson gson=new Gson();
                        EditAndPopBooks editAndPopBooks=gson.fromJson(json,EditAndPopBooks.class);
                        list.clear();
                        list.addAll(editAndPopBooks.getBooks());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter=new AllBooksAdapter(list,RecommendBooksActivity.this);
                                listView.setAdapter(adapter);
                            }
                        });
                    }
                });


        }

        else {
            toolbar.setTitle("热门推荐");
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder()
                    .url(url1)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json=response.body().string();
                    Gson gson=new Gson();
                    EditAndPopBooks editAndPopBooks=gson.fromJson(json,EditAndPopBooks.class);
                    list.clear();
                    list.addAll(editAndPopBooks.getBooks());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter=new AllBooksAdapter(list,RecommendBooksActivity.this);
                            listView.setAdapter(adapter);
                        }
                    });
                }
            });

        }
        listView.setOnItemClickListener(this);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(RecommendBooksActivity.this,BookDetailActivity.class);
        intent.putExtra("id",list.get(position).getId());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
