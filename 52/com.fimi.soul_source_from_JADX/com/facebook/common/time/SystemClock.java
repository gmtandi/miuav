package com.facebook.common.time;

public class SystemClock implements Clock {
    private static final SystemClock INSTANCE;

    static {
        INSTANCE = new SystemClock();
    }

    private SystemClock() {
    }

    public static SystemClock get() {
        return INSTANCE;
    }

    public long now() {
        return System.currentTimeMillis();
    }
}
