package com.p016a;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.n */
public class C0255n {
    static final String f1297a;
    static final String f1298b;
    static final String f1299c;
    static final String f1300d;

    static {
        f1297a = "/a/";
        f1298b = "b";
        f1299c = "c";
        f1300d = "d";
    }

    static C0251r m2028a(Context context, int i) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return new C0253l(i);
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return new C0254m(i);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return new C0252k(i);
            default:
                return null;
        }
    }

    public static String m2029a(int i) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return C0263y.f1318b;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return C0263y.f1319c;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return C0263y.f1320d;
            default:
                return C2915a.f14760f;
        }
    }

    static void m2030a(Context context) {
        try {
            C0251r a = C0255n.m2028a(context, 2);
            if (a != null) {
                a.m2016b(context);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    static void m2031a(Context context, gd gdVar, String str) {
        try {
            if (gdVar.m1943e()) {
                ExecutorService b = C0248h.m1978b();
                if (b != null && !b.isShutdown()) {
                    b.submit(new C0256o(context, gdVar, str));
                }
            }
        } catch (RejectedExecutionException e) {
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    static void m2032a(Context context, Throwable th, int i, String str, String str2) {
        try {
            ExecutorService b = C0248h.m1978b();
            if (b != null && !b.isShutdown()) {
                b.submit(new C0257p(context, i, th, str, str2));
            }
        } catch (RejectedExecutionException e) {
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public static String m2033b(int i) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return f1299c;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return f1298b;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return f1300d;
            default:
                return C2915a.f14760f;
        }
    }

    static void m2034b(Context context) {
        try {
            ExecutorService b = C0248h.m1978b();
            if (b != null && !b.isShutdown()) {
                b.submit(new C0258q(context));
            }
        } catch (Throwable th) {
            C0247g.m1917a(th, "Log", "processLog");
        }
    }
}
