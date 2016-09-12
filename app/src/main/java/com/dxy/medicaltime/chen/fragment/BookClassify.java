package com.dxy.medicaltime.chen.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dxy.medicaltime.R;
import com.dxy.medicaltime.chen.activity.TheOneBookClassified;
import com.dxy.medicaltime.chen.adapter.ClassifiedAdapter;
import com.dxy.medicaltime.chen.bean.Classified;
import com.dxy.medicaltime.chen.bean.ClassifiedItem;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/25.
 */
public class BookClassify extends Fragment implements AdapterView.OnItemClickListener{
    String url="http://d.dxy.cn/book/api/read?action=GetSubject&appType=1&ac=9432e53c-74f7-44f9-be13-15f6cc3cd4ba&vc=4.5.1&vs=4.4.2&mc=0000000038ff13b4ffffffffe9f0e5e8&hardName=R6007";
    ListView listView;
    //数据集合
    List<ClassifiedItem>list;
    ClassifiedAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;
        rootView=inflater.inflate(R.layout.fragment_classify, container, false);
        listView= (ListView) rootView.findViewById(R.id.classify_listView);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list=new ArrayList<>();
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
                Classified classified=gson.fromJson(json,Classified.class);
                list=classified.getItems();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter=new ClassifiedAdapter(list,getActivity());
                        listView.setAdapter(adapter);
                    }
                });
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (list.get(position).getCount().equals("0")){
            view.setClickable(false);
        }else {
            Intent intent=new Intent(getActivity(),TheOneBookClassified.class);
            intent.putExtra("name",list.get(position).getName());
            intent.putExtra("subjectId",list.get(position).getSubjectId());
            startActivity(intent);
        }
    }
}
