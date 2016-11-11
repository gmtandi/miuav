package com.p016a;

import android.content.Context;
import java.util.List;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.a.z */
public class C0264z {
    private C0261v f1338a;

    public C0264z(Context context) {
        this.f1338a = new C0261v(context, C0263y.m2053c());
    }

    private void m2058a(ab abVar, aa aaVar) {
        aaVar.m971a(abVar);
        this.f1338a.m2045a((C0239w) aaVar);
    }

    private void m2059b(ab abVar, aa aaVar) {
        String a = aa.m968a(abVar.m978b());
        List b = this.f1338a.m2050b(a, aaVar, true);
        if (b == null || b.size() == 0) {
            aaVar.m971a(abVar);
            this.f1338a.m2047a((C0239w) aaVar, true);
            return;
        }
        ab abVar2 = (ab) b.get(0);
        if (abVar.m975a() == 0) {
            abVar2.m979b(abVar2.m982d() + 1);
        } else {
            abVar2.m979b(0);
        }
        aaVar.m971a(abVar2);
        this.f1338a.m2051b(a, aaVar);
    }

    private void m2060c(String str, int i) {
        this.f1338a.m2048a(aa.m968a(str), new aa(i));
    }

    public List<ab> m2061a(int i, int i2) {
        List<ab> list = null;
        try {
            C0239w aaVar = new aa(i2);
            list = this.f1338a.m2052c(aa.m967a(i), aaVar);
        } catch (Throwable th) {
            C0247g.m1917a(th, "LogDB", "ByState");
        }
        return list;
    }

    public void m2062a(ab abVar, int i) {
        try {
            C0239w aaVar = new aa(i);
            aaVar.m965a((Object) abVar);
            this.f1338a.m2051b(aa.m968a(abVar.m978b()), aaVar);
        } catch (Throwable th) {
            C0247g.m1917a(th, "LogDB", "updateLogInfo");
        }
    }

    public void m2063a(String str, int i) {
        try {
            m2060c(str, i);
        } catch (Throwable th) {
            C0247g.m1917a(th, "LogDB", "delLog");
        }
    }

    public void m2064b(ab abVar, int i) {
        try {
            aa aaVar = new aa(i);
            switch (i) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    m2058a(abVar, aaVar);
                    return;
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    m2059b(abVar, aaVar);
                    return;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    m2059b(abVar, aaVar);
                    return;
                default:
                    return;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        th.printStackTrace();
    }

    public void m2065b(String str, int i) {
        try {
            m2060c(str, i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
