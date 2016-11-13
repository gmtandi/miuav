package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.oned.UPCEReader;

final class ProductResultParser extends ResultParser {
    private ProductResultParser() {
    }

    public static ProductParsedResult parse(Result result) {
        BarcodeFormat barcodeFormat = result.getBarcodeFormat();
        if (!BarcodeFormat.UPC_A.equals(barcodeFormat) && !BarcodeFormat.UPC_E.equals(barcodeFormat) && !BarcodeFormat.EAN_8.equals(barcodeFormat) && !BarcodeFormat.EAN_13.equals(barcodeFormat)) {
            return null;
        }
        String text = result.getText();
        if (text == null) {
            return null;
        }
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char charAt = text.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return null;
            }
        }
        return new ProductParsedResult(text, BarcodeFormat.UPC_E.equals(barcodeFormat) ? UPCEReader.convertUPCEtoUPCA(text) : text);
    }
}
