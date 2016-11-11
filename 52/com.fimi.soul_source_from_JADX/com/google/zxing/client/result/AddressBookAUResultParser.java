package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Vector;
import org.p122a.p123a.p124f.p125c.C3022o;

final class AddressBookAUResultParser extends ResultParser {
    AddressBookAUResultParser() {
    }

    private static String[] matchMultipleValuePrefix(String str, int i, String str2, boolean z) {
        Vector vector = null;
        for (int i2 = 1; i2 <= i; i2++) {
            String matchSinglePrefixedField = ResultParser.matchSinglePrefixedField(new StringBuffer().append(str).append(i2).append(':').toString(), str2, C3022o.f15053a, z);
            if (matchSinglePrefixedField == null) {
                break;
            }
            if (vector == null) {
                vector = new Vector(i);
            }
            vector.addElement(matchSinglePrefixedField);
        }
        return vector == null ? null : ResultParser.toStringArray(vector);
    }

    public static AddressBookParsedResult parse(Result result) {
        String text = result.getText();
        if (text == null || text.indexOf("MEMORY") < 0 || text.indexOf("\r\n") < 0) {
            return null;
        }
        return new AddressBookParsedResult(ResultParser.maybeWrap(ResultParser.matchSinglePrefixedField("NAME1:", text, C3022o.f15053a, true)), ResultParser.matchSinglePrefixedField("NAME2:", text, C3022o.f15053a, true), matchMultipleValuePrefix("TEL", 3, text, true), matchMultipleValuePrefix("MAIL", 3, text, true), ResultParser.matchSinglePrefixedField("MEMORY:", text, C3022o.f15053a, false), ResultParser.matchSinglePrefixedField("ADD:", text, C3022o.f15053a, true) == null ? null : new String[]{ResultParser.matchSinglePrefixedField("ADD:", text, C3022o.f15053a, true)}, null, null, null, null);
    }
}
