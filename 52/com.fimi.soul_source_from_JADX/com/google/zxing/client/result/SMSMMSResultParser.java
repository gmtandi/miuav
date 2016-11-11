package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Hashtable;
import java.util.Vector;

final class SMSMMSResultParser extends ResultParser {
    private SMSMMSResultParser() {
    }

    private static void addNumberVia(Vector vector, Vector vector2, String str) {
        Object obj = null;
        int indexOf = str.indexOf(59);
        if (indexOf < 0) {
            vector.addElement(str);
            vector2.addElement(null);
            return;
        }
        vector.addElement(str.substring(0, indexOf));
        String substring = str.substring(indexOf + 1);
        if (substring.startsWith("via=")) {
            obj = substring.substring(4);
        }
        vector2.addElement(obj);
    }

    public static SMSParsedResult parse(Result result) {
        String str = null;
        String text = result.getText();
        if (text == null) {
            return null;
        }
        if (!text.startsWith("sms:") && !text.startsWith("SMS:") && !text.startsWith("mms:") && !text.startsWith("MMS:")) {
            return null;
        }
        String str2;
        Hashtable parseNameValuePairs = ResultParser.parseNameValuePairs(text);
        int i = 0;
        if (parseNameValuePairs == null || parseNameValuePairs.isEmpty()) {
            str2 = null;
        } else {
            str = (String) parseNameValuePairs.get("body");
            str2 = (String) parseNameValuePairs.get("subject");
            i = 1;
        }
        int indexOf = text.indexOf(63, 4);
        String substring = (indexOf < 0 || i == 0) ? text.substring(4) : text.substring(4, indexOf);
        Vector vector = new Vector(1);
        Vector vector2 = new Vector(1);
        int i2 = -1;
        while (true) {
            int indexOf2 = substring.indexOf(44, i2 + 1);
            if (indexOf2 > i2) {
                addNumberVia(vector, vector2, substring.substring(i2 + 1, indexOf2));
                i2 = indexOf2;
            } else {
                addNumberVia(vector, vector2, substring.substring(i2 + 1));
                return new SMSParsedResult(ResultParser.toStringArray(vector), ResultParser.toStringArray(vector2), str2, str);
            }
        }
    }
}
