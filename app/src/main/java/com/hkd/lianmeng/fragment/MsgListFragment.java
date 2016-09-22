package com.hkd.lianmeng.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.adapter.MsgListAdapter;
import com.hkd.lianmeng.model.UserFriend;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * gqf
 * 通讯录消息列表
 */
public class MsgListFragment extends Fragment {

    @Bind(R.id.contact_msg_list)
    ListView contactMsgList;
    private ArrayList<UserFriend> mUserFriends;
    private UserFriend mUserFriend;
    private MsgListAdapter msgListAdapter;
    private Map<String, EMConversation> conversations;
    private Thread mThread;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_msg_list, container, false);
        ButterKnife.bind(this, view);

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


        return view;
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

    public void initList(){
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
        msgListAdapter=new MsgListAdapter(getContext(),mUserFriends);
        contactMsgList.setAdapter(msgListAdapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
