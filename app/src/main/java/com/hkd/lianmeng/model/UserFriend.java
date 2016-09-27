package com.hkd.lianmeng.model;

import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Created by johe on 2016/9/22.
 * gqf
 * 用户好友
 */
public class UserFriend extends User{
    private List<EMMessage> messages;//聊天记录
    private String sortLetters;  //显示数据拼音的首字母

    private int mesCount;

    public int getMesCount() {
        return mesCount;
    }

    public void setMesCount(int mesCount) {
        this.mesCount = mesCount;
    }


    public String getSortLetters() {
        return sortLetters;
    }
    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public List<EMMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<EMMessage> messages) {
        this.messages = messages;
    }
}
