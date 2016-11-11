package com.facebook.common.references;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

@VisibleForTesting
public class SharedReference<T> {
    @GuardedBy("itself")
    private static final Map<Object, Integer> sLiveObjects;
    @GuardedBy("this")
    private int mRefCount;
    private final ResourceReleaser<T> mResourceReleaser;
    @GuardedBy("this")
    private T mValue;

    public class NullReferenceException extends RuntimeException {
        public NullReferenceException() {
            super("Null shared reference");
        }
    }

    static {
        sLiveObjects = new IdentityHashMap();
    }

    public SharedReference(T t, ResourceReleaser<T> resourceReleaser) {
        this.mValue = Preconditions.checkNotNull(t);
        this.mResourceReleaser = (ResourceReleaser) Preconditions.checkNotNull(resourceReleaser);
        this.mRefCount = 1;
        addLiveReference(t);
    }

    private static void addLiveReference(Object obj) {
        synchronized (sLiveObjects) {
            Integer num = (Integer) sLiveObjects.get(obj);
            if (num == null) {
                sLiveObjects.put(obj, Integer.valueOf(1));
            } else {
                sLiveObjects.put(obj, Integer.valueOf(num.intValue() + 1));
            }
        }
    }

    private synchronized int decreaseRefCount() {
        ensureValid();
        Preconditions.checkArgument(this.mRefCount > 0);
        this.mRefCount--;
        return this.mRefCount;
    }

    private void ensureValid() {
        if (!isValid(this)) {
            throw new NullReferenceException();
        }
    }

    public static boolean isValid(SharedReference<?> sharedReference) {
        return sharedReference != null && sharedReference.isValid();
    }

    private static void removeLiveReference(Object obj) {
        synchronized (sLiveObjects) {
            Integer num = (Integer) sLiveObjects.get(obj);
            if (num == null) {
                FLog.wtf("SharedReference", "No entry in sLiveObjects for value of type %s", obj.getClass());
            } else if (num.intValue() == 1) {
                sLiveObjects.remove(obj);
            } else {
                sLiveObjects.put(obj, Integer.valueOf(num.intValue() - 1));
            }
        }
    }

    public synchronized void addReference() {
        ensureValid();
        this.mRefCount++;
    }

    public void deleteReference() {
        if (decreaseRefCount() == 0) {
            Object obj;
            synchronized (this) {
                obj = this.mValue;
                this.mValue = null;
            }
            this.mResourceReleaser.release(obj);
            removeLiveReference(obj);
        }
    }

    public synchronized T get() {
        return this.mValue;
    }

    public synchronized int getRefCountTestOnly() {
        return this.mRefCount;
    }

    public synchronized boolean isValid() {
        return this.mRefCount > 0;
    }
}
