package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

public class ISBNResultParser extends ResultParser {
    private ISBNResultParser() {
    }

    public static ISBNParsedResult parse(Result result) {
        if (!BarcodeFormat.EAN_13.equals(result.getBarcodeFormat())) {
            return null;
        }
        String text = result.getText();
        return (text == null || text.length() != 13) ? null : (text.startsWith("978") || text.startsWith("979")) ? new ISBNParsedResult(text) : null;
    }
}
