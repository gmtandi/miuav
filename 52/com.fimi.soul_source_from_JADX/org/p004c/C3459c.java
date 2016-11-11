package org.p004c;

import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p004c.p198b.C3448e;
import org.p004c.p198b.C3449f;
import org.p122a.p123a.C2915a;
import org.p184b.C3275p;
import org.p184b.C3317q;

/* renamed from: org.c.c */
public class C3459c {
    protected C3459c() {
    }

    private static String m18839a(Object obj, String str) {
        return (obj == null ? "null" : obj.getClass().getName()) + "<" + str + ">";
    }

    public static void m18840a() {
        C3459c.m18848a(null);
    }

    @Deprecated
    public static void m18841a(double d, double d2) {
        C3459c.m18849a(null, d, d2);
    }

    public static void m18842a(double d, double d2, double d3) {
        C3459c.m18850a(null, d, d2, d3);
    }

    public static void m18843a(float f, float f2, float f3) {
        C3459c.m18883b(null, f, f2, f3);
    }

    public static void m18844a(long j, long j2) {
        C3459c.m18852a(null, j, j2);
    }

    public static void m18845a(Object obj) {
        C3459c.m18853a(null, obj);
    }

    public static void m18846a(Object obj, Object obj2) {
        C3459c.m18854a(null, obj, obj2);
    }

    public static <T> void m18847a(T t, C3275p<? super T> c3275p) {
        C3459c.m18855a(C2915a.f14760f, (Object) t, (C3275p) c3275p);
    }

    public static void m18848a(String str) {
        if (str == null) {
            throw new AssertionError();
        }
        throw new AssertionError(str);
    }

    @Deprecated
    public static void m18849a(String str, double d, double d2) {
        C3459c.m18848a("Use assertEquals(expected, actual, delta) to compare floating-point numbers");
    }

    public static void m18850a(String str, double d, double d2, double d3) {
        if (!C3459c.m18894c(d, d2, d3)) {
            C3459c.m18892c(str, Double.valueOf(d2));
        }
    }

    public static void m18851a(String str, float f, float f2, float f3) {
        if (C3459c.m18895c(f, f2, f3)) {
            C3459c.m18904h(str, Float.valueOf(f), Float.valueOf(f2));
        }
    }

    public static void m18852a(String str, long j, long j2) {
        if (j == j2) {
            C3459c.m18892c(str, Long.valueOf(j2));
        }
    }

    public static void m18853a(String str, Object obj) {
        C3459c.m18856a(str, obj != null);
    }

    public static void m18854a(String str, Object obj, Object obj2) {
        if (!C3459c.m18900e(obj, obj2)) {
            if ((obj instanceof String) && (obj2 instanceof String)) {
                if (str == null) {
                    str = C2915a.f14760f;
                }
                throw new C3563i(str, (String) obj, (String) obj2);
            }
            C3459c.m18904h(str, obj, obj2);
        }
    }

    public static <T> void m18855a(String str, T t, C3275p<? super T> c3275p) {
        C3317q.m18310a(str, t, c3275p);
    }

    public static void m18856a(String str, boolean z) {
        if (!z) {
            C3459c.m18848a(str);
        }
    }

    public static void m18857a(String str, byte[] bArr, byte[] bArr2) {
        C3459c.m18901f(str, bArr, bArr2);
    }

    public static void m18858a(String str, char[] cArr, char[] cArr2) {
        C3459c.m18901f(str, cArr, cArr2);
    }

    public static void m18859a(String str, double[] dArr, double[] dArr2, double d) {
        new C3449f(d).m18806a(str, (Object) dArr, (Object) dArr2);
    }

    public static void m18860a(String str, float[] fArr, float[] fArr2, float f) {
        new C3449f(f).m18806a(str, (Object) fArr, (Object) fArr2);
    }

    public static void m18861a(String str, int[] iArr, int[] iArr2) {
        C3459c.m18901f(str, iArr, iArr2);
    }

    public static void m18862a(String str, long[] jArr, long[] jArr2) {
        C3459c.m18901f(str, jArr, jArr2);
    }

    public static void m18863a(String str, Object[] objArr, Object[] objArr2) {
        C3459c.m18901f(str, objArr, objArr2);
    }

    public static void m18864a(String str, short[] sArr, short[] sArr2) {
        C3459c.m18901f(str, sArr, sArr2);
    }

    public static void m18865a(String str, boolean[] zArr, boolean[] zArr2) {
        C3459c.m18901f(str, zArr, zArr2);
    }

    public static void m18866a(boolean z) {
        C3459c.m18856a(null, z);
    }

    public static void m18867a(byte[] bArr, byte[] bArr2) {
        C3459c.m18857a(null, bArr, bArr2);
    }

    public static void m18868a(char[] cArr, char[] cArr2) {
        C3459c.m18858a(null, cArr, cArr2);
    }

    public static void m18869a(double[] dArr, double[] dArr2, double d) {
        C3459c.m18859a(null, dArr, dArr2, d);
    }

    public static void m18870a(float[] fArr, float[] fArr2, float f) {
        C3459c.m18860a(null, fArr, fArr2, f);
    }

    public static void m18871a(int[] iArr, int[] iArr2) {
        C3459c.m18861a(null, iArr, iArr2);
    }

