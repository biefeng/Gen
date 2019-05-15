package com.example.commservice.common.util;

import com.example.commservice.common.config.SftpProperties;
import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

@Component
public class SftpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SftpUtils.class);

    @Autowired
    private SftpProperties properties;

    private Session session;

    private ThreadLocal<ChannelSftp> sftpThreadLocal = new ThreadLocal<>();

    private Session getSession() {
        JSch jSch = new JSch();

        try {
            session = jSch.getSession(properties.getUserName(), properties.getHost(), properties.getPort());
        } catch (JSchException e) {
            LOGGER.error("SFTP 在获取Session时失败");
            e.printStackTrace();
        }
        session.setPassword(properties.getPassword());

        Properties config = new Properties();
        config.setProperty("StrictHostKeyChecking", "no");
        session.setConfig(config);
        try {
            session.connect();
        } catch (JSchException e) {
            if (session.isConnected()) {
                session.disconnect();
            }
            LOGGER.error("SFTP 在Session连接时失败");
            e.printStackTrace();
        }
        return session;
    }

    private ChannelSftp getChannel() {
        if (session == null) {
            session = getSession();
        }
        Channel sftp = null;
        try {
            sftp = session.openChannel("sftp");
        } catch (JSchException e) {
            LOGGER.error("SFTP 在开启Channel时失败");
            e.printStackTrace();
        }

        try {
            sftp.connect();
        } catch (JSchException e) {
            if (sftp.isConnected())
                sftp.disconnect();
            LOGGER.error("SFTP 在连接Channel时失败");
            e.printStackTrace();
        }
        if (sftp instanceof ChannelSftp) {
            ChannelSftp channelSftp = (ChannelSftp) sftp;
            try {
                Class<ChannelSftp> clazz = ChannelSftp.class;
                Field serverVersion = clazz.getDeclaredField("server_version");
                if (!serverVersion.isAccessible()) {
                    serverVersion.setAccessible(true);
                }
                serverVersion.set(channelSftp, 2);
                //channelSftp.setFilenameEncoding("UTF-8");  //如果上传后文件乱码，可修改编码
            } catch (Exception e) {
                e.printStackTrace();
            }
            return channelSftp;
        }
        return null;
    }

    /**
     * @param path     文件存放路径
     * @param ins      文件流
     * @param fileName 存放的文件名
     */
    public void upload(String path, InputStream ins, String fileName) {
        ChannelSftp channelSftp = sftpThreadLocal.get();
        if (channelSftp == null) {
            channelSftp = getChannel();
            try {
                if (null != channelSftp) {
                    sftpThreadLocal.set(channelSftp);

                    cd(channelSftp, path);
                    channelSftp.put(ins, fileName);
                }
            } catch (SftpException e) {
                LOGGER.error("SFTP 服务器不存在该路径，无法进入。");
                e.printStackTrace();
            }
        }
    }

    public InputStream download(String path, String fileName) {
        ChannelSftp channelSftp = sftpThreadLocal.get();
        if (channelSftp == null) {
            channelSftp = getChannel();
            if (null != channelSftp) {
                sftpThreadLocal.set(channelSftp);
                try {
                    channelSftp.cd(path);
                } catch (SftpException e) {
                    LOGGER.error("{}不存在或其不是文件夹", path);
                    e.printStackTrace();
                }
                try {
                    InputStream inputStream = channelSftp.get(fileName);
                    return inputStream;
                } catch (SftpException e) {
                    LOGGER.error("文件：{} 下载失败", fileName);
                    e.printStackTrace();
                }

            }

        }
        return null;
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
                }
                sftp.cd(str);
            } catch (Exception e) {
                sftp.mkdir(str);
                sftp.cd(str);
            }
        }
    }


}
