package com.example.commservice.dao;

import org.apache.ibatis.annotations.Param;

public interface FileHandleDao {

    void saveAttach(@Param("fileName") String fileName, @Param("filePath") String filePath);

}
