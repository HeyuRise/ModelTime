package com.dxy.medicaltime.fang.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.chen.activity.ChenMainActivity;
import com.dxy.medicaltime.fang.fragment.GuideFragment;
import com.dxy.medicaltime.fang.fragment.InfoFragment;
import com.dxy.medicaltime.sun.fragment.OpenClassActivity;
import com.dxy.medicaltime.sun.fragment.TextHeyuMyActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    public static float density;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        density=getResources().getDisplayMetrics().density;

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.bottom_layout);
        radioGroup.setOnCheckedChangeListener(this);

        // 添加Fragment
        fragments.add(new InfoFragment());
        fragments.add(new GuideFragment());
        fragments.add(new ChenMainActivity());
        fragments.add(new OpenClassActivity());
        fragments.add(new TextHeyuMyActivity());

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            fragmentTransaction.add(R.id.fragments_container, fragments.get(i));
            fragmentTransaction.hide(fragments.get(i));
        }
        fragmentTransaction.show(fragments.get(0));
        fragmentTransaction.commit();
    }

    /**
     * 点击底部导航栏，切换Fragment
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {

            case R.id.info_btn:
                changFragment(0);
                break;

            case R.id.fingerpost_btn:
                changFragment(1);
                break;

            case R.id.books_btn:
                changFragment(2);
                break;

            case R.id.public_class_btn:
                changFragment(3);
                break;

            case R.id.personal_center_btn:
                changFragment(4);
                break;

        }
    }

    /**
     * 切换到指定的Fragment
     *
     * @param position Fragment的索引
     */
    private void changFragment(int position) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if (i == position)
                fragmentTransaction.show(fragments.get(i));
            else
                fragmentTransaction.hide(fragments.get(i));
        }
        fragmentTransaction.commit();
    }


    private long time;
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - time < 1500) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            time = System.currentTimeMillis();
        }
    }
}
