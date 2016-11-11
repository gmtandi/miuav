package com.google.zxing.client.result;

import com.google.zxing.Result;

final class URLTOResultParser {
    private URLTOResultParser() {
    }

    public static URIParsedResult parse(Result result) {
        String str = null;
        String text = result.getText();
        if (text == null) {
            return null;
        }
        if (!text.startsWith("urlto:") && !text.startsWith("URLTO:")) {
            return null;
        }
        int indexOf = text.indexOf(58, 6);
        if (indexOf < 0) {
            return null;
        }
        if (indexOf > 6) {
            str = text.substring(6, indexOf);
        }
        return new URIParsedResult(text.substring(indexOf + 1), str);
    }
}
