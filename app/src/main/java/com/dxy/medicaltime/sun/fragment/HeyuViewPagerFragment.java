package com.dxy.medicaltime.sun.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.constants.Common;
import com.dxy.medicaltime.fang.activity.DetailsActivity;
import com.dxy.medicaltime.sun.bean.OpenClassItem;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeyuViewPagerFragment extends Fragment implements View.OnClickListener{
    private ImageView imageView;
    private TextView textView;
    private int position;
    private ArrayList<OpenClassItem.MessageBean.ListBean> data;
    private String baseurl="http://www.dxy.cn/webservices/channel/column/articles?channel=class&limit=20&pge=1&username="+ Common.username+"&u="+Common.username+"&mc="+ Common.mc+"&hardName="+Common.hardName+"&ac="+Common.ac+"&vc="+Common.vc+"&vs="+Common.vs;

    public static HeyuViewPagerFragment newInstance(int position1){
        HeyuViewPagerFragment f=new HeyuViewPagerFragment();
        Bundle b=new Bundle();
        b.putInt("position",position1);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_heyu_view_pager, container, false);
        Bundle bundle=getArguments();
        position=bundle.getInt("position");
        data=new ArrayList<>();
        imageView= (ImageView) view.findViewById(R.id.heyu_viewPager_img);
        textView= (TextView) view.findViewById(R.id.heyu_viewPager_text);
        imageView.setOnClickListener(this);
        initData();
        return view;
    }
    private void initData(){
        data.clear();
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(baseurl).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json=response.body().string();
                Gson gson=new Gson();
                OpenClassItem item=gson.fromJson(json,OpenClassItem.class);
                data= (ArrayList<OpenClassItem.MessageBean.ListBean>) item.getMessage().getList();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(data.get(position).getTitle());
                        Picasso.with(getActivity()).load(data.get(position).getAppImg()).into(imageView);
                    }
                });
            }
        });
    }
    public void flash(){
        initData();
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("id",data.get(position).getId());
        intent.putExtra("title",data.get(position).getTitle());
        intent.putExtra("url",data.get(position).getUrl());
        intent.putExtra("img",data.get(position).getAppImg());
        intent.putExtra("time",data.get(position).getArticleDate());
        startActivity(intent);
    }
}
