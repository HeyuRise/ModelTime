package com.dxy.medicaltime.sun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.dxy.medicaltime.R;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }
    public void goback(View view){
        finish();
    }

    public void clear(View view) throws InterruptedException {
        Toast.makeText(this,"正在清理",Toast.LENGTH_SHORT).show();
        Thread.sleep(2000);
        Toast.makeText(this,"已清理",Toast.LENGTH_SHORT).show();
    }

    public void aboutUs(View view){
        Intent intent=new Intent(this,AboutActivity.class);
        startActivity(intent);
    }

    public void appUpdate(View view){
        Toast.makeText(this,"已是最新版本",Toast.LENGTH_SHORT).show();
    }
}
