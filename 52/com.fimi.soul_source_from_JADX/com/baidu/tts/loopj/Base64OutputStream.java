package com.baidu.tts.loopj;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.codehaus.jackson.smile.SmileConstants;

public class Base64OutputStream extends FilterOutputStream {
    private static byte[] EMPTY;
    private int bpos;
    private byte[] buffer;
    private final Coder coder;
    private final int flags;

    static {
        EMPTY = new byte[0];
    }

    public Base64OutputStream(OutputStream outputStream, int i) {
        this(outputStream, i, true);
    }

    public Base64OutputStream(OutputStream outputStream, int i, boolean z) {
        super(outputStream);
        this.buffer = null;
        this.bpos = 0;
        this.flags = i;
        if (z) {
            this.coder = new Encoder(i, null);
        } else {
            this.coder = new Decoder(i, null);
        }
    }

    private byte[] embiggen(byte[] bArr, int i) {
        return (bArr == null || bArr.length < i) ? new byte[i] : bArr;
    }

    private void flushBuffer() {
        if (this.bpos > 0) {
            internalWrite(this.buffer, 0, this.bpos, false);
            this.bpos = 0;
        }
    }

    private void internalWrite(byte[] bArr, int i, int i2, boolean z) {
        this.coder.output = embiggen(this.coder.output, this.coder.maxOutputSize(i2));
        if (this.coder.process(bArr, i, i2, z)) {
            this.out.write(this.coder.output, 0, this.coder.op);
            return;
        }
        throw new Base64DataException("bad base-64");
    }

    public void close() {
        IOException e;
        IOException iOException = null;
        try {
            flushBuffer();
            internalWrite(EMPTY, 0, 0, true);
        } catch (IOException e2) {
            iOException = e2;
        }
        try {
            if ((this.flags & 16) == 0) {
                this.out.close();
            } else {
                this.out.flush();
            }
            e2 = iOException;
        } catch (IOException e3) {
            e2 = e3;
            if (iOException == null) {
                e2 = iOException;
            }
        }
        if (e2 != null) {
            throw e2;
        }
    }

    public void write(int i) {
        if (this.buffer == null) {
            this.buffer = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
        }
        if (this.bpos >= this.buffer.length) {
            internalWrite(this.buffer, 0, this.bpos, false);
            this.bpos = 0;
        }
        byte[] bArr = this.buffer;
        int i2 = this.bpos;
        this.bpos = i2 + 1;
        bArr[i2] = (byte) i;
    }

    public void write(byte[] bArr, int i, int i2) {
        if (i2 > 0) {
            flushBuffer();
            internalWrite(bArr, i, i2, false);
        }
    }
}
