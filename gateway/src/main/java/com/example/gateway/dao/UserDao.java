package com.example.gateway.dao;

import com.example.gateway.model.UserPO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Author : BieFeNg
 * @DATE : 2018/10/18 21:49
 */
public interface UserDao {
    /**
     * registry
     *
     * @param po
     */
    void registry(@Param("po") UserPO po);

    /**
     * login
     *
     * @param userName
     * @return
     */
    UserPO login(@Param("userName") String userName);

    UserPO getUser(String username);
}
