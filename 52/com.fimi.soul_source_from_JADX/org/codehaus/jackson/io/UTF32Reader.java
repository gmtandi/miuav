package org.codehaus.jackson.io;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.CharConversionException;
import java.io.InputStream;

public final class UTF32Reader extends BaseReader {
    final boolean mBigEndian;
    int mByteCount;
    int mCharCount;
    char mSurrogate;

    public UTF32Reader(IOContext iOContext, InputStream inputStream, byte[] bArr, int i, int i2, boolean z) {
        super(iOContext, inputStream, bArr, i, i2);
        this.mSurrogate = '\u0000';
        this.mCharCount = 0;
        this.mByteCount = 0;
        this.mBigEndian = z;
    }

    private boolean loadMore(int i) {
        this.mByteCount += this._length - i;
        int i2;
        if (i > 0) {
            if (this._ptr > 0) {
                for (i2 = 0; i2 < i; i2++) {
                    this._buffer[i2] = this._buffer[this._ptr + i2];
                }
                this._ptr = 0;
            }
            this._length = i;
        } else {
            this._ptr = 0;
            i2 = this._in.read(this._buffer);
            if (i2 < 1) {
                this._length = 0;
                if (i2 < 0) {
                    freeBuffers();
                    return false;
                }
                reportStrangeStream();
            }
            this._length = i2;
        }
        while (this._length < 4) {
            int read = this._in.read(this._buffer, this._length, this._buffer.length - this._length);
            if (read < 1) {
                if (read < 0) {
                    freeBuffers();
                    reportUnexpectedEOF(this._length, 4);
                }
                reportStrangeStream();
            }
            this._length = read + this._length;
        }
        return true;
    }

    private void reportInvalid(int i, int i2, String str) {
        throw new CharConversionException("Invalid UTF-32 character 0x" + Integer.toHexString(i) + str + " at char #" + (this.mCharCount + i2) + ", byte #" + ((this.mByteCount + this._ptr) - 1) + ")");
    }

    private void reportUnexpectedEOF(int i, int i2) {
        throw new CharConversionException("Unexpected EOF in the middle of a 4-byte UTF-32 char: got " + i + ", needed " + i2 + ", at char #" + this.mCharCount + ", byte #" + (this.mByteCount + i) + ")");
    }

    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    public /* bridge */ /* synthetic */ int read() {
        return super.read();
    }

    public int read(char[] cArr, int i, int i2) {
        if (this._buffer == null) {
            return -1;
        }
        if (i2 < 1) {
            return i2;
        }
        int i3;
        int i4;
        if (i < 0 || i + i2 > cArr.length) {
            reportBounds(cArr, i, i2);
        }
        int i5 = i2 + i;
        if (this.mSurrogate != '\u0000') {
            i3 = i + 1;
            cArr[i] = this.mSurrogate;
            this.mSurrogate = '\u0000';
        } else {
            i4 = this._length - this._ptr;
            if (i4 < 4 && !loadMore(i4)) {
                return -1;
            }
            i3 = i;
        }
        while (i3 < i5) {
            int i6 = this._ptr;
            if (this.mBigEndian) {
                i6 = (this._buffer[i6 + 3] & Util.MASK_8BIT) | (((this._buffer[i6] << 24) | ((this._buffer[i6 + 1] & Util.MASK_8BIT) << 16)) | ((this._buffer[i6 + 2] & Util.MASK_8BIT) << 8));
            } else {
                i6 = (this._buffer[i6 + 3] << 24) | (((this._buffer[i6] & Util.MASK_8BIT) | ((this._buffer[i6 + 1] & Util.MASK_8BIT) << 8)) | ((this._buffer[i6 + 2] & Util.MASK_8BIT) << 16));
            }
            this._ptr += 4;
            if (i6 > Util.MASK_16BIT) {
                if (i6 > 1114111) {
                    reportInvalid(i6, i3 - i, "(above " + Integer.toHexString(1114111) + ") ");
                }
                i6 -= AccessibilityNodeInfoCompat.ACTION_CUT;
                i4 = i3 + 1;
                cArr[i3] = (char) (55296 + (i6 >> 10));
                i6 = (i6 & 1023) | 56320;
                if (i4 >= i5) {
                    this.mSurrogate = (char) i6;
                    break;
                }
            }
            i4 = i3;
            i3 = i4 + 1;
            cArr[i4] = (char) i6;
            if (this._ptr >= this._length) {
                i4 = i3;
                break;
            }
        }
        i4 = i3;
        i2 = i4 - i;
        this.mCharCount += i2;
        return i2;
    }
}
