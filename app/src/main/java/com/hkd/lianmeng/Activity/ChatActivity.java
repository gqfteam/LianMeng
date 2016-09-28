package com.hkd.lianmeng.Activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;

import com.example.johe.lianmengdemo.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.easeui.ui.EaseChatFragment;

import java.util.List;

import butterknife.ButterKnife;

import static com.hyphenate.easeui.EaseConstant.CHATTYPE_SINGLE;

/**
 * Created by Administrator on 2016/9/22.
 */

public class ChatActivity extends FragmentActivity {

//    @Bind(R.id.chat_back_txt)
//    TextView chatBackTxt;
//    @Bind(R.id.chat_listView)
//    ListView chatListView;
//    @Bind(R.id.chatActivity_contack_txt)
//    TextView chatActivityContackTxt;
    private Context mContext;
    private List<EMMessage> mMessage;
    private Thread mThread;
    private String mFriendName;
    private EMConversation mConversation;
    private int mIndex = -1;
    private EaseUI easeUI;
    private EaseChatFragment chatFragment;
    private boolean mFriendList_to_ChatFragment_flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_content);
       ButterKnife.bind(ChatActivity.this);
        mIndex = getIntent().getIntExtra("index", -1);
        mFriendName = getIntent().getStringExtra("friendName");
        mFriendList_to_ChatFragment_flag= getIntent().getBooleanExtra("FriendList_to_ChatFragment",false);
//        Log.i("wjd","mFriendName:"+mFriendName);
//        Log.i("wjd","mFriendList_to_ChatFragment_flag:"+mFriendList_to_ChatFragment_flag);
        if(mFriendList_to_ChatFragment_flag){
            goInChat();
        }else{
            thread();
            mThread.start();
        }

    }
    private void thread() {

        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    mConversation = EMClient.getInstance().chatManager().getConversation(mFriendName);
                    //获取所有的会话

                    mMessage = mConversation.getAllMessages();

                   // EaseUser easeUser = new EaseUser(hxIdFrom);


                } catch (Exception e) {
                }
                if (mMessage.size() != 0 ) {
                    Message message = new Message();
                    message.what = 1;
                    myHandler.sendMessage(message);
                }
            }
        });
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
//        chatActivityContackTxt.setText(mFriendName);
        goInChat();

    }

    private void goInChat() {
        //new出EaseChatFragment或其子类的实例
        if(chatFragment==null){
            chatFragment = new EaseChatFragment();
        }
        //传入参数
        Bundle _args = new Bundle();
        _args.putInt(EaseConstant.EXTRA_CHAT_TYPE, CHATTYPE_SINGLE);
        _args.putString(EaseConstant.EXTRA_USER_ID, mFriendName);
        chatFragment.setArguments(_args);
        mFriendList_to_ChatFragment_flag=false;
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();

    }
}
