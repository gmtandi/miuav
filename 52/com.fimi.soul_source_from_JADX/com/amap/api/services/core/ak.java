package com.amap.api.services.core;

import android.content.Context;
import java.util.List;
import org.codehaus.jackson.smile.SmileConstants;

public class ak {
    private ai f3006a;

    public ak(Context context) {
        this.f3006a = new ai(context);
    }

    private al m4528a(int i) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return new ag();
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return new aj();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return new af();
            default:
                return null;
        }
    }

    private void m4529a(am amVar, al alVar) {
        alVar.m4515a(amVar);
        this.f3006a.m4523a(alVar);
    }

    private void m4530b(am amVar, al alVar) {
        String a = al.m4513a(amVar.m4540b());
        List c = this.f3006a.m4526c(a, alVar);
        if (c == null || c.size() == 0) {
            alVar.m4515a(amVar);
            this.f3006a.m4523a(alVar);
            return;
        }
        am amVar2 = (am) c.get(0);
        if (amVar.m4537a() == 0) {
            amVar2.m4541b(amVar2.m4544d() + 1);
        } else {
            amVar2.m4541b(0);
        }
        alVar.m4515a(amVar2);
        this.f3006a.m4525b(a, alVar);
    }

    private void m4531c(String str, int i) {
        this.f3006a.m4524a(al.m4513a(str), m4528a(i));
    }

    public List<am> m4532a(int i, int i2) {
        List<am> list = null;
        try {
            ap a = m4528a(i2);
            list = this.f3006a.m4526c(al.m4512a(i), a);
        } catch (Throwable th) {
            ay.m4590a(th, "LogDB", "ByState");
            th.printStackTrace();
        }
        return list;
    }

    public void m4533a(am amVar, int i) {
        try {
            ap a = m4528a(i);
            a.m4509a(amVar);
            this.f3006a.m4525b(al.m4513a(amVar.m4540b()), a);
        } catch (Throwable th) {
            ay.m4590a(th, "LogDB", "updateLogInfo");
            th.printStackTrace();
        }
    }

    public void m4534a(String str, int i) {
        try {
            m4531c(str, i);
        } catch (Throwable th) {
            ay.m4590a(th, "LogDB", "delLog");
            th.printStackTrace();
        }
    }

    public void m4535b(am amVar, int i) {
        try {
            al a = m4528a(i);
            switch (i) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    m4529a(amVar, a);
                    return;
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    m4530b(amVar, a);
                    return;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    m4530b(amVar, a);
                    return;
                default:
                    return;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        th.printStackTrace();
    }

    public void m4536b(String str, int i) {
        try {
            m4531c(str, i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
