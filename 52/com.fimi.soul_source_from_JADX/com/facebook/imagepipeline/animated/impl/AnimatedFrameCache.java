package com.facebook.imagepipeline.animated.impl;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.CountingMemoryCache.EntryStateObserver;
import com.facebook.imagepipeline.image.CloseableImage;
import java.util.Iterator;
import java.util.LinkedHashSet;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class AnimatedFrameCache {
    private final CountingMemoryCache<CacheKey, CloseableImage> mBackingCache;
    private final EntryStateObserver<CacheKey> mEntryStateObserver;
    @GuardedBy("this")
    private final LinkedHashSet<CacheKey> mFreeItemsPool;
    private final CacheKey mImageCacheKey;

    /* renamed from: com.facebook.imagepipeline.animated.impl.AnimatedFrameCache.1 */
    class C10021 implements EntryStateObserver<CacheKey> {
        C10021() {
        }

        public void onExclusivityChanged(CacheKey cacheKey, boolean z) {
            AnimatedFrameCache.this.onReusabilityChange(cacheKey, z);
        }
    }

    @VisibleForTesting
    class FrameKey implements CacheKey {
        private final int mFrameIndex;
        private final CacheKey mImageCacheKey;

        public FrameKey(CacheKey cacheKey, int i) {
            this.mImageCacheKey = cacheKey;
            this.mFrameIndex = i;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FrameKey)) {
                return false;
            }
            FrameKey frameKey = (FrameKey) obj;
            return this.mImageCacheKey == frameKey.mImageCacheKey && this.mFrameIndex == frameKey.mFrameIndex;
        }

        public int hashCode() {
            return (this.mImageCacheKey.hashCode() * 1013) + this.mFrameIndex;
        }

        public String toString() {
            return Objects.toStringHelper((Object) this).add("imageCacheKey", this.mImageCacheKey).add("frameIndex", this.mFrameIndex).toString();
        }
    }

    public AnimatedFrameCache(CacheKey cacheKey, CountingMemoryCache<CacheKey, CloseableImage> countingMemoryCache) {
        this.mImageCacheKey = cacheKey;
        this.mBackingCache = countingMemoryCache;
        this.mFreeItemsPool = new LinkedHashSet();
        this.mEntryStateObserver = new C10021();
    }

    private FrameKey keyFor(int i) {
        return new FrameKey(this.mImageCacheKey, i);
    }

    @Nullable
    private synchronized CacheKey popFirstFreeItemKey() {
        CacheKey cacheKey;
        cacheKey = null;
        Iterator it = this.mFreeItemsPool.iterator();
        if (it.hasNext()) {
            cacheKey = (CacheKey) it.next();
            it.remove();
        }
        return cacheKey;
    }

    @Nullable
    public CloseableReference<CloseableImage> cache(int i, CloseableReference<CloseableImage> closeableReference) {
        return this.mBackingCache.cache(keyFor(i), closeableReference, this.mEntryStateObserver);
    }

    @Nullable
    public CloseableReference<CloseableImage> get(int i) {
        return this.mBackingCache.get(keyFor(i));
    }

    @Nullable
    public CloseableReference<CloseableImage> getForReuse() {
        CloseableReference<CloseableImage> reuse;
        do {
            CacheKey popFirstFreeItemKey = popFirstFreeItemKey();
            if (popFirstFreeItemKey == null) {
                return null;
            }
            reuse = this.mBackingCache.reuse(popFirstFreeItemKey);
        } while (reuse == null);
        return reuse;
    }

    public synchronized void onReusabilityChange(CacheKey cacheKey, boolean z) {
        if (z) {
            this.mFreeItemsPool.add(cacheKey);
        } else {
            this.mFreeItemsPool.remove(cacheKey);
        }
    }
}
