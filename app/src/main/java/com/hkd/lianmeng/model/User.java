package com.hkd.lianmeng.model;

import com.hyphenate.easeui.domain.EaseUser;

/**
 * Created by johe on 2016/9/21.
 * gqf
 * 父类user
 */
public class User extends EaseUser {
    private String username;

    public User(String username) {
        super(username);
    }

    public String getUserName() {
        return username;
    }
    public void setUserName(String username) {
        this.username = username;
    }
}
