package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Hashtable;
import java.util.Vector;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.p122a.p123a.p124f.p125c.C3022o;

public abstract class ResultParser {
    private static void appendKeyValue(String str, int i, int i2, Hashtable hashtable) {
        int indexOf = str.indexOf(61, i);
        if (indexOf >= 0) {
            hashtable.put(str.substring(i, indexOf), urlDecode(str.substring(indexOf + 1, i2)));
        }
    }

    private static int findFirstEscape(char[] cArr) {
        int length = cArr.length;
        int i = 0;
        while (i < length) {
            char c = cArr[i];
            if (c == SignatureVisitor.EXTENDS || c == '%') {
                return i;
            }
            i++;
        }
        return -1;
    }

    protected static boolean isStringOfDigits(String str, int i) {
        if (str == null || i != str.length()) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            char charAt = str.charAt(i2);
            if (charAt < '0' || charAt > '9') {
                return false;
            }
        }
        return true;
    }

    protected static boolean isSubstringOfDigits(String str, int i, int i2) {
        if (str == null) {
            return false;
        }
        int i3 = i + i2;
        if (str.length() < i3) {
            return false;
        }
        while (i < i3) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return false;
            }
            i++;
        }
        return true;
    }

    static String[] matchPrefixedField(String str, String str2, char c, boolean z) {
        int length = str2.length();
        int i = 0;
        Vector vector = null;
        while (i < length) {
            i = str2.indexOf(str, i);
            if (i < 0) {
                break;
            }
            int length2 = i + str.length();
            Object obj = null;
            Vector vector2 = vector;
            int i2 = length2;
            while (obj == null) {
                int indexOf = str2.indexOf(c, i2);
                if (indexOf < 0) {
                    i2 = str2.length();
                    obj = 1;
                } else if (str2.charAt(indexOf - 1) == C3022o.f15058f) {
                    i2 = indexOf + 1;
                } else {
                    if (vector2 == null) {
                        vector2 = new Vector(3);
                    }
                    Object unescapeBackslash = unescapeBackslash(str2.substring(length2, indexOf));
                    if (z) {
                        unescapeBackslash = unescapeBackslash.trim();
                    }
                    vector2.addElement(unescapeBackslash);
                    i2 = indexOf + 1;
                    obj = 1;
                }
            }
            int i3 = i2;
            vector = vector2;
            i = i3;
        }
        return (vector == null || vector.isEmpty()) ? null : toStringArray(vector);
    }

    static String matchSinglePrefixedField(String str, String str2, char c, boolean z) {
        String[] matchPrefixedField = matchPrefixedField(str, str2, c, z);
        return matchPrefixedField == null ? null : matchPrefixedField[0];
    }

    protected static void maybeAppend(String str, StringBuffer stringBuffer) {
        if (str != null) {
            stringBuffer.append('\n');
            stringBuffer.append(str);
        }
    }

    protected static void maybeAppend(String[] strArr, StringBuffer stringBuffer) {
        if (strArr != null) {
            for (String append : strArr) {
                stringBuffer.append('\n');
                stringBuffer.append(append);
            }
        }
    }

    protected static String[] maybeWrap(String str) {
        if (str == null) {
            return null;
        }
        return new String[]{str};
    }

    private static int parseHexDigit(char c) {
        if (c >= 'a') {
            if (c <= 'f') {
                return (c - 97) + 10;
            }
        } else if (c >= 'A') {
            if (c <= 'F') {
                return (c - 65) + 10;
            }
        } else if (c >= '0' && c <= '9') {
            return c - 48;
        }
        return -1;
    }

    static Hashtable parseNameValuePairs(String str) {
        int indexOf = str.indexOf(63);
        if (indexOf < 0) {
            return null;
        }
        Hashtable hashtable = new Hashtable(3);
        indexOf++;
        while (true) {
            int indexOf2 = str.indexOf(38, indexOf);
            if (indexOf2 >= 0) {
                appendKeyValue(str, indexOf, indexOf2, hashtable);
                indexOf = indexOf2 + 1;
            } else {
                appendKeyValue(str, indexOf, str.length(), hashtable);
                return hashtable;
            }
        }
    }

    public static ParsedResult parseResult(Result result) {
        ParsedResult parse = BookmarkDoCoMoResultParser.parse(result);
        if (parse != null) {
            return parse;
        }
        parse = AddressBookDoCoMoResultParser.parse(result);
        if (parse != null) {
            return parse;
        }
        parse = EmailDoCoMoResultParser.parse(result);
        if (parse != null) {
            return parse;
        }
        parse = AddressBookAUResultParser.parse(result);
        if (parse != null) {
            return parse;
        }
        parse = VCardResultParser.parse(result);
        if (parse != null) {
            return parse;
        }
        parse = BizcardResultParser.parse(result);
        if (parse != null) {
            return parse;
        }
        parse = VEventResultParser.parse(result);
        if (parse != null) {
            return parse;
        }
        parse = EmailAddressResultParser.parse(result);
        if (parse != null) {
            return parse;
        }
        parse = TelResultParser.parse(result);
        if (parse != null) {
            return parse;
        }
        parse = SMSMMSResultParser.parse(result);
        if (parse != null) {
            return parse;
        }
        parse = SMSTOMMSTOResultParser.parse(result);
        if (parse != null) {
            return parse;
        }
        parse = GeoResultParser.parse(result);
        if (parse != null) {
            return parse;
        }
        parse = WifiResultParser.parse(result);
        if (parse != null) {
            return parse;
        }
        parse = URLTOResultParser.parse(result);
        if (parse != null) {
            return parse;
        }
        parse = URIResultParser.parse(result);
        if (parse != null) {
            return parse;
        }
        parse = ISBNResultParser.parse(result);
        if (parse != null) {
            return parse;
        }
        parse = ProductResultParser.parse(result);
        if (parse != null) {
            return parse;
        }
        parse = ExpandedProductResultParser.parse(result);
        return parse == null ? new TextParsedResult(result.getText(), null) : parse;
    }

    static String[] toStringArray(Vector vector) {
        int size = vector.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = (String) vector.elementAt(i);
        }
        return strArr;
    }

    protected static String unescapeBackslash(String str) {
        if (str == null) {
            return str;
        }
        int indexOf = str.indexOf(92);
        if (indexOf < 0) {
            return str;
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length - 1);
        stringBuffer.append(str.toCharArray(), 0, indexOf);
        indexOf = 0;
        for (int i = indexOf; i < length; i++) {
            char charAt = str.charAt(i);
            if (indexOf == 0 && charAt == C3022o.f15058f) {
                indexOf = 1;
            } else {
                stringBuffer.append(charAt);
                indexOf = 0;
            }
        }
        return stringBuffer.toString();
    }

    private static String urlDecode(String str) {
        if (str == null) {
            return null;
        }
        char[] toCharArray = str.toCharArray();
        int findFirstEscape = findFirstEscape(toCharArray);
        if (findFirstEscape < 0) {
            return str;
        }
        int length = toCharArray.length;
        StringBuffer stringBuffer = new StringBuffer(length - 2);
        stringBuffer.append(toCharArray, 0, findFirstEscape);
        while (findFirstEscape < length) {
            char c = toCharArray[findFirstEscape];
            if (c == SignatureVisitor.EXTENDS) {
                stringBuffer.append(C3022o.f15055c);
            } else if (c != '%') {
                stringBuffer.append(c);
            } else if (findFirstEscape >= length - 2) {
                stringBuffer.append('%');
            } else {
                findFirstEscape++;
                int parseHexDigit = parseHexDigit(toCharArray[findFirstEscape]);
                findFirstEscape++;
                int parseHexDigit2 = parseHexDigit(toCharArray[findFirstEscape]);
                if (parseHexDigit < 0 || parseHexDigit2 < 0) {
                    stringBuffer.append('%');
                    stringBuffer.append(toCharArray[findFirstEscape - 1]);
                    stringBuffer.append(toCharArray[findFirstEscape]);
                }
                stringBuffer.append((char) ((parseHexDigit << 4) + parseHexDigit2));
            }
            findFirstEscape++;
        }
        return stringBuffer.toString();
    }
}
