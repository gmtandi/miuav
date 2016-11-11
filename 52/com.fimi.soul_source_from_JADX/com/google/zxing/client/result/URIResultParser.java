package com.google.zxing.client.result;

import com.google.zxing.Result;

final class URIResultParser extends ResultParser {
    private URIResultParser() {
    }

    static boolean isBasicallyValidURI(String str) {
        if (str == null || str.indexOf(32) >= 0 || str.indexOf(10) >= 0) {
            return false;
        }
        int indexOf = str.indexOf(46);
        if (indexOf >= str.length() - 2) {
            return false;
        }
        int indexOf2 = str.indexOf(58);
        if (indexOf < 0 && indexOf2 < 0) {
            return false;
        }
        if (indexOf2 >= 0) {
            char charAt;
            if (indexOf < 0 || indexOf > indexOf2) {
                for (indexOf = 0; indexOf < indexOf2; indexOf++) {
                    charAt = str.charAt(indexOf);
                    if ((charAt < 'a' || charAt > 'z') && (charAt < 'A' || charAt > 'Z')) {
                        return false;
                    }
                }
            } else if (indexOf2 >= str.length() - 2) {
                return false;
            } else {
                for (indexOf = indexOf2 + 1; indexOf < indexOf2 + 3; indexOf++) {
                    charAt = str.charAt(indexOf);
                    if (charAt < '0' || charAt > '9') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static URIParsedResult parse(Result result) {
        String text = result.getText();
        if (text != null && text.startsWith("URL:")) {
            text = text.substring(4);
        }
        return !isBasicallyValidURI(text) ? null : new URIParsedResult(text, null);
    }
}
