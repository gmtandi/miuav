package com.google.zxing;

public final class EncodeHintType {
    public static final EncodeHintType CHARACTER_SET;
    public static final EncodeHintType ERROR_CORRECTION;

    static {
        ERROR_CORRECTION = new EncodeHintType();
        CHARACTER_SET = new EncodeHintType();
    }

    private EncodeHintType() {
    }
}
