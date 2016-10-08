package com.hkd.lianmeng.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.hkd.lianmeng.R;
import com.hkd.lianmeng.adapter.SettingListAdapter;
import com.hkd.lianmeng.base.BaseApplication;
import com.hyphenate.chat.EMClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends Activity {
    //listview
    @Bind(R.id.listView_fregment_setting)
    ListView listViewFregmentSetting;
    //textview_我
    @Bind(R.id.setting_me_txt)
    TextView settingMeTxt;
    //textView_退出登录
    @Bind(R.id.setting_TCDL_txt)
    TextView settingTCDLTxt;

    //自定义adapter
    private SettingListAdapter mSettingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        //设置listview适配器adapter
        setAdapter();


    }

    /**
     * 设置view 的margin
     * @param v view
     * @param l 左边距
     * @param t 顶部
     * @param r 右边距
     * @param b 底部
     */
    /*public static void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }*/


    /**
     * 设置SettingActivity中listView适配器
     */
    private void setAdapter() {
        if (mSettingListAdapter == null) {
            mSettingListAdapter = new SettingListAdapter(SettingActivity.this);
        }

        listViewFregmentSetting.setAdapter(mSettingListAdapter);
    }

    @OnClick({R.id.setting_me_txt,R.id.setting_TCDL_txt})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.setting_me_txt:
                finish();
                break;
            case R.id.setting_TCDL_txt:

                //Toast.makeText(this, "tuichudegnlu", Toast.LENGTH_SHORT).show();
                BaseApplication a = new BaseApplication();
                EMClient.getInstance().logout(true);
                startActivity(new Intent(SettingActivity.this,LoginActivity.class));

                break;

        }
    }


}
