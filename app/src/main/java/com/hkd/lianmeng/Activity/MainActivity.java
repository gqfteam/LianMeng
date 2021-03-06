package com.hkd.lianmeng.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.hkd.lianmeng.R;
import com.hkd.lianmeng.base.BaseApplication;
import com.hkd.lianmeng.fragment.MeFragment;
import com.hkd.lianmeng.fragment.SaleFragemnt;
import com.hkd.lianmeng.fragment.SchoolInfoFragment;
import com.hkd.lianmeng.fragment.ToolsFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * gqf
 * 主页面，加载主页，义卖，工具，我的fragment
 */


public class MainActivity extends FragmentActivity implements SaleFragemnt.mListener,ToolsFragment.mListener{

    @Bind(R.id.Main_bottom_HomePage_Rad)
    RadioButton MainBottomHomePageRad;
    @Bind(R.id.Main_bottom_Sale_Rad)
    RadioButton MainBottomSaleRad;
    @Bind(R.id.Main_bottom_Tools_Rad)
    RadioButton MainBottomToolsRad;
    @Bind(R.id.Main_bottom_User_Rad)
    RadioButton MainBottomUserRad;
    private FragmentTransaction ft;
    private SaleFragemnt mSaleFragemnt;
    private SchoolInfoFragment mSchoolInfoFragment;
    private MeFragment mMeFragment;
    private ToolsFragment mToolsFragment;
    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSchoolInfoFragment = new SchoolInfoFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_fragment, mSchoolInfoFragment).commit();
        MainBottomHomePageRad.setChecked(true);

    }


    private void showFragment(Fragment index) {
        ft = getSupportFragmentManager().beginTransaction();

        for (int i = 0; i < getSupportFragmentManager().getFragments().size(); i++) {
            Fragment f = getSupportFragmentManager().getFragments().get(i);
            if (f == index) {
                ft.show(f);
            } else {
                ft.hide(f);
            }
        }
        ft.commit();
    }

    @OnClick({R.id.Main_bottom_HomePage_Rad, R.id.Main_bottom_Sale_Rad, R.id.Main_bottom_Tools_Rad, R.id.Main_bottom_User_Rad})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Main_bottom_HomePage_Rad:
                if (mSchoolInfoFragment != null) {
                    showFragment(mSchoolInfoFragment);
                }
                break;
            case R.id.Main_bottom_Sale_Rad:
                if (mSaleFragemnt != null) {
                    showFragment(mSaleFragemnt);
                } else {
                    mSaleFragemnt = new SaleFragemnt();
                           getSupportFragmentManager().beginTransaction()
                            .add(R.id.main_fragment, mSaleFragemnt).commit();
                }
                break;
            case R.id.Main_bottom_Tools_Rad:

                if (mToolsFragment != null) {
                    showFragment(mToolsFragment);
                } else {
                    mToolsFragment = new ToolsFragment();
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.main_fragment, mToolsFragment).commit();
                }
                break;
            case R.id.Main_bottom_User_Rad:
                if (mMeFragment != null) {
                    showFragment(mMeFragment);
                } else {
                    mMeFragment = new MeFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.main_fragment,mMeFragment).commit();
                }
                break;
        }
    }
    public void changeActivity(
            @SuppressWarnings("rawtypes") Class activityClass){

        mIntent=new Intent();
        if(activityClass==ContactActivity.class){
            //判断是否登录
           /* if(BaseApplication.isLogin){*/
            mIntent.setClass(MainActivity.this,activityClass);
           /* }else {

                mIntent.setClass(MainActivity.this, LoginActivity.class);
            }*/
        }else if(activityClass==UserSaleListActivity.class){
            mIntent.setClass(MainActivity.this, UserSaleListActivity.class);
        }
        if(activityClass==InitiateSigninActivity.class){
            mIntent.setClass(MainActivity.this,activityClass);
        }

        startActivity(mIntent);
    }
}
