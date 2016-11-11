package com.xiaomi.smack.util;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.string.C2473a;
import java.util.Random;
import org.p122a.p123a.p124f.p125c.C3022o;

/* renamed from: com.xiaomi.smack.util.g */
public class C2718g {
    private static final char[] f13424a;
    private static final char[] f13425b;
    private static final char[] f13426c;
    private static final char[] f13427d;
    private static final char[] f13428e;
    private static Random f13429f;
    private static char[] f13430g;

    static {
        f13424a = "&quot;".toCharArray();
        f13425b = "&apos;".toCharArray();
        f13426c = "&amp;".toCharArray();
        f13427d = "&lt;".toCharArray();
        f13428e = "&gt;".toCharArray();
        f13429f = new Random();
        f13430g = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    }

    public static String m15357a(int i) {
        if (i < 1) {
            return null;
        }
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < cArr.length; i2++) {
            cArr[i2] = f13430g[f13429f.nextInt(71)];
        }
        return new String(cArr);
    }

    public static String m15358a(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        char[] toCharArray = str.toCharArray();
        int length = toCharArray.length;
        StringBuilder stringBuilder = new StringBuilder((int) (((double) length) * 1.3d));
        int i2 = 0;
        while (i2 < length) {
            char c = toCharArray[i2];
            if (c <= '>') {
                if (c == '<') {
                    if (i2 > i) {
                        stringBuilder.append(toCharArray, i, i2 - i);
                    }
                    i = i2 + 1;
                    stringBuilder.append(f13427d);
                } else if (c == '>') {
                    if (i2 > i) {
                        stringBuilder.append(toCharArray, i, i2 - i);
                    }
                    i = i2 + 1;
                    stringBuilder.append(f13428e);
                } else if (c == '&') {
                    if (i2 > i) {
                        stringBuilder.append(toCharArray, i, i2 - i);
                    }
                    if (length <= i2 + 5 || toCharArray[i2 + 1] != '#' || !Character.isDigit(toCharArray[i2 + 2]) || !Character.isDigit(toCharArray[i2 + 3]) || !Character.isDigit(toCharArray[i2 + 4]) || toCharArray[i2 + 5] != ';') {
                        i = i2 + 1;
                        stringBuilder.append(f13426c);
                    }
                } else if (c == C3022o.f15057e) {
                    if (i2 > i) {
                        stringBuilder.append(toCharArray, i, i2 - i);
                    }
                    i = i2 + 1;
                    stringBuilder.append(f13424a);
                } else if (c == '\'') {
                    if (i2 > i) {
                        stringBuilder.append(toCharArray, i, i2 - i);
                    }
                    i = i2 + 1;
                    stringBuilder.append(f13425b);
                }
            }
            i2++;
        }
        if (i == 0) {
            return str;
        }
        if (i2 > i) {
            stringBuilder.append(toCharArray, i, i2 - i);
        }
        return stringBuilder.toString();
    }

    public static final String m15359a(String str, String str2, String str3) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(str2, 0);
        if (indexOf < 0) {
            return str;
        }
        char[] toCharArray = str.toCharArray();
        char[] toCharArray2 = str3.toCharArray();
        int length = str2.length();
        StringBuilder stringBuilder = new StringBuilder(toCharArray.length);
        stringBuilder.append(toCharArray, 0, indexOf).append(toCharArray2);
        indexOf += length;
        int i = indexOf;
        while (true) {
            i = str.indexOf(str2, i);
            if (i > 0) {
                stringBuilder.append(toCharArray, indexOf, i - indexOf).append(toCharArray2);
                indexOf = i + length;
                i = indexOf;
            } else {
                stringBuilder.append(toCharArray, indexOf, toCharArray.length - indexOf);
                return stringBuilder.toString();
            }
        }
    }

    public static String m15360a(byte[] bArr) {
        return String.valueOf(C2473a.m14158a(bArr));
    }

    public static boolean m15361a(char c) {
        return (c >= C3022o.f15055c && c <= '\ud7ff') || ((c >= '\ue000' && c <= '\ufffd') || ((c >= '\u0000' && c <= '\uffff') || c == '\t' || c == '\n' || c == C3022o.f15053a));
    }

    public static final String m15362b(String str) {
        return C2718g.m15359a(C2718g.m15359a(C2718g.m15359a(C2718g.m15359a(C2718g.m15359a(str, "&lt;", "<"), "&gt;", ">"), "&quot;", "\""), "&apos;", "'"), "&amp;", "&");
    }

    public static String m15363c(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (C2718g.m15361a(charAt)) {
                stringBuilder.append(charAt);
            }
        }
        return stringBuilder.toString();
    }
}
