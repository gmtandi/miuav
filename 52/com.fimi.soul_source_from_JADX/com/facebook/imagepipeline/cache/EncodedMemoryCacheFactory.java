package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.memory.PooledByteBuffer;

public class EncodedMemoryCacheFactory {

    /* renamed from: com.facebook.imagepipeline.cache.EncodedMemoryCacheFactory.1 */
    final class C10161 implements MemoryCacheTracker {
        final /* synthetic */ ImageCacheStatsTracker val$imageCacheStatsTracker;

        C10161(ImageCacheStatsTracker imageCacheStatsTracker) {
            this.val$imageCacheStatsTracker = imageCacheStatsTracker;
        }

        public void onCacheHit() {
            this.val$imageCacheStatsTracker.onMemoryCacheHit();
        }

        public void onCacheMiss() {
            this.val$imageCacheStatsTracker.onMemoryCacheMiss();
        }

        public void onCachePut() {
            this.val$imageCacheStatsTracker.onMemoryCachePut();
        }
    }

    public static MemoryCache<CacheKey, PooledByteBuffer> get(CountingMemoryCache<CacheKey, PooledByteBuffer> countingMemoryCache, ImageCacheStatsTracker imageCacheStatsTracker) {
        imageCacheStatsTracker.registerEncodedMemoryCache(countingMemoryCache);
        return new InstrumentedMemoryCache(countingMemoryCache, new C10161(imageCacheStatsTracker));
    }
}
