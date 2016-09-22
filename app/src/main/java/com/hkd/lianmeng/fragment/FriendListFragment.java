package com.hkd.lianmeng.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.adapter.FriendsListAdapter;
import com.hkd.lianmeng.model.User;
import com.hkd.lianmeng.tools.DemoHelper;
import com.hkd.lianmeng.view.SideBar;
import com.hyphenate.chat.EMClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * gqf
 * 通讯录好友列表
 */
public class FriendListFragment extends Fragment {

    @Bind(R.id.friend_list_allfriends)
    ListView friendListAllfriends;
    @Bind(R.id.friend_list_sidebar)
    SideBar friendListSidebar;
    @Bind(R.id.friend_list_dialog)
    TextView friendListDialog;
    private List<String> usernames;
    private FriendsListAdapter mFriendsListAdapter;
    private Thread mThread;
    private DemoHelper demoHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friend_list, container, false);
        ButterKnife.bind(this, view);
        mThread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                }catch (Exception e){

                }
                Message message = new Message();
                message.what = 1;
                myHandler.sendMessage(message);
            }
        });
        mThread.start();

        return view;
    }
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    initList(usernames);
                break;
            }
            super.handleMessage(msg);
        }
    };
    public void initSideBar(){
        friendListSidebar.setTextView(friendListDialog);
        // 设置右侧触摸监听
        friendListSidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                int position = mFriendsListAdapter.getPositionForSection(s
                        .charAt(0));
                if(position != -1){
                    friendListAllfriends.setSelection(position);
                }

            }
        });
    }
    public void initList(List<String> usernames){
        ArrayList<User> users=new ArrayList<>();
        for(int i=0;i<usernames.size();i++){
            User user=new User();
            user.setUserName(usernames.get(i));
            users.add(user);
        }
        demoHelper=DemoHelper.getInstance();
        users=demoHelper.filledData(users);
        mFriendsListAdapter=new FriendsListAdapter(getContext(),users);
        friendListAllfriends.setAdapter(mFriendsListAdapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    //    id,username,password(服务器)
    // id,username,userimg+。。。（服务器）
    //id,username,聊天记录,好友username(本地，表名username+。。。)

}
