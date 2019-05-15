package com.example.commservice.controller;

import com.example.commservice.common.util.SftpUtils;
import com.example.commservice.dao.FileHandleDao;
import com.example.commservice.model.ResponseEntity;
import com.example.commservice.service.FileHandleService;
import org.apache.catalina.core.ApplicationPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

@RestController
@RequestMapping("file")
public class FileHandleController {

    @Autowired
    private SftpUtils sftpUtils;

    @Autowired
    private FileHandleService service;

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

                   // fileName = new String(fileName.getBytes("utf-8") ,"utf-8");

                }
            }
            if (ins != null && path != null && fileName != null) {
                sftpUtils.upload(path, ins, fileName);
            } else {
                throw new RuntimeException("缺少参数");
            }
            service.saveAttach(fileName,path);
            entity.setSuccess(true);
            entity.setMessage("上传成功！");
        } catch (Exception e) {
            entity.setSuccess(false);
            entity.setMessage(e.getMessage());
            e.printStackTrace();
        }

        return entity;
    }

    @GetMapping
    public ResponseEntity download(String guid) {
        ResponseEntity entity = new ResponseEntity();


        return entity;
    }
}
