package com.example.service1.asm.enums;

public enum Justification {

    LEFT(0),
    CENTER(1),
    RIGHT(2),
    LEFT_ALT(48),
    CENTER_ALT(49),
    RIGHT_ALT(50);

    public final int code;

    Justification(int code) {
        this.code = code;
    }
    
}
