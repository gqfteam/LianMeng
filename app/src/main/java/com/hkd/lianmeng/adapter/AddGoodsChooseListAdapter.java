package com.hkd.lianmeng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hkd.lianmeng.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by johe on 2016/9/21.
 */
public class AddGoodsChooseListAdapter extends BaseAdapter {
    private ArrayList<String> datas;//数据源
    private Context mContext;
    private LayoutInflater layoutInflater;
    private ViewHolder mHolder;


    public void update(ArrayList<String> datas) {
        this.datas = datas;
        this.notifyDataSetChanged();
    }

    public AddGoodsChooseListAdapter(Context context,ArrayList<String> datas) {
        this.mContext = context;
        this.datas = datas;
        this.layoutInflater = LayoutInflater.from(context);

    }

    public int getCount() {
        if (datas == null) {
            return 0;
        }
        return datas.size();
    }

    public Object getItem(int arg0) {
        return datas.get(arg0);
    }

    public long getItemId(int arg0) {
        return arg0;
    }


    public View getView(int arg0, View arg1, ViewGroup arg2) {

        if (arg1 == null) {

            arg1 = layoutInflater.inflate(R.layout.item,
                    null);// inflate(context,
            // R.layout.list_item,
            // null);
            mHolder = new ViewHolder(arg1);
            arg1.setTag(mHolder);

        } else {
            mHolder = (ViewHolder) arg1.getTag();
        }
        mHolder.txt.setText(datas.get(arg0));
        return arg1;
    }


    static class ViewHolder {
        @Bind(R.id.txt)
        TextView txt;
        @Bind(R.id.txt1)
        TextView txt1;
        @Bind(R.id.txt2)
        TextView txt2;
        @Bind(R.id.txt3)
        TextView txt3;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
