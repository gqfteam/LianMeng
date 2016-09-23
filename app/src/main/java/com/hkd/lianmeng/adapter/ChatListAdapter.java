package com.hkd.lianmeng.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.model.UserFriend;
import com.hkd.lianmeng.tools.DemoHelper;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/22.
 */

public class ChatListAdapter extends BaseAdapter {
    private Context mContext;
    private ViewHolder mHolder;
    private LayoutInflater layoutInflater;
    private  ArrayList<UserFriend> mUserFriends;
    private DemoHelper mDemoHelper;

    public ChatListAdapter(Context mContext,  ArrayList<UserFriend> mUserFriends) {
        this.mContext = mContext;
        this.mUserFriends = mUserFriends;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        if (mUserFriends == null) {
            return 0;
        }
        Log.i("11111111111111",""+mUserFriends.size());
        return mUserFriends.size();
    }

    @Override
    public Object getItem(int i) {
        return mUserFriends.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.chat_list_item, null);
            mHolder = new ViewHolder(view);
            view.setTag(mHolder);
        }else{
            mHolder = (ViewHolder) view.getTag();
        }
        mHolder.chatLItemNameTxt.setText(mUserFriends.get(i).getUserName());
        //EMMessageBody _eb = mDatas.get(i).getMessages().get(mDatas.get(i).getMessages().size() - 1).getBody();

        mHolder.chatLItemMsgTxt.setText(mUserFriends.get(i).getMessages().get(mUserFriends.get(i).getMessages().size()-1).getBody().toString());
        //String time = mDemoHelper.getTimeLongToString(mDatas.get(i).getMessages().get(i).getMsgTime());
        //mHolder.chatLItemTimeTxt.setText(time);

        return view;
    }

    static class ViewHolder {
        @Bind(R.id.chat_lItem_img)
        ImageView chatLItemImg;
        @Bind(R.id.chat_lItem_name_txt)
        TextView chatLItemNameTxt;
        @Bind(R.id.chat_lItem_time_txt)
        TextView chatLItemTimeTxt;
        @Bind(R.id.chat_lItem_msg_txt)
        TextView chatLItemMsgTxt;
        @Bind(R.id.chat_lItem_lLaout)
        LinearLayout chatLItemLLaout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
