package com.dxy.medicaltime.sun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.sun.bean.Login;
import com.dxy.medicaltime.sun.utils.DBUserManager;
import com.mob.tools.utils.UIHandler;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private Platform platform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShareSDK.initSDK(this);

        setContentView(R.layout.activity_login);
    }
    public void weixin(View view){
        login(Wechat.NAME);
    }
    public void sina(View view){
        login(SinaWeibo.NAME);
    }
    public void QQ(View view){
        login(QQ.NAME);
    }

    public void login(String name) {
        platform = ShareSDK.getPlatform(this, name);
        platform.showUser(null);
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(final Platform platform, int i, final HashMap<String, Object> hashMap) {
                final Message message = new Message();
                message.what = 0;
                message.obj = hashMap;
                UIHandler.sendMessage(message, new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        if (msg.what == 0) {
                            String name = platform.getDb().getUserName();
                            String icon = platform.getDb().getUserIcon();
                            DBUserManager.getDbManager(LoginActivity.this).insert(new Login(name,icon));
                            Intent intent =new Intent();
                            intent.setAction("0729");
                            LoginActivity.this.sendBroadcast(intent);
                            finish();
                        }
                        return false;
                    }
                });

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(LoginActivity.this,"登陆失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(Platform platform, int i) {
            }
        });
    }

    public void goback(View view){
        finish();
    }
}
