package com.google.zxing.client.result;

import com.google.zxing.Result;

final class VEventResultParser extends ResultParser {
    private VEventResultParser() {
    }

    public static CalendarParsedResult parse(Result result) {
        String text = result.getText();
        if (text == null) {
            return null;
        }
        if (text.indexOf("BEGIN:VEVENT") < 0) {
            return null;
        }
        try {
            return new CalendarParsedResult(VCardResultParser.matchSingleVCardPrefixedField("SUMMARY", text, true), VCardResultParser.matchSingleVCardPrefixedField("DTSTART", text, true), VCardResultParser.matchSingleVCardPrefixedField("DTEND", text, true), VCardResultParser.matchSingleVCardPrefixedField("LOCATION", text, true), null, VCardResultParser.matchSingleVCardPrefixedField("DESCRIPTION", text, true));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
