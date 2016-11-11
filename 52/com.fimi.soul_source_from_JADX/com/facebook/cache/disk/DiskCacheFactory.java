package com.facebook.cache.disk;

import com.facebook.cache.disk.DiskStorageCache.Params;

public class DiskCacheFactory {
    public static DiskStorageCache newDiskStorageCache(DiskCacheConfig diskCacheConfig) {
        DiskStorageSupplier newDiskStorageSupplier = newDiskStorageSupplier(diskCacheConfig);
        return new DiskStorageCache(newDiskStorageSupplier, new Params(diskCacheConfig.getMinimumSizeLimit(), diskCacheConfig.getLowDiskSpaceSizeLimit(), diskCacheConfig.getDefaultSizeLimit()), diskCacheConfig.getCacheEventListener(), diskCacheConfig.getCacheErrorLogger(), diskCacheConfig.getDiskTrimmableRegistry());
    }

    private static DiskStorageSupplier newDiskStorageSupplier(DiskCacheConfig diskCacheConfig) {
        return new DefaultDiskStorageSupplier(diskCacheConfig.getVersion(), diskCacheConfig.getBaseDirectoryPathSupplier(), diskCacheConfig.getBaseDirectoryName(), diskCacheConfig.getCacheErrorLogger());
    }
}
