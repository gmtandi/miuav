package p001b.p002b;

import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;

@Deprecated
/* renamed from: b.b.a */
public class C0119a {
    protected C0119a() {
    }

    public static void m152a(byte b, byte b2) {
        C0119a.m161a(null, b, b2);
    }

    public static void m153a(char c, char c2) {
        C0119a.m162a(null, c, c2);
    }

    public static void m154a(double d, double d2, double d3) {
        C0119a.m163a(null, d, d2, d3);
    }

    public static void m155a(float f, float f2, float f3) {
        C0119a.m164a(null, f, f2, f3);
    }

    public static void m156a(int i, int i2) {
        C0119a.m165a(null, i, i2);
    }

    public static void m157a(long j, long j2) {
        C0119a.m166a(null, j, j2);
    }

    public static void m158a(Object obj) {
        C0119a.m167a(null, obj);
    }

    public static void m159a(Object obj, Object obj2) {
        C0119a.m168a(null, obj, obj2);
    }

    public static void m160a(String str) {
        if (str == null) {
            throw new C0125b();
        }
        throw new C0125b(str);
    }

    public static void m161a(String str, byte b, byte b2) {
        C0119a.m168a(str, Byte.valueOf(b), Byte.valueOf(b2));
    }

    public static void m162a(String str, char c, char c2) {
        C0119a.m168a(str, Character.valueOf(c), Character.valueOf(c2));
    }

    public static void m163a(String str, double d, double d2, double d3) {
        if (Double.compare(d, d2) != 0 && Math.abs(d - d2) > d3) {
            C0119a.m188e(str, new Double(d), new Double(d2));
        }
    }

    public static void m164a(String str, float f, float f2, float f3) {
        if (Float.compare(f, f2) != 0 && Math.abs(f - f2) > f3) {
            C0119a.m188e(str, new Float(f), new Float(f2));
        }
    }

    public static void m165a(String str, int i, int i2) {
        C0119a.m168a(str, Integer.valueOf(i), Integer.valueOf(i2));
    }

    public static void m166a(String str, long j, long j2) {
        C0119a.m168a(str, Long.valueOf(j), Long.valueOf(j2));
    }

    public static void m167a(String str, Object obj) {
        C0119a.m172a(str, obj != null);
    }

    public static void m168a(String str, Object obj, Object obj2) {
        if (obj != null || obj2 != null) {
            if (obj == null || !obj.equals(obj2)) {
                C0119a.m188e(str, obj, obj2);
            }
        }
    }

    public static void m169a(String str, String str2) {
        C0119a.m170a(null, str, str2);
    }

    public static void m170a(String str, String str2, String str3) {
        if (str2 != null || str3 != null) {
            if (str2 == null || !str2.equals(str3)) {
                if (str == null) {
                    str = C2915a.f14760f;
                }
                throw new C0127d(str, str2, str3);
            }
        }
    }

    public static void m171a(String str, short s, short s2) {
        C0119a.m168a(str, Short.valueOf(s), Short.valueOf(s2));
    }

    public static void m172a(String str, boolean z) {
        if (!z) {
            C0119a.m160a(str);
        }
    }

    public static void m173a(String str, boolean z, boolean z2) {
        C0119a.m168a(str, Boolean.valueOf(z), Boolean.valueOf(z2));
    }

    public static void m174a(short s, short s2) {
        C0119a.m171a(null, s, s2);
    }

    public static void m175a(boolean z) {
        C0119a.m172a(null, z);
    }

    public static void m176a(boolean z, boolean z2) {
        C0119a.m173a(null, z, z2);
    }

    public static void m177b(Object obj) {
        if (obj != null) {
            C0119a.m180b("Expected: <null> but was: " + obj.toString(), obj);
        }
    }

    public static void m178b(Object obj, Object obj2) {
        C0119a.m181b(null, obj, obj2);
    }

    public static void m179b(String str) {
        C0119a.m160a((str != null ? str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR : C2915a.f14760f) + "expected not same");
    }

    public static void m180b(String str, Object obj) {
        C0119a.m172a(str, obj == null);
    }

    public static void m181b(String str, Object obj, Object obj2) {
        if (obj != obj2) {
            C0119a.m186d(str, obj, obj2);
        }
    }

    public static void m182b(String str, boolean z) {
        C0119a.m172a(str, !z);
    }

    public static void m183b(boolean z) {
        C0119a.m182b(null, z);
    }

    public static void m184c(Object obj, Object obj2) {
        C0119a.m185c(null, obj, obj2);
    }

    public static void m185c(String str, Object obj, Object obj2) {
        if (obj == obj2) {
            C0119a.m179b(str);
        }
    }

    public static void m186d(String str, Object obj, Object obj2) {
        C0119a.m160a((str != null ? str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR : C2915a.f14760f) + "expected same:<" + obj + "> was not:<" + obj2 + ">");
    }

    public static void m187e() {
        C0119a.m160a(null);
    }

    public static void m188e(String str, Object obj, Object obj2) {
        C0119a.m160a(C0119a.m189f(str, obj, obj2));
    }

    public static String m189f(String str, Object obj, Object obj2) {
        String str2 = C2915a.f14760f;
        if (str != null && str.length() > 0) {
            str2 = str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        }
        return str2 + "expected:<" + obj + "> but was:<" + obj2 + ">";
    }
}
