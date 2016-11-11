package com.fimi.kernel.p084e;

/* renamed from: com.fimi.kernel.e.i */
final class C1170i implements Runnable {
    final /* synthetic */ Process f5320a;
    final /* synthetic */ StringBuilder f5321b;

    C1170i(Process process, StringBuilder stringBuilder) {
        this.f5320a = process;
        this.f5321b = stringBuilder;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r3 = this;
        r1 = new java.io.BufferedReader;
        r0 = new java.io.InputStreamReader;
        r2 = r3.f5320a;
        r2 = r2.getErrorStream();
        r0.<init>(r2);
        r2 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r1.<init>(r0, r2);
    L_0x0012:
        r0 = r1.readLine();	 Catch:{ IOException -> 0x0024 }
        if (r0 == 0) goto L_0x002c;
    L_0x0018:
        r2 = r3.f5321b;	 Catch:{ IOException -> 0x0024 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.fimi.kernel.e.i.run():void");
    }
}
