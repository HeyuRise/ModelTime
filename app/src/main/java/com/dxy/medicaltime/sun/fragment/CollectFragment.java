package com.dxy.medicaltime.sun.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.dxy.medicaltime.R;
import com.dxy.medicaltime.sun.interfac.OnDrawerStateChangeListener;

public class CollectFragment extends Fragment implements View.OnClickListener{
    private ImageView goBack,goSearch;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private OnDrawerStateChangeListener listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_collect, container, false);
        init(view);

        return view;
    }
    public void init(View view){
        listener= (OnDrawerStateChangeListener) getActivity();
        goBack= (ImageView) view.findViewById(R.id.collect_back);
        goSearch= (ImageView) view.findViewById(R.id.collect_search);
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.collect_swipeRefresh);
        listView= (ListView) view.findViewById(R.id.collect_listView);
        goSearch.setOnClickListener(this);
        goBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.collect_back:
                getActivity().finish();
                break;
            case R.id.collect_search:
                listener.onDrawerOpen();
                break;
        }
    }
}
