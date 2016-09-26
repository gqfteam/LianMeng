package com.hkd.lianmeng.model;

import java.sql.Timestamp;

/**
 * Created by johe on 2016/9/26.
 */
public class Goods {
    int id;
    String phone;
    String goodsname;
    String goodsdetails;
    String goodsimgaddress;
    String userphonenum;
    double goodsprice;
    Timestamp shelvestime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsspecies() {
        return goodsspecies;
    }

    public void setGoodsspecies(String goodsspecies) {
        this.goodsspecies = goodsspecies;
    }

    public String getGoodsclassification() {
        return goodsclassification;
    }

    public void setGoodsclassification(String goodsclassification) {
        this.goodsclassification = goodsclassification;
    }

    public String getGoodscampus() {
        return goodscampus;
    }

    public void setGoodscampus(String goodscampus) {
        this.goodscampus = goodscampus;
    }

    public String getGoodsuniversity() {
        return goodsuniversity;
    }

    public void setGoodsuniversity(String goodsuniversity) {
        this.goodsuniversity = goodsuniversity;
    }

    public String getGoodscity() {
        return goodscity;
    }

    public void setGoodscity(String goodscity) {
        this.goodscity = goodscity;
    }

    public Timestamp getShelvestime() {
        return shelvestime;
    }

    public void setShelvestime(Timestamp shelvestime) {
        this.shelvestime = shelvestime;
    }

    public double getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(double goodsprice) {
        this.goodsprice = goodsprice;
    }

    public String getUserphonenum() {
        return userphonenum;
    }

    public void setUserphonenum(String userphonenum) {
        this.userphonenum = userphonenum;
    }

    public String getGoodsimgaddress() {
        return goodsimgaddress;
    }

    public void setGoodsimgaddress(String goodsimgaddress) {
        this.goodsimgaddress = goodsimgaddress;
    }

    public String getGoodsdetails() {
        return goodsdetails;
    }

    public void setGoodsdetails(String goodsdetails) {
        this.goodsdetails = goodsdetails;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    String goodscity;
    String goodsuniversity;
    String goodscampus;
    String goodsclassification;
    String goodsspecies;
}
