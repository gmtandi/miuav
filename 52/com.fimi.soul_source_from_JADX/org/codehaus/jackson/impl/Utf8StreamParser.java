package org.codehaus.jackson.impl;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.fimi.soul.entity.User;
import com.fimi.soul.module.setting.newhand.C1873o;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.open.GameAppOperation;
import java.io.InputStream;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.sym.BytesToNameCanonicalizer;
import org.codehaus.jackson.sym.Name;
import org.codehaus.jackson.util.ByteArrayBuilder;
import org.codehaus.jackson.util.CharTypes;
import org.p122a.p123a.p124f.p125c.C3022o;

public final class Utf8StreamParser extends StreamBasedParserBase {
    static final byte BYTE_LF = (byte) 10;
    private static final int[] sInputCodesLatin1;
    private static final int[] sInputCodesUtf8;
    protected ObjectCodec _objectCodec;
    private int _quad1;
    protected int[] _quadBuffer;
    protected final BytesToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;

    /* renamed from: org.codehaus.jackson.impl.Utf8StreamParser.1 */
    /* synthetic */ class C35781 {
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$JsonToken;

        static {
            $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 2;
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

    static {
        sInputCodesUtf8 = CharTypes.getInputCodeUtf8();
        sInputCodesLatin1 = CharTypes.getInputCodeLatin1();
    }

    public Utf8StreamParser(IOContext iOContext, int i, InputStream inputStream, ObjectCodec objectCodec, BytesToNameCanonicalizer bytesToNameCanonicalizer, byte[] bArr, int i2, int i3, boolean z) {
        super(iOContext, i, inputStream, bArr, i2, i3, z);
        this._quadBuffer = new int[16];
        this._tokenIncomplete = false;
        this._objectCodec = objectCodec;
        this._symbols = bytesToNameCanonicalizer;
        if (!Feature.CANONICALIZE_FIELD_NAMES.enabledIn(i)) {
            _throwInternal();
        }
    }

    private final int _decodeBase64Escape(Base64Variant base64Variant, int i, int i2) {
        if (i != 92) {
            throw reportInvalidChar(base64Variant, i, i2);
        }
        int _decodeEscaped = _decodeEscaped();
        if (_decodeEscaped <= C3022o.f15055c && i2 == 0) {
            return -1;
        }
        int decodeBase64Char = base64Variant.decodeBase64Char(_decodeEscaped);
        if (decodeBase64Char >= 0) {
            return decodeBase64Char;
        }
        throw reportInvalidChar(base64Variant, _decodeEscaped, i2);
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

    private final void _finishString2(char[] cArr, int i) {
        int[] iArr = sInputCodesUtf8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i2 = this._inputPtr;
            if (i2 >= this._inputEnd) {
                loadMoreGuaranteed();
                i2 = this._inputPtr;
            }
            if (i >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            int min = Math.min(this._inputEnd, (cArr.length - i) + i2);
            while (i2 < min) {
                int i3 = i2 + 1;
                i2 = bArr[i2] & Util.MASK_8BIT;
                if (iArr[i2] != 0) {
                    this._inputPtr = i3;
                    if (i2 == 34) {
                        this._textBuffer.setCurrentLength(i);
                        return;
                    }
                    switch (iArr[i2]) {
                        case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                            i2 = _decodeEscaped();
                            break;
                        case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                            i2 = _decodeUtf8_2(i2);
                            break;
                        case Type.BYTE /*3*/:
                            if (this._inputEnd - this._inputPtr < 2) {
                                i2 = _decodeUtf8_3(i2);
                                break;
                            } else {
                                i2 = _decodeUtf8_3fast(i2);
                                break;
                            }
                        case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                            i3 = _decodeUtf8_4(i2);
                            i2 = i + 1;
                            cArr[i] = (char) (55296 | (i3 >> 10));
                            if (i2 >= cArr.length) {
                                cArr = this._textBuffer.finishCurrentSegment();
                                i2 = 0;
                            }
                            i = i2;
                            i2 = (i3 & 1023) | 56320;
                            break;
                        default:
                            if (i2 >= 32) {
                                _reportInvalidChar(i2);
                                break;
                            } else {
                                _throwUnquotedSpace(i2, "string value");
                                break;
                            }
                    }
                    if (i >= cArr.length) {
                        cArr = this._textBuffer.finishCurrentSegment();
                        i3 = 0;
                    } else {
                        i3 = i;
                    }
                    i = i3 + 1;
                    cArr[i3] = (char) i2;
                } else {
                    int i4 = i + 1;
                    cArr[i] = (char) i2;
                    i2 = i3;
                    i = i4;
                }
            }
            this._inputPtr = i2;
        }
    }

    private final JsonToken _nextAfterName() {
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        this._currToken = jsonToken;
        return jsonToken;
    }

    private final JsonToken _nextTokenNotInObject(int i) {
        if (i == 34) {
            this._tokenIncomplete = true;
            JsonToken jsonToken = JsonToken.VALUE_STRING;
            this._currToken = jsonToken;
            return jsonToken;
        }
        switch (i) {
            case GameAppOperation.SHARE_PRIZE_TITLE_MAX_LENGTH /*45*/:
            case SmileConstants.TOKEN_PREFIX_KEY_SHARED_LONG /*48*/:
            case Opcodes.V1_5 /*49*/:
            case Opcodes.V1_6 /*50*/:
            case Opcodes.V1_7 /*51*/:
            case Opcodes.CALOAD /*52*/:
            case Opcodes.SALOAD /*53*/:
            case Opcodes.ISTORE /*54*/:
            case Opcodes.LSTORE /*55*/:
            case SmileConstants.MAX_SHORT_NAME_UNICODE_BYTES /*56*/:
            case Opcodes.DSTORE /*57*/:
                jsonToken = parseNumberText(i);
                this._currToken = jsonToken;
                return jsonToken;
            case Opcodes.DUP_X2 /*91*/:
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                jsonToken = JsonToken.START_ARRAY;
                this._currToken = jsonToken;
                return jsonToken;
            case Opcodes.DUP2_X1 /*93*/:
            case Opcodes.LUSHR /*125*/:
                _reportUnexpectedChar(i, "expected a value");
                break;
            case Opcodes.FSUB /*102*/:
                _matchToken(JsonToken.VALUE_FALSE);
                jsonToken = JsonToken.VALUE_FALSE;
                this._currToken = jsonToken;
                return jsonToken;
            case Opcodes.FDIV /*110*/:
                _matchToken(JsonToken.VALUE_NULL);
                jsonToken = JsonToken.VALUE_NULL;
                this._currToken = jsonToken;
                return jsonToken;
            case Opcodes.INEG /*116*/:
                break;
            case Opcodes.LSHR /*123*/:
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                jsonToken = JsonToken.START_OBJECT;
                this._currToken = jsonToken;
                return jsonToken;
            default:
                jsonToken = _handleUnexpectedValue(i);
                this._currToken = jsonToken;
                return jsonToken;
        }
        _matchToken(JsonToken.VALUE_TRUE);
        jsonToken = JsonToken.VALUE_TRUE;
        this._currToken = jsonToken;
        return jsonToken;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final org.codehaus.jackson.JsonToken _parseFloatText(char[] r10, int r11, int r12, boolean r13, int r14) {
        /*
        r9 = this;
        r0 = 0;
        r4 = 0;
        r1 = 46;
        if (r12 != r1) goto L_0x0109;
    L_0x0006:
        r1 = r11 + 1;
        r2 = (char) r12;
        r10[r11] = r2;
    L_0x000b:
        r2 = r9._inputPtr;
        r3 = r9._inputEnd;
        if (r2 < r3) goto L_0x00c0;
    L_0x0011:
        r2 = r9.loadMore();
        if (r2 != 0) goto L_0x00c0;
    L_0x0017:
        r4 = 1;
    L_0x0018:
        if (r0 != 0) goto L_0x001f;
    L_0x001a:
        r2 = "Decimal point not followed by a digit";
        r9.reportUnexpectedNumberChar(r12, r2);
    L_0x001f:
        r6 = r0;
        r0 = r1;
        r1 = r10;
    L_0x0022:
        r3 = 0;
        r2 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r12 == r2) goto L_0x002b;
    L_0x0027:
        r2 = 69;
        if (r12 != r2) goto L_0x0103;
    L_0x002b:
        r2 = r1.length;
        if (r0 < r2) goto L_0x0035;
    L_0x002e:
        r0 = r9._textBuffer;
        r1 = r0.finishCurrentSegment();
        r0 = 0;
    L_0x0035:
        r2 = r0 + 1;
        r5 = (char) r12;
        r1[r0] = r5;
        r0 = r9._inputPtr;
        r5 = r9._inputEnd;
        if (r0 < r5) goto L_0x0043;
    L_0x0040:
        r9.loadMoreGuaranteed();
    L_0x0043:
        r0 = r9._inputBuffer;
        r5 = r9._inputPtr;
        r7 = r5 + 1;
        r9._inputPtr = r7;
        r0 = r0[r5];
        r5 = r0 & 255;
        r0 = 45;
        if (r5 == r0) goto L_0x0057;
    L_0x0053:
        r0 = 43;
        if (r5 != r0) goto L_0x00ff;
    L_0x0057:
        r0 = r1.length;
        if (r2 < r0) goto L_0x00fc;
    L_0x005a:
        r0 = r9._textBuffer;
        r1 = r0.finishCurrentSegment();
        r0 = 0;
    L_0x0061:
        r2 = r0 + 1;
        r5 = (char) r5;
        r1[r0] = r5;
        r0 = r9._inputPtr;
        r5 = r9._inputEnd;
        if (r0 < r5) goto L_0x006f;
    L_0x006c:
        r9.loadMoreGuaranteed();
    L_0x006f:
        r0 = r9._inputBuffer;
        r5 = r9._inputPtr;
        r7 = r5 + 1;
        r9._inputPtr = r7;
        r0 = r0[r5];
        r0 = r0 & 255;
        r5 = r0;
        r0 = r2;
        r2 = r3;
    L_0x007e:
        r3 = 57;
        if (r5 > r3) goto L_0x00f7;
    L_0x0082:
        r3 = 48;
        if (r5 < r3) goto L_0x00f7;
    L_0x0086:
        r2 = r2 + 1;
        r3 = r1.length;
        if (r0 < r3) goto L_0x0092;
    L_0x008b:
        r0 = r9._textBuffer;
        r1 = r0.finishCurrentSegment();
        r0 = 0;
    L_0x0092:
        r3 = r0 + 1;
        r7 = (char) r5;
        r1[r0] = r7;
        r0 = r9._inputPtr;
        r7 = r9._inputEnd;
        if (r0 < r7) goto L_0x00e8;
    L_0x009d:
        r0 = r9.loadMore();
        if (r0 != 0) goto L_0x00e8;
    L_0x00a3:
        r4 = 1;
        r0 = r2;
        r1 = r4;
        r2 = r3;
    L_0x00a7:
        if (r0 != 0) goto L_0x00ae;
    L_0x00a9:
        r3 = "Exponent indicator not followed by a digit";
        r9.reportUnexpectedNumberChar(r5, r3);
    L_0x00ae:
        if (r1 != 0) goto L_0x00b6;
    L_0x00b0:
        r1 = r9._inputPtr;
        r1 = r1 + -1;
        r9._inputPtr = r1;
    L_0x00b6:
        r1 = r9._textBuffer;
        r1.setCurrentLength(r2);
        r0 = r9.resetFloat(r13, r14, r6, r0);
        return r0;
    L_0x00c0:
        r2 = r9._inputBuffer;
        r3 = r9._inputPtr;
        r5 = r3 + 1;
        r9._inputPtr = r5;
        r2 = r2[r3];
        r12 = r2 & 255;
        r2 = 48;
        if (r12 < r2) goto L_0x0018;
    L_0x00d0:
        r2 = 57;
        if (r12 > r2) goto L_0x0018;
    L_0x00d4:
        r0 = r0 + 1;
        r2 = r10.length;
        if (r1 < r2) goto L_0x0107;
    L_0x00d9:
        r1 = r9._textBuffer;
        r10 = r1.finishCurrentSegment();
        r1 = 0;
        r2 = r1;
    L_0x00e1:
        r1 = r2 + 1;
        r3 = (char) r12;
        r10[r2] = r3;
        goto L_0x000b;
    L_0x00e8:
        r0 = r9._inputBuffer;
        r5 = r9._inputPtr;
        r7 = r5 + 1;
        r9._inputPtr = r7;
        r0 = r0[r5];
        r0 = r0 & 255;
        r5 = r0;
        r0 = r3;
        goto L_0x007e;
    L_0x00f7:
        r1 = r4;
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x00a7;
    L_0x00fc:
        r0 = r2;
        goto L_0x0061;
    L_0x00ff:
        r0 = r2;
        r2 = r3;
        goto L_0x007e;
    L_0x0103:
        r1 = r4;
        r2 = r0;
        r0 = r3;
        goto L_0x00ae;
    L_0x0107:
        r2 = r1;
        goto L_0x00e1;
    L_0x0109:
        r6 = r0;
        r1 = r10;
        r0 = r11;
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.Utf8StreamParser._parseFloatText(char[], int, int, boolean, int):org.codehaus.jackson.JsonToken");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final org.codehaus.jackson.JsonToken _parserNumber2(char[] r7, int r8, boolean r9, int r10) {
        /*
        r6 = this;
        r5 = r10;
        r2 = r8;
        r1 = r7;
    L_0x0003:
        r0 = r6._inputPtr;
        r3 = r6._inputEnd;
        if (r0 < r3) goto L_0x0019;
    L_0x0009:
        r0 = r6.loadMore();
        if (r0 != 0) goto L_0x0019;
    L_0x000f:
        r0 = r6._textBuffer;
        r0.setCurrentLength(r2);
        r0 = r6.resetInt(r9, r5);
    L_0x0018:
        return r0;
    L_0x0019:
        r0 = r6._inputBuffer;
        r3 = r6._inputPtr;
        r4 = r3 + 1;
        r6._inputPtr = r4;
        r0 = r0[r3];
        r3 = r0 & 255;
        r0 = 57;
        if (r3 > r0) goto L_0x002d;
    L_0x0029:
        r0 = 48;
        if (r3 >= r0) goto L_0x0040;
    L_0x002d:
        r0 = 46;
        if (r3 == r0) goto L_0x0039;
    L_0x0031:
        r0 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r3 == r0) goto L_0x0039;
    L_0x0035:
        r0 = 69;
        if (r3 != r0) goto L_0x0053;
    L_0x0039:
        r0 = r6;
        r4 = r9;
        r0 = r0._parseFloatText(r1, r2, r3, r4, r5);
        goto L_0x0018;
    L_0x0040:
        r0 = r1.length;
        if (r2 < r0) goto L_0x0063;
    L_0x0043:
        r0 = r6._textBuffer;
        r1 = r0.finishCurrentSegment();
        r2 = 0;
        r0 = r2;
    L_0x004b:
        r2 = r0 + 1;
        r3 = (char) r3;
        r1[r0] = r3;
        r5 = r5 + 1;
        goto L_0x0003;
    L_0x0053:
        r0 = r6._inputPtr;
        r0 = r0 + -1;
        r6._inputPtr = r0;
        r0 = r6._textBuffer;
        r0.setCurrentLength(r2);
        r0 = r6.resetInt(r9, r5);
        goto L_0x0018;
    L_0x0063:
        r0 = r2;
        goto L_0x004b;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.Utf8StreamParser._parserNumber2(char[], int, boolean, int):org.codehaus.jackson.JsonToken");
    }

    private final void _skipCComment() {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                int i2 = bArr[i] & Util.MASK_8BIT;
                i = inputCodeComment[i2];
                if (i != 0) {
                    switch (i) {
                        case Type.OBJECT /*10*/:
                            _skipLF();
                            break;
                        case Opcodes.FCONST_2 /*13*/:
                            _skipCR();
                            break;
                        case C1873o.f9552n /*42*/:
                            if (this._inputBuffer[this._inputPtr] != 47) {
                                break;
                            }
                            this._inputPtr++;
                            return;
                        default:
                            _reportInvalidChar(i2);
                            break;
                    }
                }
            } else {
                _reportInvalidEOF(" in a comment");
                return;
            }
        }
    }

