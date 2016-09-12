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
import com.dxy.medicaltime.fang.bean.SecondSpecialDetailData;
import com.dxy.medicaltime.fang.utils.ShareUtils;
import com.dxy.medicaltime.sun.bean.DaoMaster;
import com.dxy.medicaltime.sun.bean.SpecialSubscribeData;
import com.dxy.medicaltime.sun.bean.SpecialSubscribeDataDao;
import com.dxy.medicaltime.sun.utils.DBManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.dxy.medicaltime.R.id.second_special_detail_subscribe_imgbtn;

/**
 * Created by Administrator on 2016/7/29.
 * 专题详情第二页
 */
public class SecondSpecialDetailActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ArrayList<SecondSpecialDetailData.MessageBean.ListBean> listBeen = new ArrayList<>();
    private MyAdapter adapter;
    private String id;
    private String cardId;
    private String cardTitle;
    private String img;
    private boolean isSave;
    private SpecialSubscribeDataDao specialSubscribeDataDao;
    private ImageButton subscribeBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.second_special_detail_activity_view);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");
        cardId=intent.getStringExtra("cardId");
        cardTitle=intent.getStringExtra("cardTitle");
        img=intent.getStringExtra("img");

        ListView listView = (ListView) findViewById(R.id.second_special_detail_list_view);
        TextView titleTv=(TextView)findViewById(R.id.second_special_detail_hearder_view_tv);
        subscribeBtn=(ImageButton)findViewById(R.id.second_special_detail_subscribe_imgbtn);

        titleTv.setText(title);
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        downLoad();

        specialSubscribeDataDao= DaoMaster.newDevSession(this, DBManager.DB_NAME).getSpecialSubscribeDataDao();
        List<SpecialSubscribeData> subscribeDatas=specialSubscribeDataDao.queryBuilder()
                .where(SpecialSubscribeDataDao.Properties.CardId.eq(cardId))
                .list();
        if(subscribeDatas!=null&&subscribeDatas.size()!=0) {
            isSave = true;
            subscribeBtn.setImageResource(R.drawable.news_topic_icon01_hold);
        }else{
            isSave=false;
            subscribeBtn.setImageResource(R.drawable.news_topic_icon01);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SecondSpecialDetailData.MessageBean.ListBean data=listBeen.get(position);
        Intent intent=new Intent(this,DetailsActivity.class);
        intent.putExtra("id",data.getId());
        intent.putExtra("title",data.getTitle());
        intent.putExtra("url",data.getUrl());
        intent.putExtra("img",data.getAppImg());
        intent.putExtra("time",data.getArticleDate());
        startActivity(intent);
    }

    public void onBtnClick(View view){
        switch(view.getId()){

            case R.id.second_special_detail_share_imgbtn:
                ShareUtils.share(this);
                break;

            case second_special_detail_subscribe_imgbtn:
                changeSaveStatu();
                break;

            case R.id.second_special_detail_back_imgbtn:
                finish();
                break;

        }
    }

    private void changeSaveStatu(){
        if(isSave){
            isSave=false;
            subscribeBtn.setImageResource(R.drawable.news_topic_icon01);
            specialSubscribeDataDao.deleteByKey(Long.valueOf(cardId));
            Toast.makeText(this, "已取消收藏", Toast.LENGTH_SHORT).show();
        }else{
            isSave = true;
            subscribeBtn.setImageResource(R.drawable.news_topic_icon01_hold);
            specialSubscribeDataDao.insert(new SpecialSubscribeData(Long.valueOf(cardId),cardTitle,img));
            Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
        }
    }

    public void downLoad() {
        String subUrl = "http://www.dxy.cn/webservices/special/articles?";
        String url = subUrl + "limit=2000" + "&username=" + Common.username + "&u=" + Common.u +
                "&mc=" + Common.mc + "&hardName=" + Common.hardName + "&ac=" + Common.ac + "&vc=" + Common.vc +
                "&vs=" + Common.vs + "&pge=1" + "&id=" + id;
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
                String json = response.body().string();
                SecondSpecialDetailData data = new Gson().fromJson(json, SecondSpecialDetailData.class);
                listBeen.addAll(data.getMessage().getList());
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
            return listBeen.size();
        }

        @Override
        public SecondSpecialDetailData.MessageBean.ListBean getItem(int position) {
            return listBeen.get(position);
        }

        @Override
        public long getItemId(int position) {
            return listBeen.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(SecondSpecialDetailActivity.this).inflate(R.layout.second_special_item_view, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            SecondSpecialDetailData.MessageBean.ListBean data = listBeen.get(position);
            holder.img.setImageURI(data.getAppImg());
            holder.titleTv.setText(data.getTitle());
            holder.shareTv.setText(data.getNumOfShared()+"分享");
            String qa = data.getVoteid();
            if (qa != null && !qa.equals("")){
                holder.timeTv.setBackgroundResource(R.drawable.second_special_detail_qa_bcg);
                holder.timeTv.setText("问答");
            }else{
                String time = data.getArticleDate();
                holder.timeTv.setText(time.substring(0, time.indexOf(" ")));
                holder.timeTv.setBackgroundColor(0x00000000);
            }
            return convertView;
        }

        private class ViewHolder {

            SimpleDraweeView img;
            TextView titleTv;
            TextView shareTv;
            TextView timeTv;

            protected ViewHolder(View convertView) {
                img = (SimpleDraweeView) convertView.findViewById(R.id.second_special_detail_img);
                titleTv = (TextView) convertView.findViewById(R.id.second_special_detail_title_tv);
                shareTv = (TextView) convertView.findViewById(R.id.second_special_detail_share_tv);
                timeTv = (TextView) convertView.findViewById(R.id.second_special_detail_time_tv);

            }

        }
    }

}
