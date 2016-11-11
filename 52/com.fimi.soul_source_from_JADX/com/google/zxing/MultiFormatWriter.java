package com.google.zxing;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.Code39Writer;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.oned.EAN8Writer;
import com.google.zxing.oned.ITFWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.Hashtable;

public final class MultiFormatWriter implements Writer {
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) {
        return encode(str, barcodeFormat, i, i2, null);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Hashtable hashtable) {
        Writer eAN8Writer;
        if (barcodeFormat == BarcodeFormat.EAN_8) {
            eAN8Writer = new EAN8Writer();
        } else if (barcodeFormat == BarcodeFormat.EAN_13) {
            eAN8Writer = new EAN13Writer();
        } else if (barcodeFormat == BarcodeFormat.QR_CODE) {
            eAN8Writer = new QRCodeWriter();
        } else if (barcodeFormat == BarcodeFormat.CODE_39) {
            eAN8Writer = new Code39Writer();
        } else if (barcodeFormat == BarcodeFormat.CODE_128) {
            eAN8Writer = new Code128Writer();
        } else if (barcodeFormat == BarcodeFormat.ITF) {
            eAN8Writer = new ITFWriter();
        } else {
            throw new IllegalArgumentException(new StringBuffer().append("No encoder available for format ").append(barcodeFormat).toString());
        }
        return eAN8Writer.encode(str, barcodeFormat, i, i2, hashtable);
    }
}
