package com.dxy.medicaltime.chen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dxy.medicaltime.R;
import com.dxy.medicaltime.chen.bean.ClassifiedItem;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

/**
 * Created by Administrator on 2016/7/26.
 */
public class ClassifiedAdapter extends BaseAdapter{
    List<ClassifiedItem>list;
    LayoutInflater inflater;
    Context context;

    public ClassifiedAdapter(List<ClassifiedItem> list, Context context) {
        Fresco.initialize(context);
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
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
        ViewHolder holder;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.classfied_item,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        if (list.get(position).getCount().equals("0")){
            holder.textView1.setText(list.get(position).getName());
            holder.textView2.setText("");
            holder.textView1.setTextSize(23);
            holder.imageView.setImageResource(0);
        }else {
            holder.textView1.setText(list.get(position).getName());
            holder.textView1.setTextSize(18);
            holder.textView2.setText(list.get(position).getCount());
            holder.imageView.setImageResource(R.drawable.ebook_listicon01);
        }
        return convertView;
    }
    class ViewHolder{
        TextView textView1;
        TextView textView2;
        ImageView imageView;
        public ViewHolder(View convertView) {
            textView1= (TextView) convertView.findViewById(R.id.classified_name);
            textView2=(TextView) convertView.findViewById(R.id.classified_count);
            imageView= (ImageView) convertView.findViewById(R.id.classified_img);
        }
    }
}
