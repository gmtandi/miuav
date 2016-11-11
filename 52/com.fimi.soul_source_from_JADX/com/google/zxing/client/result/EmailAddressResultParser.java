package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Hashtable;

final class EmailAddressResultParser extends ResultParser {
    EmailAddressResultParser() {
    }

    public static EmailAddressParsedResult parse(Result result) {
        String str = null;
        String text = result.getText();
        if (text == null) {
            return null;
        }
        if (!text.startsWith("mailto:") && !text.startsWith("MAILTO:")) {
            return EmailDoCoMoResultParser.isBasicallyValidEmailAddress(text) ? new EmailAddressParsedResult(text, null, null, new StringBuffer().append("mailto:").append(text).toString()) : null;
        } else {
            String str2;
            String substring = text.substring(7);
            int indexOf = substring.indexOf(63);
            if (indexOf >= 0) {
                substring = substring.substring(0, indexOf);
            }
            Hashtable parseNameValuePairs = ResultParser.parseNameValuePairs(text);
            if (parseNameValuePairs != null) {
                str2 = substring.length() == 0 ? (String) parseNameValuePairs.get("to") : substring;
                substring = (String) parseNameValuePairs.get("subject");
                str = (String) parseNameValuePairs.get("body");
            } else {
                str2 = substring;
                substring = null;
            }
            return new EmailAddressParsedResult(str2, substring, str, text);
        }
    }
}
