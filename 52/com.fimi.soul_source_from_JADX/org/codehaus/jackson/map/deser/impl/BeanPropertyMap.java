package org.codehaus.jackson.map.deser.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.codehaus.jackson.map.deser.SettableBeanProperty;

public final class BeanPropertyMap {
    private final Bucket[] _buckets;
    private final int _hashMask;
    private final int _size;

    final class Bucket {
        public final String key;
        public final Bucket next;
        public final SettableBeanProperty value;

        public Bucket(Bucket bucket, String str, SettableBeanProperty settableBeanProperty) {
            this.next = bucket;
            this.key = str;
            this.value = settableBeanProperty;
        }
    }

    final class IteratorImpl implements Iterator<SettableBeanProperty> {
        private final Bucket[] _buckets;
        private Bucket _currentBucket;
        private int _nextBucketIndex;

        public IteratorImpl(Bucket[] bucketArr) {
            int i;
            this._buckets = bucketArr;
            int i2 = 0;
            int length = this._buckets.length;
            while (i2 < length) {
                i = i2 + 1;
                Bucket bucket = this._buckets[i2];
                if (bucket != null) {
                    this._currentBucket = bucket;
                    break;
                }
                i2 = i;
            }
            i = i2;
            this._nextBucketIndex = i;
        }

        public boolean hasNext() {
            return this._currentBucket != null;
        }

        public SettableBeanProperty next() {
            Bucket bucket = this._currentBucket;
            if (bucket == null) {
                throw new NoSuchElementException();
            }
            Bucket bucket2 = bucket.next;
            while (bucket2 == null && this._nextBucketIndex < this._buckets.length) {
                Bucket[] bucketArr = this._buckets;
                int i = this._nextBucketIndex;
                this._nextBucketIndex = i + 1;
                bucket2 = bucketArr[i];
            }
            this._currentBucket = bucket2;
            return bucket.value;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public BeanPropertyMap(Collection<SettableBeanProperty> collection) {
        this._size = collection.size();
        int findSize = findSize(this._size);
        this._hashMask = findSize - 1;
        Bucket[] bucketArr = new Bucket[findSize];
        for (SettableBeanProperty settableBeanProperty : collection) {
            String name = settableBeanProperty.getName();
            int hashCode = name.hashCode() & this._hashMask;
            bucketArr[hashCode] = new Bucket(bucketArr[hashCode], name, settableBeanProperty);
        }
        this._buckets = bucketArr;
    }

    private SettableBeanProperty _findWithEquals(String str, int i) {
        for (Bucket bucket = this._buckets[i]; bucket != null; bucket = bucket.next) {
            if (str.equals(bucket.key)) {
                return bucket.value;
            }
        }
        return null;
    }

    private static final int findSize(int i) {
        int i2 = 2;
        while (i2 < (i <= 32 ? i + i : (i >> 2) + i)) {
            i2 += i2;
        }
        return i2;
    }

    public Iterator<SettableBeanProperty> allProperties() {
        return new IteratorImpl(this._buckets);
    }

    public void assignIndexes() {
        int i = 0;
        for (Bucket bucket : this._buckets) {
            Bucket bucket2;
            while (bucket2 != null) {
                int i2 = i + 1;
                bucket2.value.assignIndex(i);
                bucket2 = bucket2.next;
                i = i2;
            }
        }
    }

    public SettableBeanProperty find(String str) {
        int hashCode = this._hashMask & str.hashCode();
        Bucket bucket = this._buckets[hashCode];
        if (bucket == null) {
            return null;
        }
        if (bucket.key == str) {
            return bucket.value;
        }
        do {
            bucket = bucket.next;
            if (bucket == null) {
                return _findWithEquals(str, hashCode);
            }
        } while (bucket.key != str);
        return bucket.value;
    }

    public void replace(SettableBeanProperty settableBeanProperty) {
        String name = settableBeanProperty.getName();
        int hashCode = name.hashCode() & (this._buckets.length - 1);
        Bucket bucket = this._buckets[hashCode];
        Object obj = null;
        Bucket bucket2 = null;
        while (bucket != null) {
            if (obj == null && bucket.key.equals(name)) {
                obj = 1;
            } else {
                bucket2 = new Bucket(bucket2, bucket.key, bucket.value);
            }
            bucket = bucket.next;
        }
        if (obj == null) {
            throw new NoSuchElementException("No entry '" + settableBeanProperty + "' found, can't replace");
        }
        this._buckets[hashCode] = new Bucket(bucket2, name, settableBeanProperty);
    }

    public int size() {
        return this._size;
    }
}
