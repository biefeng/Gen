package com.example.service1.dao;

import com.example.service1.common.model.PageVo;
import com.example.service1.model.BlogPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author : BieFeNg
 * @DATE : 2018/11/11 22:53
 */
public interface BlogDao {


    void save(@Param("blogPO") BlogPO blogPO);

    Map<String, String> get(@Param("id") String id);

    void delete(@Param("id") String id);

    List<Map<String, String>> list(@Param("pageVo") PageVo pageVo);
}
