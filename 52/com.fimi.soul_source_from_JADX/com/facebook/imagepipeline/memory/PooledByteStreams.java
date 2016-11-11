package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import java.io.InputStream;
import java.io.OutputStream;

public class PooledByteStreams {
    private static final int DEFAULT_TEMP_BUF_SIZE = 16384;
    private final ByteArrayPool mByteArrayPool;
    private final int mTempBufSize;

    public PooledByteStreams(ByteArrayPool byteArrayPool) {
        this(byteArrayPool, DEFAULT_TEMP_BUF_SIZE);
    }

    @VisibleForTesting
    PooledByteStreams(ByteArrayPool byteArrayPool, int i) {
        Preconditions.checkArgument(i > 0);
        this.mTempBufSize = i;
        this.mByteArrayPool = byteArrayPool;
    }

    public long copy(InputStream inputStream, OutputStream outputStream) {
        long j = 0;
        byte[] bArr = (byte[]) this.mByteArrayPool.get(this.mTempBufSize);
        while (true) {
            int read = inputStream.read(bArr, 0, this.mTempBufSize);
            if (read == -1) {
                break;
            }
            try {
                outputStream.write(bArr, 0, read);
                j += (long) read;
            } finally {
                j = this.mByteArrayPool;
                j.release(bArr);
            }
        }
        return j;
    }

    public long copy(InputStream inputStream, OutputStream outputStream, long j) {
        long j2 = 0;
        boolean z = false;
        if (j > 0) {
            z = true;
        }
        Preconditions.checkState(z);
        byte[] bArr = (byte[]) this.mByteArrayPool.get(this.mTempBufSize);
        while (j2 < j) {
            int read = inputStream.read(bArr, 0, (int) Math.min((long) this.mTempBufSize, j - j2));
            if (read == -1) {
                return j2;
            }
            try {
                outputStream.write(bArr, 0, read);
                j2 += (long) read;
            } finally {
                j2 = this.mByteArrayPool;
                j2.release(bArr);
            }
        }
        this.mByteArrayPool.release(bArr);
        return j2;
    }
}
