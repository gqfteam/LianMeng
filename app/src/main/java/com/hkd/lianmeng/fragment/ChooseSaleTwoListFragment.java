package com.hkd.lianmeng.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.johe.lianmengdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
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
    private int RadioBtnId = 1;

    public void setRadioBtnId(int radioBtnId) {
        RadioBtnId = radioBtnId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_sale_two_list, container, false);
        ButterKnife.bind(this, view);
        return view;
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
    private void hide(){
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
