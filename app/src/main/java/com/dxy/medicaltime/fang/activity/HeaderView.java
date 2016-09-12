package com.dxy.medicaltime.fang.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.fang.bean.InfoItemData;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/7/26.
 * 用于创建头视图的一个普通类
 */
public class HeaderView implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ArrayList adListBeen = new ArrayList<>();
    private ViewPager viewPager;
    private LinearLayout rootView;
    private LinearLayout pointLayout;
    private TextView infoAdTitle;
    private ArrayList<TextView> points = new ArrayList<>();
    private ArrayList<ImageView> imageViews = new ArrayList<>();
    private int currentItem = 1;
    private boolean isTouch = false;
    private Timer timer = new Timer();
    private Context context;
    private float density;
    private boolean reallyAd = false;
    private int time = 10;

    public HeaderView(final Context context, View addView, String url) {
        this.context = context;
        rootView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.info_header_view, null);
        if (addView != null)
            rootView.addView(addView);
        viewPager = (ViewPager) rootView.findViewById(R.id.info_header_view_pager);
        pointLayout = (LinearLayout) rootView.findViewById(R.id.point_layout);
        infoAdTitle = (TextView) rootView.findViewById(R.id.info_ad_title_tv);
        density = context.getResources().getDisplayMetrics().density;
        downLoad(url);
    }


    /**
     * 点击广告，打开详情
     */
    @Override
    public void onClick(View v) {
        for (int i = 0; i < adListBeen.size(); i++) {
            if (v == imageViews.get(i + 1)) {
                Intent intent = new Intent(context, DetailsActivity.class);
                if (reallyAd) {
                    InfoItemData.MessageBean.AdListBean data = (InfoItemData.MessageBean.AdListBean) adListBeen.get(i);
                    intent.putExtra("title", data.getTitle());
                    intent.putExtra("url", data.getOutLink());
                    intent.putExtra("shareCount", "");
                } else {
                    InfoItemData.MessageBean.ListBean listBean = (InfoItemData.MessageBean.ListBean) adListBeen.get(i);
                    intent.putExtra("title", listBean.getTitle());
                    intent.putExtra("url", listBean.getUrl());
                    intent.putExtra("shareCount", listBean.getNumOfShared());
                }
                context.startActivity(intent);
                break;
            }
        }
    }

    /**
     * @return 返回创建好的头视图
     */
    public View getHeaderView() {
        return rootView;
    }

    /**
     * 当数据加载完成,将其添加进去
     */
    public void addData(ArrayList adListBeen) {

        if (adListBeen == null || adListBeen.size() == 0)
            return;
        this.adListBeen.addAll(adListBeen);
        if (adListBeen.get(0) instanceof InfoItemData.MessageBean.AdListBean)
            reallyAd = true;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ImageView img = new ImageView(context);
        img.setLayoutParams(layoutParams);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (reallyAd)
            Picasso.with(context).load(((InfoItemData.MessageBean.AdListBean) adListBeen.get(adListBeen.size() - 1)).getAdImg()).into(img);
        else
            Picasso.with(context).load(((InfoItemData.MessageBean.ListBean) adListBeen.get(adListBeen.size() - 1)).getAppImg()).into(img);
        imageViews.add(img);
        LinearLayout.LayoutParams pointParams = new LinearLayout.LayoutParams((int) (8 * density), (int) (8 * density));
        pointParams.setMargins(0, 0, (int) (6 * density), 0);
        for (int i = 0; i < adListBeen.size(); i++) {
            img = new ImageView(context);
            img.setLayoutParams(layoutParams);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setOnClickListener(this);
            if (reallyAd)
                Picasso.with(context).load(((InfoItemData.MessageBean.AdListBean) adListBeen.get(i)).getAdImg()).into(img);
            else
                Picasso.with(context).load(((InfoItemData.MessageBean.ListBean) adListBeen.get(i)).getAppImg()).into(img);
            imageViews.add(img);
            if (adListBeen.size() > 1) {
                TextView pointView = new TextView(context);
                pointView.setLayoutParams(pointParams);
                pointView.setBackgroundResource(R.drawable.point_bcg);
                points.add(pointView);
                pointLayout.addView(pointView);
            }
        }

        img = new ImageView(context);
        img.setLayoutParams(layoutParams);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        if (reallyAd)
            Picasso.with(context).load(((InfoItemData.MessageBean.AdListBean) adListBeen.get(0)).getAdImg()).into(img);
        else
            Picasso.with(context).load(((InfoItemData.MessageBean.ListBean) adListBeen.get(0)).getAppImg()).into(img);
        imageViews.add(img);
        viewPager.setAdapter(new MyAdapter());
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(1, false);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!isTouch) {
                            if (time == 0) {
                                if (currentItem == imageViews.size() - 1)
                                    viewPager.setCurrentItem(1);
                                else
                                    viewPager.setCurrentItem(currentItem + 1);
                                time = 10;
                            }else{
                                time--;
                            }
                        }
                    }
                });
            }
        };
        if (adListBeen.size() > 1) {
            points.get(0).setSelected(true);
            timer.schedule(task, 500, 500);
        }
    }

    private void downLoad(String url) {
        Request request = new Request.Builder()
                .url(url + 1)
                .build();

        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                final InfoItemData data = gson.fromJson(json, InfoItemData.class);
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (data.getMessage().getAdList() != null && data.getMessage().getAdList().size() != 0) {
                            addData(data.getMessage().getAdList());
                        } else {
                            ArrayList<InfoItemData.MessageBean.ListBean> list = new ArrayList<>();
                            list.add(data.getMessage().getList().get(0));
                            list.add(data.getMessage().getList().get(1));
                            addData(list);
                        }

                    }
                });
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
        if (position - 1 == adListBeen.size()) {
            if (reallyAd)
                infoAdTitle.setText(((InfoItemData.MessageBean.AdListBean) adListBeen.get(0)).getTitle());
            else
                infoAdTitle.setText(((InfoItemData.MessageBean.ListBean) adListBeen.get(0)).getTitle());
        } else if (position == 0) {
            if (reallyAd)
                infoAdTitle.setText(((InfoItemData.MessageBean.AdListBean) adListBeen.get(adListBeen.size() - 1)).getTitle());
            else
                infoAdTitle.setText(((InfoItemData.MessageBean.ListBean) adListBeen.get(adListBeen.size() - 1)).getTitle());
        } else {
            if (reallyAd)
                infoAdTitle.setText(((InfoItemData.MessageBean.AdListBean) adListBeen.get(position - 1)).getTitle());
            else
                infoAdTitle.setText(((InfoItemData.MessageBean.ListBean) adListBeen.get(position - 1)).getTitle());
        }

        for (int i = 0; i < points.size(); i++) {
            points.get(i).setSelected(i == position - 1);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE && currentItem == adListBeen.size() + 1)
            viewPager.setCurrentItem(1, false);
        if (state == ViewPager.SCROLL_STATE_IDLE && currentItem == 0)
            viewPager.setCurrentItem(adListBeen.size(), false);
        if (state == ViewPager.SCROLL_STATE_DRAGGING)
            isTouch = true;
        if (state == ViewPager.SCROLL_STATE_SETTLING) {
            isTouch = false;
            time=10;
        }
    }

    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public ImageView instantiateItem(ViewGroup container, int position) {
            container.addView(imageViews.get(position));
            return imageViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));
        }

    }

}
