package com.dxy.medicaltime.fang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.constants.Common;
import com.dxy.medicaltime.fang.utils.ShareUtils;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Administrator on 2016/7/28.
 * 任务中心
 */
public class TaskCenterActivity extends AppCompatActivity {

    private TextView desTv, inviteCodeTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_center_activity_view);

        desTv = (TextView) findViewById(R.id.task_cneter_des);
        inviteCodeTv = (TextView) findViewById(R.id.invite_code_tv);
        downLoad();
    }

    public void onBtnClick(View view) {
        if (view.getId() == R.id.task_center_share_btn) {
            ShareUtils.share(this);
        } else {
            finish();
        }
    }

    private void downLoad() {
        String subUrl = "http://drugs.dxy.cn/invitecenter/api/get_invite_code?";
        String url = subUrl + "u=" + Common.u + "&mc=" + Common.mc + "&hardName=" + Common.hardName +
                "&ac=" + Common.ac + "&bv=" + Common.ac + "&vc=" + Common.vc +
                "&vs=" + Common.vs + "&token=" + Common.token;
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String des = jsonObject.getString("invite_desc");
                            desTv.setText(des);
                            String inviteCode = jsonObject.getString("inviteCode");
                            inviteCodeTv.setText(inviteCode);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

}
