package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.image.CloseableImage;

public class BitmapMemoryCacheFactory {

    /* renamed from: com.facebook.imagepipeline.cache.BitmapMemoryCacheFactory.1 */
    final class C10051 implements MemoryCacheTracker {
        final /* synthetic */ ImageCacheStatsTracker val$imageCacheStatsTracker;

        C10051(ImageCacheStatsTracker imageCacheStatsTracker) {
            this.val$imageCacheStatsTracker = imageCacheStatsTracker;
        }

        public void onCacheHit() {
            this.val$imageCacheStatsTracker.onBitmapCacheHit();
        }

        public void onCacheMiss() {
            this.val$imageCacheStatsTracker.onBitmapCacheMiss();
        }

        public void onCachePut() {
            this.val$imageCacheStatsTracker.onBitmapCachePut();
        }
    }

    public static MemoryCache<CacheKey, CloseableImage> get(CountingMemoryCache<CacheKey, CloseableImage> countingMemoryCache, ImageCacheStatsTracker imageCacheStatsTracker) {
        imageCacheStatsTracker.registerBitmapMemoryCache(countingMemoryCache);
        return new InstrumentedMemoryCache(countingMemoryCache, new C10051(imageCacheStatsTracker));
    }
}
