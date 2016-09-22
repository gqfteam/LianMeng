package com.hkd.lianmeng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.Activity.InitiateSigninActivity;


public class ToolsFragment extends Fragment implements View.OnClickListener{
    View toolsView;
    RelativeLayout initialSignin_Relative;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        toolsView=inflater.inflate(R.layout.fragment_tools, container, false);
        init();
        addListener();
        return toolsView;
    }

    /**
     * 初始化组件
     */
    private void init() {
        initialSignin_Relative=(RelativeLayout)toolsView.findViewById(R.id.toolsf_middle_initialsignin_relative);
    }

    /**
     *组件的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolsf_middle_initialsignin_relative:
                Intent intent=new Intent(getActivity(), InitiateSigninActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 给组件添加事件
     */
    public void addListener(){
        initialSignin_Relative.setOnClickListener(this);
    }

}
