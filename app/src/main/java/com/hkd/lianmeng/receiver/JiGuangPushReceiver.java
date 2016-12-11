package com.hkd.lianmeng.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.hkd.lianmeng.Activity.SigninListActivity;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/9/29.
 */
public class JiGuangPushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        //如果这两个相等，则说明应用已接收到自定义消息
        if (intent.getAction().equals(JPushInterface.ACTION_MESSAGE_RECEIVED)){
            //获取message里的内容
            Bundle bundle=intent.getExtras();
            String title=bundle.getString(JPushInterface.EXTRA_TITLE);
            String message=bundle.getString(JPushInterface.EXTRA_MESSAGE);

//            Toast.makeText(context,"Message title:"+title+" context:"+message,Toast.LENGTH_LONG).show();
        }
        else if(intent.getAction().equals((JPushInterface.ACTION_NOTIFICATION_OPENED))){
            System.out.println("用户点击打开了通知");
            // 在这里可以自己写代码去定义用户点击后的行为
            Intent i = new Intent(context, SigninListActivity.class);  //自定义打开的界面
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

        }

    }
}
