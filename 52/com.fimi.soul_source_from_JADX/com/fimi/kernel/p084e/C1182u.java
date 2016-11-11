package com.fimi.kernel.p084e;

import com.fimi.kernel.p073a.C1088a;
import com.fimi.kernel.p073a.C1092e;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p124f.p125c.C3022o;

/* renamed from: com.fimi.kernel.e.u */
public class C1182u {
    public static char m8237a(int i) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return '0';
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return '1';
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return '2';
            case Type.BYTE /*3*/:
                return '3';
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return '4';
            case Type.INT /*5*/:
                return '5';
            case Type.FLOAT /*6*/:
                return '6';
            case Type.LONG /*7*/:
                return '7';
            case Type.DOUBLE /*8*/:
                return '8';
            case Type.ARRAY /*9*/:
                return '9';
            case Type.OBJECT /*10*/:
                return 'a';
            case Opcodes.T_LONG /*11*/:
                return 'b';
            case Opcodes.FCONST_1 /*12*/:
                return 'c';
            case Opcodes.FCONST_2 /*13*/:
                return 'd';
            case Opcodes.DCONST_0 /*14*/:
                return 'e';
            case Opcodes.DCONST_1 /*15*/:
                return 'f';
            default:
                return C3022o.f15055c;
        }
    }

    public static double m8238a(double d, double d2) {
        return Math.log(d) / Math.log(d2);
    }

    public static double m8239a(double d, double d2, double d3, double d4) {
        double d5 = d - d3;
        double d6 = d2 - d4;
        return Math.sqrt((d5 * d5) + (d6 * d6));
    }

    public static double m8240a(C1092e c1092e, C1092e c1092e2) {
        return C1182u.m8239a(c1092e.f5077a, c1092e.f5078b, c1092e2.f5077a, c1092e2.f5078b);
    }

    public static int m8241a(double[] dArr) {
        float f = 0.0f;
        for (double d : dArr) {
            f = (float) (((double) f) + d);
        }
        return (int) (f / ((float) dArr.length));
    }

    public static String m8242a(byte[] bArr, int i) {
        String str = C2915a.f14760f;
        String str2 = C2915a.f14760f;
        str2 = str;
        int i2 = 0;
        while (i2 < i) {
            String toHexString = Integer.toHexString(bArr[i2] & Util.MASK_8BIT);
            i2++;
            str2 = (toHexString.length() == 1 ? str2 + Constants.VIA_RESULT_SUCCESS + toHexString : str2 + toHexString) + MiPushClient.ACCEPT_TIME_SEPARATOR;
        }
        return str2.toUpperCase();
    }

    public static BigDecimal m8243a(double d, int i) {
        return new BigDecimal(d).setScale(i, 4);
    }

    public static List<C1092e> m8244a(C1088a c1088a, C1088a c1088a2) {
        List<C1092e> arrayList = new ArrayList();
        double d = (c1088a.f5059b * 2.0d) * (c1088a.f5058a.f5077a - c1088a2.f5058a.f5077a);
        double d2 = (c1088a.f5059b * 2.0d) * (c1088a.f5058a.f5078b - c1088a2.f5058a.f5078b);
        double pow = ((Math.pow(c1088a2.f5059b, 2.0d) - Math.pow(c1088a.f5059b, 2.0d)) - Math.pow(c1088a.f5058a.f5077a - c1088a2.f5058a.f5077a, 2.0d)) - Math.pow(c1088a.f5058a.f5078b - c1088a2.f5058a.f5078b, 2.0d);
        double pow2 = Math.pow(d, 2.0d) + Math.pow(d2, 2.0d);
        d = (d * -2.0d) * pow;
        d2 = Math.pow(d, 2.0d) - ((Math.pow(pow, 2.0d) - Math.pow(d2, 2.0d)) * (4.0d * pow2));
        pow = (Math.sqrt(d2) - d) / (2.0d * pow2);
        d = ((-Math.sqrt(d2)) - d) / (2.0d * pow2);
        d2 = (c1088a.f5059b * pow) + c1088a.f5058a.f5077a;
        pow = Math.sqrt(Math.pow(c1088a.f5059b, 2.0d) - Math.pow(d2 - c1088a.f5058a.f5077a, 2.0d)) + c1088a.f5058a.f5078b;
        pow2 = (-Math.sqrt(Math.pow(c1088a.f5059b, 2.0d) - Math.pow(d2 - c1088a.f5058a.f5077a, 2.0d))) + c1088a.f5058a.f5078b;
        Set<C1092e> hashSet = new HashSet();
        C1092e c1092e = new C1092e(d2, pow);
        if (C1182u.m8249a(c1092e, c1088a, c1088a2)) {
            hashSet.add(c1092e);
        }
        C1092e c1092e2 = new C1092e(d2, pow2);
        if (C1182u.m8249a(c1092e2, c1088a, c1088a2)) {
            hashSet.add(c1092e2);
        }
        d = (d * c1088a.f5059b) + c1088a.f5058a.f5077a;
        pow = (-Math.sqrt(Math.pow(c1088a.f5059b, 2.0d) - Math.pow(d - c1088a.f5058a.f5077a, 2.0d))) + c1088a.f5058a.f5078b;
        C1092e c1092e3 = new C1092e(d, Math.sqrt(Math.pow(c1088a.f5059b, 2.0d) - Math.pow(d - c1088a.f5058a.f5077a, 2.0d)) + c1088a.f5058a.f5078b);
        if (C1182u.m8249a(c1092e3, c1088a, c1088a2)) {
            hashSet.add(c1092e3);
        }
        C1092e c1092e4 = new C1092e(d, pow);
        if (C1182u.m8249a(c1092e4, c1088a, c1088a2)) {
            hashSet.add(c1092e4);
        }
        for (C1092e add : hashSet) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public static boolean m8245a(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
        if (d < Math.min(d4, d6) || d > Math.max(d4, d6) || d2 < Math.min(d5, d7) || d2 > Math.max(d5, d7)) {
            return false;
        }
        return d3 <= Math.abs(d - d4) && d3 <= Math.abs(d2 - d7) && d3 <= Math.abs(d - d6) && d3 <= Math.abs(d2 - d7);
    }

    public static boolean m8246a(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        if ((d4 - d2) / (d3 - d) == (d8 - d6) / (d7 - d5)) {
            return false;
        }
        double d9 = (((d * d4) - (d2 * d3)) - ((((((d * d4) - (d2 * d3)) * (d5 - d7)) - (((d5 * d8) - (d6 * d7)) * (d - d3))) / (((d4 - d2) * (d5 - d7)) - ((d8 - d6) * (d - d3)))) * (d4 - d2))) / (d - d3);
        return true;
    }

    public static boolean m8247a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        return (f5 <= f || f5 <= f + f3) ? (f5 >= f || f5 >= f - f7) ? (f6 <= f2 || f6 <= f2 + f4) ? f6 >= f2 || f6 >= f2 - f8 : false : false : false;
    }

    public static boolean m8248a(C1092e c1092e, C1088a c1088a) {
        return Math.pow(c1092e.f5077a - c1088a.f5058a.f5077a, 2.0d) + Math.pow(c1092e.f5078b - c1088a.f5058a.f5078b, 2.0d) <= Math.pow(c1088a.f5059b, 2.0d);
    }

    public static boolean m8249a(C1092e c1092e, C1088a c1088a, C1088a c1088a2) {
        return Math.pow(c1092e.f5077a - c1088a2.f5058a.f5077a, 2.0d) + Math.pow(c1092e.f5078b - c1088a2.f5058a.f5078b, 2.0d) == Math.pow(c1088a2.f5059b, 2.0d) && Math.pow(c1092e.f5077a - c1088a.f5058a.f5077a, 2.0d) + Math.pow(c1092e.f5078b - c1088a.f5058a.f5078b, 2.0d) == Math.pow(c1088a.f5059b, 2.0d);
    }

    public static boolean m8250a(C1092e c1092e, C1088a c1088a, C1088a c1088a2, float f) {
        return Math.pow(c1092e.f5077a - c1088a2.f5058a.f5077a, 2.0d) + Math.pow(c1092e.f5078b - c1088a2.f5058a.f5078b, 2.0d) <= Math.pow(c1088a2.f5059b, 2.0d) + ((double) f) && Math.pow(c1092e.f5077a - c1088a2.f5058a.f5077a, 2.0d) + Math.pow(c1092e.f5078b - c1088a2.f5058a.f5078b, 2.0d) >= Math.pow(c1088a2.f5059b, 2.0d) - ((double) f) && Math.pow(c1092e.f5077a - c1088a.f5058a.f5077a, 2.0d) + Math.pow(c1092e.f5078b - c1088a.f5058a.f5078b, 2.0d) <= Math.pow(c1088a.f5059b, 2.0d) + ((double) f) && Math.pow(c1092e.f5077a - c1088a.f5058a.f5077a, 2.0d) + Math.pow(c1092e.f5078b - c1088a.f5058a.f5078b, 2.0d) >= Math.pow(c1088a.f5059b, 2.0d) - ((double) f);
    }

    public static double[] m8251a(int[] iArr) {
        int length = iArr.length;
        double[] dArr = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = Double.valueOf(String.valueOf(iArr[i])).doubleValue();
        }
        return dArr;
    }

    public static double[] m8252a(double[][] dArr) {
        double[] dArr2 = new double[(dArr.length * dArr[0].length)];
        for (int i = 0; i < dArr.length; i++) {
            for (int i2 = 0; i2 < dArr[i].length; i2++) {
                dArr2[(dArr.length * i2) + i] = dArr[i][i2];
            }
        }
        return dArr2;
    }

    public static double[][] m8253a(int[][] iArr) {
        int length = iArr.length;
        int length2 = iArr[0].length;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, new int[]{length, length2});
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length2; i2++) {
                dArr[i][i2] = Double.valueOf(String.valueOf(iArr[i][i2])).doubleValue();
            }
        }
        return dArr;
    }

    public static int[][] m8254a(int[] iArr, int i, int i2) {
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{i2, i});
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                iArr2[i3][i4] = iArr[(i4 * i2) + i3];
            }
        }
        return iArr2;
    }

    public static int m8255b(int[] iArr) {
        float f = 0.0f;
        for (int i : iArr) {
            f += (float) i;
        }
        return (int) (f / ((float) iArr.length));
    }

    public static boolean m8256b(double d, double d2, double d3, double d4, double d5, double d6) {
        return ((d - d3) * (d6 - d4)) - ((d2 - d4) * (d5 - d3)) == 0.0d ? d >= Math.min(d3, d5) && d <= Math.max(d3, d5) && d2 >= Math.min(d4, d6) && d2 <= Math.max(d4, d6) : false;
    }

    public static boolean m8257b(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        if ((d4 - d2) / (d3 - d) == (d8 - d6) / (d7 - d5)) {
            return false;
        }
        double d9 = ((((d * d4) - (d2 * d3)) * (d5 - d7)) - (((d5 * d8) - (d6 * d7)) * (d - d3))) / (((d4 - d2) * (d5 - d7)) - ((d8 - d6) * (d - d3)));
        double d10 = (((d * d4) - (d2 * d3)) - ((d4 - d2) * d9)) / (d - d3);
        return d9 >= Math.min(d, d3) && d9 <= Math.max(d, d3) && d10 >= Math.min(d2, d4) && d10 <= Math.max(d2, d4) && d9 >= Math.min(d5, d7) && d9 <= Math.max(d5, d7) && d10 >= Math.min(d6, d8) && d10 <= Math.max(d6, d8);
    }

    public static boolean m8258c(double d, double d2, double d3, double d4, double d5, double d6) {
        return d >= Math.min(d3, d5) && d <= Math.max(d3, d5) && d2 >= Math.min(d4, d6) && d2 <= Math.max(d4, d6);
    }

    public static boolean m8259c(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        if ((d4 - d2) / (d3 - d) == (d8 - d6) / (d7 - d5)) {
            return false;
        }
        double d9 = ((((d * d4) - (d2 * d3)) * (d5 - d7)) - (((d5 * d8) - (d6 * d7)) * (d - d3))) / (((d4 - d2) * (d5 - d7)) - ((d8 - d6) * (d - d3)));
        double d10 = (((d * d4) - (d2 * d3)) - ((d4 - d2) * d9)) / (d - d3);
        return d9 >= Math.min(d, d3) && d9 <= Math.max(d, d3) && d10 >= Math.min(d2, d4) && d10 <= Math.max(d2, d4);
    }

    public static boolean m8260d(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        return d >= Math.min(d5, d7) && d <= Math.max(d5, d7) && d2 >= Math.min(d6, d8) && d2 <= Math.max(d6, d8) && d3 >= Math.min(d5, d7) && d3 <= Math.max(d5, d7) && d4 >= Math.min(d6, d8) && d4 <= Math.max(d6, d8);
    }

    public boolean m8261a(double d, double d2, double d3, double d4, double d5, double d6) {
        return ((d - d3) * (d6 - d4)) - ((d2 - d4) * (d5 - d3)) == 0.0d;
    }
}
