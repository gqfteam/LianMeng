package com.hkd.lianmeng.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.adapter.SettingListAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingActivity extends Activity {

    @Bind(R.id.setting_back_img)
    ImageView settingBackImg;
    @Bind(R.id.listView_fregment_setting)
    ListView listViewFregmentSetting;
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
    public static void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }


    /**
     * 设置SettingActivity中listView适配器
     */
    private void setAdapter() {
        if (mSettingListAdapter == null) {
            mSettingListAdapter = new SettingListAdapter(SettingActivity.this);
        }

        View _settingFoot = LayoutInflater.from(SettingActivity.this).inflate(R.layout.setting_list_foot,null);

        listViewFregmentSetting.addFooterView(_settingFoot);
        listViewFregmentSetting.setAdapter(mSettingListAdapter);
    }

}
