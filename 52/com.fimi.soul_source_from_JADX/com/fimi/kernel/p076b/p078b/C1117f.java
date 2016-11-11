package com.fimi.kernel.p076b.p078b;

/* renamed from: com.fimi.kernel.b.b.f */
class C1117f implements Runnable {
    final /* synthetic */ C1113b f5160a;
    private boolean f5161b;
    private C1128p f5162c;

    public C1117f(C1113b c1113b, C1128p c1128p) {
        this.f5160a = c1113b;
        this.f5161b = false;
        this.f5162c = c1128p;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r19 = this;
        r3 = 0;
        r4 = 0;
        r0 = r19;
        r2 = r0.f5162c;
        r6 = r2.m7828e();
        r0 = r19;
        r2 = r0.f5162c;	 Catch:{ Exception -> 0x02ab, all -> 0x02a2 }
        r8 = r2.m7825c();	 Catch:{ Exception -> 0x02ab, all -> 0x02a2 }
        r0 = r19;
        r2 = r0.f5162c;	 Catch:{ Exception -> 0x02ab, all -> 0x02a2 }
        r10 = r2.m7827d();	 Catch:{ Exception -> 0x02ab, all -> 0x02a2 }
        r2 = new java.net.URL;	 Catch:{ Exception -> 0x02ab, all -> 0x02a2 }
        r0 = r19;
        r5 = r0.f5162c;	 Catch:{ Exception -> 0x02ab, all -> 0x02a2 }
        r5 = r5.m7819a();	 Catch:{ Exception -> 0x02ab, all -> 0x02a2 }
        r2.<init>(r5);	 Catch:{ Exception -> 0x02ab, all -> 0x02a2 }
        r2 = r2.openConnection();	 Catch:{ Exception -> 0x02ab, all -> 0x02a2 }
        r2 = (java.net.HttpURLConnection) r2;	 Catch:{ Exception -> 0x02ab, all -> 0x02a2 }
        r3 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r2.setConnectTimeout(r3);	 Catch:{ Exception -> 0x02b3, all -> 0x028a }
        r3 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r2.setReadTimeout(r3);	 Catch:{ Exception -> 0x02b3, all -> 0x028a }
        r3 = "GET";
        r2.setRequestMethod(r3);	 Catch:{ Exception -> 0x02b3, all -> 0x028a }
        r3 = "Range";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x02b3, all -> 0x028a }
        r5.<init>();	 Catch:{ Exception -> 0x02b3, all -> 0x028a }
        r12 = "bytes=";
        r5 = r5.append(r12);	 Catch:{ Exception -> 0x02b3, all -> 0x028a }
        r12 = r8 + r6;
        r5 = r5.append(r12);	 Catch:{ Exception -> 0x02b3, all -> 0x028a }
        r12 = "-";
        r5 = r5.append(r12);	 Catch:{ Exception -> 0x02b3, all -> 0x028a }
        r5 = r5.append(r10);	 Catch:{ Exception -> 0x02b3, all -> 0x028a }
        r5 = r5.toString();	 Catch:{ Exception -> 0x02b3, all -> 0x028a }
        r2.setRequestProperty(r3, r5);	 Catch:{ Exception -> 0x02b3, all -> 0x028a }
        r10 = new java.io.RandomAccessFile;	 Catch:{ Exception -> 0x02b3, all -> 0x028a }
        r0 = r19;
        r3 = r0.f5160a;	 Catch:{ Exception -> 0x02b3, all -> 0x028a }
        r3 = r3.m7786h();	 Catch:{ Exception -> 0x02b3, all -> 0x028a }
        r5 = "rwd";
        r10.<init>(r3, r5);	 Catch:{ Exception -> 0x02b3, all -> 0x028a }
        r8 = r8 + r6;
        r10.seek(r8);	 Catch:{ Exception -> 0x02b3, all -> 0x028a }
        r4 = r2.getInputStream();	 Catch:{ Exception -> 0x02b3, all -> 0x028a }
        r3 = 5120; // 0x1400 float:7.175E-42 double:2.5296E-320;
        r8 = new byte[r3];	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
    L_0x007b:
        r9 = r4.read(r8);	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3 = -1;
        if (r9 == r3) goto L_0x0088;
    L_0x0082:
        r0 = r19;
        r3 = r0.f5161b;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        if (r3 == 0) goto L_0x008f;
    L_0x0088:
        r2.disconnect();	 Catch:{ Exception -> 0x0297 }
        r4.close();	 Catch:{ Exception -> 0x0297 }
    L_0x008e:
        return;
    L_0x008f:
        r3 = 0;
        r10.write(r8, r3, r9);	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r12 = (long) r9;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r6 = r6 + r12;
        r0 = r19;
        r3 = r0.f5162c;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3.m7826c(r6);	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3 = 0;
        r0 = r19;
        r5 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r11 = r5.f5141q;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        monitor-enter(r11);	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r0 = r19;
        r5 = r0.f5160a;	 Catch:{ all -> 0x01fb }
        r5 = r5.f5141q;	 Catch:{ all -> 0x01fb }
        r12 = r5.iterator();	 Catch:{ all -> 0x01fb }
        r5 = r3;
    L_0x00b3:
        r3 = r12.hasNext();	 Catch:{ all -> 0x01fb }
        if (r3 == 0) goto L_0x00c9;
    L_0x00b9:
        r3 = r12.next();	 Catch:{ all -> 0x01fb }
        r3 = (com.fimi.kernel.p076b.p078b.C1128p) r3;	 Catch:{ all -> 0x01fb }
        r14 = (long) r5;	 Catch:{ all -> 0x01fb }
        r16 = r3.m7828e();	 Catch:{ all -> 0x01fb }
        r14 = r14 + r16;
        r3 = (int) r14;	 Catch:{ all -> 0x01fb }
        r5 = r3;
        goto L_0x00b3;
    L_0x00c9:
        monitor-exit(r11);	 Catch:{ all -> 0x01fb }
        r0 = r19;
        r3 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r12 = (long) r5;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3.f5135k = r12;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r0 = r19;
        r3 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r12 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r0 = r19;
        r5 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r14 = r5.f5149y;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r12 = r12 - r14;
        r14 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r12 = r12 / r14;
        r5 = (float) r12;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3.f5130a = r5;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r0 = r19;
        r3 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3 = r3.f5130a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r5 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r3 < 0) goto L_0x013a;
    L_0x00f5:
        r0 = r19;
        r3 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r12 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3.f5149y = r12;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r0 = r19;
        r3 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r0 = r19;
        r5 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r12 = r5.f5135k;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r0 = r19;
        r5 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r14 = r5.f5136l;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r12 = r12 - r14;
        r5 = (float) r12;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r0 = r19;
        r11 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r11 = r11.f5130a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r5 = r5 / r11;
        r12 = (long) r5;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3.f5137m = r12;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r0 = r19;
        r3 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r0 = r19;
        r5 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r12 = r5.f5135k;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3.f5136l = r12;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3 = new com.fimi.kernel.b.b.g;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r0 = r19;
        r3.<init>(r0);	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        com.fimi.kernel.p084e.ah.m8075a(r3);	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
    L_0x013a:
        r5 = android.os.Message.obtain();	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3 = 2;
        r5.what = r3;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r5.arg1 = r9;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r0 = r19;
        r3 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r12 = r3.f5135k;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r0 = r19;
        r3 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r14 = r3.f5134j;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1));
        if (r3 < 0) goto L_0x01d8;
    L_0x0157:
        r0 = r19;
        r3 = r0.f5160a;	 Catch:{ IOException -> 0x0284 }
        r3 = r3.f5147w;	 Catch:{ IOException -> 0x0284 }
        if (r3 != 0) goto L_0x019e;
    L_0x0161:
        r0 = r19;
        r3 = r0.f5160a;	 Catch:{ IOException -> 0x0284 }
        r3 = r3.f5138n;	 Catch:{ IOException -> 0x0284 }
        r3 = com.fimi.kernel.p084e.C1174m.m8196g(r3);	 Catch:{ IOException -> 0x0284 }
        if (r3 == 0) goto L_0x0265;
    L_0x016f:
        r0 = r19;
        r3 = r0.f5160a;	 Catch:{ IOException -> 0x0284 }
        r0 = r19;
        r9 = r0.f5160a;	 Catch:{ IOException -> 0x0284 }
        r9 = r9.f5138n;	 Catch:{ IOException -> 0x0284 }
        r11 = 1;
        r9 = android.media.ThumbnailUtils.createVideoThumbnail(r9, r11);	 Catch:{ IOException -> 0x0284 }
        r3.f5147w = r9;	 Catch:{ IOException -> 0x0284 }
        r0 = r19;
        r3 = r0.f5160a;	 Catch:{ IOException -> 0x0284 }
        r0 = r19;
        r9 = r0.f5160a;	 Catch:{ IOException -> 0x0284 }
        r9 = r9.f5143s;	 Catch:{ IOException -> 0x0284 }
        r0 = r19;
        r11 = r0.f5160a;	 Catch:{ IOException -> 0x0284 }
        r11 = r11.f5138n;	 Catch:{ IOException -> 0x0284 }
        r9 = com.fimi.kernel.p084e.aa.m7993a(r9, r11);	 Catch:{ IOException -> 0x0284 }
        r3.f5148x = r9;	 Catch:{ IOException -> 0x0284 }
    L_0x019e:
        r0 = r19;
        r3 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3 = r3.f5143s;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3 = com.fimi.kernel.p076b.p078b.C1131s.m7844a(r3);	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r0 = r19;
        r9 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r9 = r9.f5139o;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3.m7856d(r9);	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3 = new java.io.File;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r0 = r19;
        r9 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r9 = r9.m7786h();	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3.<init>(r9);	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3 = android.net.Uri.fromFile(r3);	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r9 = new android.content.Intent;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r11 = "android.intent.action.MEDIA_SCANNER_SCAN_FILE";
        r9.<init>(r11, r3);	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r0 = r19;
        r3 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3 = r3.f5143s;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3.sendBroadcast(r9);	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
    L_0x01d8:
        r0 = r19;
        r3 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3 = r3.f5144t;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        if (r3 == 0) goto L_0x01ed;
    L_0x01e2:
        r0 = r19;
        r3 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3 = r3.f5144t;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3.sendMessage(r5);	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
    L_0x01ed:
        r0 = r19;
        r3 = r0.f5160a;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r3 = r3.f5145u;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        r5 = com.fimi.kernel.p076b.p078b.C1115d.Pause;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        if (r3 != r5) goto L_0x007b;
    L_0x01f9:
        goto L_0x0088;
    L_0x01fb:
        r3 = move-exception;
        monitor-exit(r11);	 Catch:{ all -> 0x01fb }
        throw r3;	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
    L_0x01fe:
        r3 = move-exception;
        r18 = r3;
        r3 = r4;
        r4 = r2;
        r2 = r18;
    L_0x0205:
        r2.printStackTrace();	 Catch:{ all -> 0x02a4 }
        r5 = "Good";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x02a4 }
        r6.<init>();	 Catch:{ all -> 0x02a4 }
        r7 = "DOWNLOADING ERROR ";
        r6 = r6.append(r7);	 Catch:{ all -> 0x02a4 }
        r2 = r6.append(r2);	 Catch:{ all -> 0x02a4 }
        r6 = ",id";
        r2 = r2.append(r6);	 Catch:{ all -> 0x02a4 }
        r0 = r19;
        r6 = r0.f5162c;	 Catch:{ all -> 0x02a4 }
        r6 = r6.m7823b();	 Catch:{ all -> 0x02a4 }
        r2 = r2.append(r6);	 Catch:{ all -> 0x02a4 }
        r2 = r2.toString();	 Catch:{ all -> 0x02a4 }
        android.util.Log.d(r5, r2);	 Catch:{ all -> 0x02a4 }
        r2 = android.os.Message.obtain();	 Catch:{ all -> 0x02a4 }
        r0 = r19;
        r5 = r0.f5160a;	 Catch:{ all -> 0x02a4 }
        r6 = com.fimi.kernel.p076b.p078b.C1115d.Error;	 Catch:{ all -> 0x02a4 }
        r5.f5145u = r6;	 Catch:{ all -> 0x02a4 }
        r5 = 3;
        r2.what = r5;	 Catch:{ all -> 0x02a4 }
        r0 = r19;
        r5 = r0.f5160a;	 Catch:{ all -> 0x02a4 }
        r5 = r5.f5144t;	 Catch:{ all -> 0x02a4 }
        if (r5 == 0) goto L_0x0257;
    L_0x024c:
        r0 = r19;
        r5 = r0.f5160a;	 Catch:{ all -> 0x02a4 }
        r5 = r5.f5144t;	 Catch:{ all -> 0x02a4 }
        r5.sendMessage(r2);	 Catch:{ all -> 0x02a4 }
    L_0x0257:
        r4.disconnect();	 Catch:{ Exception -> 0x025f }
        r3.close();	 Catch:{ Exception -> 0x025f }
        goto L_0x008e;
    L_0x025f:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x008e;
    L_0x0265:
        r0 = r19;
        r3 = r0.f5160a;	 Catch:{ IOException -> 0x0284 }
        r0 = r19;
        r9 = r0.f5160a;	 Catch:{ IOException -> 0x0284 }
        r9 = r9.f5143s;	 Catch:{ IOException -> 0x0284 }
        r0 = r19;
        r11 = r0.f5160a;	 Catch:{ IOException -> 0x0284 }
        r11 = r11.f5138n;	 Catch:{ IOException -> 0x0284 }
        r12 = 160; // 0xa0 float:2.24E-43 double:7.9E-322;
        r9 = com.fimi.kernel.p084e.aa.m7982a(r9, r11, r12);	 Catch:{ IOException -> 0x0284 }
        r3.f5147w = r9;	 Catch:{ IOException -> 0x0284 }
        goto L_0x019e;
    L_0x0284:
        r3 = move-exception;
        r3.printStackTrace();	 Catch:{ Exception -> 0x01fe, all -> 0x028a }
        goto L_0x019e;
    L_0x028a:
        r3 = move-exception;
        r18 = r3;
        r3 = r2;
        r2 = r18;
    L_0x0290:
        r3.disconnect();	 Catch:{ Exception -> 0x029d }
        r4.close();	 Catch:{ Exception -> 0x029d }
    L_0x0296:
        throw r2;
    L_0x0297:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x008e;
    L_0x029d:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x0296;
    L_0x02a2:
        r2 = move-exception;
        goto L_0x0290;
    L_0x02a4:
        r2 = move-exception;
        r18 = r3;
        r3 = r4;
        r4 = r18;
        goto L_0x0290;
    L_0x02ab:
        r2 = move-exception;
        r18 = r4;
        r4 = r3;
        r3 = r18;
        goto L_0x0205;
    L_0x02b3:
        r3 = move-exception;
        r18 = r3;
        r3 = r4;
        r4 = r2;
        r2 = r18;
        goto L_0x0205;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fimi.kernel.b.b.f.run():void");
    }
}
