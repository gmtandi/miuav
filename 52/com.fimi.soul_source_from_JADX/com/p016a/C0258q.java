package com.p016a;

import android.content.Context;

/* renamed from: com.a.q */
final class C0258q implements Runnable {
    final /* synthetic */ Context f1309a;

    C0258q(Context context) {
        this.f1309a = context;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r7 = this;
        r0 = 0;
        r1 = r7.f1309a;	 Catch:{ RejectedExecutionException -> 0x0068, Throwable -> 0x0035, all -> 0x0052 }
        r2 = 0;
        r2 = com.p016a.C0255n.m2028a(r1, r2);	 Catch:{ RejectedExecutionException -> 0x0068, Throwable -> 0x0035, all -> 0x0052 }
        r1 = r7.f1309a;	 Catch:{ RejectedExecutionException -> 0x00a4, Throwable -> 0x008f, all -> 0x0078 }
        r3 = 1;
        r1 = com.p016a.C0255n.m2028a(r1, r3);	 Catch:{ RejectedExecutionException -> 0x00a4, Throwable -> 0x008f, all -> 0x0078 }
        r3 = r7.f1309a;	 Catch:{ RejectedExecutionException -> 0x00a7, Throwable -> 0x0096, all -> 0x007f }
        r4 = 2;
        r0 = com.p016a.C0255n.m2028a(r3, r4);	 Catch:{ RejectedExecutionException -> 0x00a7, Throwable -> 0x0096, all -> 0x007f }
        r3 = r7.f1309a;	 Catch:{ RejectedExecutionException -> 0x00a7, Throwable -> 0x009d, all -> 0x0086 }
        r2.m2018c(r3);	 Catch:{ RejectedExecutionException -> 0x00a7, Throwable -> 0x009d, all -> 0x0086 }
        r3 = r7.f1309a;	 Catch:{ RejectedExecutionException -> 0x00a7, Throwable -> 0x009d, all -> 0x0086 }
        r1.m2018c(r3);	 Catch:{ RejectedExecutionException -> 0x00a7, Throwable -> 0x009d, all -> 0x0086 }
        r3 = r7.f1309a;	 Catch:{ RejectedExecutionException -> 0x00a7, Throwable -> 0x009d, all -> 0x0086 }
        r0.m2018c(r3);	 Catch:{ RejectedExecutionException -> 0x00a7, Throwable -> 0x009d, all -> 0x0086 }
        if (r2 == 0) goto L_0x002a;
    L_0x0027:
        r2.m2017c();
    L_0x002a:
        if (r1 == 0) goto L_0x002f;
    L_0x002c:
        r1.m2017c();
    L_0x002f:
        if (r0 == 0) goto L_0x0034;
    L_0x0031:
        r0.m2017c();
    L_0x0034:
        return;
    L_0x0035:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r6 = r1;
        r1 = r0;
        r0 = r6;
    L_0x003b:
        r4 = "Log";
        r5 = "processLog";
        com.p016a.C0247g.m1917a(r0, r4, r5);	 Catch:{ all -> 0x008d }
        if (r3 == 0) goto L_0x0047;
    L_0x0044:
        r3.m2017c();
    L_0x0047:
        if (r2 == 0) goto L_0x004c;
    L_0x0049:
        r2.m2017c();
    L_0x004c:
        if (r1 == 0) goto L_0x0034;
    L_0x004e:
        r1.m2017c();
        goto L_0x0034;
    L_0x0052:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r6 = r1;
        r1 = r0;
        r0 = r6;
    L_0x0058:
        if (r3 == 0) goto L_0x005d;
    L_0x005a:
        r3.m2017c();
    L_0x005d:
        if (r2 == 0) goto L_0x0062;
    L_0x005f:
        r2.m2017c();
    L_0x0062:
        if (r1 == 0) goto L_0x0067;
    L_0x0064:
        r1.m2017c();
    L_0x0067:
        throw r0;
    L_0x0068:
        r1 = move-exception;
        r1 = r0;
        r2 = r0;
    L_0x006b:
        if (r2 == 0) goto L_0x0070;
    L_0x006d:
        r2.m2017c();
    L_0x0070:
        if (r1 == 0) goto L_0x0075;
    L_0x0072:
        r1.m2017c();
    L_0x0075:
        if (r0 == 0) goto L_0x0034;
    L_0x0077:
        goto L_0x0031;
    L_0x0078:
        r1 = move-exception;
        r3 = r2;
        r2 = r0;
        r6 = r0;
        r0 = r1;
        r1 = r6;
        goto L_0x0058;
    L_0x007f:
        r3 = move-exception;
        r6 = r3;
        r3 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0058;
    L_0x0086:
        r3 = move-exception;
        r6 = r3;
        r3 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0058;
    L_0x008d:
        r0 = move-exception;
        goto L_0x0058;
    L_0x008f:
        r1 = move-exception;
        r3 = r2;
        r2 = r0;
        r6 = r0;
        r0 = r1;
        r1 = r6;
        goto L_0x003b;
    L_0x0096:
        r3 = move-exception;
        r6 = r3;
        r3 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x003b;
    L_0x009d:
        r3 = move-exception;
        r6 = r3;
        r3 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x003b;
    L_0x00a4:
        r1 = move-exception;
        r1 = r0;
        goto L_0x006b;
    L_0x00a7:
        r3 = move-exception;
        goto L_0x006b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.q.run():void");
    }
}
