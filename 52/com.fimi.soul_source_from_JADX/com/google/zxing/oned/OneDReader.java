package com.google.zxing.oned;

import com.fimi.soul.view.photodraweeview.C2020f;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

public abstract class OneDReader implements Reader {
    protected static final int INTEGER_MATH_SHIFT = 8;
    protected static final int PATTERN_MATCH_RESULT_SCALE_FACTOR = 256;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.zxing.Result doDecode(com.google.zxing.BinaryBitmap r18, java.util.Hashtable r19) {
        /*
        r17 = this;
        r8 = r18.getWidth();
        r2 = r18.getHeight();
        r4 = new com.google.zxing.common.BitArray;
        r4.<init>(r8);
        r9 = r2 >> 1;
        if (r19 == 0) goto L_0x0049;
    L_0x0011:
        r1 = com.google.zxing.DecodeHintType.TRY_HARDER;
        r0 = r19;
        r1 = r0.containsKey(r1);
        if (r1 == 0) goto L_0x0049;
    L_0x001b:
        r1 = 1;
        r3 = r1;
    L_0x001d:
        r5 = 1;
        if (r3 == 0) goto L_0x004c;
    L_0x0020:
        r1 = 8;
    L_0x0022:
        r1 = r2 >> r1;
        r10 = java.lang.Math.max(r5, r1);
        if (r3 == 0) goto L_0x004e;
    L_0x002a:
        r1 = r2;
    L_0x002b:
        r3 = 0;
        r7 = r3;
        r3 = r4;
        r4 = r19;
    L_0x0030:
        if (r7 >= r1) goto L_0x0044;
    L_0x0032:
        r5 = r7 + 1;
        r5 = r5 >> 1;
        r6 = r7 & 1;
        if (r6 != 0) goto L_0x0051;
    L_0x003a:
        r6 = 1;
    L_0x003b:
        if (r6 == 0) goto L_0x0053;
    L_0x003d:
        r5 = r5 * r10;
        r11 = r9 + r5;
        if (r11 < 0) goto L_0x0044;
    L_0x0042:
        if (r11 < r2) goto L_0x0055;
    L_0x0044:
        r1 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r1;
    L_0x0049:
        r1 = 0;
        r3 = r1;
        goto L_0x001d;
    L_0x004c:
        r1 = 5;
        goto L_0x0022;
    L_0x004e:
        r1 = 15;
        goto L_0x002b;
    L_0x0051:
        r6 = 0;
        goto L_0x003b;
    L_0x0053:
        r5 = -r5;
        goto L_0x003d;
    L_0x0055:
        r0 = r18;
        r3 = r0.getBlackRow(r11, r3);	 Catch:{ NotFoundException -> 0x00f2 }
        r5 = 0;
        r6 = r5;
    L_0x005d:
        r5 = 2;
        if (r6 >= r5) goto L_0x00f3;
    L_0x0060:
        r5 = 1;
        if (r6 != r5) goto L_0x0094;
    L_0x0063:
        r3.reverse();
        if (r4 == 0) goto L_0x0094;
    L_0x0068:
        r5 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK;
        r5 = r4.containsKey(r5);
        if (r5 == 0) goto L_0x0094;
    L_0x0070:
        r5 = new java.util.Hashtable;
        r5.<init>();
        r12 = r4.keys();
    L_0x0079:
        r13 = r12.hasMoreElements();
        if (r13 == 0) goto L_0x0093;
    L_0x007f:
        r13 = r12.nextElement();
        r14 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK;
        r14 = r13.equals(r14);
        if (r14 != 0) goto L_0x0079;
    L_0x008b:
        r14 = r4.get(r13);
        r5.put(r13, r14);
        goto L_0x0079;
    L_0x0093:
        r4 = r5;
    L_0x0094:
        r0 = r17;
        r5 = r0.decodeRow(r11, r3, r4);	 Catch:{ ReaderException -> 0x00ec }
        r12 = 1;
        if (r6 != r12) goto L_0x00eb;
    L_0x009d:
        r12 = com.google.zxing.ResultMetadataType.ORIENTATION;	 Catch:{ ReaderException -> 0x00ec }
        r13 = new java.lang.Integer;	 Catch:{ ReaderException -> 0x00ec }
        r14 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;
        r13.<init>(r14);	 Catch:{ ReaderException -> 0x00ec }
        r5.putMetadata(r12, r13);	 Catch:{ ReaderException -> 0x00ec }
        r12 = r5.getResultPoints();	 Catch:{ ReaderException -> 0x00ec }
        r13 = 0;
        r14 = new com.google.zxing.ResultPoint;	 Catch:{ ReaderException -> 0x00ec }
        r15 = (float) r8;	 Catch:{ ReaderException -> 0x00ec }
        r16 = 0;
        r16 = r12[r16];	 Catch:{ ReaderException -> 0x00ec }
        r16 = r16.getX();	 Catch:{ ReaderException -> 0x00ec }
        r15 = r15 - r16;
        r16 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r15 = r15 - r16;
        r16 = 0;
        r16 = r12[r16];	 Catch:{ ReaderException -> 0x00ec }
        r16 = r16.getY();	 Catch:{ ReaderException -> 0x00ec }
        r14.<init>(r15, r16);	 Catch:{ ReaderException -> 0x00ec }
        r12[r13] = r14;	 Catch:{ ReaderException -> 0x00ec }
        r13 = 1;
        r14 = new com.google.zxing.ResultPoint;	 Catch:{ ReaderException -> 0x00ec }
        r15 = (float) r8;	 Catch:{ ReaderException -> 0x00ec }
        r16 = 1;
        r16 = r12[r16];	 Catch:{ ReaderException -> 0x00ec }
        r16 = r16.getX();	 Catch:{ ReaderException -> 0x00ec }
        r15 = r15 - r16;
        r16 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r15 = r15 - r16;
        r16 = 1;
        r16 = r12[r16];	 Catch:{ ReaderException -> 0x00ec }
        r16 = r16.getY();	 Catch:{ ReaderException -> 0x00ec }
        r14.<init>(r15, r16);	 Catch:{ ReaderException -> 0x00ec }
        r12[r13] = r14;	 Catch:{ ReaderException -> 0x00ec }
    L_0x00eb:
        return r5;
    L_0x00ec:
        r5 = move-exception;
        r5 = r6 + 1;
        r6 = r5;
        goto L_0x005d;
    L_0x00f2:
        r5 = move-exception;
    L_0x00f3:
        r5 = r7 + 1;
        r7 = r5;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.OneDReader.doDecode(com.google.zxing.BinaryBitmap, java.util.Hashtable):com.google.zxing.Result");
    }

    protected static int patternMatchVariance(int[] iArr, int[] iArr2, int i) {
        int i2;
        int length = iArr.length;
        int i3 = 0;
        int i4 = 0;
        for (i2 = 0; i2 < length; i2++) {
            i4 += iArr[i2];
            i3 += iArr2[i2];
        }
        if (i4 < i3) {
            return Integer.MAX_VALUE;
        }
        int i5 = (i4 << INTEGER_MATH_SHIFT) / i3;
        int i6 = (i * i5) >> INTEGER_MATH_SHIFT;
        i3 = 0;
        for (i2 = 0; i2 < length; i2++) {
            int i7 = iArr[i2] << INTEGER_MATH_SHIFT;
            int i8 = iArr2[i2] * i5;
            i7 = i7 > i8 ? i7 - i8 : i8 - i7;
            if (i7 > i6) {
                return Integer.MAX_VALUE;
            }
            i3 += i7;
        }
        return i3 / i4;
    }

    protected static void recordPattern(BitArray bitArray, int i, int[] iArr) {
        int i2;
        int length = iArr.length;
        for (i2 = 0; i2 < length; i2++) {
            iArr[i2] = 0;
        }
        int size = bitArray.getSize();
        if (i >= size) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i3;
        int i4 = !bitArray.get(i) ? 1 : 0;
        i2 = 0;
        while (i < size) {
            if ((bitArray.get(i) ^ i4) != 0) {
                iArr[i2] = iArr[i2] + 1;
                i3 = i4;
            } else {
                i3 = i2 + 1;
                if (i3 == length) {
                    break;
                }
                iArr[i3] = 1;
                int i5 = i3;
                i3 = i4 == 0 ? 1 : 0;
                i2 = i5;
            }
            i++;
            i4 = i3;
        }
        i3 = i2;
        if (i3 == length) {
            return;
        }
        if (i3 != length - 1 || i != size) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    protected static void recordPatternInReverse(BitArray bitArray, int i, int[] iArr) {
        int length = iArr.length;
        boolean z = bitArray.get(i);
        while (i > 0 && length >= 0) {
            i--;
            if (bitArray.get(i) != z) {
                length--;
                z = !z;
            }
        }
        if (length >= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        recordPattern(bitArray, i + 1, iArr);
    }

    public Result decode(BinaryBitmap binaryBitmap) {
        return decode(binaryBitmap, null);
    }

    public Result decode(BinaryBitmap binaryBitmap, Hashtable hashtable) {
        try {
            return doDecode(binaryBitmap, hashtable);
        } catch (NotFoundException e) {
            Object obj = (hashtable == null || !hashtable.containsKey(DecodeHintType.TRY_HARDER)) ? null : 1;
            if (obj == null || !binaryBitmap.isRotateSupported()) {
                throw e;
            }
            BinaryBitmap rotateCounterClockwise = binaryBitmap.rotateCounterClockwise();
            Result doDecode = doDecode(rotateCounterClockwise, hashtable);
            Hashtable resultMetadata = doDecode.getResultMetadata();
            int intValue = (resultMetadata == null || !resultMetadata.containsKey(ResultMetadataType.ORIENTATION)) ? 270 : (((Integer) resultMetadata.get(ResultMetadataType.ORIENTATION)).intValue() + 270) % 360;
            doDecode.putMetadata(ResultMetadataType.ORIENTATION, new Integer(intValue));
            ResultPoint[] resultPoints = doDecode.getResultPoints();
            int height = rotateCounterClockwise.getHeight();
            for (intValue = 0; intValue < resultPoints.length; intValue++) {
                resultPoints[intValue] = new ResultPoint((((float) height) - resultPoints[intValue].getY()) - C2020f.f10933c, resultPoints[intValue].getX());
            }
            return doDecode;
        }
    }

    public abstract Result decodeRow(int i, BitArray bitArray, Hashtable hashtable);

    public void reset() {
    }
}
