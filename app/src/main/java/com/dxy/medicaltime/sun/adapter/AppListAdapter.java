package com.dxy.medicaltime.sun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dxy.medicaltime.R;
import com.dxy.medicaltime.sun.bean.AppList;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by dell-pc on 2016/7/29.
 */
public class AppListAdapter extends BaseAdapter implements View.OnClickListener{
    private ArrayList<AppList.DataBean> data;
    private LayoutInflater inflater;
    private download download;
    public AppListAdapter(Context context, ArrayList<AppList.DataBean> data) {
        this.data = data;
        Fresco.initialize(context);
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
        ViewhHolder viewhHolder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.app_item,parent,false);
            viewhHolder=new ViewhHolder(convertView);
            convertView.setTag(viewhHolder);
        }else {
            viewhHolder= (ViewhHolder) convertView.getTag();
        }
        viewhHolder.simpleDraweeView.setImageURI(data.get(position).getIconPath());
        viewhHolder.title.setText(data.get(position).getName());
        viewhHolder.text.setText(data.get(position).getDesc());
        viewhHolder.img.setOnClickListener(this);
        viewhHolder.img.setTag(position);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        download.getPosition((Integer) v.getTag());
    }

    class ViewhHolder{
        SimpleDraweeView simpleDraweeView;
        TextView title,text;
        ImageView img;

        public ViewhHolder(View convertView) {
            simpleDraweeView= (SimpleDraweeView) convertView.findViewById(R.id.app_item_img);
            title= (TextView) convertView.findViewById(R.id.app_item_title);
            text= (TextView) convertView.findViewById(R.id.app_item_text);
            img= (ImageView) convertView.findViewById(R.id.app_item_download);
        }
    }
    public interface download{
        void getPosition(int position);
    }

    public void setInterface(download download){
        this.download=download;
    }
}
