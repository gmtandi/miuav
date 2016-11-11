package com.facebook.cache.common;

import com.facebook.cache.common.CacheEventListener.EvictionReason;

public class NoOpCacheEventListener implements CacheEventListener {
    private static NoOpCacheEventListener sInstance;

    static {
        sInstance = null;
    }

    private NoOpCacheEventListener() {
    }

    public static synchronized NoOpCacheEventListener getInstance() {
        NoOpCacheEventListener noOpCacheEventListener;
        synchronized (NoOpCacheEventListener.class) {
            if (sInstance == null) {
                sInstance = new NoOpCacheEventListener();
            }
            noOpCacheEventListener = sInstance;
        }
        return noOpCacheEventListener;
    }

    public void onEviction(EvictionReason evictionReason, int i, long j) {
    }

    public void onHit() {
    }

    public void onMiss() {
    }

    public void onReadException() {
    }

    public void onWriteAttempt() {
    }

    public void onWriteException() {
    }
}
