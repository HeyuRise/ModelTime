package com.dxy.medicaltime.sun.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.constants.Common;
import com.dxy.medicaltime.fang.activity.DetailsActivity;
import com.dxy.medicaltime.sun.adapter.HeyuViewPagerAdapter2;
import com.dxy.medicaltime.sun.adapter.ItemAdapter;
import com.dxy.medicaltime.sun.bean.OpenClassItem;
import com.dxy.medicaltime.sun.utils.MyListView;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.squareup.picasso.Picasso;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;


public class RecommendFragment extends Fragment implements AdapterView.OnItemClickListener{
    private LinearLayout linearLayout1,linearLayout2;
    private RelativeLayout relativeLayout;
    private PullToRefreshScrollView pullToRefreshScrollView;
    private AutoScrollViewPager viewPager;
    private MyListView listView1,listView2;
    private ImageView imageView;
    private TextView title,text;
    private int page=1;
    private boolean isViewPager=false;
    private String baseurl="http://www.dxy.cn/webservices/channel/column/articles?channel=class&limit=20&pge=";
    private String url;
    private ArrayList<OpenClassItem.MessageBean.ListBean> data;
    private OpenClassItem.MessageBean messageBean;
    private ArrayList<OpenClassItem.MessageBean.ListBean> list1Data;
    private ArrayList<OpenClassItem.MessageBean.ListBean> list2Data;
    private ArrayList<Fragment> fragmentList;
    private HeyuViewPagerAdapter2 viewPagerAdapter;
    private HeyuViewPagerFragment fragment1,fragment2,fragment3;
    private ItemAdapter adapter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    pullToRefreshScrollView.onRefreshComplete();
                    if(messageBean.getAdList().size()!=0){
                        linearLayout1.setVisibility(View.VISIBLE);
                        linearLayout2.setVisibility(View.VISIBLE);
                        relativeLayout.setVisibility(View.VISIBLE);
                        title.setText(messageBean.getAdList().get(0).getTitle());
                        text.setText(messageBean.getAdList().get(0).getAddescription());
                        Picasso.with(getActivity()).load(messageBean.getAdList().get(0).getAdImg()).into(imageView);
                    }
                    data= (ArrayList<OpenClassItem.MessageBean.ListBean>) messageBean.getList();
                    for(int i=3;i<6;i++){
                            list1Data.add(i-3,data.get(i));
                    }
                    for(int i=6;i<data.size();i++){
                            list2Data.add(i-6,data.get(i));
                    }
                    listView1.setAdapter(new ItemAdapter(getActivity(),list1Data));
                    adapter=new ItemAdapter(getActivity(),list2Data);
                    listView2.setAdapter(adapter);
                    if(isViewPager){
                        fragment1.flash();
                        fragment2.flash();
                        fragment3.flash();
                        isViewPager=true;
                    }
                    break;
                case 2:
                    if (adapter!=null){
                        adapter.notifyDataSetChanged();
                    }
                    pullToRefreshScrollView.onRefreshComplete();
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_recommend_sun,container,false);
        linearLayout1= (LinearLayout) view.findViewById(R.id.shadow);
        linearLayout2= (LinearLayout) view.findViewById(R.id.shadow1);
        relativeLayout= (RelativeLayout) view.findViewById(R.id.advertise);
        linearLayout1.setVisibility(View.GONE);
        linearLayout2.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.GONE);
        init(view);
        initViewPager();
        initUrl();
        initData();
        initPullToRefresh();
        return view;
    }
    public void init(View view){
        pullToRefreshScrollView= (PullToRefreshScrollView) view.findViewById(R.id.pull_to_refresh_scrollView);
        viewPager= (AutoScrollViewPager) view.findViewById(R.id.autoViewPager);
        listView1= (MyListView) view.findViewById(R.id.heyu_recommend_listView1);
        listView2= (MyListView) view.findViewById(R.id.heyu_recommend_listView2);
        listView1.setOnItemClickListener(this);
        listView2.setOnItemClickListener(this);
        imageView= (ImageView) view.findViewById(R.id.recommendAdImg);
        title= (TextView) view.findViewById(R.id.recommendAdtitle);
        text= (TextView) view.findViewById(R.id.recommendAdText);

    }
    public void initViewPager(){
        fragmentList=new ArrayList<>();
        fragment1=HeyuViewPagerFragment.newInstance(0);
        fragment2=HeyuViewPagerFragment.newInstance(1);
        fragment3=HeyuViewPagerFragment.newInstance(2);
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        viewPagerAdapter=new HeyuViewPagerAdapter2(getFragmentManager(),fragmentList);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setBorderAnimation(true);
        viewPager.setCycle(true);
        viewPager.setInterval(4000);
        viewPager.startAutoScroll();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {



            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        viewPager.stopAutoScroll();
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        viewPager.startAutoScroll();
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        break;
                }

            }
        });
    }
    public void initUrl(){
        url=baseurl+page+"&username="+ Common.username+"&u="+Common.username+"&mc="+ Common.mc+"&hardName="+Common.hardName+"&ac="+Common.ac+"&vc="+Common.vc+"&vs="+Common.vs;
    }
    public void initData(){
        data=new ArrayList<>();
        list1Data=new ArrayList<>();
        list2Data=new ArrayList<>();
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
                OpenClassItem item=gson.fromJson(json,OpenClassItem.class);
                    data.clear();
                    list1Data.clear();
                    list2Data.clear();
                    messageBean=item.getMessage();
                    Message message=Message.obtain();
                    message.what=1;
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
                Log.e("========",json);
                Gson gson=new Gson();
                OpenClassItem item=gson.fromJson(json,OpenClassItem.class);
                list2Data.addAll(item.getMessage().getList());
                Message msg=Message.obtain();
                msg.what=2;
                handler.sendEmptyMessage(msg.what);
            }
        });
    }
    public void initPullToRefresh(){
        pullToRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                initData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                page++;
                initUrl();
                initData1();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getActivity(), DetailsActivity.class);
        switch (parent.getId()){
            case R.id.heyu_recommend_listView1:
                intent.putExtra("id",list1Data.get(position).getId());
                intent.putExtra("title",list1Data.get(position).getTitle());
                intent.putExtra("url",list1Data.get(position).getUrl());
                intent.putExtra("img",list1Data.get(position).getAppImg());
                intent.putExtra("time",list1Data.get(position).getArticleDate());
                break;
            case R.id.heyu_recommend_listView2:
                intent.putExtra("id",list2Data.get(position).getId());
                intent.putExtra("title",list2Data.get(position).getTitle());
                intent.putExtra("url",list2Data.get(position).getUrl());
                intent.putExtra("img",list2Data.get(position).getAppImg());
                intent.putExtra("time",list2Data.get(position).getArticleDate());
                break;
        }
        startActivity(intent);
    }
}
