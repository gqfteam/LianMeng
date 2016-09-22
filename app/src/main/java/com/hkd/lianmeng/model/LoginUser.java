package com.hkd.lianmeng.model;

/**
 * Created by johe on 2016/9/22.
 */
public class LoginUser extends User{
    private String password;//登录用户密码

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
