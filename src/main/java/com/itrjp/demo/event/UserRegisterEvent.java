package com.itrjp.demo.event;

import org.springframework.context.ApplicationEvent;

/**
 * 用户注册事件
 *
 * @author : renjp
 * @date : 2021-03-24 23:16
 **/
public class UserRegisterEvent extends ApplicationEvent {
    private String username;

    public UserRegisterEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
