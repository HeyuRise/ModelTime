package com.dxy.medicaltime.fang.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.fang.activity.SpecialActivity;
import com.dxy.medicaltime.fang.activity.SpecialSubscribeActivity;
import com.dxy.medicaltime.fang.activity.TaskCenterActivity;
import com.dxy.medicaltime.sun.activity.QuestionAnswerActivity;

/**
 * Created by Administrator on 2016/7/25.
 * 最新资讯的Fragment
 */
public class NewestInfoFragment extends InfoCommonFragment implements View.OnClickListener {

    @Override
    public String getUrl() {
        String subUrl = "http://www.dxy.cn/webservices/article/latest/v3.3?version=1&limit=20&ac=9432e53c-74f7-44f9-be13-15f6cc3cd4ba&vc=4.5.1&vs=4.4.2&mc=ffffffffa63b8598930190c405753397&hardName=SCH-i889";
        String url = subUrl + "&pge=";
        return url;
    }

    @Override
    public View addView() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.newestinfo_fragment_btn_layout, null);
        rootView.findViewById(R.id.task_center_imgbtn).setOnClickListener(this);
        rootView.findViewById(R.id.special_imgbtn).setOnClickListener(this);
        rootView.findViewById(R.id.qa_imgbtn).setOnClickListener(this);
        rootView.findViewById(R.id.disease_subscribe_imgbtn).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()) {
            case R.id.task_center_imgbtn:
                intent = new Intent(getContext(), TaskCenterActivity.class);
                break;

            case R.id.special_imgbtn:
                intent=new Intent(getContext(), SpecialActivity.class);
                break;

            case R.id.qa_imgbtn:
                intent=new Intent(getContext(), QuestionAnswerActivity.class);
                break;

            case R.id.disease_subscribe_imgbtn:
                intent=new Intent(getContext(), SpecialSubscribeActivity.class);
                break;
        }

        if(intent!=null){
            startActivity(intent);
        }
    }
}
