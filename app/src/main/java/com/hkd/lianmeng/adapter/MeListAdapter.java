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
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author：Administrator on 2016/9/18 12:16
 */
public class MeListAdapter extends BaseAdapter {
    Context context;
    ArrayList<HashMap<String, Object>> mData;

    public MeListAdapter(ArrayList<HashMap<String, Object>> pData, Context context) {
        this.mData = pData;
        this.context = context;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder _holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.me_list_item, null);
            _holder = new ViewHolder(view);
            view.setTag(_holder);
        } else {
            _holder = (ViewHolder) view.getTag();
        }
        _holder.meLeftIconImg.setImageResource((Integer) mData.get(i).get("img"));
        _holder.meMiddleLabelTxt.setText(mData.get(i).get("txt").toString());
        _holder.meMiddleLabelTxt.setTextColor(R.color.black);
        _holder.meRightInImg.setImageResource(R.mipmap.a);


        return view;
    }

    static class ViewHolder {
        @Bind(R.id.me_left_icon_img)
        ImageView meLeftIconImg;
        @Bind(R.id.me_middle_label_txt)
        TextView meMiddleLabelTxt;
        @Bind(R.id.me_right_in_img)
        ImageView meRightInImg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
