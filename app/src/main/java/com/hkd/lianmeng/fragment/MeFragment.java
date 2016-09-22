package com.hkd.lianmeng.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.Activity.SettingActivity;
import com.hkd.lianmeng.adapter.MeListAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author：Administrator on 2016/9/18 09:29
 */
public class MeFragment extends Fragment {
    ArrayList<HashMap<String, Object>> mData;
    @Bind(R.id.listView_fregment_me)
    ListView listViewFregmentMe;
    @Bind(R.id.me_settingClick_lLayout)
    LinearLayout meSettingClickLLayout;
    Context mContext;

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fregment_me, container, false);
        ButterKnife.bind(this, view);
        //初始化mData,设置适配器
        init();

        return view;
    }

    /**
     * 初始化数据函数
     */
    private void init() {
        mContext = getActivity();
        String[] _txt;
        _txt = getResources().getStringArray(R.array.me_listViewItem_strArray);
        int[] _img = {R.mipmap.me_list_jsxx, R.mipmap.me_list_mmsp, R.mipmap.me_list_lxkf};
        int _length = _txt.length;
        mData = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < _length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("txt", _txt[i]);
            map.put("img", _img[i]);
            mData.add(map);
        }
        //配置自定义listview适配器MyAdapter_me_listView
        MeListAdapter listViewAdapter;
        listViewAdapter = new MeListAdapter(mData, getActivity());
        listViewFregmentMe.setAdapter(listViewAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.me_settingClick_lLayout)
    public void onClick() {
        Intent intent = new Intent(mContext, SettingActivity.class);
        startActivity(intent);
    }
}
