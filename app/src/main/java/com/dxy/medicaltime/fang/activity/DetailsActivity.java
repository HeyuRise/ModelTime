package com.dxy.medicaltime.fang.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.fang.utils.ShareUtils;
import com.dxy.medicaltime.sun.bean.CollectionData;
import com.dxy.medicaltime.sun.bean.CollectionDataDao;
import com.dxy.medicaltime.sun.bean.DaoMaster;
import com.dxy.medicaltime.sun.utils.DBManager;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 * 详情页，这是一个WebView
 */
public class DetailsActivity extends AppCompatActivity {

    private boolean isCollection;
    private CollectionDataDao collectionDataDao;
    private SharedPreferences.Editor textSizeConfigEditor;
    private int id;
    private String title;
    private String url;
    private String img;
    private String time;
    private ImageView imgBtn;
    private WebView webView;
    private int textSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity_view);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        title = intent.getStringExtra("title");
        url = intent.getStringExtra("url");
        img = intent.getStringExtra("img");
        time = intent.getStringExtra("time");

        TextView shareCountTv = (TextView) findViewById(R.id.details_activity_share_coint_tv);
        shareCountTv.setText(intent.getStringExtra("shareCount"));

        webView = (WebView) findViewById(R.id.details_webview);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        SharedPreferences sharedPreferences = getSharedPreferences("TextSize", Context.MODE_PRIVATE);
        textSizeConfigEditor = sharedPreferences.edit();
        textSize = sharedPreferences.getInt("size", 1);
        changeTextSize(textSize);

        TextView titleTv = (TextView) findViewById(R.id.details_title_tv);
        titleTv.setText(title);

        imgBtn = (ImageView) findViewById(R.id.details_collection_imgbtn);

        collectionDataDao = DaoMaster.newDevSession(this, DBManager.DB_NAME).getCollectionDataDao();
        List<CollectionData> datas = collectionDataDao.queryBuilder()
                .where(CollectionDataDao.Properties.Id.eq(id))
                .list();
        if (datas.size() == 0) {
            isCollection = false;
            imgBtn.setImageResource(R.drawable.news_detail_icon01);
        } else {
            isCollection = true;
            imgBtn.setImageResource(R.drawable.news_detail_icon01_hold);
        }
    }

    public void onBtnClick(View view) {

        switch (view.getId()) {
            case R.id.details_back_imgbtn:
                finish();
                break;
            case R.id.details_share_imgbtn:
                ShareUtils.share(this);
                break;

            case R.id.details_collection_imgbtn:
                changeCollection();
                break;

            case R.id.details_text_size:
                showDialog();
                break;

            case R.id.details_discuss_imgbtn:
                Intent intent = new Intent(this, DiscussActivity.class);
                intent.putExtra("id", id + "");
                intent.putExtra("title", title);
                startActivity(intent);
                break;
        }

    }

    /**
     * 显示改变字体的对话框
     */
    private void showDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("修改字体大小");
        builder.setSingleChoiceItems(new String[]{"小", "中", "大"}, textSize, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textSize = which;
                saveTextSizeConfig(which);
            }
        });
        builder.setPositiveButton("确定", null);
        builder.show();
    }

    private void changeCollection() {
        if (isCollection) {
            collectionDataDao.deleteByKey((long) id);
            isCollection = false;
            imgBtn.setImageResource(R.drawable.news_detail_icon01);
            Toast.makeText(this, "已取消收藏", Toast.LENGTH_SHORT).show();
        } else {
            collectionDataDao.insert(new CollectionData(id, title, url, img, time));
            isCollection = true;
            imgBtn.setImageResource(R.drawable.news_detail_icon01_hold);
            Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 改变WebView的字体大小
     */
    public void changeTextSize(int size) {
        WebSettings settings = webView.getSettings();
        if (size == 0)
            settings.setTextSize(WebSettings.TextSize.SMALLER);
        if (size == 1)
            settings.setTextSize(WebSettings.TextSize.NORMAL);
        if (size == 2)
            settings.setTextSize(WebSettings.TextSize.LARGER);
        webView.loadUrl(url);
    }

    /**
     * 保存WebView的字体大小设置
     */
    public void saveTextSizeConfig(int size) {
        textSizeConfigEditor.putInt("size", size);
        textSizeConfigEditor.commit();
        changeTextSize(size);
    }

}
