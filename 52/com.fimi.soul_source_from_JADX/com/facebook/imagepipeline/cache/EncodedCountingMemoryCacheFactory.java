package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.memory.PooledByteBuffer;

public class EncodedCountingMemoryCacheFactory {

    /* renamed from: com.facebook.imagepipeline.cache.EncodedCountingMemoryCacheFactory.1 */
    final class C10151 implements ValueDescriptor<PooledByteBuffer> {
        C10151() {
        }

        public int getSizeInBytes(PooledByteBuffer pooledByteBuffer) {
            return pooledByteBuffer.size();
        }
    }

    public static CountingMemoryCache<CacheKey, PooledByteBuffer> get(Supplier<MemoryCacheParams> supplier, MemoryTrimmableRegistry memoryTrimmableRegistry) {
        Object countingMemoryCache = new CountingMemoryCache(new C10151(), new NativeMemoryCacheTrimStrategy(), supplier);
        memoryTrimmableRegistry.registerMemoryTrimmable(countingMemoryCache);
        return countingMemoryCache;
    }
}
