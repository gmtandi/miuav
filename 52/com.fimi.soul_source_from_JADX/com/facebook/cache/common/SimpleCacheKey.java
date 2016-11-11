package com.facebook.cache.common;

import com.facebook.common.internal.Preconditions;

public class SimpleCacheKey implements CacheKey {
    final String mKey;

    public SimpleCacheKey(String str) {
        this.mKey = (String) Preconditions.checkNotNull(str);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SimpleCacheKey)) {
            return false;
        }
        return this.mKey.equals(((SimpleCacheKey) obj).mKey);
    }

    public int hashCode() {
        return this.mKey.hashCode();
    }

    public String toString() {
        return this.mKey;
    }
}
