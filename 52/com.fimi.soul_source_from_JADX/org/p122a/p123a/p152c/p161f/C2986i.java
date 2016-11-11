package org.p122a.p123a.p152c.p161f;

import java.util.StringTokenizer;
import org.p122a.p123a.p150a.C2912b;

@C2912b
/* renamed from: org.a.a.c.f.i */
public class C2986i implements C2983f {
    private static final int f14916a = 36;
    private static final int f14917b = 1;
    private static final int f14918c = 26;
    private static final int f14919d = 38;
    private static final int f14920e = 700;
    private static final int f14921f = 72;
    private static final int f14922g = 128;
    private static final char f14923h = '-';
    private static final String f14924i = "xn--";

    private int m16929a(char c) {
        if (c >= 'A' && c <= 'Z') {
            return c - 65;
        }
        if (c >= 'a' && c <= 'z') {
            return c - 97;
        }
        if (c >= '0' && c <= '9') {
            return (c - 48) + f14918c;
        }
        throw new IllegalArgumentException("illegal digit: " + c);
    }

    private int m16930a(int i, int i2, boolean z) {
        int i3 = z ? i / f14920e : i / 2;
        int i4 = (i3 / i2) + i3;
        i3 = 0;
        while (i4 > 455) {
            i4 /= 35;
            i3 += f14916a;
        }
        return i3 + ((i4 * f14916a) / (i4 + f14919d));
    }

    public String m16931a(String str) {
        StringBuilder stringBuilder = new StringBuilder(str.length());
        StringTokenizer stringTokenizer = new StringTokenizer(str, ".");
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (stringBuilder.length() > 0) {
                stringBuilder.append('.');
            }
            if (nextToken.startsWith(f14924i)) {
                nextToken = m16932b(nextToken.substring(4));
            }
            stringBuilder.append(nextToken);
        }
        return stringBuilder.toString();
    }

    protected String m16932b(String str) {
        int i;
        int i2 = f14921f;
        StringBuilder stringBuilder = new StringBuilder(str.length());
        int lastIndexOf = str.lastIndexOf(45);
        if (lastIndexOf != -1) {
            stringBuilder.append(str.subSequence(0, lastIndexOf));
            str = str.substring(lastIndexOf + f14917b);
            lastIndexOf = f14922g;
            i = 0;
        } else {
            lastIndexOf = f14922g;
            i = 0;
        }
        while (str.length() > 0) {
            int i3 = f14916a;
            int i4 = f14917b;
            int i5 = i;
            while (str.length() != 0) {
                char charAt = str.charAt(0);
                str = str.substring(f14917b);
                int a = m16929a(charAt);
                i5 += a * i4;
                int i6 = i3 <= i2 + f14917b ? f14917b : i3 >= i2 + f14918c ? f14918c : i3 - i2;
                if (a < i6) {
                    break;
                }
                i4 *= 36 - i6;
                i3 += f14916a;
            }
            i2 = m16930a(i5 - i, stringBuilder.length() + f14917b, i == 0);
            lastIndexOf += i5 / (stringBuilder.length() + f14917b);
            i = i5 % (stringBuilder.length() + f14917b);
            stringBuilder.insert(i, (char) lastIndexOf);
            i += f14917b;
        }
        return stringBuilder.toString();
    }
}
