package com.hkd.lianmeng.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.base.BaseApplication;
import com.hkd.lianmeng.model.LoginUser;
import com.hkd.lianmeng.tools.LoginUserInfoUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private LoginUserInfoUtils mLoginUserInfoUtils;

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

                //保存用户名和密码在本地
                mLoginUser=new LoginUser();
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
