package com.facebook.cache.disk;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.DiskStorage.DiskDumpInfo;
import com.facebook.common.disk.DiskTrimmable;

public interface FileCache extends DiskTrimmable {
    void clearAll();

    long clearOldEntries(long j);

    DiskDumpInfo getDumpInfo();

    BinaryResource getResource(CacheKey cacheKey);

    long getSize();

    boolean hasKey(CacheKey cacheKey);

    BinaryResource insert(CacheKey cacheKey, WriterCallback writerCallback);

    boolean isEnabled();

    boolean probe(CacheKey cacheKey);

    void remove(CacheKey cacheKey);
}
