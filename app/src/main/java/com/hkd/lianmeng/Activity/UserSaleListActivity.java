package com.hkd.lianmeng.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.hkd.lianmeng.R;
import com.hkd.lianmeng.adapter.SaleListAdapter;
import com.hkd.lianmeng.base.BaseApplication;
import com.hkd.lianmeng.model.Goods;
import com.hkd.lianmeng.tools.ReadJson;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by johe on 2016/10/9.
 */

public class UserSaleListActivity extends Activity {


    @Bind(R.id.User_salelist_top_back_btn)
    Button UserSalelistTopBackBtn;
    @Bind(R.id.User_salelist_top_add_btn)
    Button UserSalelistTopAddBtn;
    @Bind(R.id.User_salelist_top_delete_btn)
    Button UserSalelistTopDeleteBtn;
    @Bind(R.id.user_sale_fragment_list)
    ListView userSaleFragmentList;
    Context mContext;
    ReadJson mReadJson;
    ArrayList<Goods> mGoodses;
    SaleListAdapter mSaleListAdapter;

    String goodsJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sale_list);
        ButterKnife.bind(this);
        mReadJson= ReadJson.getInstance();
        mContext=this;

    }

    @Override
    protected void onResume() {
        super.onResume();

        getJson();
    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if(goodsJson.contains("false")||goodsJson.contains("Failed")){
                        Toast.makeText(mContext,"网络问题，查询数据失败！",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    mGoodses=mReadJson.readGoodsJson(goodsJson);
                    mSaleListAdapter=new SaleListAdapter(mContext,mGoodses);
                    userSaleFragmentList.setAdapter(mSaleListAdapter);
                    break;
            }
            super.handleMessage(msg);
        }
    };
    public void getJson() {

        final String url =  BaseApplication.HTTPCLIENTADDRESS+"goodsinfo_getUsersGoodsInfo?params={%22phone%22:" +
                "" +"%22"+ "15670702651" +"%22"+
                "}";
        Log.i("gqf",url);

        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    goodsJson=post(url,"");
                    Log.i("gqf",goodsJson);
                    Message message = new Message();
                    message.what = 1;
                    myHandler.sendMessage(message);
                }catch (IOException e){
                    Log.i("gqf",e.getMessage().toString());
                }
            }
        });
        t.start();

    }
    OkHttpClient client;
    String post(String url, String json) throws IOException {
        client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                //.url("http://192.168.1.136:8080/MFace/userinfo_getUserInfo?params={%22phone%22:%2218860316546%22,%22passWord%22:%22123456%22}")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    @OnClick({R.id.User_salelist_top_back_btn, R.id.User_salelist_top_add_btn, R.id.User_salelist_top_delete_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.User_salelist_top_back_btn:
                finish();
                break;
            case R.id.User_salelist_top_add_btn:
                //跳转页面，增加新的商品
                startActivity(new Intent(UserSaleListActivity.this, AddUserGoodsActivity.class));

                break;
            case R.id.User_salelist_top_delete_btn:
                //删除当前选中商品


                break;
        }
    }
}
