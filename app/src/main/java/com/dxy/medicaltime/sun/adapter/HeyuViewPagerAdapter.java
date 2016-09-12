package com.dxy.medicaltime.sun.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by bluesky on 16/7/13.
 */
public class HeyuViewPagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragments;
    List<String> list;

    public HeyuViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> list) {
        super(fm);
        this.fragments = fragments;
        this.list =list;
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
        return list.get(position);
    }
}
