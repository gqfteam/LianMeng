package com.hkd.lianmeng.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.Abstract.GoodsCallback;
import com.hkd.lianmeng.base.BaseApplication;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * gqf
 * 商品列表fragment
 */
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

        String url = "http://localhost:8080/MFace/goodsinfo_getUsersGoodsInfo?params={\"goodscity\":" +
                "" +"\""+ BaseApplication.mSearchConditions.getCity()+"\"" + ",\"goodsuniversity\":" +
                "" +"\""+ BaseApplication.mSearchConditions.getUniversity()+"\"" + ",\"goodscampus\":" +
                "" +"\""+ BaseApplication.mSearchConditions.getCampus()+"\"" + ",\"goodsclassification\":" +
                "" +"\""+ BaseApplication.mSearchConditions.getClassification()+"\"" + ",\"goodsspecies\":" +
                "" +"\""+ BaseApplication.mSearchConditions.getSpecies()+"\"" +
                "}";
        Log.i("gqf",url);
        String goodsUrl="http://localhost:8080/MFace/";//goodsinfo_getUsersGoodsInfo/";
        String meituan="http://www.meituan.com/api/v2/beijing/deals";

        OkHttpUtils
                .post()
                .url(goodsUrl)
                .build()
                .execute(new GoodsCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Log.i("gqf","onError");
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Log.i("gqf","onResponse"+s);
                    }
                });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
