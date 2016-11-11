package org.codehaus.jackson.map.util;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LRUMap<K, V> extends LinkedHashMap<K, V> {
    protected final int _maxEntries;

    public LRUMap(int i, int i2) {
        super(i, 0.8f, true);
        this._maxEntries = i2;
    }

    protected boolean removeEldestEntry(Entry<K, V> entry) {
        return size() > this._maxEntries;
    }
}
