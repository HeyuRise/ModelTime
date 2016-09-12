package com.dxy.medicaltime.fang.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.dxy.medicaltime.sun.activity.SectionSubActivity;
import com.dxy.medicaltime.sun.bean.SectionHeyu;
import com.dxy.medicaltime.sun.utils.DBManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/25.
 * 资讯
 */
public class InfoFragment extends Fragment implements View.OnClickListener {

    private ArrayList<String> tags = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private MyAdapter adapter;
    private MyBroadcastReceiver receiver;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.info_fragment_view, container, false);
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.info_tablayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabTextColors(0xffaaaaaa, 0xffffffff);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.info_view_pager);
        rootView.findViewById(R.id.info_add_tv).setOnClickListener(this);
        rootView.findViewById(R.id.info_search_btn).setOnClickListener(this);
        adapter = new MyAdapter(getFragmentManager());
        refresh();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        IntentFilter filter=new IntentFilter("info.fragment.refresh");
        receiver=new MyBroadcastReceiver();
        getActivity().registerReceiver(receiver,filter);
        return rootView;
    }

    @Override
    public void onClick(View v) {

        Intent intent=null;
        switch (v.getId()) {
            case R.id.info_add_tv:
                intent = new Intent(getContext(), SectionSubActivity.class);
                break;

            case R.id.info_search_btn:
                intent=new Intent(getContext(), SearchActivity.class);
                break;
        }
        startActivity(intent);
    }

    /**
     * 刷新数据
     */
    public void refresh() {

        tags.clear();
        fragments.clear();

        tags.add("最新资讯");
        tags.add("专题");
        fragments.add(new NewestInfoFragment());
        fragments.add(new SpecialFragment());

        List<SectionHeyu> titleDatas = DBManager.getDbManager(getContext()).queryAll();
        for (int i = 0; i < titleDatas.size(); i++) {
            tags.add(titleDatas.get(i).getName());
            Bundle bundle = new Bundle();
            bundle.putString("dls", titleDatas.get(i).getId() + "");
            Fragment fragment = new InfoCommonFragment();
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        adapter.notifyDataSetChanged();
    }

    /**
     * ViewPager的适配器
     */
    private class MyAdapter extends FragmentPagerAdapter {

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
            return tags.get(position);
        }
    }

    /**
     * 接受到广播刷新数据
     */
    private class MyBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            refresh();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }
}
