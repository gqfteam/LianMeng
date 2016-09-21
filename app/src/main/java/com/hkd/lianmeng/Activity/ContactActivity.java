package com.hkd.lianmeng.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.fragment.FriendListFragment;
import com.hkd.lianmeng.fragment.MsgListFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by johe on 2016/9/21.
 * gqf
 * 通讯录界面
 */
public class ContactActivity extends FragmentActivity {

    @Bind(R.id.contact_top_msg_rad)
    RadioButton contactTopMsgRad;
    @Bind(R.id.contact_top_friend_rad)
    RadioButton contactTopFriendRad;
    private FragmentTransaction ft;
    private MsgListFragment msgListFragment;
    private FriendListFragment friendListFragment;


    private boolean isContactRad=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);
        initTopRadAndFragment();

    }
    private void initTopRadAndFragment(){
        if(isContactRad){
            //消息
            contactTopMsgRad.setChecked(true);
            contactTopFriendRad.setChecked(false);
            msgListFragment=new MsgListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contact_fragment, msgListFragment).commit();

        }else{
            //联系人
            contactTopMsgRad.setChecked(false);
            contactTopFriendRad.setChecked(true);
            friendListFragment=new FriendListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contact_fragment, friendListFragment).commit();
        }
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

    @OnClick({R.id.contact_top_msg_rad, R.id.contact_top_friend_rad})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contact_top_msg_rad:
                Log.i("gqf","aaaaaaacontact_top_msg_rad");
                isContactRad=true;
                if (msgListFragment != null) {
                    showFragment(msgListFragment);
                } else {
                    msgListFragment = new MsgListFragment();
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.contact_fragment, msgListFragment).commit();
                }
                break;
            case R.id.contact_top_friend_rad:
                    Log.i("gqf","aaaaaaacontact_top_friend_rad");
                isContactRad=false;
                if (friendListFragment != null) {
                    showFragment(friendListFragment);
                } else {
                    friendListFragment = new FriendListFragment();
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.contact_fragment, friendListFragment).commit();
                }
                break;
        }
    }
}
