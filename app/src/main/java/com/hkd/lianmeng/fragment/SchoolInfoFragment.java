package com.hkd.lianmeng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.Activity.ContactActivity;
import com.hkd.lianmeng.Activity.SchoolinfoNewListActivity;
import com.hkd.lianmeng.constant.Constant;
import com.hkd.lianmeng.model.Sentence;
import com.hkd.lianmeng.view.CarouselView;
import com.hkd.lianmeng.view.SearchView;
import com.hkd.lianmeng.view.VerticalScrollTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SchoolInfoFragment extends Fragment {
    View mview;
    @Bind(R.id.schoolinfo_ScrollView)
    ScrollView mSchoolinfoScrollView;
    @Bind(R.id.schoolinfo_top_address_tv)
    TextView schoolinfoTopAddressTv;
    @Bind(R.id.schoolinfo_top_address_iv)
    ImageView schoolinfoTopAddressIv;
    @Bind(R.id.schoolinfo_top_search_sv)
    SearchView schoolinfoTopSearchSv;
    @Bind(R.id.schoolinfo_top_qcord_iv)
    ImageView schoolinfoTopQcordIv;
    @Bind(R.id.schoolinfo_top_chat_iv)
    ImageView mSchoolinfoTopChatIv;
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
   /* @Bind(R.id.schoolinfo_moddle_newslist_vstv)*/
    VerticalScrollTextView mNewListVerticalScrollTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         mview = inflater.inflate(R.layout.fragment_school_info, container, false);
        ButterKnife.bind(this, mview);
        mNewListVerticalScrollTextView= (VerticalScrollTextView) mview.findViewById(R.id.schoolinfo_moddle_newslist_vstv);
        initDisplay();

        return mview;

    }

    /*初始化数据*/
    public void initDatas() {
        mSchoolinfoTopCarouselCv.setImagesRes(Constant.FieldImage);
        initNewsList();

    }
    /*初始化显示信息*/
    public void initDisplay() {
        initDatas();
        mSchoolinfoScrollView.setOnTouchListener(new TouchListenerImpl());
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
                startActivity(new Intent(getActivity(), ContactActivity.class));
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

    /**
     * 新闻设置滚动条的数据
     */
    public void initNewsList(){

        List lst=new ArrayList<Sentence>();
        for(int i=0;i<30;i++){

            if(i%2==0){
                Sentence sen=new Sentence(i,i+"、金球奖三甲揭晓 C罗梅西哈维入围 ");
                lst.add(i, sen);
            }else{
                Sentence sen=new Sentence(i,i+"、公牛欲用三大主力换魔兽？？？？");
                lst.add(i, sen);
            }
        }
        //给View传递数据
        Log.d("TAG",mNewListVerticalScrollTextView+"---"+lst);

        mNewListVerticalScrollTextView.setList(lst);
        //更新View
        mNewListVerticalScrollTextView.updateUI();//*
    }

    /**
     * 设置ScrollVie滚动的监听时间
     */
    //判断是否滑动到底部，防止重复加载

    @Override
    public void onResume() {
        super.onResume();
       //重新设置flag
        flag=true;
    }
    boolean flag=true;
    private class TouchListenerImpl implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    break;
                case MotionEvent.ACTION_MOVE:
                    int scrollY=view.getScrollY();
                    int height=view.getHeight();
                    int scrollViewMeasuredHeight=mSchoolinfoScrollView.getChildAt(0).getMeasuredHeight();
                    if(scrollY==0){
                        System.out.println("滑动到了顶端 view.getScrollY()="+scrollY);
                    }

                    if (flag) {
                        if ((scrollY + height) == scrollViewMeasuredHeight) {
                            flag=false;
                            System.out.println("滑动到了底部 scrollY=" + scrollY);
                            System.out.println("滑动到了底部 height=" + height);
                            System.out.println("滑动到了底部 scrollViewMeasuredHeight=" + scrollViewMeasuredHeight);
                            startActivity(new Intent(getActivity(), SchoolinfoNewListActivity.class));

                        }
                    }
                        break;

                default:
                    break;
            }
            return false;
        }

    };

}
