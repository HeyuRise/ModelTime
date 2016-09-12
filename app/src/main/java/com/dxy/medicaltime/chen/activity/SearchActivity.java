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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dxy.medicaltime.R;
import com.dxy.medicaltime.chen.adapter.AllBooksAdapter;
import com.dxy.medicaltime.chen.bean.NewBook;
import com.dxy.medicaltime.chen.bean.NewBooks;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    Toolbar toolbar;
    ActionBar actionBar;
    EditText editText;
    ListView listView;
    TextView textView;
    Button button;
    ImageView imageView;
    List<NewBook> list;
    AllBooksAdapter adapter;
    String url,str,baseUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        imageView= (ImageView) findViewById(R.id.Cleared);
        button= (Button) findViewById(R.id.sure);
        textView= (TextView) findViewById(R.id.SearchActivity_textView);
        toolbar= (Toolbar) findViewById(R.id.SearchActivity_toolBar);
        setSupportActionBar(toolbar);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        editText= (EditText) findViewById(R.id.Search_editext);
        listView= (ListView) findViewById(R.id.SearchBook_listView);
        //初始化集合
        list=new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用户输入的字段
                str=editText.getText().toString();

                try {
                    baseUrl= URLDecoder.decode(str,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
//                Log.e("aaa",baseUrl);
                url="http://d.dxy.cn/bbsapi/mobile?s=book_list&appType=1&page=1&size=10&bookName="+baseUrl+"&username=i8201098847&u=i8201098847&mc=ffffffffb8a4d9b26bc19af4795acaf8&token=TGT-72158-zdd7DOaiiNgINV2L342aAcjI1InpDRWwGtK-50&hardName=A0001&ac=9432e53c-74f7-44f9-be13-15f6cc3cd4ba&vc=4.5.1&vs=4.4.2";
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
                        String json = response.body().string();
                        Gson gson = new Gson();
                        NewBooks newBooks = gson.fromJson(json, NewBooks.class);
                        if (newBooks.getSuccess().equals("true")) {
                            list.clear();
                            list.addAll(newBooks.getItems());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText("您搜索字段的全部书籍");
                                    adapter = new AllBooksAdapter(list, SearchActivity.this);
                                    listView.setAdapter(adapter);
                                }
                            });
                        }

                       else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText("没有相对应搜索结果，换个词试试?");
                                }
                            });
                        }
                    }

                });
            }
        });

        listView.setOnItemClickListener(this);
    }
    //图片onClick
    public void Cleared(View view){
        str=editText.getText().toString();
        if (list.size()==0){
            textView.setText("输入您要查询的书籍");
        }else {
            imageView.setClickable(true);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editText.getText().clear();
                    list.clear();
                    adapter.notifyDataSetChanged();
                    textView.setText("");
                }
            });

        }
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(SearchActivity.this,BookDetailActivity.class);
        intent.putExtra("id",list.get(position).getId());
        startActivity(intent);
    }
}
