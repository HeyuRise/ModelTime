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
import com.dxy.medicaltime.chen.bean.NewBook;
import com.dxy.medicaltime.chen.bean.NewBooks;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewBooksActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    int page=1;
    //新书推荐
    String url3="http://i.dxy.cn/bbs/bbsapi/mobile?s=special_book_list&typeId=9&page="+page+"&size=20&ac=9432e53c-74f7-44f9-be13-15f6cc3cd4ba&vc=4.5.1&vs=4.4.2&mc=0000000038ff13b4ffffffffe9f0e5e8&hardName=R6007";
    //知网推广
    String url4="http://i.dxy.cn/bbs/bbsapi/mobile?s=special_book_list&typeId=14&page=1&size=20&ac=9432e53c-74f7-44f9-be13-15f6cc3cd4ba&vc=4.5.1&vs=4.4.2&mc=0000000038ff13b4ffffffffe9f0e5e8&hardName=R6007";
    Toolbar toolbar;
    ActionBar actionBar;
    ListView listView;
    String subjectId;
    List<NewBook>list;
    AllBooksAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_books);
        Intent intent=getIntent();
        subjectId=intent.getStringExtra("subjectId");
        listView = (ListView) findViewById(R.id.NewBooksActivity_listView);
        toolbar = (Toolbar) findViewById(R.id.NewBooksActivity_toolBar);
        list=new ArrayList<>();
        if (subjectId.equals("9")) {
                toolbar.setTitle("新书推荐");
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url3)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }


                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String json = response.body().string();
                        Gson gson = new Gson();
                        NewBooks newBooks = gson.fromJson(json, NewBooks.class);
                        list.clear();
                        list.addAll(newBooks.getItems());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new AllBooksAdapter(list, NewBooksActivity.this);
                                listView.setAdapter(adapter);
                            }
                        });
                    }
                });
            }else if (subjectId.equals("14")){
                toolbar.setTitle("知书推广");
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url4)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }


                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String json = response.body().string();
                        Gson gson = new Gson();
                        NewBooks newBooks = gson.fromJson(json, NewBooks.class);
                        list.clear();
                        list.addAll(newBooks.getItems());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new AllBooksAdapter(list, NewBooksActivity.this);
                                listView.setAdapter(adapter);
                            }
                        });
                    }
                });
            }
        listView.setOnItemClickListener(NewBooksActivity.this);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(NewBooksActivity.this,BookDetailActivity.class);
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
