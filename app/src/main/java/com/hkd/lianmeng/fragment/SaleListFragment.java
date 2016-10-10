package com.hkd.lianmeng.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hkd.lianmeng.R;
import com.hkd.lianmeng.adapter.SaleListAdapter;
import com.hkd.lianmeng.base.BaseApplication;
import com.hkd.lianmeng.model.Goods;
import com.hkd.lianmeng.tools.ReadJson;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * gqf
 * 商品列表fragment
 */
public class SaleListFragment extends Fragment {

    String goodsJson="";
    @Bind(R.id.sale_fragment_list)
    ListView saleFragmentList;
    private ArrayList<Goods> mGoodses;
    private ReadJson mReadJson;
    private SaleListAdapter mSaleListAdapter;
    public SaleListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale_list, container, false);
        ButterKnife.bind(this, view);


        getJson();

        return view;

    }



    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mReadJson=ReadJson.getInstance();
                    mGoodses=mReadJson.readGoodsJson(goodsJson);
                    mSaleListAdapter=new SaleListAdapter(getContext(),mGoodses);
                    saleFragmentList.setAdapter(mSaleListAdapter);
                    break;
            }
            super.handleMessage(msg);
        }
    };
    public void getJson() {

        final String url = "http://192.168.1.136:8080/MFace/goodsinfo_getUsersGoodsInfo?params={%22goodscity%22:" +
                "" +"%22"+ BaseApplication.mSearchConditions.getCity()+"%22" + ",%22goodsuniversity%22:" +
                "" +"%22"+ BaseApplication.mSearchConditions.getUniversity()+"%22" + ",%22goodscampus%22:" +
                "" +"%22"+ BaseApplication.mSearchConditions.getCampus()+"%22" + ",%22goodsclassification%22:" +
                "" +"%22"+ BaseApplication.mSearchConditions.getClassification()+"%22" + ",%22goodsspecies%22:" +
                "" +"%22"+ BaseApplication.mSearchConditions.getSpecies()+"%22" +
                "}";
        Log.i("gqf",url);
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    goodsJson=post(url,"");
                    Message message = new Message();
                    message.what = 1;

                    myHandler.sendMessage(message);
                }catch (IOException  e){
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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
