package com.example.service1;

import com.jcraft.jsch.*;
import org.junit.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.objectweb.asm.Opcodes.*;

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
            sftp.put(fis, "total.txt");

            cd(sftp, "/usr/home/testCd/gen");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void name1() throws IOException {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_6, ACC_PUBLIC + ACC_INTERFACE, "com/demo/asm/DemoInterface", null, "java/lang/Object", null);
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "NAME", "I", null, new Integer(-1)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "AGE", "I", null, new Integer(0)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "ADDRESS", "I", null, new Integer(1)).visitEnd();
        cw.visitMethod(ACC_PUBLIC, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();
        cw.visitMethod(ACC_PUBLIC, "getAge", "(I)I", null, null).visitEnd();
        cw.visitEnd();
        // ----------------------------------------定义Bean类----------------------------------------------------------------
        cw.visit(V1_6, ACC_PUBLIC, "com/demo/asm/DemoBean", null, "java/lang/Object", null);
        cw.visitField(ACC_PUBLIC, "name", "Ljava/lang/String;", null, null).visitEnd();
        MethodVisitor m_getName = cw.visitMethod(ACC_PUBLIC, "getName", "()Ljava/lang/String;", null, null);
        m_getName.visitVarInsn(ALOAD, 0);
        m_getName.visitFieldInsn(GETFIELD, "com/demo/asm/DemoBean", "name", "Ljava/lang/String;");
        m_getName.visitInsn(ARETURN);
        m_getName.visitMaxs(2, 1);
        m_getName.visitEnd();
        MethodVisitor m_setName = cw.visitMethod(ACC_PUBLIC, "setName", "(Ljava/lang/String;)V", null, null);
        m_setName.visitVarInsn(ALOAD, 0);
        m_setName.visitVarInsn(ALOAD, 1);
        m_setName.visitFieldInsn(PUTFIELD, "com/demo/asm/DemoBean", "name", "Ljava/lang/String;");
        m_setName.visitInsn(RETURN);
        m_setName.visitMaxs(2, 2);
        m_setName.visitEnd();
        cw.visitEnd();
// --------------------------------------------------------------------------------------------------------------
        cw.visitField(ACC_PUBLIC, "age", "Ljava/util/Date;", null, null).visitEnd();
        MethodVisitor m_getDate = cw.visitMethod(ACC_PUBLIC, "getDate", "()Ljava/util/Date;", null, null);
        m_getDate.visitVarInsn(ALOAD, 0);
        m_getDate.visitFieldInsn(GETFIELD, "com/demo/asm/DemoBean", "age", "Ljava/util/Date;");
        m_getDate.visitInsn(ARETURN);
        m_getDate.visitMaxs(2, 1);
        m_getDate.visitEnd();
        MethodVisitor m_setDate = cw.visitMethod(ACC_PUBLIC, "setDate", "(Ljava/util/Date;)V", null, null);
        m_setDate.visitVarInsn(ALOAD, 0);
        m_setDate.visitVarInsn(ALOAD, 1);
        m_setDate.visitFieldInsn(PUTFIELD, "com/demo/asm/DemoBean", "age", "Ljava/util/Date;");
        m_setDate.visitInsn(RETURN);
        m_setDate.visitMaxs(2, 2);
        m_setDate.visitEnd();
        cw.visitEnd();
        byte[] code = cw.toByteArray();
// 将二进制流写到本地磁盘上
        FileOutputStream fos = new FileOutputStream(new File("d:/doc/DemoBean.class"));
        fos.write(code);
        fos.close();
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

    /**
     * 根据字符串长度限制做自动换行
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void splitStr() throws UnsupportedEncodingException {
        List<String> list = new ArrayList<>();
        String content = "你是魔鬼吗你是1234567890魔1234567890鬼吗，。，,.,.,.你是魔鬼吗你是魔鬼吗你是魔鬼吗你是魔鬼吗你是魔鬼吗你是魔鬼吗";
        byte[] bytes = content.getBytes("GBK");
        System.out.println("bytes长度：" + bytes.length);
        int count = 0;
        int limit = 12;
        int index = 0;
        int bukket = 0;
        for (int i = 0; i < bytes.length; i++) {
            bukket++;
            if (bytes[i] >= 0 && bytes[i] < 128) {
                count++;
            } else {
                count++;
                i++;
                bukket++;
            }
            if (bukket >= limit || i == bytes.length - 1) {
                list.add(content.substring(index, index + count));
                index += count;
                count = 0;
                bukket = 0;
            }
        }
        System.out.println(list);
    }
}
