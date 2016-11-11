package com.p016a;

import android.text.TextUtils;
import java.util.Hashtable;
import java.util.Locale;

/* renamed from: com.a.cl */
public class cl {
    private static final char[] f750a;
    private static final int[] f751b;

    static {
        f750a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        f751b = new int[]{16, 8, 4, 2, 1};
    }

    private cl() {
    }

    public static final String m1298a(double d, double d2) {
        return cl.m1299a(d, d2, 6);
    }

    public static final String m1299a(double d, double d2, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        int i2 = 0;
        int i3 = 0;
        double[] dArr = new double[]{-90.0d, 90.0d};
        double[] dArr2 = new double[]{-180.0d, 180.0d};
        while (stringBuilder.length() < i) {
            double d3;
            if (obj != null) {
                d3 = (dArr2[0] + dArr2[1]) / 2.0d;
                if (d2 > d3) {
                    i3 |= f751b[i2];
                    dArr2[0] = d3;
                } else {
                    dArr2[1] = d3;
                }
            } else {
                d3 = (dArr[0] + dArr[1]) / 2.0d;
                if (d > d3) {
                    i3 |= f751b[i2];
                    dArr[0] = d3;
                } else {
                    dArr[1] = d3;
                }
            }
            obj = obj == null ? 1 : null;
            if (i2 < 4) {
                i2++;
            } else {
                stringBuilder.append(f750a[i3]);
                i2 = 0;
                i3 = 0;
            }
        }
        return stringBuilder.toString();
    }

    private static final String m1300a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        Object obj;
        String toLowerCase = str.toLowerCase(Locale.US);
        char charAt = toLowerCase.charAt(toLowerCase.length() - 1);
        if (toLowerCase.length() % 2 == 0) {
            obj = "odd";
        } else {
            String str3 = "even";
        }
        toLowerCase = toLowerCase.substring(0, toLowerCase.length() - 1);
        if (!(((String) ((Hashtable) cj.f748a.get(str2)).get(obj)).indexOf(charAt) == -1 || TextUtils.isEmpty(toLowerCase))) {
            toLowerCase = cl.m1300a(toLowerCase, str2);
        }
        return toLowerCase + f750a[((String) ((Hashtable) ck.f749a.get(str2)).get(obj)).indexOf(charAt)];
    }

    public static final String[] m1301a(String str) {
        return new String[]{cl.m1300a(str, "right"), cl.m1300a(str, "btm"), cl.m1300a(str, "left"), cl.m1300a(str, "top"), cl.m1300a(r0[0], "top"), cl.m1300a(r0[0], "btm"), cl.m1300a(r0[2], "top"), cl.m1300a(r0[2], "btm"), cl.m1300a(r0[0], "right"), cl.m1300a(r0[8], "top"), cl.m1300a(r0[9], "top"), cl.m1300a(r0[10], "left"), cl.m1300a(r0[11], "left"), cl.m1300a(r0[12], "left"), cl.m1300a(r0[13], "left"), cl.m1300a(r0[14], "btm"), cl.m1300a(r0[15], "btm"), cl.m1300a(r0[16], "btm"), cl.m1300a(r0[17], "btm"), cl.m1300a(r0[18], "right"), cl.m1300a(r0[19], "right"), cl.m1300a(r0[20], "right"), cl.m1300a(r0[21], "right"), cl.m1300a(r0[22], "top")};
    }
}
