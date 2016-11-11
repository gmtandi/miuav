package com.google.zxing.client.result;

import com.google.zxing.Result;

final class GeoResultParser extends ResultParser {
    private GeoResultParser() {
    }

    public static GeoParsedResult parse(Result result) {
        String text = result.getText();
        if (text == null) {
            return null;
        }
        if (!text.startsWith("geo:") && !text.startsWith("GEO:")) {
            return null;
        }
        String str;
        int indexOf = text.indexOf(63, 4);
        if (indexOf < 0) {
            text = text.substring(4);
            str = null;
        } else {
            str = text.substring(indexOf + 1);
            text = text.substring(4, indexOf);
        }
        int indexOf2 = text.indexOf(44);
        if (indexOf2 < 0) {
            return null;
        }
        int indexOf3 = text.indexOf(44, indexOf2 + 1);
        try {
            double parseDouble = Double.parseDouble(text.substring(0, indexOf2));
            if (parseDouble > 90.0d || parseDouble < -90.0d) {
                return null;
            }
            double parseDouble2;
            double d;
            if (indexOf3 < 0) {
                parseDouble2 = Double.parseDouble(text.substring(indexOf2 + 1));
                d = 0.0d;
            } else {
                parseDouble2 = Double.parseDouble(text.substring(indexOf2 + 1, indexOf3));
                d = Double.parseDouble(text.substring(indexOf3 + 1));
            }
            return (parseDouble2 > 180.0d || parseDouble2 < -180.0d || d < 0.0d) ? null : new GeoParsedResult(parseDouble, parseDouble2, d, str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
