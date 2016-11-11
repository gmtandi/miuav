package com.facebook.binaryresource;

import com.facebook.common.internal.Preconditions;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ByteArrayBinaryResource implements BinaryResource {
    private final byte[] mBytes;

    public ByteArrayBinaryResource(byte[] bArr) {
        this.mBytes = (byte[]) Preconditions.checkNotNull(bArr);
    }

    public InputStream openStream() {
        return new ByteArrayInputStream(this.mBytes);
    }

    public byte[] read() {
        return this.mBytes;
    }

    public long size() {
        return (long) this.mBytes.length;
    }
}
