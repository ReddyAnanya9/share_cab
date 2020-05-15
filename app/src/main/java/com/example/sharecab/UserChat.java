package com.example.sharecab;

import java.util.Date;

public class UserChat {
    private String msg;
    private String user_name;
    private long msgtime;

    public UserChat(String msg, String user_name) {
        this.msg = msg;
        this.user_name = user_name;
        msgtime = new Date().getTime();
    }
    public UserChat(){

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public long getMsgtime() {
        return msgtime;
    }

    public void setMsgtime(long msgtime) {
        this.msgtime = msgtime;
    }
}


