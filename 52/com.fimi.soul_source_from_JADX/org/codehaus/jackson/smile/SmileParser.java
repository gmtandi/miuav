package org.codehaus.jackson.smile;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonParser.NumberType;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.impl.StreamBasedParserBase;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.sym.BytesToNameCanonicalizer;
import org.codehaus.jackson.sym.Name;
import org.p122a.p123a.C2915a;

public class SmileParser extends StreamBasedParserBase {
    private static final int[] NO_INTS;
    private static final String[] NO_STRINGS;
    protected static final ThreadLocal<SoftReference<SmileBufferRecycler<String>>> _smileRecyclerRef;
    protected boolean _got32BitFloat;
    protected boolean _mayContainRawBinary;
    protected ObjectCodec _objectCodec;
    protected int _quad1;
    protected int _quad2;
    protected int[] _quadBuffer;
    protected int _seenNameCount;
    protected String[] _seenNames;
    protected int _seenStringValueCount;
    protected String[] _seenStringValues;
    protected final SmileBufferRecycler<String> _smileBufferRecycler;
    protected final BytesToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;
    protected int _typeByte;

    /* renamed from: org.codehaus.jackson.smile.SmileParser.1 */
    /* synthetic */ class C35931 {
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$JsonToken;

        static {
            $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public enum Feature {
        REQUIRE_HEADER(true);
        
        final boolean _defaultState;
        final int _mask;

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

    static {
        NO_INTS = new int[0];
        NO_STRINGS = new String[0];
        _smileRecyclerRef = new ThreadLocal();
    }

    public SmileParser(IOContext iOContext, int i, int i2, ObjectCodec objectCodec, BytesToNameCanonicalizer bytesToNameCanonicalizer, InputStream inputStream, byte[] bArr, int i3, int i4, boolean z) {
        super(iOContext, i, inputStream, bArr, i3, i4, z);
        this._tokenIncomplete = false;
        this._quadBuffer = NO_INTS;
        this._seenNames = NO_STRINGS;
        this._seenNameCount = 0;
        this._seenStringValues = null;
        this._seenStringValueCount = -1;
        this._objectCodec = objectCodec;
        this._symbols = bytesToNameCanonicalizer;
        this._tokenInputRow = -1;
        this._tokenInputCol = -1;
        this._smileBufferRecycler = _smileBufferRecycler();
    }

    private final String _addDecodedToSymbols(int i, String str) {
        if (i < 5) {
            return this._symbols.addName(str, this._quad1, 0).getName();
        }
        if (i < 9) {
            return this._symbols.addName(str, this._quad1, this._quad2).getName();
        }
        return this._symbols.addName(str, this._quadBuffer, (i + 3) >> 2).getName();
    }

    private final void _addSeenStringValue() {
        _finishToken();
        if (this._seenStringValueCount < this._seenStringValues.length) {
            String[] strArr = this._seenStringValues;
            int i = this._seenStringValueCount;
            this._seenStringValueCount = i + 1;
            strArr[i] = this._textBuffer.contentsAsString();
            return;
        }
        _expandSeenStringValues();
    }

    private final void _decodeLongAscii() {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            int i2 = this._inputPtr;
            int i3 = this._inputEnd - i2;
            if (i >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            i3 = Math.min(i3, emptyAndGetCurrentSegment.length - i);
            int i4 = i;
            i = i2;
            while (true) {
                i2 = i + 1;
                byte b = this._inputBuffer[i];
                if (b == -4) {
                    this._inputPtr = i2;
                    this._textBuffer.setCurrentLength(i4);
                    return;
                }
                i = i4 + 1;
                emptyAndGetCurrentSegment[i4] = (char) b;
                i3--;
                if (i3 <= 0) {
                    break;
                }
                i4 = i;
                i = i2;
            }
            this._inputPtr = i2;
        }
    }

    private final void _decodeLongUnicode() {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = SmileConstants.sUtf8UnitLengths;
        byte[] bArr = this._inputBuffer;
        int i = 0;
        while (true) {
            int i2 = this._inputPtr;
            if (i2 >= this._inputEnd) {
                loadMoreGuaranteed();
                i2 = this._inputPtr;
            }
            if (i >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            int i3 = this._inputEnd;
            int length = (emptyAndGetCurrentSegment.length - i) + i2;
            if (length >= i3) {
                length = i3;
            }
            while (i2 < length) {
                i3 = i2 + 1;
                i2 = bArr[i2] & Util.MASK_8BIT;
                if (iArr[i2] != 0) {
                    this._inputPtr = i3;
                    if (i2 == SmileConstants.INT_MARKER_END_OF_STRING) {
                        this._textBuffer.setCurrentLength(i);
                        return;
                    }
                    switch (iArr[i2]) {
                        case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                            i2 = _decodeUtf8_2(i2);
                            break;
                        case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                            if (this._inputEnd - this._inputPtr < 2) {
                                i2 = _decodeUtf8_3(i2);
                                break;
                            } else {
                                i2 = _decodeUtf8_3fast(i2);
                                break;
                            }
                        case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                            length = _decodeUtf8_4(i2);
                            i2 = i + 1;
                            emptyAndGetCurrentSegment[i] = (char) (55296 | (length >> 10));
                            if (i2 >= emptyAndGetCurrentSegment.length) {
                                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                                i = 0;
                            } else {
                                i = i2;
                            }
                            i2 = 56320 | (length & 1023);
                            break;
                        default:
                            _reportInvalidChar(i2);
                            break;
                    }
                    if (i >= emptyAndGetCurrentSegment.length) {
                        emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                        length = 0;
                    } else {
                        length = i;
                    }
                    i = length + 1;
                    emptyAndGetCurrentSegment[length] = (char) i2;
                } else {
                    int i4 = i + 1;
                    emptyAndGetCurrentSegment[i] = (char) i2;
                    i2 = i3;
                    i = i4;
                }
            }
            this._inputPtr = i2;
        }
    }

    private final Name _decodeLongUnicodeName(int[] iArr, int i, int i2) {
        int i3;
        int i4 = i & 3;
        if (i4 < 4) {
            i3 = iArr[i2 - 1];
            iArr[i2 - 1] = i3 << ((4 - i4) << 3);
        } else {
            i3 = 0;
        }
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i5 = 0;
        int i6 = 0;
        while (i6 < i) {
            char[] cArr;
            int i7;
            int i8 = (iArr[i6 >> 2] >> ((3 - (i6 & 3)) << 3)) & Util.MASK_8BIT;
            i6++;
            if (i8 > Opcodes.LAND) {
                int i9;
                if ((i8 & SmileConstants.TOKEN_PREFIX_MISC_OTHER) == SmileConstants.TOKEN_PREFIX_SMALL_INT) {
                    i8 &= 31;
                    i9 = 1;
                } else if ((i8 & 240) == SmileConstants.TOKEN_PREFIX_MISC_OTHER) {
                    i8 &= 15;
                    i9 = 2;
                } else if ((i8 & 248) == 240) {
                    i8 &= 7;
                    i9 = 3;
                } else {
                    _reportInvalidInitial(i8);
                    i8 = 1;
                    i9 = 1;
                }
                if (i6 + i9 > i) {
                    _reportInvalidEOF(" in long field name");
                }
                int i10 = iArr[i6 >> 2] >> ((3 - (i6 & 3)) << 3);
                i6++;
                if ((i10 & SmileConstants.TOKEN_PREFIX_SMALL_INT) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
                    _reportInvalidOther(i10);
                }
                i8 = (i8 << 6) | (i10 & 63);
                if (i9 > 1) {
                    i10 = iArr[i6 >> 2] >> ((3 - (i6 & 3)) << 3);
                    i6++;
                    if ((i10 & SmileConstants.TOKEN_PREFIX_SMALL_INT) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
                        _reportInvalidOther(i10);
                    }
                    i8 = (i8 << 6) | (i10 & 63);
                    if (i9 > 2) {
                        i10 = iArr[i6 >> 2] >> ((3 - (i6 & 3)) << 3);
                        i6++;
                        if ((i10 & SmileConstants.TOKEN_PREFIX_SMALL_INT) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
                            _reportInvalidOther(i10 & Util.MASK_8BIT);
                        }
                        i8 = (i8 << 6) | (i10 & 63);
                    }
                }
                if (i9 > 2) {
                    i8 -= AccessibilityNodeInfoCompat.ACTION_CUT;
                    if (i5 >= emptyAndGetCurrentSegment.length) {
                        emptyAndGetCurrentSegment = this._textBuffer.expandCurrentSegment();
                    }
                    i9 = i5 + 1;
                    emptyAndGetCurrentSegment[i5] = (char) (55296 + (i8 >> 10));
                    int i11 = (i8 & 1023) | 56320;
                    i8 = i6;
                    i6 = i9;
                    cArr = emptyAndGetCurrentSegment;
                    i7 = i11;
                    if (i6 >= cArr.length) {
                        cArr = this._textBuffer.expandCurrentSegment();
                    }
                    i5 = i6 + 1;
                    cArr[i6] = (char) i7;
                    i6 = i8;
                    emptyAndGetCurrentSegment = cArr;
                }
            }
            cArr = emptyAndGetCurrentSegment;
            i7 = i8;
            i8 = i6;
            i6 = i5;
            if (i6 >= cArr.length) {
                cArr = this._textBuffer.expandCurrentSegment();
            }
            i5 = i6 + 1;
            cArr[i6] = (char) i7;
            i6 = i8;
            emptyAndGetCurrentSegment = cArr;
        }
        String str = new String(emptyAndGetCurrentSegment, 0, i5);
        if (i4 < 4) {
            iArr[i2 - 1] = i3;
        }
        return this._symbols.addName(str, iArr, i2);
    }

    private final String _decodeShortAsciiName(int i) {
        int i2;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = (i3 + i) - 3;
        int i5 = 0;
        while (i3 < i4) {
            i2 = i5 + 1;
            int i6 = i3 + 1;
            emptyAndGetCurrentSegment[i5] = (char) bArr[i3];
            i3 = i2 + 1;
            i5 = i6 + 1;
            emptyAndGetCurrentSegment[i2] = (char) bArr[i6];
            i6 = i3 + 1;
            int i7 = i5 + 1;
            emptyAndGetCurrentSegment[i3] = (char) bArr[i5];
            i2 = i6 + 1;
            i3 = i7 + 1;
            emptyAndGetCurrentSegment[i6] = (char) bArr[i7];
            i5 = i2;
        }
        i4 = i & 3;
        if (i4 > 0) {
            i6 = i5 + 1;
            i2 = i3 + 1;
            emptyAndGetCurrentSegment[i5] = (char) bArr[i3];
            if (i4 > 1) {
                i5 = i6 + 1;
                i3 = i2 + 1;
                emptyAndGetCurrentSegment[i6] = (char) bArr[i2];
                if (i4 > 2) {
                    i2 = i5 + 1;
                    i2 = i3 + 1;
                    emptyAndGetCurrentSegment[i5] = (char) bArr[i3];
                }
            }
            this._inputPtr = i2;
            this._textBuffer.setCurrentLength(i);
            return this._textBuffer.contentsAsString();
        }
        i2 = i3;
        this._inputPtr = i2;
        this._textBuffer.setCurrentLength(i);
        return this._textBuffer.contentsAsString();
    }

    private final String _decodeShortUnicodeName(int i) {
        int i2 = 0;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i3 = this._inputPtr;
        this._inputPtr += i;
        int[] iArr = SmileConstants.sUtf8UnitLengths;
        byte[] bArr = this._inputBuffer;
        int i4 = i3 + i;
        while (i3 < i4) {
            int i5 = i3 + 1;
            int i6 = bArr[i3] & Util.MASK_8BIT;
            i3 = iArr[i6];
            if (i3 != 0) {
                switch (i3) {
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        i3 = i5 + 1;
                        i6 = ((i6 & 31) << 6) | (bArr[i5] & 63);
                        i5 = i2;
                        continue;
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                        int i7 = i5 + 1;
                        i3 = i7 + 1;
                        i6 = (((i6 & 15) << 12) | ((bArr[i5] & 63) << 6)) | (bArr[i7] & 63);
                        i5 = i2;
                        continue;
                    case Type.BYTE /*3*/:
                        i3 = i5 + 1;
                        i6 = ((i6 & 7) << 18) | ((bArr[i5] & 63) << 12);
                        i5 = i3 + 1;
                        i6 |= (bArr[i3] & 63) << 6;
                        i3 = i5 + 1;
                        i6 = (i6 | (bArr[i5] & 63)) - AccessibilityNodeInfoCompat.ACTION_CUT;
                        i5 = i2 + 1;
                        emptyAndGetCurrentSegment[i2] = (char) (55296 | (i6 >> 10));
                        i6 = (i6 & 1023) | 56320;
                        continue;
                    default:
                        _reportError("Invalid byte " + Integer.toHexString(i6) + " in short Unicode text block");
                        break;
                }
            }
            i3 = i5;
            i5 = i2;
            i2 = i5 + 1;
            emptyAndGetCurrentSegment[i5] = (char) i6;
        }
        this._textBuffer.setCurrentLength(i2);
        return this._textBuffer.contentsAsString();
    }

    private final int _decodeUtf8_2(int i) {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & SmileConstants.TOKEN_PREFIX_SMALL_INT) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
            _reportInvalidOther(b & Util.MASK_8BIT, this._inputPtr);
        }
        return (b & 63) | ((i & 31) << 6);
    }

    private final int _decodeUtf8_3(int i) {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b = bArr[i3];
        if ((b & SmileConstants.TOKEN_PREFIX_SMALL_INT) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
            _reportInvalidOther(b & Util.MASK_8BIT, this._inputPtr);
        }
        i2 = (i2 << 6) | (b & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        bArr = this._inputBuffer;
        i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        b = bArr[i3];
        if ((b & SmileConstants.TOKEN_PREFIX_SMALL_INT) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
            _reportInvalidOther(b & Util.MASK_8BIT, this._inputPtr);
        }
        return (i2 << 6) | (b & 63);
    }

    private final int _decodeUtf8_3fast(int i) {
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b = bArr[i3];
        if ((b & SmileConstants.TOKEN_PREFIX_SMALL_INT) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
            _reportInvalidOther(b & Util.MASK_8BIT, this._inputPtr);
        }
        i2 = (i2 << 6) | (b & 63);
        bArr = this._inputBuffer;
        i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        b = bArr[i3];
        if ((b & SmileConstants.TOKEN_PREFIX_SMALL_INT) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
            _reportInvalidOther(b & Util.MASK_8BIT, this._inputPtr);
        }
        return (i2 << 6) | (b & 63);
    }

    private final int _decodeUtf8_4(int i) {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & SmileConstants.TOKEN_PREFIX_SMALL_INT) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
            _reportInvalidOther(b & Util.MASK_8BIT, this._inputPtr);
        }
        int i3 = (b & 63) | ((i & 7) << 6);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        byte b2 = bArr2[i4];
        if ((b2 & SmileConstants.TOKEN_PREFIX_SMALL_INT) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
            _reportInvalidOther(b2 & Util.MASK_8BIT, this._inputPtr);
        }
        i3 = (i3 << 6) | (b2 & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        bArr2 = this._inputBuffer;
        i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        b2 = bArr2[i4];
        if ((b2 & SmileConstants.TOKEN_PREFIX_SMALL_INT) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
            _reportInvalidOther(b2 & Util.MASK_8BIT, this._inputPtr);
        }
        return ((i3 << 6) | (b2 & 63)) - AccessibilityNodeInfoCompat.ACTION_CUT;
    }

    private final String[] _expandSeenNames(String[] strArr) {
        int i = SmileConstants.MAX_SHARED_STRING_VALUES;
        int length = strArr.length;
        if (length == 0) {
            String[] strArr2 = (String[]) this._smileBufferRecycler.allocSeenNamesBuffer();
            return strArr2 == null ? new String[64] : strArr2;
        } else if (length == SmileConstants.MAX_SHARED_STRING_VALUES) {
            this._seenNameCount = 0;
            return strArr;
        } else {
            if (length == 64) {
                i = Opcodes.ACC_NATIVE;
            }
            Object obj = new String[i];
            System.arraycopy(strArr, 0, obj, 0, strArr.length);
            return obj;
        }
    }

    private final void _expandSeenStringValues() {
        String[] strArr;
        int i = SmileConstants.MAX_SHARED_STRING_VALUES;
        Object obj = this._seenStringValues;
        int length = obj.length;
        if (length == 0) {
            strArr = (String[]) this._smileBufferRecycler.allocSeenStringValuesBuffer();
            if (strArr == null) {
                strArr = new String[64];
            }
        } else if (length == SmileConstants.MAX_SHARED_STRING_VALUES) {
            this._seenStringValueCount = 0;
            Object obj2 = obj;
        } else {
            if (length == 64) {
                i = Opcodes.ACC_NATIVE;
            }
            strArr = new String[i];
            System.arraycopy(obj, 0, strArr, 0, obj.length);
        }
        this._seenStringValues = strArr;
        strArr = this._seenStringValues;
        int i2 = this._seenStringValueCount;
        this._seenStringValueCount = i2 + 1;
        strArr[i2] = this._textBuffer.contentsAsString();
    }

    private final Name _findDecodedFromSymbols(int i) {
        if (this._inputEnd - this._inputPtr < i) {
            _loadToHaveAtLeast(i);
        }
        int i2;
        int i3;
        if (i < 5) {
            int i4 = this._inputPtr;
            byte[] bArr = this._inputBuffer;
            i2 = bArr[i4] & Util.MASK_8BIT;
            i3 = i - 1;
            if (i3 > 0) {
                i4++;
                i2 = (i2 << 8) + (bArr[i4] & Util.MASK_8BIT);
                i3--;
                if (i3 > 0) {
                    i4++;
                    i2 = (i2 << 8) + (bArr[i4] & Util.MASK_8BIT);
                    if (i3 - 1 > 0) {
                        i2 = (i2 << 8) + (bArr[i4 + 1] & Util.MASK_8BIT);
                    }
                }
            }
            this._quad1 = i2;
            return this._symbols.findName(i2);
        } else if (i >= 9) {
            return _findDecodedMedium(i);
        } else {
            i2 = this._inputPtr;
            byte[] bArr2 = this._inputBuffer;
            i2++;
            i2++;
            i2++;
            int i5 = ((((((bArr2[i2] & Util.MASK_8BIT) << 8) + (bArr2[i2] & Util.MASK_8BIT)) << 8) + (bArr2[i2] & Util.MASK_8BIT)) << 8) + (bArr2[i2] & Util.MASK_8BIT);
            i3 = i2 + 1;
            i2 = bArr2[i3] & Util.MASK_8BIT;
            int i6 = i - 5;
            if (i6 > 0) {
                i3++;
                i2 = (i2 << 8) + (bArr2[i3] & Util.MASK_8BIT);
                i6--;
                if (i6 > 0) {
                    i3++;
                    i2 = (i2 << 8) + (bArr2[i3] & Util.MASK_8BIT);
                    if (i6 - 1 > 0) {
                        i2 = (i2 << 8) + (bArr2[i3 + 1] & Util.MASK_8BIT);
                    }
                }
            }
            this._quad1 = i5;
            this._quad2 = i2;
            return this._symbols.findName(i5, i2);
        }
    }

    private final Name _findDecodedMedium(int i) {
        int i2;
        int i3 = (i + 3) >> 2;
        if (i3 > this._quadBuffer.length) {
            this._quadBuffer = _growArrayTo(this._quadBuffer, i3);
        }
        int i4 = 0;
        i3 = this._inputPtr;
        byte[] bArr = this._inputBuffer;
        while (true) {
            i2 = i3 + 1;
            int i5 = i2 + 1;
            i2 = i5 + 1;
            i5 = (((((bArr[i3] & Util.MASK_8BIT) << 8) | (bArr[i2] & Util.MASK_8BIT)) << 8) | (bArr[i5] & Util.MASK_8BIT)) << 8;
            i3 = i2 + 1;
            i5 |= bArr[i2] & Util.MASK_8BIT;
            i2 = i4 + 1;
            this._quadBuffer[i4] = i5;
            i -= 4;
            if (i <= 3) {
                break;
            }
            i4 = i2;
        }
        if (i > 0) {
            i4 = bArr[i3] & Util.MASK_8BIT;
            i5 = i - 1;
            if (i5 > 0) {
                int i6 = i3 + 1;
                i3 = (bArr[i6] & Util.MASK_8BIT) + (i4 << 8);
                if (i5 - 1 > 0) {
                    i3 = (i3 << 8) + (bArr[i6 + 1] & Util.MASK_8BIT);
                }
            } else {
                i3 = i4;
            }
            i4 = i2 + 1;
            this._quadBuffer[i2] = i3;
            i2 = i4;
        }
        return this._symbols.findName(this._quadBuffer, i2);
    }

    private final void _finishBigDecimal() {
        this._numberBigDecimal = new BigDecimal(new BigInteger(_read7BitBinaryWithLength()), SmileUtil.zigzagDecode(_readUnsignedVInt()));
        this._numTypesValid = 16;
    }

    private final void _finishBigInteger() {
        this._numberBigInt = new BigInteger(_read7BitBinaryWithLength());
        this._numTypesValid = 4;
    }

    private final void _finishDouble() {
        long _fourBytesToInt = (((long) _fourBytesToInt()) << 28) + ((long) _fourBytesToInt());
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        _fourBytesToInt <<= 7;
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        _fourBytesToInt += (long) bArr[i];
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        _fourBytesToInt <<= 7;
        bArr = this._inputBuffer;
        i = this._inputPtr;
        this._inputPtr = i + 1;
        this._numberDouble = Double.longBitsToDouble(_fourBytesToInt + ((long) bArr[i]));
        this._numTypesValid = 8;
    }

    private final void _finishFloat() {
        int _fourBytesToInt = _fourBytesToInt();
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        _fourBytesToInt <<= 7;
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        this._numberDouble = (double) Float.intBitsToFloat(_fourBytesToInt + bArr[i]);
        this._numTypesValid = 8;
    }

    private final void _finishInt() {
        int i;
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        i2 = bArr[i2];
        if (i2 < 0) {
            i = i2 & 63;
        } else {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            i = bArr[i3];
            if (i >= 0) {
                i2 = (i2 << 7) + i;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                bArr = this._inputBuffer;
                i3 = this._inputPtr;
                this._inputPtr = i3 + 1;
                i = bArr[i3];
                if (i >= 0) {
                    i2 = (i2 << 7) + i;
                    if (this._inputPtr >= this._inputEnd) {
                        loadMoreGuaranteed();
                    }
                    bArr = this._inputBuffer;
                    i3 = this._inputPtr;
                    this._inputPtr = i3 + 1;
                    i = bArr[i3];
                    if (i >= 0) {
                        i2 = (i2 << 7) + i;
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        bArr = this._inputBuffer;
                        i3 = this._inputPtr;
                        this._inputPtr = i3 + 1;
                        i = bArr[i3];
                        if (i >= 0) {
                            _reportError("Corrupt input; 32-bit VInt extends beyond 5 data bytes");
                        }
                    }
                }
            }
            i = (i & 63) + (i2 << 6);
        }
        this._numberInt = SmileUtil.zigzagDecode(i);
        this._numTypesValid = 1;
    }

    private final void _finishLong() {
        long _fourBytesToInt = (long) _fourBytesToInt();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            byte b = bArr[i];
            if (b < null) {
                this._numberLong = SmileUtil.zigzagDecode((_fourBytesToInt << 6) + ((long) (b & 63)));
                this._numTypesValid = 2;
                return;
            }
            _fourBytesToInt = (_fourBytesToInt << 7) + ((long) b);
        }
    }

    private final void _finishRawBinary() {
        int _readUnsignedVInt = _readUnsignedVInt();
        this._binaryValue = new byte[_readUnsignedVInt];
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        int i = 0;
        while (true) {
            int min = Math.min(_readUnsignedVInt, this._inputEnd - this._inputPtr);
            System.arraycopy(this._inputBuffer, this._inputPtr, this._binaryValue, i, min);
            this._inputPtr += min;
            i += min;
            _readUnsignedVInt -= min;
            if (_readUnsignedVInt > 0) {
                loadMoreGuaranteed();
            } else {
                return;
            }
        }
    }

    private final int _fourBytesToInt() {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b = bArr[i];
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        int i2 = b << 7;
        byte[] bArr2 = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        i2 += bArr2[i3];
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        i2 <<= 7;
        bArr2 = this._inputBuffer;
        i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        i2 += bArr2[i3];
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        i2 <<= 7;
        bArr2 = this._inputBuffer;
        i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        return i2 + bArr2[i3];
    }

    private static int[] _growArrayTo(int[] iArr, int i) {
        Object obj = new int[(i + 4)];
        if (iArr != null) {
            System.arraycopy(iArr, 0, obj, 0, iArr.length);
        }
        return obj;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void _handleLongFieldName() {
        /*
        r7 = this;
        r1 = 0;
        r6 = -4;
        r4 = r7._inputBuffer;
        r0 = r1;
        r2 = r1;
    L_0x0006:
        r3 = r7._inputPtr;
        r5 = r3 + 1;
        r7._inputPtr = r5;
        r3 = r4[r3];
        if (r6 != r3) goto L_0x0061;
    L_0x0010:
        r3 = r0;
        r4 = r1;
    L_0x0012:
        r0 = r2 << 2;
        if (r4 <= 0) goto L_0x0030;
    L_0x0016:
        r1 = r7._quadBuffer;
        r1 = r1.length;
        if (r2 < r1) goto L_0x0028;
    L_0x001b:
        r1 = r7._quadBuffer;
        r5 = r7._quadBuffer;
        r5 = r5.length;
        r5 = r5 + 256;
        r1 = _growArrayTo(r1, r5);
        r7._quadBuffer = r1;
    L_0x0028:
        r5 = r7._quadBuffer;
        r1 = r2 + 1;
        r5[r2] = r3;
        r0 = r0 + r4;
        r2 = r1;
    L_0x0030:
        r1 = r7._symbols;
        r3 = r7._quadBuffer;
        r1 = r1.findName(r3, r2);
        if (r1 == 0) goto L_0x00b8;
    L_0x003a:
        r0 = r1.getName();
    L_0x003e:
        r1 = r7._seenNames;
        if (r1 == 0) goto L_0x005b;
    L_0x0042:
        r1 = r7._seenNameCount;
        r2 = r7._seenNames;
        r2 = r2.length;
        if (r1 < r2) goto L_0x0051;
    L_0x0049:
        r1 = r7._seenNames;
        r1 = r7._expandSeenNames(r1);
        r7._seenNames = r1;
    L_0x0051:
        r1 = r7._seenNames;
        r2 = r7._seenNameCount;
        r3 = r2 + 1;
        r7._seenNameCount = r3;
        r1[r2] = r0;
    L_0x005b:
        r1 = r7._parsingContext;
        r1.setCurrentName(r0);
        return;
    L_0x0061:
        r0 = r3 & 255;
        r3 = r7._inputPtr;
        r5 = r3 + 1;
        r7._inputPtr = r5;
        r3 = r4[r3];
        if (r6 != r3) goto L_0x0071;
    L_0x006d:
        r1 = 1;
        r3 = r0;
        r4 = r1;
        goto L_0x0012;
    L_0x0071:
        r0 = r0 << 8;
        r3 = r3 & 255;
        r0 = r0 | r3;
        r3 = r7._inputPtr;
        r5 = r3 + 1;
        r7._inputPtr = r5;
        r3 = r4[r3];
        if (r6 != r3) goto L_0x0084;
    L_0x0080:
        r1 = 2;
        r3 = r0;
        r4 = r1;
        goto L_0x0012;
    L_0x0084:
        r0 = r0 << 8;
        r3 = r3 & 255;
        r0 = r0 | r3;
        r3 = r7._inputPtr;
        r5 = r3 + 1;
        r7._inputPtr = r5;
        r3 = r4[r3];
        if (r6 != r3) goto L_0x0098;
    L_0x0093:
        r1 = 3;
        r3 = r0;
        r4 = r1;
        goto L_0x0012;
    L_0x0098:
        r0 = r0 << 8;
        r3 = r3 & 255;
        r0 = r0 | r3;
        r3 = r7._quadBuffer;
        r3 = r3.length;
        if (r2 < r3) goto L_0x00af;
    L_0x00a2:
        r3 = r7._quadBuffer;
        r5 = r7._quadBuffer;
        r5 = r5.length;
        r5 = r5 + 256;
        r3 = _growArrayTo(r3, r5);
        r7._quadBuffer = r3;
    L_0x00af:
        r5 = r7._quadBuffer;
        r3 = r2 + 1;
        r5[r2] = r0;
        r2 = r3;
        goto L_0x0006;
    L_0x00b8:
        r1 = r7._quadBuffer;
        r0 = r7._decodeLongUnicodeName(r1, r0, r2);
        r0 = r0.getName();
        goto L_0x003e;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.smile.SmileParser._handleLongFieldName():void");
    }

    private final JsonToken _handleSharedString(int i) {
        if (i >= this._seenStringValueCount) {
            _reportInvalidSharedStringValue(i);
        }
        this._textBuffer.resetWithString(this._seenStringValues[i]);
        JsonToken jsonToken = JsonToken.VALUE_STRING;
        this._currToken = jsonToken;
        return jsonToken;
    }

    private final byte[] _read7BitBinaryWithLength() {
        int i;
        int _readUnsignedVInt = _readUnsignedVInt();
        byte[] bArr = new byte[_readUnsignedVInt];
        int i2 = 0;
        _readUnsignedVInt -= 7;
        while (i2 <= _readUnsignedVInt) {
            if (this._inputEnd - this._inputPtr < 8) {
                _loadToHaveAtLeast(8);
            }
            byte[] bArr2 = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            int i4 = bArr2[i3] << 25;
            byte[] bArr3 = this._inputBuffer;
            i = this._inputPtr;
            this._inputPtr = i + 1;
            i4 += bArr3[i] << 18;
            bArr3 = this._inputBuffer;
            i = this._inputPtr;
            this._inputPtr = i + 1;
            i4 += bArr3[i] << 11;
            bArr3 = this._inputBuffer;
            i = this._inputPtr;
            this._inputPtr = i + 1;
            i4 += bArr3[i] << 4;
            bArr3 = this._inputBuffer;
            i = this._inputPtr;
            this._inputPtr = i + 1;
            byte b = bArr3[i];
            i4 += b >> 3;
            i3 = (b & 7) << 21;
            byte[] bArr4 = this._inputBuffer;
            int i5 = this._inputPtr;
            this._inputPtr = i5 + 1;
            i3 += bArr4[i5] << 14;
            bArr4 = this._inputBuffer;
            i5 = this._inputPtr;
            this._inputPtr = i5 + 1;
            i3 += bArr4[i5] << 7;
            bArr4 = this._inputBuffer;
            i5 = this._inputPtr;
            this._inputPtr = i5 + 1;
            i3 += bArr4[i5];
            i = i2 + 1;
            bArr[i2] = (byte) (i4 >> 24);
            i2 = i + 1;
            bArr[i] = (byte) (i4 >> 16);
            i = i2 + 1;
            bArr[i2] = (byte) (i4 >> 8);
            i2 = i + 1;
            bArr[i] = (byte) i4;
            i4 = i2 + 1;
            bArr[i2] = (byte) (i3 >> 16);
            i = i4 + 1;
            bArr[i4] = (byte) (i3 >> 8);
            i2 = i + 1;
            bArr[i] = (byte) i3;
        }
        i = bArr.length - i2;
        if (i > 0) {
            if (this._inputEnd - this._inputPtr < i + 1) {
                _loadToHaveAtLeast(i + 1);
            }
            byte[] bArr5 = this._inputBuffer;
            i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            i4 = bArr5[i4];
            _readUnsignedVInt = 1;
            while (_readUnsignedVInt < i) {
                i4 <<= 7;
                bArr3 = this._inputBuffer;
                i5 = this._inputPtr;
                this._inputPtr = i5 + 1;
                i4 += bArr3[i5];
                i3 = i2 + 1;
                bArr[i2] = (byte) (i4 >> (7 - _readUnsignedVInt));
                _readUnsignedVInt++;
                i2 = i3;
            }
            _readUnsignedVInt = i4 << i;
            bArr2 = this._inputBuffer;
            i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            bArr[i2] = (byte) (_readUnsignedVInt + bArr2[i3]);
        }
        return bArr;
    }

    private final int _readUnsignedVInt() {
        int i = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            byte b = bArr[i2];
            if (b < null) {
                return (i << 6) + (b & 63);
            }
            i = (i << 7) + b;
        }
    }

    protected static final SmileBufferRecycler<String> _smileBufferRecycler() {
        SoftReference softReference = (SoftReference) _smileRecyclerRef.get();
        SmileBufferRecycler<String> smileBufferRecycler = softReference == null ? null : (SmileBufferRecycler) softReference.get();
        if (smileBufferRecycler != null) {
            return smileBufferRecycler;
        }
        smileBufferRecycler = new SmileBufferRecycler();
        _smileRecyclerRef.set(new SoftReference(smileBufferRecycler));
        return smileBufferRecycler;
    }

    protected byte[] _decodeBase64(Base64Variant base64Variant) {
        _throwInternal();
        return null;
    }

    protected final void _decodeShortAsciiValue(int i) {
        if (this._inputEnd - this._inputPtr < i) {
            _loadToHaveAtLeast(i);
        }
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i2 = 0;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = i3 + i;
        while (i3 < i4) {
            int i5 = i2 + 1;
            emptyAndGetCurrentSegment[i2] = (char) bArr[i3];
            i3++;
            i2 = i5;
        }
        this._inputPtr = i3;
        this._textBuffer.setCurrentLength(i);
    }

    protected final void _decodeShortUnicodeValue(int i) {
        if (this._inputEnd - this._inputPtr < i) {
            _loadToHaveAtLeast(i);
        }
        int i2 = 0;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i3 = this._inputPtr;
        this._inputPtr += i;
        int[] iArr = SmileConstants.sUtf8UnitLengths;
        byte[] bArr = this._inputBuffer;
        int i4 = i3 + i;
        while (i3 < i4) {
            int i5 = i3 + 1;
            int i6 = bArr[i3] & Util.MASK_8BIT;
            i3 = iArr[i6];
            if (i3 != 0) {
                switch (i3) {
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        i3 = i5 + 1;
                        i6 = ((i6 & 31) << 6) | (bArr[i5] & 63);
                        i5 = i2;
                        continue;
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                        int i7 = i5 + 1;
                        i3 = i7 + 1;
                        i6 = (((i6 & 15) << 12) | ((bArr[i5] & 63) << 6)) | (bArr[i7] & 63);
                        i5 = i2;
                        continue;
                    case Type.BYTE /*3*/:
                        i3 = i5 + 1;
                        i6 = ((i6 & 7) << 18) | ((bArr[i5] & 63) << 12);
                        i5 = i3 + 1;
                        i6 |= (bArr[i3] & 63) << 6;
                        i3 = i5 + 1;
                        i6 = (i6 | (bArr[i5] & 63)) - AccessibilityNodeInfoCompat.ACTION_CUT;
                        i5 = i2 + 1;
                        emptyAndGetCurrentSegment[i2] = (char) (55296 | (i6 >> 10));
                        i6 = (i6 & 1023) | 56320;
                        continue;
                    default:
                        _reportError("Invalid byte " + Integer.toHexString(i6) + " in short Unicode text block");
                        break;
                }
            }
            i3 = i5;
            i5 = i2;
            i2 = i5 + 1;
            emptyAndGetCurrentSegment[i5] = (char) i6;
        }
        this._textBuffer.setCurrentLength(i2);
    }

    protected final void _finishNumberToken(int i) {
        int i2 = i & 31;
        int i3 = i2 >> 2;
        if (i3 == 1) {
            i2 &= 3;
            if (i2 == 0) {
                _finishInt();
                return;
            } else if (i2 == 1) {
                _finishLong();
                return;
            } else if (i2 == 2) {
                _finishBigInteger();
                return;
            } else {
                _throwInternal();
                return;
            }
        }
        if (i3 == 2) {
            switch (i2 & 3) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    _finishFloat();
                    return;
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    _finishDouble();
                    return;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    _finishBigDecimal();
                    return;
            }
        }
        _throwInternal();
    }

    protected void _finishString() {
        _throwInternal();
    }

    protected void _finishToken() {
        this._tokenIncomplete = false;
        int i = this._typeByte;
        int i2 = (i >> 5) & 7;
        if (i2 == 1) {
            _finishNumberToken(i);
        } else if (i2 <= 3) {
            _decodeShortAsciiValue((i & 63) + 1);
        } else if (i2 <= 5) {
            _decodeShortUnicodeValue((i & 63) + 2);
        } else {
            if (i2 == 7) {
                switch ((i & 31) >> 2) {
                    case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                        _decodeLongAscii();
                        return;
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        _decodeLongUnicode();
                        return;
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                        this._binaryValue = _read7BitBinaryWithLength();
                        return;
                    case Type.LONG /*7*/:
                        _finishRawBinary();
                        return;
                }
            }
            _throwInternal();
        }
    }

    protected final JsonToken _handleFieldName() {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b = bArr[i];
        this._typeByte = b;
        int i2;
        int i3;
        Name _findDecodedFromSymbols;
        String name;
        String[] strArr;
        switch ((b >> 6) & 3) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                switch (b) {
                    case Opcodes.ACC_SYNCHRONIZED /*32*/:
                        this._parsingContext.setCurrentName(C2915a.f14760f);
                        return JsonToken.FIELD_NAME;
                    case SmileConstants.TOKEN_PREFIX_KEY_SHARED_LONG /*48*/:
                    case Opcodes.V1_5 /*49*/:
                    case Opcodes.V1_6 /*50*/:
                    case Opcodes.V1_7 /*51*/:
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        i2 = (b & 3) << 8;
                        byte[] bArr2 = this._inputBuffer;
                        i3 = this._inputPtr;
                        this._inputPtr = i3 + 1;
                        i2 += bArr2[i3] & Util.MASK_8BIT;
                        if (i2 >= this._seenNameCount) {
                            _reportInvalidSharedName(i2);
                        }
                        this._parsingContext.setCurrentName(this._seenNames[i2]);
                        return JsonToken.FIELD_NAME;
                    case Opcodes.CALOAD /*52*/:
                        _handleLongFieldName();
                        return JsonToken.FIELD_NAME;
                    default:
                        break;
                }
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                i2 = b & 63;
                if (i2 >= this._seenNameCount) {
                    _reportInvalidSharedName(i2);
                }
                this._parsingContext.setCurrentName(this._seenNames[i2]);
                return JsonToken.FIELD_NAME;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                i = (b & 63) + 1;
                _findDecodedFromSymbols = _findDecodedFromSymbols(i);
                if (_findDecodedFromSymbols != null) {
                    name = _findDecodedFromSymbols.getName();
                    this._inputPtr = i + this._inputPtr;
                } else {
                    name = _addDecodedToSymbols(i, _decodeShortAsciiName(i));
                }
                if (this._seenNames != null) {
                    if (this._seenNameCount >= this._seenNames.length) {
                        this._seenNames = _expandSeenNames(this._seenNames);
                    }
                    strArr = this._seenNames;
                    i3 = this._seenNameCount;
                    this._seenNameCount = i3 + 1;
                    strArr[i3] = name;
                }
                this._parsingContext.setCurrentName(name);
                return JsonToken.FIELD_NAME;
            case Type.BYTE /*3*/:
                i = b & 63;
                if (i <= 55) {
                    i += 2;
                    _findDecodedFromSymbols = _findDecodedFromSymbols(i);
                    if (_findDecodedFromSymbols != null) {
                        name = _findDecodedFromSymbols.getName();
                        this._inputPtr = i + this._inputPtr;
                    } else {
                        name = _addDecodedToSymbols(i, _decodeShortUnicodeName(i));
                    }
                    if (this._seenNames != null) {
                        if (this._seenNameCount >= this._seenNames.length) {
                            this._seenNames = _expandSeenNames(this._seenNames);
                        }
                        strArr = this._seenNames;
                        i3 = this._seenNameCount;
                        this._seenNameCount = i3 + 1;
                        strArr[i3] = name;
                    }
                    this._parsingContext.setCurrentName(name);
                    return JsonToken.FIELD_NAME;
                } else if (i == 59) {
                    if (!this._parsingContext.inObject()) {
                        _reportMismatchedEndMarker(Opcodes.LUSHR, ']');
                    }
                    this._parsingContext = this._parsingContext.getParent();
                    return JsonToken.END_OBJECT;
                }
                break;
        }
        _reportError("Invalid type marker byte 0x" + Integer.toHexString(b) + " for expected field name (or END_OBJECT marker)");
        return null;
    }

    protected void _parseNumericValue(int i) {
        if (this._tokenIncomplete) {
            int i2 = this._typeByte;
            if (((i2 >> 5) & 7) != 1) {
                _reportError("Current token (" + this._currToken + ") not numeric, can not use numeric value accessors");
            }
            this._tokenIncomplete = false;
            _finishNumberToken(i2);
        }
    }

    protected void _releaseBuffers() {
        super._releaseBuffers();
        String[] strArr = this._seenNames;
        if (strArr != null && strArr.length > 0) {
            this._seenNames = null;
            Arrays.fill(strArr, 0, this._seenNameCount, null);
            this._smileBufferRecycler.releaseSeenNamesBuffer(strArr);
        }
        strArr = this._seenStringValues;
        if (strArr != null && strArr.length > 0) {
            this._seenStringValues = null;
            Arrays.fill(strArr, 0, this._seenStringValueCount, null);
            this._smileBufferRecycler.releaseSeenStringValuesBuffer(strArr);
        }
    }

    protected void _reportInvalidChar(int i) {
        if (i < 32) {
            _throwInvalidSpace(i);
        }
        _reportInvalidInitial(i);
    }

    protected void _reportInvalidInitial(int i) {
        _reportError("Invalid UTF-8 start byte 0x" + Integer.toHexString(i));
    }

    protected void _reportInvalidOther(int i) {
        _reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(i));
    }

    protected void _reportInvalidOther(int i, int i2) {
        this._inputPtr = i2;
        _reportInvalidOther(i);
    }

    protected void _reportInvalidSharedName(int i) {
        if (this._seenNames == null) {
            _reportError("Encountered shared name reference, even though document header explicitly declared no shared name references are included");
        }
        _reportError("Invalid shared name reference " + i + "; only got " + this._seenNameCount + " names in buffer (invalid content)");
    }

    protected void _reportInvalidSharedStringValue(int i) {
        if (this._seenStringValues == null) {
            _reportError("Encountered shared text value reference, even though document header did not declared shared text value references may be included");
        }
        _reportError("Invalid shared text value reference " + i + "; only got " + this._seenStringValueCount + " names in buffer (invalid content)");
    }

    protected void _skip7BitBinary() {
        int _readUnsignedVInt = _readUnsignedVInt();
        int i = _readUnsignedVInt / 7;
        int i2 = i * 8;
        _readUnsignedVInt -= i * 7;
        if (_readUnsignedVInt > 0) {
            i2 += _readUnsignedVInt + 1;
        }
        _skipBytes(i2);
    }

    protected void _skipBytes(int i) {
        while (true) {
            int min = Math.min(i, this._inputEnd - this._inputPtr);
            this._inputPtr += min;
            i -= min;
            if (i > 0) {
                loadMoreGuaranteed();
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void _skipIncomplete() {
        /*
        r4 = this;
        r0 = 0;
        r4._tokenIncomplete = r0;
        r0 = r4._typeByte;
        r1 = r0 >> 5;
        r1 = r1 & 7;
        switch(r1) {
            case 1: goto L_0x0010;
            case 2: goto L_0x0056;
            case 3: goto L_0x0056;
            case 4: goto L_0x005e;
            case 5: goto L_0x005e;
            case 6: goto L_0x000c;
            case 7: goto L_0x0066;
            default: goto L_0x000c;
        };
    L_0x000c:
        r4._throwInternal();
    L_0x000f:
        return;
    L_0x0010:
        r0 = r0 & 31;
        r1 = r0 >> 2;
        switch(r1) {
            case 1: goto L_0x0018;
            case 2: goto L_0x003e;
            default: goto L_0x0017;
        };
    L_0x0017:
        goto L_0x000c;
    L_0x0018:
        r0 = r0 & 3;
        switch(r0) {
            case 0: goto L_0x001e;
            case 1: goto L_0x0031;
            case 2: goto L_0x003a;
            default: goto L_0x001d;
        };
    L_0x001d:
        goto L_0x000c;
    L_0x001e:
        r0 = r4._inputEnd;
        r1 = r4._inputBuffer;
    L_0x0022:
        r2 = r4._inputPtr;
        if (r2 >= r0) goto L_0x0036;
    L_0x0026:
        r2 = r4._inputPtr;
        r3 = r2 + 1;
        r4._inputPtr = r3;
        r2 = r1[r2];
        if (r2 >= 0) goto L_0x0022;
    L_0x0030:
        goto L_0x000f;
    L_0x0031:
        r0 = 4;
        r4._skipBytes(r0);
        goto L_0x001e;
    L_0x0036:
        r4.loadMoreGuaranteed();
        goto L_0x001e;
    L_0x003a:
        r4._skip7BitBinary();
        goto L_0x000f;
    L_0x003e:
        r0 = r0 & 3;
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x0049;
            case 2: goto L_0x004f;
            default: goto L_0x0043;
        };
    L_0x0043:
        goto L_0x000c;
    L_0x0044:
        r0 = 5;
        r4._skipBytes(r0);
        goto L_0x000f;
    L_0x0049:
        r0 = 10;
        r4._skipBytes(r0);
        goto L_0x000f;
    L_0x004f:
        r4._readUnsignedVInt();
        r4._skip7BitBinary();
        goto L_0x000f;
    L_0x0056:
        r0 = r0 & 63;
        r0 = r0 + 1;
        r4._skipBytes(r0);
        goto L_0x000f;
    L_0x005e:
        r0 = r0 & 63;
        r0 = r0 + 2;
        r4._skipBytes(r0);
        goto L_0x000f;
    L_0x0066:
        r0 = r0 & 31;
        r0 = r0 >> 2;
        switch(r0) {
            case 0: goto L_0x006e;
            case 1: goto L_0x006e;
            case 2: goto L_0x0086;
            case 3: goto L_0x006d;
            case 4: goto L_0x006d;
            case 5: goto L_0x006d;
            case 6: goto L_0x006d;
            case 7: goto L_0x008a;
            default: goto L_0x006d;
        };
    L_0x006d:
        goto L_0x000c;
    L_0x006e:
        r0 = r4._inputEnd;
        r1 = r4._inputBuffer;
    L_0x0072:
        r2 = r4._inputPtr;
        if (r2 >= r0) goto L_0x0082;
    L_0x0076:
        r2 = r4._inputPtr;
        r3 = r2 + 1;
        r4._inputPtr = r3;
        r2 = r1[r2];
        r3 = -4;
        if (r2 != r3) goto L_0x0072;
    L_0x0081:
        goto L_0x000f;
    L_0x0082:
        r4.loadMoreGuaranteed();
        goto L_0x006e;
    L_0x0086:
        r4._skip7BitBinary();
        goto L_0x000f;
    L_0x008a:
        r0 = r4._readUnsignedVInt();
        r4._skipBytes(r0);
        goto L_0x000f;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.smile.SmileParser._skipIncomplete():void");
    }

    public void close() {
        super.close();
        this._symbols.release();
    }

    public byte[] getBinaryValue(Base64Variant base64Variant) {
        if (this._tokenIncomplete) {
            _finishToken();
        }
        if (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT) {
            _reportError("Current token (" + this._currToken + ") not VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        return this._binaryValue;
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public String getCurrentName() {
        return this._parsingContext.getCurrentName();
    }

    public NumberType getNumberType() {
        return this._got32BitFloat ? NumberType.FLOAT : super.getNumberType();
    }

    public String getText() {
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            int i = this._typeByte;
            int i2 = (i >> 5) & 7;
            if (i2 == 2 || i2 == 3) {
                _decodeShortAsciiValue((i & 63) + 1);
                return this._textBuffer.contentsAsString();
            } else if (i2 == 4 || i2 == 5) {
                _decodeShortUnicodeValue((i & 63) + 2);
                return this._textBuffer.contentsAsString();
            } else {
                _finishToken();
            }
        }
        if (this._currToken == JsonToken.VALUE_STRING) {
            return this._textBuffer.contentsAsString();
        }
        JsonToken jsonToken = this._currToken;
        return jsonToken == null ? null : jsonToken == JsonToken.FIELD_NAME ? this._parsingContext.getCurrentName() : jsonToken.isNumeric() ? getNumberValue().toString() : this._currToken.asString();
    }

    public char[] getTextCharacters() {
        if (this._currToken == null) {
            return null;
        }
        if (this._tokenIncomplete) {
            _finishToken();
        }
        switch (C35931.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return this._textBuffer.getTextBuffer();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (!this._nameCopied) {
                    String currentName = this._parsingContext.getCurrentName();
                    int length = currentName.length();
                    if (this._nameCopyBuffer == null) {
                        this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(length);
                    } else if (this._nameCopyBuffer.length < length) {
                        this._nameCopyBuffer = new char[length];
                    }
                    currentName.getChars(0, length, this._nameCopyBuffer, 0);
                    this._nameCopied = true;
                }
                return this._nameCopyBuffer;
            case Type.BYTE /*3*/:
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return getNumberValue().toString().toCharArray();
            default:
                return this._currToken.asCharArray();
        }
    }

    public int getTextLength() {
        if (this._currToken == null) {
            return 0;
        }
        if (this._tokenIncomplete) {
            _finishToken();
        }
        switch (C35931.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return this._textBuffer.size();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return this._parsingContext.getCurrentName().length();
            case Type.BYTE /*3*/:
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return getNumberValue().toString().length();
            default:
                return this._currToken.asCharArray().length;
        }
    }

    public int getTextOffset() {
        return 0;
    }

    protected boolean handleSignature(boolean z, boolean z2) {
        boolean z3 = false;
        if (z) {
            this._inputPtr++;
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        if (this._inputBuffer[this._inputPtr] == 41) {
            int i = this._inputPtr + 1;
            this._inputPtr = i;
            if (i >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            if (this._inputBuffer[this._inputPtr] == 10) {
                i = this._inputPtr + 1;
                this._inputPtr = i;
                if (i >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                byte b = bArr[i2];
                i2 = (b >> 4) & 15;
                if (i2 != 0) {
                    _reportError("Header version number bits (0x" + Integer.toHexString(i2) + ") indicate unrecognized version; only 0x0 handled by parser");
                }
                if ((b & 1) == 0) {
                    this._seenNames = null;
                    this._seenNameCount = -1;
                }
                if ((b & 2) != 0) {
                    this._seenStringValues = NO_STRINGS;
                    this._seenStringValueCount = 0;
                }
                if ((b & 4) != 0) {
                    z3 = true;
                }
                this._mayContainRawBinary = z3;
                return true;
            } else if (!z2) {
                return false;
            } else {
                _reportError("Malformed content: signature not valid, starts with 0x3a, 0x29, but followed by 0x" + Integer.toHexString(this._inputBuffer[this._inputPtr]) + ", not 0xA");
                return false;
            }
        } else if (!z2) {
            return false;
        } else {
            _reportError("Malformed content: signature not valid, starts with 0x3a but followed by 0x" + Integer.toHexString(this._inputBuffer[this._inputPtr]) + ", not 0x29");
            return false;
        }
    }

    public boolean mayContainRawBinary() {
        return this._mayContainRawBinary;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.codehaus.jackson.JsonToken nextToken() {
        /*
        r9 = this;
        r8 = 2;
        r2 = 0;
        r1 = 0;
        r0 = 1;
        r3 = r9._tokenIncomplete;
        if (r3 == 0) goto L_0x000b;
    L_0x0008:
        r9._skipIncomplete();
    L_0x000b:
        r4 = r9._currInputProcessed;
        r3 = r9._inputPtr;
        r6 = (long) r3;
        r4 = r4 + r6;
        r6 = 1;
        r4 = r4 - r6;
        r9._tokenInputTotal = r4;
        r9._binaryValue = r2;
        r3 = r9._parsingContext;
        r3 = r3.inObject();
        if (r3 == 0) goto L_0x002d;
    L_0x0020:
        r3 = r9._currToken;
        r4 = org.codehaus.jackson.JsonToken.FIELD_NAME;
        if (r3 == r4) goto L_0x002d;
    L_0x0026:
        r0 = r9._handleFieldName();
        r9._currToken = r0;
    L_0x002c:
        return r0;
    L_0x002d:
        r3 = r9._inputPtr;
        r4 = r9._inputEnd;
        if (r3 < r4) goto L_0x0043;
    L_0x0033:
        r3 = r9.loadMore();
        if (r3 != 0) goto L_0x0043;
    L_0x0039:
        r9._handleEOF();
        r9.close();
        r9._currToken = r2;
        r0 = r2;
        goto L_0x002c;
    L_0x0043:
        r3 = r9._inputBuffer;
        r4 = r9._inputPtr;
        r5 = r4 + 1;
        r9._inputPtr = r5;
        r3 = r3[r4];
        r9._typeByte = r3;
        r4 = r3 >> 5;
        r4 = r4 & 7;
        switch(r4) {
            case 0: goto L_0x007a;
            case 1: goto L_0x0088;
            case 2: goto L_0x00f3;
            case 3: goto L_0x00f3;
            case 4: goto L_0x00f3;
            case 5: goto L_0x00f3;
            case 6: goto L_0x0105;
            case 7: goto L_0x0115;
            default: goto L_0x0056;
        };
    L_0x0056:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Invalid type marker byte 0x";
        r0 = r0.append(r1);
        r1 = r3 & 255;
        r1 = java.lang.Integer.toHexString(r1);
        r0 = r0.append(r1);
        r1 = " for expected value token";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r9._reportError(r0);
        r0 = r2;
        goto L_0x002c;
    L_0x007a:
        if (r3 != 0) goto L_0x0081;
    L_0x007c:
        r0 = "Invalid token byte 0x00";
        r9._reportError(r0);
    L_0x0081:
        r0 = r3 + -1;
        r0 = r9._handleSharedString(r0);
        goto L_0x002c;
    L_0x0088:
        r4 = r3 & 31;
        r5 = 4;
        if (r4 >= r5) goto L_0x00a9;
    L_0x008d:
        switch(r4) {
            case 0: goto L_0x0095;
            case 1: goto L_0x009f;
            case 2: goto L_0x00a4;
            default: goto L_0x0090;
        };
    L_0x0090:
        r0 = org.codehaus.jackson.JsonToken.VALUE_TRUE;
        r9._currToken = r0;
        goto L_0x002c;
    L_0x0095:
        r0 = r9._textBuffer;
        r0.resetWithEmpty();
        r0 = org.codehaus.jackson.JsonToken.VALUE_STRING;
        r9._currToken = r0;
        goto L_0x002c;
    L_0x009f:
        r0 = org.codehaus.jackson.JsonToken.VALUE_NULL;
        r9._currToken = r0;
        goto L_0x002c;
    L_0x00a4:
        r0 = org.codehaus.jackson.JsonToken.VALUE_FALSE;
        r9._currToken = r0;
        goto L_0x002c;
    L_0x00a9:
        r5 = 8;
        if (r4 >= r5) goto L_0x00bb;
    L_0x00ad:
        r4 = r4 & 3;
        if (r4 > r8) goto L_0x0056;
    L_0x00b1:
        r9._tokenIncomplete = r0;
        r9._numTypesValid = r1;
        r0 = org.codehaus.jackson.JsonToken.VALUE_NUMBER_INT;
        r9._currToken = r0;
        goto L_0x002c;
    L_0x00bb:
        r5 = 12;
        if (r4 >= r5) goto L_0x00d3;
    L_0x00bf:
        r4 = r4 & 3;
        if (r4 > r8) goto L_0x0056;
    L_0x00c3:
        r9._tokenIncomplete = r0;
        r9._numTypesValid = r1;
        if (r4 != 0) goto L_0x00d1;
    L_0x00c9:
        r9._got32BitFloat = r0;
        r0 = org.codehaus.jackson.JsonToken.VALUE_NUMBER_FLOAT;
        r9._currToken = r0;
        goto L_0x002c;
    L_0x00d1:
        r0 = r1;
        goto L_0x00c9;
    L_0x00d3:
        r0 = 26;
        if (r4 != r0) goto L_0x00ec;
    L_0x00d7:
        r0 = r9.handleSignature(r1, r1);
        if (r0 == 0) goto L_0x00ec;
    L_0x00dd:
        r0 = r9._currToken;
        if (r0 != 0) goto L_0x00e7;
    L_0x00e1:
        r0 = r9.nextToken();
        goto L_0x002c;
    L_0x00e7:
        r9._currToken = r2;
        r0 = r2;
        goto L_0x002c;
    L_0x00ec:
        r0 = "Unrecognized token byte 0x3A (malformed segment header?";
        r9._reportError(r0);
        goto L_0x0056;
    L_0x00f3:
        r1 = org.codehaus.jackson.JsonToken.VALUE_STRING;
        r9._currToken = r1;
        r1 = r9._seenStringValueCount;
        if (r1 < 0) goto L_0x0102;
    L_0x00fb:
        r9._addSeenStringValue();
    L_0x00fe:
        r0 = r9._currToken;
        goto L_0x002c;
    L_0x0102:
        r9._tokenIncomplete = r0;
        goto L_0x00fe;
    L_0x0105:
        r1 = r3 & 31;
        r1 = org.codehaus.jackson.smile.SmileUtil.zigzagDecode(r1);
        r9._numberInt = r1;
        r9._numTypesValid = r0;
        r0 = org.codehaus.jackson.JsonToken.VALUE_NUMBER_INT;
        r9._currToken = r0;
        goto L_0x002c;
    L_0x0115:
        r1 = r3 & 31;
        switch(r1) {
            case 0: goto L_0x011c;
            case 4: goto L_0x011c;
            case 8: goto L_0x0124;
            case 12: goto L_0x012c;
            case 13: goto L_0x012c;
            case 14: goto L_0x012c;
            case 15: goto L_0x012c;
            case 24: goto L_0x014c;
            case 25: goto L_0x015e;
            case 26: goto L_0x017b;
            case 27: goto L_0x018d;
            case 29: goto L_0x0192;
            case 31: goto L_0x019a;
            default: goto L_0x011a;
        };
    L_0x011a:
        goto L_0x0056;
    L_0x011c:
        r9._tokenIncomplete = r0;
        r0 = org.codehaus.jackson.JsonToken.VALUE_STRING;
        r9._currToken = r0;
        goto L_0x002c;
    L_0x0124:
        r9._tokenIncomplete = r0;
        r0 = org.codehaus.jackson.JsonToken.VALUE_EMBEDDED_OBJECT;
        r9._currToken = r0;
        goto L_0x002c;
    L_0x012c:
        r0 = r9._inputPtr;
        r1 = r9._inputEnd;
        if (r0 < r1) goto L_0x0135;
    L_0x0132:
        r9.loadMoreGuaranteed();
    L_0x0135:
        r0 = r3 & 3;
        r0 = r0 << 8;
        r1 = r9._inputBuffer;
        r2 = r9._inputPtr;
        r3 = r2 + 1;
        r9._inputPtr = r3;
        r1 = r1[r2];
        r1 = r1 & 255;
        r0 = r0 + r1;
        r0 = r9._handleSharedString(r0);
        goto L_0x002c;
    L_0x014c:
        r0 = r9._parsingContext;
        r1 = r9._tokenInputRow;
        r2 = r9._tokenInputCol;
        r0 = r0.createChildArrayContext(r1, r2);
        r9._parsingContext = r0;
        r0 = org.codehaus.jackson.JsonToken.START_ARRAY;
        r9._currToken = r0;
        goto L_0x002c;
    L_0x015e:
        r0 = r9._parsingContext;
        r0 = r0.inArray();
        if (r0 != 0) goto L_0x016d;
    L_0x0166:
        r0 = 93;
        r1 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        r9._reportMismatchedEndMarker(r0, r1);
    L_0x016d:
        r0 = r9._parsingContext;
        r0 = r0.getParent();
        r9._parsingContext = r0;
        r0 = org.codehaus.jackson.JsonToken.END_ARRAY;
        r9._currToken = r0;
        goto L_0x002c;
    L_0x017b:
        r0 = r9._parsingContext;
        r1 = r9._tokenInputRow;
        r2 = r9._tokenInputCol;
        r0 = r0.createChildObjectContext(r1, r2);
        r9._parsingContext = r0;
        r0 = org.codehaus.jackson.JsonToken.START_OBJECT;
        r9._currToken = r0;
        goto L_0x002c;
    L_0x018d:
        r1 = "Invalid type marker byte 0xFB in value mode (would be END_OBJECT in key mode)";
        r9._reportError(r1);
    L_0x0192:
        r9._tokenIncomplete = r0;
        r0 = org.codehaus.jackson.JsonToken.VALUE_EMBEDDED_OBJECT;
        r9._currToken = r0;
        goto L_0x002c;
    L_0x019a:
        r9._currToken = r2;
        r0 = r2;
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.smile.SmileParser.nextToken():org.codehaus.jackson.JsonToken");
    }

    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }
}
