package com.hkd.lianmeng.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hkd.lianmeng.Activity.SettingActivity;
import com.hkd.lianmeng.R;
import com.hkd.lianmeng.adapter.MeListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author：Administrator on 2016/9/18 09:29
 */
public class MeFragment extends Fragment {
    ArrayList<HashMap<String, Object>> mData;
    @Bind(R.id.listView_fregment_me)
    ListView listViewFregmentMe;
    @Bind(R.id.me_settingClick_lLayout)
    LinearLayout meSettingClickLLayout;
    Context mContext;
    @Bind(R.id.layoutMe_userName_txt)
    TextView layoutMeUserNameTxt;
    @Bind(R.id.layoutMe_studentNum_txt)
    TextView layoutMeStudentNumTxt;
    RequestQueue mQueue;
    String mUrl = "http://192.168.1.196:8080/MFaceService/userinfo_getUserInfo?params={\"phone\":\"13460065326\",\"passWord\":\"123456\"}";

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fregment_me, container, false);
        ButterKnife.bind(this, view);
        mQueue= Volley.newRequestQueue(getActivity());
        //初始化mData,设置适配器
        init();
        Log.i("Daniel","开始获取本机ip");
        String ip=getHostIP();
        Log.i("Daniel","ip"+ip);

        getUserInfo();




        return view;
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Bundle _bundle=msg.getData();
                    setUserInfo(_bundle.getString("_nickName"),_bundle.getString("_studentNum"));
                    break;
            }



            super.handleMessage(msg);
        }
    };

    private void setUserInfo(String _pNickName,String _pStudentNum) {
        layoutMeStudentNumTxt.setText(_pStudentNum);
        layoutMeUserNameTxt.setText(_pNickName);
    }


    /**
     * 从数据库获取登录用户的信息
     */
    private void getUserInfo() {
        StringRequest stringRequest = new StringRequest(mUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.i("Daniel","请求成功！");
                Log.i("Daniel","onResponse:"+s);
                JSONObject js = null;
                try {
                    js = new JSONObject(s);
                    JSONArray ja = js.getJSONArray("datas");
                    String _nickName= ja.getJSONObject(0).getString("nickName");
                    String _studentNum= ja.getJSONObject(0).getString("studentNum");
                    Message msg = new Message();
                    msg.what=1;
                    Bundle _bundle = new Bundle();
                    _bundle.putString("_nickName",_nickName);
                    _bundle.putString("_studentNum",_studentNum);
                    msg.setData(_bundle);
                    handler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


//                ResultMessage resultMessage = gSon.fromJson(s, ResultMessage.class);
//                Log.i("Daniel","resultMessage:"+resultMessage.toString());
//                ArrayList<ShowAllUserInfoView> aList = resultMessage.getDatas();
//
//                Type type = new TypeToken<ArrayList<ShowAllUserInfoView>>() {}.getType();
//                Object fromJson2 = gson.fromJson(String.valueOf(aList), type);
//
//                ArrayList<ShowAllUserInfoView> list = (ArrayList<ShowAllUserInfoView>) fromJson2;
//                String _nickName=list.get(0).getNickName();
//                String _studentNum=list.get(0).getStudentNum();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("Daniel","请求失败！");
                Log.i("Daniel","volleyError："+volleyError);
            }
        });

        mQueue.add(stringRequest);


//
//        //创建okHttpClient对象
//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        //创建一个Request
//        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "");
//
//        final Request request = new Request.Builder()
//                .url("http://192.168.1.196:8080/MFaceService/userinfo_getUserInfo?params={\"phone\":\"13460065326\",\"passWord\":\"123456\"}")
//                .post(body)
//                .build();
//        //new call
//        Call call = mOkHttpClient.newCall(request);
//        //请求加入调度
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.i("Daniel","请求失败！");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.i("Daniel","请求成功！");
//                Gson gSon = new Gson();
//                try {
//                    Log.i("Daniel","response.body()"+response.body().string());
//                    String  _str = response.body().string() ;
//                    ResultMessage<ShowAllUserInfoView> resultMessage =gSon.fromJson(_str, ResultMessage.class);
//                    Log.i("Daniel","resultMessage:"+resultMessage.getDatas().size());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
////                JsonObject jo = new JsonObject().getAsJsonObject(_json);
////                JsonArray ja = (JsonArray) jo.get("datas");
////                String _nickName=ja.get(0).getAsString();
////                String _studentNum=ja.get(0).getAsString();
//
//
//
//            }
//        });
    }


    /**
     * 获取ip地址
     * @return
     */
    public static String getHostIP() {

        String hostIp = null;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia = null;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = ia.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        hostIp = ia.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            Log.i("yao", "SocketException");
            e.printStackTrace();
        }
        return hostIp;

    }

    /**
     * 初始化数据函数
     */
    private void init() {
        mContext = getActivity();
        String[] _txt;
        _txt = getResources().getStringArray(R.array.me_listViewItem_strArray);
        int[] _img = {R.mipmap.me_list_jsxx, R.mipmap.me_list_mmsp, R.mipmap.me_list_lxkf};
        int _length = _txt.length;
        mData = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < _length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("txt", _txt[i]);
            map.put("img", _img[i]);
            mData.add(map);
        }
        //配置自定义listview适配器MyAdapter_me_listView
        MeListAdapter listViewAdapter;
        listViewAdapter = new MeListAdapter(mData, getActivity());
        listViewFregmentMe.setAdapter(listViewAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.me_settingClick_lLayout)
    public void onClick() {
        Intent intent = new Intent(mContext, SettingActivity.class);
        startActivity(intent);
    }
}
