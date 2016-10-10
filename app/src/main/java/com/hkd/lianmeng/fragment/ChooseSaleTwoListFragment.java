package com.hkd.lianmeng.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.hkd.lianmeng.R;
import com.hkd.lianmeng.adapter.ChooseSaleOneAdapter;
import com.hkd.lianmeng.adapter.ChooseSaleTwoAdapter;
import com.hkd.lianmeng.base.BaseApplication;
import com.hkd.lianmeng.model.SaleChooseModel;
import com.hkd.lianmeng.tools.ReadJson;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * gqf
 * Salefragment顶部条件选择器界面，含有两个list联动
 */
public class ChooseSaleTwoListFragment extends Fragment {

    @Bind(R.id.choseListViewOne)
    ListView choseListViewOne;
    @Bind(R.id.choseListViewTwo)
    ListView choseListViewTwo;
    @Bind(R.id.chose_popu_lin)
    LinearLayout chosePopuLin;
    @Bind(R.id.chose_popu_back)
    View chosePopuBack;
    private String nowChoose;
    private int RadioBtnId = 0;
    private ReadJson rj;
    private ChooseSaleOneAdapter mChooseSaleOneAdapter;
    private ChooseSaleTwoAdapter mChooseSaleTwoAdapter;
    ArrayList<SaleChooseModel> datas;
    SaleChooseModel have;

    public void setRadioBtnId(int radioBtnId) {
        RadioBtnId = radioBtnId;
        if (view != null) {

            if (mChooseSaleTwoAdapter != null) {
                mChooseSaleTwoAdapter.update(null);
            }
            initList(RadioBtnId);
        }
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_choose_sale_two_list, container, false);
        ButterKnife.bind(this, view);
        initList(RadioBtnId);
        return view;
    }


    public void initList(int id) {
        //读取json
        String json = null;
        if (id == 0) {
            json = getActivity().getString(R.string.city_json);
        } else if (id == 1) {
            json = getActivity().getString(R.string.university_json);
        } else {
            json = getActivity().getString(R.string.classification_json);
        }
        rj = ReadJson.getInstance();
        datas = rj.readSaleTopChooseJson(json);

        mChooseSaleOneAdapter = new ChooseSaleOneAdapter(getContext(), datas);
        mChooseSaleOneAdapter.setChooseid(RadioBtnId);
        choseListViewOne.setAdapter(mChooseSaleOneAdapter);

        choseListViewOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                have = datas.get(position);

                if (RadioBtnId == 0) {
                    mChooseSaleOneAdapter.setProvince(datas.get(position).getName());
                    nowChoose = BaseApplication.mSearchConditions.getCity();
                } else if (RadioBtnId == 1) {
                    mChooseSaleOneAdapter.setUniversity(datas.get(position).getName());
                    nowChoose = BaseApplication.mSearchConditions.getSpecies();
                } else {
                    mChooseSaleOneAdapter.setClassification(datas.get(position).getName());
                    nowChoose = BaseApplication.mSearchConditions.getSpecies();
                }
                mChooseSaleOneAdapter.update(datas);
                initTwoList(have, nowChoose);
            }
        });

    }

    public void initTwoList(SaleChooseModel datas, String nowChoose) {
        if (mChooseSaleTwoAdapter == null) {
            mChooseSaleTwoAdapter = new ChooseSaleTwoAdapter(getContext(), datas);

            mChooseSaleTwoAdapter.setChooseid(RadioBtnId);
            mChooseSaleTwoAdapter.setmSaleChooseModelName(nowChoose);
            choseListViewTwo.setAdapter(mChooseSaleTwoAdapter);
            choseListViewTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (RadioBtnId == 0) {
                        mChooseSaleTwoAdapter.setCity(have.getHave().get(position));
                        BaseApplication.mSearchConditions.setCity(have.getHave().get(position));
                        BaseApplication.mSearchConditions.setProvince(have.getName());
                    } else if (RadioBtnId == 1) {
                        mChooseSaleTwoAdapter.setCampus(have.getHave().get(position));
                        BaseApplication.mSearchConditions.setCampus(have.getHave().get(position));
                        BaseApplication.mSearchConditions.setUniversity(have.getName());
                    } else {
                        mChooseSaleTwoAdapter.setSpecies(have.getHave().get(position));
                        BaseApplication.mSearchConditions.setSpecies(have.getHave().get(position));
                        BaseApplication.mSearchConditions.setClassification(have.getName());
                    }
                    mChooseSaleTwoAdapter.setmSaleChooseModelName(have.getHave().get(position));
                    mChooseSaleTwoAdapter.update(have);
                    //关闭当前fragment
                    hide();
                    if (oClose != null) {
                        oClose.CloseFragment();
                    }
                }
            });
        } else {
            mChooseSaleTwoAdapter.setmSaleChooseModelName(nowChoose);
            mChooseSaleTwoAdapter.setChooseid(RadioBtnId);
            mChooseSaleTwoAdapter.update(have);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.chose_popu_back)
    public void onClick() {
        //点击隐藏当前条件选择fragment
        hide();
        if (oClose != null) {
            oClose.CloseFragment();
        }


    }

    private void hide() {
        getParentFragment().getChildFragmentManager().beginTransaction()
                .hide(this).commit();
    }

    public onClose getoClose() {
        return oClose;
    }

    public void setoClose(onClose oClose) {
        this.oClose = oClose;
    }

    onClose oClose;

    public interface onClose {
        public void CloseFragment();
    }
}
