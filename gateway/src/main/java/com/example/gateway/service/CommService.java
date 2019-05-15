package com.example.gateway.service;

import com.example.gateway.dao.UserDao;
import com.example.gateway.model.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommService {

    @Autowired
    private UserDao userDao;

    public void registry(UserPO userPO) {
        userDao.registry(userPO);
    }

    public UserPO getUser(String username) {
        return userDao.getUser(username);
    }

}
