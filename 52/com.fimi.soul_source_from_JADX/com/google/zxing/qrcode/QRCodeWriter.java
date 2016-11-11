package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.QRCode;

public final class QRCodeWriter implements Writer {
    private static final int QUIET_ZONE_SIZE = 4;

    private static BitMatrix renderResult(QRCode qRCode, int i, int i2) {
        ByteMatrix matrix = qRCode.getMatrix();
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int i3 = width + 8;
        int i4 = height + 8;
        int max = Math.max(i, i3);
        int max2 = Math.max(i2, i4);
        int min = Math.min(max / i3, max2 / i4);
        i4 = (max - (width * min)) / 2;
        i3 = (max2 - (height * min)) / 2;
        BitMatrix bitMatrix = new BitMatrix(max, max2);
        max2 = i3;
        for (int i5 = 0; i5 < height; i5++) {
            i3 = i4;
            max = 0;
            while (max < width) {
                if (matrix.get(max, i5) == 1) {
                    bitMatrix.setRegion(i3, max2, min, min);
                }
                max++;
                i3 += min;
            }
            max2 += min;
        }
        return bitMatrix;
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) {
        return encode(str, barcodeFormat, i, i2, null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.common.BitMatrix encode(java.lang.String r4, com.google.zxing.BarcodeFormat r5, int r6, int r7, java.util.Hashtable r8) {
        /*
        r3 = this;
        if (r4 == 0) goto L_0x0008;
    L_0x0002:
        r0 = r4.length();
        if (r0 != 0) goto L_0x0010;
    L_0x0008:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "Found empty contents";
        r0.<init>(r1);
        throw r0;
    L_0x0010:
        r0 = com.google.zxing.BarcodeFormat.QR_CODE;
        if (r5 == r0) goto L_0x002d;
    L_0x0014:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuffer;
        r1.<init>();
        r2 = "Can only encode QR_CODE, but got ";
        r1 = r1.append(r2);
        r1 = r1.append(r5);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x002d:
        if (r6 < 0) goto L_0x0031;
    L_0x002f:
        if (r7 >= 0) goto L_0x0054;
    L_0x0031:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuffer;
        r1.<init>();
        r2 = "Requested dimensions are too small: ";
        r1 = r1.append(r2);
        r1 = r1.append(r6);
        r2 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        r1 = r1.append(r2);
        r1 = r1.append(r7);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0054:
        r1 = com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.f11109L;
        if (r8 == 0) goto L_0x006f;
    L_0x0058:
        r0 = com.google.zxing.EncodeHintType.ERROR_CORRECTION;
        r0 = r8.get(r0);
        r0 = (com.google.zxing.qrcode.decoder.ErrorCorrectionLevel) r0;
        if (r0 == 0) goto L_0x006f;
    L_0x0062:
        r1 = new com.google.zxing.qrcode.encoder.QRCode;
        r1.<init>();
        com.google.zxing.qrcode.encoder.Encoder.encode(r4, r0, r8, r1);
        r0 = renderResult(r1, r6, r7);
        return r0;
    L_0x006f:
        r0 = r1;
        goto L_0x0062;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.QRCodeWriter.encode(java.lang.String, com.google.zxing.BarcodeFormat, int, int, java.util.Hashtable):com.google.zxing.common.BitMatrix");
    }
}
