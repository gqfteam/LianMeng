package com.hkd.lianmeng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hkd.lianmeng.Activity.InitiateSigninActivity;
import com.hkd.lianmeng.Activity.SigninListActivity;
import com.hkd.lianmeng.Activity.UserSaleListActivity;
import com.hkd.lianmeng.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ToolsFragment extends Fragment implements View.OnClickListener {
    View toolsView;
    RelativeLayout initialSignin_Relative, inquiry_Signin_Relative;
    @Bind(R.id.toolsf_middle_inquiry_goods_relative)
    RelativeLayout toolsfMiddleInquiryGoodsRelative;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        toolsView = inflater.inflate(R.layout.fragment_tools, container, false);
        init();
        addListener();
        ButterKnife.bind(this, toolsView);
        return toolsView;
    }

    /**
     * 初始化组件
     */
    private void init() {
        initialSignin_Relative = (RelativeLayout) toolsView.findViewById(R.id.toolsf_middle_initialsignin_relative);
        inquiry_Signin_Relative = (RelativeLayout) toolsView.findViewById(R.id.toolsf_middle_inquiry_signin_relative);
    }

    /**
     * 给组件添加事件
     */
    public void addListener() {
        initialSignin_Relative.setOnClickListener(this);
        inquiry_Signin_Relative.setOnClickListener(this);
    }


    /**
     * 组件的点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolsf_middle_initialsignin_relative:
                Intent intoInitiateSignin = new Intent(getActivity(), InitiateSigninActivity.class);
                startActivity(intoInitiateSignin);
                break;
            case R.id.toolsf_middle_inquiry_signin_relative:
                Intent intoInquirySignin = new Intent(getActivity(), SigninListActivity.class);
                startActivity(intoInquirySignin);
                break;

        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.toolsf_middle_inquiry_goods_relative)
    public void onClick() {
        startActivity(new Intent(getActivity(), UserSaleListActivity.class));

    }
}
