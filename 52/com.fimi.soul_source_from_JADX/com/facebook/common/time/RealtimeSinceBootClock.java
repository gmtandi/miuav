package com.facebook.common.time;

import android.os.SystemClock;

public class RealtimeSinceBootClock implements MonotonicClock {
    private static final RealtimeSinceBootClock INSTANCE;

    static {
        INSTANCE = new RealtimeSinceBootClock();
    }

    private RealtimeSinceBootClock() {
    }

    public static RealtimeSinceBootClock get() {
        return INSTANCE;
    }

    public long now() {
        return SystemClock.elapsedRealtime();
    }
}
