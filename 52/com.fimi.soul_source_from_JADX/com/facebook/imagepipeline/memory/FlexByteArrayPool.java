package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import java.util.Map;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class FlexByteArrayPool {
    @VisibleForTesting
    final SoftRefByteArrayPool mDelegatePool;
    private final ResourceReleaser<byte[]> mResourceReleaser;

    /* renamed from: com.facebook.imagepipeline.memory.FlexByteArrayPool.1 */
    class C10361 implements ResourceReleaser<byte[]> {
        C10361() {
        }

        public void release(byte[] bArr) {
            FlexByteArrayPool.this.release(bArr);
        }
    }

    @VisibleForTesting
    class SoftRefByteArrayPool extends GenericByteArrayPool {
        public SoftRefByteArrayPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
            super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        }

        Bucket<byte[]> newBucket(int i) {
            return new OOMSoftReferenceBucket(getSizeInBytes(i), this.mPoolParams.maxNumThreads, 0);
        }
    }

    public FlexByteArrayPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams) {
        Preconditions.checkArgument(poolParams.maxNumThreads > 0);
        this.mDelegatePool = new SoftRefByteArrayPool(memoryTrimmableRegistry, poolParams, NoOpPoolStatsTracker.getInstance());
        this.mResourceReleaser = new C10361();
    }

    public CloseableReference<byte[]> get(int i) {
        return CloseableReference.of(this.mDelegatePool.get(i), this.mResourceReleaser);
    }

    public int getMinBufferSize() {
        return this.mDelegatePool.getMinBufferSize();
    }

    public Map<String, Integer> getStats() {
        return this.mDelegatePool.getStats();
    }

    public void release(byte[] bArr) {
        this.mDelegatePool.release(bArr);
    }
}
