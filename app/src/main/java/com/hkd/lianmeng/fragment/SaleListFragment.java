package com.hkd.lianmeng.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.base.BaseApplication;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SaleListFragment extends Fragment {

    String goodsJson;
    @Bind(R.id.sale_fragment_list)
    ListView saleFragmentList;

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

    public void getJson() {

        // http://localhost:8080/MFace/goodsinfo_getUsersGoodsInfo?params={%22phone%22:%2215670702651%22}
        String url = "http://localhost:8080/MFace/goodsinfo_getUsersGoodsInfo?params={\"goodscity\":" +
                "" +"\""+ BaseApplication.mSearchConditions.getCity()+"\"" + ",\"goodsuniversity\":" +
                "" +"\""+ BaseApplication.mSearchConditions.getUniversity()+"\"" + ",\"goodscampus\":" +
                "" +"\""+ BaseApplication.mSearchConditions.getCampus()+"\"" + ",\"goodsclassification\":" +
                "" +"\""+ BaseApplication.mSearchConditions.getClassification()+"\"" + ",\"goodsspecies\":" +
                "" +"\""+ BaseApplication.mSearchConditions.getSpecies()+"\"" +
                "}";
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url(url)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("gqf", "服务器响应失败" );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                goodsJson = response.body().string();
                Log.i("gqf", "aaaaaaaaaaaaaaa" + goodsJson);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
