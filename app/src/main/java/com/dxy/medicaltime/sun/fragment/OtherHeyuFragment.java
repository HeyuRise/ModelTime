package com.dxy.medicaltime.sun.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.constants.Common;
import com.dxy.medicaltime.fang.activity.DetailsActivity;
import com.dxy.medicaltime.sun.adapter.ItemAdapter;
import com.dxy.medicaltime.sun.bean.OpenClassItem;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherHeyuFragment extends Fragment implements AdapterView.OnItemClickListener{
    private PullToRefreshListView pullToRefreshScrollView;
    private String baseurl="http://www.dxy.cn/webservices/channel/column/articles?channel=class&limit=20&pge=";
    private String url;
    private OpenClassItem openClassItem;
    private ArrayList<OpenClassItem.MessageBean.ListBean> data;
    private ItemAdapter adapter;
    private int page=1;
    private String id;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    adapter=new ItemAdapter(getActivity(),data);
                    pullToRefreshScrollView.setAdapter(adapter);
                    pullToRefreshScrollView.onRefreshComplete();
                    break;
                case 1:
                    adapter.notifyDataSetChanged();
            }
        }
    };

    public static OtherHeyuFragment newInstance(String id){
        OtherHeyuFragment f=new OtherHeyuFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_other_heyu,container,false);
        Bundle bundle=getArguments();
        id=bundle.getString("id");
        pullToRefreshScrollView= (PullToRefreshListView) view.findViewById(R.id.pull_to_refresh);
        pullToRefreshScrollView.setOnItemClickListener(this);
        data=new ArrayList<>();
        initUrl();
        initData();
        initPullToRefresh();
        return view;
    }

    public void initPullToRefresh(){
        pullToRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                data.clear();
                initData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                initUrl();
                initData1();
            }
        });
    }

    public void initUrl(){

        url=baseurl+page+"&username="+Common.username+"&u="+Common.username+"&mc="+ Common.mc+"&hardName="+Common.hardName+"&column="+id+"&ac="+Common.ac+"&vc="+Common.vc+"&vs="+Common.vs;
    }

    public void initData(){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json=response.body().string();
                Gson gson=new Gson();
                openClassItem=gson.fromJson(json,OpenClassItem.class);
                data.addAll(openClassItem.getMessage().getList());
                //data= (ArrayList<OpenClassItem.MessageBean.ListBean>) openClassItem.getMessage().getList();
                Message message=Message.obtain();
                message.what=0;
                handler.sendEmptyMessage(message.what);
            }
        });
    }
    public void initData1(){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json=response.body().string();
                Gson gson=new Gson();
                openClassItem=gson.fromJson(json,OpenClassItem.class);
                data.addAll(openClassItem.getMessage().getList());
                //data= (ArrayList<OpenClassItem.MessageBean.ListBean>) openClassItem.getMessage().getList();
                Message message=Message.obtain();
                message.what=1;
                handler.sendEmptyMessage(message.what);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("id",data.get(position-1).getId());
        intent.putExtra("title",data.get(position).getTitle());
        intent.putExtra("url",data.get(position).getUrl());
        intent.putExtra("img",data.get(position).getAppImg());
        intent.putExtra("time",data.get(position).getArticleDate());
        startActivity(intent);
    }
}
