package org.codehaus.jackson.impl;

import java.io.Reader;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public abstract class ReaderBasedNumericParser extends ReaderBasedParserBase {
    public ReaderBasedNumericParser(IOContext iOContext, int i, Reader reader) {
        super(iOContext, i, reader);
    }

    private final char _verifyNoLeadingZeroes() {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            return '0';
        }
        char c = this._inputBuffer[this._inputPtr];
        if (c < '0' || c > '9') {
            return '0';
        }
        if (!isEnabled(Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            reportInvalidNumber("Leading zeroes not allowed");
        }
        this._inputPtr++;
        if (c != '0') {
            return c;
        }
        do {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                return c;
            }
            c = this._inputBuffer[this._inputPtr];
            if (c < '0' || c > '9') {
                return '0';
            }
            this._inputPtr++;
        } while (c == '0');
        return c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final org.codehaus.jackson.JsonToken parseNumberText2(boolean r15) {
        /*
        r14 = this;
        r10 = 45;
        r12 = 57;
        r11 = 48;
        r1 = 1;
        r2 = 0;
        r0 = r14._textBuffer;
        r4 = r0.emptyAndGetCurrentSegment();
        if (r15 == 0) goto L_0x01a0;
    L_0x0010:
        r4[r2] = r10;
        r0 = r1;
    L_0x0013:
        r3 = r14._inputPtr;
        r5 = r14._inputEnd;
        if (r3 >= r5) goto L_0x0126;
    L_0x0019:
        r3 = r14._inputBuffer;
        r5 = r14._inputPtr;
        r6 = r5 + 1;
        r14._inputPtr = r6;
        r3 = r3[r5];
    L_0x0023:
        if (r3 != r11) goto L_0x0029;
    L_0x0025:
        r3 = r14._verifyNoLeadingZeroes();
    L_0x0029:
        r5 = r2;
        r13 = r3;
        r3 = r4;
        r4 = r13;
    L_0x002d:
        if (r4 < r11) goto L_0x0199;
    L_0x002f:
        if (r4 > r12) goto L_0x0199;
    L_0x0031:
        r5 = r5 + 1;
        r6 = r3.length;
        if (r0 < r6) goto L_0x003e;
    L_0x0036:
        r0 = r14._textBuffer;
        r0 = r0.finishCurrentSegment();
        r3 = r0;
        r0 = r2;
    L_0x003e:
        r6 = r0 + 1;
        r3[r0] = r4;
        r0 = r14._inputPtr;
        r4 = r14._inputEnd;
        if (r0 < r4) goto L_0x012e;
    L_0x0048:
        r0 = r14.loadMore();
        if (r0 != 0) goto L_0x012e;
    L_0x004e:
        r7 = r1;
        r0 = r2;
        r9 = r5;
        r4 = r3;
        r5 = r6;
    L_0x0053:
        if (r9 != 0) goto L_0x0075;
    L_0x0055:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r6 = "Missing integer part (next char ";
        r3 = r3.append(r6);
        r6 = org.codehaus.jackson.impl.JsonParserMinimalBase._getCharDesc(r0);
        r3 = r3.append(r6);
        r6 = ")";
        r3 = r3.append(r6);
        r3 = r3.toString();
        r14.reportInvalidNumber(r3);
    L_0x0075:
        r3 = 46;
        if (r0 != r3) goto L_0x0192;
    L_0x0079:
        r3 = r5 + 1;
        r4[r5] = r0;
        r5 = r4;
        r4 = r3;
        r3 = r0;
        r0 = r2;
    L_0x0081:
        r6 = r14._inputPtr;
        r8 = r14._inputEnd;
        if (r6 < r8) goto L_0x013b;
    L_0x0087:
        r6 = r14.loadMore();
        if (r6 != 0) goto L_0x013b;
    L_0x008d:
        r6 = r3;
        r3 = r1;
    L_0x008f:
        if (r0 != 0) goto L_0x0096;
    L_0x0091:
        r7 = "Decimal point not followed by a digit";
        r14.reportUnexpectedNumberChar(r6, r7);
    L_0x0096:
        r8 = r0;
        r0 = r4;
        r13 = r3;
        r3 = r5;
        r5 = r13;
    L_0x009b:
        r4 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r6 == r4) goto L_0x00a3;
    L_0x009f:
        r4 = 69;
        if (r6 != r4) goto L_0x0189;
    L_0x00a3:
        r4 = r3.length;
        if (r0 < r4) goto L_0x00ae;
    L_0x00a6:
        r0 = r14._textBuffer;
        r0 = r0.finishCurrentSegment();
        r3 = r0;
        r0 = r2;
    L_0x00ae:
        r4 = r0 + 1;
        r3[r0] = r6;
        r0 = r14._inputPtr;
        r6 = r14._inputEnd;
        if (r0 >= r6) goto L_0x015f;
    L_0x00b8:
        r0 = r14._inputBuffer;
        r6 = r14._inputPtr;
        r7 = r6 + 1;
        r14._inputPtr = r7;
        r6 = r0[r6];
    L_0x00c2:
        if (r6 == r10) goto L_0x00c8;
    L_0x00c4:
        r0 = 43;
        if (r6 != r0) goto L_0x0184;
    L_0x00c8:
        r0 = r3.length;
        if (r4 < r0) goto L_0x0181;
    L_0x00cb:
        r0 = r14._textBuffer;
        r3 = r0.finishCurrentSegment();
        r0 = r2;
    L_0x00d2:
        r4 = r0 + 1;
        r3[r0] = r6;
        r0 = r14._inputPtr;
        r6 = r14._inputEnd;
        if (r0 >= r6) goto L_0x0167;
    L_0x00dc:
        r0 = r14._inputBuffer;
        r6 = r14._inputPtr;
        r7 = r6 + 1;
        r14._inputPtr = r7;
        r0 = r0[r6];
    L_0x00e6:
        r7 = r0;
        r0 = r4;
        r4 = r2;
    L_0x00e9:
        if (r7 > r12) goto L_0x017d;
    L_0x00eb:
        if (r7 < r11) goto L_0x017d;
    L_0x00ed:
        r4 = r4 + 1;
        r6 = r3.length;
        if (r0 < r6) goto L_0x00fa;
    L_0x00f2:
        r0 = r14._textBuffer;
        r0 = r0.finishCurrentSegment();
        r3 = r0;
        r0 = r2;
    L_0x00fa:
        r6 = r0 + 1;
        r3[r0] = r7;
        r0 = r14._inputPtr;
        r10 = r14._inputEnd;
        if (r0 < r10) goto L_0x016f;
    L_0x0104:
        r0 = r14.loadMore();
        if (r0 != 0) goto L_0x016f;
    L_0x010a:
        r2 = r4;
        r0 = r1;
        r1 = r6;
    L_0x010d:
        if (r2 != 0) goto L_0x0114;
    L_0x010f:
        r3 = "Exponent indicator not followed by a digit";
        r14.reportUnexpectedNumberChar(r7, r3);
    L_0x0114:
        if (r0 != 0) goto L_0x011c;
    L_0x0116:
        r0 = r14._inputPtr;
        r0 = r0 + -1;
        r14._inputPtr = r0;
    L_0x011c:
        r0 = r14._textBuffer;
        r0.setCurrentLength(r1);
        r0 = r14.reset(r15, r9, r8, r2);
        return r0;
    L_0x0126:
        r3 = "No digit following minus sign";
        r3 = r14.getNextChar(r3);
        goto L_0x0023;
    L_0x012e:
        r0 = r14._inputBuffer;
        r4 = r14._inputPtr;
        r7 = r4 + 1;
        r14._inputPtr = r7;
        r4 = r0[r4];
        r0 = r6;
        goto L_0x002d;
    L_0x013b:
        r3 = r14._inputBuffer;
        r6 = r14._inputPtr;
        r8 = r6 + 1;
        r14._inputPtr = r8;
        r3 = r3[r6];
        if (r3 < r11) goto L_0x018e;
    L_0x0147:
        if (r3 <= r12) goto L_0x014d;
    L_0x0149:
        r6 = r3;
        r3 = r7;
        goto L_0x008f;
    L_0x014d:
        r0 = r0 + 1;
        r6 = r5.length;
        if (r4 < r6) goto L_0x018c;
    L_0x0152:
        r4 = r14._textBuffer;
        r5 = r4.finishCurrentSegment();
        r6 = r2;
    L_0x0159:
        r4 = r6 + 1;
        r5[r6] = r3;
        goto L_0x0081;
    L_0x015f:
        r0 = "expected a digit for number exponent";
        r6 = r14.getNextChar(r0);
        goto L_0x00c2;
    L_0x0167:
        r0 = "expected a digit for number exponent";
        r0 = r14.getNextChar(r0);
        goto L_0x00e6;
    L_0x016f:
        r0 = r14._inputBuffer;
        r7 = r14._inputPtr;
        r10 = r7 + 1;
        r14._inputPtr = r10;
        r0 = r0[r7];
        r7 = r0;
        r0 = r6;
        goto L_0x00e9;
    L_0x017d:
        r2 = r4;
        r1 = r0;
        r0 = r5;
        goto L_0x010d;
    L_0x0181:
        r0 = r4;
        goto L_0x00d2;
    L_0x0184:
        r7 = r6;
        r0 = r4;
        r4 = r2;
        goto L_0x00e9;
    L_0x0189:
        r1 = r0;
        r0 = r5;
        goto L_0x0114;
    L_0x018c:
        r6 = r4;
        goto L_0x0159;
    L_0x018e:
        r6 = r3;
        r3 = r7;
        goto L_0x008f;
    L_0x0192:
        r8 = r2;
        r6 = r0;
        r3 = r4;
        r0 = r5;
        r5 = r7;
        goto L_0x009b;
    L_0x0199:
        r7 = r2;
        r9 = r5;
        r5 = r0;
        r0 = r4;
        r4 = r3;
        goto L_0x0053;
    L_0x01a0:
        r0 = r2;
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.ReaderBasedNumericParser.parseNumberText2(boolean):org.codehaus.jackson.JsonToken");
    }

    protected JsonToken _handleInvalidNumberStart(int i, boolean z) {
        double d = Double.NEGATIVE_INFINITY;
        if (i == 73) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOFInValue();
            }
            char[] cArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            i = cArr[i2];
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

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final org.codehaus.jackson.JsonToken parseNumberText(int r14) {
        /*
        r13 = this;
        r11 = 45;
        r1 = 1;
        r2 = 0;
        r10 = 57;
        r9 = 48;
        if (r14 != r11) goto L_0x0022;
    L_0x000a:
        r0 = r1;
    L_0x000b:
        r4 = r13._inputPtr;
        r5 = r4 + -1;
        r7 = r13._inputEnd;
        if (r0 == 0) goto L_0x0035;
    L_0x0013:
        r3 = r13._inputEnd;
        if (r4 < r3) goto L_0x0024;
    L_0x0017:
        if (r0 == 0) goto L_0x00b4;
    L_0x0019:
        r1 = r5 + 1;
    L_0x001b:
        r13._inputPtr = r1;
        r0 = r13.parseNumberText2(r0);
    L_0x0021:
        return r0;
    L_0x0022:
        r0 = r2;
        goto L_0x000b;
    L_0x0024:
        r6 = r13._inputBuffer;
        r3 = r4 + 1;
        r14 = r6[r4];
        if (r14 > r10) goto L_0x002e;
    L_0x002c:
        if (r14 >= r9) goto L_0x0036;
    L_0x002e:
        r13._inputPtr = r3;
        r0 = r13._handleInvalidNumberStart(r14, r1);
        goto L_0x0021;
    L_0x0035:
        r3 = r4;
    L_0x0036:
        if (r14 == r9) goto L_0x0017;
    L_0x0038:
        r4 = r13._inputEnd;
        if (r3 >= r4) goto L_0x0017;
    L_0x003c:
        r6 = r13._inputBuffer;
        r4 = r3 + 1;
        r3 = r6[r3];
        if (r3 < r9) goto L_0x0046;
    L_0x0044:
        if (r3 <= r10) goto L_0x0093;
    L_0x0046:
        r6 = 46;
        if (r3 != r6) goto L_0x00bb;
    L_0x004a:
        r3 = r2;
        r6 = r4;
    L_0x004c:
        if (r6 >= r7) goto L_0x0017;
    L_0x004e:
        r8 = r13._inputBuffer;
        r4 = r6 + 1;
        r6 = r8[r6];
        if (r6 < r9) goto L_0x0058;
    L_0x0056:
        if (r6 <= r10) goto L_0x0097;
    L_0x0058:
        if (r3 != 0) goto L_0x005f;
    L_0x005a:
        r8 = "Decimal point not followed by a digit";
        r13.reportUnexpectedNumberChar(r6, r8);
    L_0x005f:
        r12 = r3;
        r3 = r4;
        r4 = r6;
        r6 = r12;
    L_0x0063:
        r8 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r4 == r8) goto L_0x006b;
    L_0x0067:
        r8 = 69;
        if (r4 != r8) goto L_0x00a2;
    L_0x006b:
        if (r3 >= r7) goto L_0x0017;
    L_0x006d:
        r8 = r13._inputBuffer;
        r4 = r3 + 1;
        r3 = r8[r3];
        if (r3 == r11) goto L_0x0079;
    L_0x0075:
        r8 = 43;
        if (r3 != r8) goto L_0x00b7;
    L_0x0079:
        if (r4 >= r7) goto L_0x0017;
    L_0x007b:
        r8 = r13._inputBuffer;
        r3 = r4 + 1;
        r4 = r8[r4];
    L_0x0081:
        if (r4 > r10) goto L_0x009b;
    L_0x0083:
        if (r4 < r9) goto L_0x009b;
    L_0x0085:
        r2 = r2 + 1;
        if (r3 >= r7) goto L_0x0017;
    L_0x0089:
        r8 = r13._inputBuffer;
        r4 = r3 + 1;
        r3 = r8[r3];
        r12 = r4;
        r4 = r3;
        r3 = r12;
        goto L_0x0081;
    L_0x0093:
        r1 = r1 + 1;
        r3 = r4;
        goto L_0x0038;
    L_0x0097:
        r3 = r3 + 1;
        r6 = r4;
        goto L_0x004c;
    L_0x009b:
        if (r2 != 0) goto L_0x00a2;
    L_0x009d:
        r7 = "Exponent indicator not followed by a digit";
        r13.reportUnexpectedNumberChar(r4, r7);
    L_0x00a2:
        r3 = r3 + -1;
        r13._inputPtr = r3;
        r3 = r3 - r5;
        r4 = r13._textBuffer;
        r7 = r13._inputBuffer;
        r4.resetWithShared(r7, r5, r3);
        r0 = r13.reset(r0, r1, r6, r2);
        goto L_0x0021;
    L_0x00b4:
        r1 = r5;
        goto L_0x001b;
    L_0x00b7:
        r12 = r4;
        r4 = r3;
        r3 = r12;
        goto L_0x0081;
    L_0x00bb:
        r6 = r2;
        r12 = r4;
        r4 = r3;
        r3 = r12;
        goto L_0x0063;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.impl.ReaderBasedNumericParser.parseNumberText(int):org.codehaus.jackson.JsonToken");
    }
}
