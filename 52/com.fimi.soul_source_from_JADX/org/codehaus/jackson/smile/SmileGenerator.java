package org.codehaus.jackson.smile;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.PrettyPrinter;
import org.codehaus.jackson.SerializableString;
import org.codehaus.jackson.impl.JsonGeneratorBase;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.io.SerializedString;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class SmileGenerator extends JsonGeneratorBase {
    protected static final long MAX_INT_AS_LONG = 2147483647L;
    private static final int MIN_BUFFER_LENGTH = 770;
    protected static final long MIN_INT_AS_LONG = -2147483648L;
    protected static final int SURR1_FIRST = 55296;
    protected static final int SURR1_LAST = 56319;
    protected static final int SURR2_FIRST = 56320;
    protected static final int SURR2_LAST = 57343;
    protected static final byte TOKEN_BYTE_BIG_DECIMAL = (byte) 42;
    protected static final byte TOKEN_BYTE_BIG_INTEGER = (byte) 38;
    protected static final byte TOKEN_BYTE_FLOAT_32 = (byte) 40;
    protected static final byte TOKEN_BYTE_FLOAT_64 = (byte) 41;
    protected static final byte TOKEN_BYTE_INT_32 = (byte) 36;
    protected static final byte TOKEN_BYTE_INT_64 = (byte) 37;
    protected static final byte TOKEN_BYTE_LONG_STRING_ASCII = (byte) -32;
    protected static final byte TOKEN_BYTE_LONG_STRING_UNICODE = (byte) -28;
    protected static final ThreadLocal<SoftReference<SmileBufferRecycler<SharedStringNode>>> _smileRecyclerRef;
    protected boolean _bufferRecyclable;
    protected int _bytesWritten;
    protected char[] _charBuffer;
    protected final int _charBufferLength;
    protected final IOContext _ioContext;
    protected final OutputStream _out;
    protected byte[] _outputBuffer;
    protected final int _outputEnd;
    protected int _outputTail;
    protected int _seenNameCount;
    protected SharedStringNode[] _seenNames;
    protected int _seenStringValueCount;
    protected SharedStringNode[] _seenStringValues;
    protected final SmileBufferRecycler<SharedStringNode> _smileBufferRecycler;
    protected int _smileFeatures;

    public enum Feature {
        WRITE_HEADER(true),
        WRITE_END_MARKER(false),
        ENCODE_BINARY_AS_7BIT(true),
        CHECK_SHARED_NAMES(true),
        CHECK_SHARED_STRING_VALUES(false);
        
        protected final boolean _defaultState;
        protected final int _mask;

        private Feature(boolean z) {
            this._defaultState = z;
            this._mask = 1 << ordinal();
        }

        public static int collectDefaults() {
            int i = 0;
            for (Feature feature : values()) {
                if (feature.enabledByDefault()) {
                    i |= feature.getMask();
                }
            }
            return i;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public int getMask() {
            return this._mask;
        }
    }

    public final class SharedStringNode {
        public final int index;
        public SharedStringNode next;
        public final String value;

        public SharedStringNode(String str, int i, SharedStringNode sharedStringNode) {
            this.value = str;
            this.index = i;
            this.next = sharedStringNode;
        }
    }

    static {
        _smileRecyclerRef = new ThreadLocal();
    }

    public SmileGenerator(IOContext iOContext, int i, int i2, ObjectCodec objectCodec, OutputStream outputStream) {
        super(i, objectCodec);
        this._outputTail = 0;
        this._smileFeatures = i2;
        this._ioContext = iOContext;
        this._smileBufferRecycler = _smileBufferRecycler();
        this._out = outputStream;
        this._bufferRecyclable = true;
        this._outputBuffer = iOContext.allocWriteEncodingBuffer();
        this._outputEnd = this._outputBuffer.length;
        this._charBuffer = iOContext.allocConcatBuffer();
        this._charBufferLength = this._charBuffer.length;
        if (this._outputEnd < MIN_BUFFER_LENGTH) {
            throw new IllegalStateException("Internal encoding buffer length (" + this._outputEnd + ") too short, must be at least " + MIN_BUFFER_LENGTH);
        }
        if ((Feature.CHECK_SHARED_NAMES.getMask() & i2) == 0) {
            this._seenNames = null;
            this._seenNameCount = -1;
        } else {
            this._seenNames = (SharedStringNode[]) this._smileBufferRecycler.allocSeenNamesBuffer();
            if (this._seenNames == null) {
                this._seenNames = new SharedStringNode[64];
            }
            this._seenNameCount = 0;
        }
        if ((Feature.CHECK_SHARED_STRING_VALUES.getMask() & i2) == 0) {
            this._seenStringValues = null;
            this._seenStringValueCount = -1;
            return;
        }
        this._seenStringValues = (SharedStringNode[]) this._smileBufferRecycler.allocSeenStringValuesBuffer();
        if (this._seenStringValues == null) {
            this._seenStringValues = new SharedStringNode[64];
        }
        this._seenStringValueCount = 0;
    }

    public SmileGenerator(IOContext iOContext, int i, int i2, ObjectCodec objectCodec, OutputStream outputStream, byte[] bArr, int i3, boolean z) {
        super(i, objectCodec);
        this._outputTail = 0;
        this._smileFeatures = i2;
        this._ioContext = iOContext;
        this._smileBufferRecycler = _smileBufferRecycler();
        this._out = outputStream;
        this._bufferRecyclable = z;
        this._outputTail = i3;
        this._outputBuffer = bArr;
        this._outputEnd = this._outputBuffer.length;
        this._charBuffer = iOContext.allocConcatBuffer();
        this._charBufferLength = this._charBuffer.length;
        if (this._outputEnd < MIN_BUFFER_LENGTH) {
            throw new IllegalStateException("Internal encoding buffer length (" + this._outputEnd + ") too short, must be at least " + MIN_BUFFER_LENGTH);
        }
        if ((Feature.CHECK_SHARED_NAMES.getMask() & i2) == 0) {
            this._seenNames = null;
            this._seenNameCount = -1;
        } else {
            this._seenNames = (SharedStringNode[]) this._smileBufferRecycler.allocSeenNamesBuffer();
            if (this._seenNames == null) {
                this._seenNames = new SharedStringNode[64];
            }
            this._seenNameCount = 0;
        }
        if ((Feature.CHECK_SHARED_STRING_VALUES.getMask() & i2) == 0) {
            this._seenStringValues = null;
            this._seenStringValueCount = -1;
            return;
        }
        this._seenStringValues = (SharedStringNode[]) this._smileBufferRecycler.allocSeenStringValuesBuffer();
        if (this._seenStringValues == null) {
            this._seenStringValues = new SharedStringNode[64];
        }
        this._seenStringValueCount = 0;
    }

    private final void _addSeenName(String str) {
        if (this._seenNameCount == this._seenNames.length) {
            if (this._seenNameCount == SmileConstants.MAX_SHARED_STRING_VALUES) {
                Arrays.fill(this._seenNames, null);
                this._seenNameCount = 0;
            } else {
                SharedStringNode[] sharedStringNodeArr = this._seenNames;
                this._seenNames = new SharedStringNode[SmileConstants.MAX_SHARED_STRING_VALUES];
                for (SharedStringNode sharedStringNode : sharedStringNodeArr) {
                    for (SharedStringNode sharedStringNode2 = sharedStringNodeArr[r1]; sharedStringNode2 != null; sharedStringNode2 = sharedStringNode2.next) {
                        int hashCode = sharedStringNode2.value.hashCode() & 1023;
                        sharedStringNode2.next = this._seenNames[hashCode];
                        this._seenNames[hashCode] = sharedStringNode2;
                    }
                }
            }
        }
        int hashCode2 = str.hashCode() & (this._seenNames.length - 1);
        this._seenNames[hashCode2] = new SharedStringNode(str, this._seenNameCount, this._seenNames[hashCode2]);
        this._seenNameCount++;
    }

    private final void _addSeenStringValue(String str) {
        if (this._seenStringValueCount == this._seenStringValues.length) {
            if (this._seenStringValueCount == SmileConstants.MAX_SHARED_STRING_VALUES) {
                Arrays.fill(this._seenStringValues, null);
                this._seenStringValueCount = 0;
            } else {
                SharedStringNode[] sharedStringNodeArr = this._seenStringValues;
                this._seenStringValues = new SharedStringNode[SmileConstants.MAX_SHARED_STRING_VALUES];
                for (SharedStringNode sharedStringNode : sharedStringNodeArr) {
                    for (SharedStringNode sharedStringNode2 = sharedStringNodeArr[r1]; sharedStringNode2 != null; sharedStringNode2 = sharedStringNode2.next) {
                        int hashCode = sharedStringNode2.value.hashCode() & 1023;
                        sharedStringNode2.next = this._seenStringValues[hashCode];
                        this._seenStringValues[hashCode] = sharedStringNode2;
                    }
                }
            }
        }
        int hashCode2 = str.hashCode() & (this._seenStringValues.length - 1);
        this._seenStringValues[hashCode2] = new SharedStringNode(str, this._seenStringValueCount, this._seenStringValues[hashCode2]);
        this._seenStringValueCount++;
    }

    private int _convertSurrogate(int i, int i2) {
        if (i2 >= SURR2_FIRST && i2 <= SURR2_LAST) {
            return (AccessibilityNodeInfoCompat.ACTION_CUT + ((i - SURR1_FIRST) << 10)) + (i2 - SURR2_FIRST);
        }
        throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(i2) + "; illegal combination");
    }

    private final void _ensureRoomForOutput(int i) {
        if (this._outputTail + i >= this._outputEnd) {
            _flushBuffer();
        }
    }

    private final int _findSeenName(String str) {
        int hashCode = str.hashCode();
        SharedStringNode sharedStringNode = this._seenNames[(this._seenNames.length - 1) & hashCode];
        if (sharedStringNode == null) {
            return -1;
        }
        if (sharedStringNode.value == str) {
            return sharedStringNode.index;
        }
        SharedStringNode sharedStringNode2 = sharedStringNode;
        do {
            sharedStringNode2 = sharedStringNode2.next;
            if (sharedStringNode2 == null) {
                do {
                    String str2 = sharedStringNode.value;
                    if (str2.hashCode() == hashCode && str2.equals(str)) {
                        return sharedStringNode.index;
                    }
                    sharedStringNode = sharedStringNode.next;
                } while (sharedStringNode != null);
                return -1;
            }
        } while (sharedStringNode2.value != str);
        return sharedStringNode2.index;
    }

    private final int _findSeenStringValue(String str) {
        int hashCode = str.hashCode();
        SharedStringNode sharedStringNode = this._seenStringValues[(this._seenStringValues.length - 1) & hashCode];
        if (sharedStringNode != null) {
            SharedStringNode sharedStringNode2 = sharedStringNode;
            while (sharedStringNode2.value != str) {
                sharedStringNode2 = sharedStringNode2.next;
                if (sharedStringNode2 == null) {
                    do {
                        String str2 = sharedStringNode.value;
                        if (str2.hashCode() == hashCode && str2.equals(str)) {
                            return sharedStringNode.index;
                        }
                        sharedStringNode = sharedStringNode.next;
                    } while (sharedStringNode != null);
                }
            }
            return sharedStringNode2.index;
        }
        return -1;
    }

    private void _mediumUTF8Encode(char[] cArr, int i, int i2) {
        int i3 = this._outputEnd - 4;
        int i4 = i;
        while (i4 < i2) {
            int i5;
            int i6;
            if (this._outputTail >= i3) {
                _flushBuffer();
            }
            int i7 = i4 + 1;
            char c = cArr[i4];
            if (c <= '\u007f') {
                byte[] bArr = this._outputBuffer;
                i5 = this._outputTail;
                this._outputTail = i5 + 1;
                bArr[i5] = (byte) c;
                i6 = i2 - i7;
                i4 = i3 - this._outputTail;
                if (i6 <= i4) {
                    i4 = i6;
                }
                i5 = i4 + i7;
                while (i7 < i5) {
                    i6 = i7 + 1;
                    c = cArr[i7];
                    if (c > '\u007f') {
                        i7 = c;
                        i4 = i6;
                    } else {
                        byte[] bArr2 = this._outputBuffer;
                        int i8 = this._outputTail;
                        this._outputTail = i8 + 1;
                        bArr2[i8] = (byte) c;
                        i7 = i6;
                    }
                }
                i4 = i7;
            } else {
                char c2 = c;
                i4 = i7;
                char c3 = c2;
            }
            if (i7 < Opcodes.ACC_STRICT) {
                bArr = this._outputBuffer;
                i5 = this._outputTail;
                this._outputTail = i5 + 1;
                bArr[i5] = (byte) ((i7 >> 6) | SmileConstants.TOKEN_PREFIX_SMALL_INT);
                bArr = this._outputBuffer;
                i5 = this._outputTail;
                this._outputTail = i5 + 1;
                bArr[i5] = (byte) ((i7 & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            } else if (i7 < SURR1_FIRST || i7 > SURR2_LAST) {
                bArr = this._outputBuffer;
                i5 = this._outputTail;
                this._outputTail = i5 + 1;
                bArr[i5] = (byte) ((i7 >> 12) | SmileConstants.TOKEN_PREFIX_MISC_OTHER);
                bArr = this._outputBuffer;
                i5 = this._outputTail;
                this._outputTail = i5 + 1;
                bArr[i5] = (byte) (((i7 >> 6) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                bArr = this._outputBuffer;
                i5 = this._outputTail;
                this._outputTail = i5 + 1;
                bArr[i5] = (byte) ((i7 & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            } else {
                if (i7 > SURR1_LAST) {
                    _throwIllegalSurrogate(i7);
                }
                if (i4 >= i2) {
                    _throwIllegalSurrogate(i7);
                }
                i6 = i4 + 1;
                i4 = _convertSurrogate(i7, cArr[i4]);
                if (i4 > 1114111) {
                    _throwIllegalSurrogate(i4);
                }
                bArr2 = this._outputBuffer;
                i5 = this._outputTail;
                this._outputTail = i5 + 1;
                bArr2[i5] = (byte) ((i4 >> 18) | 240);
                bArr2 = this._outputBuffer;
                i5 = this._outputTail;
                this._outputTail = i5 + 1;
                bArr2[i5] = (byte) (((i4 >> 12) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                bArr2 = this._outputBuffer;
                i5 = this._outputTail;
                this._outputTail = i5 + 1;
                bArr2[i5] = (byte) (((i4 >> 6) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                bArr2 = this._outputBuffer;
                i5 = this._outputTail;
                this._outputTail = i5 + 1;
                bArr2[i5] = (byte) ((i4 & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                i4 = i6;
            }
        }
    }

    private final int _shortUTF8Encode(char[] cArr, int i, int i2) {
        int i3 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        while (true) {
            char c = cArr[i];
            if (c > '\u007f') {
                return _shortUTF8Encode2(cArr, i, i2, i3);
            }
            int i4 = i3 + 1;
            bArr[i3] = (byte) c;
            i++;
            if (i >= i2) {
                i3 = i4 - this._outputTail;
                this._outputTail = i4;
                return i3;
            }
            i3 = i4;
        }
    }

    private final int _shortUTF8Encode2(char[] cArr, int i, int i2, int i3) {
        int i4;
        byte[] bArr = this._outputBuffer;
        while (i < i2) {
            int i5 = i + 1;
            char c = cArr[i];
            if (c <= '\u007f') {
                i4 = i3 + 1;
                bArr[i3] = (byte) c;
                i3 = i4;
                i = i5;
            } else if (c < '\u0800') {
                i4 = i3 + 1;
                bArr[i3] = (byte) ((c >> 6) | SmileConstants.TOKEN_PREFIX_SMALL_INT);
                i3 = i4 + 1;
                bArr[i4] = (byte) ((c & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                i = i5;
            } else if (c < '\ud800' || c > '\udfff') {
                i4 = i3 + 1;
                bArr[i3] = (byte) ((c >> 12) | SmileConstants.TOKEN_PREFIX_MISC_OTHER);
                int i6 = i4 + 1;
                bArr[i4] = (byte) (((c >> 6) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                i3 = i6 + 1;
                bArr[i6] = (byte) ((c & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                i = i5;
            } else {
                if (c > '\udbff') {
                    _throwIllegalSurrogate(c);
                }
                if (i5 >= i2) {
                    _throwIllegalSurrogate(c);
                }
                i = i5 + 1;
                i4 = _convertSurrogate(c, cArr[i5]);
                if (i4 > 1114111) {
                    _throwIllegalSurrogate(i4);
                }
                i5 = i3 + 1;
                bArr[i3] = (byte) ((i4 >> 18) | 240);
                int i7 = i5 + 1;
                bArr[i5] = (byte) (((i4 >> 12) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                i5 = i7 + 1;
                bArr[i7] = (byte) (((i4 >> 6) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                i3 = i5 + 1;
                bArr[i5] = (byte) ((i4 & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            }
        }
        i4 = i3 - this._outputTail;
        this._outputTail = i3;
        return i4;
    }

    private void _slowUTF8Encode(String str) {
        int length = str.length();
        int i = 0;
        int i2 = this._outputEnd - 4;
        while (i < length) {
            int i3;
            int i4;
            if (this._outputTail >= i2) {
                _flushBuffer();
            }
            int i5 = i + 1;
            char charAt = str.charAt(i);
            if (charAt <= '\u007f') {
                byte[] bArr = this._outputBuffer;
                i3 = this._outputTail;
                this._outputTail = i3 + 1;
                bArr[i3] = (byte) charAt;
                i4 = length - i5;
                i = i2 - this._outputTail;
                if (i4 <= i) {
                    i = i4;
                }
                i3 = i + i5;
                while (i5 < i3) {
                    i4 = i5 + 1;
                    charAt = str.charAt(i5);
                    if (charAt > '\u007f') {
                        i5 = charAt;
                        i = i4;
                    } else {
                        byte[] bArr2 = this._outputBuffer;
                        int i6 = this._outputTail;
                        this._outputTail = i6 + 1;
                        bArr2[i6] = (byte) charAt;
                        i5 = i4;
                    }
                }
                i = i5;
            } else {
                char c = charAt;
                i = i5;
                char c2 = c;
            }
            if (i5 < Opcodes.ACC_STRICT) {
                bArr = this._outputBuffer;
                i3 = this._outputTail;
                this._outputTail = i3 + 1;
                bArr[i3] = (byte) ((i5 >> 6) | SmileConstants.TOKEN_PREFIX_SMALL_INT);
                bArr = this._outputBuffer;
                i3 = this._outputTail;
                this._outputTail = i3 + 1;
                bArr[i3] = (byte) ((i5 & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            } else if (i5 < SURR1_FIRST || i5 > SURR2_LAST) {
                bArr = this._outputBuffer;
                i3 = this._outputTail;
                this._outputTail = i3 + 1;
                bArr[i3] = (byte) ((i5 >> 12) | SmileConstants.TOKEN_PREFIX_MISC_OTHER);
                bArr = this._outputBuffer;
                i3 = this._outputTail;
                this._outputTail = i3 + 1;
                bArr[i3] = (byte) (((i5 >> 6) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                bArr = this._outputBuffer;
                i3 = this._outputTail;
                this._outputTail = i3 + 1;
                bArr[i3] = (byte) ((i5 & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            } else {
                if (i5 > SURR1_LAST) {
                    _throwIllegalSurrogate(i5);
                }
                if (i >= length) {
                    _throwIllegalSurrogate(i5);
                }
                i4 = i + 1;
                i = _convertSurrogate(i5, str.charAt(i));
                if (i > 1114111) {
                    _throwIllegalSurrogate(i);
                }
                bArr2 = this._outputBuffer;
                i3 = this._outputTail;
                this._outputTail = i3 + 1;
                bArr2[i3] = (byte) ((i >> 18) | 240);
                bArr2 = this._outputBuffer;
                i3 = this._outputTail;
                this._outputTail = i3 + 1;
                bArr2[i3] = (byte) (((i >> 12) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                bArr2 = this._outputBuffer;
                i3 = this._outputTail;
                this._outputTail = i3 + 1;
                bArr2[i3] = (byte) (((i >> 6) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                bArr2 = this._outputBuffer;
                i3 = this._outputTail;
                this._outputTail = i3 + 1;
                bArr2[i3] = (byte) ((i & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                i = i4;
            }
        }
    }

    protected static final SmileBufferRecycler<SharedStringNode> _smileBufferRecycler() {
        SoftReference softReference = (SoftReference) _smileRecyclerRef.get();
        SmileBufferRecycler<SharedStringNode> smileBufferRecycler = softReference == null ? null : (SmileBufferRecycler) softReference.get();
        if (smileBufferRecycler != null) {
            return smileBufferRecycler;
        }
        smileBufferRecycler = new SmileBufferRecycler();
        _smileRecyclerRef.set(new SoftReference(smileBufferRecycler));
        return smileBufferRecycler;
    }

    private void _throwIllegalSurrogate(int i) {
        if (i > 1114111) {
            throw new IllegalArgumentException("Illegal character point (0x" + Integer.toHexString(i) + ") to output; max is 0x10FFFF as per RFC 4627");
        } else if (i < SURR1_FIRST) {
            throw new IllegalArgumentException("Illegal character point (0x" + Integer.toHexString(i) + ") to output");
        } else if (i <= SURR1_LAST) {
            throw new IllegalArgumentException("Unmatched first part of surrogate pair (0x" + Integer.toHexString(i) + ")");
        } else {
            throw new IllegalArgumentException("Unmatched second part of surrogate pair (0x" + Integer.toHexString(i) + ")");
        }
    }

    private final void _writeByte(byte b) {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b;
    }

    private final void _writeBytes(byte b, byte b2) {
        if (this._outputTail + 1 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b;
        bArr = this._outputBuffer;
        i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b2;
    }

    private final void _writeBytes(byte b, byte b2, byte b3) {
        if (this._outputTail + 2 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b;
        bArr = this._outputBuffer;
        i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b2;
        bArr = this._outputBuffer;
        i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b3;
    }

    private final void _writeBytes(byte b, byte b2, byte b3, byte b4) {
        if (this._outputTail + 3 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b;
        bArr = this._outputBuffer;
        i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b2;
        bArr = this._outputBuffer;
        i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b3;
        bArr = this._outputBuffer;
        i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b4;
    }

    private final void _writeBytes(byte b, byte b2, byte b3, byte b4, byte b5) {
        if (this._outputTail + 4 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b;
        bArr = this._outputBuffer;
        i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b2;
        bArr = this._outputBuffer;
        i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b3;
        bArr = this._outputBuffer;
        i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b4;
        bArr = this._outputBuffer;
        i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b5;
    }

    private final void _writeBytes(byte b, byte b2, byte b3, byte b4, byte b5, byte b6) {
        if (this._outputTail + 5 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b;
        bArr = this._outputBuffer;
        i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b2;
        bArr = this._outputBuffer;
        i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b3;
        bArr = this._outputBuffer;
        i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b4;
        bArr = this._outputBuffer;
        i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b5;
        bArr = this._outputBuffer;
        i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b6;
    }

    private final void _writeBytes(byte[] bArr, int i, int i2) {
        if (i2 != 0) {
            if (this._outputTail + i2 >= this._outputEnd) {
                _writeBytesLong(bArr, i, i2);
                return;
            }
            System.arraycopy(bArr, i, this._outputBuffer, this._outputTail, i2);
            this._outputTail += i2;
        }
    }

    private final void _writeBytesLong(byte[] bArr, int i, int i2) {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        while (true) {
            int min = Math.min(i2, this._outputEnd - this._outputTail);
            System.arraycopy(bArr, i, this._outputBuffer, this._outputTail, min);
            this._outputTail += min;
            i2 -= min;
            if (i2 != 0) {
                i += min;
                _flushBuffer();
            } else {
                return;
            }
        }
    }

    private final void _writeFieldName(String str) {
        byte b = SmileConstants.TOKEN_KEY_LONG_STRING;
        int length = str.length();
        if (length == 0) {
            _writeByte(SmileConstants.TOKEN_LITERAL_EMPTY_STRING);
            return;
        }
        int _findSeenName;
        if (this._seenNameCount >= 0) {
            _findSeenName = _findSeenName(str);
            if (_findSeenName >= 0) {
                _writeSharedNameReference(_findSeenName);
                return;
            }
        }
        if (length > 56) {
            _writeNonShortFieldName(str, length);
            return;
        }
        if (this._outputTail + SmileConstants.MIN_BUFFER_FOR_POSSIBLE_SHORT_STRING >= this._outputEnd) {
            _flushBuffer();
        }
        str.getChars(0, length, this._charBuffer, 0);
        _findSeenName = this._outputTail;
        this._outputTail++;
        int _shortUTF8Encode = _shortUTF8Encode(this._charBuffer, 0, length);
        byte[] bArr;
        if (_shortUTF8Encode == length) {
            if (_shortUTF8Encode <= 64) {
                b = (byte) (_shortUTF8Encode + Opcodes.LAND);
            } else {
                bArr = this._outputBuffer;
                _shortUTF8Encode = this._outputTail;
                this._outputTail = _shortUTF8Encode + 1;
                bArr[_shortUTF8Encode] = (byte) -4;
            }
        } else if (_shortUTF8Encode <= 56) {
            b = (byte) (_shortUTF8Encode + Opcodes.ARRAYLENGTH);
        } else {
            bArr = this._outputBuffer;
            _shortUTF8Encode = this._outputTail;
            this._outputTail = _shortUTF8Encode + 1;
            bArr[_shortUTF8Encode] = (byte) -4;
        }
        this._outputBuffer[_findSeenName] = b;
        if (this._seenNameCount >= 0) {
            _addSeenName(str);
        }
    }

    private final void _writeNonSharedString(String str, int i) {
        if (i > this._charBufferLength) {
            _writeByte(TOKEN_BYTE_LONG_STRING_UNICODE);
            _slowUTF8Encode(str);
            _writeByte((byte) -4);
            return;
        }
        str.getChars(0, i, this._charBuffer, 0);
        int i2 = ((i + i) + i) + 2;
        if (i2 > this._outputBuffer.length) {
            _writeByte(TOKEN_BYTE_LONG_STRING_UNICODE);
            _mediumUTF8Encode(this._charBuffer, 0, i);
            _writeByte((byte) -4);
            return;
        }
        if (i2 + this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        i2 = this._outputTail;
        _writeByte(TOKEN_BYTE_LONG_STRING_ASCII);
        if (_shortUTF8Encode(this._charBuffer, 0, i) > i) {
            this._outputBuffer[i2] = TOKEN_BYTE_LONG_STRING_UNICODE;
        }
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = (byte) -4;
    }

    private final void _writeNonShortFieldName(String str, int i) {
        _writeByte(SmileConstants.TOKEN_KEY_LONG_STRING);
        if (i > this._charBufferLength) {
            _slowUTF8Encode(str);
        } else {
            str.getChars(0, i, this._charBuffer, 0);
            int i2 = (i + i) + i;
            if (i2 <= this._outputBuffer.length) {
                if (i2 + this._outputTail >= this._outputEnd) {
                    _flushBuffer();
                }
                _shortUTF8Encode(this._charBuffer, 0, i);
            } else {
                _mediumUTF8Encode(this._charBuffer, 0, i);
            }
        }
        if (this._seenNameCount >= 0) {
            _addSeenName(str);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = (byte) -4;
    }

    private void _writePositiveVInt(int i) {
        _ensureRoomForOutput(5);
        byte b = (byte) ((i & 63) + SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        int i2 = i >> 6;
        if (i2 <= Opcodes.LAND) {
            if (i2 > 0) {
                byte[] bArr = this._outputBuffer;
                int i3 = this._outputTail;
                this._outputTail = i3 + 1;
                bArr[i3] = (byte) i2;
            }
            byte[] bArr2 = this._outputBuffer;
            int i4 = this._outputTail;
            this._outputTail = i4 + 1;
            bArr2[i4] = b;
            return;
        }
        byte b2 = (byte) (i2 & Opcodes.LAND);
        i2 >>= 7;
        if (i2 <= Opcodes.LAND) {
            byte[] bArr3 = this._outputBuffer;
            int i5 = this._outputTail;
            this._outputTail = i5 + 1;
            bArr3[i5] = (byte) i2;
            bArr2 = this._outputBuffer;
            i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr2[i3] = b2;
            bArr2 = this._outputBuffer;
            i4 = this._outputTail;
            this._outputTail = i4 + 1;
            bArr2[i4] = b;
            return;
        }
        byte b3 = (byte) (i2 & Opcodes.LAND);
        i2 >>= 7;
        if (i2 <= Opcodes.LAND) {
            byte[] bArr4 = this._outputBuffer;
            int i6 = this._outputTail;
            this._outputTail = i6 + 1;
            bArr4[i6] = (byte) i2;
            bArr2 = this._outputBuffer;
            i5 = this._outputTail;
            this._outputTail = i5 + 1;
            bArr2[i5] = b3;
            bArr2 = this._outputBuffer;
            i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr2[i3] = b2;
            bArr2 = this._outputBuffer;
            i4 = this._outputTail;
            this._outputTail = i4 + 1;
            bArr2[i4] = b;
            return;
        }
        byte b4 = (byte) (i2 & Opcodes.LAND);
        byte[] bArr5 = this._outputBuffer;
        int i7 = this._outputTail;
        this._outputTail = i7 + 1;
        bArr5[i7] = (byte) (i2 >> 7);
        bArr2 = this._outputBuffer;
        i6 = this._outputTail;
        this._outputTail = i6 + 1;
        bArr2[i6] = b4;
        bArr2 = this._outputBuffer;
        i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr2[i5] = b3;
        bArr2 = this._outputBuffer;
        i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr2[i3] = b2;
        bArr2 = this._outputBuffer;
        i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr2[i4] = b;
    }

    private final void _writeSharedNameReference(int i) {
        if (i >= this._seenNameCount) {
            throw new IllegalArgumentException("Internal error: trying to write shared name with index " + i + "; but have only seen " + this._seenNameCount + " so far!");
        } else if (i < 64) {
            _writeByte((byte) (i + 64));
        } else {
            _writeBytes((byte) ((i >> 8) + 48), (byte) i);
        }
    }

    private final void _writeSharedStringValueReference(int i) {
        if (i >= this._seenStringValueCount) {
            throw new IllegalArgumentException("Internal error: trying to write shared String value with index " + i + "; but have only seen " + this._seenStringValueCount + " so far!");
        } else if (i < 31) {
            _writeByte((byte) (i + 1));
        } else {
            _writeBytes((byte) ((i >> 8) + SmileConstants.TOKEN_MISC_SHARED_STRING_LONG), (byte) i);
        }
    }

    private void _writeSignedVInt(int i) {
        _writePositiveVInt(SmileUtil.zigzagEncode(i));
    }

    protected final void _flushBuffer() {
        if (this._outputTail > 0) {
            this._bytesWritten += this._outputTail;
            this._out.write(this._outputBuffer, 0, this._outputTail);
            this._outputTail = 0;
        }
    }

    protected UnsupportedOperationException _notSupported() {
        return new UnsupportedOperationException();
    }

    protected void _releaseBuffers() {
        byte[] bArr = this._outputBuffer;
        if (bArr != null && this._bufferRecyclable) {
            this._outputBuffer = null;
            this._ioContext.releaseWriteEncodingBuffer(bArr);
        }
        char[] cArr = this._charBuffer;
        if (cArr != null) {
            this._charBuffer = null;
            this._ioContext.releaseConcatBuffer(cArr);
        }
        SharedStringNode[] sharedStringNodeArr = this._seenNames;
        if (sharedStringNodeArr != null && sharedStringNodeArr.length == 64) {
            this._seenNames = null;
            this._smileBufferRecycler.releaseSeenNamesBuffer(sharedStringNodeArr);
        }
        sharedStringNodeArr = this._seenStringValues;
        if (sharedStringNodeArr != null && sharedStringNodeArr.length == 64) {
            this._seenStringValues = null;
            this._smileBufferRecycler.releaseSeenStringValuesBuffer(sharedStringNodeArr);
        }
    }

    protected final void _verifyValueWrite(String str) {
        if (this._writeContext.writeValue() == 5) {
            _reportError("Can not " + str + ", expecting field name");
        }
    }

    protected void _write7BitBinaryWithLength(byte[] bArr, int i, int i2) {
        _writePositiveVInt(i2);
        while (i2 >= 7) {
            if (this._outputTail + 8 >= this._outputEnd) {
                _flushBuffer();
            }
            int i3 = i + 1;
            byte b = bArr[i];
            byte[] bArr2 = this._outputBuffer;
            int i4 = this._outputTail;
            this._outputTail = i4 + 1;
            bArr2[i4] = (byte) ((b >> 1) & Opcodes.LAND);
            int i5 = i3 + 1;
            i3 = (bArr[i3] & Util.MASK_8BIT) | (b << 8);
            byte[] bArr3 = this._outputBuffer;
            i4 = this._outputTail;
            this._outputTail = i4 + 1;
            bArr3[i4] = (byte) ((i3 >> 2) & Opcodes.LAND);
            int i6 = i5 + 1;
            i3 = (i3 << 8) | (bArr[i5] & Util.MASK_8BIT);
            bArr2 = this._outputBuffer;
            i4 = this._outputTail;
            this._outputTail = i4 + 1;
            bArr2[i4] = (byte) ((i3 >> 3) & Opcodes.LAND);
            i5 = i6 + 1;
            i3 = (i3 << 8) | (bArr[i6] & Util.MASK_8BIT);
            bArr3 = this._outputBuffer;
            i4 = this._outputTail;
            this._outputTail = i4 + 1;
            bArr3[i4] = (byte) ((i3 >> 4) & Opcodes.LAND);
            i6 = i5 + 1;
            i3 = (i3 << 8) | (bArr[i5] & Util.MASK_8BIT);
            bArr2 = this._outputBuffer;
            i4 = this._outputTail;
            this._outputTail = i4 + 1;
            bArr2[i4] = (byte) ((i3 >> 5) & Opcodes.LAND);
            i5 = i6 + 1;
            i3 = (i3 << 8) | (bArr[i6] & Util.MASK_8BIT);
            bArr3 = this._outputBuffer;
            i4 = this._outputTail;
            this._outputTail = i4 + 1;
            bArr3[i4] = (byte) ((i3 >> 6) & Opcodes.LAND);
            i = i5 + 1;
            i3 = (i3 << 8) | (bArr[i5] & Util.MASK_8BIT);
            bArr3 = this._outputBuffer;
            i5 = this._outputTail;
            this._outputTail = i5 + 1;
            bArr3[i5] = (byte) ((i3 >> 7) & Opcodes.LAND);
            bArr3 = this._outputBuffer;
            i5 = this._outputTail;
            this._outputTail = i5 + 1;
            bArr3[i5] = (byte) (i3 & Opcodes.LAND);
            i2 -= 7;
        }
        if (i2 > 0) {
            if (this._outputTail + 7 >= this._outputEnd) {
                _flushBuffer();
            }
            i3 = i + 1;
            b = bArr[i];
            bArr2 = this._outputBuffer;
            i4 = this._outputTail;
            this._outputTail = i4 + 1;
            bArr2[i4] = (byte) ((b >> 1) & Opcodes.LAND);
            if (i2 > 1) {
                i5 = i3 + 1;
                i3 = (bArr[i3] & Util.MASK_8BIT) | ((b & 1) << 8);
                bArr3 = this._outputBuffer;
                i4 = this._outputTail;
                this._outputTail = i4 + 1;
                bArr3[i4] = (byte) ((i3 >> 2) & Opcodes.LAND);
                if (i2 > 2) {
                    i6 = i5 + 1;
                    i3 = ((i3 & 3) << 8) | (bArr[i5] & Util.MASK_8BIT);
                    bArr2 = this._outputBuffer;
                    i4 = this._outputTail;
                    this._outputTail = i4 + 1;
                    bArr2[i4] = (byte) ((i3 >> 3) & Opcodes.LAND);
                    if (i2 > 3) {
                        i5 = i6 + 1;
                        i3 = ((i3 & 7) << 8) | (bArr[i6] & Util.MASK_8BIT);
                        bArr3 = this._outputBuffer;
                        i4 = this._outputTail;
                        this._outputTail = i4 + 1;
                        bArr3[i4] = (byte) ((i3 >> 4) & Opcodes.LAND);
                        if (i2 > 4) {
                            i6 = i5 + 1;
                            i3 = ((i3 & 15) << 8) | (bArr[i5] & Util.MASK_8BIT);
                            bArr2 = this._outputBuffer;
                            i4 = this._outputTail;
                            this._outputTail = i4 + 1;
                            bArr2[i4] = (byte) ((i3 >> 5) & Opcodes.LAND);
                            if (i2 > 5) {
                                i5 = i6 + 1;
                                i3 = ((i3 & 31) << 8) | (bArr[i6] & Util.MASK_8BIT);
                                bArr3 = this._outputBuffer;
                                i5 = this._outputTail;
                                this._outputTail = i5 + 1;
                                bArr3[i5] = (byte) ((i3 >> 6) & Opcodes.LAND);
                                bArr3 = this._outputBuffer;
                                i5 = this._outputTail;
                                this._outputTail = i5 + 1;
                                bArr3[i5] = (byte) (i3 & 63);
                                return;
                            }
                            bArr3 = this._outputBuffer;
                            i5 = this._outputTail;
                            this._outputTail = i5 + 1;
                            bArr3[i5] = (byte) (i3 & 31);
                            return;
                        }
                        bArr3 = this._outputBuffer;
                        i5 = this._outputTail;
                        this._outputTail = i5 + 1;
                        bArr3[i5] = (byte) (i3 & 15);
                        return;
                    }
                    bArr3 = this._outputBuffer;
                    i5 = this._outputTail;
                    this._outputTail = i5 + 1;
                    bArr3[i5] = (byte) (i3 & 7);
                    return;
                }
                bArr3 = this._outputBuffer;
                i5 = this._outputTail;
                this._outputTail = i5 + 1;
                bArr3[i5] = (byte) (i3 & 3);
                return;
            }
            byte[] bArr4 = this._outputBuffer;
            i5 = this._outputTail;
            this._outputTail = i5 + 1;
            bArr4[i5] = (byte) (b & 1);
        }
    }

    protected final void _writeFieldName(SerializableString serializableString) {
        int charLength = serializableString.charLength();
        if (charLength == 0) {
            _writeByte(SmileConstants.TOKEN_LITERAL_EMPTY_STRING);
            return;
        }
        Object asUnquotedUTF8 = serializableString.asUnquotedUTF8();
        int length = asUnquotedUTF8.length;
        if (length != charLength) {
            _writeFieldNameUnicode(serializableString, asUnquotedUTF8);
            return;
        }
        if (this._seenNameCount >= 0) {
            charLength = _findSeenName(serializableString.getValue());
            if (charLength >= 0) {
                _writeSharedNameReference(charLength);
                return;
            }
        }
        if (length <= 64) {
            if (this._outputTail + length >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = (byte) (length + Opcodes.LAND);
            System.arraycopy(asUnquotedUTF8, 0, this._outputBuffer, this._outputTail, length);
            this._outputTail += length;
            if (this._seenNameCount >= 0) {
                _addSeenName(serializableString.getValue());
                return;
            }
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        bArr = this._outputBuffer;
        i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = SmileConstants.TOKEN_KEY_LONG_STRING;
        if ((this._outputTail + length) + 1 < this._outputEnd) {
            System.arraycopy(asUnquotedUTF8, 0, this._outputBuffer, this._outputTail, length);
            this._outputTail += length;
        } else {
            _flushBuffer();
            if (length < MIN_BUFFER_LENGTH) {
                System.arraycopy(asUnquotedUTF8, 0, this._outputBuffer, this._outputTail, length);
                this._outputTail += length;
            } else {
                if (this._outputTail > 0) {
                    _flushBuffer();
                }
                this._out.write(asUnquotedUTF8, 0, length);
            }
        }
        bArr = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr[i2] = (byte) -4;
        if (this._seenNameCount >= 0) {
            _addSeenName(serializableString.getValue());
        }
    }

    protected final void _writeFieldNameUnicode(SerializableString serializableString, byte[] bArr) {
        int _findSeenName;
        if (this._seenNameCount >= 0) {
            _findSeenName = _findSeenName(serializableString.getValue());
            if (_findSeenName >= 0) {
                _writeSharedNameReference(_findSeenName);
                return;
            }
        }
        _findSeenName = bArr.length;
        if (_findSeenName <= 56) {
            if (this._outputTail + _findSeenName >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr2[i] = (byte) (_findSeenName + Opcodes.ARRAYLENGTH);
            System.arraycopy(bArr, 0, this._outputBuffer, this._outputTail, _findSeenName);
            this._outputTail = _findSeenName + this._outputTail;
            if (this._seenNameCount >= 0) {
                _addSeenName(serializableString.getValue());
                return;
            }
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        bArr2 = this._outputBuffer;
        i = this._outputTail;
        this._outputTail = i + 1;
        bArr2[i] = SmileConstants.TOKEN_KEY_LONG_STRING;
        if ((this._outputTail + _findSeenName) + 1 < this._outputEnd) {
            System.arraycopy(bArr, 0, this._outputBuffer, this._outputTail, _findSeenName);
            this._outputTail = _findSeenName + this._outputTail;
        } else {
            _flushBuffer();
            if (_findSeenName < MIN_BUFFER_LENGTH) {
                System.arraycopy(bArr, 0, this._outputBuffer, this._outputTail, _findSeenName);
                this._outputTail = _findSeenName + this._outputTail;
            } else {
                if (this._outputTail > 0) {
                    _flushBuffer();
                }
                this._out.write(bArr, 0, _findSeenName);
            }
        }
        byte[] bArr3 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr3[i2] = (byte) -4;
        if (this._seenNameCount >= 0) {
            _addSeenName(serializableString.getValue());
        }
    }

    public void close() {
        boolean z = this._closed;
        super.close();
        if (this._outputBuffer != null && isEnabled(org.codehaus.jackson.JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT)) {
            while (true) {
                JsonStreamContext outputContext = getOutputContext();
                if (!outputContext.inArray()) {
                    if (!outputContext.inObject()) {
                        break;
                    }
                    writeEndObject();
                } else {
                    writeEndArray();
                }
            }
        }
        if (!z && isEnabled(Feature.WRITE_END_MARKER)) {
            _writeByte((byte) -1);
        }
        _flushBuffer();
        if (this._ioContext.isResourceManaged() || isEnabled(org.codehaus.jackson.JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
            this._out.close();
        } else {
            this._out.flush();
        }
        _releaseBuffers();
    }

    public SmileGenerator configure(Feature feature, boolean z) {
        if (z) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    public SmileGenerator disable(Feature feature) {
        this._smileFeatures &= feature.getMask() ^ -1;
        return this;
    }

    public SmileGenerator enable(Feature feature) {
        this._smileFeatures |= feature.getMask();
        return this;
    }

    public final void flush() {
        _flushBuffer();
        if (isEnabled(org.codehaus.jackson.JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
            this._out.flush();
        }
    }

    public Object getOutputTarget() {
        return this._out;
    }

    public final boolean isEnabled(Feature feature) {
        return (this._smileFeatures & feature.getMask()) != 0;
    }

    protected long outputOffset() {
        return (long) (this._bytesWritten + this._outputTail);
    }

    public JsonGenerator setPrettyPrinter(PrettyPrinter prettyPrinter) {
        return this;
    }

    public JsonGenerator useDefaultPrettyPrinter() {
        return this;
    }

    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) {
        if (bArr == null) {
            writeNull();
            return;
        }
        _verifyValueWrite("write Binary value");
        if (isEnabled(Feature.ENCODE_BINARY_AS_7BIT)) {
            _writeByte((byte) -24);
            _write7BitBinaryWithLength(bArr, i, i2);
            return;
        }
        _writeByte((byte) -3);
        _writePositiveVInt(i2);
        _writeBytes(bArr, i, i2);
    }

    public void writeBoolean(boolean z) {
        _verifyValueWrite("write boolean value");
        if (z) {
            _writeByte(SmileConstants.TOKEN_LITERAL_TRUE);
        } else {
            _writeByte(SmileConstants.TOKEN_LITERAL_FALSE);
        }
    }

    public void writeBytes(byte[] bArr, int i, int i2) {
        _writeBytes(bArr, i, i2);
    }

    public final void writeEndArray() {
        if (!this._writeContext.inArray()) {
            _reportError("Current context not an ARRAY but " + this._writeContext.getTypeDesc());
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeEndArray(this, this._writeContext.getEntryCount());
        } else {
            _writeByte((byte) -7);
        }
        this._writeContext = this._writeContext.getParent();
    }

    public final void writeEndObject() {
        if (!this._writeContext.inObject()) {
            _reportError("Current context not an object but " + this._writeContext.getTypeDesc());
        }
        this._writeContext = this._writeContext.getParent();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeEndObject(this, this._writeContext.getEntryCount());
        } else {
            _writeByte((byte) -5);
        }
    }

    public final void writeFieldName(String str) {
        if (this._writeContext.writeFieldName(str) == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        _writeFieldName(str);
    }

    public final void writeFieldName(SerializableString serializableString) {
        if (this._writeContext.writeFieldName(serializableString.getValue()) == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        _writeFieldName(serializableString);
    }

    public final void writeFieldName(SerializedString serializedString) {
        if (this._writeContext.writeFieldName(serializedString.getValue()) == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        _writeFieldName((SerializableString) serializedString);
    }

    public void writeHeader() {
        int i = 0;
        if ((this._smileFeatures & Feature.CHECK_SHARED_NAMES.getMask()) != 0) {
            i = 1;
        }
        if ((this._smileFeatures & Feature.CHECK_SHARED_STRING_VALUES.getMask()) != 0) {
            i |= 2;
        }
        if ((this._smileFeatures & Feature.ENCODE_BINARY_AS_7BIT.getMask()) == 0) {
            i |= 4;
        }
        _writeBytes(SmileConstants.HEADER_BYTE_1, TOKEN_BYTE_FLOAT_64, (byte) 10, (byte) i);
    }

    public void writeNull() {
        _verifyValueWrite("write null value");
        _writeByte(SmileConstants.TOKEN_LITERAL_NULL);
    }

    public void writeNumber(double d) {
        _ensureRoomForOutput(11);
        _verifyValueWrite("write number");
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = TOKEN_BYTE_FLOAT_64;
        int i2 = (int) (doubleToRawLongBits >>> 35);
        this._outputBuffer[this._outputTail + 4] = (byte) (i2 & Opcodes.LAND);
        i2 >>= 7;
        this._outputBuffer[this._outputTail + 3] = (byte) (i2 & Opcodes.LAND);
        i2 >>= 7;
        this._outputBuffer[this._outputTail + 2] = (byte) (i2 & Opcodes.LAND);
        i2 >>= 7;
        this._outputBuffer[this._outputTail + 1] = (byte) (i2 & Opcodes.LAND);
        this._outputBuffer[this._outputTail] = (byte) (i2 >> 7);
        this._outputTail += 5;
        i2 = (int) (doubleToRawLongBits >> 28);
        byte[] bArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr2[i3] = (byte) (i2 & Opcodes.LAND);
        int i4 = (int) doubleToRawLongBits;
        this._outputBuffer[this._outputTail + 3] = (byte) (i4 & Opcodes.LAND);
        i4 >>= 7;
        this._outputBuffer[this._outputTail + 2] = (byte) (i4 & Opcodes.LAND);
        i4 >>= 7;
        this._outputBuffer[this._outputTail + 1] = (byte) (i4 & Opcodes.LAND);
        this._outputBuffer[this._outputTail] = (byte) ((i4 >> 7) & Opcodes.LAND);
        this._outputTail += 4;
    }

    public void writeNumber(float f) {
        _ensureRoomForOutput(6);
        _verifyValueWrite("write number");
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = TOKEN_BYTE_FLOAT_32;
        this._outputBuffer[this._outputTail + 4] = (byte) (floatToRawIntBits & Opcodes.LAND);
        floatToRawIntBits >>= 7;
        this._outputBuffer[this._outputTail + 3] = (byte) (floatToRawIntBits & Opcodes.LAND);
        floatToRawIntBits >>= 7;
        this._outputBuffer[this._outputTail + 2] = (byte) (floatToRawIntBits & Opcodes.LAND);
        floatToRawIntBits >>= 7;
        this._outputBuffer[this._outputTail + 1] = (byte) (floatToRawIntBits & Opcodes.LAND);
        this._outputBuffer[this._outputTail] = (byte) ((floatToRawIntBits >> 7) & Opcodes.LAND);
        this._outputTail += 5;
    }

    public void writeNumber(int i) {
        _verifyValueWrite("write number");
        int zigzagEncode = SmileUtil.zigzagEncode(i);
        if (zigzagEncode > 63 || zigzagEncode < 0) {
            byte b = (byte) ((zigzagEncode & 63) + SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            zigzagEncode >>>= 6;
            if (zigzagEncode <= Opcodes.LAND) {
                _writeBytes((byte) TOKEN_BYTE_INT_32, (byte) zigzagEncode, b);
                return;
            }
            byte b2 = (byte) (zigzagEncode & Opcodes.LAND);
            zigzagEncode >>= 7;
            if (zigzagEncode <= Opcodes.LAND) {
                _writeBytes(TOKEN_BYTE_INT_32, (byte) zigzagEncode, b2, b);
                return;
            }
            byte b3 = (byte) (zigzagEncode & Opcodes.LAND);
            zigzagEncode >>= 7;
            if (zigzagEncode <= Opcodes.LAND) {
                _writeBytes(TOKEN_BYTE_INT_32, (byte) zigzagEncode, b3, b2, b);
                return;
            }
            byte b4 = TOKEN_BYTE_INT_32;
            _writeBytes(b4, (byte) (zigzagEncode >> 7), (byte) (zigzagEncode & Opcodes.LAND), b3, b2, b);
        } else if (zigzagEncode <= 31) {
            _writeByte((byte) (zigzagEncode + SmileConstants.TOKEN_PREFIX_SMALL_INT));
        } else {
            _writeBytes(TOKEN_BYTE_INT_32, (byte) (zigzagEncode + SmileConstants.TOKEN_PREFIX_TINY_UNICODE));
        }
    }

    public void writeNumber(long j) {
        if (j > MAX_INT_AS_LONG || j < MIN_INT_AS_LONG) {
            _verifyValueWrite("write number");
            long zigzagEncode = SmileUtil.zigzagEncode(j);
            int i = (int) zigzagEncode;
            byte b = (byte) ((i & 63) + SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            byte b2 = (byte) ((i >> 6) & Opcodes.LAND);
            byte b3 = (byte) ((i >> 13) & Opcodes.LAND);
            byte b4 = (byte) ((i >> 20) & Opcodes.LAND);
            zigzagEncode >>>= 27;
            byte b5 = (byte) (((int) zigzagEncode) & Opcodes.LAND);
            int i2 = (int) (zigzagEncode >> 7);
            if (i2 == 0) {
                _writeBytes(TOKEN_BYTE_INT_64, b5, b4, b3, b2, b);
                return;
            } else if (i2 <= Opcodes.LAND) {
                _writeBytes(TOKEN_BYTE_INT_64, (byte) i2);
                _writeBytes(b5, b4, b3, b2, b);
                return;
            } else {
                byte b6 = (byte) (i2 & Opcodes.LAND);
                i2 >>= 7;
                if (i2 <= Opcodes.LAND) {
                    _writeBytes(TOKEN_BYTE_INT_64, (byte) i2);
                    _writeBytes(b6, b5, b4, b3, b2, b);
                    return;
                }
                byte b7 = (byte) (i2 & Opcodes.LAND);
                i2 >>= 7;
                if (i2 <= Opcodes.LAND) {
                    _writeBytes((byte) TOKEN_BYTE_INT_64, (byte) i2, b7);
                    _writeBytes(b6, b5, b4, b3, b2, b);
                    return;
                }
                byte b8 = (byte) (i2 & Opcodes.LAND);
                i2 >>= 7;
                if (i2 <= Opcodes.LAND) {
                    _writeBytes(TOKEN_BYTE_INT_64, (byte) i2, b8, b7);
                    _writeBytes(b6, b5, b4, b3, b2, b);
                    return;
                }
                _writeBytes(TOKEN_BYTE_INT_64, (byte) (i2 >> 7), (byte) (i2 & Opcodes.LAND), b8, b7);
                _writeBytes(b6, b5, b4, b3, b2, b);
                return;
            }
        }
        writeNumber((int) j);
    }

    public void writeNumber(String str) {
        throw _notSupported();
    }

    public void writeNumber(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            writeNull();
            return;
        }
        _verifyValueWrite("write number");
        _writeByte(TOKEN_BYTE_BIG_DECIMAL);
        _writeSignedVInt(bigDecimal.scale());
        byte[] toByteArray = bigDecimal.unscaledValue().toByteArray();
        _write7BitBinaryWithLength(toByteArray, 0, toByteArray.length);
    }

    public void writeNumber(BigInteger bigInteger) {
        if (bigInteger == null) {
            writeNull();
            return;
        }
        _verifyValueWrite("write number");
        _writeByte(TOKEN_BYTE_BIG_INTEGER);
        byte[] toByteArray = bigInteger.toByteArray();
        _write7BitBinaryWithLength(toByteArray, 0, toByteArray.length);
    }

    public void writeRaw(byte b) {
        _writeByte((byte) -8);
    }

    public void writeRaw(char c) {
        throw _notSupported();
    }

    public void writeRaw(String str) {
        throw _notSupported();
    }

    public void writeRaw(String str, int i, int i2) {
        throw _notSupported();
    }

    public void writeRaw(char[] cArr, int i, int i2) {
        throw _notSupported();
    }

    public void writeRawUTF8String(byte[] bArr, int i, int i2) {
        _verifyValueWrite("write String value");
        if (i2 == 0) {
            _writeByte(SmileConstants.TOKEN_LITERAL_EMPTY_STRING);
        } else if (this._seenStringValueCount >= 0) {
            throw new UnsupportedOperationException("Can not use direct UTF-8 write methods when 'Feature.CHECK_SHARED_STRING_VALUES' enabled");
        } else if (i2 <= 65) {
            if (this._outputTail + i2 >= this._outputEnd) {
                _flushBuffer();
            }
            if (i2 == 1) {
                r0 = this._outputBuffer;
                r1 = this._outputTail;
                this._outputTail = r1 + 1;
                r0[r1] = (byte) 64;
                r0 = this._outputBuffer;
                r1 = this._outputTail;
                this._outputTail = r1 + 1;
                r0[r1] = bArr[i];
                return;
            }
            r0 = this._outputBuffer;
            r1 = this._outputTail;
            this._outputTail = r1 + 1;
            r0[r1] = (byte) (i2 + Opcodes.IAND);
            System.arraycopy(bArr, i, this._outputBuffer, this._outputTail, i2);
            this._outputTail += i2;
        } else {
            int i3 = ((i2 + i2) + i2) + 2;
            if (i3 <= this._outputBuffer.length) {
                if (i3 + this._outputTail >= this._outputEnd) {
                    _flushBuffer();
                }
                r0 = this._outputBuffer;
                r1 = this._outputTail;
                this._outputTail = r1 + 1;
                r0[r1] = TOKEN_BYTE_LONG_STRING_UNICODE;
                System.arraycopy(bArr, i, this._outputBuffer, this._outputTail, i2);
                this._outputTail += i2;
                r0 = this._outputBuffer;
                r1 = this._outputTail;
                this._outputTail = r1 + 1;
                r0[r1] = (byte) -4;
                return;
            }
            _writeByte(TOKEN_BYTE_LONG_STRING_UNICODE);
            _writeBytes(bArr, i, i2);
            _writeByte((byte) -4);
        }
    }

    public void writeRawValue(String str) {
        throw _notSupported();
    }

    public void writeRawValue(String str, int i, int i2) {
        throw _notSupported();
    }

    public void writeRawValue(char[] cArr, int i, int i2) {
        throw _notSupported();
    }

    public final void writeStartArray() {
        _verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartArray(this);
        } else {
            _writeByte((byte) -8);
        }
    }

    public final void writeStartObject() {
        _verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartObject(this);
        } else {
            _writeByte((byte) -6);
        }
    }

    public void writeString(String str) {
        if (str == null) {
            writeNull();
            return;
        }
        _verifyValueWrite("write String value");
        int length = str.length();
        if (length == 0) {
            _writeByte(SmileConstants.TOKEN_LITERAL_EMPTY_STRING);
        } else if (length > 65) {
            _writeNonSharedString(str, length);
        } else {
            int _findSeenStringValue;
            if (this._seenStringValueCount >= 0) {
                _findSeenStringValue = _findSeenStringValue(str);
                if (_findSeenStringValue >= 0) {
                    _writeSharedStringValueReference(_findSeenStringValue);
                    return;
                }
            }
            if (this._outputTail + SmileConstants.MIN_BUFFER_FOR_POSSIBLE_SHORT_STRING >= this._outputEnd) {
                _flushBuffer();
            }
            str.getChars(0, length, this._charBuffer, 0);
            _findSeenStringValue = this._outputTail;
            this._outputTail++;
            int _shortUTF8Encode = _shortUTF8Encode(this._charBuffer, 0, length);
            if (_shortUTF8Encode <= 64) {
                if (this._seenStringValueCount >= 0) {
                    _addSeenStringValue(str);
                }
                if (_shortUTF8Encode == length) {
                    this._outputBuffer[_findSeenStringValue] = (byte) (_shortUTF8Encode + 63);
                    return;
                } else {
                    this._outputBuffer[_findSeenStringValue] = (byte) (_shortUTF8Encode + Opcodes.IAND);
                    return;
                }
            }
            this._outputBuffer[_findSeenStringValue] = _shortUTF8Encode == length ? TOKEN_BYTE_LONG_STRING_ASCII : TOKEN_BYTE_LONG_STRING_UNICODE;
            byte[] bArr = this._outputBuffer;
            _findSeenStringValue = this._outputTail;
            this._outputTail = _findSeenStringValue + 1;
            bArr[_findSeenStringValue] = (byte) -4;
        }
    }

    public final void writeString(SerializableString serializableString) {
        _verifyValueWrite("write String value");
        String value = serializableString.getValue();
        int length = value.length();
        if (length == 0) {
            _writeByte(SmileConstants.TOKEN_LITERAL_EMPTY_STRING);
            return;
        }
        int _findSeenStringValue;
        if (length <= 65 && this._seenStringValueCount >= 0) {
            _findSeenStringValue = _findSeenStringValue(value);
            if (_findSeenStringValue >= 0) {
                _writeSharedStringValueReference(_findSeenStringValue);
                return;
            }
        }
        byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
        int length2 = asUnquotedUTF8.length;
        if (length2 <= 64) {
            if ((this._outputTail + length2) + 1 >= this._outputEnd) {
                _flushBuffer();
            }
            _findSeenStringValue = length2 == length ? length2 + 63 : length2 + Opcodes.IAND;
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = (byte) _findSeenStringValue;
            System.arraycopy(asUnquotedUTF8, 0, this._outputBuffer, this._outputTail, length2);
            this._outputTail += length2;
            if (this._seenStringValueCount >= 0) {
                _addSeenStringValue(serializableString.getValue());
                return;
            }
            return;
        }
        _writeByte(length2 == length ? TOKEN_BYTE_LONG_STRING_ASCII : TOKEN_BYTE_LONG_STRING_UNICODE);
        _writeBytes(asUnquotedUTF8, 0, asUnquotedUTF8.length);
        _writeByte((byte) -4);
    }

    public void writeString(char[] cArr, int i, int i2) {
        byte b = TOKEN_BYTE_LONG_STRING_UNICODE;
        if (i2 > 65 || this._seenStringValueCount < 0 || i2 <= 0) {
            _verifyValueWrite("write String value");
            if (i2 == 0) {
                _writeByte(SmileConstants.TOKEN_LITERAL_EMPTY_STRING);
                return;
            } else if (i2 <= 64) {
                if (this._outputTail + SmileConstants.MIN_BUFFER_FOR_POSSIBLE_SHORT_STRING >= this._outputEnd) {
                    _flushBuffer();
                }
                r1 = this._outputTail;
                this._outputTail++;
                int _shortUTF8Encode = _shortUTF8Encode(cArr, i, i + i2);
                if (_shortUTF8Encode <= 64) {
                    b = _shortUTF8Encode == i2 ? (byte) (_shortUTF8Encode + 63) : (byte) (_shortUTF8Encode + Opcodes.IAND);
                } else {
                    byte[] bArr = this._outputBuffer;
                    int i3 = this._outputTail;
                    this._outputTail = i3 + 1;
                    bArr[i3] = (byte) -4;
                }
                this._outputBuffer[r1] = b;
                return;
            } else {
                r1 = ((i2 + i2) + i2) + 2;
                if (r1 <= this._outputBuffer.length) {
                    if (r1 + this._outputTail >= this._outputEnd) {
                        _flushBuffer();
                    }
                    r1 = this._outputTail;
                    _writeByte(TOKEN_BYTE_LONG_STRING_UNICODE);
                    if (_shortUTF8Encode(cArr, i, i + i2) == i2) {
                        this._outputBuffer[r1] = TOKEN_BYTE_LONG_STRING_ASCII;
                    }
                    byte[] bArr2 = this._outputBuffer;
                    r1 = this._outputTail;
                    this._outputTail = r1 + 1;
                    bArr2[r1] = (byte) -4;
                    return;
                }
                _writeByte(TOKEN_BYTE_LONG_STRING_UNICODE);
                _mediumUTF8Encode(cArr, i, i + i2);
                _writeByte((byte) -4);
                return;
            }
        }
        writeString(new String(cArr, i, i2));
    }

    public final void writeStringField(String str, String str2) {
        if (this._writeContext.writeFieldName(str) == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        _writeFieldName(str);
        writeString(str2);
    }

    public final void writeUTF8String(byte[] bArr, int i, int i2) {
        writeRawUTF8String(bArr, i, i2);
    }
}
