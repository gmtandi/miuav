package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Vector;
import org.p122a.p123a.p124f.p125c.C3022o;

final class BizcardResultParser extends AbstractDoCoMoResultParser {
    BizcardResultParser() {
    }

    private static String buildName(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 != null) {
            str = new StringBuffer().append(str).append(C3022o.f15055c).append(str2).toString();
        }
        return str;
    }

    private static String[] buildPhoneNumbers(String str, String str2, String str3) {
        Vector vector = new Vector(3);
        if (str != null) {
            vector.addElement(str);
        }
        if (str2 != null) {
            vector.addElement(str2);
        }
        if (str3 != null) {
            vector.addElement(str3);
        }
        int size = vector.size();
        if (size == 0) {
            return null;
        }
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = (String) vector.elementAt(i);
        }
        return strArr;
    }

    public static AddressBookParsedResult parse(Result result) {
        String text = result.getText();
        if (text == null || !text.startsWith("BIZCARD:")) {
            return null;
        }
        String buildName = buildName(AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("N:", text, true), AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("X:", text, true));
        String matchSingleDoCoMoPrefixedField = AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("T:", text, true);
        String matchSingleDoCoMoPrefixedField2 = AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("C:", text, true);
        return new AddressBookParsedResult(ResultParser.maybeWrap(buildName), null, buildPhoneNumbers(AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("B:", text, true), AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("M:", text, true), AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("F:", text, true)), ResultParser.maybeWrap(AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("E:", text, true)), null, AbstractDoCoMoResultParser.matchDoCoMoPrefixedField("A:", text, true), matchSingleDoCoMoPrefixedField2, null, matchSingleDoCoMoPrefixedField, null);
    }
}
