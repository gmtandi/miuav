package com.google.zxing.client.result;

import com.google.zxing.Result;

final class TelResultParser extends ResultParser {
    private TelResultParser() {
    }

    public static TelParsedResult parse(Result result) {
        String text = result.getText();
        if (text == null || (!text.startsWith("tel:") && !text.startsWith("TEL:"))) {
            return null;
        }
        String stringBuffer = text.startsWith("TEL:") ? new StringBuffer().append("tel:").append(text.substring(4)).toString() : text;
        int indexOf = text.indexOf(63, 4);
        return new TelParsedResult(indexOf < 0 ? text.substring(4) : text.substring(4, indexOf), stringBuffer, null);
    }
}
