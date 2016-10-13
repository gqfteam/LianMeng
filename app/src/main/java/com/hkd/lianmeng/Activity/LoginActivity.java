package com.hkd.lianmeng.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hkd.lianmeng.R;
import com.hkd.lianmeng.base.BaseApplication;
import com.hkd.lianmeng.model.LoginUser;
import com.hkd.lianmeng.tools.DemoHelper;
import com.hkd.lianmeng.tools.LoginUserInfoUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by johe on 2016/9/21.
 * gqf
 * 登陆界面
 */
public class LoginActivity extends Activity {


    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.login_botton_register_btn)
    Button loginBottonRegisterBtn;
    @Bind(R.id.login_botton_login_btn)
    Button loginBottonLoginBtn;
    private LoginUser mLoginUser;
    private EMMessageListener msgListener;
    private LoginUserInfoUtils mLoginUserInfoUtils;
    private DemoHelper mDemoHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.login_botton_register_btn, R.id.login_botton_login_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_botton_register_btn:
                //跳转注册界面

                break;
            case R.id.login_botton_login_btn:
                //登录成功后跳转通讯录界面
                gotoLogin();

                break;
        }
    }

    /**
     * 接收消息监听
     * @param _pStrAcct
     */
    private  void getMsg(final String _pStrAcct){
        Log.i("Daniel","getMsg");

        msgListener = new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                //收到消息
                Log.i("Daniel","收到消息");
                Log.i("Daniel","aaaaaaaaaaaaaaa:"+messages.size());

                for (EMMessage msg:messages){
                    Log.i("Daniel","收到消息时间"+msg.getMsgTime());
                    String _time = mDemoHelper.getTimeLongToString(msg.getMsgTime());
                    Log.i("Daniel","收到消息时间"+_time);
                    saveChatMsg(msg.getUserName(),_pStrAcct,msg.getBody().toString().substring(5,msg.getBody().toString().length()-1),_time);
                }
                Log.i("Daniel","收到消息结束");

            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
                Log.i("Daniel","收到透传消息");
            }

            @Override
            public void onMessageReadAckReceived(List<EMMessage> messages) {
                //收到已读回执
                Log.i("Daniel","收到已读回执");
            }

            @Override
            public void onMessageDeliveryAckReceived(List<EMMessage> message) {
                //收到已送达回执
                Log.i("Daniel","收到已送达回执");
            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
                Log.i("Daniel","消息状态变动");
            }
        };

        EMClient.getInstance().chatManager().addMessageListener(msgListener);
        Log.i("Daniel","getMsg()结束");

    }

    /**
     * 保存聊天信息
     * @param rName
     * @param sName
     * @param msgContent
     * @param msgTime
     */
    private void saveChatMsg(final String rName, final String sName, final String msgContent,final String msgTime){

        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        Log.i("Daniel","aaaaaaaaaaaaaaa:"+rName+sName+msgContent+msgTime);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "");
        String url = "http://192.168.1.196:8080/MFaceService/ChatMessageAction_addChatMessage?";
        String params = "params={\"sName\":\""+sName+"\",\"rName\":\""+rName+"\",\"msgContent\":\""+msgContent+"\",\"msgTime\":\""+msgTime+"\"}";
        final Request request = new Request.Builder()
                .url(url+params)
                .post(body)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("Daniel","有监听请求失败！");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("Daniel","有监听请求成功！");

            }
        });

    }

    private void gotoLogin() {


        final String strAcct = username.getText().toString().trim();
        final String strPwd = password.getText().toString().trim();
        if (TextUtils.isEmpty(strAcct)) {
            // ToastUtils.showToast(this, "账号不为空！");
            return;
        }
        if (TextUtils.isEmpty(strPwd)) {
            // ToastUtils.showToast(this, "密码不为空！");
            return;
        }
        loginBottonLoginBtn.setEnabled(false);
        EMClient.getInstance().login(strAcct, strPwd, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.d("main", "登录聊天服务器成功！");
                mDemoHelper = DemoHelper.getInstance();
                getMsg(strAcct);

                //保存用户名和密码在本地
                mLoginUser=new LoginUser(strAcct);
                mLoginUser.setUserName(strAcct);
                mLoginUser.setPassword(strPwd);
                mLoginUserInfoUtils=LoginUserInfoUtils.getLoginUserInfoUtils();
                try {
                    mLoginUserInfoUtils.saveLoginUserInfo(getApplication(), LoginUserInfoUtils.KEY, mLoginUser);
                    BaseApplication.isLogin=true;
                    BaseApplication.mUser.setUserName(strAcct);
                }
                catch (Exception e){

                }
                startActivity(new Intent(LoginActivity.this, ContactActivity.class));
                finish();
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {

                Log.d("main", "登录聊天服务器失败！");
                //Toast.makeText(LoginActivity.this,"登录聊天服务器失败",Toast.LENGTH_LONG).show();
                loginBottonLoginBtn.setEnabled(true);
            }
        });
    }

}
