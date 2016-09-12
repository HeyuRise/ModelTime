package com.dxy.medicaltime.fang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.constants.Common;
import com.dxy.medicaltime.fang.activity.GuideDetailActivity;
import com.dxy.medicaltime.fang.bean.GuideClassifyData;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 * 指南分类
 */
public class GuideClassifyFragment extends Fragment implements AdapterView.OnItemClickListener{

    ArrayList<ItemData> itemDatas = new ArrayList<>();
    private ListView listView;
    private OkHttpClient client = new OkHttpClient();
    private MyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listView = (ListView) inflater.inflate(R.layout.common_list_view, container, false);
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        downLoad();
        return listView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ItemData data=itemDatas.get(position);
        if(data.getId()!=-1){
            Intent intent=new Intent(getContext(), GuideDetailActivity.class);
            intent.putExtra("title",data.getTitle());
            intent.putExtra("id",data.getId()+"");
            startActivity(intent);
        }
    }

    private void downLoad() {
        Request request = new Request.Builder()
                .url(getUrl())
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                GuideClassifyData data = new Gson().fromJson(json, GuideClassifyData.class);
                List<GuideClassifyData.MessageBean.ChildrenBean> childrenBeanList = data.getMessage().get(0).getChildren();
                for (int i = 0; i < childrenBeanList.size(); i++) {
                    ItemData itemData = new ItemData(-1, childrenBeanList.get(i).getName());
                    itemDatas.add(itemData);
                    List<GuideClassifyData.MessageBean.ChildrenBean.ChildrenBean1> childrenBean1List = childrenBeanList.get(i).getChildren();
                    for (int j = 0; j < childrenBean1List.size(); j++) {
                        itemData = new ItemData(childrenBean1List.get(j).getId(), childrenBean1List.get(j).getName());
                        itemDatas.add(itemData);
                    }
                }
                getActivity().runOnUiThread(new Runnable() {
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
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            int type = 1;
            if (itemDatas.get(position).getId() == -1)
                type = -1;
            return type;
        }

        @Override
        public int getCount() {
            return itemDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return itemDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return itemDatas.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder1 holder1 = null;
            ViewHolder2 holder2 = null;
            if (convertView == null) {
                if (getItemViewType(position) == -1) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.guide_class_title1, parent, false);
                    holder1 = new ViewHolder1(convertView);
                    convertView.setTag(holder1);
                } else {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.guide_class_title2, parent, false);
                    holder2 = new ViewHolder2(convertView);
                    convertView.setTag(holder2);
                }
            } else {
                if (getItemViewType(position) == -1) {
                    holder1 = (ViewHolder1) convertView.getTag();
                } else {
                    holder2 = (ViewHolder2) convertView.getTag();
                }
            }

            if (getItemViewType(position) == -1) {
                holder1.textView.setText(itemDatas.get(position).getTitle());
            } else {
                holder2.textView.setText(itemDatas.get(position).getTitle());
            }
            return convertView;
        }

        private class ViewHolder1 {
            TextView textView;

            protected ViewHolder1(View convertView) {
                textView = (TextView) convertView.findViewById(R.id.guide_class_title1);
            }
        }

        private class ViewHolder2 {
            TextView textView;

            protected ViewHolder2(View convertView) {
                textView = (TextView) convertView.findViewById(R.id.guide_class_title2);
            }
        }
    }

    private String getUrl() {
        String subUrl = "http://www.dxy.cn/webservices/app-departmentV2?";
        String url = subUrl + "username=" + Common.username + "&mc=" + Common.mc + "&hardName=" + Common.hardName + "&vc=" + Common.vc + "&vs=" + Common.vs;
        return url;
    }

    private class ItemData {
        private int id = -1;
        private String title;

        protected ItemData(int id, String title) {
            this.id = id;
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}
