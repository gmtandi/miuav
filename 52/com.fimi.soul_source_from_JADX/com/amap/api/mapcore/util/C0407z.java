package com.amap.api.mapcore.util;

/* renamed from: com.amap.api.mapcore.util.z */
public class C0407z {

    /* renamed from: com.amap.api.mapcore.util.z.a */
    public interface C0382a {
        void m4052a(String str, String str2);

        void m4053a(String str, String str2, float f);

        void m4054a(String str, String str2, int i);

        void m4055b(String str, String str2);
    }

    private float m4241a(long j, long j2) {
        return (((float) j) / ((float) j2)) * 100.0f;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long m4242a(java.io.File r20, java.io.File r21, long r22, long r24, com.amap.api.mapcore.util.C0407z.C0382a r26) {
        /*
        r19 = this;
        r6 = 0;
        r6 = (r22 > r6 ? 1 : (r22 == r6 ? 0 : -1));
        if (r6 != 0) goto L_0x001c;
    L_0x0006:
        r6 = java.lang.System.err;
        r7 = "sizeOfDirectory is the total Size,  must be a positive number";
        r6.println(r7);
        if (r26 == 0) goto L_0x0019;
    L_0x000f:
        r6 = "";
        r7 = "";
        r8 = -1;
        r0 = r26;
        r0.m4054a(r6, r7, r8);
    L_0x0019:
        r10 = 0;
    L_0x001b:
        return r10;
    L_0x001c:
        r15 = r20.getAbsolutePath();
        r16 = r21.getAbsolutePath();
        r6 = r20.isDirectory();	 Catch:{ Exception -> 0x0053 }
        if (r6 == 0) goto L_0x0091;
    L_0x002a:
        r6 = r21.exists();	 Catch:{ Exception -> 0x0053 }
        if (r6 != 0) goto L_0x0064;
    L_0x0030:
        r6 = r21.mkdirs();	 Catch:{ Exception -> 0x0053 }
        if (r6 != 0) goto L_0x0064;
    L_0x0036:
        r6 = new java.io.IOException;	 Catch:{ Exception -> 0x0053 }
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0053 }
        r7.<init>();	 Catch:{ Exception -> 0x0053 }
        r8 = "Cannot create dir ";
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x0053 }
        r8 = r21.getAbsolutePath();	 Catch:{ Exception -> 0x0053 }
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x0053 }
        r7 = r7.toString();	 Catch:{ Exception -> 0x0053 }
        r6.<init>(r7);	 Catch:{ Exception -> 0x0053 }
        throw r6;	 Catch:{ Exception -> 0x0053 }
    L_0x0053:
        r6 = move-exception;
        r10 = r22;
    L_0x0056:
        r6.printStackTrace();
        if (r26 == 0) goto L_0x001b;
    L_0x005b:
        r6 = -1;
        r0 = r26;
        r1 = r16;
        r0.m4054a(r15, r1, r6);
        goto L_0x001b;
    L_0x0064:
        r17 = r20.list();	 Catch:{ Exception -> 0x0053 }
        if (r17 == 0) goto L_0x0128;
    L_0x006a:
        r6 = 0;
        r10 = r22;
    L_0x006d:
        r0 = r17;
        r7 = r0.length;	 Catch:{ Exception -> 0x0122 }
        if (r6 >= r7) goto L_0x001b;
    L_0x0072:
        r8 = new java.io.File;	 Catch:{ Exception -> 0x0122 }
        r7 = r17[r6];	 Catch:{ Exception -> 0x0122 }
        r0 = r20;
        r8.<init>(r0, r7);	 Catch:{ Exception -> 0x0122 }
        r9 = new java.io.File;	 Catch:{ Exception -> 0x0122 }
        r7 = r17[r6];	 Catch:{ Exception -> 0x0122 }
        r0 = r21;
        r9.<init>(r0, r7);	 Catch:{ Exception -> 0x0122 }
        r7 = r19;
        r12 = r24;
        r14 = r26;
        r10 = r7.m4242a(r8, r9, r10, r12, r14);	 Catch:{ Exception -> 0x0122 }
        r6 = r6 + 1;
        goto L_0x006d;
    L_0x0091:
        r6 = r21.getParentFile();	 Catch:{ Exception -> 0x0053 }
        if (r6 == 0) goto L_0x00c0;
    L_0x0097:
        r7 = r6.exists();	 Catch:{ Exception -> 0x0053 }
        if (r7 != 0) goto L_0x00c0;
    L_0x009d:
        r7 = r6.mkdirs();	 Catch:{ Exception -> 0x0053 }
        if (r7 != 0) goto L_0x00c0;
    L_0x00a3:
        r7 = new java.io.IOException;	 Catch:{ Exception -> 0x0053 }
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0053 }
        r8.<init>();	 Catch:{ Exception -> 0x0053 }
        r9 = "Cannot create dir ";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x0053 }
        r6 = r6.getAbsolutePath();	 Catch:{ Exception -> 0x0053 }
        r6 = r8.append(r6);	 Catch:{ Exception -> 0x0053 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x0053 }
        r7.<init>(r6);	 Catch:{ Exception -> 0x0053 }
        throw r7;	 Catch:{ Exception -> 0x0053 }
    L_0x00c0:
        if (r26 == 0) goto L_0x00cf;
    L_0x00c2:
        r6 = 0;
        r6 = (r22 > r6 ? 1 : (r22 == r6 ? 0 : -1));
        if (r6 > 0) goto L_0x00cf;
    L_0x00c8:
        r0 = r26;
        r1 = r16;
        r0.m4052a(r15, r1);	 Catch:{ Exception -> 0x0053 }
    L_0x00cf:
        r6 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0053 }
        r0 = r20;
        r6.<init>(r0);	 Catch:{ Exception -> 0x0053 }
        r7 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0053 }
        r0 = r21;
        r7.<init>(r0);	 Catch:{ Exception -> 0x0053 }
        r8 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r8 = new byte[r8];	 Catch:{ Exception -> 0x0053 }
        r10 = r22;
    L_0x00e3:
        r9 = r6.read(r8);	 Catch:{ Exception -> 0x0122 }
        if (r9 <= 0) goto L_0x0106;
    L_0x00e9:
        r12 = 0;
        r7.write(r8, r12, r9);	 Catch:{ Exception -> 0x0122 }
        r12 = (long) r9;
        r22 = r10 + r12;
        if (r26 == 0) goto L_0x0125;
    L_0x00f2:
        r0 = r19;
        r1 = r22;
        r3 = r24;
        r9 = r0.m4241a(r1, r3);	 Catch:{ Exception -> 0x0053 }
        r0 = r26;
        r1 = r16;
        r0.m4053a(r15, r1, r9);	 Catch:{ Exception -> 0x0053 }
        r10 = r22;
        goto L_0x00e3;
    L_0x0106:
        r6.close();	 Catch:{ Exception -> 0x0122 }
        r7.flush();	 Catch:{ Exception -> 0x0122 }
        r7.close();	 Catch:{ Exception -> 0x0122 }
        if (r26 == 0) goto L_0x001b;
    L_0x0111:
        r6 = 1;
        r6 = r24 - r6;
        r6 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1));
        if (r6 < 0) goto L_0x001b;
    L_0x0119:
        r0 = r26;
        r1 = r16;
        r0.m4055b(r15, r1);	 Catch:{ Exception -> 0x0122 }
        goto L_0x001b;
    L_0x0122:
        r6 = move-exception;
        goto L_0x0056;
    L_0x0125:
        r10 = r22;
        goto L_0x00e3;
    L_0x0128:
        r10 = r22;
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.z.a(java.io.File, java.io.File, long, long, com.amap.api.mapcore.util.z$a):long");
    }
}
