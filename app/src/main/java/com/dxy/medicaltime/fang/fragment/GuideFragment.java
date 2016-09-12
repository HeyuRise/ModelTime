package com.dxy.medicaltime.fang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.chen.activity.SearchActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/27.
 * 指南
 */
public class GuideFragment extends Fragment implements View.OnClickListener{

    private ArrayList<Fragment> fragments=new ArrayList<>();
    private boolean isFirst=true;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.guide_fragment_view,container,false);
        ViewPager viewPager=(ViewPager)rootView.findViewById(R.id.guide_view_pager);
        TabLayout tabLayout=(TabLayout)rootView.findViewById(R.id.guide_tablayout);
        rootView.findViewById(R.id.guide_search_btn).setOnClickListener(this);

        if(isFirst) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
            fragments.add(new NewestGuideFragment());
            fragments.add(new GuideClassifyFragment());
            isFirst=false;
        }

        viewPager.setAdapter(new MyAdapter(getFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), SearchActivity.class));
    }

    private class MyAdapter extends FragmentPagerAdapter{

        private String[] titles={"最新指南","指南分类"};

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
