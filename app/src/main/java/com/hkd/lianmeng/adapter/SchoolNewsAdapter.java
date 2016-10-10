package com.hkd.lianmeng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hkd.lianmeng.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/20.
 */
public class SchoolNewsAdapter extends BaseAdapter {

    ArrayList<String> listDatas;
    Context context;
    ViewHolder holder;

    public SchoolNewsAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.listDatas = list;
    }

    @Override
    public int getCount() {
        return listDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return listDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.school_info_news_list,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }


        return convertView;
    }



    class ViewHolder {
        @Bind(R.id.schoolinfo_left_newslogo_iv)
        ImageView schoolinfoLeftNewslogoIv;
        @Bind(R.id.schoolinfo_left_newslogo_tv)
        TextView schoolinfoLeftNewslogoTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
