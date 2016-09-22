package com.hkd.lianmeng.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
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
    private Context mContext;
    private ArrayList<UserFriend> mUserFriends;
    private UserFriend mUserFriend;
    private ChatListAdapter chatListAdapter;
    private Map<String, EMConversation> conversations;
    private Thread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        mThread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    conversations = EMClient.getInstance().chatManager().getAllConversations();



                }catch (Exception e){
                }
                if(conversations.size()!=0) {
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
        mUserFriends=new ArrayList<>();
        Set<String> friends=conversations.keySet();
        ArrayList<String> friendsname=new ArrayList<String>() ;
        for (String user : friends) {
            friendsname.add(user);
        }
        for(int i=0;i<conversations.size();i++){
            mUserFriend=new UserFriend();
            mUserFriend.setUserName(friendsname.get(i));
            mUserFriend.setMessages(conversations.get(friendsname.get(i)).getAllMessages());
            mUserFriends.add(mUserFriend);
        }

        chatListAdapter = new ChatListAdapter(mContext,mUserFriends);
        chatListView.setAdapter(chatListAdapter);

    }

    @OnClick({R.id.chat_back_txt, R.id.chat_listView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chat_back_txt:
                break;
            case R.id.chat_listView:
                break;
        }
    }
}
