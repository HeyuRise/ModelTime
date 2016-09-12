package com.dxy.medicaltime.fang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.constants.Common;
import com.dxy.medicaltime.fang.activity.DetailsActivity;
import com.dxy.medicaltime.fang.bean.NewestGuideData;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/27.
 * 最新指南
 */
public class NewestGuideFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2 ,AdapterView.OnItemClickListener{

    private final int TOPDATA = 0, BOTTOMDATA = 1;

    private OkHttpClient client = new OkHttpClient();
    private ArrayList<NewestGuideData.MessageBean.ListBean> listBeen = new ArrayList<>();
    private PullToRefreshListView pullToRefreshListView;
    private MyAdapter adapter;
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        pullToRefreshListView = (PullToRefreshListView) inflater.inflate(R.layout.pulltorefresh_list_view, null);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshListView.setOnRefreshListener(this);
        adapter=new MyAdapter();
        pullToRefreshListView.setAdapter(adapter);
        pullToRefreshListView.setOnItemClickListener(this);
        downLoad(BOTTOMDATA);
        return pullToRefreshListView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getContext(), DetailsActivity.class);
        NewestGuideData.MessageBean.ListBean data=listBeen.get(position-1);
        intent.putExtra("id",data.getId());
        intent.putExtra("title",data.getTitle());
        intent.putExtra("url",data.getUrl());
        intent.putExtra("img",data.getAppImg());
        intent.putExtra("time",data.getArticleDate());
        intent.putExtra("shareCount",data.getNumOfShared()+"");
        startActivity(intent);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        downLoad(TOPDATA);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        downLoad(BOTTOMDATA);
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
                NewestGuideData guideData = new Gson().fromJson(json, NewestGuideData.class);
                if (flag == TOPDATA)
                    listBeen.addAll(0, guideData.getMessage().getList());
                else
                    listBeen.addAll(guideData.getMessage().getList());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        pullToRefreshListView.onRefreshComplete();
                    }
                });
            }
        });
    }

    private String getUrl() {
        String subUrl = "http://www.dxy.cn/webservices/article/latest?version=1&tagIds=288&appImg=false&appTop=false&limit=20";
        String url = subUrl + "&ac=" + Common.ac + "&vc=" + Common.vc + "&vs=" + Common.vs + "&mc=" + Common.mc + "&hardName=" + Common.hardName + "&pge=" + page++;
        return url;
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return listBeen.size();
        }

        @Override
        public NewestGuideData.MessageBean.ListBean getItem(int position) {
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
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.newestguide_fragment_item_view, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            NewestGuideData.MessageBean.ListBean data = listBeen.get(position);
            holder.img.setImageURI(data.getAppImg());
            holder.titleTv.setText(data.getTitle());
            holder.shareTv.setText(data.getNumOfShared() + "分享");
            String time = data.getArticleDate();
            holder.timeTv.setText(time.substring(0, time.indexOf(" ")));
            return convertView;
        }

        private class ViewHolder {
            SimpleDraweeView img;
            TextView titleTv;
            TextView shareTv;
            TextView timeTv;

            protected ViewHolder(View convertView) {
                img = (SimpleDraweeView) convertView.findViewById(R.id.guide_item_img);
                titleTv = (TextView) convertView.findViewById(R.id.guide_item_title);
                shareTv = (TextView) convertView.findViewById(R.id.guide_item_share);
                timeTv = (TextView) convertView.findViewById(R.id.guide_item_time);
            }
        }
    }

}