    public static void m18872a(long[] jArr, long[] jArr2) {
        C3459c.m18862a(null, jArr, jArr2);
    }

    public static void m18873a(Object[] objArr, Object[] objArr2) {
        C3459c.m18863a(null, objArr, objArr2);
    }

    public static void m18874a(short[] sArr, short[] sArr2) {
        C3459c.m18864a(null, sArr, sArr2);
    }

    public static void m18875a(boolean[] zArr, boolean[] zArr2) {
        C3459c.m18865a(null, zArr, zArr2);
    }

    public static void m18876b(double d, double d2, double d3) {
        C3459c.m18882b(null, d, d2, d3);
    }

    public static void m18877b(float f, float f2, float f3) {
        C3459c.m18851a(null, f, f2, f3);
    }

    public static void m18878b(long j, long j2) {
        C3459c.m18884b(null, j, j2);
    }

    public static void m18879b(Object obj) {
        C3459c.m18885b(null, obj);
    }

    public static void m18880b(Object obj, Object obj2) {
        C3459c.m18886b(null, obj, obj2);
    }

    private static void m18881b(String str) {
        String str2 = C2915a.f14760f;
        if (str != null) {
            str2 = str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        }
        C3459c.m18848a(str2 + "expected not same");
    }

    public static void m18882b(String str, double d, double d2, double d3) {
        if (C3459c.m18894c(d, d2, d3)) {
            C3459c.m18904h(str, Double.valueOf(d), Double.valueOf(d2));
        }
    }

    public static void m18883b(String str, float f, float f2, float f3) {
        if (!C3459c.m18895c(f, f2, f3)) {
            C3459c.m18892c(str, Float.valueOf(f2));
        }
    }

    public static void m18884b(String str, long j, long j2) {
        if (j != j2) {
            C3459c.m18904h(str, Long.valueOf(j), Long.valueOf(j2));
        }
    }

    public static void m18885b(String str, Object obj) {
        if (obj != null) {
            C3459c.m18897d(str, obj);
        }
    }

    public static void m18886b(String str, Object obj, Object obj2) {
        if (C3459c.m18900e(obj, obj2)) {
            C3459c.m18892c(str, obj2);
        }
    }

    public static void m18887b(String str, boolean z) {
        C3459c.m18856a(str, !z);
    }

    @Deprecated
    public static void m18888b(String str, Object[] objArr, Object[] objArr2) {
        C3459c.m18863a(str, objArr, objArr2);
    }

    public static void m18889b(boolean z) {
        C3459c.m18887b(null, z);
    }

    @Deprecated
    public static void m18890b(Object[] objArr, Object[] objArr2) {
        C3459c.m18873a(objArr, objArr2);
    }

    public static void m18891c(Object obj, Object obj2) {
        C3459c.m18893c(null, obj, obj2);
    }

    private static void m18892c(String str, Object obj) {
        String str2 = "Values should be different. ";
        if (str != null) {
            str2 = str + ". ";
        }
        C3459c.m18848a(str2 + "Actual: " + obj);
    }

    public static void m18893c(String str, Object obj, Object obj2) {
        if (obj != obj2) {
            C3459c.m18903g(str, obj, obj2);
        }
    }

    private static boolean m18894c(double d, double d2, double d3) {
        return Double.compare(d, d2) != 0 && Math.abs(d - d2) > d3;
    }

    private static boolean m18895c(float f, float f2, float f3) {
        return Float.compare(f, f2) != 0 && Math.abs(f - f2) > f3;
    }

    public static void m18896d(Object obj, Object obj2) {
        C3459c.m18898d(null, obj, obj2);
    }

    private static void m18897d(String str, Object obj) {
        String str2 = C2915a.f14760f;
        if (str != null) {
            str2 = str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        }
        C3459c.m18848a(str2 + "expected null, but was:<" + obj + ">");
    }

    public static void m18898d(String str, Object obj, Object obj2) {
        if (obj == obj2) {
            C3459c.m18881b(str);
        }
    }

    static String m18899e(String str, Object obj, Object obj2) {
        String str2 = C2915a.f14760f;
        if (!(str == null || str.equals(C2915a.f14760f))) {
            str2 = str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        }
        String valueOf = String.valueOf(obj);
        String valueOf2 = String.valueOf(obj2);
        return valueOf.equals(valueOf2) ? str2 + "expected: " + C3459c.m18839a(obj, valueOf) + " but was: " + C3459c.m18839a(obj2, valueOf2) : str2 + "expected:<" + valueOf + "> but was:<" + valueOf2 + ">";
    }

    private static boolean m18900e(Object obj, Object obj2) {
        return obj == null ? obj2 == null : C3459c.m18902f(obj, obj2);
    }

    private static void m18901f(String str, Object obj, Object obj2) {
        new C3448e().m18806a(str, obj, obj2);
    }

    private static boolean m18902f(Object obj, Object obj2) {
        return obj.equals(obj2);
    }

    private static void m18903g(String str, Object obj, Object obj2) {
        String str2 = C2915a.f14760f;
        if (str != null) {
            str2 = str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        }
        C3459c.m18848a(str2 + "expected same:<" + obj + "> was not:<" + obj2 + ">");
    }

    private static void m18904h(String str, Object obj, Object obj2) {
        C3459c.m18848a(C3459c.m18899e(str, obj, obj2));
    }
}
