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
import android.widget.ListView;
import android.widget.TextView;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.constants.Common;
import com.dxy.medicaltime.fang.activity.DetailsActivity;
import com.dxy.medicaltime.fang.activity.HeaderView;
import com.dxy.medicaltime.fang.bean.InfoItemData;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/25.
 * 资讯页公共的Fragment
 */
public class InfoCommonFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2<ListView>, AdapterView.OnItemClickListener {

    private int TOPDATA = 0, BOTTOMDATA = 1;

    private OkHttpClient client = new OkHttpClient();
    private PullToRefreshListView pullToRefreshListView;
    private MyAdapter adapter;
    private String dls;
    public int page = 1;
    private HeaderView headerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null)
            dls = bundle.getString("dls");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        pullToRefreshListView = (PullToRefreshListView) inflater.
                inflate(R.layout.pulltorefresh_list_view, container, false);
        ListView listView = pullToRefreshListView.getRefreshableView();
        headerView = new HeaderView(getContext(), addView(),getUrl());
        listView.addHeaderView(headerView.getHeaderView());
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new MyAdapter();
        pullToRefreshListView.setAdapter(adapter);
        downLoad(BOTTOMDATA);
        pullToRefreshListView.setOnRefreshListener(this);
        pullToRefreshListView.setOnItemClickListener(this);
        return pullToRefreshListView;
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        downLoad(TOPDATA);
    }

    /**
     * 上啦刷新
     */
    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        downLoad(BOTTOMDATA);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        InfoItemData.MessageBean.ListBean itemData=adapter.getItem(position-2);
        Intent intent=new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("id",itemData.getId());
        intent.putExtra("title",itemData.getTitle());
        intent.putExtra("url",itemData.getUrl());
        intent.putExtra("img",itemData.getAppImg());
        intent.putExtra("time",itemData.getArticleDate());
        intent.putExtra("shareCount",itemData.getNumOfShared()+"");
        startActivity(intent);
    }

    /**
     * 为最新资讯页添加那些按钮预留的方法
     */
    public View addView() {
        return null;
    }

    /**
     * 获取URL的方法，单独提取出来以便子类重写
     */
    public String getUrl() {
        String subUrl = "http://www.dxy.cn/webservices/article/list/departments/v3.3?version=1&appTop=true&appImg=true&limit=20&homepage=0&mc=ffffffffa63b8598930190c405753397&ac=9432e53c-74f7-44f9-be13-15f6cc3cd4ba&vc=4.5.1&vs=4.4.2";
        String url = subUrl + "&username=" + Common.username + "&u=" + Common.u + "&dls=" + dls + "&pge=";
        return url;
    }

    /**
     * 下载listView中数据
     */
    private void downLoad(final int flag) {
        Request request = new Request.Builder()
                .url(getUrl()+page++)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                final InfoItemData data = gson.fromJson(json, InfoItemData.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addData(data.getMessage().getList(), flag);
                        pullToRefreshListView.onRefreshComplete();
                    }
                });
            }
        });
    }

    private class MyAdapter extends BaseAdapter {

        private ArrayList<InfoItemData.MessageBean.ListBean> list = new ArrayList<>();

        /**
         * 下载数据后更新
         */
        protected void addData(ArrayList<InfoItemData.MessageBean.ListBean> list, int flags) {
            if (flags == TOPDATA)
                this.list.addAll(0, list);
            if (flags == BOTTOMDATA)
                this.list.addAll(list);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public InfoItemData.MessageBean.ListBean getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return list.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.info_fragment_item_view, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            InfoItemData.MessageBean.ListBean data = list.get(position);

            holder.titleTv.setText(data.getTitle());
            holder.shareTv.setText(data.getNumOfShared() +"分享");
            String time=data.getArticleDate();
            holder.timeTv.setText(time.substring(0,time.indexOf("")));
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
