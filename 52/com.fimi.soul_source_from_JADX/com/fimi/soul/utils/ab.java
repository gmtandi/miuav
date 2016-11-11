package com.fimi.soul.utils;

import com.facebook.common.internal.Supplier;
import com.facebook.imagepipeline.cache.MemoryCacheParams;

final class ab implements Supplier<MemoryCacheParams> {
    final /* synthetic */ MemoryCacheParams f10011a;

    ab(MemoryCacheParams memoryCacheParams) {
        this.f10011a = memoryCacheParams;
    }

    public MemoryCacheParams m12216a() {
        return this.f10011a;
    }

    public /* synthetic */ Object get() {
        return m12216a();
    }
}
