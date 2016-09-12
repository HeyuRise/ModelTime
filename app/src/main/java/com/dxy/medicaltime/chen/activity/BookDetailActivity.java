package com.dxy.medicaltime.chen.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.chen.bean.TheOneBook;
import com.dxy.medicaltime.chen.bean.TheOneBookContent;
import com.dxy.medicaltime.fang.utils.ShareUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.text.DecimalFormat;

public class BookDetailActivity extends AppCompatActivity{
    String id;
    Toolbar toolbar;
    ActionBar actionBar;
    Button button1,button2;
    TextView TitleTextView,AuthorTextView,TotalTextView,NowPriceTextView,OldPriceTextView
            ,DetailContentTextView;
    //数据
    TheOneBookContent data;

//    Platform platform;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_book_detail);


        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        String url="http://i.dxy.cn/bbs/bbsapi/mobile?s=book_detail&appType=1&id="+id+"&ac=9432e53c-74f7-44f9-be13-15f6cc3cd4ba&vc=4.5.1&vs=4.4.2&mc=0000000038ff13b4ffffffffe9f0e5e8&hardName=R6007";
        data=new TheOneBookContent();
        toolbar= (Toolbar) findViewById(R.id.bookDetail_toolbar);
        toolbar.setTitle("书籍详情");
        setSupportActionBar(toolbar);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final SimpleDraweeView simpleDraweeView= (SimpleDraweeView) findViewById(R.id.bookDetail_img);
        button1= (Button) findViewById(R.id.bookDetail_Button1);
        button2= (Button) findViewById(R.id.bookDtail_Button2);
        TitleTextView= (TextView) findViewById(R.id.bookDetail_title);
        AuthorTextView= (TextView) findViewById(R.id.bookDetail_author);
        TotalTextView= (TextView) findViewById(R.id.bookDetail_total);
        NowPriceTextView= (TextView) findViewById(R.id.bookDetail_NowMoney);
        OldPriceTextView= (TextView) findViewById(R.id.bookDetail_OldMoney);
        DetailContentTextView= (TextView) findViewById(R.id.bookDetail_ContentThing);

        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json=response.body().string();
                Gson gson=new Gson();
                TheOneBook theOneBook=gson.fromJson(json,TheOneBook.class);
                data=theOneBook.getItem();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TitleTextView.setText(data.getTitle());
                        AuthorTextView.setText("作者:"+data.getAuthor());
                        TotalTextView.setText("大小:"+"...");
                        int a=Integer.parseInt(data.getCurrentPrice());
                        int b=Integer.parseInt(data.getIosPrice());
                        double a1=a/100;
                        double b1=b/100;
                        DecimalFormat d=new DecimalFormat("#0.00");
                        if (data.getCurrentPrice().equals("0")) {
                            NowPriceTextView.setText("价格:  免费 |");
                            button1.setText("   免费");
                            OldPriceTextView.setText(" ￥" + d.format(b1));
                            OldPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                        }else {
                            NowPriceTextView.setText("价格: ￥" + d.format(a1) + " |");
                            button1.setText("   ￥" + d.format(a1));
                            OldPriceTextView.setText(" ￥" + d.format(b1));
                            OldPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                        }
                            DetailContentTextView.setText(data.getDescription());

                        simpleDraweeView.setImageURI(data.getCover());
                    }
                });
            }
        });

        //点击免费试读按钮 下载书籍
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url2=data.getPreview();
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder()
                        .url(url2)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                    }
                });
            }
        });
    }

    //点击分享
    public void share(View view){
//        showshare();
        ShareUtils.share(this);
    }

    private void showshare() {
//        ShareSDK.initSDK(this);
//        OnekeyShare oks = new OnekeyShare();
//        //设置平台
//        //oks.setPlatform(name);
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//        oks.setTitle("标题");
//        oks.setTitleUrl("http://sharesdk.cn");
//        oks.setText("我是分享文本");
//        oks.setUrl("http://sharesdk.cn");
//        oks.setComment("我是测试评论文本");
//
//        oks.setSite(getString(R.string.app_name));
//
//        oks.setSiteUrl("http://sharesdk.cn");
//
//
//        oks.show(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
