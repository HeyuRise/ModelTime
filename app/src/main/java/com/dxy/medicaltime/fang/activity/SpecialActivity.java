package com.dxy.medicaltime.fang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.fang.fragment.SpecialFragment;

/**
 * Created by Administrator on 2016/7/28.
 * 专题activity
 */
public class SpecialActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.special_activity_view);
        getSupportFragmentManager().beginTransaction().add(R.id.special_fragment_layout,
                new SpecialFragment()).commit();
    }

    public void onBtnClick(View view){
        finish();
    }

}
