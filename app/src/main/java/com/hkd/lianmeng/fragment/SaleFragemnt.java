package com.hkd.lianmeng.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import com.hkd.lianmeng.Activity.ContactActivity;
import com.hkd.lianmeng.R;
import com.hkd.lianmeng.base.BaseApplication;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * gqf
 * 义卖界面
 */

public class SaleFragemnt extends Fragment {


    @Bind(R.id.CB_shop_city)
    CheckBox CBShopCity;
    @Bind(R.id.CB_shop_campus)
    CheckBox CBShopCampus;
    @Bind(R.id.CB_shop_classification)
    CheckBox CBShopClassification;
    @Bind(R.id.sf_top_msg_img)
    ImageView sfTopMsgImg;

    private SaleListFragment mSaleListFragment;
    private ChooseSaleTwoListFragment mChooseSaleTwoListFragment;
    private Intent mIntent;

    public interface mListener {
        public void changeActivity(
                @SuppressWarnings("rawtypes") Class activityClass);
    }

    private mListener mListener;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (mListener) activity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale, container, false);
        ButterKnife.bind(this, view);
        CBShopCampus.setChecked(false);
        CBShopClassification.setChecked(false);
        CBShopCity.setChecked(false);
        mSaleListFragment = new SaleListFragment();
        getChildFragmentManager().beginTransaction()
                .add(R.id.sale_bottom_fragment, mSaleListFragment).commit();
        initChackBox();
        return view;
    }

    private void initChackBox(){
        CBShopCity.setText(BaseApplication.mSearchConditions.getCity());
        CBShopCampus.setText(BaseApplication.mSearchConditions.getCampus());
        CBShopClassification.setText(BaseApplication.mSearchConditions.getClassification()+"/"+BaseApplication.mSearchConditions.getSpecies());
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
                    .add(R.id.sale_bottom_fragment, mChooseSaleTwoListFragment).commit();
            mChooseSaleTwoListFragment.setRadioBtnId(id);
            mChooseSaleTwoListFragment.setoClose(new ChooseSaleTwoListFragment.onClose() {
                @Override
                public void CloseFragment() {
                    CBShopCity.setChecked(false);
                    CBShopCampus.setChecked(false);
                    CBShopClassification.setChecked(false);
                    initChackBox();
                }
            });
        } else if (cb.isChecked() == false) {
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

    @OnClick(R.id.sf_top_msg_img)
    public void onClick() {
        //通知主界面跳转通讯录消息界面
        mListener.changeActivity(ContactActivity.class);
    }
}
