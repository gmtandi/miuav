package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.image.CloseableImage;

public class BitmapCountingMemoryCacheFactory {

    /* renamed from: com.facebook.imagepipeline.cache.BitmapCountingMemoryCacheFactory.1 */
    final class C10041 implements ValueDescriptor<CloseableImage> {
        C10041() {
        }

        public int getSizeInBytes(CloseableImage closeableImage) {
            return closeableImage.getSizeInBytes();
        }
    }

    public static CountingMemoryCache<CacheKey, CloseableImage> get(Supplier<MemoryCacheParams> supplier, MemoryTrimmableRegistry memoryTrimmableRegistry) {
        Object countingMemoryCache = new CountingMemoryCache(new C10041(), new BitmapMemoryCacheTrimStrategy(), supplier);
        memoryTrimmableRegistry.registerMemoryTrimmable(countingMemoryCache);
        return countingMemoryCache;
    }
}
