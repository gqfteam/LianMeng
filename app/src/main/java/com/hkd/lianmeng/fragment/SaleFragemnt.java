package com.hkd.lianmeng.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.johe.lianmengdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SaleFragemnt extends Fragment {


    @Bind(R.id.CB_shop_city)
    CheckBox CBShopCity;//根据省份寻找城市
    @Bind(R.id.CB_shop_campus)
    CheckBox CBShopCampus;//根据大学寻找校区
    @Bind(R.id.CB_shop_classification)
    CheckBox CBShopClassification;//根据大分类寻找小分类

    @Bind(R.id.shop_rad_group)
    RadioGroup shopRadGroup;
    @Bind(R.id.shop_fragment)
    FrameLayout shopFragment;//切换选择界面与加载商品信息

    public SaleFragemnt() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



    @OnClick({R.id.CB_shop_city, R.id.CB_shop_campus, R.id.CB_shop_classification})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.CB_shop_city:
                break;
            case R.id.CB_shop_campus:
                break;
            case R.id.CB_shop_classification:
                break;
        }
    }
}
