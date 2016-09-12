package com.dxy.medicaltime.sun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dxy.medicaltime.R;
import com.dxy.medicaltime.sun.bean.CollectionData;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by dell-pc on 2016/7/30.
 */
public class CollectionAdapter extends BaseAdapter{
    private ArrayList<CollectionData> data;
    private LayoutInflater inflater;

    public CollectionAdapter(ArrayList<CollectionData> data, Context context) {
        Fresco.initialize(context);
        this.data = data;
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
            convertView=inflater.inflate(R.layout.collection_item,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.simpleDraweeView.setImageURI(data.get(position).getImg());
        viewHolder.title.setText(data.get(position).getTitle());
        viewHolder.date.setText(data.get(position).getTime());
        return convertView;
    }
    class ViewHolder{
        SimpleDraweeView simpleDraweeView;
        TextView title,date;

        public ViewHolder(View convertView) {
            simpleDraweeView= (SimpleDraweeView) convertView.findViewById(R.id.collection_item_img);
            title= (TextView) convertView.findViewById(R.id.collection_item_title);
            date= (TextView) convertView.findViewById(R.id.collection_item_hospital);
        }
    }
}
