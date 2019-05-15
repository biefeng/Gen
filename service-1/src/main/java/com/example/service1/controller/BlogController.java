package com.example.service1.controller;

import com.example.service1.common.model.PageResult;
import com.example.service1.common.model.PageVo;
import com.example.service1.common.model.ResponseEntity;
import com.example.service1.common.util.SftpUtils;
import com.example.service1.model.BlogPO;
import com.example.service1.service.BlogService;
import org.apache.catalina.core.ApplicationPart;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author : BieFeNg
 * @DATE : 2018/11/10 22:38
 */

@RestController
@RequestMapping("blog")
@CrossOrigin(allowCredentials = "true")
public class BlogController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private SftpUtils sftpUtils;

    @PostMapping("save")
    public ResponseEntity save(@RequestBody BlogPO blogPO, HttpServletRequest request) {
        blogService.save(blogPO);
        return ResponseEntity.ResponseEntityUtil.success(blogPO.getGuid(), "保存成功");
    }

    @GetMapping("/{id}")
    private ResponseEntity get(@PathVariable String id) {
        Map<String, String> blog = blogService.get(id);
        return ResponseEntity.ResponseEntityUtil.success(blog, null);
    }

    @GetMapping("list")
    public ResponseEntity list(@ModelAttribute PageVo pageVo) {
        ResponseEntity entity = new ResponseEntity();
        try {
            List<Map<String, String>> blogIds = blogService.list(pageVo);
            if (null != blogIds && blogIds.size() > 0) {
                PageResult pageResult = (PageResult) blogIds.get(0);
                entity.setData(pageResult.getResult());
                entity.setTotalRows(pageResult.getTotalRows());
            }
        } catch (Exception e) {
            e.printStackTrace();
            entity.setMessage(e.getMessage());
            entity.setSuccess(false);
        }
        return entity;
    }

    @PostMapping("upload")
    public ResponseEntity uploadFile(HttpServletRequest request) {
        ResponseEntity entity = new ResponseEntity();
        try {
            Collection<Part> parts = request.getParts();
            InputStream ins = null;
            String path = null;
            String fileName = null;
            for (Part part : parts) {
                String partName = part.getName();
                ApplicationPart applicationPart = ((ApplicationPart) part);
                if ("path".equals(partName)) {
                    path = applicationPart.getString("UTF-8");
                } else if ("file".equals(partName)) {
                    ins = part.getInputStream();
                    fileName = applicationPart.getSubmittedFileName();
                }
            }
            if (ins != null && path != null && fileName != null) {
                sftpUtils.upload(path, ins, fileName);
            } else {
                throw new RuntimeException("缺少参数");
            }
            entity.setSuccess(true);
            entity.setMessage("上传成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return entity;
    }


}
