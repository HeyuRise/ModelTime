package com.dxy.medicaltime.fang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.constants.Common;
import com.dxy.medicaltime.fang.bean.SpecialDetailData;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/27.
 * 专题详情activity
 */
public class FirstSpecialDetailActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ArrayList<SpecialDetailData.MessageBean.ListBean> listBeen = new ArrayList<>();
    private MyAdapter adapter;
    private String cardId;
    private ListView listView;
    private int page = 1;
    private String img;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.special_detail_activity_view);

        Intent intent = getIntent();
        cardId = intent.getStringExtra("id");
        title = intent.getStringExtra("title");
        img=intent.getStringExtra("img");

        TextView titleTv = (TextView) findViewById(R.id.special_details_title_tv);
        titleTv.setText(title);

        listView=new ListView(this);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        listView.setLayoutParams(layoutParams);
        ((LinearLayout)findViewById(R.id.special_detail_activity_view)).addView(listView);
        listView.setOnItemClickListener(this);

        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        downLoad();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SpecialDetailData.MessageBean.ListBean data=listBeen.get(position);
        Intent intent=new Intent(this,SecondSpecialDetailActivity.class);
        intent.putExtra("id",data.getSpecialTagId()+"");
        intent.putExtra("cardId",cardId);
        intent.putExtra("cardTitle",title);
        intent.putExtra("img",img);
        intent.putExtra("title",data.getSpecialTagTitle());
        startActivity(intent);
    }


    private String getUrl() {
        String subUrl = "http://www.dxy.cn/webservices/special/category/articleGroup?limit=2000";
        String url = subUrl + "&username=" + Common.username + "&mc=" + Common.mc + "&hardName=" + Common.hardName + "&ac=" + Common.ac + "&vc=" + Common.vc + "&vs=" + Common.vs + "&cateId=" + cardId + "&pge=" + page++;
        return url;
    }

    /**
     * 返回按钮的监听方法
     */
    public void onBtnClick(View view) {
        finish();
    }

    private void downLoad() {
        Request request = new Request.Builder()
                .url(getUrl())
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(FirstSpecialDetailActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                SpecialDetailData specialDetailData = new Gson().fromJson(json, SpecialDetailData.class);
                listBeen.addAll(specialDetailData.getMessage().getList());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return listBeen.size();
        }

        @Override
        public SpecialDetailData.MessageBean.ListBean getItem(int position) {
            return listBeen.get(position);
        }

        @Override
        public long getItemId(int position) {
            return listBeen.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(FirstSpecialDetailActivity.this).inflate(R.layout.special_detail_item_view, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            SpecialDetailData.MessageBean.ListBean data = listBeen.get(position);
            holder.img.setImageURI(data.getBannerPath());
            holder.title.setText(data.getArticleGroupTitle());
            holder.des.setText(data.getDescription());
            return convertView;
        }

        private class ViewHolder {
            SimpleDraweeView img;
            TextView title;
            TextView des;

            protected ViewHolder(View view) {
                img = (SimpleDraweeView) view.findViewById(R.id.special_details_Item_img);
                title = (TextView) view.findViewById(R.id.special_details_item_title);
                des = (TextView) view.findViewById(R.id.special_details_item_des);
            }

        }
    }

}
