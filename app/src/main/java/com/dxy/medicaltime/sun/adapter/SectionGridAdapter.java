package com.dxy.medicaltime.sun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dxy.medicaltime.R;
import com.dxy.medicaltime.sun.bean.SectionHeyu;
import com.dxy.medicaltime.sun.bean.SectionSub;
import com.dxy.medicaltime.sun.utils.DBManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell-pc on 2016/7/27.
 */
public class SectionGridAdapter extends BaseAdapter {
    private List<SectionSub.MessageBean.ChildrenBean.ChildrenBean1> data;
    private LayoutInflater inflater;
    private ArrayList<SectionHeyu> sectionHeyus;

    public SectionGridAdapter(Context context, List<SectionSub.MessageBean.ChildrenBean.ChildrenBean1> data) {
        this.data = data;
        sectionHeyus=new ArrayList<>();
        sectionHeyus= (ArrayList<SectionHeyu>) DBManager.getDbManager(context).queryAll();
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
            convertView=inflater.inflate(R.layout.heyu_section_grid_item,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(data.get(position).getName());
        viewHolder.textView.setTag(data.get(position).getId());
        for(int i=0;i<sectionHeyus.size();i++){
            if(sectionHeyus.get(i).getId()==data.get(position).getId()){
                viewHolder.imageView.setSelected(true);
            }
        }
        return convertView;
    }
    class ViewHolder{
        TextView textView;
        ImageView imageView;
        public ViewHolder(View convertView) {
            textView= (TextView) convertView.findViewById(R.id.section_sub_grid_text);
            imageView= (ImageView) convertView.findViewById(R.id.section_sub_grid_img);
        }
    }

}
