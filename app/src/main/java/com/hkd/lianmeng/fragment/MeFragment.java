package com.hkd.lianmeng.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.Activity.SettingActivity;
import com.hkd.lianmeng.adapter.MeListAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * @author：Administrator on 2016/9/18 09:29
 */
public class MeFragment extends Fragment {
    ArrayList<HashMap<String, Object>> mData;
    @Bind(R.id.listView_fregment_me)
    ListView listViewFregmentMe;

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fregment_me, container, false);
        ButterKnife.bind(this, view);
        //初始化数据
        init();

        return view;
    }

    /**
     * 初始化数据函数
     */
    private void init() {
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
        View _hView = LayoutInflater.from(getActivity()).inflate(R.layout.me_list_head, null, false);
        View _fView = LayoutInflater.from(getActivity()).inflate(R.layout.me_list_foot, null, false);
        listViewFregmentMe.addHeaderView(_hView);
        listViewFregmentMe.addFooterView(_fView);
        listViewFregmentMe.setAdapter(listViewAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnItemClick(R.id.listView_fregment_me)
    public void OnItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        switch (arg2) {

            case 4:
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }


    }
}
