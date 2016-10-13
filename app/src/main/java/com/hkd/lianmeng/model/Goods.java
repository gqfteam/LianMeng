package com.hkd.lianmeng.model;

import java.sql.Timestamp;

/**
 * Created by johe on 2016/9/26.
 * gqf
 * 商品模型
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
    String goodscity;
    String collegeName;
    String campusname;
    String goodsclassification;
    String speciesname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getGoodsdetails() {
        return goodsdetails;
    }

    public void setGoodsdetails(String goodsdetails) {
        this.goodsdetails = goodsdetails;
    }

    public String getGoodsimgaddress() {
        return goodsimgaddress;
    }

    public void setGoodsimgaddress(String goodsimgaddress) {
        this.goodsimgaddress = goodsimgaddress;
    }

    public String getUserphonenum() {
        return userphonenum;
    }

    public void setUserphonenum(String userphonenum) {
        this.userphonenum = userphonenum;
    }

    public double getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(double goodsprice) {
        this.goodsprice = goodsprice;
    }

    public Timestamp getShelvestime() {
        return shelvestime;
    }

    public void setShelvestime(Timestamp shelvestime) {
        this.shelvestime = shelvestime;
    }

    public String getGoodscity() {
        return goodscity;
    }

    public void setGoodscity(String goodscity) {
        this.goodscity = goodscity;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCampusname() {
        return campusname;
    }

    public void setCampusname(String campusname) {
        this.campusname = campusname;
    }

    public String getGoodsclassification() {
        return goodsclassification;
    }

    public void setGoodsclassification(String goodsclassification) {
        this.goodsclassification = goodsclassification;
    }

    public String getSpeciesname() {
        return speciesname;
    }

    public void setSpeciesname(String speciesname) {
        this.speciesname = speciesname;
    }
}
