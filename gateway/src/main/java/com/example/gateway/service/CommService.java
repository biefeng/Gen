package com.example.gateway.service;

import com.example.gateway.dao.UserDao;
import com.example.gateway.model.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommService {

    @Autowired
    private UserDao userDao;

    public void registry(UserPO userPO) {
        userDao.registry(userPO);
    }

    @Transactional("getUser")
    public UserPO getUser(String username) {
        return userDao.getUser(username);
    }

}
