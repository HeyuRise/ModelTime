package com.dxy.medicaltime.chen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.dxy.medicaltime.R;

/**
 * Created by Administrator on 2016/7/25.
 */
public class BookShelf extends Fragment{
    GridView gridView;
    ImageView Empty_img;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;
        rootView=inflater.inflate(R.layout.fragment_shelf, container, false);
        gridView= (GridView) rootView.findViewById(R.id.shelfGridView);
        Empty_img= (ImageView) rootView.findViewById(R.id.empty_img);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        gridView.setEmptyView(Empty_img);
    }
}
