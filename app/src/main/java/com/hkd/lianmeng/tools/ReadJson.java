package com.hkd.lianmeng.tools;

import com.google.gson.Gson;
import com.hkd.lianmeng.model.Campus;
import com.hkd.lianmeng.model.Goods;
import com.hkd.lianmeng.model.GoodsClassification;
import com.hkd.lianmeng.model.GoodsSpecis;
import com.hkd.lianmeng.model.SaleChooseModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by johe on 2016/9/19.
 * gqf
 * 用来解析Json为model
 */
public class ReadJson {
    private static class holder {
        private static ReadJson rj = new ReadJson();
    }

    private ReadJson() {
    }

    public static ReadJson getInstance() {
        return holder.rj;
    }

    /**
     * gqf
     * 解析义卖界面顶部条件JSon
     * @param json
     * @return
     */
    public ArrayList<SaleChooseModel> readSaleTopChooseJson(String json) {

        ArrayList<SaleChooseModel> scms = new ArrayList<SaleChooseModel>();
        SaleChooseModel scm = null;
        try {
            JSONArray datas = new JSONArray(json);

            int length = datas.length();
            for (int i = 0; i < length; i++) {
                JSONObject dataObj = datas.getJSONObject(i);
                scm=new SaleChooseModel();
                scm.setName(dataObj.getString("n"));

                JSONArray dataAry=dataObj.getJSONArray("c");
                int clenth=dataAry.length();
                ArrayList<String> have = new ArrayList<String>();
                for(int j=0;j<clenth;j++){
                    have.add((String)dataAry.get(j));
                }
                scm.setHave(have);
                scms.add(scm);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return scms;
    }
    /**
     * 返回goods列表
     */
    public ArrayList<Goods> readGoodsJson(String goodsJson){
        ArrayList<Goods> mGoodses=new ArrayList<Goods>();
        try {
            JSONObject datas = new JSONObject(goodsJson);
            JSONArray Goods=datas.getJSONArray("msg");
            Gson gson=new Gson();
            for(int i=0;i<Goods.length();i++) {
                Goods goods = gson.fromJson(Goods.get(i).toString(), Goods.class);
                mGoodses.add(goods);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mGoodses;
    }
    /**
     * 返回campus列表
     */
    public ArrayList<Campus> readCampusJson(String goodsJson){
        ArrayList<Campus> mGoodses=new ArrayList<Campus>();
        try {
            JSONObject datas = new JSONObject(goodsJson);
            JSONArray Goods=datas.getJSONArray("msg");
            Gson gson=new Gson();
            for(int i=0;i<Goods.length();i++) {
                Campus goods = gson.fromJson(Goods.get(i).toString(), Campus.class);
                mGoodses.add(goods);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mGoodses;
    }
    /**
     * 返回classification列表
     */
    public ArrayList<GoodsClassification> readclassificationJson(String goodsJson){
        ArrayList<GoodsClassification> mGoodses=new ArrayList<GoodsClassification>();
        try {
            JSONObject datas = new JSONObject(goodsJson);
            JSONArray Goods=datas.getJSONArray("msg");
            Gson gson=new Gson();
            for(int i=0;i<Goods.length();i++) {
                GoodsClassification goods = gson.fromJson(Goods.get(i).toString(), GoodsClassification.class);
                mGoodses.add(goods);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mGoodses;
    }
    /**
     * 返回classification列表
     */
    public ArrayList<GoodsSpecis> readSpecisJson(String goodsJson){
        ArrayList<GoodsSpecis> mGoodses=new ArrayList<GoodsSpecis>();
        try {
            JSONObject datas = new JSONObject(goodsJson);
            JSONArray Goods=datas.getJSONArray("msg");
            Gson gson=new Gson();
            for(int i=0;i<Goods.length();i++) {

                GoodsSpecis goods = gson.fromJson(Goods.get(i).toString(), GoodsSpecis.class);
                mGoodses.add(goods);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mGoodses;
    }
}
