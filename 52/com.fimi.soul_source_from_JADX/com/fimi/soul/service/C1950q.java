package com.fimi.soul.service;

import com.fimi.kernel.C1189f;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p114e.C1532a;
import com.fimi.soul.utils.C1969i;
import com.tencent.connect.common.Constants;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.service.q */
public class C1950q {
    BufferedWriter f9989a;
    BufferedWriter f9990b;
    SimpleDateFormat f9991c;

    public static C1950q m12185a() {
        return C1951r.f9992a;
    }

    private void m12186a(C1465c c1465c) {
        if (c1465c != null) {
            byte[] e = c1465c.m9817e();
            try {
                if (this.f9990b == null) {
                    this.f9990b = m12189b();
                }
                this.f9990b.write(m12187c());
                this.f9990b.write("    ");
                StringBuffer stringBuffer = new StringBuffer(e.length * 2);
                for (int i = 0; i < e.length; i++) {
                    stringBuffer.append(Character.forDigit((e[i] & 240) >> 4, 16));
                    stringBuffer.append(Character.forDigit(e[i] & 15, 16));
                    stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                }
                this.f9990b.write(stringBuffer.toString());
                this.f9990b.write("\n");
                this.f9990b.flush();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private String m12187c() {
        if (this.f9991c == null) {
            this.f9991c = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.US);
        }
        return this.f9991c.format(Long.valueOf(System.currentTimeMillis()));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m12188a(java.util.concurrent.ConcurrentHashMap<java.lang.String, com.fimi.soul.drone.p107c.p108a.C1465c> r3) {
        /*
        r2 = this;
        if (r3 == 0) goto L_0x0023;
    L_0x0002:
        monitor-enter(r2);
        r0 = r3.keySet();	 Catch:{ all -> 0x001f }
        r1 = r0.iterator();	 Catch:{ all -> 0x001f }
    L_0x000b:
        r0 = r1.hasNext();	 Catch:{ all -> 0x001f }
        if (r0 == 0) goto L_0x0022;
    L_0x0011:
        r0 = r1.next();	 Catch:{ all -> 0x001f }
        r0 = r3.get(r0);	 Catch:{ all -> 0x001f }
        r0 = (com.fimi.soul.drone.p107c.p108a.C1465c) r0;	 Catch:{ all -> 0x001f }
        r2.m12186a(r0);	 Catch:{ all -> 0x001f }
        goto L_0x000b;
    L_0x001f:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x001f }
        throw r0;
    L_0x0022:
        monitor-exit(r2);	 Catch:{ all -> 0x001f }
    L_0x0023:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fimi.soul.service.q.a(java.util.concurrent.ConcurrentHashMap):void");
    }

    public BufferedWriter m12189b() {
        C1532a c1532a = (C1532a) C1189f.m8333c().m7926a(Constants.VIA_RESULT_SUCCESS, C1532a.class);
        String str = c1532a != null ? c1532a.m10059d() + C2915a.f14760f + c1532a.m10061e() + C2915a.f14760f + c1532a.m10063g() : null;
        try {
            File file = new File(C1969i.m12474a() + "/" + "SAVEDRONEINFO/");
            file.mkdirs();
            if (str == null) {
                str = "savedrone.txt";
            }
            File file2 = new File(file, str);
            if (!file2.exists() && this.f9989a != null) {
                this.f9989a.close();
                file2.createNewFile();
                this.f9989a = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2, true)));
                return this.f9989a;
            } else if (file2.exists() || this.f9989a != null) {
                if (file2.exists() && this.f9989a == null) {
                    this.f9989a = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2, true)));
                }
                return this.f9989a;
            } else {
                file2.createNewFile();
                this.f9989a = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2, true)));
                return this.f9989a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
