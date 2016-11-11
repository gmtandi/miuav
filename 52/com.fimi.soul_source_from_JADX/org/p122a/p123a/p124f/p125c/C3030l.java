package org.p122a.p123a.p124f.p125c;

import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import org.p122a.p123a.p150a.C2912b;

@C2912b
/* renamed from: org.a.a.f.c.l */
public class C3030l {
    public static SSLContext m17120a() {
        try {
            SSLContext instance = SSLContext.getInstance(C3026h.f15062a);
            instance.init(null, null, null);
            return instance;
        } catch (Throwable e) {
            throw new C3031m(e.getMessage(), e);
        } catch (Throwable e2) {
            throw new C3031m(e2.getMessage(), e2);
        }
    }

    public static SSLContext m17121b() {
        try {
            return SSLContext.getInstance("Default");
        } catch (NoSuchAlgorithmException e) {
            return C3030l.m17120a();
        }
    }

    public static C3027i m17122c() {
        return new C3027i();
    }
}
