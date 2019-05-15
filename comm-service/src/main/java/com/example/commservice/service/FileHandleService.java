package com.example.commservice.service;

import com.example.commservice.dao.FileHandleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileHandleService {

    @Autowired
    private FileHandleDao dao;

    public void saveAttach(String fileName, String filePath) {
        dao.saveAttach(fileName, filePath);
    }
}
