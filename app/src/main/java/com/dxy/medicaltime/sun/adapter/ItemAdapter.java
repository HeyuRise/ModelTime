package com.dxy.medicaltime.sun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dxy.medicaltime.R;
import com.dxy.medicaltime.sun.bean.OpenClassItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dell-pc on 2016/7/26.
 */
public class ItemAdapter extends BaseAdapter {
    private ArrayList<OpenClassItem.MessageBean.ListBean> data;
    private Context context;
    private LayoutInflater inflater;

    public ItemAdapter(Context context, ArrayList<OpenClassItem.MessageBean.ListBean> data) {
        this.data = data;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
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
            convertView=inflater.inflate(R.layout.heyu_little_item,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(data.get(position).getAppImg()).into(viewHolder.imageView);
        viewHolder.hospital.setText(data.get(position).getOpenclass().getProfessorHospital()+"  "+data.get(position).getOpenclass().getProfessorSection()+"    "+data.get(position).getOpenclass().getProfessor());
        viewHolder.title.setText(data.get(position).getTitle());
        return convertView;
    }
    private class ViewHolder{
        ImageView imageView;
        TextView title,hospital;

        public ViewHolder(View convertView) {
            imageView= (ImageView) convertView.findViewById(R.id.heyu_little_item_img);
            title= (TextView) convertView.findViewById(R.id.heyu_little_item_title);
            hospital= (TextView) convertView.findViewById(R.id.heyu_little_item_hospital);
        }

    }
}
