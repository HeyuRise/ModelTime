package com.dxy.medicaltime.sun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dxy.medicaltime.R;
import com.dxy.medicaltime.sun.bean.Login;
import com.dxy.medicaltime.sun.utils.DBUserManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell-pc on 2016/7/30.
 */
public class FeedBackAdapter extends BaseAdapter {
    private ArrayList<String> data;
    private LayoutInflater inflater;
    private List<Login> icon;

    public FeedBackAdapter(Context context, ArrayList<String> data) {
        Fresco.initialize(context);
        this.data = data;
        icon= DBUserManager.getDbManager(context).queryAll();
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.feed_back_my_item,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(data.get(position));
        viewHolder.simpleDraweeView.setImageURI(icon.get(0).getUrl());
        return convertView;
    }
    class ViewHolder{
        TextView textView;
        SimpleDraweeView simpleDraweeView;

        public ViewHolder(View convertView) {
            textView= (TextView) convertView.findViewById(R.id.feed_back_my_text);
            simpleDraweeView = (SimpleDraweeView) convertView.findViewById(R.id.feed_back_my_icon);
        }
    }
}
