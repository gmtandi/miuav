package com.facebook.imagepipeline.memory;

import java.io.Closeable;

public interface PooledByteBuffer extends Closeable {

    public class ClosedException extends RuntimeException {
        public ClosedException() {
            super("Invalid bytebuf. Already closed");
        }
    }

    void close();

    long getNativePtr();

    boolean isClosed();

    byte read(int i);

    void read(int i, byte[] bArr, int i2, int i3);

    int size();
}