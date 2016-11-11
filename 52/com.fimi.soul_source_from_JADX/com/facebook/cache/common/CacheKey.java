package com.facebook.cache.common;

public interface CacheKey {
    boolean equals(Object obj);

    int hashCode();

    String toString();
}