    private final void _skipComment() {
        if (!isEnabled(Feature.ALLOW_COMMENTS)) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in a comment");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = bArr[i] & Util.MASK_8BIT;
        if (i2 == 47) {
            _skipCppComment();
        } else if (i2 == 42) {
            _skipCComment();
        } else {
            _reportUnexpectedChar(i2, "was expecting either '*' or '/' for a comment");
        }
    }

    private final void _skipCppComment() {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                int i2 = bArr[i] & Util.MASK_8BIT;
                i = inputCodeComment[i2];
                if (i != 0) {
                    switch (i) {
                        case Type.OBJECT /*10*/:
                            _skipLF();
                            return;
                        case Opcodes.FCONST_2 /*13*/:
                            _skipCR();
                            return;
                        case C1873o.f9552n /*42*/:
                            break;
                        default:
                            _reportInvalidChar(i2);
                            break;
                    }
                }
            } else {
                return;
            }
        }
    }

    private final void _skipUtf8_2(int i) {
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
    }

    private final void _skipUtf8_3(int i) {
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
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        bArr = this._inputBuffer;
        i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        b = bArr[i2];
        if ((b & SmileConstants.TOKEN_PREFIX_SMALL_INT) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
            _reportInvalidOther(b & Util.MASK_8BIT, this._inputPtr);
        }
    }

    private final void _skipUtf8_4(int i) {
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
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        if ((b & SmileConstants.TOKEN_PREFIX_SMALL_INT) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
            _reportInvalidOther(b & Util.MASK_8BIT, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        bArr = this._inputBuffer;
        i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        b = bArr[i2];
        if ((b & SmileConstants.TOKEN_PREFIX_SMALL_INT) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
            _reportInvalidOther(b & Util.MASK_8BIT, this._inputPtr);
        }
    }

    private final int _skipWS() {
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & Util.MASK_8BIT;
            if (i2 > 32) {
                if (i2 != 47) {
                    return i2;
                }
                _skipComment();
            } else if (i2 != 32) {
                if (i2 == 10) {
                    _skipLF();
                } else if (i2 == 13) {
                    _skipCR();
                } else if (i2 != 9) {
                    _throwInvalidSpace(i2);
                }
            }
        }
        throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.getTypeDesc() + " entries");
    }

    private final int _skipWSOrEnd() {
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                int i2 = bArr[i] & Util.MASK_8BIT;
                if (i2 > 32) {
                    if (i2 != 47) {
                        return i2;
                    }
                    _skipComment();
                } else if (i2 != 32) {
                    if (i2 == 10) {
                        _skipLF();
                    } else if (i2 == 13) {
                        _skipCR();
                    } else if (i2 != 9) {
                        _throwInvalidSpace(i2);
                    }
                }
            } else {
                _handleEOF();
                return -1;
            }
        }
    }

    private final int _verifyNoLeadingZeroes() {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            return 48;
        }
        int i = this._inputBuffer[this._inputPtr] & Util.MASK_8BIT;
        if (i < 48 || i > 57) {
            return 48;
        }
        if (!isEnabled(Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            reportInvalidNumber("Leading zeroes not allowed");
        }
        this._inputPtr++;
        if (i != 48) {
            return i;
        }
        do {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                return i;
            }
            i = this._inputBuffer[this._inputPtr] & Util.MASK_8BIT;
            if (i < 48 || i > 57) {
                return 48;
            }
            this._inputPtr++;
        } while (i == 48);
        return i;
    }

    private final Name addName(int[] iArr, int i, int i2) {
        int i3;
        int i4 = ((i << 2) - 4) + i2;
        if (i2 < 4) {
            i3 = iArr[i - 1];
            iArr[i - 1] = i3 << ((4 - i2) << 3);
        } else {
            i3 = 0;
        }
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i5 = 0;
        int i6 = 0;
        while (i6 < i4) {
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
                if (i6 + i9 > i4) {
                    _reportInvalidEOF(" in field name");
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
        if (i2 < 4) {
            iArr[i - 1] = i3;
        }
        return this._symbols.addName(str, iArr, i);
    }

    private final Name findName(int i, int i2) {
        Name findName = this._symbols.findName(i);
        if (findName != null) {
            return findName;
        }
        this._quadBuffer[0] = i;
        return addName(this._quadBuffer, 1, i2);
    }

    private final Name findName(int i, int i2, int i3) {
        Name findName = this._symbols.findName(i, i2);
        if (findName != null) {
            return findName;
        }
        this._quadBuffer[0] = i;
        this._quadBuffer[1] = i2;
        return addName(this._quadBuffer, 2, i3);
    }

    private final Name findName(int[] iArr, int i, int i2, int i3) {
        if (i >= iArr.length) {
            iArr = growArrayBy(iArr, iArr.length);
            this._quadBuffer = iArr;
        }
        int i4 = i + 1;
        iArr[i] = i2;
        Name findName = this._symbols.findName(iArr, i4);
        return findName == null ? addName(iArr, i4, i3) : findName;
    }

    public static int[] growArrayBy(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        int length = iArr.length;
        Object obj = new int[(length + i)];
        System.arraycopy(iArr, 0, obj, 0, length);
        return obj;
    }

    private int nextByte() {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        return bArr[i] & Util.MASK_8BIT;
    }

    private final Name parseFieldName(int i, int i2, int i3) {
        return parseEscapedFieldName(this._quadBuffer, 0, i, i2, i3);
    }

    private final Name parseFieldName(int i, int i2, int i3, int i4) {
        this._quadBuffer[0] = i;
        return parseEscapedFieldName(this._quadBuffer, 1, i2, i3, i4);
    }

    protected byte[] _decodeBase64(Base64Variant base64Variant) {
        int decodeBase64Char;
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            i = bArr[i] & Util.MASK_8BIT;
            if (i > 32) {
                decodeBase64Char = base64Variant.decodeBase64Char(i);
                if (decodeBase64Char < 0) {
                    if (i == 34) {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, i, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr2 = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                i2 = bArr2[i2] & Util.MASK_8BIT;
                i = base64Variant.decodeBase64Char(i2);
                if (i < 0) {
                    i = _decodeBase64Escape(base64Variant, i2, 1);
                }
                i |= decodeBase64Char << 6;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                bArr = this._inputBuffer;
                i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                i2 = bArr[i2] & Util.MASK_8BIT;
                decodeBase64Char = base64Variant.decodeBase64Char(i2);
                if (decodeBase64Char < 0) {
                    if (decodeBase64Char != -2) {
                        if (i2 != 34 || base64Variant.usesPadding()) {
                            decodeBase64Char = _decodeBase64Escape(base64Variant, i2, 2);
                        } else {
                            _getByteArrayBuilder.append(i >> 4);
                            return _getByteArrayBuilder.toByteArray();
                        }
                    }
                    if (decodeBase64Char == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        bArr = this._inputBuffer;
                        i2 = this._inputPtr;
                        this._inputPtr = i2 + 1;
                        decodeBase64Char = bArr[i2] & Util.MASK_8BIT;
                        if (!base64Variant.usesPaddingChar(decodeBase64Char)) {
                            break;
                        }
                        _getByteArrayBuilder.append(i >> 4);
                    }
                }
                i = (i << 6) | decodeBase64Char;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                bArr = this._inputBuffer;
                i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                i2 = bArr[i2] & Util.MASK_8BIT;
                decodeBase64Char = base64Variant.decodeBase64Char(i2);
                if (decodeBase64Char < 0) {
                    if (decodeBase64Char != -2) {
                        if (i2 != 34 || base64Variant.usesPadding()) {
                            decodeBase64Char = _decodeBase64Escape(base64Variant, i2, 3);
                        } else {
                            _getByteArrayBuilder.appendTwoBytes(i >> 2);
                            return _getByteArrayBuilder.toByteArray();
                        }
                    }
                    if (decodeBase64Char == -2) {
                        _getByteArrayBuilder.appendTwoBytes(i >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes(decodeBase64Char | (i << 6));
            }
        }
        throw reportInvalidChar(base64Variant, decodeBase64Char, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
    }

    protected int _decodeCharForError(int i) {
        if (i >= 0) {
            return i;
        }
        Object obj;
        int i2;
        if ((i & SmileConstants.TOKEN_PREFIX_MISC_OTHER) == SmileConstants.TOKEN_PREFIX_SMALL_INT) {
            i &= 31;
            obj = 1;
        } else if ((i & 240) == SmileConstants.TOKEN_PREFIX_MISC_OTHER) {
            i &= 15;
            i2 = 2;
        } else if ((i & 248) == 240) {
            i &= 7;
            obj = 3;
        } else {
            _reportInvalidInitial(i & Util.MASK_8BIT);
            i2 = 1;
        }
        int nextByte = nextByte();
        if ((nextByte & SmileConstants.TOKEN_PREFIX_SMALL_INT) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
            _reportInvalidOther(nextByte & Util.MASK_8BIT);
        }
        i = (i << 6) | (nextByte & 63);
        if (obj <= 1) {
            return i;
        }
        int nextByte2 = nextByte();
        if ((nextByte2 & SmileConstants.TOKEN_PREFIX_SMALL_INT) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
            _reportInvalidOther(nextByte2 & Util.MASK_8BIT);
        }
        i = (i << 6) | (nextByte2 & 63);
        if (obj <= 2) {
            return i;
        }
        i2 = nextByte();
        if ((i2 & SmileConstants.TOKEN_PREFIX_SMALL_INT) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
            _reportInvalidOther(i2 & Util.MASK_8BIT);
        }
        return (i << 6) | (i2 & 63);
    }

    protected final char _decodeEscaped() {
        int i = 0;
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in character escape sequence");
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        switch (b) {
            case C1873o.f9538Z /*34*/:
            case Opcodes.V1_3 /*47*/:
            case Opcodes.DUP2 /*92*/:
                return (char) b;
            case Opcodes.FADD /*98*/:
                return '\b';
            case Opcodes.FSUB /*102*/:
                return '\f';
            case Opcodes.FDIV /*110*/:
                return '\n';
            case Opcodes.FREM /*114*/:
                return C3022o.f15053a;
            case Opcodes.INEG /*116*/:
                return '\t';
            case Opcodes.LNEG /*117*/:
                int i3 = 0;
                while (i < 4) {
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        _reportInvalidEOF(" in character escape sequence");
                    }
                    byte[] bArr2 = this._inputBuffer;
                    int i4 = this._inputPtr;
                    this._inputPtr = i4 + 1;
                    byte b2 = bArr2[i4];
                    i4 = CharTypes.charToHex(b2);
                    if (i4 < 0) {
                        _reportUnexpectedChar(b2, "expected a hex-digit for character escape sequence");
                    }
                    i3 = (i3 << 4) | i4;
                    i++;
                }
                return (char) i3;
            default:
                return _handleUnrecognizedCharacterEscape((char) _decodeCharForError(b));
        }
    }

    protected void _finishString() {
        int i = this._inputPtr;
        if (i >= this._inputEnd) {
            loadMoreGuaranteed();
            i = this._inputPtr;
        }
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = sInputCodesUtf8;
        int min = Math.min(this._inputEnd, emptyAndGetCurrentSegment.length + i);
        byte[] bArr = this._inputBuffer;
        int i2 = i;
        i = 0;
        while (i2 < min) {
            int i3 = bArr[i2] & Util.MASK_8BIT;
            if (iArr[i3] != 0) {
                if (i3 == 34) {
                    this._inputPtr = i2 + 1;
                    this._textBuffer.setCurrentLength(i);
                    return;
                }
                this._inputPtr = i2;
                _finishString2(emptyAndGetCurrentSegment, i);
            }
            int i4 = i2 + 1;
            i2 = i + 1;
            emptyAndGetCurrentSegment[i] = (char) i3;
            i = i2;
            i2 = i4;
        }
        this._inputPtr = i2;
        _finishString2(emptyAndGetCurrentSegment, i);
    }

    protected final String _getText2(JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        switch (C35781.$SwitchMap$org$codehaus$jackson$JsonToken[jsonToken.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return this._parsingContext.getCurrentName();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
            case Type.BYTE /*3*/:
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return this._textBuffer.contentsAsString();
            default:
                return jsonToken.asString();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected org.codehaus.jackson.JsonToken _handleApostropheValue() {
        /*
        r10 = this;
        r9 = 39;
        r2 = 0;
        r0 = r10._textBuffer;
        r0 = r0.emptyAndGetCurrentSegment();
        r6 = sInputCodesUtf8;
        r7 = r10._inputBuffer;
        r1 = r2;
    L_0x000e:
        r3 = r10._inputPtr;
        r4 = r10._inputEnd;
        if (r3 < r4) goto L_0x0017;
    L_0x0014:
        r10.loadMoreGuaranteed();
    L_0x0017:
        r3 = r0.length;
        if (r1 < r3) goto L_0x0021;
    L_0x001a:
        r0 = r10._textBuffer;
        r0 = r0.finishCurrentSegment();
        r1 = r2;
    L_0x0021:
        r4 = r10._inputEnd;
        r3 = r10._inputPtr;
        r5 = r0.length;
        r5 = r5 - r1;
        r3 = r3 + r5;
        if (r3 >= r4) goto L_0x00b5;
    L_0x002a:
        r4 = r10._inputPtr;
        if (r4 >= r3) goto L_0x000e;
    L_0x002e:
        r4 = r10._inputPtr;
        r5 = r4 + 1;
        r10._inputPtr = r5;
        r4 = r7[r4];
        r5 = r4 & 255;
        if (r5 == r9) goto L_0x003e;
    L_0x003a:
        r4 = r6[r5];
        if (r4 == 0) goto L_0x0048;
    L_0x003e:
        if (r5 != r9) goto L_0x004f;
    L_0x0040:
        r0 = r10._textBuffer;
        r0.setCurrentLength(r1);
        r0 = org.codehaus.jackson.JsonToken.VALUE_STRING;
        return r0;
    L_0x0048:
        r4 = r1 + 1;
        r5 = (char) r5;
        r0[r1] = r5;
        r1 = r4;
        goto L_0x002a;
    L_0x004f:
        r3 = r6[r5];
        switch(r3) {
            case 1: goto L_0x0071;
            case 2: goto L_0x007a;
            case 3: goto L_0x007f;
            case 4: goto L_0x0091;
            default: goto L_0x0054;
        };
    L_0x0054:
        r3 = 32;
        if (r5 >= r3) goto L_0x005d;
    L_0x0058:
        r3 = "string value";
        r10._throwUnquotedSpace(r5, r3);
    L_0x005d:
        r10._reportInvalidChar(r5);
    L_0x0060:
        r3 = r5;
    L_0x0061:
        r4 = r0.length;
        if (r1 < r4) goto L_0x00b1;
    L_0x0064:
        r0 = r10._textBuffer;
        r0 = r0.finishCurrentSegment();
        r4 = r2;
    L_0x006b:
        r1 = r4 + 1;
        r3 = (char) r3;
        r0[r4] = r3;
        goto L_0x000e;
    L_0x0071:
        r3 = 34;
        if (r5 == r3) goto L_0x0060;
    L_0x0075:
        r3 = r10._decodeEscaped();
        goto L_0x0061;
    L_0x007a:
        r3 = r10._decodeUtf8_2(r5);
        goto L_0x0061;
    L_0x007f:
        r3 = r10._inputEnd;
        r4 = r10._inputPtr;
        r3 = r3 - r4;
        r4 = 2;
        if (r3 < r4) goto L_0x008c;
    L_0x0087:
        r3 = r10._decodeUtf8_3fast(r5);
        goto L_0x0061;
    L_0x008c:
        r3 = r10._decodeUtf8_3(r5);
        goto L_0x0061;
    L_0x0091:
        r4 = r10._decodeUtf8_4(r5);
        r3 = r1 + 1;
        r5 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        r8 = r4 >> 10;
        r5 = r5 | r8;
        r5 = (char) r5;
        r0[r1] = r5;
        r1 = r0.length;
        if (r3 < r1) goto L_0x00b3;
    L_0x00a3:
        r0 = r10._textBuffer;
        r0 = r0.finishCurrentSegment();
        r1 = r2;
    L_0x00aa:
        r3 = 56320; // 0xdc00 float:7.8921E-41 double:2.7826E-319;
        r4 = r4 & 1023;
        r3 = r3 | r4;
        goto L_0x0061;
    L_0x00b1:
        r4 = r1;
        goto L_0x006b;
    L_0x00b3:
        r1 = r3;
        goto L_0x00aa;
    L_0x00b5:
        r3 = r4;
        goto L_0x002a;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.Utf8StreamParser._handleApostropheValue():org.codehaus.jackson.JsonToken");
    }

    protected JsonToken _handleInvalidNumberStart(int i, boolean z) {
        double d = Double.NEGATIVE_INFINITY;
        if (i == 73) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOFInValue();
            }
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            i = bArr[i2];
            String str;
            if (i == 78) {
                str = z ? "-INF" : "+INF";
                if (_matchToken(str, 3)) {
                    if (isEnabled(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                        if (!z) {
                            d = Double.POSITIVE_INFINITY;
                        }
                        return resetAsNaN(str, d);
                    }
                    _reportError("Non-standard token '" + str + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                }
            } else if (i == Opcodes.FDIV) {
                str = z ? "-Infinity" : "+Infinity";
                if (_matchToken(str, 3)) {
                    if (isEnabled(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                        if (!z) {
                            d = Double.POSITIVE_INFINITY;
                        }
                        return resetAsNaN(str, d);
                    }
                    _reportError("Non-standard token '" + str + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                }
            }
        }
        reportUnexpectedNumberChar(i, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }

    protected JsonToken _handleUnexpectedValue(int i) {
        byte[] bArr;
        int i2;
        switch (i) {
            case 39:
                if (isEnabled(Feature.ALLOW_SINGLE_QUOTES)) {
                    return _handleApostropheValue();
                }
                break;
            case C1873o.f9553o /*43*/:
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    _reportInvalidEOFInValue();
                }
                bArr = this._inputBuffer;
                i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                return _handleInvalidNumberStart(bArr[i2] & Util.MASK_8BIT, false);
            case 78:
                if (_matchToken("NaN", 1)) {
                    if (isEnabled(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                        return resetAsNaN("NaN", Double.NaN);
                    }
                    _reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                }
                bArr = this._inputBuffer;
                i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                _reportUnexpectedChar(bArr[i2] & Util.MASK_8BIT, "expected 'NaN' or a valid value");
                break;
        }
        _reportUnexpectedChar(i, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
    }

    protected final Name _handleUnusualFieldName(int i) {
        if (i == 39 && isEnabled(Feature.ALLOW_SINGLE_QUOTES)) {
            return _parseApostropheFieldName();
        }
        int[] iArr;
        int i2;
        if (!isEnabled(Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            _reportUnexpectedChar(i, "was expecting double-quote to start field name");
        }
        int[] inputCodeUtf8JsNames = CharTypes.getInputCodeUtf8JsNames();
        if (inputCodeUtf8JsNames[i] != 0) {
            _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int i3 = 0;
        int i4 = 0;
        int i5 = i;
        int i6 = 0;
        int[] iArr2 = this._quadBuffer;
        while (true) {
            if (i3 < 4) {
                int i7 = i3 + 1;
                i3 = i5 | (i4 << 8);
                i5 = i6;
                iArr = iArr2;
                i2 = i7;
            } else {
                if (i6 >= iArr2.length) {
                    iArr2 = growArrayBy(iArr2, iArr2.length);
                    this._quadBuffer = iArr2;
                }
                int i8 = i6 + 1;
                iArr2[i6] = i4;
                iArr = iArr2;
                i2 = 1;
                i3 = i5;
                i5 = i8;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in field name");
            }
            i = this._inputBuffer[this._inputPtr] & Util.MASK_8BIT;
            if (inputCodeUtf8JsNames[i] != 0) {
                break;
            }
            this._inputPtr++;
            i4 = i3;
            i3 = i2;
            iArr2 = iArr;
            i6 = i5;
            i5 = i;
        }
        if (i2 > 0) {
            if (i5 >= iArr.length) {
                iArr = growArrayBy(iArr, iArr.length);
                this._quadBuffer = iArr;
            }
            i8 = i5 + 1;
            iArr[i5] = i3;
            i5 = i8;
        }
        Name findName = this._symbols.findName(iArr, i5);
        return findName == null ? addName(iArr, i5, i2) : findName;
    }

    protected void _matchToken(JsonToken jsonToken) {
        byte[] asByteArray = jsonToken.asByteArray();
        int length = asByteArray.length;
        for (int i = 1; i < length; i++) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            if (asByteArray[i] != this._inputBuffer[this._inputPtr]) {
                _reportInvalidToken(jsonToken.asString().substring(0, i), "'null', 'true' or 'false'");
            }
            this._inputPtr++;
        }
    }

    protected final boolean _matchToken(String str, int i) {
        int length = str.length();
        do {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in a value");
            }
            if (this._inputBuffer[this._inputPtr] != str.charAt(i)) {
                _reportInvalidToken(str.substring(0, i), "'null', 'true', 'false' or NaN");
            }
            this._inputPtr++;
            i++;
        } while (i < length);
        if ((this._inputPtr < this._inputEnd || loadMore()) && Character.isJavaIdentifierPart((char) _decodeCharForError(this._inputBuffer[this._inputPtr] & Util.MASK_8BIT))) {
            this._inputPtr++;
            _reportInvalidToken(str.substring(0, i), "'null', 'true', 'false' or NaN");
        }
        return true;
    }

    protected final Name _parseApostropheFieldName() {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(": was expecting closing ''' for name");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = bArr[i] & Util.MASK_8BIT;
        if (i2 == 39) {
            return BytesToNameCanonicalizer.getEmptyName();
        }
        int i3;
        int[] iArr;
        int[] iArr2 = this._quadBuffer;
        int[] iArr3 = sInputCodesLatin1;
        int i4 = 0;
        int i5 = 0;
        i = 0;
        while (i2 != 39) {
            int i6;
            int[] iArr4;
            int i7;
            byte[] bArr2;
            if (!(i2 == 34 || iArr3[i2] == 0)) {
                if (i2 != 92) {
                    _throwUnquotedSpace(i2, User.FN_NAME);
                } else {
                    i2 = _decodeEscaped();
                }
                if (i2 > Opcodes.LAND) {
                    int[] iArr5;
                    if (i4 >= 4) {
                        if (i >= iArr2.length) {
                            iArr2 = growArrayBy(iArr2, iArr2.length);
                            this._quadBuffer = iArr2;
                        }
                        i4 = i + 1;
                        iArr2[i] = i5;
                        i = 0;
                        i5 = i4;
                        i4 = 0;
                    } else {
                        i6 = i4;
                        i4 = i5;
                        i5 = i;
                        i = i6;
                    }
                    if (i2 < Opcodes.ACC_STRICT) {
                        i6 = i + 1;
                        i = (i4 << 8) | ((i2 >> 6) | SmileConstants.TOKEN_PREFIX_SMALL_INT);
                        iArr5 = iArr2;
                        i3 = i6;
                    } else {
                        i4 = (i4 << 8) | ((i2 >> 12) | SmileConstants.TOKEN_PREFIX_MISC_OTHER);
                        i++;
                        if (i >= 4) {
                            if (i5 >= iArr2.length) {
                                iArr2 = growArrayBy(iArr2, iArr2.length);
                                this._quadBuffer = iArr2;
                            }
                            i = i5 + 1;
                            iArr2[i5] = i4;
                            i4 = i;
                            iArr4 = iArr2;
                            i3 = 0;
                            i = 0;
                        } else {
                            i6 = i;
                            i = i4;
                            i4 = i5;
                            iArr4 = iArr2;
                            i3 = i6;
                        }
                        i = (i << 8) | (((i2 >> 6) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                        i3++;
                        i6 = i4;
                        iArr5 = iArr4;
                        i5 = i6;
                    }
                    i7 = i;
                    i = i3;
                    iArr2 = iArr5;
                    i4 = (i2 & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE;
                    if (i >= 4) {
                        i6 = i + 1;
                        i = i4 | (i7 << 8);
                        i4 = i5;
                        iArr4 = iArr2;
                        i3 = i6;
                    } else {
                        if (i5 >= iArr2.length) {
                            iArr2 = growArrayBy(iArr2, iArr2.length);
                            this._quadBuffer = iArr2;
                        }
                        i2 = i5 + 1;
                        iArr2[i5] = i7;
                        iArr4 = iArr2;
                        i3 = 1;
                        i = i4;
                        i4 = i2;
                    }
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        _reportInvalidEOF(" in field name");
                    }
                    bArr2 = this._inputBuffer;
                    i7 = this._inputPtr;
                    this._inputPtr = i7 + 1;
                    i2 = bArr2[i7] & Util.MASK_8BIT;
                    i6 = i3;
                    iArr2 = iArr4;
                    i5 = i;
                    i = i4;
                    i4 = i6;
                }
            }
            i7 = i5;
            i5 = i;
            i = i4;
            i4 = i2;
            if (i >= 4) {
                if (i5 >= iArr2.length) {
                    iArr2 = growArrayBy(iArr2, iArr2.length);
                    this._quadBuffer = iArr2;
                }
                i2 = i5 + 1;
                iArr2[i5] = i7;
                iArr4 = iArr2;
                i3 = 1;
                i = i4;
                i4 = i2;
            } else {
                i6 = i + 1;
                i = i4 | (i7 << 8);
                i4 = i5;
                iArr4 = iArr2;
                i3 = i6;
            }
            _reportInvalidEOF(" in field name");
            bArr2 = this._inputBuffer;
            i7 = this._inputPtr;
            this._inputPtr = i7 + 1;
            i2 = bArr2[i7] & Util.MASK_8BIT;
            i6 = i3;
            iArr2 = iArr4;
            i5 = i;
            i = i4;
            i4 = i6;
        }
        if (i4 > 0) {
            if (i >= iArr2.length) {
                iArr2 = growArrayBy(iArr2, iArr2.length);
                this._quadBuffer = iArr2;
            }
            int i8 = i + 1;
            iArr2[i] = i5;
            i6 = i8;
            iArr = iArr2;
            i3 = i6;
        } else {
            iArr = iArr2;
            i3 = i;
        }
        Name findName = this._symbols.findName(iArr, i3);
        return findName == null ? addName(iArr, i3, i4) : findName;
    }

    protected final Name _parseFieldName(int i) {
        if (i != 34) {
            return _handleUnusualFieldName(i);
        }
        if (this._inputPtr + 9 > this._inputEnd) {
            return slowParseFieldName();
        }
        byte[] bArr = this._inputBuffer;
        int[] iArr = sInputCodesLatin1;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        i2 = bArr[i2] & Util.MASK_8BIT;
        if (iArr[i2] != 0) {
            return i2 == 34 ? BytesToNameCanonicalizer.getEmptyName() : parseFieldName(0, i2, 0);
        } else {
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            i3 = bArr[i3] & Util.MASK_8BIT;
            if (iArr[i3] != 0) {
                return i3 == 34 ? findName(i2, 1) : parseFieldName(i2, i3, 1);
            } else {
                i2 = (i2 << 8) | i3;
                i3 = this._inputPtr;
                this._inputPtr = i3 + 1;
                i3 = bArr[i3] & Util.MASK_8BIT;
                if (iArr[i3] != 0) {
                    return i3 == 34 ? findName(i2, 2) : parseFieldName(i2, i3, 2);
                } else {
                    i2 = (i2 << 8) | i3;
                    i3 = this._inputPtr;
                    this._inputPtr = i3 + 1;
                    i3 = bArr[i3] & Util.MASK_8BIT;
                    if (iArr[i3] != 0) {
                        return i3 == 34 ? findName(i2, 3) : parseFieldName(i2, i3, 3);
                    } else {
                        i2 = (i2 << 8) | i3;
                        i3 = this._inputPtr;
                        this._inputPtr = i3 + 1;
                        int i4 = bArr[i3] & Util.MASK_8BIT;
                        if (iArr[i4] != 0) {
                            return i4 == 34 ? findName(i2, 4) : parseFieldName(i2, i4, 4);
                        } else {
                            this._quad1 = i2;
                            return parseMediumFieldName(i4, iArr);
                        }
                    }
                }
            }
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

    protected void _reportInvalidToken(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder(str);
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char _decodeCharForError = (char) _decodeCharForError(bArr[i]);
            if (!Character.isJavaIdentifierPart(_decodeCharForError)) {
                break;
            }
            this._inputPtr++;
            stringBuilder.append(_decodeCharForError);
        }
        _reportError("Unrecognized token '" + stringBuilder.toString() + "': was expecting " + str2);
    }

    protected final void _skipCR() {
        if ((this._inputPtr < this._inputEnd || loadMore()) && this._inputBuffer[this._inputPtr] == 10) {
            this._inputPtr++;
        }
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    protected final void _skipLF() {
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    protected void _skipString() {
        this._tokenIncomplete = false;
        int[] iArr = sInputCodesUtf8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i = this._inputPtr;
            int i2 = this._inputEnd;
            if (i >= i2) {
                loadMoreGuaranteed();
                i = this._inputPtr;
                i2 = this._inputEnd;
            }
            while (i < i2) {
                int i3 = i + 1;
                i = bArr[i] & Util.MASK_8BIT;
                if (iArr[i] != 0) {
                    this._inputPtr = i3;
                    if (i != 34) {
                        switch (iArr[i]) {
                            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                                _decodeEscaped();
                                break;
                            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                                _skipUtf8_2(i);
                                break;
                            case Type.BYTE /*3*/:
                                _skipUtf8_3(i);
                                break;
                            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                                _skipUtf8_4(i);
                                break;
                            default:
                                if (i >= 32) {
                                    _reportInvalidChar(i);
                                    break;
                                } else {
                                    _throwUnquotedSpace(i, "string value");
                                    break;
                                }
                        }
                    }
                    return;
                }
                i = i3;
            }
            this._inputPtr = i;
        }
    }

    public void close() {
        super.close();
        this._symbols.release();
    }

    public byte[] getBinaryValue(Base64Variant base64Variant) {
        if (this._currToken != JsonToken.VALUE_STRING && (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null)) {
            _reportError("Current token (" + this._currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (this._tokenIncomplete) {
            try {
                this._binaryValue = _decodeBase64(base64Variant);
                this._tokenIncomplete = false;
            } catch (IllegalArgumentException e) {
                throw _constructError("Failed to decode VALUE_STRING as base64 (" + base64Variant + "): " + e.getMessage());
            }
        }
        return this._binaryValue;
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public String getText() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_STRING) {
            return _getText2(jsonToken);
        }
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
        return this._textBuffer.contentsAsString();
    }

    public char[] getTextCharacters() {
        if (this._currToken == null) {
            return null;
        }
        switch (C35781.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
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
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                    break;
                }
                break;
            case Type.BYTE /*3*/:
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                break;
            default:
                return this._currToken.asCharArray();
        }
        return this._textBuffer.getTextBuffer();
    }

    public int getTextLength() {
        if (this._currToken == null) {
            return 0;
        }
        switch (C35781.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return this._parsingContext.getCurrentName().length();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                    break;
                }
                break;
            case Type.BYTE /*3*/:
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                break;
            default:
                return this._currToken.asCharArray().length;
        }
        return this._textBuffer.size();
    }

    public int getTextOffset() {
        if (this._currToken == null) {
            return 0;
        }
        switch (C35781.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                    break;
                }
                break;
            case Type.BYTE /*3*/:
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                break;
            default:
                return 0;
        }
        return this._textBuffer.getTextOffset();
    }

    public JsonToken nextToken() {
        if (this._currToken == JsonToken.FIELD_NAME) {
            return _nextAfterName();
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return null;
        }
        this._tokenInputTotal = (this._currInputProcessed + ((long) this._inputPtr)) - 1;
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = (this._inputPtr - this._currInputRowStart) - 1;
        this._binaryValue = null;
        JsonToken jsonToken;
        if (_skipWSOrEnd == 93) {
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, '}');
            }
            this._parsingContext = this._parsingContext.getParent();
            jsonToken = JsonToken.END_ARRAY;
            this._currToken = jsonToken;
            return jsonToken;
        } else if (_skipWSOrEnd == Opcodes.LUSHR) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, ']');
            }
            this._parsingContext = this._parsingContext.getParent();
            jsonToken = JsonToken.END_OBJECT;
            this._currToken = jsonToken;
            return jsonToken;
        } else {
            if (this._parsingContext.expectComma()) {
                if (_skipWSOrEnd != 44) {
                    _reportUnexpectedChar(_skipWSOrEnd, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries");
                }
                _skipWSOrEnd = _skipWS();
            }
            if (!this._parsingContext.inObject()) {
                return _nextTokenNotInObject(_skipWSOrEnd);
            }
            this._parsingContext.setCurrentName(_parseFieldName(_skipWSOrEnd).getName());
            this._currToken = JsonToken.FIELD_NAME;
            _skipWSOrEnd = _skipWS();
            if (_skipWSOrEnd != 58) {
                _reportUnexpectedChar(_skipWSOrEnd, "was expecting a colon to separate field name and value");
            }
            _skipWSOrEnd = _skipWS();
            if (_skipWSOrEnd == 34) {
                this._tokenIncomplete = true;
                this._nextToken = JsonToken.VALUE_STRING;
                return this._currToken;
            }
            switch (_skipWSOrEnd) {
                case GameAppOperation.SHARE_PRIZE_TITLE_MAX_LENGTH /*45*/:
                case SmileConstants.TOKEN_PREFIX_KEY_SHARED_LONG /*48*/:
                case Opcodes.V1_5 /*49*/:
                case Opcodes.V1_6 /*50*/:
                case Opcodes.V1_7 /*51*/:
                case Opcodes.CALOAD /*52*/:
                case Opcodes.SALOAD /*53*/:
                case Opcodes.ISTORE /*54*/:
                case Opcodes.LSTORE /*55*/:
                case SmileConstants.MAX_SHORT_NAME_UNICODE_BYTES /*56*/:
                case Opcodes.DSTORE /*57*/:
                    jsonToken = parseNumberText(_skipWSOrEnd);
                    break;
                case Opcodes.DUP_X2 /*91*/:
                    jsonToken = JsonToken.START_ARRAY;
                    break;
                case Opcodes.DUP2_X1 /*93*/:
                case Opcodes.LUSHR /*125*/:
                    _reportUnexpectedChar(_skipWSOrEnd, "expected a value");
                    break;
                case Opcodes.FSUB /*102*/:
                    _matchToken(JsonToken.VALUE_FALSE);
                    jsonToken = JsonToken.VALUE_FALSE;
                    break;
                case Opcodes.FDIV /*110*/:
                    _matchToken(JsonToken.VALUE_NULL);
                    jsonToken = JsonToken.VALUE_NULL;
                    break;
                case Opcodes.INEG /*116*/:
                    break;
                case Opcodes.LSHR /*123*/:
                    jsonToken = JsonToken.START_OBJECT;
                    break;
                default:
                    jsonToken = _handleUnexpectedValue(_skipWSOrEnd);
                    break;
            }
            _matchToken(JsonToken.VALUE_TRUE);
            jsonToken = JsonToken.VALUE_TRUE;
            this._nextToken = jsonToken;
            return this._currToken;
        }
    }

    protected Name parseEscapedFieldName(int[] iArr, int i, int i2, int i3, int i4) {
        int[] iArr2 = sInputCodesLatin1;
        while (true) {
            int[] iArr3;
            int i5;
            int i6;
            int i7;
            byte[] bArr;
            if (iArr2[i3] != 0) {
                if (i3 == 34) {
                    break;
                }
                if (i3 != 92) {
                    _throwUnquotedSpace(i3, User.FN_NAME);
                } else {
                    i3 = _decodeEscaped();
                }
                if (i3 > Opcodes.LAND) {
                    int i8;
                    int[] iArr4;
                    if (i4 >= 4) {
                        if (i >= iArr.length) {
                            iArr = growArrayBy(iArr, iArr.length);
                            this._quadBuffer = iArr;
                        }
                        i8 = i + 1;
                        iArr[i] = i2;
                        i4 = 0;
                        i2 = 0;
                        iArr3 = iArr;
                    } else {
                        i8 = i;
                        iArr3 = iArr;
                    }
                    if (i3 < Opcodes.ACC_STRICT) {
                        i5 = ((i3 >> 6) | SmileConstants.TOKEN_PREFIX_SMALL_INT) | (i2 << 8);
                        iArr4 = iArr3;
                        i6 = i4 + 1;
                    } else {
                        int[] iArr5;
                        int i9;
                        i7 = ((i3 >> 12) | SmileConstants.TOKEN_PREFIX_MISC_OTHER) | (i2 << 8);
                        i5 = i4 + 1;
                        if (i5 >= 4) {
                            if (i8 >= iArr3.length) {
                                iArr3 = growArrayBy(iArr3, iArr3.length);
                                this._quadBuffer = iArr3;
                            }
                            i5 = i8 + 1;
                            iArr3[i8] = i7;
                            i7 = i5;
                            iArr5 = iArr3;
                            i6 = 0;
                            i5 = 0;
                        } else {
                            i9 = i5;
                            i5 = i7;
                            i7 = i8;
                            iArr5 = iArr3;
                            i6 = i9;
                        }
                        i5 = (i5 << 8) | (((i3 >> 6) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                        i6++;
                        i9 = i7;
                        iArr4 = iArr5;
                        i8 = i9;
                    }
                    i2 = (i3 & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE;
                    i4 = i6;
                    i = i8;
                    iArr3 = iArr4;
                    i7 = i5;
                    if (i4 >= 4) {
                        i4++;
                        i2 |= i7 << 8;
                        iArr = iArr3;
                    } else {
                        if (i >= iArr3.length) {
                            iArr3 = growArrayBy(iArr3, iArr3.length);
                            this._quadBuffer = iArr3;
                        }
                        i5 = i + 1;
                        iArr3[i] = i7;
                        i4 = 1;
                        i = i5;
                        iArr = iArr3;
                    }
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        _reportInvalidEOF(" in field name");
                    }
                    bArr = this._inputBuffer;
                    i5 = this._inputPtr;
                    this._inputPtr = i5 + 1;
                    i3 = bArr[i5] & Util.MASK_8BIT;
                }
            }
            i7 = i2;
            iArr3 = iArr;
            i2 = i3;
            if (i4 >= 4) {
                if (i >= iArr3.length) {
                    iArr3 = growArrayBy(iArr3, iArr3.length);
                    this._quadBuffer = iArr3;
                }
                i5 = i + 1;
                iArr3[i] = i7;
                i4 = 1;
                i = i5;
                iArr = iArr3;
            } else {
                i4++;
                i2 |= i7 << 8;
                iArr = iArr3;
            }
            _reportInvalidEOF(" in field name");
            bArr = this._inputBuffer;
            i5 = this._inputPtr;
            this._inputPtr = i5 + 1;
            i3 = bArr[i5] & Util.MASK_8BIT;
        }
        if (i4 > 0) {
            if (i >= iArr.length) {
                iArr = growArrayBy(iArr, iArr.length);
                this._quadBuffer = iArr;
            }
            i6 = i + 1;
            iArr[i] = i2;
            i = i6;
        }
        Name findName = this._symbols.findName(iArr, i);
        return findName == null ? addName(iArr, i, i4) : findName;
    }

    protected Name parseLongFieldName(int i) {
        int[] iArr = sInputCodesLatin1;
        int i2 = 2;
        int i3 = i;
        while (this._inputEnd - this._inputPtr >= 4) {
            byte[] bArr = this._inputBuffer;
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            int i5 = bArr[i4] & Util.MASK_8BIT;
            if (iArr[i5] != 0) {
                return i5 == 34 ? findName(this._quadBuffer, i2, i3, 1) : parseEscapedFieldName(this._quadBuffer, i2, i3, i5, 1);
            } else {
                i4 = (i3 << 8) | i5;
                bArr = this._inputBuffer;
                i3 = this._inputPtr;
                this._inputPtr = i3 + 1;
                i3 = bArr[i3] & Util.MASK_8BIT;
                if (iArr[i3] != 0) {
                    return i3 == 34 ? findName(this._quadBuffer, i2, i4, 2) : parseEscapedFieldName(this._quadBuffer, i2, i4, i3, 2);
                } else {
                    i4 = (i4 << 8) | i3;
                    bArr = this._inputBuffer;
                    i3 = this._inputPtr;
                    this._inputPtr = i3 + 1;
                    i3 = bArr[i3] & Util.MASK_8BIT;
                    if (iArr[i3] != 0) {
                        return i3 == 34 ? findName(this._quadBuffer, i2, i4, 3) : parseEscapedFieldName(this._quadBuffer, i2, i4, i3, 3);
                    } else {
                        i4 = (i4 << 8) | i3;
                        bArr = this._inputBuffer;
                        i3 = this._inputPtr;
                        this._inputPtr = i3 + 1;
                        i3 = bArr[i3] & Util.MASK_8BIT;
                        if (iArr[i3] != 0) {
                            return i3 == 34 ? findName(this._quadBuffer, i2, i4, 4) : parseEscapedFieldName(this._quadBuffer, i2, i4, i3, 4);
                        } else {
                            if (i2 >= this._quadBuffer.length) {
                                this._quadBuffer = growArrayBy(this._quadBuffer, i2);
                            }
                            int i6 = i2 + 1;
                            this._quadBuffer[i2] = i4;
                            i2 = i6;
                        }
                    }
                }
            }
        }
        return parseEscapedFieldName(this._quadBuffer, i2, 0, i3, 0);
    }

    protected final Name parseMediumFieldName(int i, int[] iArr) {
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int i3 = bArr[i2] & Util.MASK_8BIT;
        if (iArr[i3] != 0) {
            return i3 == 34 ? findName(this._quad1, i, 1) : parseFieldName(this._quad1, i, i3, 1);
        } else {
            i3 |= i << 8;
            byte[] bArr2 = this._inputBuffer;
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            i2 = bArr2[i4] & Util.MASK_8BIT;
            if (iArr[i2] != 0) {
                return i2 == 34 ? findName(this._quad1, i3, 2) : parseFieldName(this._quad1, i3, i2, 2);
            } else {
                i3 = (i3 << 8) | i2;
                bArr2 = this._inputBuffer;
                i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                i2 = bArr2[i4] & Util.MASK_8BIT;
                if (iArr[i2] != 0) {
                    return i2 == 34 ? findName(this._quad1, i3, 3) : parseFieldName(this._quad1, i3, i2, 3);
                } else {
                    i3 = (i3 << 8) | i2;
                    bArr2 = this._inputBuffer;
                    i4 = this._inputPtr;
                    this._inputPtr = i4 + 1;
                    i2 = bArr2[i4] & Util.MASK_8BIT;
                    if (iArr[i2] != 0) {
                        return i2 == 34 ? findName(this._quad1, i3, 4) : parseFieldName(this._quad1, i3, i2, 4);
                    } else {
                        this._quadBuffer[0] = this._quad1;
                        this._quadBuffer[1] = i3;
                        return parseLongFieldName(i2);
                    }
                }
            }
        }
    }

    protected final JsonToken parseNumberText(int i) {
        int i2;
        int i3;
        int i4;
        int i5 = 1;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        boolean z = i == 45;
        if (z) {
            emptyAndGetCurrentSegment[0] = SignatureVisitor.SUPER;
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            i3 = bArr[i2] & Util.MASK_8BIT;
            if (i3 < 48 || i3 > 57) {
                return _handleInvalidNumberStart(i3, true);
            }
            i4 = 1;
        } else {
            i4 = 0;
            i3 = i;
        }
        if (i3 == 48) {
            i3 = _verifyNoLeadingZeroes();
        }
        i2 = i4 + 1;
        emptyAndGetCurrentSegment[i4] = (char) i3;
        i3 = this._inputPtr + emptyAndGetCurrentSegment.length;
        if (i3 > this._inputEnd) {
            i3 = this._inputEnd;
        }
        while (this._inputPtr < i3) {
            byte[] bArr2 = this._inputBuffer;
            int i6 = this._inputPtr;
            this._inputPtr = i6 + 1;
            i4 = bArr2[i6] & Util.MASK_8BIT;
            if (i4 >= 48 && i4 <= 57) {
                i5++;
                i6 = i2 + 1;
                emptyAndGetCurrentSegment[i2] = (char) i4;
                i2 = i6;
            } else if (i4 == 46 || i4 == Opcodes.LSUB || i4 == 69) {
                return _parseFloatText(emptyAndGetCurrentSegment, i2, i4, z, i5);
            } else {
                this._inputPtr--;
                this._textBuffer.setCurrentLength(i2);
                return resetInt(z, i5);
            }
        }
        return _parserNumber2(emptyAndGetCurrentSegment, i2, z, i5);
    }

    protected IllegalArgumentException reportInvalidChar(Base64Variant base64Variant, int i, int i2) {
        return reportInvalidChar(base64Variant, i, i2, null);
    }

    protected IllegalArgumentException reportInvalidChar(Base64Variant base64Variant, int i, int i2, String str) {
        r0 = i <= 32 ? "Illegal white space character (code 0x" + Integer.toHexString(i) + ") as character #" + (i2 + 1) + " of 4-char base64 unit: can only used between units" : base64Variant.usesPaddingChar(i) ? "Unexpected padding character ('" + base64Variant.getPaddingChar() + "') as character #" + (i2 + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character" : (!Character.isDefined(i) || Character.isISOControl(i)) ? "Illegal character (code 0x" + Integer.toHexString(i) + ") in base64 content" : "Illegal character '" + ((char) i) + "' (code 0x" + Integer.toHexString(i) + ") in base64 content";
        if (str != null) {
            r0 = r0 + ": " + str;
        }
        return new IllegalArgumentException(r0);
    }

    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    protected Name slowParseFieldName() {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(": was expecting closing '\"' for name");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = bArr[i] & Util.MASK_8BIT;
        return i2 == 34 ? BytesToNameCanonicalizer.getEmptyName() : parseEscapedFieldName(this._quadBuffer, 0, 0, i2, 0);
    }
}
