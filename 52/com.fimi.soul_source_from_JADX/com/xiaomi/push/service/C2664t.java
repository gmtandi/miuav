package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.C2463b;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* renamed from: com.xiaomi.push.service.t */
public class C2664t {
    private static final Pattern f13172a;
    private static ThreadPoolExecutor f13173b;

    static {
        f13172a = Pattern.compile("([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})");
        f13173b = new ThreadPoolExecutor(1, 1, 20, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    public static void m15090a() {
        if (f13173b.getActiveCount() <= 0) {
            f13173b.execute(new C2666u());
        }
    }

    private static void m15093b(String str) {
        Process exec;
        BufferedReader bufferedReader;
        Throwable e;
        BufferedReader bufferedReader2 = null;
        C2463b.m14123a("Network Checkup: Begin to ping " + str);
        try {
            exec = Runtime.getRuntime().exec(String.format("ping -W 500 -i 0.2 -c 3 %s", new Object[]{str}));
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            } catch (IOException e2) {
                e = e2;
                try {
                    C2463b.m14125a(e);
                    try {
                        bufferedReader2.close();
                    } catch (IOException e3) {
                    }
                    if (exec == null) {
                        exec.destroy();
                    }
                    return;
                } catch (Throwable th) {
                    e = th;
                    try {
                        bufferedReader2.close();
                    } catch (IOException e4) {
                    }
                    if (exec != null) {
                        exec.destroy();
                    }
                    throw e;
                }
            } catch (Exception e5) {
                e = e5;
                C2463b.m14125a(e);
                try {
                    bufferedReader2.close();
                } catch (IOException e6) {
                }
                if (exec == null) {
                    exec.destroy();
                }
                return;
            }
            try {
                for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    C2463b.m14123a("Network Checkup:" + readLine);
                }
                exec.waitFor();
                try {
                    bufferedReader.close();
                } catch (IOException e7) {
                }
                if (exec == null) {
                    return;
                }
            } catch (IOException e8) {
                e = e8;
                bufferedReader2 = bufferedReader;
                C2463b.m14125a(e);
                bufferedReader2.close();
                if (exec == null) {
                    exec.destroy();
                }
                return;
            } catch (Exception e9) {
                e = e9;
                bufferedReader2 = bufferedReader;
                C2463b.m14125a(e);
                bufferedReader2.close();
                if (exec == null) {
                    exec.destroy();
                }
                return;
            } catch (Throwable th2) {
                e = th2;
                bufferedReader2 = bufferedReader;
                bufferedReader2.close();
                if (exec != null) {
                    exec.destroy();
                }
                throw e;
            }
        } catch (IOException e10) {
            e = e10;
            exec = null;
            C2463b.m14125a(e);
            bufferedReader2.close();
            if (exec == null) {
                exec.destroy();
            }
            return;
        } catch (Exception e11) {
            e = e11;
            exec = null;
            C2463b.m14125a(e);
            bufferedReader2.close();
            if (exec == null) {
                exec.destroy();
            }
            return;
        } catch (Throwable th3) {
            e = th3;
            exec = null;
            bufferedReader2.close();
            if (exec != null) {
                exec.destroy();
            }
            throw e;
        }
        exec.destroy();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m15094c() {
        /*
        r1 = 0;
        r0 = java.lang.Runtime.getRuntime();	 Catch:{ IOException -> 0x0058, Exception -> 0x0069, all -> 0x007a }
        r2 = "ip route";
        r2 = r0.exec(r2);	 Catch:{ IOException -> 0x0058, Exception -> 0x0069, all -> 0x007a }
        r3 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x00a5, Exception -> 0x0095, all -> 0x0090 }
        r0 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x00a5, Exception -> 0x0095, all -> 0x0090 }
        r4 = r2.getInputStream();	 Catch:{ IOException -> 0x00a5, Exception -> 0x0095, all -> 0x0090 }
        r0.<init>(r4);	 Catch:{ IOException -> 0x00a5, Exception -> 0x0095, all -> 0x0090 }
        r3.<init>(r0);	 Catch:{ IOException -> 0x00a5, Exception -> 0x0095, all -> 0x0090 }
        r0 = r3.readLine();	 Catch:{ IOException -> 0x00ab, Exception -> 0x009b }
        r4 = android.text.TextUtils.isEmpty(r0);	 Catch:{ IOException -> 0x00ab, Exception -> 0x009b }
        if (r4 != 0) goto L_0x00b7;
    L_0x0023:
        r4 = "default via";
        r4 = r0.startsWith(r4);	 Catch:{ IOException -> 0x00ab, Exception -> 0x009b }
        if (r4 == 0) goto L_0x00b7;
    L_0x002b:
        r4 = " ";
        r5 = r0.split(r4);	 Catch:{ IOException -> 0x00ab, Exception -> 0x009b }
        r6 = r5.length;	 Catch:{ IOException -> 0x00ab, Exception -> 0x009b }
        r0 = 0;
        r4 = r0;
    L_0x0034:
        if (r4 >= r6) goto L_0x0049;
    L_0x0036:
        r0 = r5[r4];	 Catch:{ IOException -> 0x00b0, Exception -> 0x00a0 }
        r7 = f13172a;	 Catch:{ IOException -> 0x00b0, Exception -> 0x00a0 }
        r7 = r7.matcher(r0);	 Catch:{ IOException -> 0x00b0, Exception -> 0x00a0 }
        r7 = r7.matches();	 Catch:{ IOException -> 0x00b0, Exception -> 0x00a0 }
        if (r7 == 0) goto L_0x00b5;
    L_0x0044:
        r1 = r4 + 1;
        r4 = r1;
        r1 = r0;
        goto L_0x0034;
    L_0x0049:
        r2.waitFor();	 Catch:{ IOException -> 0x00b0, Exception -> 0x00a0 }
        r0 = r1;
    L_0x004d:
        if (r3 == 0) goto L_0x0052;
    L_0x004f:
        r3.close();	 Catch:{ IOException -> 0x008e }
    L_0x0052:
        if (r2 == 0) goto L_0x0057;
    L_0x0054:
        r2.destroy();
    L_0x0057:
        return r0;
    L_0x0058:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
        r8 = r0;
        r0 = r1;
        r1 = r8;
    L_0x005e:
        com.xiaomi.channel.commonutils.logger.C2463b.m14125a(r1);	 Catch:{ all -> 0x0093 }
        if (r3 == 0) goto L_0x0066;
    L_0x0063:
        r3.close();	 Catch:{ IOException -> 0x008a }
    L_0x0066:
        if (r2 == 0) goto L_0x0057;
    L_0x0068:
        goto L_0x0054;
    L_0x0069:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
        r8 = r0;
        r0 = r1;
        r1 = r8;
    L_0x006f:
        com.xiaomi.channel.commonutils.logger.C2463b.m14125a(r1);	 Catch:{ all -> 0x0093 }
        if (r3 == 0) goto L_0x0077;
    L_0x0074:
        r3.close();	 Catch:{ IOException -> 0x008c }
    L_0x0077:
        if (r2 == 0) goto L_0x0057;
    L_0x0079:
        goto L_0x0054;
    L_0x007a:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x007d:
        if (r3 == 0) goto L_0x0082;
    L_0x007f:
        r3.close();	 Catch:{ IOException -> 0x0088 }
    L_0x0082:
        if (r2 == 0) goto L_0x0087;
    L_0x0084:
        r2.destroy();
    L_0x0087:
        throw r0;
    L_0x0088:
        r1 = move-exception;
        goto L_0x0082;
    L_0x008a:
        r1 = move-exception;
        goto L_0x0066;
    L_0x008c:
        r1 = move-exception;
        goto L_0x0077;
    L_0x008e:
        r1 = move-exception;
        goto L_0x0052;
    L_0x0090:
        r0 = move-exception;
        r3 = r1;
        goto L_0x007d;
    L_0x0093:
        r0 = move-exception;
        goto L_0x007d;
    L_0x0095:
        r0 = move-exception;
        r3 = r1;
        r8 = r0;
        r0 = r1;
        r1 = r8;
        goto L_0x006f;
    L_0x009b:
        r0 = move-exception;
        r8 = r0;
        r0 = r1;
        r1 = r8;
        goto L_0x006f;
    L_0x00a0:
        r0 = move-exception;
        r8 = r0;
        r0 = r1;
        r1 = r8;
        goto L_0x006f;
    L_0x00a5:
        r0 = move-exception;
        r3 = r1;
        r8 = r0;
        r0 = r1;
        r1 = r8;
        goto L_0x005e;
    L_0x00ab:
        r0 = move-exception;
        r8 = r0;
        r0 = r1;
        r1 = r8;
        goto L_0x005e;
    L_0x00b0:
        r0 = move-exception;
        r8 = r0;
        r0 = r1;
        r1 = r8;
        goto L_0x005e;
    L_0x00b5:
        r0 = r1;
        goto L_0x0044;
    L_0x00b7:
        r0 = r1;
        goto L_0x004d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.t.c():java.lang.String");
    }
}
