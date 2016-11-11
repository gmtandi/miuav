package com.google.zxing.client.result;

import com.google.zxing.Result;
import org.p122a.p123a.p124f.p125c.C3022o;

final class AddressBookDoCoMoResultParser extends AbstractDoCoMoResultParser {
    AddressBookDoCoMoResultParser() {
    }

    public static AddressBookParsedResult parse(Result result) {
        String text = result.getText();
        if (text == null || !text.startsWith("MECARD:")) {
            return null;
        }
        String[] matchDoCoMoPrefixedField = AbstractDoCoMoResultParser.matchDoCoMoPrefixedField("N:", text, true);
        if (matchDoCoMoPrefixedField == null) {
            return null;
        }
        String parseName = parseName(matchDoCoMoPrefixedField[0]);
        String matchSingleDoCoMoPrefixedField = AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("SOUND:", text, true);
        String[] matchDoCoMoPrefixedField2 = AbstractDoCoMoResultParser.matchDoCoMoPrefixedField("TEL:", text, true);
        String[] matchDoCoMoPrefixedField3 = AbstractDoCoMoResultParser.matchDoCoMoPrefixedField("EMAIL:", text, true);
        String matchSingleDoCoMoPrefixedField2 = AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("NOTE:", text, false);
        String[] matchDoCoMoPrefixedField4 = AbstractDoCoMoResultParser.matchDoCoMoPrefixedField("ADR:", text, true);
        String matchSingleDoCoMoPrefixedField3 = AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("BDAY:", text, true);
        if (!(matchSingleDoCoMoPrefixedField3 == null || ResultParser.isStringOfDigits(matchSingleDoCoMoPrefixedField3, 8))) {
            matchSingleDoCoMoPrefixedField3 = null;
        }
        String matchSingleDoCoMoPrefixedField4 = AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("URL:", text, true);
        return new AddressBookParsedResult(ResultParser.maybeWrap(parseName), matchSingleDoCoMoPrefixedField, matchDoCoMoPrefixedField2, matchDoCoMoPrefixedField3, matchSingleDoCoMoPrefixedField2, matchDoCoMoPrefixedField4, AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("ORG:", text, true), matchSingleDoCoMoPrefixedField3, null, matchSingleDoCoMoPrefixedField4);
    }

    private static String parseName(String str) {
        int indexOf = str.indexOf(44);
        return indexOf >= 0 ? new StringBuffer().append(str.substring(indexOf + 1)).append(C3022o.f15055c).append(str.substring(0, indexOf)).toString() : str;
    }
}
