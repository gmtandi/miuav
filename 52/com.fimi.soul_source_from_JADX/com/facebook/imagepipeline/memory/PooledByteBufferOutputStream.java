package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Throwables;
import java.io.OutputStream;

public abstract class PooledByteBufferOutputStream extends OutputStream {
    public void close() {
        try {
            super.close();
        } catch (Throwable e) {
            Throwables.propagate(e);
        }
    }

    public abstract int size();

    public abstract PooledByteBuffer toByteBuffer();
}
