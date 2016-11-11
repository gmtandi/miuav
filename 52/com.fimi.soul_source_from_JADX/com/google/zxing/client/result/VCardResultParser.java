package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.p122a.p123a.p124f.p125c.C3022o;

final class VCardResultParser extends ResultParser {
    private VCardResultParser() {
    }

    private static String decodeQuotedPrintable(String str, String str2) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case Type.OBJECT /*10*/:
                case Opcodes.FCONST_2 /*13*/:
                    break;
                case '=':
                    if (i >= length - 2) {
                        break;
                    }
                    charAt = str.charAt(i + 1);
                    if (!(charAt == C3022o.f15053a || charAt == '\n')) {
                        try {
                            byteArrayOutputStream.write((toHexValue(charAt) * 16) + toHexValue(str.charAt(i + 2)));
                        } catch (IllegalArgumentException e) {
                        }
                        i += 2;
                        break;
                    }
                default:
                    maybeAppendFragment(byteArrayOutputStream, str2, stringBuffer);
                    stringBuffer.append(charAt);
                    break;
            }
            i++;
        }
        maybeAppendFragment(byteArrayOutputStream, str2, stringBuffer);
        return stringBuffer.toString();
    }

    private static String formatAddress(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length);
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == ';') {
                stringBuffer.append(C3022o.f15055c);
            } else {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer.toString().trim();
    }

    private static void formatNames(String[] strArr) {
        if (strArr != null) {
            for (int i = 0; i < strArr.length; i++) {
                String str = strArr[i];
                String[] strArr2 = new String[5];
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    int indexOf = str.indexOf(59, i3);
                    if (indexOf <= 0) {
                        break;
                    }
                    strArr2[i2] = str.substring(i3, indexOf);
                    i2++;
                    i3 = indexOf + 1;
                }
                strArr2[i2] = str.substring(i3);
                StringBuffer stringBuffer = new StringBuffer(100);
                maybeAppendComponent(strArr2, 3, stringBuffer);
                maybeAppendComponent(strArr2, 1, stringBuffer);
                maybeAppendComponent(strArr2, 2, stringBuffer);
                maybeAppendComponent(strArr2, 0, stringBuffer);
                maybeAppendComponent(strArr2, 4, stringBuffer);
                strArr[i] = stringBuffer.toString().trim();
            }
        }
    }

    private static boolean isLikeVCardDate(String str) {
        return (str == null || ResultParser.isStringOfDigits(str, 8)) ? true : str.length() == 10 && str.charAt(4) == SignatureVisitor.SUPER && str.charAt(7) == SignatureVisitor.SUPER && ResultParser.isSubstringOfDigits(str, 0, 4) && ResultParser.isSubstringOfDigits(str, 5, 2) && ResultParser.isSubstringOfDigits(str, 8, 2);
    }

    static String matchSingleVCardPrefixedField(String str, String str2, boolean z) {
        String[] matchVCardPrefixedField = matchVCardPrefixedField(str, str2, z);
        return matchVCardPrefixedField == null ? null : matchVCardPrefixedField[0];
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String[] matchVCardPrefixedField(java.lang.String r10, java.lang.String r11, boolean r12) {
        /*
        r0 = 0;
        r2 = 0;
        r3 = r11.length();
    L_0x0006:
        if (r2 >= r3) goto L_0x000e;
    L_0x0008:
        r1 = r11.indexOf(r10, r2);
        if (r1 >= 0) goto L_0x0018;
    L_0x000e:
        if (r0 == 0) goto L_0x0016;
    L_0x0010:
        r1 = r0.isEmpty();
        if (r1 == 0) goto L_0x0123;
    L_0x0016:
        r0 = 0;
    L_0x0017:
        return r0;
    L_0x0018:
        if (r1 <= 0) goto L_0x0027;
    L_0x001a:
        r2 = r1 + -1;
        r2 = r11.charAt(r2);
        r4 = 10;
        if (r2 == r4) goto L_0x0027;
    L_0x0024:
        r2 = r1 + 1;
        goto L_0x0006;
    L_0x0027:
        r2 = r10.length();
        r2 = r2 + r1;
        r1 = r11.charAt(r2);
        r4 = 58;
        if (r1 == r4) goto L_0x003c;
    L_0x0034:
        r1 = r11.charAt(r2);
        r4 = 59;
        if (r1 != r4) goto L_0x0006;
    L_0x003c:
        r1 = r2;
    L_0x003d:
        r4 = r11.charAt(r1);
        r5 = 58;
        if (r4 == r5) goto L_0x0048;
    L_0x0045:
        r1 = r1 + 1;
        goto L_0x003d;
    L_0x0048:
        r5 = 0;
        r4 = 0;
        if (r1 <= r2) goto L_0x009d;
    L_0x004c:
        r6 = r2 + 1;
        r9 = r4;
        r4 = r2;
        r2 = r9;
    L_0x0051:
        if (r6 > r1) goto L_0x009e;
    L_0x0053:
        r7 = r11.charAt(r6);
        r8 = 59;
        if (r7 == r8) goto L_0x0063;
    L_0x005b:
        r7 = r11.charAt(r6);
        r8 = 58;
        if (r7 != r8) goto L_0x008f;
    L_0x0063:
        r4 = r4 + 1;
        r4 = r11.substring(r4, r6);
        r7 = 61;
        r7 = r4.indexOf(r7);
        if (r7 < 0) goto L_0x0129;
    L_0x0071:
        r8 = 0;
        r8 = r4.substring(r8, r7);
        r7 = r7 + 1;
        r4 = r4.substring(r7);
        r7 = "ENCODING";
        r7 = r8.equalsIgnoreCase(r7);
        if (r7 == 0) goto L_0x0092;
    L_0x0084:
        r7 = "QUOTED-PRINTABLE";
        r4 = r4.equalsIgnoreCase(r7);
        if (r4 == 0) goto L_0x0129;
    L_0x008c:
        r4 = 1;
    L_0x008d:
        r5 = r4;
        r4 = r6;
    L_0x008f:
        r6 = r6 + 1;
        goto L_0x0051;
    L_0x0092:
        r7 = "CHARSET";
        r7 = r8.equalsIgnoreCase(r7);
        if (r7 == 0) goto L_0x0129;
    L_0x009a:
        r2 = r4;
        r4 = r5;
        goto L_0x008d;
    L_0x009d:
        r2 = r4;
    L_0x009e:
        r4 = r1 + 1;
        r1 = r4;
    L_0x00a1:
        r6 = 10;
        r1 = r11.indexOf(r6, r1);
        if (r1 < 0) goto L_0x00e1;
    L_0x00a9:
        r6 = r11.length();
        r6 = r6 + -1;
        if (r1 >= r6) goto L_0x00c8;
    L_0x00b1:
        r6 = r1 + 1;
        r6 = r11.charAt(r6);
        r7 = 32;
        if (r6 == r7) goto L_0x00c5;
    L_0x00bb:
        r6 = r1 + 1;
        r6 = r11.charAt(r6);
        r7 = 9;
        if (r6 != r7) goto L_0x00c8;
    L_0x00c5:
        r1 = r1 + 2;
        goto L_0x00a1;
    L_0x00c8:
        if (r5 == 0) goto L_0x00e1;
    L_0x00ca:
        r6 = r1 + -1;
        r6 = r11.charAt(r6);
        r7 = 61;
        if (r6 == r7) goto L_0x00de;
    L_0x00d4:
        r6 = r1 + -2;
        r6 = r11.charAt(r6);
        r7 = 61;
        if (r6 != r7) goto L_0x00e1;
    L_0x00de:
        r1 = r1 + 1;
        goto L_0x00a1;
    L_0x00e1:
        if (r1 >= 0) goto L_0x00e9;
    L_0x00e3:
        r1 = r0;
        r0 = r3;
    L_0x00e5:
        r2 = r0;
        r0 = r1;
        goto L_0x0006;
    L_0x00e9:
        if (r1 <= r4) goto L_0x011d;
    L_0x00eb:
        if (r0 != 0) goto L_0x00f3;
    L_0x00ed:
        r0 = new java.util.Vector;
        r6 = 1;
        r0.<init>(r6);
    L_0x00f3:
        r6 = r1 + -1;
        r6 = r11.charAt(r6);
        r7 = 13;
        if (r6 != r7) goto L_0x00ff;
    L_0x00fd:
        r1 = r1 + -1;
    L_0x00ff:
        r4 = r11.substring(r4, r1);
        if (r12 == 0) goto L_0x0109;
    L_0x0105:
        r4 = r4.trim();
    L_0x0109:
        if (r5 == 0) goto L_0x0118;
    L_0x010b:
        r2 = decodeQuotedPrintable(r4, r2);
    L_0x010f:
        r0.addElement(r2);
        r1 = r1 + 1;
        r9 = r1;
        r1 = r0;
        r0 = r9;
        goto L_0x00e5;
    L_0x0118:
        r2 = stripContinuationCRLF(r4);
        goto L_0x010f;
    L_0x011d:
        r1 = r1 + 1;
        r9 = r1;
        r1 = r0;
        r0 = r9;
        goto L_0x00e5;
    L_0x0123:
        r0 = com.google.zxing.client.result.ResultParser.toStringArray(r0);
        goto L_0x0017;
    L_0x0129:
        r4 = r5;
        goto L_0x008d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.result.VCardResultParser.matchVCardPrefixedField(java.lang.String, java.lang.String, boolean):java.lang.String[]");
    }

    private static void maybeAppendComponent(String[] strArr, int i, StringBuffer stringBuffer) {
        if (strArr[i] != null) {
            stringBuffer.append(C3022o.f15055c);
            stringBuffer.append(strArr[i]);
        }
    }

    private static void maybeAppendFragment(ByteArrayOutputStream byteArrayOutputStream, String str, StringBuffer stringBuffer) {
        if (byteArrayOutputStream.size() > 0) {
            String str2;
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            if (str == null) {
                str2 = new String(toByteArray);
            } else {
                try {
                    str2 = new String(toByteArray, str);
                } catch (UnsupportedEncodingException e) {
                    str2 = new String(toByteArray);
                }
            }
            byteArrayOutputStream.reset();
            stringBuffer.append(str2);
        }
    }

    public static AddressBookParsedResult parse(Result result) {
        int i = 0;
        String text = result.getText();
        if (text == null || !text.startsWith("BEGIN:VCARD")) {
            return null;
        }
        String[] matchVCardPrefixedField = matchVCardPrefixedField("FN", text, true);
        if (matchVCardPrefixedField == null) {
            matchVCardPrefixedField = matchVCardPrefixedField("N", text, true);
            formatNames(matchVCardPrefixedField);
        }
        String[] matchVCardPrefixedField2 = matchVCardPrefixedField("TEL", text, true);
        String[] matchVCardPrefixedField3 = matchVCardPrefixedField("EMAIL", text, true);
        String matchSingleVCardPrefixedField = matchSingleVCardPrefixedField("NOTE", text, false);
        String[] matchVCardPrefixedField4 = matchVCardPrefixedField("ADR", text, true);
        if (matchVCardPrefixedField4 != null) {
            while (i < matchVCardPrefixedField4.length) {
                matchVCardPrefixedField4[i] = formatAddress(matchVCardPrefixedField4[i]);
                i++;
            }
        }
        String matchSingleVCardPrefixedField2 = matchSingleVCardPrefixedField("ORG", text, true);
        String matchSingleVCardPrefixedField3 = matchSingleVCardPrefixedField("BDAY", text, true);
        if (!isLikeVCardDate(matchSingleVCardPrefixedField3)) {
            matchSingleVCardPrefixedField3 = null;
        }
        return new AddressBookParsedResult(matchVCardPrefixedField, null, matchVCardPrefixedField2, matchVCardPrefixedField3, matchSingleVCardPrefixedField, matchVCardPrefixedField4, matchSingleVCardPrefixedField2, matchSingleVCardPrefixedField3, matchSingleVCardPrefixedField("TITLE", text, true), matchSingleVCardPrefixedField("URL", text, true));
    }

    private static String stripContinuationCRLF(String str) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length);
        Object obj = null;
        for (int i = 0; i < length; i++) {
            if (obj == null) {
                char charAt = str.charAt(i);
                switch (charAt) {
                    case Type.OBJECT /*10*/:
                        obj = 1;
                        break;
                    case Opcodes.FCONST_2 /*13*/:
                        obj = null;
                        break;
                    default:
                        stringBuffer.append(charAt);
                        obj = null;
                        break;
                }
            }
            obj = null;
        }
        return stringBuffer.toString();
    }

    private static int toHexValue(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 65) + 10;
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 97) + 10;
        }
        throw new IllegalArgumentException();
    }
}
