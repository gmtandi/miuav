package org.codehaus.jackson.io;

import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

abstract class BaseReader extends Reader {
    protected static final int LAST_VALID_UNICODE_CHAR = 1114111;
    protected static final char NULL_BYTE = '\u0000';
    protected static final char NULL_CHAR = '\u0000';
    protected byte[] _buffer;
    protected final IOContext _context;
    protected InputStream _in;
    protected int _length;
    protected int _ptr;
    protected char[] _tmpBuf;

    protected BaseReader(IOContext iOContext, InputStream inputStream, byte[] bArr, int i, int i2) {
        this._tmpBuf = null;
        this._context = iOContext;
        this._in = inputStream;
        this._buffer = bArr;
        this._ptr = i;
        this._length = i2;
    }

    public void close() {
        InputStream inputStream = this._in;
        if (inputStream != null) {
            this._in = null;
            freeBuffers();
            inputStream.close();
        }
    }

    public final void freeBuffers() {
        byte[] bArr = this._buffer;
        if (bArr != null) {
            this._buffer = null;
            this._context.releaseReadIOBuffer(bArr);
        }
    }

    public int read() {
        if (this._tmpBuf == null) {
            this._tmpBuf = new char[1];
        }
        return read(this._tmpBuf, 0, 1) < 1 ? -1 : this._tmpBuf[0];
    }

    protected void reportBounds(char[] cArr, int i, int i2) {
        throw new ArrayIndexOutOfBoundsException("read(buf," + i + MiPushClient.ACCEPT_TIME_SEPARATOR + i2 + "), cbuf[" + cArr.length + "]");
    }

    protected void reportStrangeStream() {
        throw new IOException("Strange I/O stream, returned 0 bytes on read");
    }
}
