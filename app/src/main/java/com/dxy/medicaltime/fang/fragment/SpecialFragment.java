package com.dxy.medicaltime.fang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.constants.Common;
import com.dxy.medicaltime.fang.activity.FirstSpecialDetailActivity;
import com.dxy.medicaltime.fang.adapter.MyAdapter;
import com.dxy.medicaltime.fang.bean.SpecialTitleData;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/26.
 * 专题Fragment
 */
public class SpecialFragment extends Fragment implements AdapterView.OnItemClickListener {

    private GridView gridView;
    private ArrayList<SpecialTitleData.MessageBean> specialTitleData = new ArrayList<>();
    private OkHttpClient client = new OkHttpClient();
    private MyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        gridView = (GridView) inflater.inflate(R.layout.special_fragment_view, container, false);
        adapter = new MyAdapter(specialTitleData,getContext());
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
        downLoad();
        return gridView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SpecialTitleData.MessageBean itemdata = specialTitleData.get(position);
        Intent intent = new Intent(getActivity(), FirstSpecialDetailActivity.class);
        intent.putExtra("id", itemdata.getId() + "");
        intent.putExtra("img",itemdata.getIconUrl());
        intent.putExtra("title", itemdata.getName());
        startActivity(intent);
    }

    private void downLoad() {
        String subUrl = "http://www.dxy.cn/webservices/special/category?";
        String url = subUrl + "username=" + Common.username + "&mc=" + Common.mc + "&hardName=" + Common.hardName + "&ac=" + Common.ac + "&vc=" + Common.vc + "&vs=" + Common.vc;
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                SpecialTitleData data = new Gson().fromJson(json, SpecialTitleData.class);
                specialTitleData.addAll(data.getMessage());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetInvalidated();
                    }
                });
            }
        });

    }

}
