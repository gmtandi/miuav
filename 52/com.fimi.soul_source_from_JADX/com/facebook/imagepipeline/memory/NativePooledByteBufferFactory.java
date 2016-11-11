package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import java.io.InputStream;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class NativePooledByteBufferFactory implements PooledByteBufferFactory {
    private final NativeMemoryChunkPool mPool;
    private final PooledByteStreams mPooledByteStreams;

    public NativePooledByteBufferFactory(NativeMemoryChunkPool nativeMemoryChunkPool, PooledByteStreams pooledByteStreams) {
        this.mPool = nativeMemoryChunkPool;
        this.mPooledByteStreams = pooledByteStreams;
    }

    @VisibleForTesting
    NativePooledByteBuffer newByteBuf(InputStream inputStream, NativePooledByteBufferOutputStream nativePooledByteBufferOutputStream) {
        this.mPooledByteStreams.copy(inputStream, nativePooledByteBufferOutputStream);
        return nativePooledByteBufferOutputStream.toByteBuffer();
    }

    public NativePooledByteBuffer newByteBuffer(int i) {
        Preconditions.checkArgument(i > 0);
        CloseableReference of = CloseableReference.of(this.mPool.get(i), this.mPool);
        try {
            NativePooledByteBuffer nativePooledByteBuffer = new NativePooledByteBuffer(of, i);
            return nativePooledByteBuffer;
        } finally {
            of.close();
        }
    }

    public NativePooledByteBuffer newByteBuffer(InputStream inputStream) {
        NativePooledByteBufferOutputStream nativePooledByteBufferOutputStream = new NativePooledByteBufferOutputStream(this.mPool);
        try {
            NativePooledByteBuffer newByteBuf = newByteBuf(inputStream, nativePooledByteBufferOutputStream);
            return newByteBuf;
        } finally {
            nativePooledByteBufferOutputStream.close();
        }
    }

    public NativePooledByteBuffer newByteBuffer(InputStream inputStream, int i) {
        NativePooledByteBufferOutputStream nativePooledByteBufferOutputStream = new NativePooledByteBufferOutputStream(this.mPool, i);
        try {
            NativePooledByteBuffer newByteBuf = newByteBuf(inputStream, nativePooledByteBufferOutputStream);
            return newByteBuf;
        } finally {
            nativePooledByteBufferOutputStream.close();
        }
    }

    public NativePooledByteBuffer newByteBuffer(byte[] bArr) {
        NativePooledByteBufferOutputStream nativePooledByteBufferOutputStream = new NativePooledByteBufferOutputStream(this.mPool, bArr.length);
        try {
            nativePooledByteBufferOutputStream.write(bArr, 0, bArr.length);
            NativePooledByteBuffer toByteBuffer = nativePooledByteBufferOutputStream.toByteBuffer();
            nativePooledByteBufferOutputStream.close();
            return toByteBuffer;
        } catch (Throwable e) {
            throw Throwables.propagate(e);
        } catch (Throwable th) {
            nativePooledByteBufferOutputStream.close();
        }
    }

    public NativePooledByteBufferOutputStream newOutputStream() {
        return new NativePooledByteBufferOutputStream(this.mPool);
    }

    public NativePooledByteBufferOutputStream newOutputStream(int i) {
        return new NativePooledByteBufferOutputStream(this.mPool, i);
    }
}
