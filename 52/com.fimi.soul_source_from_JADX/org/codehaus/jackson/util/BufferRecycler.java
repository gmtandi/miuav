package org.codehaus.jackson.util;

import it.p074a.p075a.C2799f;

public class BufferRecycler {
    public static final int DEFAULT_WRITE_CONCAT_BUFFER_LEN = 2000;
    protected final byte[][] _byteBuffers;
    protected final char[][] _charBuffers;

    public enum ByteBufferType {
        READ_IO_BUFFER(4000),
        WRITE_ENCODING_BUFFER(4000),
        WRITE_CONCAT_BUFFER(BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
        
        private final int size;

        private ByteBufferType(int i) {
            this.size = i;
        }
    }

    public enum CharBufferType {
        TOKEN_BUFFER(BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN),
        CONCAT_BUFFER(BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN),
        TEXT_BUFFER(C2799f.f14282t),
        NAME_COPY_BUFFER(C2799f.f14282t);
        
        private final int size;

        private CharBufferType(int i) {
            this.size = i;
        }
    }

    public BufferRecycler() {
        this._byteBuffers = new byte[ByteBufferType.values().length][];
        this._charBuffers = new char[CharBufferType.values().length][];
    }

    private final byte[] balloc(int i) {
        return new byte[i];
    }

    private final char[] calloc(int i) {
        return new char[i];
    }

    public final byte[] allocByteBuffer(ByteBufferType byteBufferType) {
        int ordinal = byteBufferType.ordinal();
        byte[] bArr = this._byteBuffers[ordinal];
        if (bArr == null) {
            return balloc(byteBufferType.size);
        }
        this._byteBuffers[ordinal] = null;
        return bArr;
    }

    public final char[] allocCharBuffer(CharBufferType charBufferType) {
        return allocCharBuffer(charBufferType, 0);
    }

    public final char[] allocCharBuffer(CharBufferType charBufferType, int i) {
        if (charBufferType.size > i) {
            i = charBufferType.size;
        }
        int ordinal = charBufferType.ordinal();
        char[] cArr = this._charBuffers[ordinal];
        if (cArr == null || cArr.length < i) {
            return calloc(i);
        }
        this._charBuffers[ordinal] = null;
        return cArr;
    }

    public final void releaseByteBuffer(ByteBufferType byteBufferType, byte[] bArr) {
        this._byteBuffers[byteBufferType.ordinal()] = bArr;
    }

    public final void releaseCharBuffer(CharBufferType charBufferType, char[] cArr) {
        this._charBuffers[charBufferType.ordinal()] = cArr;
    }
}
