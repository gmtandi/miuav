package org.codehaus.jackson.impl;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.ByteArrayInputStream;
import java.io.CharConversionException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.format.InputAccessor;
import org.codehaus.jackson.format.MatchStrength;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.io.MergedStream;
import org.codehaus.jackson.io.UTF32Reader;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.sym.BytesToNameCanonicalizer;
import org.codehaus.jackson.sym.CharsToNameCanonicalizer;

public final class ByteSourceBootstrapper {
    static final byte UTF8_BOM_1 = (byte) -17;
    static final byte UTF8_BOM_2 = (byte) -69;
    static final byte UTF8_BOM_3 = (byte) -65;
    protected boolean _bigEndian;
    private final boolean _bufferRecyclable;
    protected int _bytesPerChar;
    final IOContext _context;
    final InputStream _in;
    final byte[] _inputBuffer;
    private int _inputEnd;
    protected int _inputProcessed;
    private int _inputPtr;

    /* renamed from: org.codehaus.jackson.impl.ByteSourceBootstrapper.1 */
    /* synthetic */ class C35731 {
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$JsonEncoding;

        static {
            $SwitchMap$org$codehaus$jackson$JsonEncoding = new int[JsonEncoding.values().length];
            try {
                $SwitchMap$org$codehaus$jackson$JsonEncoding[JsonEncoding.UTF32_BE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonEncoding[JsonEncoding.UTF32_LE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonEncoding[JsonEncoding.UTF16_BE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonEncoding[JsonEncoding.UTF16_LE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonEncoding[JsonEncoding.UTF8.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public ByteSourceBootstrapper(IOContext iOContext, InputStream inputStream) {
        this._bigEndian = true;
        this._bytesPerChar = 0;
        this._context = iOContext;
        this._in = inputStream;
        this._inputBuffer = iOContext.allocReadIOBuffer();
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._inputProcessed = 0;
        this._bufferRecyclable = true;
    }

    public ByteSourceBootstrapper(IOContext iOContext, byte[] bArr, int i, int i2) {
        this._bigEndian = true;
        this._bytesPerChar = 0;
        this._context = iOContext;
        this._in = null;
        this._inputBuffer = bArr;
        this._inputPtr = i;
        this._inputEnd = i + i2;
        this._inputProcessed = -i;
        this._bufferRecyclable = false;
    }

    private boolean checkUTF16(int i) {
        if ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) == 0) {
            this._bigEndian = true;
        } else if ((i & Util.MASK_8BIT) != 0) {
            return false;
        } else {
            this._bigEndian = false;
        }
        this._bytesPerChar = 2;
        return true;
    }

    private boolean checkUTF32(int i) {
        if ((i >> 8) == 0) {
            this._bigEndian = true;
        } else if ((ViewCompat.MEASURED_SIZE_MASK & i) == 0) {
            this._bigEndian = false;
        } else if ((-16711681 & i) == 0) {
            reportWeirdUCS4("3412");
        } else if ((-65281 & i) != 0) {
            return false;
        } else {
            reportWeirdUCS4("2143");
        }
        this._bytesPerChar = 4;
        return true;
    }

    private boolean handleBOM(int i) {
        switch (i) {
            case -16842752:
                break;
            case -131072:
                this._inputPtr += 4;
                this._bytesPerChar = 4;
                this._bigEndian = false;
                return true;
            case 65279:
                this._bigEndian = true;
                this._inputPtr += 4;
                this._bytesPerChar = 4;
                return true;
            case 65534:
                reportWeirdUCS4("2143");
                break;
        }
        reportWeirdUCS4("3412");
        int i2 = i >>> 16;
        if (i2 == 65279) {
            this._inputPtr += 2;
            this._bytesPerChar = 2;
            this._bigEndian = true;
            return true;
        } else if (i2 == 65534) {
            this._inputPtr += 2;
            this._bytesPerChar = 2;
            this._bigEndian = false;
            return true;
        } else if ((i >>> 8) != 15711167) {
            return false;
        } else {
            this._inputPtr += 3;
            this._bytesPerChar = 1;
            this._bigEndian = true;
            return true;
        }
    }

    public static MatchStrength hasJSONFormat(InputAccessor inputAccessor) {
        if (!inputAccessor.hasMoreBytes()) {
            return MatchStrength.INCONCLUSIVE;
        }
        byte nextByte = inputAccessor.nextByte();
        if (nextByte == -17) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != -69) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != -65) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            nextByte = inputAccessor.nextByte();
        }
        int skipSpace = skipSpace(inputAccessor, nextByte);
        if (skipSpace < 0) {
            return MatchStrength.INCONCLUSIVE;
        }
        int skipSpace2;
        if (skipSpace == Opcodes.LSHR) {
            skipSpace2 = skipSpace(inputAccessor);
            return skipSpace2 < 0 ? MatchStrength.INCONCLUSIVE : (skipSpace2 == 34 || skipSpace2 == Opcodes.LUSHR) ? MatchStrength.SOLID_MATCH : MatchStrength.NO_MATCH;
        } else if (skipSpace == 91) {
            skipSpace2 = skipSpace(inputAccessor);
            return skipSpace2 < 0 ? MatchStrength.INCONCLUSIVE : (skipSpace2 == 93 || skipSpace2 == 91) ? MatchStrength.SOLID_MATCH : MatchStrength.SOLID_MATCH;
        } else {
            MatchStrength matchStrength = MatchStrength.WEAK_MATCH;
            if (skipSpace == 34) {
                return matchStrength;
            }
            if (skipSpace <= 57 && skipSpace >= 48) {
                return matchStrength;
            }
            if (skipSpace != 45) {
                return skipSpace == Opcodes.FDIV ? tryMatch(inputAccessor, "ull", matchStrength) : skipSpace == Opcodes.INEG ? tryMatch(inputAccessor, "rue", matchStrength) : skipSpace == Opcodes.FSUB ? tryMatch(inputAccessor, "alse", matchStrength) : MatchStrength.NO_MATCH;
            } else {
                skipSpace = skipSpace(inputAccessor);
                return skipSpace < 0 ? MatchStrength.INCONCLUSIVE : (skipSpace > 57 || skipSpace < 48) ? MatchStrength.NO_MATCH : matchStrength;
            }
        }
    }

    private void reportWeirdUCS4(String str) {
        throw new CharConversionException("Unsupported UCS-4 endianness (" + str + ") detected");
    }

    private static final int skipSpace(InputAccessor inputAccessor) {
        return !inputAccessor.hasMoreBytes() ? -1 : skipSpace(inputAccessor, inputAccessor.nextByte());
    }

    private static final int skipSpace(InputAccessor inputAccessor, byte b) {
        while (true) {
            int nextByte;
            int i = nextByte & Util.MASK_8BIT;
            if (i != 32 && i != 13 && i != 10 && i != 9) {
                return i;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return -1;
            }
            nextByte = inputAccessor.nextByte();
            i = nextByte & Util.MASK_8BIT;
        }
    }

    private static final MatchStrength tryMatch(InputAccessor inputAccessor, String str, MatchStrength matchStrength) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != str.charAt(i)) {
                return MatchStrength.NO_MATCH;
            }
        }
        return matchStrength;
    }

