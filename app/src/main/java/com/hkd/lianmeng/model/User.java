package com.hkd.lianmeng.model;

/**
 * Created by johe on 2016/9/21.
 */
public class User {
    private String username;
    private String sortLetters;  //显示数据拼音的首字母
    public String getUserName() {
        return username;
    }
    public void setUserName(String username) {
        this.username = username;
    }
    public String getSortLetters() {
        return sortLetters;
    }
    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }
}
