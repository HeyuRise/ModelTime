package com.dxy.medicaltime.sun.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dxy.medicaltime.R;
import com.dxy.medicaltime.fang.utils.ShareUtils;

public class AccountActivity extends AppCompatActivity {
    private String url="https://auth.dxy.cn/accounts/activate/info?&service=http%3A%2F%2Fi.dxy.cn%2Fprofile%2F+++++918";
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        init();
    }

    public void init(){
        webView= (WebView) findViewById(R.id.account_webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.loadUrl(url);
    }

    public void share(View view){
        ShareUtils.share(this);
    }

    public void goback(View view){
        finish();
    }
}
