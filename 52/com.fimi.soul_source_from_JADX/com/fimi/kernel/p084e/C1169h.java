package com.fimi.kernel.p084e;

/* renamed from: com.fimi.kernel.e.h */
final class C1169h implements Runnable {
    final /* synthetic */ Process f5318a;
    final /* synthetic */ StringBuilder f5319b;

    C1169h(Process process, StringBuilder stringBuilder) {
        this.f5318a = process;
        this.f5319b = stringBuilder;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r3 = this;
        r1 = new java.io.BufferedReader;
        r0 = new java.io.InputStreamReader;
        r2 = r3.f5318a;
        r2 = r2.getInputStream();
        r0.<init>(r2);
        r2 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r1.<init>(r0, r2);
    L_0x0012:
        r0 = r1.readLine();	 Catch:{ IOException -> 0x0024 }
        if (r0 == 0) goto L_0x002c;
    L_0x0018:
        r2 = r3.f5319b;	 Catch:{ IOException -> 0x0024 }
        r0 = r2.append(r0);	 Catch:{ IOException -> 0x0024 }
        r2 = "\n";
        r0.append(r2);	 Catch:{ IOException -> 0x0024 }
        goto L_0x0012;
    L_0x0024:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x003a }
        r1.close();	 Catch:{ IOException -> 0x0035 }
    L_0x002b:
        return;
    L_0x002c:
        r1.close();	 Catch:{ IOException -> 0x0030 }
        goto L_0x002b;
    L_0x0030:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x002b;
    L_0x0035:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x002b;
    L_0x003a:
        r0 = move-exception;
        r1.close();	 Catch:{ IOException -> 0x003f }
    L_0x003e:
        throw r0;
    L_0x003f:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x003e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fimi.kernel.e.h.run():void");
    }
}
