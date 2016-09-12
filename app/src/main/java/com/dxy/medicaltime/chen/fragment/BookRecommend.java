package com.dxy.medicaltime.chen.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dxy.medicaltime.R;
import com.dxy.medicaltime.chen.activity.BookDetailActivity;
import com.dxy.medicaltime.chen.activity.NewBooksActivity;
import com.dxy.medicaltime.chen.activity.RecommendBooksActivity;
import com.dxy.medicaltime.chen.adapter.EditBooksAdapter;
import com.dxy.medicaltime.chen.adapter.MyAdAdapter;
import com.dxy.medicaltime.chen.bean.EditAndPopBooks;
import com.dxy.medicaltime.chen.bean.NewBook;
import com.dxy.medicaltime.chen.bean.Pic;
import com.dxy.medicaltime.chen.bean.RecommendHeadView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/25.
 */
public class BookRecommend extends Fragment{
    AutoScrollViewPager viewPager;
    TextView editTextView,PopTextView;
    MyGridView editgridView,PopgridView;
    String mc="0000000038ff13b4ffffffffe9f0e5e8&hardName=000000038ff13b4ffffffffe9f0e5e8&hardName=R6007R6007";
    //新书推荐图片地址
    String url1=" http://i.dxy.cn/bbs/bbsapi/mobile?s=special_book_type&ac=9432e53c-74f7-44f9-be13-15f6cc3cd4ba&vc=4.5.1&vs=4.4.2&mc=0";
    //热门推荐
    String url3="http://d.dxy.cn/book/api/read?action=Recommend&appType=1&tpg=1&size=20&ac=9432e53c-74f7-44f9-be13-15f6cc3cd4ba&vc=4.5.1&vs=4.4.2&mc="+mc;
    //编辑推荐
    String url4="http://d.dxy.cn/book/api/read?action=EditorRecommend&appType=1&tpg=1&size=20&ac=9432e53c-74f7-44f9-be13-15f6cc3cd4ba&vc=4.5.1&vs=4.4.2&mc="+mc;
    //新书推荐书籍地址
    String url5="http://i.dxy.cn/bbs/bbsapi/mobile?s=special_book_list&typeId=9&page=1&size=20&ac=9432e53c-74f7-44f9-be13-15f6cc3cd4ba&vc=4.5.1&vs=4.4.2&mc="+mc;

    //图片数据
    RecommendHeadView recommendheadViewData;

    //推荐GridView的数据集合（GridView展示）
    List<NewBook>Editlist;
    List<NewBook>Poplist;

    //新书推荐集合
    List<Pic>NewList;
    List<View>viewList;
    //点击推荐进入的书籍简介集合（ListView展示）
    List<NewBook>AllList;
    //编辑和热门推荐的适配器
    EditBooksAdapter editBooksAdapter;
    EditBooksAdapter popBooksAdapter;

    String subjectId;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getActivity());
        View rootView;
        rootView=inflater.inflate(R.layout.fragment_recommend, container, false);
        viewPager= (AutoScrollViewPager) rootView.findViewById(R.id.Recommend_headView);
        editTextView= (TextView) rootView.findViewById(R.id.editTextView);
        PopTextView= (TextView) rootView.findViewById(R.id.popular_TextView);
        editgridView= (MyGridView) rootView.findViewById(R.id.edit_gridView);
        PopgridView= (MyGridView) rootView.findViewById(R.id.popular_gridView);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化集合
        viewList=new ArrayList<>();
        NewList=new ArrayList<>();
        Editlist=new ArrayList<>();
        Poplist=new ArrayList<>();
        AllList=new ArrayList<>();
        //头视图图片下载

        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(url1)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json=response.body().string();
                Gson gson=new Gson();
                recommendheadViewData=gson.fromJson(json,RecommendHeadView.class);
                NewList.addAll(recommendheadViewData.getItems());
                //图片地址
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0;i<NewList.size();i++){
                            ImageView  img=new ImageView(getActivity());
                            Picasso.with(getActivity()).load(NewList.get(i).getImage()).into(img);
                            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            viewList.add(img);
                            img.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    for(int j=0;j<viewList.size();j++){
                                        if(v==viewList.get(j)){
                                            subjectId=NewList.get(j).getSubjectId();
                                            Intent intent2 = new Intent(getActivity(), NewBooksActivity.class);
                                            intent2.putExtra("subjectId",subjectId);
                                            startActivity(intent2);
                                        }
                                    }

                                }
                            });

                        }
                        viewPager.setAdapter(new MyAdAdapter(viewList));
                        viewPager.setBorderAnimation(true);
                        //设置自动循环轮播，默认开启
                        viewPager.setCycle(true);
                        //设置循环轮播的事件间隔
                        viewPager.setInterval(100000);
                        //开启自动循环轮播
                        viewPager.startAutoScroll();
                    }
                });

            }
        });


        //编辑推荐GridVIew展示数据下载
        OkHttpClient client3=new OkHttpClient();
        Request request3=new Request.Builder()
                .url(url4)
                .build();
        client3.newCall(request3).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json=response.body().string();
                Gson gson=new Gson();
                EditAndPopBooks editAndPopBooks=gson.fromJson(json,EditAndPopBooks.class);
                Editlist=editAndPopBooks.getBooks();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        editBooksAdapter=new EditBooksAdapter(Editlist,getActivity());
                        editgridView.setAdapter(editBooksAdapter);
                    }
                });
            }
        });
        //热门推荐GridVIew展示数据下载
        OkHttpClient client4=new OkHttpClient();
        Request request4=new Request.Builder()
                .url(url3)
                .build();
        client4.newCall(request4).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json=response.body().string();
                Gson gson=new Gson();
                EditAndPopBooks editAndPopBooks=gson.fromJson(json,EditAndPopBooks.class);
                Poplist=editAndPopBooks.getBooks();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        popBooksAdapter=new EditBooksAdapter(Poplist,getActivity());
                        PopgridView.setAdapter(popBooksAdapter);
                    }
                });
            }
        });




    }
    @Override
    public void onStart() {
        super.onStart();
        //点击编辑推荐展示书籍
        editTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = "a";
                Intent intent = new Intent(getActivity(), RecommendBooksActivity.class);
                intent.putExtra("type", type);
                startActivity(intent);
            }
        });
        //点击热门推荐展示书籍
        PopTextView.setOnClickListener(new View.OnClickListener() {
            //热门推荐接口
            @Override
            public void onClick(View v) {
                String type = "b";
                Intent intent = new Intent(getActivity(), RecommendBooksActivity.class);
                intent.putExtra("type", type);
                startActivity(intent);
            }
        });


        editgridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent2 = new Intent(getActivity(), BookDetailActivity.class);
                intent2.putExtra("id", Editlist.get(position).getId());
                startActivity(intent2);
            }
        });
        PopgridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent2 = new Intent(getActivity(), BookDetailActivity.class);
                intent2.putExtra("id", Poplist.get(position).getId());
                startActivity(intent2);
            }
        });

        //轮播
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


}
