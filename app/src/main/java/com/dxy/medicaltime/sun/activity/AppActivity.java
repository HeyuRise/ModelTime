package com.dxy.medicaltime.sun.activity;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.*;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.sun.adapter.AppListAdapter;
import com.dxy.medicaltime.sun.bean.AppList;
import com.dxy.medicaltime.sun.utils.FileUtils;
import com.dxy.medicaltime.sun.utils.IOUtils;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AppActivity extends AppCompatActivity {
    private ListView listView;
    private String url="http://drugs.dxy.cn/api/recommend?type=3&productType=28&u=%E4%BD%A0%E4%B8%BA%E6%88%91%E7%9D%80%E8%BF%B7918&deviceName=&mc=fffffffff6ef993bffff08623803f0de&hardName=HTC%20X720d&ac=9432e53c-74f7-44f9-be13-15f6cc3cd4ba&bv=2013&vc=4.5.1&vs=4.4.2";
    private ArrayList<AppList.DataBean> data;
    private String downurl;
    private String appName;
    MyRecevier myRecevier;
    NotificationCompat.Builder builder;
    NotificationManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        listView= (ListView) findViewById(R.id.app_list);
        myRecevier = new MyRecevier();
        IntentFilter filter = new IntentFilter();
        filter.addAction("0711");
        registerReceiver(myRecevier, filter);
        initData();
    }
    public void initData(){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json=response.body().string();
                Gson gson=new Gson();
                AppList appList=gson.fromJson(json,AppList.class);
                data= (ArrayList<AppList.DataBean>) appList.getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initUI();
                    }
                });
            }
        });
    }
    public void initUI(){
        AppListAdapter adapter=new AppListAdapter(AppActivity.this,data);
        adapter.setInterface(new AppListAdapter.download() {
            @Override
            public void getPosition(final int position) {
                appName=data.get(position).getName();
                dialog(position);

            }
        });
        listView.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myRecevier);
    }

    class MyRecevier extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("0711")) {
                int percent = intent.getIntExtra("percent", 0);
                String path = intent.getStringExtra("path");
                Log.e("====", "==percent1111===" + percent);
                if (percent==100){
                    builder.mContentText = "下载完毕，点击即可安装程序";
                    Intent intent1 =new Intent();
                    //安装apk
                    intent1.setAction(Intent.ACTION_VIEW);
                    intent1.setDataAndType(Uri.fromFile(new File(path)),"application/vnd.android.package-archive");

                    PendingIntent pendingIntent =PendingIntent.getActivity(
                            AppActivity.this,100,intent1,PendingIntent.FLAG_ONE_SHOT
                    );
                    builder.setContentIntent(pendingIntent);
                }else{
                    builder.mContentText = "下载进度为"+percent+"%";
                }
                builder.setProgress(100,percent,false);

                manager.notify(0,builder.build());

            }
        }
    }

    public void initNotification() {
        /**
         * 1.获取通知管理类
         * 2.配置通知
         * 3.notify发送通知
         */
        manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        builder = new NotificationCompat.Builder(this);

        builder.mContentTitle =appName+ "下载中";
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.mContentText = "内容";
        builder.setTicker("开始下载");
        builder.setWhen(System.currentTimeMillis());
        builder.setProgress(100, 0, false);

        manager.notify(0, builder.build());

    }

    public void goback(View view){
        finish();
    }

    public void dialog(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(AppActivity.this);
        builder.setTitle("提示信息");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("是否下载"+appName);
        builder.setCancelable(true);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                initNotification();
                downurl=data.get(position).getDownloadUrl();
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().url(downurl).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        long total = response.body().contentLength();
                        InputStream is = response.body().byteStream();
                        File file = FileUtils.getStorageFile(data.get(position).getName());
                        IOUtils.getApkByNetWork(AppActivity.this, is, file, total);

                    }
                });
            }
        });
        // 设置消极按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
