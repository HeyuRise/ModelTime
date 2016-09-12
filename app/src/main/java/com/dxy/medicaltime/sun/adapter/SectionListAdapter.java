package com.dxy.medicaltime.sun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dxy.medicaltime.R;
import com.dxy.medicaltime.sun.bean.SectionSub;
import com.dxy.medicaltime.sun.utils.MyGridView;

import java.util.ArrayList;

/**
 * Created by dell-pc on 2016/7/27.
 */
public class SectionListAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{
    private ArrayList<SectionSub.MessageBean.ChildrenBean> data;
    private Context context;
    private LayoutInflater inflater;
    private toCollect toCollect;

    public SectionListAdapter(ArrayList<SectionSub.MessageBean.ChildrenBean> data, Context context) {
        this.data = data;
        this.context = context;
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
        SectionGridAdapter sectionGridAdapter;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.heyu_section_item,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(data.get(position).getName());
        sectionGridAdapter=new SectionGridAdapter(context,data.get(position).getChildren());
        viewHolder.gridView.setAdapter(sectionGridAdapter);
        viewHolder.gridView.setOnItemClickListener(this);
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView textView1= (TextView) view.findViewById(R.id.section_sub_grid_text);
        String a=textView1.getText().toString();
        int b= (int) textView1.getTag();
        ImageView imageView= (ImageView) view.findViewById(R.id.section_sub_grid_img);
        boolean isSelected=imageView.isSelected();
        imageView.setSelected(!isSelected);
        toCollect.collect(b,a,!isSelected);
    }

    class ViewHolder{
        TextView textView;
        MyGridView gridView;

        public ViewHolder(View convertView) {
            textView= (TextView) convertView.findViewById(R.id.section_sub_list_title);
            gridView= (MyGridView) convertView.findViewById(R.id.grid_section_sub);
        }
    }
    public interface toCollect{
        void collect(int id, String name, boolean isCollect);
    }
    public void setInterace(toCollect toCollect){
        this.toCollect= toCollect;
    }
}
