package com.dxy.medicaltime.fang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.constants.Common;
import com.dxy.medicaltime.sun.bean.DaoMaster;
import com.dxy.medicaltime.fang.bean.DiscussData;
import com.dxy.medicaltime.sun.bean.Login;
import com.dxy.medicaltime.sun.bean.LoginDao;
import com.dxy.medicaltime.sun.utils.DBManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.dxy.medicaltime.constants.Common.*;

/**
 * Created by Administrator on 2016/7/30.
 * 评论Activity
 */
public class DiscussActivity extends AppCompatActivity {

    private OkHttpClient client = new OkHttpClient();
    private ArrayList<DiscussData.MessageBean.ListBean> itemDatas = new ArrayList<>();
    private MyAdapter adapter;
    private EditText edt;
    private String id, title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.discuss_activity_view);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");

        ListView listView = (ListView) findViewById(R.id.discuss_activity_list_view);
        edt = (EditText) findViewById(R.id.discuss_activity_edt);

        adapter = new MyAdapter();
        TextView empteyView=(TextView)findViewById(R.id.empty_view);
        empteyView.setText("暂无评论");
        listView.setEmptyView(empteyView);
        listView.setAdapter(adapter);
        downLoad();
    }

    public void onBtnClick(View view){
        switch (view.getId()) {
            case R.id.discuss_acitivity_back:
                finish();
                break;

            case R.id.discuss_activity_submit_btn:
                String content = edt.getText().toString().trim();
                if (content.equals("")) {
                    Toast.makeText(this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    DiscussData.MessageBean.ListBean data = new DiscussData.MessageBean.ListBean();
                    LoginDao loginDao = DaoMaster.newDevSession(this, DBManager.DB_NAME).getLoginDao();
                    List<Login> logins = loginDao.queryBuilder()
                            .list();
                    if (logins == null || logins.size() == 0)
                        data.setHead("");
                    else
                        data.setHead(logins.get(0).getName());
                    data.setBody(content);
                    data.setUsername(Common.username);
                    addDiscuss(data);
                    edt.setText("");
                }
                break;
        }
    }

    private void addDiscuss(DiscussData.MessageBean.ListBean data) {
        itemDatas.add(data);
        adapter.notifyDataSetChanged();
        RequestBody requestBody = new FormBody.Builder()
                .add("identify", "dxy_article_" + id)
                .add("site", "medtime2")
                .add("title", title)
                .add("content", data.getBody())
                .add("username", Common.username)
                .add("token",Common.token)
                .add("u", u)
                .add("mc", mc)
                .add("ac", Common.ac)
                .add("vc", Common.vc)
                .add("vs", Common.vs)
                .build();
        Request request = new Request.Builder()
                .url("http://www.dxy.cn/webservices/comment/submit")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DiscussActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void downLoad() {
        String subUrl = "http://www.dxy.cn/webservices/comment/list?limit=2000&username=";
        String url = subUrl + Common.username + "&identify=dxy_article_" + id + "&u=" + Common.u + "&mc=" + Common.mc + "&hardName=" +
                hardName + "&ac=" + Common.ac + "&vc=" + Common.vc + "&vs=" + Common.vs + "&pge=1";
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                if(json==null||json.equals(""))
                    return;
                DiscussData data = new Gson().fromJson(json, DiscussData.class);
                if (data != null && data.getMessage().getList().size() != 0)
                    itemDatas.addAll(data.getMessage().getList());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return itemDatas.size();
        }

        @Override
        public DiscussData.MessageBean.ListBean getItem(int position) {
            return itemDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return itemDatas.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(DiscussActivity.this).inflate(R.layout.discuss_item_view, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            DiscussData.MessageBean.ListBean data = itemDatas.get(position);
            holder.img.setImageURI(data.getHead());
            holder.username.setText(data.getUsername());
            holder.content.setText(data.getBody());
            return convertView;
        }

        private class ViewHolder {
            SimpleDraweeView img;
            TextView username;
            TextView content;

            protected ViewHolder(View convertView) {
                img = (SimpleDraweeView) convertView.findViewById(R.id.discuss_item_img);
                username = (TextView) convertView.findViewById(R.id.discuss_item_username);
                content = (TextView) convertView.findViewById(R.id.discuss_item_content);
            }
        }
    }
}
