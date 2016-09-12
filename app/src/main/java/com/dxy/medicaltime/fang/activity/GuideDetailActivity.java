package com.dxy.medicaltime.fang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.constants.Common;
import com.dxy.medicaltime.fang.bean.GuideClassDetailData;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 * 指南详情Activity
 */
public class GuideDetailActivity extends AppCompatActivity implements PullToRefreshBase.OnRefreshListener2, AdapterView.OnItemClickListener {

    private final int TOPDATA = 0, BOTTMDATA = 1;
    private List<GuideClassDetailData.MessageBean.ListBean> listBeen = new ArrayList<>();
    private MyAdapter adapter;
    private PullToRefreshListView listView;
    private int page = 1;
    private String channel;
    private OkHttpClient client = new OkHttpClient();
    private int totalCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.special_detail_activity_view);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        channel = intent.getStringExtra("id");

        TextView titleTv = (TextView) findViewById(R.id.special_details_title_tv);
        titleTv.setText(title);

        listView = new PullToRefreshListView(this);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        listView.setLayoutParams(layoutParams);
        ((LinearLayout)findViewById(R.id.special_detail_activity_view)).addView(listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(this);
        listView.setOnItemClickListener(this);

        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        downLoad(BOTTMDATA);
    }

    public void onBtnClick(View view) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GuideClassDetailData.MessageBean.ListBean data = listBeen.get(position - 1);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("id",data.getId());
        intent.putExtra("title", data.getTitle());
        intent.putExtra("url", data.getUrl());
        intent.putExtra("img",data.getImgpath());
        intent.putExtra("time",data.getArticleDate());
        startActivity(intent);
    }

    /**
     * 获取URL的方法，单独提取出来以便子类重写
     */
    public String getUrl() {
        String subUrl = "http://www.dxy.cn/webservices/article/list/channel-tag?";
        String url = subUrl + "tagname=guideline" + "&limit=20" + "&username=" + Common.username +
                "&u=" + Common.u + "&mc=" + Common.mc + "&hardName=" + Common.hardName + "&ac=" + Common.ac +
                "&vc=" + Common.vc + "&vs=" + Common.vs + "&channel=" + channel + "&pge=" + page++;
        Log.d("mytest", "getUrl: " + url);
        return url;
    }

    private void downLoad(final int flag) {
        Request request = new Request.Builder()
                .url(getUrl())
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                GuideClassDetailData data = new Gson().fromJson(json, GuideClassDetailData.class);
                totalCount = data.getMessage().getTotal();
                if (flag == TOPDATA) {
                    listBeen.addAll(0, data.getMessage().getList());
                } else {
                    listBeen.addAll(data.getMessage().getList());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        listView.onRefreshComplete();
                    }
                });
            }
        });
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        if (listBeen.size() < totalCount)
            downLoad(TOPDATA);
        else
            listView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        if (listBeen.size() < totalCount)
            downLoad(BOTTMDATA);
        else
            listView.onRefreshComplete();
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return listBeen.size();
        }

        @Override
        public GuideClassDetailData.MessageBean.ListBean getItem(int position) {
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
                convertView = LayoutInflater.from(GuideDetailActivity.this).inflate(R.layout.info_fragment_item_view, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            GuideClassDetailData.MessageBean.ListBean data = listBeen.get(position);

            holder.titleTv.setText(data.getTitle());
            holder.shareTv.setText(data.getNumOfShared() + "分享");
            String time = data.getArticleDate();
            holder.timeTv.setText(time.substring(0, time.indexOf("")));
            holder.img.setImageURI(data.getImgpath());

            return convertView;
        }

        private class ViewHolder {

            SimpleDraweeView img;
            TextView titleTv;
            TextView shareTv;
            TextView timeTv;

            protected ViewHolder(View convertView) {
                img = (SimpleDraweeView) convertView.findViewById(R.id.info_item_img);
                titleTv = (TextView) convertView.findViewById(R.id.info_item_title);
                shareTv = (TextView) convertView.findViewById(R.id.info_item_share);
                timeTv = (TextView) convertView.findViewById(R.id.info_item_time);
            }

        }
    }

}
