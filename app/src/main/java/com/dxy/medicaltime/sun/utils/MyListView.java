package com.dxy.medicaltime.sun.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by dell-pc on 2016/7/27.
 */
public class MyListView extends ListView {
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int h =MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, h);
    }
}
