package com.dxy.medicaltime.chen.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.dxy.medicaltime.R;

/**
 * Created by Administrator on 2016/7/31.
 * 已购图书
 */
public class AlreadyHavedBookActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.already_haved_activity_view);
    }

    public void onBtnClick(View view){
        finish();
    }

}
