package com.dxy.medicaltime.sun.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dxy.medicaltime.R;
import com.dxy.medicaltime.sun.interfac.OnDrawerStateChangeListener;

public class CollectSearchFragment extends Fragment implements View.OnClickListener{
    private OnDrawerStateChangeListener listener;
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_collect_search, container, false);
        listener= (OnDrawerStateChangeListener) getActivity();
        imageView= (ImageView) view.findViewById(R.id.collect_search_back);
        imageView.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        listener.onDrawerClose();
    }
}
