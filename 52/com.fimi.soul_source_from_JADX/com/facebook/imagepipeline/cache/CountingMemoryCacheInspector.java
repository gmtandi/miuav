package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class CountingMemoryCacheInspector<K, V> {
    private final CountingMemoryCache<K, V> mCountingBitmapCache;

    public class DumpInfo<K, V> {
        public final List<DumpInfoEntry<K, V>> lruEntries;
        public final int lruSize;
        public final int maxEntriesCount;
        public final int maxEntrySize;
        public final int maxSize;
        public final List<DumpInfoEntry<K, V>> sharedEntries;
        public final int size;

        public DumpInfo(int i, int i2, MemoryCacheParams memoryCacheParams) {
            this.maxSize = memoryCacheParams.maxCacheSize;
            this.maxEntriesCount = memoryCacheParams.maxCacheEntries;
            this.maxEntrySize = memoryCacheParams.maxCacheEntrySize;
            this.size = i;
            this.lruSize = i2;
            this.lruEntries = new ArrayList();
            this.sharedEntries = new ArrayList();
        }

        public void release() {
            for (DumpInfoEntry release : this.lruEntries) {
                release.release();
            }
            for (DumpInfoEntry release2 : this.sharedEntries) {
                release2.release();
            }
        }
    }

    public class DumpInfoEntry<K, V> {
        public final K key;
        public final CloseableReference<V> value;

        public DumpInfoEntry(K k, CloseableReference<V> closeableReference) {
            this.key = Preconditions.checkNotNull(k);
            this.value = CloseableReference.cloneOrNull((CloseableReference) closeableReference);
        }

        public void release() {
            CloseableReference.closeSafely(this.value);
        }
    }

    public CountingMemoryCacheInspector(CountingMemoryCache<K, V> countingMemoryCache) {
        this.mCountingBitmapCache = countingMemoryCache;
    }

    public DumpInfo dumpCacheContent() {
        DumpInfo dumpInfo;
        synchronized (this.mCountingBitmapCache) {
            dumpInfo = new DumpInfo(this.mCountingBitmapCache.getSizeInBytes(), this.mCountingBitmapCache.getEvictionQueueSizeInBytes(), this.mCountingBitmapCache.mMemoryCacheParams);
            for (Entry value : this.mCountingBitmapCache.mCachedEntries.getMatchingEntries(null)) {
                Entry entry = (Entry) value.getValue();
                DumpInfoEntry dumpInfoEntry = new DumpInfoEntry(entry.key, entry.valueRef);
                if (entry.clientCount > 0) {
                    dumpInfo.sharedEntries.add(dumpInfoEntry);
                } else {
                    dumpInfo.lruEntries.add(dumpInfoEntry);
                }
            }
        }
        return dumpInfo;
    }
}
