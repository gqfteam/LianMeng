package com.hkd.lianmeng.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import com.hyphenate.easeui.controller.EaseUI;
import com.hkd.lianmeng.model.LoginUser;
import com.hkd.lianmeng.model.SearchConditions;
import com.hkd.lianmeng.model.User;
import com.hkd.lianmeng.tools.LoginUserInfoUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;


/**
 * Created by Administrator on 2015/12/19 0019.
 * gqf
 * 环信初始化
 */
public class BaseApplication extends Application {

    public static Context applicationContext;
    //public static DemoHXSDKHelper hxSDKHelper = new DemoHXSDKHelper();
    public static boolean isLogin=false;
    public static SearchConditions mSearchConditions;
    public static User mUser;
    private LoginUserInfoUtils mLoginUserInfoUtils;
    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        mSearchConditions=new SearchConditions();
        initHuanXinParams();
        initOkHttp();

        /*
        * 柳亚婷  极光推送
        */
        //设置极光推送的Debug模式开启，这样就可以在日志当中查看相应的输出，就可以知道
        //我们项目与极光推送的一些链接的一些状态
        JPushInterface.setDebugMode(true);
        //初始化代码
        Toast.makeText(this,"aaaaa",Toast.LENGTH_LONG).show();
        JPushInterface.init(this);
       /*
       * 极光推送设置结束
       */

    }
    /**
     * 初始化okhttp
     */
    private void initOkHttp(){
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .cookieJar(cookieJar)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

    }
    /**
     * 初始化环信
     */

    private void initHuanXinParams() {

        EMOptions options = new EMOptions();
        options.setAutoLogin(false);
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        //初始化EaseUI
        EaseUI.getInstance().init(applicationContext, options);

        //建议初始化SDK的时候设置成每个会话默认load一条消息，节省加载会话的时间，方法为：
        //options.setNumberOfMessagesLoaded(10);
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null ||!processAppName.equalsIgnoreCase(applicationContext.getPackageName())) {

            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
        //初始化
        EMClient.getInstance().init(applicationContext, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
        //EaseUI.getInstance().init(applicationContext, options);

        //判断用户是否登录过
        isLogin();





    }
    private EMMessageListener msgListener;
    private  void getMsg(){
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
        msgListener = new EMMessageListener() {

            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                //收到消息
                Log.i("gqf","aaaaaaaaaaaaaaagqf"+messages.size());
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
            }

            @Override
            public void onMessageReadAckReceived(List<EMMessage> messages) {
                //收到已读回执
            }

            @Override
            public void onMessageDeliveryAckReceived(List<EMMessage> message) {
                //收到已送达回执
            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
            }
        };
    }
    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }
    /**
     * 获取当前登陆用户名
     *
     * @return
     */
    public static String getUserName() {
        return null;
    }
    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        //EMClient.getInstance().logout(true);
        super.onTerminate();
    }
    /**
     * 退出登录,清空数据
     */
    public void logout(final EMCallBack emCallBack) {
        // 先调用sdk logout，在清理app中自己的数据
        //hxSDKHelper.logout(emCallBack);
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }



    /**
     * 读取登录信息，判断是否登录过
     */
    public void isLogin() {
        mLoginUserInfoUtils = LoginUserInfoUtils.getLoginUserInfoUtils();
        LoginUser mLoginUser ;
        try {
            mLoginUser = mLoginUserInfoUtils.getLoginUserInfo(applicationContext, LoginUserInfoUtils.KEY);
            if(mLoginUser!=null){
                mUser.setUserName(mLoginUser.getUserName());
                //这里先设置每回打开app前都要退出登录
                Log.i("gqf","用户登录过");
                isLogin=false;
                EMClient.getInstance().logout(true, new EMCallBack() {

                    @Override
                    public void onSuccess() {
                        // TODO Auto-generated method stub
                        Log.i("gqf","退出登录");
                    }

                    @Override
                    public void onProgress(int progress, String status) {
                        // TODO Auto-generated method stub
                        Log.i("gqf","退出登录中");
                    }

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        Log.i("gqf","退出登录失败");
                    }
                });
            }else{
                Log.i("gqf","用户没有登录");
            }

        }
        catch (Exception e){

        }
    }
}
