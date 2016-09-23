package com.hkd.lianmeng.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.adapter.ChatListAdapter;
import com.hkd.lianmeng.model.UserFriend;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

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
    private ArrayList<UserFriend> mMsgData;
    private UserFriend mUserFriend;
    private ChatListAdapter chatListAdapter;
    private Map<String, EMConversation> conversations;
    private Thread mThread;
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
                    conversations = EMClient.getInstance().chatManager().getAllConversations();
                    String _username = getIntent().getStringExtra("userName");
                    Log.d("msg",_username);

                    EMConversation conversation = EMClient.getInstance().chatManager().getConversation(_username);
                    //获取此会话在本地的所有的消息数量
                    conversation.getAllMsgCount();

                    Log.d("msg",""+ conversation.getAllMsgCount());

                } catch (Exception e) {
                }
                if (conversations.size() != 0) {
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
        //得到msgList的点击下标
        mIndex = getIntent().getIntExtra("index", -1);
        mMsgData = new ArrayList<UserFriend>();
        Set<String> friends = conversations.keySet();
        ArrayList<String> friendsname = new ArrayList<String>();
        for (String user : friends) {
            friendsname.add(user);
        }
        mUserFriend = new UserFriend();
        mUserFriend.setUserName(friendsname.get(mIndex));
        mUserFriend.setMessages(conversations.get(friendsname.get(mIndex)).getAllMessages());
        mMsgData.add(mUserFriend);

        //设置头部联系对象
        chatActivityContackTxt.setText(friendsname.get(mIndex));
        chatListAdapter = new ChatListAdapter(mContext, mMsgData);
        chatListView.setAdapter(chatListAdapter);

    }
    @OnClick(R.id.chat_back_txt)
    public void onClick(){
        finish();
    }


}
