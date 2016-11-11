package com.p016a;

/* renamed from: com.a.ds */
class ds extends Thread {
    final /* synthetic */ dr f963a;

    ds(dr drVar) {
        this.f963a = drVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r8 = this;
        r7 = 0;
        r0 = r8.f963a;	 Catch:{ Throwable -> 0x00c5, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r0.m1569f();	 Catch:{ Throwable -> 0x00c5, RemoteException -> 0x00de, InterruptedException -> 0x010e }
    L_0x0006:
        r0 = r8.f963a;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r0 = r0.f946j;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        if (r0 == 0) goto L_0x019a;
    L_0x000c:
        r1 = 0;
        r0 = r8.f963a;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r0 = r0.f947k;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        if (r0 == 0) goto L_0x0180;
    L_0x0013:
        r0 = r8.f963a;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r0 = r0.f935C;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        if (r0 == 0) goto L_0x0124;
    L_0x001b:
        r0 = r8.f963a;	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r2 = r8.f963a;	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r3 = r8.f963a;	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r3 = r3.f943g;	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r2 = r2.m1550a(r3);	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r0.f949m = r2;	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r0 = r8.f963a;	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r0 = r0.f934B;	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        if (r0 == 0) goto L_0x0055;
    L_0x0031:
        r0 = r8.f963a;	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r0 = r0.f949m;	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r2 = r0.m5343k();	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r0 = r8.f963a;	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r4 = r8.f963a;	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r4 = r4.f934B;	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r5 = r8.f963a;	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r5 = r5.f949m;	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r6 = 0;
        r6 = new java.lang.String[r6];	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r4 = r4.m1251a(r5, r6);	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r0.f949m = r4;	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r0 = r8.f963a;	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r0 = r0.f949m;	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
        r0.m5314a(r2);	 Catch:{ Throwable -> 0x00f4, RemoteException -> 0x00de, InterruptedException -> 0x010e }
    L_0x0055:
        r0 = r8.f963a;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r3 = r0.f948l;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        monitor-enter(r3);	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r0 = r8.f963a;	 Catch:{ all -> 0x017d }
        r0 = r0.f949m;	 Catch:{ all -> 0x017d }
        if (r0 == 0) goto L_0x0072;
    L_0x0060:
        r0 = r8.f963a;	 Catch:{ all -> 0x017d }
        r0 = r0.f949m;	 Catch:{ all -> 0x017d }
        r0 = r0.m5310a();	 Catch:{ all -> 0x017d }
        if (r0 != 0) goto L_0x0072;
    L_0x006a:
        r0 = r8.f963a;	 Catch:{ all -> 0x017d }
        r4 = com.p016a.dn.m1519b();	 Catch:{ all -> 0x017d }
        r0.f950n = r4;	 Catch:{ all -> 0x017d }
    L_0x0072:
        r0 = r8.f963a;	 Catch:{ all -> 0x017d }
        r2 = 0;
        r0.f947k = r2;	 Catch:{ all -> 0x017d }
        r0 = r8.f963a;	 Catch:{ all -> 0x017d }
        r0 = r0.f944h;	 Catch:{ all -> 0x017d }
        if (r0 == 0) goto L_0x01a9;
    L_0x007d:
        r0 = r8.f963a;	 Catch:{ all -> 0x017d }
        r0 = r0.f944h;	 Catch:{ all -> 0x017d }
        r0 = r0.size();	 Catch:{ all -> 0x017d }
        if (r0 <= 0) goto L_0x01a9;
    L_0x0087:
        r0 = r8.f963a;	 Catch:{ all -> 0x017d }
        r0 = r0.f944h;	 Catch:{ all -> 0x017d }
        r0 = r0.size();	 Catch:{ all -> 0x017d }
        r2 = r0;
    L_0x0090:
        if (r7 >= r2) goto L_0x014e;
    L_0x0092:
        r1 = android.os.Message.obtain();	 Catch:{ all -> 0x017d }
        r0 = new android.os.Bundle;	 Catch:{ all -> 0x017d }
        r0.<init>();	 Catch:{ all -> 0x017d }
        r4 = "location";
        r5 = r8.f963a;	 Catch:{ all -> 0x017d }
        r5 = r5.f949m;	 Catch:{ all -> 0x017d }
        r0.putParcelable(r4, r5);	 Catch:{ all -> 0x017d }
        r1.setData(r0);	 Catch:{ all -> 0x017d }
        r0 = 1;
        r1.what = r0;	 Catch:{ all -> 0x017d }
        r0 = r8.f963a;	 Catch:{ all -> 0x017d }
        r0 = r0.f944h;	 Catch:{ all -> 0x017d }
        r4 = 0;
        r0 = r0.get(r4);	 Catch:{ all -> 0x017d }
        r0 = (android.os.Messenger) r0;	 Catch:{ all -> 0x017d }
        r0.send(r1);	 Catch:{ all -> 0x017d }
        r1 = r8.f963a;	 Catch:{ all -> 0x017d }
        r1 = r1.f944h;	 Catch:{ all -> 0x017d }
        r4 = 0;
        r1.remove(r4);	 Catch:{ all -> 0x017d }
        r1 = r2 + -1;
        r2 = r1;
        r1 = r0;
        goto L_0x0090;
    L_0x00c5:
        r0 = move-exception;
        r1 = r8.f963a;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r2 = r0.getMessage();	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r1.f936D = r2;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r1 = r8.f963a;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r2 = 0;
        r1.f935C = r2;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r1 = "APSServiceCore";
        r2 = "run part1";
        com.p016a.ev.m1777a(r0, r1, r2);	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        goto L_0x0006;
    L_0x00de:
        r0 = move-exception;
        r1 = "APSServiceCore";
        r2 = "run part3";
        com.p016a.ev.m1777a(r0, r1, r2);	 Catch:{ all -> 0x016e }
        r0 = r8.f963a;
        r0 = r0.m1563c();
        if (r0 != 0) goto L_0x00f3;
    L_0x00ee:
        r0 = r8.f963a;
        r0.m1572h();
    L_0x00f3:
        return;
    L_0x00f4:
        r0 = move-exception;
        r2 = r8.f963a;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r3 = r8.f963a;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r4 = 8;
        r5 = r0.getMessage();	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r3 = r3.m1548a(r4, r5);	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r2.f949m = r3;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r2 = "APSServiceCore";
        r3 = "run part2";
        com.p016a.ev.m1777a(r0, r2, r3);	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        goto L_0x0055;
    L_0x010e:
        r0 = move-exception;
        r0 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x016e }
        r0.interrupt();	 Catch:{ all -> 0x016e }
        r0 = r8.f963a;
        r0 = r0.m1563c();
        if (r0 != 0) goto L_0x00f3;
    L_0x011e:
        r0 = r8.f963a;
        r0.m1572h();
        goto L_0x00f3;
    L_0x0124:
        r0 = r8.f963a;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r2 = r8.f963a;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r3 = 9;
        r4 = r8.f963a;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r4 = r4.f936D;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r2 = r2.m1548a(r3, r4);	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r0.f949m = r2;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        goto L_0x0055;
    L_0x0138:
        r0 = move-exception;
        r1 = "APSServiceCore";
        r2 = "run part5";
        com.p016a.ev.m1777a(r0, r1, r2);	 Catch:{ all -> 0x016e }
        r0 = r8.f963a;
        r0 = r0.m1563c();
        if (r0 != 0) goto L_0x00f3;
    L_0x0148:
        r0 = r8.f963a;
        r0.m1572h();
        goto L_0x00f3;
    L_0x014e:
        r0 = r1;
    L_0x014f:
        monitor-exit(r3);	 Catch:{ all -> 0x017d }
    L_0x0150:
        r1 = r8.f963a;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r1 = r1.f960x;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        if (r1 == 0) goto L_0x0006;
    L_0x0158:
        r1 = r8.f963a;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r1.m1567e();	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r1 = r8.f963a;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r1.m1564d();	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r1 = r8.f963a;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r1.m1552a(r0);	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r0 = r8.f963a;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r0.m1574i();	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        goto L_0x0006;
    L_0x016e:
        r0 = move-exception;
        r1 = r8.f963a;
        r1 = r1.m1563c();
        if (r1 != 0) goto L_0x017c;
    L_0x0177:
        r1 = r8.f963a;
        r1.m1572h();
    L_0x017c:
        throw r0;
    L_0x017d:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x017d }
        throw r0;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
    L_0x0180:
        r0 = r8.f963a;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r2 = r0.f948l;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        monitor-enter(r2);	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
        r0 = r8.f963a;	 Catch:{ all -> 0x0197 }
        r0 = r0.m1563c();	 Catch:{ all -> 0x0197 }
        if (r0 == 0) goto L_0x0194;
    L_0x018d:
        r0 = r8.f963a;	 Catch:{ all -> 0x0197 }
        r0 = r0.f948l;	 Catch:{ all -> 0x0197 }
        r0.wait();	 Catch:{ all -> 0x0197 }
    L_0x0194:
        monitor-exit(r2);	 Catch:{ all -> 0x0197 }
        r0 = r1;
        goto L_0x0150;
    L_0x0197:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0197 }
        throw r0;	 Catch:{ RemoteException -> 0x00de, InterruptedException -> 0x010e, Throwable -> 0x0138 }
    L_0x019a:
        r0 = r8.f963a;
        r0 = r0.m1563c();
        if (r0 != 0) goto L_0x00f3;
    L_0x01a2:
        r0 = r8.f963a;
        r0.m1572h();
        goto L_0x00f3;
    L_0x01a9:
        r0 = r1;
        goto L_0x014f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.ds.run():void");
    }
}
