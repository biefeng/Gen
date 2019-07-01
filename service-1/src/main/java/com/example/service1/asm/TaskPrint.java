package com.example.service1.asm;

import com.example.service1.asm.enums.CutA;
import com.example.service1.asm.enums.Font;
import com.example.service1.asm.enums.Height;
import com.example.service1.asm.enums.Width;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/*
 *@Author BieFeNg
 *@Date 2019/7/1 21:32
 *@DESC
 */
public class TaskPrint {
    private EscPosWriter escPosWriter;

    public TaskPrint(String ip, int port) throws IOException {
        Socket socket = new Socket(ip, port);
        OutputStream os = new ByteArrayOutputStream();
        escPosWriter = new EscPosWriter("10.128.38.168");
    }

    public void printTask() {
        escPosWriter
                .initialize()
                .setPageMode()
                .printArea("", "", "", "")
                .setFont(Font.A_SPECIAL)
                .setCharacterSize(Width.X3, Height.X5)
                .text("Hello T11 Foodmarket")
                .printInPageMode()
                .cut(CutA.PARTIAL)
                .doPrint();
    }
}
