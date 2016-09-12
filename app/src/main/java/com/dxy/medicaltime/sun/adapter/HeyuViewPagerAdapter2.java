package com.dxy.medicaltime.sun.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dell-pc on 2016/7/27.
 */
public class HeyuViewPagerAdapter2 extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentList;
    public HeyuViewPagerAdapter2(FragmentManager fm,ArrayList<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
