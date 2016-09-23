package com.hkd.lianmeng.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.TextView;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.adapter.ChatListAdapter;
import com.hkd.lianmeng.model.UserFriend;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/9/22.
 */

public class ChatActivity extends Activity {

    @Bind(R.id.chat_back_txt)
    TextView chatBackTxt;
    @Bind(R.id.chat_listView)
    ListView chatListView;
    @Bind(R.id.chatActivity_contack_txt)
    TextView chatActivityContackTxt;
    private Context mContext;
    private ChatListAdapter chatListAdapter;
    private List<EMMessage> mMessage;
    private ArrayList<UserFriend> mUserFriends;
    private UserFriend userFriend;
    private Thread mThread;
    private String mFriendName;
    private EMConversation mConversation;
    private int mIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(ChatActivity.this);

        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mIndex = getIntent().getIntExtra("index", -1);
                    mFriendName = getIntent().getStringExtra("friendName");

                    mConversation = EMClient.getInstance().chatManager().getConversation(mFriendName);
                    //获取所有的会话
                    mMessage = mConversation.getAllMessages();


                } catch (Exception e) {
                }
                if (mMessage.size() != 0) {
                    Message message = new Message();
                    message.what = 1;
                    myHandler.sendMessage(message);
                }
            }
        });
        mThread.start();


    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    initList();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void initList() {
        mContext = ChatActivity.this;
        //设置头部联系对象
        chatActivityContackTxt.setText(mFriendName);
        if (mUserFriends==null){
            mUserFriends = new ArrayList<>();
        }
        int _length =  mConversation.getAllMsgCount();
        for (int i=0;i<_length;i++){
            userFriend = new UserFriend();
            userFriend.setMessages(mMessage);
            mUserFriends.add(userFriend);
        }


        chatListAdapter = new ChatListAdapter(mContext, mUserFriends);
        chatListView.setAdapter(chatListAdapter);

    }

    @OnClick(R.id.chat_back_txt)
    public void onClick() {
        finish();
    }


}
