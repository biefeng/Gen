package com.example.service1;

import com.jcraft.jsch.*;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class GenTest {

    @Test
    public void name() {
        try {
            JSch jSch = new JSch();
            Session session = jSch.getSession("root", "192.168.186.137", 22);
            session.setPassword("272232");
            Properties config = new Properties();
            config.setProperty("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();

            ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
            sftp.cd("/home/biefeng/aegis");
            FileInputStream fis = new FileInputStream("C:\\Users\\33504\\Desktop\\total.txt");
            sftp.put(fis,"total.txt");

            cd(sftp,"/usr/home/testCd/gen");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 进入目录，如果不存在就创建目录
     *
     * @param path
     */
    private void cd(ChannelSftp sftp, String path) throws SftpException {
        if (!path.startsWith("/")) {
            throw new RuntimeException("请输入完整路径");
        }
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        int index = 0;
        String[] strs = path.split("/");
        for (String str : strs) {
            try {
                if (index++ == 0) {
                    sftp.cd("/");
                    continue;
                }
                sftp.cd(str);
            } catch (Exception e) {
                sftp.mkdir(str);
                sftp.cd(str);
            }
        }


    }
}
