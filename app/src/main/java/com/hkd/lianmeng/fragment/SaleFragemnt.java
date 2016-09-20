package com.hkd.lianmeng.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.johe.lianmengdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SaleFragemnt extends Fragment {



    @Bind(R.id.CB_shop_city)
    CheckBox CBShopCity;
    @Bind(R.id.CB_shop_campus)
    CheckBox CBShopCampus;
    @Bind(R.id.CB_shop_classification)
    CheckBox CBShopClassification;

    private SaleListFragment mSaleListFragment;
    private ChooseSaleTwoListFragment mChooseSaleTwoListFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale, container, false);
        ButterKnife.bind(this, view);
        CBShopCampus.setChecked(false);
        CBShopClassification.setChecked(false);
        CBShopCity.setChecked(false);
        mSaleListFragment=new SaleListFragment();
        getChildFragmentManager().beginTransaction()
                .add(R.id.sale_bottom_fragment,mSaleListFragment).commit();
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private void showChooseSaleListFragment(CheckBox cb, int id) {

        if (mChooseSaleTwoListFragment == null) {
            mChooseSaleTwoListFragment = new ChooseSaleTwoListFragment();
            getChildFragmentManager().beginTransaction()
                    .add(R.id.sale_bottom_fragment,mChooseSaleTwoListFragment).commit();
            mChooseSaleTwoListFragment.setRadioBtnId(id);
            mChooseSaleTwoListFragment.setoClose(new ChooseSaleTwoListFragment.onClose() {
                @Override
                public void CloseFragment() {
                    CBShopCity.setChecked(false);
                    CBShopCampus.setChecked(false);
                    CBShopClassification.setChecked(false);
                }
            });
        } else if (cb.isChecked()==false) {
            getChildFragmentManager().beginTransaction()
                    .hide(mChooseSaleTwoListFragment).commit();
        } else {
            getChildFragmentManager().beginTransaction()
                    .show(mChooseSaleTwoListFragment).commit();
            mChooseSaleTwoListFragment.setRadioBtnId(id);
        }


    }

    @OnClick({R.id.CB_shop_city, R.id.CB_shop_campus, R.id.CB_shop_classification})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.CB_shop_city:

                CBShopCampus.setChecked(false);
                CBShopClassification.setChecked(false);
                showChooseSaleListFragment(CBShopCity, 0);
                break;
            case R.id.CB_shop_campus:

                CBShopCity.setChecked(false);
                CBShopClassification.setChecked(false);
                showChooseSaleListFragment(CBShopCampus, 1);
                break;
            case R.id.CB_shop_classification:

                CBShopCampus.setChecked(false);
                CBShopCity.setChecked(false);
                showChooseSaleListFragment(CBShopClassification, 2);
                break;
        }
    }
}