    public JsonParser constructParser(int i, ObjectCodec objectCodec, BytesToNameCanonicalizer bytesToNameCanonicalizer, CharsToNameCanonicalizer charsToNameCanonicalizer) {
        JsonEncoding detectEncoding = detectEncoding();
        boolean enabledIn = Feature.CANONICALIZE_FIELD_NAMES.enabledIn(i);
        boolean enabledIn2 = Feature.INTERN_FIELD_NAMES.enabledIn(i);
        if (detectEncoding == JsonEncoding.UTF8 && enabledIn) {
            BytesToNameCanonicalizer makeChild = bytesToNameCanonicalizer.makeChild(enabledIn, enabledIn2);
            return new Utf8StreamParser(this._context, i, this._in, objectCodec, makeChild, this._inputBuffer, this._inputPtr, this._inputEnd, this._bufferRecyclable);
        }
        return new ReaderBasedParser(this._context, i, constructReader(), objectCodec, charsToNameCanonicalizer.makeChild(enabledIn, enabledIn2));
    }

    public Reader constructReader() {
        JsonEncoding encoding = this._context.getEncoding();
        switch (C35731.$SwitchMap$org$codehaus$jackson$JsonEncoding[encoding.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return new UTF32Reader(this._context, this._in, this._inputBuffer, this._inputPtr, this._inputEnd, this._context.getEncoding().isBigEndian());
            case Type.BYTE /*3*/:
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
            case Type.INT /*5*/:
                InputStream inputStream = this._in;
                InputStream byteArrayInputStream = inputStream == null ? new ByteArrayInputStream(this._inputBuffer, this._inputPtr, this._inputEnd) : this._inputPtr < this._inputEnd ? new MergedStream(this._context, inputStream, this._inputBuffer, this._inputPtr, this._inputEnd) : inputStream;
                return new InputStreamReader(byteArrayInputStream, encoding.getJavaName());
            default:
                throw new RuntimeException("Internal error");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.codehaus.jackson.JsonEncoding detectEncoding() {
        /*
        r5 = this;
        r0 = 1;
        r1 = 0;
        r2 = 4;
        r2 = r5.ensureLoaded(r2);
        if (r2 == 0) goto L_0x0056;
    L_0x0009:
        r2 = r5._inputBuffer;
        r3 = r5._inputPtr;
        r2 = r2[r3];
        r2 = r2 << 24;
        r3 = r5._inputBuffer;
        r4 = r5._inputPtr;
        r4 = r4 + 1;
        r3 = r3[r4];
        r3 = r3 & 255;
        r3 = r3 << 16;
        r2 = r2 | r3;
        r3 = r5._inputBuffer;
        r4 = r5._inputPtr;
        r4 = r4 + 2;
        r3 = r3[r4];
        r3 = r3 & 255;
        r3 = r3 << 8;
        r2 = r2 | r3;
        r3 = r5._inputBuffer;
        r4 = r5._inputPtr;
        r4 = r4 + 3;
        r3 = r3[r4];
        r3 = r3 & 255;
        r2 = r2 | r3;
        r3 = r5.handleBOM(r2);
        if (r3 == 0) goto L_0x0046;
    L_0x003c:
        if (r0 != 0) goto L_0x0079;
    L_0x003e:
        r0 = org.codehaus.jackson.JsonEncoding.UTF8;
    L_0x0040:
        r1 = r5._context;
        r1.setEncoding(r0);
        return r0;
    L_0x0046:
        r3 = r5.checkUTF32(r2);
        if (r3 != 0) goto L_0x003c;
    L_0x004c:
        r2 = r2 >>> 16;
        r2 = r5.checkUTF16(r2);
        if (r2 != 0) goto L_0x003c;
    L_0x0054:
        r0 = r1;
        goto L_0x003c;
    L_0x0056:
        r2 = 2;
        r2 = r5.ensureLoaded(r2);
        if (r2 == 0) goto L_0x0054;
    L_0x005d:
        r2 = r5._inputBuffer;
        r3 = r5._inputPtr;
        r2 = r2[r3];
        r2 = r2 & 255;
        r2 = r2 << 8;
        r3 = r5._inputBuffer;
        r4 = r5._inputPtr;
        r4 = r4 + 1;
        r3 = r3[r4];
        r3 = r3 & 255;
        r2 = r2 | r3;
        r2 = r5.checkUTF16(r2);
        if (r2 == 0) goto L_0x0054;
    L_0x0078:
        goto L_0x003c;
    L_0x0079:
        r0 = r5._bytesPerChar;
        switch(r0) {
            case 1: goto L_0x0086;
            case 2: goto L_0x0089;
            case 3: goto L_0x007e;
            case 4: goto L_0x0093;
            default: goto L_0x007e;
        };
    L_0x007e:
        r0 = new java.lang.RuntimeException;
        r1 = "Internal error";
        r0.<init>(r1);
        throw r0;
    L_0x0086:
        r0 = org.codehaus.jackson.JsonEncoding.UTF8;
        goto L_0x0040;
    L_0x0089:
        r0 = r5._bigEndian;
        if (r0 == 0) goto L_0x0090;
    L_0x008d:
        r0 = org.codehaus.jackson.JsonEncoding.UTF16_BE;
        goto L_0x0040;
    L_0x0090:
        r0 = org.codehaus.jackson.JsonEncoding.UTF16_LE;
        goto L_0x0040;
    L_0x0093:
        r0 = r5._bigEndian;
        if (r0 == 0) goto L_0x009a;
    L_0x0097:
        r0 = org.codehaus.jackson.JsonEncoding.UTF32_BE;
        goto L_0x0040;
    L_0x009a:
        r0 = org.codehaus.jackson.JsonEncoding.UTF32_LE;
        goto L_0x0040;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.ByteSourceBootstrapper.detectEncoding():org.codehaus.jackson.JsonEncoding");
    }

    protected boolean ensureLoaded(int i) {
        int i2 = this._inputEnd - this._inputPtr;
        while (i2 < i) {
            int read = this._in == null ? -1 : this._in.read(this._inputBuffer, this._inputEnd, this._inputBuffer.length - this._inputEnd);
            if (read < 1) {
                return false;
            }
            this._inputEnd += read;
            i2 = read + i2;
        }
        return true;
    }
}
