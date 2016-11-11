package com.facebook.cache.common;

public interface CacheEventListener {

    public enum EvictionReason {
        CACHE_FULL,
        CONTENT_STALE,
        USER_FORCED,
        CACHE_MANAGER_TRIMMED
    }

    void onEviction(EvictionReason evictionReason, int i, long j);

    void onHit();

    void onMiss();

    void onReadException();

    void onWriteAttempt();

    void onWriteException();
}
