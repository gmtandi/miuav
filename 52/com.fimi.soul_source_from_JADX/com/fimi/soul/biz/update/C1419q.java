package com.fimi.soul.biz.update;

import com.fimi.soul.entity.UpdateVersonBean;

/* renamed from: com.fimi.soul.biz.update.q */
class C1419q implements Runnable {
    final /* synthetic */ UpdateVersonBean f6369a;
    final /* synthetic */ String f6370b;
    final /* synthetic */ C1403p f6371c;

    C1419q(C1403p c1403p, UpdateVersonBean updateVersonBean, String str) {
        this.f6371c = c1403p;
        this.f6369a = updateVersonBean;
        this.f6370b = str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r8 = this;
        r1 = 0;
        r4 = 0;
        r0 = new java.net.URL;	 Catch:{ Exception -> 0x0101, all -> 0x00e2 }
        r2 = r8.f6369a;	 Catch:{ Exception -> 0x0101, all -> 0x00e2 }
        r2 = r2.getUrl();	 Catch:{ Exception -> 0x0101, all -> 0x00e2 }
        r0.<init>(r2);	 Catch:{ Exception -> 0x0101, all -> 0x00e2 }
        r0 = r0.openConnection();	 Catch:{ Exception -> 0x0101, all -> 0x00e2 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x0101, all -> 0x00e2 }
        r2 = 50000; // 0xc350 float:7.0065E-41 double:2.47033E-319;
        r0.setConnectTimeout(r2);	 Catch:{ Exception -> 0x0101, all -> 0x00e2 }
        r2 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
        r0.setReadTimeout(r2);	 Catch:{ Exception -> 0x0101, all -> 0x00e2 }
        r2 = "GET";
        r0.setRequestMethod(r2);	 Catch:{ Exception -> 0x0101, all -> 0x00e2 }
        r5 = new java.io.File;	 Catch:{ Exception -> 0x0101, all -> 0x00e2 }
        r2 = r8.f6370b;	 Catch:{ Exception -> 0x0101, all -> 0x00e2 }
        r5.<init>(r2);	 Catch:{ Exception -> 0x0101, all -> 0x00e2 }
        r2 = r5.exists();	 Catch:{ Exception -> 0x0101, all -> 0x00e2 }
        if (r2 != 0) goto L_0x0034;
    L_0x0031:
        r5.createNewFile();	 Catch:{ Exception -> 0x0101, all -> 0x00e2 }
    L_0x0034:
        r2 = r0.getResponseCode();	 Catch:{ Exception -> 0x0101, all -> 0x00e2 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 != r3) goto L_0x0108;
    L_0x003c:
        r3 = r0.getInputStream();	 Catch:{ Exception -> 0x0101, all -> 0x00e2 }
        r2 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0104, all -> 0x00f7 }
        r2.<init>(r5);	 Catch:{ Exception -> 0x0104, all -> 0x00f7 }
        r1 = r8.f6371c;	 Catch:{ Exception -> 0x009b, all -> 0x00f9 }
        r0 = r0.getContentLength();	 Catch:{ Exception -> 0x009b, all -> 0x00f9 }
        r6 = (long) r0;	 Catch:{ Exception -> 0x009b, all -> 0x00f9 }
        r1.f6314g = r6;	 Catch:{ Exception -> 0x009b, all -> 0x00f9 }
        r0 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        r1 = new byte[r0];	 Catch:{ Exception -> 0x009b, all -> 0x00f9 }
        r0 = r4;
    L_0x0054:
        r4 = r3.read(r1);	 Catch:{ Exception -> 0x009b, all -> 0x00f9 }
        r5 = -1;
        if (r4 == r5) goto L_0x00c2;
    L_0x005b:
        r5 = com.fimi.soul.biz.update.C1403p.f6310c;	 Catch:{ Exception -> 0x009b, all -> 0x00f9 }
        if (r5 == 0) goto L_0x0072;
    L_0x005f:
        if (r3 == 0) goto L_0x0064;
    L_0x0061:
        r3.close();	 Catch:{ Exception -> 0x006d }
    L_0x0064:
        if (r2 == 0) goto L_0x006c;
    L_0x0066:
        r2.flush();	 Catch:{ Exception -> 0x006d }
        r2.close();	 Catch:{ Exception -> 0x006d }
    L_0x006c:
        return;
    L_0x006d:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x006c;
    L_0x0072:
        r5 = com.fimi.soul.biz.update.C1403p.f6309b;	 Catch:{ Exception -> 0x009b, all -> 0x00f9 }
        if (r5 == 0) goto L_0x0089;
    L_0x0076:
        if (r3 == 0) goto L_0x007b;
    L_0x0078:
        r3.close();	 Catch:{ Exception -> 0x0084 }
    L_0x007b:
        if (r2 == 0) goto L_0x006c;
    L_0x007d:
        r2.flush();	 Catch:{ Exception -> 0x0084 }
        r2.close();	 Catch:{ Exception -> 0x0084 }
        goto L_0x006c;
    L_0x0084:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x006c;
    L_0x0089:
        r5 = 0;
        r2.write(r1, r5, r4);	 Catch:{ Exception -> 0x009b, all -> 0x00f9 }
        r0 = r0 + r4;
        r4 = r8.f6371c;	 Catch:{ Exception -> 0x009b, all -> 0x00f9 }
        r5 = 0;
        r6 = r8.f6369a;	 Catch:{ Exception -> 0x009b, all -> 0x00f9 }
        r6 = r6.getSysid();	 Catch:{ Exception -> 0x009b, all -> 0x00f9 }
        r4.m9380a(r5, r0, r6);	 Catch:{ Exception -> 0x009b, all -> 0x00f9 }
        goto L_0x0054;
    L_0x009b:
        r0 = move-exception;
        r0 = r2;
        r1 = r3;
    L_0x009e:
        r2 = com.fimi.soul.biz.update.C1403p.f6309b;	 Catch:{ all -> 0x00fc }
        if (r2 != 0) goto L_0x00af;
    L_0x00a2:
        r2 = r8.f6371c;	 Catch:{ all -> 0x00fc }
        r3 = 1;
        r4 = -2;
        r5 = r8.f6369a;	 Catch:{ all -> 0x00fc }
        r5 = r5.getSysid();	 Catch:{ all -> 0x00fc }
        r2.m9380a(r3, r4, r5);	 Catch:{ all -> 0x00fc }
    L_0x00af:
        if (r1 == 0) goto L_0x00b4;
    L_0x00b1:
        r1.close();	 Catch:{ Exception -> 0x00bd }
    L_0x00b4:
        if (r0 == 0) goto L_0x006c;
    L_0x00b6:
        r0.flush();	 Catch:{ Exception -> 0x00bd }
        r0.close();	 Catch:{ Exception -> 0x00bd }
        goto L_0x006c;
    L_0x00bd:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x006c;
    L_0x00c2:
        r0 = r8.f6371c;	 Catch:{ Exception -> 0x009b, all -> 0x00f9 }
        r1 = 1;
        r4 = 0;
        r5 = r8.f6369a;	 Catch:{ Exception -> 0x009b, all -> 0x00f9 }
        r5 = r5.getSysid();	 Catch:{ Exception -> 0x009b, all -> 0x00f9 }
        r0.m9380a(r1, r4, r5);	 Catch:{ Exception -> 0x009b, all -> 0x00f9 }
    L_0x00cf:
        if (r3 == 0) goto L_0x00d4;
    L_0x00d1:
        r3.close();	 Catch:{ Exception -> 0x00dd }
    L_0x00d4:
        if (r2 == 0) goto L_0x006c;
    L_0x00d6:
        r2.flush();	 Catch:{ Exception -> 0x00dd }
        r2.close();	 Catch:{ Exception -> 0x00dd }
        goto L_0x006c;
    L_0x00dd:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x006c;
    L_0x00e2:
        r0 = move-exception;
        r3 = r1;
    L_0x00e4:
        if (r3 == 0) goto L_0x00e9;
    L_0x00e6:
        r3.close();	 Catch:{ Exception -> 0x00f2 }
    L_0x00e9:
        if (r1 == 0) goto L_0x00f1;
    L_0x00eb:
        r1.flush();	 Catch:{ Exception -> 0x00f2 }
        r1.close();	 Catch:{ Exception -> 0x00f2 }
    L_0x00f1:
        throw r0;
    L_0x00f2:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00f1;
    L_0x00f7:
        r0 = move-exception;
        goto L_0x00e4;
    L_0x00f9:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00e4;
    L_0x00fc:
        r2 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r2;
        goto L_0x00e4;
    L_0x0101:
        r0 = move-exception;
        r0 = r1;
        goto L_0x009e;
    L_0x0104:
        r0 = move-exception;
        r0 = r1;
        r1 = r3;
        goto L_0x009e;
    L_0x0108:
        r2 = r1;
        r3 = r1;
        goto L_0x00cf;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fimi.soul.biz.update.q.run():void");
    }
}
