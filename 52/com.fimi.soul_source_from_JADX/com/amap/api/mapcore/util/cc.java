package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class cc {
    public static final String f2315a;
    static final String f2316b;
    static final String f2317c;
    static final String f2318d;
    public static final String f2319e;

    /* renamed from: com.amap.api.mapcore.util.cc.1 */
    final class C03651 implements Runnable {
        final /* synthetic */ Context f2309a;
        final /* synthetic */ int f2310b;
        final /* synthetic */ Throwable f2311c;
        final /* synthetic */ String f2312d;
        final /* synthetic */ String f2313e;

        C03651(Context context, int i, Throwable th, String str, String str2) {
            this.f2309a = context;
            this.f2310b = i;
            this.f2311c = th;
            this.f2312d = str;
            this.f2313e = str2;
        }

        public void run() {
            try {
                ci a = cc.m3812a(this.f2309a, this.f2310b);
                if (a != null) {
                    a.m3857a(this.f2309a, this.f2311c, this.f2312d, this.f2313e);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.util.cc.2 */
    final class C03662 implements Runnable {
        final /* synthetic */ Context f2314a;

        C03662(Context context) {
            this.f2314a = context;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r7 = this;
            r0 = 0;
            r1 = r7.f2314a;	 Catch:{ RejectedExecutionException -> 0x006d, Throwable -> 0x003a, all -> 0x0057 }
            r2 = 0;
            r2 = com.amap.api.mapcore.util.cc.m3812a(r1, r2);	 Catch:{ RejectedExecutionException -> 0x006d, Throwable -> 0x003a, all -> 0x0057 }
            r1 = r7.f2314a;	 Catch:{ RejectedExecutionException -> 0x00a9, Throwable -> 0x0094, all -> 0x007d }
            r3 = 1;
            r1 = com.amap.api.mapcore.util.cc.m3812a(r1, r3);	 Catch:{ RejectedExecutionException -> 0x00a9, Throwable -> 0x0094, all -> 0x007d }
            r3 = r7.f2314a;	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x009b, all -> 0x0084 }
            r4 = 2;
            r0 = com.amap.api.mapcore.util.cc.m3812a(r3, r4);	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x009b, all -> 0x0084 }
            r3 = r7.f2314a;	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
            r2.m3866c(r3);	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
            r3 = r7.f2314a;	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
            r1.m3866c(r3);	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
            r3 = r7.f2314a;	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
            r0.m3866c(r3);	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
            r3 = r7.f2314a;	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
            com.amap.api.mapcore.util.bz.m3795a(r3);	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
            if (r2 == 0) goto L_0x002f;
        L_0x002c:
            r2.m3865c();
        L_0x002f:
            if (r1 == 0) goto L_0x0034;
        L_0x0031:
            r1.m3865c();
        L_0x0034:
            if (r0 == 0) goto L_0x0039;
        L_0x0036:
            r0.m3865c();
        L_0x0039:
            return;
        L_0x003a:
            r1 = move-exception;
            r2 = r0;
            r3 = r0;
            r6 = r1;
            r1 = r0;
            r0 = r6;
        L_0x0040:
            r4 = "Log";
            r5 = "processLog";
            com.amap.api.mapcore.util.cb.m3809a(r0, r4, r5);	 Catch:{ all -> 0x0092 }
            if (r3 == 0) goto L_0x004c;
        L_0x0049:
            r3.m3865c();
        L_0x004c:
            if (r2 == 0) goto L_0x0051;
        L_0x004e:
            r2.m3865c();
        L_0x0051:
            if (r1 == 0) goto L_0x0039;
        L_0x0053:
            r1.m3865c();
            goto L_0x0039;
        L_0x0057:
            r1 = move-exception;
            r2 = r0;
            r3 = r0;
            r6 = r1;
            r1 = r0;
            r0 = r6;
        L_0x005d:
            if (r3 == 0) goto L_0x0062;
        L_0x005f:
            r3.m3865c();
        L_0x0062:
            if (r2 == 0) goto L_0x0067;
        L_0x0064:
            r2.m3865c();
        L_0x0067:
            if (r1 == 0) goto L_0x006c;
        L_0x0069:
            r1.m3865c();
        L_0x006c:
            throw r0;
        L_0x006d:
            r1 = move-exception;
            r1 = r0;
            r2 = r0;
        L_0x0070:
            if (r2 == 0) goto L_0x0075;
        L_0x0072:
            r2.m3865c();
        L_0x0075:
            if (r1 == 0) goto L_0x007a;
        L_0x0077:
            r1.m3865c();
        L_0x007a:
            if (r0 == 0) goto L_0x0039;
        L_0x007c:
            goto L_0x0036;
        L_0x007d:
            r1 = move-exception;
            r3 = r2;
            r2 = r0;
            r6 = r0;
            r0 = r1;
            r1 = r6;
            goto L_0x005d;
        L_0x0084:
            r3 = move-exception;
            r6 = r3;
            r3 = r2;
            r2 = r1;
            r1 = r0;
            r0 = r6;
            goto L_0x005d;
        L_0x008b:
            r3 = move-exception;
            r6 = r3;
            r3 = r2;
            r2 = r1;
            r1 = r0;
            r0 = r6;
            goto L_0x005d;
        L_0x0092:
            r0 = move-exception;
            goto L_0x005d;
        L_0x0094:
            r1 = move-exception;
            r3 = r2;
            r2 = r0;
            r6 = r0;
            r0 = r1;
            r1 = r6;
            goto L_0x0040;
        L_0x009b:
            r3 = move-exception;
            r6 = r3;
            r3 = r2;
            r2 = r1;
            r1 = r0;
            r0 = r6;
            goto L_0x0040;
        L_0x00a2:
            r3 = move-exception;
            r6 = r3;
            r3 = r2;
            r2 = r1;
            r1 = r0;
            r0 = r6;
            goto L_0x0040;
        L_0x00a9:
            r1 = move-exception;
            r1 = r0;
            goto L_0x0070;
        L_0x00ac:
            r3 = move-exception;
            goto L_0x0070;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.cc.2.run():void");
        }
    }

    static {
        f2315a = "/a/";
        f2316b = "b";
        f2317c = "c";
        f2318d = "d";
        f2319e = "e";
    }

    static ci m3812a(Context context, int i) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return new cg(i);
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return new ch(i);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return new cf(i);
            default:
                return null;
        }
    }

    public static Class<? extends ct> m3813a(int i) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return co.class;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return cq.class;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return cn.class;
            default:
                return null;
        }
    }

    public static String m3814a(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getFilesDir().getAbsolutePath());
        stringBuilder.append(f2315a);
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    static void m3815a(Context context) {
        try {
            ci a = m3812a(context, 2);
            if (a != null) {
                a.m3864b(context);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    static void m3816a(Context context, Throwable th, int i, String str, String str2) {
        try {
            ExecutorService c = ce.m3831c();
            if (c != null && !c.isShutdown()) {
                c.submit(new C03651(context, i, th, str, str2));
            }
        } catch (RejectedExecutionException e) {
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public static ct m3817b(int i) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return new co();
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return new cq();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return new cn();
            default:
                return null;
        }
    }

    static void m3818b(Context context) {
        try {
            ExecutorService c = ce.m3831c();
            if (c != null && !c.isShutdown()) {
                c.submit(new C03662(context));
            }
        } catch (Throwable th) {
            cb.m3809a(th, "Log", "processLog");
        }
    }

    public static String m3819c(int i) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return f2317c;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return f2316b;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return f2318d;
            default:
                return C2915a.f14760f;
        }
    }
}
