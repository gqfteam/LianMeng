package com.hkd.lianmeng.Abstract;

import android.util.Log;

import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by johe on 2016/9/26.
 */
public abstract class GoodsCallback extends Callback<String>{
    @Override
    public String parseNetworkResponse(Response response, int i) throws Exception {
        String string = response.body().string();
                Log.i("gqf",string);
                //Goods goods = new Gson().fromJson(string, Goods.class);
                return string;
    }
}
