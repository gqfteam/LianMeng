package com.hkd.lianmeng.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.adapter.MyAdapter_me_listView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author：Administrator on 2016/9/18 09:29
 */
public class MeFragment extends Fragment {
    @Bind(R.id.listView_fregment_me)
    ListView listViewFregmentMe;

    ArrayList<HashMap<String, Object>> mData;

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
        int[] _img = {R.mipmap.me_list_jsxx, R.mipmap.me_list_mmsp, R.mipmap.me_list_lxkf, R.mipmap.me_list_sz};
        int _length = _txt.length;
        mData = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < _length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("txt", _txt[i]);
            map.put("img", _img[i]);
            mData.add(map);
        }
        //配置自定义listview适配器MyAdapter_me_listView
        MyAdapter_me_listView listViewAdapter;
        listViewAdapter = new MyAdapter_me_listView(mData, getActivity());
        View hView = LayoutInflater.from(getActivity()).inflate(R.layout.me_list_head, null, false);
        listViewFregmentMe.addHeaderView(hView);
        listViewFregmentMe.setAdapter(listViewAdapter);

        }

        @Override
        public void onDestroyView () {
            super.onDestroyView();
            ButterKnife.unbind(this);
        }
    }