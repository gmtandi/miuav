package org.codehaus.jackson.io;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;

public final class UTF8Writer extends Writer {
    static final int SURR1_FIRST = 55296;
    static final int SURR1_LAST = 56319;
    static final int SURR2_FIRST = 56320;
    static final int SURR2_LAST = 57343;
    protected final IOContext _context;
    OutputStream _out;
    byte[] _outBuffer;
    final int _outBufferEnd;
    int _outPtr;
    int _surrogate;

    public UTF8Writer(IOContext iOContext, OutputStream outputStream) {
        this._surrogate = 0;
        this._context = iOContext;
        this._out = outputStream;
        this._outBuffer = iOContext.allocWriteEncodingBuffer();
        this._outBufferEnd = this._outBuffer.length - 4;
        this._outPtr = 0;
    }

    private int convertSurrogate(int i) {
        int i2 = this._surrogate;
        this._surrogate = 0;
        if (i >= SURR2_FIRST && i <= SURR2_LAST) {
            return (((i2 - SURR1_FIRST) << 10) + AccessibilityNodeInfoCompat.ACTION_CUT) + (i - SURR2_FIRST);
        }
        throw new IOException("Broken surrogate pair: first char 0x" + Integer.toHexString(i2) + ", second 0x" + Integer.toHexString(i) + "; illegal combination");
    }

    private void throwIllegal(int i) {
        if (i > 1114111) {
            throw new IOException("Illegal character point (0x" + Integer.toHexString(i) + ") to output; max is 0x10FFFF as per RFC 4627");
        } else if (i < SURR1_FIRST) {
            throw new IOException("Illegal character point (0x" + Integer.toHexString(i) + ") to output");
        } else if (i <= SURR1_LAST) {
            throw new IOException("Unmatched first part of surrogate pair (0x" + Integer.toHexString(i) + ")");
        } else {
            throw new IOException("Unmatched second part of surrogate pair (0x" + Integer.toHexString(i) + ")");
        }
    }

    public Writer append(char c) {
        write((int) c);
        return this;
    }

    public void close() {
        if (this._out != null) {
            if (this._outPtr > 0) {
                this._out.write(this._outBuffer, 0, this._outPtr);
                this._outPtr = 0;
            }
            OutputStream outputStream = this._out;
            this._out = null;
            byte[] bArr = this._outBuffer;
            if (bArr != null) {
                this._outBuffer = null;
                this._context.releaseWriteEncodingBuffer(bArr);
            }
            outputStream.close();
            int i = this._surrogate;
            this._surrogate = 0;
            if (i > 0) {
                throwIllegal(i);
            }
        }
    }

    public void flush() {
        if (this._out != null) {
            if (this._outPtr > 0) {
                this._out.write(this._outBuffer, 0, this._outPtr);
                this._outPtr = 0;
            }
            this._out.flush();
        }
    }

