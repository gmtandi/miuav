package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

public class StagingArea {
    private static final Class<?> TAG;
    @GuardedBy("this")
    private Map<CacheKey, EncodedImage> mMap;

    static {
        TAG = StagingArea.class;
    }

    private StagingArea() {
        this.mMap = new HashMap();
    }

    public static StagingArea getInstance() {
        return new StagingArea();
    }

    private synchronized void logStats() {
        FLog.m7618v(TAG, "Count = %d", Integer.valueOf(this.mMap.size()));
    }

    public void clearAll() {
        synchronized (this) {
            List arrayList = new ArrayList(this.mMap.values());
            this.mMap.clear();
        }
        for (int i = 0; i < arrayList.size(); i++) {
            EncodedImage encodedImage = (EncodedImage) arrayList.get(i);
            if (encodedImage != null) {
                encodedImage.close();
            }
        }
    }

    public synchronized EncodedImage get(CacheKey cacheKey) {
        EncodedImage encodedImage;
        Preconditions.checkNotNull(cacheKey);
        encodedImage = (EncodedImage) this.mMap.get(cacheKey);
        if (encodedImage != null) {
            synchronized (encodedImage) {
                if (EncodedImage.isValid(encodedImage)) {
                    EncodedImage cloneOrNull = EncodedImage.cloneOrNull(encodedImage);
                    encodedImage = cloneOrNull;
                } else {
                    this.mMap.remove(cacheKey);
                    FLog.m7635w(TAG, "Found closed reference %d for key %s (%d)", Integer.valueOf(System.identityHashCode(encodedImage)), cacheKey.toString(), Integer.valueOf(System.identityHashCode(cacheKey)));
                    encodedImage = null;
                }
            }
        }
        return encodedImage;
    }

    public synchronized void put(CacheKey cacheKey, EncodedImage encodedImage) {
        Preconditions.checkNotNull(cacheKey);
        Preconditions.checkArgument(EncodedImage.isValid(encodedImage));
        EncodedImage.closeSafely((EncodedImage) this.mMap.put(cacheKey, EncodedImage.cloneOrNull(encodedImage)));
        logStats();
    }

    public boolean remove(CacheKey cacheKey) {
        Preconditions.checkNotNull(cacheKey);
        synchronized (this) {
            EncodedImage encodedImage = (EncodedImage) this.mMap.remove(cacheKey);
        }
        if (encodedImage == null) {
            return false;
        }
        try {
            boolean isValid = encodedImage.isValid();
            return isValid;
        } finally {
            encodedImage.close();
        }
    }

    public synchronized boolean remove(CacheKey cacheKey, EncodedImage encodedImage) {
        boolean z;
        Preconditions.checkNotNull(cacheKey);
        Preconditions.checkNotNull(encodedImage);
        Preconditions.checkArgument(EncodedImage.isValid(encodedImage));
        EncodedImage encodedImage2 = (EncodedImage) this.mMap.get(cacheKey);
        if (encodedImage2 == null) {
            z = false;
        } else {
            CloseableReference byteBufferRef = encodedImage2.getByteBufferRef();
            CloseableReference byteBufferRef2 = encodedImage.getByteBufferRef();
            if (!(byteBufferRef == null || byteBufferRef2 == null)) {
                try {
                    if (byteBufferRef.get() == byteBufferRef2.get()) {
                        this.mMap.remove(cacheKey);
                        CloseableReference.closeSafely(byteBufferRef2);
                        CloseableReference.closeSafely(byteBufferRef);
                        EncodedImage.closeSafely(encodedImage2);
                        logStats();
                        z = true;
                    }
                } catch (Throwable th) {
                    CloseableReference.closeSafely(byteBufferRef2);
                    CloseableReference.closeSafely(byteBufferRef);
                    EncodedImage.closeSafely(encodedImage2);
                }
            }
            CloseableReference.closeSafely(byteBufferRef2);
            CloseableReference.closeSafely(byteBufferRef);
            EncodedImage.closeSafely(encodedImage2);
            z = false;
        }
        return z;
    }
}
