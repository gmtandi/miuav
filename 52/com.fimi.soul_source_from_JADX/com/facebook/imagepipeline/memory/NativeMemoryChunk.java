package com.facebook.imagepipeline.memory;

import android.util.Log;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.soloader.SoLoaderShim;
import java.io.Closeable;

@DoNotStrip
public class NativeMemoryChunk implements Closeable {
    private static final String TAG = "NativeMemoryChunk";
    private boolean mClosed;
    private final long mNativePtr;
    private final int mSize;

    static {
        SoLoaderShim.loadLibrary("memchunk");
    }

    @VisibleForTesting
    public NativeMemoryChunk() {
        this.mSize = 0;
        this.mNativePtr = 0;
        this.mClosed = true;
    }

    public NativeMemoryChunk(int i) {
        Preconditions.checkArgument(i > 0);
        this.mSize = i;
        this.mNativePtr = nativeAllocate(this.mSize);
        this.mClosed = false;
    }

    private int adjustByteCount(int i, int i2) {
        return Math.min(Math.max(0, this.mSize - i), i2);
    }

    private void checkBounds(int i, int i2, int i3, int i4) {
        boolean z = true;
        Preconditions.checkArgument(i4 >= 0);
        Preconditions.checkArgument(i >= 0);
        Preconditions.checkArgument(i3 >= 0);
        Preconditions.checkArgument(i + i4 <= this.mSize);
        if (i3 + i4 > i2) {
            z = false;
        }
        Preconditions.checkArgument(z);
    }

    private void doCopy(int i, NativeMemoryChunk nativeMemoryChunk, int i2, int i3) {
        boolean z = true;
        Preconditions.checkState(!isClosed());
        if (nativeMemoryChunk.isClosed()) {
            z = false;
        }
        Preconditions.checkState(z);
        checkBounds(i, nativeMemoryChunk.mSize, i2, i3);
        nativeMemcpy(nativeMemoryChunk.mNativePtr + ((long) i2), this.mNativePtr + ((long) i), i3);
    }

    @DoNotStrip
    private static native long nativeAllocate(int i);

    @DoNotStrip
    private static native void nativeCopyFromByteArray(long j, byte[] bArr, int i, int i2);

    @DoNotStrip
    private static native void nativeCopyToByteArray(long j, byte[] bArr, int i, int i2);

    @DoNotStrip
    private static native void nativeFree(long j);

    @DoNotStrip
    private static native void nativeMemcpy(long j, long j2, int i);

    @DoNotStrip
    private static native byte nativeReadByte(long j);

    public synchronized void close() {
        if (!this.mClosed) {
            this.mClosed = true;
            nativeFree(this.mNativePtr);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void copy(int r5, com.facebook.imagepipeline.memory.NativeMemoryChunk r6, int r7, int r8) {
        /*
        r4 = this;
        com.facebook.common.internal.Preconditions.checkNotNull(r6);
        r0 = r6.mNativePtr;
        r2 = r4.mNativePtr;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 != 0) goto L_0x0051;
    L_0x000b:
        r0 = "NativeMemoryChunk";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Copying from NativeMemoryChunk ";
        r1 = r1.append(r2);
        r2 = java.lang.System.identityHashCode(r4);
        r2 = java.lang.Integer.toHexString(r2);
        r1 = r1.append(r2);
        r2 = " to NativeMemoryChunk ";
        r1 = r1.append(r2);
        r2 = java.lang.System.identityHashCode(r6);
        r2 = java.lang.Integer.toHexString(r2);
        r1 = r1.append(r2);
        r2 = " which share the same address ";
        r1 = r1.append(r2);
        r2 = r4.mNativePtr;
        r2 = java.lang.Long.toHexString(r2);
        r1 = r1.append(r2);
        r1 = r1.toString();
        android.util.Log.w(r0, r1);
        r0 = 0;
        com.facebook.common.internal.Preconditions.checkArgument(r0);
    L_0x0051:
        r0 = r6.mNativePtr;
        r2 = r4.mNativePtr;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 >= 0) goto L_0x0067;
    L_0x0059:
        monitor-enter(r6);
        monitor-enter(r4);	 Catch:{ all -> 0x0064 }
        r4.doCopy(r5, r6, r7, r8);	 Catch:{ all -> 0x0061 }
        monitor-exit(r4);	 Catch:{ all -> 0x0061 }
        monitor-exit(r6);	 Catch:{ all -> 0x0064 }
    L_0x0060:
        return;
    L_0x0061:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0061 }
        throw r0;	 Catch:{ all -> 0x0064 }
    L_0x0064:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0064 }
        throw r0;
    L_0x0067:
        monitor-enter(r4);
        monitor-enter(r6);	 Catch:{ all -> 0x006f }
        r4.doCopy(r5, r6, r7, r8);	 Catch:{ all -> 0x0072 }
        monitor-exit(r6);	 Catch:{ all -> 0x0072 }
        monitor-exit(r4);	 Catch:{ all -> 0x006f }
        goto L_0x0060;
    L_0x006f:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x006f }
        throw r0;
    L_0x0072:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0072 }
        throw r0;	 Catch:{ all -> 0x006f }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.NativeMemoryChunk.copy(int, com.facebook.imagepipeline.memory.NativeMemoryChunk, int, int):void");
    }

    protected void finalize() {
        if (!isClosed()) {
            Log.w(TAG, "finalize: Chunk " + Integer.toHexString(System.identityHashCode(this)) + " still active. Underlying address = " + Long.toHexString(this.mNativePtr));
            try {
                close();
            } finally {
                super.finalize();
            }
        }
    }

    public long getNativePtr() {
        return this.mNativePtr;
    }

    public int getSize() {
        return this.mSize;
    }

    public synchronized boolean isClosed() {
        return this.mClosed;
    }

    public synchronized byte read(int i) {
        byte nativeReadByte;
        boolean z = true;
        synchronized (this) {
            Preconditions.checkState(!isClosed());
            Preconditions.checkArgument(i >= 0);
            if (i >= this.mSize) {
                z = false;
            }
            Preconditions.checkArgument(z);
            nativeReadByte = nativeReadByte(this.mNativePtr + ((long) i));
        }
        return nativeReadByte;
    }

    public synchronized int read(int i, byte[] bArr, int i2, int i3) {
        int adjustByteCount;
        Preconditions.checkNotNull(bArr);
        Preconditions.checkState(!isClosed());
        adjustByteCount = adjustByteCount(i, i3);
        checkBounds(i, bArr.length, i2, adjustByteCount);
        nativeCopyToByteArray(this.mNativePtr + ((long) i), bArr, i2, adjustByteCount);
        return adjustByteCount;
    }

    public synchronized int write(int i, byte[] bArr, int i2, int i3) {
        int adjustByteCount;
        Preconditions.checkNotNull(bArr);
        Preconditions.checkState(!isClosed());
        adjustByteCount = adjustByteCount(i, i3);
        checkBounds(i, bArr.length, i2, adjustByteCount);
        nativeCopyFromByteArray(this.mNativePtr + ((long) i), bArr, i2, adjustByteCount);
        return adjustByteCount;
    }
}