    public void write(int i) {
        if (this._surrogate > 0) {
            i = convertSurrogate(i);
        } else if (i >= SURR1_FIRST && i <= SURR2_LAST) {
            if (i > SURR1_LAST) {
                throwIllegal(i);
            }
            this._surrogate = i;
            return;
        }
        if (this._outPtr >= this._outBufferEnd) {
            this._out.write(this._outBuffer, 0, this._outPtr);
            this._outPtr = 0;
        }
        if (i < SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
            byte[] bArr = this._outBuffer;
            int i2 = this._outPtr;
            this._outPtr = i2 + 1;
            bArr[i2] = (byte) i;
            return;
        }
        int i3 = this._outPtr;
        int i4;
        if (i < Opcodes.ACC_STRICT) {
            i4 = i3 + 1;
            this._outBuffer[i3] = (byte) ((i >> 6) | SmileConstants.TOKEN_PREFIX_SMALL_INT);
            i3 = i4 + 1;
            this._outBuffer[i4] = (byte) ((i & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        } else if (i <= Util.MASK_16BIT) {
            i4 = i3 + 1;
            this._outBuffer[i3] = (byte) ((i >> 12) | SmileConstants.TOKEN_PREFIX_MISC_OTHER);
            i2 = i4 + 1;
            this._outBuffer[i4] = (byte) (((i >> 6) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            i3 = i2 + 1;
            this._outBuffer[i2] = (byte) ((i & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        } else {
            if (i > 1114111) {
                throwIllegal(i);
            }
            i4 = i3 + 1;
            this._outBuffer[i3] = (byte) ((i >> 18) | 240);
            i2 = i4 + 1;
            this._outBuffer[i4] = (byte) (((i >> 12) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            i4 = i2 + 1;
            this._outBuffer[i2] = (byte) (((i >> 6) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            i3 = i4 + 1;
            this._outBuffer[i4] = (byte) ((i & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        }
        this._outPtr = i3;
    }

    public void write(String str) {
        write(str, 0, str.length());
    }

    public void write(String str, int i, int i2) {
        if (i2 >= 2) {
            int i3;
            if (this._surrogate > 0) {
                i3 = i + 1;
                i2--;
                write(convertSurrogate(str.charAt(i)));
                i = i3;
            }
            i3 = this._outPtr;
            byte[] bArr = this._outBuffer;
            int i4 = this._outBufferEnd;
            int i5 = i2 + i;
            int i6 = i;
            while (i6 < i5) {
                int i7;
                int i8;
                if (i3 >= i4) {
                    this._out.write(bArr, 0, i3);
                    i3 = 0;
                }
                int i9 = i6 + 1;
                char charAt = str.charAt(i6);
                char c;
                if (charAt < '\u0080') {
                    i7 = i3 + 1;
                    bArr[i3] = (byte) charAt;
                    i6 = i5 - i9;
                    i3 = i4 - i7;
                    if (i6 <= i3) {
                        i3 = i6;
                    }
                    i8 = i3 + i9;
                    i6 = i7;
                    i3 = i9;
                    while (i3 < i8) {
                        i9 = i3 + 1;
                        char charAt2 = str.charAt(i3);
                        if (charAt2 >= '\u0080') {
                            c = charAt2;
                            i3 = i6;
                            i6 = i9;
                            i9 = c;
                        } else {
                            i7 = i6 + 1;
                            bArr[i6] = (byte) charAt2;
                            i6 = i7;
                            i3 = i9;
                        }
                    }
                    int i10 = i6;
                    i6 = i3;
                    i3 = i10;
                } else {
                    c = charAt;
                    i6 = i9;
                    char c2 = c;
                }
                if (i9 < Opcodes.ACC_STRICT) {
                    i7 = i3 + 1;
                    bArr[i3] = (byte) ((i9 >> 6) | SmileConstants.TOKEN_PREFIX_SMALL_INT);
                    i3 = i7 + 1;
                    bArr[i7] = (byte) ((i9 & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                } else if (i9 < SURR1_FIRST || i9 > SURR2_LAST) {
                    i7 = i3 + 1;
                    bArr[i3] = (byte) ((i9 >> 12) | SmileConstants.TOKEN_PREFIX_MISC_OTHER);
                    i8 = i7 + 1;
                    bArr[i7] = (byte) (((i9 >> 6) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                    i3 = i8 + 1;
                    bArr[i8] = (byte) ((i9 & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                } else {
                    if (i9 > SURR1_LAST) {
                        this._outPtr = i3;
                        throwIllegal(i9);
                    }
                    this._surrogate = i9;
                    if (i6 >= i5) {
                        break;
                    }
                    i9 = i6 + 1;
                    i6 = convertSurrogate(str.charAt(i6));
                    if (i6 > 1114111) {
                        this._outPtr = i3;
                        throwIllegal(i6);
                    }
                    i7 = i3 + 1;
                    bArr[i3] = (byte) ((i6 >> 18) | 240);
                    i3 = i7 + 1;
                    bArr[i7] = (byte) (((i6 >> 12) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                    i7 = i3 + 1;
                    bArr[i3] = (byte) (((i6 >> 6) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                    i3 = i7 + 1;
                    bArr[i7] = (byte) ((i6 & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                    i6 = i9;
                }
            }
            this._outPtr = i3;
        } else if (i2 == 1) {
            write(str.charAt(i));
        }
    }

    public void write(char[] cArr) {
        write(cArr, 0, cArr.length);
    }

    public void write(char[] cArr, int i, int i2) {
        if (i2 >= 2) {
            int i3;
            if (this._surrogate > 0) {
                i3 = i + 1;
                i2--;
                write(convertSurrogate(cArr[i]));
                i = i3;
            }
            i3 = this._outPtr;
            byte[] bArr = this._outBuffer;
            int i4 = this._outBufferEnd;
            int i5 = i2 + i;
            int i6 = i;
            while (i6 < i5) {
                int i7;
                int i8;
                if (i3 >= i4) {
                    this._out.write(bArr, 0, i3);
                    i3 = 0;
                }
                int i9 = i6 + 1;
                char c = cArr[i6];
                char c2;
                if (c < '\u0080') {
                    i7 = i3 + 1;
                    bArr[i3] = (byte) c;
                    i6 = i5 - i9;
                    i3 = i4 - i7;
                    if (i6 <= i3) {
                        i3 = i6;
                    }
                    i8 = i3 + i9;
                    i6 = i7;
                    i3 = i9;
                    while (i3 < i8) {
                        i9 = i3 + 1;
                        char c3 = cArr[i3];
                        if (c3 >= '\u0080') {
                            c2 = c3;
                            i3 = i6;
                            i6 = i9;
                            i9 = c2;
                        } else {
                            i7 = i6 + 1;
                            bArr[i6] = (byte) c3;
                            i6 = i7;
                            i3 = i9;
                        }
                    }
                    int i10 = i6;
                    i6 = i3;
                    i3 = i10;
                } else {
                    c2 = c;
                    i6 = i9;
                    char c4 = c2;
                }
                if (i9 < Opcodes.ACC_STRICT) {
                    i7 = i3 + 1;
                    bArr[i3] = (byte) ((i9 >> 6) | SmileConstants.TOKEN_PREFIX_SMALL_INT);
                    i3 = i7 + 1;
                    bArr[i7] = (byte) ((i9 & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                } else if (i9 < SURR1_FIRST || i9 > SURR2_LAST) {
                    i7 = i3 + 1;
                    bArr[i3] = (byte) ((i9 >> 12) | SmileConstants.TOKEN_PREFIX_MISC_OTHER);
                    i8 = i7 + 1;
                    bArr[i7] = (byte) (((i9 >> 6) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                    i3 = i8 + 1;
                    bArr[i8] = (byte) ((i9 & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                } else {
                    if (i9 > SURR1_LAST) {
                        this._outPtr = i3;
                        throwIllegal(i9);
                    }
                    this._surrogate = i9;
                    if (i6 >= i5) {
                        break;
                    }
                    i9 = i6 + 1;
                    i6 = convertSurrogate(cArr[i6]);
                    if (i6 > 1114111) {
                        this._outPtr = i3;
                        throwIllegal(i6);
                    }
                    i7 = i3 + 1;
                    bArr[i3] = (byte) ((i6 >> 18) | 240);
                    i3 = i7 + 1;
                    bArr[i7] = (byte) (((i6 >> 12) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                    i7 = i3 + 1;
                    bArr[i3] = (byte) (((i6 >> 6) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                    i3 = i7 + 1;
                    bArr[i7] = (byte) ((i6 & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                    i6 = i9;
                }
            }
            this._outPtr = i3;
        } else if (i2 == 1) {
            write(cArr[i]);
        }
    }
}
