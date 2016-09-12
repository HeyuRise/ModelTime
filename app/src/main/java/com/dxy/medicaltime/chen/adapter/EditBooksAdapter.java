package com.dxy.medicaltime.chen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dxy.medicaltime.R;
import com.dxy.medicaltime.chen.bean.NewBook;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/26.
 */
public class EditBooksAdapter extends BaseAdapter{
    List<NewBook>list;
    LayoutInflater inflater;
    Context context;

    public EditBooksAdapter(List<NewBook> list, Context context) {
        Fresco.initialize(context);
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 6;
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
            convertView=inflater.inflate(R.layout.editandpop_item,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.textView1.setText(list.get(position).getTitle());
        holder.textView2.setText(list.get(position).getAuthor());
        holder.simpleDraweeView.setImageURI(list.get(position).getCover());
        return convertView;
    }
    class ViewHolder{
        SimpleDraweeView simpleDraweeView;
        TextView textView1;
        TextView textView2;

        public ViewHolder(View convertView) {
            simpleDraweeView= (SimpleDraweeView) convertView.findViewById(R.id.inflate_recommend_img);
            textView1= (TextView) convertView.findViewById(R.id.title);
            textView2= (TextView) convertView.findViewById(R.id.author);
        }
    }
}
