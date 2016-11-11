package com.google.zxing.client.result;

import com.google.zxing.Result;

final class WifiResultParser extends ResultParser {
    private WifiResultParser() {
    }

    public static WifiParsedResult parse(Result result) {
        String text = result.getText();
        if (text == null || !text.startsWith("WIFI:")) {
            return null;
        }
        return new WifiParsedResult(ResultParser.matchSinglePrefixedField("T:", text, ';', false), ResultParser.matchSinglePrefixedField("S:", text, ';', false), ResultParser.matchSinglePrefixedField("P:", text, ';', false));
    }
}
