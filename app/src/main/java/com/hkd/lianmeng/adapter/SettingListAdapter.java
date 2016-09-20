package com.hkd.lianmeng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.johe.lianmengdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author：Administrator on 2016/9/20 17:33
 */
public class SettingListAdapter extends BaseAdapter {
    Context context;
    private String[] _txt;


    public SettingListAdapter(Context context) {
        this.context = context;
        _txt =context.getResources().getStringArray(R.array.setting_listViewItem_strArray);

    }

    @Override
    public int getCount() {
        return _txt.length;
    }

    @Override
    public Object getItem(int i) {
        return _txt[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder _holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.setting_list_item, null);
            _holder = new ViewHolder(view);
            view.setTag(_holder);
        } else {
            _holder = (ViewHolder) view.getTag();
        }
        _holder.settingListItemTxt.setText(_txt[i]);
        _holder.settingListItemImg.setImageResource(R.mipmap.a);
        return view;
    }


    static class ViewHolder {
        @Bind(R.id.setting_listItem_txt)
        TextView settingListItemTxt;
        @Bind(R.id.setting_listItem_img)
        ImageView settingListItemImg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
