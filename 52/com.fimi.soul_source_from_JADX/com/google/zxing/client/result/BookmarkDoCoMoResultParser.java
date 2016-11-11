package com.google.zxing.client.result;

import com.google.zxing.Result;

final class BookmarkDoCoMoResultParser extends AbstractDoCoMoResultParser {
    private BookmarkDoCoMoResultParser() {
    }

    public static URIParsedResult parse(Result result) {
        String text = result.getText();
        if (text == null || !text.startsWith("MEBKM:")) {
            return null;
        }
        String matchSingleDoCoMoPrefixedField = AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("TITLE:", text, true);
        String[] matchDoCoMoPrefixedField = AbstractDoCoMoResultParser.matchDoCoMoPrefixedField("URL:", text, true);
        if (matchDoCoMoPrefixedField == null) {
            return null;
        }
        text = matchDoCoMoPrefixedField[0];
        return URIResultParser.isBasicallyValidURI(text) ? new URIParsedResult(text, matchSingleDoCoMoPrefixedField) : null;
    }
}
