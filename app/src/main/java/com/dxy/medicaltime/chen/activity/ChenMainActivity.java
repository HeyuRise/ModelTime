package com.dxy.medicaltime.chen.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.chen.adapter.MyFragmentAdapter;
import com.dxy.medicaltime.chen.fragment.BookClassify;
import com.dxy.medicaltime.chen.fragment.BookRecommend;
import com.dxy.medicaltime.chen.fragment.BookShelf;

import java.util.ArrayList;
import java.util.List;

public class ChenMainActivity extends Fragment implements View.OnClickListener {
    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    ImageView imageView;

    //碎片集合
    List<Fragment> fragmentList;
    //标题集合
    List<String> titleList;
    BookShelf shelfFragment;
    BookRecommend recommendFragment;
    BookClassify classifyFragment;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_chen_main, container, false);

        imageView = (ImageView) rootView.findViewById(R.id.ClickMenu);
        tabLayout = (TabLayout) rootView.findViewById(R.id.BookStore_tabLayout);
        toolbar = (Toolbar) rootView.findViewById(R.id.BookStore_toolbar);
        viewPager = (ViewPager) rootView.findViewById(R.id.bookStore_view_pager);
        rootView.findViewById(R.id.searc_chen).setOnClickListener(this);
        rootView.findViewById(R.id.ClickMenu).setOnClickListener(this);
        //设置下划线宽度
        tabLayout.setSelectedTabIndicatorHeight(7);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);

        //初始化标题集合
        titleList = new ArrayList<>();
        titleList.add("书架");
        titleList.add("推荐");
        titleList.add("分类");

        //初始化碎片集合
        fragmentList = new ArrayList<>();
        for (int i = 0; i < titleList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titleList.get(i)));
        }
        shelfFragment = new BookShelf();
        recommendFragment = new BookRecommend();
        classifyFragment = new BookClassify();

        fragmentList.add(shelfFragment);
        fragmentList.add(recommendFragment);
        fragmentList.add(classifyFragment);

        MyFragmentAdapter adapter = new MyFragmentAdapter(getFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
        //把TabLaout和ViewPager关联起来
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ClickMenu:
                PopMenu();
                break;

            case R.id.searc_chen:
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
        }


    }

    private void PopMenu() {
        PopupMenu menu = new PopupMenu(getContext(), imageView);
        menu.getMenuInflater().inflate(R.menu.toolbar_menu, menu.getMenu());
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.first:
                        startActivity(new Intent(getContext(), AlreadyHavedBookActivity.class));
                        break;
                    case R.id.second:
                        showDialog();
                        break;
                }
                return false;
            }
        });
        //展示弹出的菜单
        menu.show();
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("图书兑换");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "兑换码不正确", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", null);

        View popupView = LayoutInflater.from(getContext()).inflate(R.layout.popup_window, null);

        builder.setView(popupView, 0, 0, 0, 0);
        builder.show();
    }

}
