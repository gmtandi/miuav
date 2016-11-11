package p001b.p002b;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* renamed from: b.b.k */
public abstract class C0136k extends C0119a implements C0115j {
    private String f149a;

    public C0136k() {
        this.f149a = null;
    }

    public C0136k(String str) {
        this.f149a = str;
    }

    public static void m241b(byte b, byte b2) {
        C0119a.m152a(b, b2);
    }

    public static void m242b(char c, char c2) {
        C0119a.m153a(c, c2);
    }

    public static void m243b(double d, double d2, double d3) {
        C0119a.m154a(d, d2, d3);
    }

    public static void m244b(float f, float f2, float f3) {
        C0119a.m155a(f, f2, f3);
    }

    public static void m245b(int i, int i2) {
        C0119a.m156a(i, i2);
    }

    public static void m246b(long j, long j2) {
        C0119a.m157a(j, j2);
    }

    public static void m247b(String str, byte b, byte b2) {
        C0119a.m161a(str, b, b2);
    }

    public static void m248b(String str, char c, char c2) {
        C0119a.m162a(str, c, c2);
    }

    public static void m249b(String str, double d, double d2, double d3) {
        C0119a.m163a(str, d, d2, d3);
    }

    public static void m250b(String str, float f, float f2, float f3) {
        C0119a.m164a(str, f, f2, f3);
    }

    public static void m251b(String str, int i, int i2) {
        C0119a.m165a(str, i, i2);
    }

    public static void m252b(String str, long j, long j2) {
        C0119a.m166a(str, j, j2);
    }

    public static void m253b(String str, String str2) {
        C0119a.m169a(str, str2);
    }

    public static void m254b(String str, String str2, String str3) {
        C0119a.m170a(str, str2, str3);
    }

    public static void m255b(String str, short s, short s2) {
        C0119a.m171a(str, s, s2);
    }

    public static void m256b(String str, boolean z, boolean z2) {
        C0119a.m173a(str, z, z2);
    }

    public static void m257b(short s, short s2) {
        C0119a.m174a(s, s2);
    }

    public static void m258b(boolean z, boolean z2) {
        C0119a.m176a(z, z2);
    }

    public static void m259c(Object obj) {
        C0119a.m158a(obj);
    }

    public static void m260c(String str) {
        C0119a.m160a(str);
    }

    public static void m261c(String str, Object obj) {
        C0119a.m167a(str, obj);
    }

    public static void m262c(String str, boolean z) {
        C0119a.m172a(str, z);
    }

    public static void m263c(boolean z) {
        C0119a.m175a(z);
    }

    public static void m264d(Object obj) {
        C0119a.m177b(obj);
    }

    public static void m265d(Object obj, Object obj2) {
        C0119a.m159a(obj, obj2);
    }

    public static void m266d(String str) {
        C0119a.m179b(str);
    }

    public static void m267d(String str, Object obj) {
        C0119a.m180b(str, obj);
    }

    public static void m268d(String str, boolean z) {
        C0119a.m182b(str, z);
    }

    public static void m269d(boolean z) {
        C0119a.m183b(z);
    }

    public static void m270e(Object obj, Object obj2) {
        C0119a.m178b(obj, obj2);
    }

    public static void m271f(Object obj, Object obj2) {
        C0119a.m184c(obj, obj2);
    }

    public static void m272g() {
        C0119a.m187e();
    }

    public static void m273g(String str, Object obj, Object obj2) {
        C0119a.m168a(str, obj, obj2);
    }

    public static void m274h(String str, Object obj, Object obj2) {
        C0119a.m181b(str, obj, obj2);
    }

    public static void m275i(String str, Object obj, Object obj2) {
        C0119a.m185c(str, obj, obj2);
    }

    public static void m276j(String str, Object obj, Object obj2) {
        C0119a.m186d(str, obj, obj2);
    }

    public static void m277k(String str, Object obj, Object obj2) {
        C0119a.m188e(str, obj, obj2);
    }

    public static String m278l(String str, Object obj, Object obj2) {
        return C0119a.m189f(str, obj, obj2);
    }

    public int m279a() {
        return 1;
    }

    public void m280a(C0139n c0139n) {
        c0139n.m304a(this);
    }

    protected C0139n m281b() {
        return new C0139n();
    }

    public C0139n m282c() {
        C0139n b = m281b();
        m280a(b);
        return b;
    }

    public void m283d() {
        Throwable th;
        Throwable th2 = null;
        m286h();
        try {
            m285f();
            try {
                m287i();
            } catch (Throwable th3) {
                th = th3;
                if (null != null) {
                    th = null;
                }
                th2 = th;
            }
        } catch (Throwable th4) {
            if (null == null) {
            }
        }
        if (th2 != null) {
            throw th2;
        }
    }

    public void m284e(String str) {
        this.f149a = str;
    }

    protected void m285f() {
        Method method;
        C0136k.m261c("TestCase.fName cannot be null", this.f149a);
        try {
            method = getClass().getMethod(this.f149a, (Class[]) null);
        } catch (NoSuchMethodException e) {
            C0136k.m260c("Method \"" + this.f149a + "\" not found");
            method = null;
        }
        if (!Modifier.isPublic(method.getModifiers())) {
            C0136k.m260c("Method \"" + this.f149a + "\" should be public");
        }
        try {
            method.invoke(this, new Object[0]);
        } catch (InvocationTargetException e2) {
            e2.fillInStackTrace();
            throw e2.getTargetException();
        } catch (IllegalAccessException e3) {
            e3.fillInStackTrace();
            throw e3;
        }
    }

    protected void m286h() {
    }

    protected void m287i() {
    }

    public String m288j() {
        return this.f149a;
    }

    public String toString() {
        return m288j() + "(" + getClass().getName() + ")";
    }
}
