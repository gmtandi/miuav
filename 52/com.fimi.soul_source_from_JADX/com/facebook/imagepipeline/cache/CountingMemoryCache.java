package com.facebook.imagepipeline.cache;

import android.os.SystemClock;
import com.amap.api.maps.model.WeightedLatLng;
import com.android.internal.util.Predicate;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class CountingMemoryCache<K, V> implements MemoryTrimmable, MemoryCache<K, V> {
    @VisibleForTesting
    static final long PARAMS_INTERCHECK_INTERVAL_MS;
    private final CacheTrimStrategy mCacheTrimStrategy;
    @GuardedBy("this")
    @VisibleForTesting
    final CountingLruMap<K, Entry<K, V>> mCachedEntries;
    @GuardedBy("this")
    @VisibleForTesting
    final CountingLruMap<K, Entry<K, V>> mExclusiveEntries;
    @GuardedBy("this")
    private long mLastCacheParamsCheck;
    @GuardedBy("this")
    protected MemoryCacheParams mMemoryCacheParams;
    private final Supplier<MemoryCacheParams> mMemoryCacheParamsSupplier;
    private final ValueDescriptor<V> mValueDescriptor;

    public interface EntryStateObserver<K> {
        void onExclusivityChanged(K k, boolean z);
    }

    public interface CacheTrimStrategy {
        double getTrimRatio(MemoryTrimType memoryTrimType);
    }

    /* renamed from: com.facebook.imagepipeline.cache.CountingMemoryCache.1 */
    class C10131 implements ValueDescriptor<Entry<K, V>> {
        final /* synthetic */ ValueDescriptor val$evictableValueDescriptor;

        C10131(ValueDescriptor valueDescriptor) {
            this.val$evictableValueDescriptor = valueDescriptor;
        }

        public int getSizeInBytes(Entry<K, V> entry) {
            return this.val$evictableValueDescriptor.getSizeInBytes(entry.valueRef.get());
        }
    }

    /* renamed from: com.facebook.imagepipeline.cache.CountingMemoryCache.2 */
    class C10142 implements ResourceReleaser<V> {
        final /* synthetic */ Entry val$entry;

        C10142(Entry entry) {
            this.val$entry = entry;
        }

        public void release(V v) {
            CountingMemoryCache.this.releaseClientReference(this.val$entry);
        }
    }

    @VisibleForTesting
    class Entry<K, V> {
        public int clientCount;
        public boolean isOrphan;
        public final K key;
        @Nullable
        public final EntryStateObserver<K> observer;
        public final CloseableReference<V> valueRef;

        private Entry(K k, CloseableReference<V> closeableReference, @Nullable EntryStateObserver<K> entryStateObserver) {
            this.key = Preconditions.checkNotNull(k);
            this.valueRef = (CloseableReference) Preconditions.checkNotNull(CloseableReference.cloneOrNull((CloseableReference) closeableReference));
            this.clientCount = 0;
            this.isOrphan = false;
            this.observer = entryStateObserver;
        }

        @VisibleForTesting
        static <K, V> Entry<K, V> of(K k, CloseableReference<V> closeableReference, @Nullable EntryStateObserver<K> entryStateObserver) {
            return new Entry(k, closeableReference, entryStateObserver);
        }
    }

    static {
        PARAMS_INTERCHECK_INTERVAL_MS = TimeUnit.MINUTES.toMillis(5);
    }

    public CountingMemoryCache(ValueDescriptor<V> valueDescriptor, CacheTrimStrategy cacheTrimStrategy, Supplier<MemoryCacheParams> supplier) {
        this.mValueDescriptor = valueDescriptor;
        this.mExclusiveEntries = new CountingLruMap(wrapValueDescriptor(valueDescriptor));
        this.mCachedEntries = new CountingLruMap(wrapValueDescriptor(valueDescriptor));
        this.mCacheTrimStrategy = cacheTrimStrategy;
        this.mMemoryCacheParamsSupplier = supplier;
        this.mMemoryCacheParams = (MemoryCacheParams) this.mMemoryCacheParamsSupplier.get();
        this.mLastCacheParamsCheck = SystemClock.elapsedRealtime();
    }

    private synchronized boolean canCacheNewValue(V v) {
        boolean z;
        int sizeInBytes = this.mValueDescriptor.getSizeInBytes(v);
        z = sizeInBytes <= this.mMemoryCacheParams.maxCacheEntrySize && getInUseCount() <= this.mMemoryCacheParams.maxCacheEntries - 1 && getInUseSizeInBytes() <= this.mMemoryCacheParams.maxCacheSize - sizeInBytes;
        return z;
    }

    private synchronized void decreaseClientCount(Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(entry.clientCount > 0);
        entry.clientCount--;
    }

    private synchronized void increaseClientCount(Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(!entry.isOrphan);
        entry.clientCount++;
    }

    private synchronized void makeOrphan(Entry<K, V> entry) {
        boolean z = true;
        synchronized (this) {
            Preconditions.checkNotNull(entry);
            if (entry.isOrphan) {
                z = false;
            }
            Preconditions.checkState(z);
            entry.isOrphan = true;
        }
    }

    private synchronized void makeOrphans(@Nullable ArrayList<Entry<K, V>> arrayList) {
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                makeOrphan((Entry) it.next());
            }
        }
    }

    private synchronized boolean maybeAddToExclusives(Entry<K, V> entry) {
        boolean z;
        if (entry.isOrphan || entry.clientCount != 0) {
            z = false;
        } else {
            this.mExclusiveEntries.put(entry.key, entry);
            z = true;
        }
        return z;
    }

    private void maybeClose(@Nullable ArrayList<Entry<K, V>> arrayList) {
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                CloseableReference.closeSafely(referenceToClose((Entry) it.next()));
            }
        }
    }

    private void maybeEvictEntries() {
        ArrayList trimExclusivelyOwnedEntries;
        synchronized (this) {
            trimExclusivelyOwnedEntries = trimExclusivelyOwnedEntries(Math.min(this.mMemoryCacheParams.maxEvictionQueueEntries, this.mMemoryCacheParams.maxCacheEntries - getInUseCount()), Math.min(this.mMemoryCacheParams.maxEvictionQueueSize, this.mMemoryCacheParams.maxCacheSize - getInUseSizeInBytes()));
            makeOrphans(trimExclusivelyOwnedEntries);
        }
        maybeClose(trimExclusivelyOwnedEntries);
        maybeNotifyExclusiveEntryRemoval(trimExclusivelyOwnedEntries);
    }

    private static <K, V> void maybeNotifyExclusiveEntryInsertion(@Nullable Entry<K, V> entry) {
        if (entry != null && entry.observer != null) {
            entry.observer.onExclusivityChanged(entry.key, true);
        }
    }

    private static <K, V> void maybeNotifyExclusiveEntryRemoval(@Nullable Entry<K, V> entry) {
        if (entry != null && entry.observer != null) {
            entry.observer.onExclusivityChanged(entry.key, false);
        }
    }

    private void maybeNotifyExclusiveEntryRemoval(@Nullable ArrayList<Entry<K, V>> arrayList) {
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                maybeNotifyExclusiveEntryRemoval((Entry) it.next());
            }
        }
    }

    private synchronized void maybeUpdateCacheParams() {
        if (this.mLastCacheParamsCheck + PARAMS_INTERCHECK_INTERVAL_MS <= SystemClock.elapsedRealtime()) {
            this.mLastCacheParamsCheck = SystemClock.elapsedRealtime();
            this.mMemoryCacheParams = (MemoryCacheParams) this.mMemoryCacheParamsSupplier.get();
        }
    }

    private synchronized CloseableReference<V> newClientReference(Entry<K, V> entry) {
        increaseClientCount(entry);
        return CloseableReference.of(entry.valueRef.get(), new C10142(entry));
    }

    @Nullable
    private synchronized CloseableReference<V> referenceToClose(Entry<K, V> entry) {
        CloseableReference<V> closeableReference;
        Preconditions.checkNotNull(entry);
        closeableReference = (entry.isOrphan && entry.clientCount == 0) ? entry.valueRef : null;
        return closeableReference;
    }

    private void releaseClientReference(Entry<K, V> entry) {
        CloseableReference referenceToClose;
        Entry entry2;
        Preconditions.checkNotNull(entry);
        synchronized (this) {
            decreaseClientCount(entry);
            boolean maybeAddToExclusives = maybeAddToExclusives(entry);
            referenceToClose = referenceToClose(entry);
        }
        CloseableReference.closeSafely(referenceToClose);
        if (!maybeAddToExclusives) {
            entry2 = null;
        }
        maybeNotifyExclusiveEntryInsertion(entry2);
        maybeUpdateCacheParams();
        maybeEvictEntries();
    }

    @Nullable
    private synchronized ArrayList<Entry<K, V>> trimExclusivelyOwnedEntries(int i, int i2) {
        ArrayList<Entry<K, V>> arrayList;
        int max = Math.max(i, 0);
        int max2 = Math.max(i2, 0);
        if (this.mExclusiveEntries.getCount() > max || this.mExclusiveEntries.getSizeInBytes() > max2) {
            arrayList = new ArrayList();
            while (true) {
                if (this.mExclusiveEntries.getCount() <= max && this.mExclusiveEntries.getSizeInBytes() <= max2) {
                    break;
                }
                Object firstKey = this.mExclusiveEntries.getFirstKey();
                this.mExclusiveEntries.remove(firstKey);
                arrayList.add(this.mCachedEntries.remove(firstKey));
            }
        } else {
            arrayList = null;
        }
        return arrayList;
    }

    private ValueDescriptor<Entry<K, V>> wrapValueDescriptor(ValueDescriptor<V> valueDescriptor) {
        return new C10131(valueDescriptor);
    }

    public CloseableReference<V> cache(K k, CloseableReference<V> closeableReference) {
        return cache(k, closeableReference, null);
    }

    public CloseableReference<V> cache(K k, CloseableReference<V> closeableReference, EntryStateObserver<K> entryStateObserver) {
        Entry entry;
        CloseableReference referenceToClose;
        CloseableReference<V> newClientReference;
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(closeableReference);
        maybeUpdateCacheParams();
        synchronized (this) {
            entry = (Entry) this.mExclusiveEntries.remove(k);
            Entry entry2 = (Entry) this.mCachedEntries.remove(k);
            if (entry2 != null) {
                makeOrphan(entry2);
                referenceToClose = referenceToClose(entry2);
            } else {
                referenceToClose = null;
            }
            if (canCacheNewValue(closeableReference.get())) {
                entry2 = Entry.of(k, closeableReference, entryStateObserver);
                this.mCachedEntries.put(k, entry2);
                newClientReference = newClientReference(entry2);
            } else {
                newClientReference = null;
            }
        }
        CloseableReference.closeSafely(referenceToClose);
        maybeNotifyExclusiveEntryRemoval(entry);
        maybeEvictEntries();
        return newClientReference;
    }

    public void clear() {
        ArrayList clear;
        ArrayList clear2;
        synchronized (this) {
            clear = this.mExclusiveEntries.clear();
            clear2 = this.mCachedEntries.clear();
            makeOrphans(clear2);
        }
        maybeClose(clear2);
        maybeNotifyExclusiveEntryRemoval(clear);
        maybeUpdateCacheParams();
    }

    public synchronized boolean contains(Predicate<K> predicate) {
        return !this.mCachedEntries.getMatchingEntries(predicate).isEmpty();
    }

    @Nullable
    public CloseableReference<V> get(K k) {
        Entry entry;
        CloseableReference<V> newClientReference;
        Preconditions.checkNotNull(k);
        synchronized (this) {
            entry = (Entry) this.mExclusiveEntries.remove(k);
            Entry entry2 = (Entry) this.mCachedEntries.get(k);
            newClientReference = entry2 != null ? newClientReference(entry2) : null;
        }
        maybeNotifyExclusiveEntryRemoval(entry);
        maybeUpdateCacheParams();
        maybeEvictEntries();
        return newClientReference;
    }

    public synchronized int getCount() {
        return this.mCachedEntries.getCount();
    }

    public synchronized int getEvictionQueueCount() {
        return this.mExclusiveEntries.getCount();
    }

    public synchronized int getEvictionQueueSizeInBytes() {
        return this.mExclusiveEntries.getSizeInBytes();
    }

    public synchronized int getInUseCount() {
        return this.mCachedEntries.getCount() - this.mExclusiveEntries.getCount();
    }

    public synchronized int getInUseSizeInBytes() {
        return this.mCachedEntries.getSizeInBytes() - this.mExclusiveEntries.getSizeInBytes();
    }

    public synchronized int getSizeInBytes() {
        return this.mCachedEntries.getSizeInBytes();
    }

    public int removeAll(Predicate<K> predicate) {
        ArrayList removeAll;
        ArrayList removeAll2;
        synchronized (this) {
            removeAll = this.mExclusiveEntries.removeAll(predicate);
            removeAll2 = this.mCachedEntries.removeAll(predicate);
            makeOrphans(removeAll2);
        }
        maybeClose(removeAll2);
        maybeNotifyExclusiveEntryRemoval(removeAll);
        maybeUpdateCacheParams();
        maybeEvictEntries();
        return removeAll2.size();
    }

    @Nullable
    public CloseableReference<V> reuse(K k) {
        CloseableReference<V> closeableReference;
        boolean z;
        boolean z2 = false;
        Preconditions.checkNotNull(k);
        synchronized (this) {
            Entry entry = (Entry) this.mExclusiveEntries.remove(k);
            if (entry != null) {
                Entry entry2 = (Entry) this.mCachedEntries.remove(k);
                Preconditions.checkNotNull(entry2);
                if (entry2.clientCount == 0) {
                    z2 = true;
                }
                Preconditions.checkState(z2);
                closeableReference = entry2.valueRef;
                z = true;
            } else {
                closeableReference = null;
                z = false;
            }
        }
        if (z) {
            maybeNotifyExclusiveEntryRemoval(entry);
        }
        return closeableReference;
    }

    public void trim(MemoryTrimType memoryTrimType) {
        ArrayList trimExclusivelyOwnedEntries;
        double trimRatio = this.mCacheTrimStrategy.getTrimRatio(memoryTrimType);
        synchronized (this) {
            trimExclusivelyOwnedEntries = trimExclusivelyOwnedEntries(Integer.MAX_VALUE, Math.max(0, ((int) ((WeightedLatLng.DEFAULT_INTENSITY - trimRatio) * ((double) this.mCachedEntries.getSizeInBytes()))) - getInUseSizeInBytes()));
            makeOrphans(trimExclusivelyOwnedEntries);
        }
        maybeClose(trimExclusivelyOwnedEntries);
        maybeNotifyExclusiveEntryRemoval(trimExclusivelyOwnedEntries);
        maybeUpdateCacheParams();
        maybeEvictEntries();
    }
}
