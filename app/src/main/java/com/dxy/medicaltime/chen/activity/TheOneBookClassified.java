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

public class TheOneBookClassified extends AppCompatActivity implements AdapterView.OnItemClickListener{
    String name;
    String subjectId;
    Toolbar toolbar;
    ListView listView;
    ActionBar actionBar;
    List<NewBook> list ;
    AllBooksAdapter adapter;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_one_book_classified);

        list=new ArrayList<>();

        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        subjectId=intent.getStringExtra("subjectId");
        url="http://d.dxy.cn/book/api/read?action=GetBookListBySubjectId&appType=1&subjectId="+subjectId+"&tpg=1&size=20&ac=9432e53c-74f7-44f9-be13-15f6cc3cd4ba&vc=4.5.1&vs=4.4.2&mc=0000000038ff13b4ffffffffe9f0e5e8&hardName=R6007";
        toolbar= (Toolbar) findViewById(R.id.theOneClassified_toolBar);
        listView= (ListView) findViewById(R.id.theOneClassified_listView);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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
                EditAndPopBooks editAndPopBooks=gson.fromJson(json,EditAndPopBooks.class);
                list.clear();
                list.addAll(editAndPopBooks.getBooks());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter=new AllBooksAdapter(list,TheOneBookClassified.this);
                        listView.setAdapter(adapter);
                    }
                });
            }
        });

        //绑定监听器
        listView.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(TheOneBookClassified.this,BookDetailActivity.class);
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
