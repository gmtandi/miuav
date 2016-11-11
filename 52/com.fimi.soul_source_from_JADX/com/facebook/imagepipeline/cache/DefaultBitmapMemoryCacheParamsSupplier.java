package com.facebook.imagepipeline.cache;

import android.app.ActivityManager;
import android.os.Build.VERSION;
import android.support.v4.view.GravityCompat;
import com.facebook.common.internal.Supplier;
import com.facebook.imagepipeline.memory.DefaultFlexByteArrayPoolParams;
import com.tencent.mm.sdk.platformtools.Util;

public class DefaultBitmapMemoryCacheParamsSupplier implements Supplier<MemoryCacheParams> {
    private static final int MAX_CACHE_ENTRIES = 256;
    private static final int MAX_CACHE_ENTRY_SIZE = Integer.MAX_VALUE;
    private static final int MAX_EVICTION_QUEUE_ENTRIES = Integer.MAX_VALUE;
    private static final int MAX_EVICTION_QUEUE_SIZE = Integer.MAX_VALUE;
    private final ActivityManager mActivityManager;

    public DefaultBitmapMemoryCacheParamsSupplier(ActivityManager activityManager) {
        this.mActivityManager = activityManager;
    }

    private int getMaxCacheSize() {
        int min = Math.min(this.mActivityManager.getMemoryClass() * Util.BYTE_OF_MB, MAX_EVICTION_QUEUE_SIZE);
        return min < 33554432 ? DefaultFlexByteArrayPoolParams.DEFAULT_MAX_BYTE_ARRAY_SIZE : min < 67108864 ? 6291456 : VERSION.SDK_INT < 11 ? GravityCompat.RELATIVE_LAYOUT_DIRECTION : min / 4;
    }

    public MemoryCacheParams get() {
        return new MemoryCacheParams(getMaxCacheSize(), MAX_CACHE_ENTRIES, MAX_EVICTION_QUEUE_SIZE, MAX_EVICTION_QUEUE_SIZE, MAX_EVICTION_QUEUE_SIZE);
    }
}
