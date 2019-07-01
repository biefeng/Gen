package com.example.service1.asm.enums;

public enum Align {

    LEFT(0x00),
    CENTER(0x01),
    RIGHT(0x02);

    public final int code;

    Align(int code) {
        this.code = code;
    }

}
