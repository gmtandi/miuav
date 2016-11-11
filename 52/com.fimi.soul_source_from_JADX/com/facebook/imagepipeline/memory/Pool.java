package com.facebook.imagepipeline.memory;

import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.references.ResourceReleaser;

public interface Pool<V> extends MemoryTrimmable, ResourceReleaser<V> {
    V get(int i);

    void release(V v);
}
