package org.p122a.p123a.p167i.p168a;

import com.fimi.soul.biz.camera.C1314u;
import java.util.Locale;
import org.apache.http.impl.auth.NTLMEngineException;

/* renamed from: org.a.a.i.a.n */
class C3068n extends C3067m {
    protected byte[] f15201a;
    protected byte[] f15202b;

    C3068n(String str, String str2) {
        byte[] bArr = null;
        try {
            String d = C3063i.m17232g(str2);
            String e = C3063i.m17234h(str);
            this.f15201a = d != null ? d.getBytes("ASCII") : null;
            if (e != null) {
                bArr = e.toUpperCase(Locale.ENGLISH).getBytes("ASCII");
            }
            this.f15202b = bArr;
        } catch (Throwable e2) {
            throw new NTLMEngineException("Unicode unsupported: " + e2.getMessage(), e2);
        }
    }

    String m17285c() {
        m17275a(40, 1);
        m17284f(-1576500735);
        m17283e(0);
        m17283e(0);
        m17284f(40);
        m17283e(0);
        m17283e(0);
        m17284f(40);
        m17283e(C1314u.f5853F);
        m17284f(2600);
        m17283e(3840);
        return super.m17281c();
    }
}
