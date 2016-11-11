package org.p184b.p185a;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.p184b.C3275p;
import org.p184b.C3276b;
import org.p184b.C3300k;
import org.p184b.C3315n;

/* renamed from: org.b.a.f */
public class C3285f<T> extends C3276b<T> {
    private static final Pattern f15792d;
    private final String f15793a;
    private final C3275p<T> f15794b;
    private final Object[] f15795c;

    static {
        f15792d = Pattern.compile("%([0-9]+)");
    }

    public C3285f(String str, C3275p<T> c3275p, Object[] objArr) {
        this.f15793a = str;
        this.f15794b = c3275p;
        this.f15795c = (Object[]) objArr.clone();
    }

    @C3315n
    public static <T> C3275p<T> m18148a(String str, C3275p<T> c3275p, Object... objArr) {
        return new C3285f(str, c3275p, objArr);
    }

    public void m18149a(Object obj, C3300k c3300k) {
        this.f15794b.m18106a(obj, c3300k);
    }

    public void m18150a(C3300k c3300k) {
        Matcher matcher = f15792d.matcher(this.f15793a);
        int i = 0;
        while (matcher.find()) {
            c3300k.m18222a(this.f15793a.substring(i, matcher.start()));
            c3300k.m18221a(this.f15795c[Integer.parseInt(matcher.group(1))]);
            i = matcher.end();
        }
        if (i < this.f15793a.length()) {
            c3300k.m18222a(this.f15793a.substring(i));
        }
    }

    public boolean m18151a(Object obj) {
        return this.f15794b.m18107a(obj);
    }
}
