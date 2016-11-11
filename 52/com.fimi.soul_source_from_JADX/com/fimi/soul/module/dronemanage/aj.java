package com.fimi.soul.module.dronemanage;

import java.util.concurrent.atomic.AtomicInteger;

public class aj {
    public static final int f8409a = 0;
    public static final int f8410b = 1;
    public static final int f8411c = 2;
    public static final int f8412d = 3;
    public static final int f8413e = 4;
    public static final int f8414f = 5;
    public static final int f8415g = 6;
    public static final int f8416h = 7;
    private static AtomicInteger f8417i;

    static {
        f8417i = new AtomicInteger(f8409a);
    }

    public static final int m11198a() {
        return f8417i.get();
    }

    public static final void m11199a(Integer num) {
        f8417i.set(num.intValue());
    }
}
