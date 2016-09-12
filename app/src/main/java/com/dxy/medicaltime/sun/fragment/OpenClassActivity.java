package com.dxy.medicaltime.sun.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.constants.Common;
import com.dxy.medicaltime.sun.adapter.HeyuViewPagerAdapter;
import com.dxy.medicaltime.sun.bean.OpenClass;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

public class OpenClassActivity extends Fragment {
    private String urlBase="http://www.dxy.cn/webservices/channel/column?channel=class&username=";
    private String userName;
    private String url;
    private OpenClass openClass;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<String> tabList;
    private ArrayList<Fragment> fragmentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.activity_open_class,container,false);
        tabList=new ArrayList<>();
        tabLayout= (TabLayout) rootView.findViewById(R.id.tabLayoutHeyu);
        viewPager= (ViewPager) rootView.findViewById(R.id.viewPagerHeyu);
        initUrl();
        initData();
        return rootView;
    }

    private void initTabLayout(){
        tabLayout.setTabTextColors(Color.GRAY,Color.WHITE);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        tabLayout.setSelectedTabIndicatorHeight(4);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabList.add("推荐");
        for(int i=0;i<openClass.getMessage().getColumns().size();i++){
            tabList.add(openClass.getMessage().getColumns().get(i).getName());
        }
        for (int i = 0; i <tabList.size() ; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabList.get(i)));
        }

    }
    private void initUrl(){
        try {
            userName= URLDecoder.decode(Common.username,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        url=urlBase+userName+"&u="+userName+"&mc="+Common.mc+"&hardName="+Common.hardName+"&ac="+Common.ac+"&vc="+Common.vc+"&vs="+Common.vs;
    }
    private void initData(){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json=response.body().string();
                Log.e("1608",json);
                Gson gson=new Gson();
                openClass=gson.fromJson(json,OpenClass.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initTabLayout();
                        initFragment();
                        HeyuViewPagerAdapter adapter=new HeyuViewPagerAdapter(getFragmentManager(),fragmentList,tabList);
                        viewPager.setAdapter(adapter);
                        tabLayout.setupWithViewPager(viewPager);
                    }
                });
            }
        });
    }
    private void initFragment(){
        fragmentList=new ArrayList<>();
        fragmentList.add(new RecommendFragment());
        for (int i=0;i<openClass.getMessage().getColumns().size();i++){
            fragmentList.add(OtherHeyuFragment.newInstance(openClass.getMessage().getColumns().get(i).getId()));
        }
    }
    public void toMy(View view){
        Intent intent=new Intent(getContext(),TextHeyuMyActivity.class);
        startActivity(intent);
    }

}
