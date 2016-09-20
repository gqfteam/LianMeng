package com.hkd.lianmeng.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.constant.Constant;
import com.hkd.lianmeng.view.CarouselView;
import com.hkd.lianmeng.view.SearchView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SchoolInfoFragment extends Fragment {

    @Bind(R.id.schoolinfo_top_address_tv)
    TextView schoolinfoTopAddressTv;
    @Bind(R.id.schoolinfo_top_address_iv)
    ImageView schoolinfoTopAddressIv;
    @Bind(R.id.schoolinfo_top_search_sv)
    SearchView schoolinfoTopSearchSv;
    @Bind(R.id.schoolinfo_top_qcord_iv)
    ImageView schoolinfoTopQcordIv;
    @Bind(R.id.schoolinfo_top_chat_iv)
    ImageView schoolinfoTopChatIv;
    @Bind(R.id.schoolinfo_top_carousel_cv)
    CarouselView mSchoolinfoTopCarouselCv;
    @Bind(R.id.schoolinfo_top_netteach_rb)
    RadioButton schoolinfoTopNetteachRb;
    @Bind(R.id.schoolinfo_top_zhxg_rb)
    RadioButton schoolinfoTopZhxgRb;
    @Bind(R.id.schoolinfo_top_netservice_rb)
    RadioButton schoolinfoTopNetserviceRb;
    @Bind(R.id.schoolinfo_top_classrooom_rb)
    RadioButton schoolinfoTopClassrooomRb;
    @Bind(R.id.schoolinfo_top_phone_rb)
    RadioButton schoolinfoTopPhoneRb;
    @Bind(R.id.schoolinfo_top_classinfo_rb)
    RadioButton schoolinfoTopClassinfoRb;
    @Bind(R.id.schoolinfo_top_scoresearch_rb)
    RadioButton schoolinfoTopScoresearchRb;
    @Bind(R.id.schoolinfo_top_yikatongfuwu_rb)
    RadioButton schoolinfoTopYikatongfuwuRb;
    @Bind(R.id.schoolinfo_top_xiaoli_rb)
    RadioButton schoolinfoTopXiaoliRb;
    @Bind(R.id.schoolinfo_top_lixiao_rb)
    RadioButton schoolinfoTopLixiaoRb;
    @Bind(R.id.schoolinfo_top_radiogroup_rg)
    RadioGroup schoolinfoTopRadiogroupRg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_school_info, container, false);
        ButterKnife.bind(this, view);
        initDatas();
        return view;

    }

    public void initDatas() {
        mSchoolinfoTopCarouselCv.setImagesRes(Constant.FieldImage);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.schoolinfo_top_address_tv, R.id.schoolinfo_top_address_iv, R.id.schoolinfo_top_search_sv, R.id.schoolinfo_top_chat_iv, R.id.schoolinfo_top_carousel_cv, R.id.schoolinfo_top_netteach_rb, R.id.schoolinfo_top_zhxg_rb, R.id.schoolinfo_top_netservice_rb, R.id.schoolinfo_top_classrooom_rb, R.id.schoolinfo_top_phone_rb, R.id.schoolinfo_top_classinfo_rb, R.id.schoolinfo_top_scoresearch_rb, R.id.schoolinfo_top_yikatongfuwu_rb, R.id.schoolinfo_top_xiaoli_rb, R.id.schoolinfo_top_lixiao_rb, R.id.schoolinfo_top_radiogroup_rg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.schoolinfo_top_address_tv:
                break;
            case R.id.schoolinfo_top_address_iv:
                break;
            case R.id.schoolinfo_top_search_sv:
                break;
            case R.id.schoolinfo_top_chat_iv:
                break;
            case R.id.schoolinfo_top_carousel_cv:
                break;
            case R.id.schoolinfo_top_netteach_rb:
                break;
            case R.id.schoolinfo_top_zhxg_rb:
                break;
            case R.id.schoolinfo_top_netservice_rb:
                break;
            case R.id.schoolinfo_top_classrooom_rb:
                break;
            case R.id.schoolinfo_top_phone_rb:
                break;
            case R.id.schoolinfo_top_classinfo_rb:
                break;
            case R.id.schoolinfo_top_scoresearch_rb:
                break;
            case R.id.schoolinfo_top_yikatongfuwu_rb:
                break;
            case R.id.schoolinfo_top_xiaoli_rb:
                break;
            case R.id.schoolinfo_top_lixiao_rb:
                break;
            case R.id.schoolinfo_top_radiogroup_rg:
                break;
        }
    }
}
