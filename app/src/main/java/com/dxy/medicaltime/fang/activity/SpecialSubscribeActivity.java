package com.dxy.medicaltime.fang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.fang.adapter.MyAdapter;
import com.dxy.medicaltime.fang.bean.SpecialTitleData;
import com.dxy.medicaltime.sun.bean.DaoMaster;
import com.dxy.medicaltime.sun.bean.SpecialSubscribeData;
import com.dxy.medicaltime.sun.bean.SpecialSubscribeDataDao;
import com.dxy.medicaltime.sun.utils.DBManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/30.
 * 展示专题订阅的Activity
 */
public class SpecialSubscribeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ArrayList<SpecialTitleData.MessageBean> datas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_subscribe_view);

        SpecialSubscribeDataDao specialSubscribeDataDao = DaoMaster.newDevSession(this, DBManager.DB_NAME)
                .getSpecialSubscribeDataDao();
        List<SpecialSubscribeData> subscribeDatas = specialSubscribeDataDao.queryBuilder()
                .list();

        if (subscribeDatas != null && subscribeDatas.size() != 0) {
            SpecialTitleData.MessageBean data;
            SpecialSubscribeData subscribeData;
            for (int i = 0; i < subscribeDatas.size(); i++) {
                subscribeData = subscribeDatas.get(i);
                data = new SpecialTitleData.MessageBean();
                data.setId((int) subscribeData.getCardId());
                data.setIconUrl(subscribeData.getImg());
                data.setName(subscribeData.getCardTitle());
                datas.add(data);
            }
            MyAdapter adapter=new MyAdapter(datas,this);
            GridView gridView = (GridView) findViewById(R.id.special_subscribe_grid_view);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(this);
            findViewById(R.id.empty_view).setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SpecialTitleData.MessageBean itemdata = datas.get(position);
        Intent intent = new Intent(this, FirstSpecialDetailActivity.class);
        intent.putExtra("id", itemdata.getId() + "");
        intent.putExtra("img",itemdata.getIconUrl());
        intent.putExtra("title", itemdata.getName());
        startActivity(intent);
    }

    public void onBtnClick(View view){
        finish();
    }

}
