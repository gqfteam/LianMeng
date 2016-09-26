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
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/22.
 */

public class ChatListAdapter extends BaseAdapter {
    private Context mContext;
    private ViewHolder mHolder;
    private LayoutInflater layoutInflater;
    private ArrayList<UserFriend> mUserFriends;
    private List<EMMessage> msgData;
    private DemoHelper mDemoHelper;

    public ChatListAdapter(Context mContext, List<EMMessage> msgData) {
        this.mContext = mContext;
        this.msgData = msgData;
        this.layoutInflater = LayoutInflater.from(mContext);
        mDemoHelper = DemoHelper.getInstance();
    }

    @Override
    public int getCount() {
        if (msgData == null) {
            return 0;
        }
        //Log.i("wjd", "msgData.size():" + msgData.size());
        return msgData.size();
    }

    @Override
    public Object getItem(int i) {
        return msgData.get(i);
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
        } else {
            mHolder = (ViewHolder) view.getTag();
        }
        mHolder.chatLItemNameTxt.setText(msgData.get(i).getUserName());
        //获取txt：“消息”中的消息
        String _txt = msgData.get(i).getBody().toString().substring(5, msgData.get(i).getBody().toString().length() - 1);
        mHolder.chatLItemMsgTxt.setText(_txt);
        String ＿time = mDemoHelper.getTimeLongToString(msgData.get(i).getMsgTime());
        Log.i("wjd", "＿time:" + ＿time);
        mHolder.chatLItemTimeTxt.setText(＿time);

        return view;
    }


    static class ViewHolder {
        @Bind(R.id.chat_lItem_img)
        ImageView chatLItemImg;
        @Bind(R.id.chat_lItem_time_txt)
        TextView chatLItemTimeTxt;
        @Bind(R.id.chat_lItem_name_txt)
        TextView chatLItemNameTxt;
        @Bind(R.id.chat_lItem_msg_txt)
        TextView chatLItemMsgTxt;
        @Bind(R.id.chat_lItem_lLaout)
        LinearLayout chatLItemLLaout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
