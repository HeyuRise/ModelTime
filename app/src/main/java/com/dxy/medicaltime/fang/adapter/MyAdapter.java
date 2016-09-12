package com.dxy.medicaltime.fang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.dxy.medicaltime.R;
import com.dxy.medicaltime.fang.bean.SpecialTitleData;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/7/30.
 */
public class MyAdapter extends BaseAdapter {
    private List<SpecialTitleData.MessageBean> specialTitleData;
    private Context context;

    public MyAdapter(List<SpecialTitleData.MessageBean> specialTitleData, Context context){
        this.specialTitleData=specialTitleData;
        this.context=context;

    }

    @Override
    public int getCount() {
        return specialTitleData.size();
    }

    @Override
    public SpecialTitleData.MessageBean getItem(int position) {
        return specialTitleData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return specialTitleData.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.special_title_item_view, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SpecialTitleData.MessageBean data = specialTitleData.get(position);
        holder.title.setText("#" + data.getName());
        Picasso.with(context).load(data.getIconUrl()).into(holder.img);
        return convertView;
    }

    private class ViewHolder {
        ImageView img;
        TextView title;

        protected ViewHolder(View convertView) {
            convertView.measure(0, 0);
            img = (ImageView) convertView.findViewById(R.id.special_title_img);
            title = (TextView) convertView.findViewById(R.id.special_title_tv);
        }
    }
}
