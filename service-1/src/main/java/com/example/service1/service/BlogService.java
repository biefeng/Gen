package com.example.service1.service;

import com.example.service1.common.model.PageVo;
import com.example.service1.dao.BlogDao;
import com.example.service1.model.BlogPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author : BieFeNg
 * @DATE : 2018/11/11 22:52
 */

@Service
public class BlogService {

    @Autowired
    private BlogDao blogDao;


    public void save(BlogPO blogPO) {

        String md = blogPO.getMd();
        blogPO.setMd_splits(handleStr(md));
        String html = blogPO.getHtml();
        blogPO.setHtml_splits(handleStr(html));

        blogDao.save(blogPO);
    }

    public List<String> handleStr(String str) {
        int limit = 4000;
        List<String> result = new ArrayList();
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        byte[] md_bytes = str.getBytes();
        int var = (str.length() + limit - 1) / limit;
        for (int i = 0; i < var; i++) {
            int endIndex = i == var - 1 ? str.length() : (i+1) * limit;
            result.add(str.substring(i * limit, endIndex));
        }
        return result;
    }

    public Map<String, String> get(String id) {
        return blogDao.get(id);
    }

    public void delete(String id){
        blogDao.delete(id);
    }

    public List<Map<String, String>> list(PageVo pageVo) {
        return blogDao.list(pageVo);
    }
}
