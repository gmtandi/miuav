package com.facebook.imagepipeline.cache;

import com.android.internal.util.Predicate;
import com.facebook.common.internal.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class CountingLruMap<K, V> {
    @GuardedBy("this")
    private final LinkedHashMap<K, V> mMap;
    @GuardedBy("this")
    private int mSizeInBytes;
    private final ValueDescriptor<V> mValueDescriptor;

    public CountingLruMap(ValueDescriptor<V> valueDescriptor) {
        this.mMap = new LinkedHashMap();
        this.mSizeInBytes = 0;
        this.mValueDescriptor = valueDescriptor;
    }

    private int getValueSizeInBytes(V v) {
        return v == null ? 0 : this.mValueDescriptor.getSizeInBytes(v);
    }

    public synchronized ArrayList<V> clear() {
        ArrayList<V> arrayList;
        arrayList = new ArrayList(this.mMap.values());
        this.mMap.clear();
        this.mSizeInBytes = 0;
        return arrayList;
    }

    public synchronized boolean contains(K k) {
        return this.mMap.containsKey(k);
    }

    @Nullable
    public synchronized V get(K k) {
        return this.mMap.get(k);
    }

    public synchronized int getCount() {
        return this.mMap.size();
    }

    @Nullable
    public synchronized K getFirstKey() {
        return this.mMap.isEmpty() ? null : this.mMap.keySet().iterator().next();
    }

    @VisibleForTesting
    synchronized ArrayList<K> getKeys() {
        return new ArrayList(this.mMap.keySet());
    }

    public synchronized ArrayList<Entry<K, V>> getMatchingEntries(@Nullable Predicate<K> predicate) {
        ArrayList<Entry<K, V>> arrayList;
        arrayList = new ArrayList();
        for (Entry entry : this.mMap.entrySet()) {
            if (predicate == null || predicate.apply(entry.getKey())) {
                arrayList.add(entry);
            }
        }
        return arrayList;
    }

    public synchronized int getSizeInBytes() {
        return this.mSizeInBytes;
    }

    @VisibleForTesting
    synchronized ArrayList<V> getValues() {
        return new ArrayList(this.mMap.values());
    }

    @Nullable
    public synchronized V put(K k, V v) {
        V remove;
        remove = this.mMap.remove(k);
        this.mSizeInBytes -= getValueSizeInBytes(remove);
        this.mMap.put(k, v);
        this.mSizeInBytes += getValueSizeInBytes(v);
        return remove;
    }

    @Nullable
    public synchronized V remove(K k) {
        V remove;
        remove = this.mMap.remove(k);
        this.mSizeInBytes -= getValueSizeInBytes(remove);
        return remove;
    }

    public synchronized ArrayList<V> removeAll(@Nullable Predicate<K> predicate) {
        ArrayList<V> arrayList;
        arrayList = new ArrayList();
        Iterator it = this.mMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (predicate == null || predicate.apply(entry.getKey())) {
                arrayList.add(entry.getValue());
                this.mSizeInBytes -= getValueSizeInBytes(entry.getValue());
                it.remove();
            }
        }
        return arrayList;
    }
}
