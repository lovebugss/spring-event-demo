package com.itrjp.demo.service;

import com.itrjp.demo.event.UserRegisterEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author : renjp
 * @date : 2021-03-24 23:16
 **/
@Service

public class UserService {
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 注册
     *
     * @param username 用户名
     */
    @Transactional(rollbackFor = {Exception.class})
    public void register(String username) {
        // 其他操作。。。

        // 发布事件
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this, username));

    }

    /**
     * 事务提交之后触发
     *
     * @param userRegisterEvent 用户注册事件
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterRegister(UserRegisterEvent userRegisterEvent) {
        // 通知
        String username = userRegisterEvent.getUsername();
        // 其他操作
    }
}
