package com.dxy.medicaltime.sun.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.fang.activity.DetailsActivity;
import com.dxy.medicaltime.sun.adapter.CollectionAdapter;
import com.dxy.medicaltime.sun.bean.CollectionData;
import com.dxy.medicaltime.sun.utils.DBCollectManager;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

public class CollectActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{
    private ListView listView;
    private ArrayList<CollectionData> data;
    private CollectionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_collect);
        init();
    }
    public void init(){
        data= (ArrayList<CollectionData>) DBCollectManager.getDbManager(this).queryAll();
        adapter=new CollectionAdapter(data,this);
        listView= (ListView) findViewById(R.id.collect_listView);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
    }

    public void goback(View view){
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this, DetailsActivity.class);
        intent.putExtra("id",(int)data.get(position).getId());
        intent.putExtra("title",data.get(position).getTitle());
        intent.putExtra("url",data.get(position).getUrl());
        intent.putExtra("img",data.get(position).getImg());
        intent.putExtra("time",data.get(position).getTime());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setIcon(R.mipmap.icon);
        builder.setMessage("是否删除此收藏");
        builder.setCancelable(true);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                DBCollectManager.getDbManager(CollectActivity.this).delete(data.get(position));
                adapter.notifyDataSetChanged();
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
        return false;
    }
}
