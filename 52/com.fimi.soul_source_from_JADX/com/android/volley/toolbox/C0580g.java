package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.C0554b;
import com.android.volley.C0555c;
import com.android.volley.ah;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.android.volley.toolbox.g */
public class C0580g implements C0554b {
    private static final int f3612e = 5242880;
    private static final float f3613f = 0.9f;
    private static final int f3614g = 538247942;
    private final Map<String, C0582i> f3615a;
    private long f3616b;
    private final File f3617c;
    private final int f3618d;

    public C0580g(File file) {
        this(file, f3612e);
    }

    public C0580g(File file, int i) {
        this.f3615a = new LinkedHashMap(16, 0.75f, true);
        this.f3616b = 0;
        this.f3617c = file;
        this.f3618d = i;
    }

    static int m5193a(InputStream inputStream) {
        return (((0 | (C0580g.m5205e(inputStream) << 0)) | (C0580g.m5205e(inputStream) << 8)) | (C0580g.m5205e(inputStream) << 16)) | (C0580g.m5205e(inputStream) << 24);
    }

    private void m5194a(int i) {
        if (this.f3616b + ((long) i) >= ((long) this.f3618d)) {
            int i2;
            if (ah.f3498b) {
                ah.m5056a("Pruning old cache entries.", new Object[0]);
            }
            long j = this.f3616b;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Iterator it = this.f3615a.entrySet().iterator();
            int i3 = 0;
            while (it.hasNext()) {
                C0582i c0582i = (C0582i) ((Entry) it.next()).getValue();
                if (m5213c(c0582i.f3620b).delete()) {
                    this.f3616b -= c0582i.f3619a;
                } else {
                    ah.m5058b("Could not delete cache entry for key=%s, filename=%s", c0582i.f3620b, m5203d(c0582i.f3620b));
                }
                it.remove();
                i2 = i3 + 1;
                if (((float) (this.f3616b + ((long) i))) < ((float) this.f3618d) * f3613f) {
                    break;
                }
                i3 = i2;
            }
            i2 = i3;
            if (ah.f3498b) {
                ah.m5056a("pruned %d files, %d bytes, %d ms", Integer.valueOf(i2), Long.valueOf(this.f3616b - j), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            }
        }
    }

    static void m5195a(OutputStream outputStream, int i) {
        outputStream.write((i >> 0) & Util.MASK_8BIT);
        outputStream.write((i >> 8) & Util.MASK_8BIT);
        outputStream.write((i >> 16) & Util.MASK_8BIT);
        outputStream.write((i >> 24) & Util.MASK_8BIT);
    }

    static void m5196a(OutputStream outputStream, long j) {
        outputStream.write((byte) ((int) (j >>> null)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    static void m5197a(OutputStream outputStream, String str) {
        byte[] bytes = str.getBytes(C1142e.f5201a);
        C0580g.m5196a(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    private void m5198a(String str, C0582i c0582i) {
        if (this.f3615a.containsKey(str)) {
            C0582i c0582i2 = (C0582i) this.f3615a.get(str);
            this.f3616b = (c0582i.f3619a - c0582i2.f3619a) + this.f3616b;
        } else {
            this.f3616b += c0582i.f3619a;
        }
        this.f3615a.put(str, c0582i);
    }

    static void m5199a(Map<String, String> map, OutputStream outputStream) {
        if (map != null) {
            C0580g.m5195a(outputStream, map.size());
            for (Entry entry : map.entrySet()) {
                C0580g.m5197a(outputStream, (String) entry.getKey());
                C0580g.m5197a(outputStream, (String) entry.getValue());
            }
            return;
        }
        C0580g.m5195a(outputStream, 0);
    }

    private static byte[] m5200a(InputStream inputStream, int i) {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read == -1) {
                break;
            }
            i2 += read;
        }
        if (i2 == i) {
            return bArr;
        }
        throw new IOException("Expected " + i + " bytes, read " + i2 + " bytes");
    }

    static long m5201b(InputStream inputStream) {
        return (((((((0 | ((((long) C0580g.m5205e(inputStream)) & 255) << null)) | ((((long) C0580g.m5205e(inputStream)) & 255) << 8)) | ((((long) C0580g.m5205e(inputStream)) & 255) << 16)) | ((((long) C0580g.m5205e(inputStream)) & 255) << 24)) | ((((long) C0580g.m5205e(inputStream)) & 255) << 32)) | ((((long) C0580g.m5205e(inputStream)) & 255) << 40)) | ((((long) C0580g.m5205e(inputStream)) & 255) << 48)) | ((((long) C0580g.m5205e(inputStream)) & 255) << 56);
    }

    static String m5202c(InputStream inputStream) {
        return new String(C0580g.m5200a(inputStream, (int) C0580g.m5201b(inputStream)), C1142e.f5201a);
    }

    private String m5203d(String str) {
        int length = str.length() / 2;
        return String.valueOf(str.substring(0, length).hashCode()) + String.valueOf(str.substring(length).hashCode());
    }

    static Map<String, String> m5204d(InputStream inputStream) {
        int a = C0580g.m5193a(inputStream);
        Map<String, String> emptyMap = a == 0 ? Collections.emptyMap() : new HashMap(a);
        for (int i = 0; i < a; i++) {
            emptyMap.put(C0580g.m5202c(inputStream).intern(), C0580g.m5202c(inputStream).intern());
        }
        return emptyMap;
    }

    private static int m5205e(InputStream inputStream) {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    private void m5206e(String str) {
        C0582i c0582i = (C0582i) this.f3615a.get(str);
        if (c0582i != null) {
            this.f3616b -= c0582i.f3619a;
            this.f3615a.remove(str);
        }
    }

    public synchronized C0555c m5207a(String str) {
        C0555c c0555c;
        C0583j c0583j;
        IOException e;
        Throwable th;
        NegativeArraySizeException e2;
        C0582i c0582i = (C0582i) this.f3615a.get(str);
        if (c0582i == null) {
            c0555c = null;
        } else {
            File c = m5213c(str);
            try {
                c0583j = new C0583j(null);
                try {
                    C0582i.m5214a((InputStream) c0583j);
                    c0555c = c0582i.m5215a(C0580g.m5200a((InputStream) c0583j, (int) (c.length() - ((long) c0583j.f3627a))));
                    if (c0583j != null) {
                        try {
                            c0583j.close();
                        } catch (IOException e3) {
                            c0555c = null;
                        }
                    }
                } catch (IOException e4) {
                    e = e4;
                    try {
                        ah.m5058b("%s: %s", c.getAbsolutePath(), e.toString());
                        m5212b(str);
                        if (c0583j != null) {
                            try {
                                c0583j.close();
                            } catch (IOException e5) {
                                c0555c = null;
                            }
                        }
                        c0555c = null;
                        return c0555c;
                    } catch (Throwable th2) {
                        th = th2;
                        if (c0583j != null) {
                            try {
                                c0583j.close();
                            } catch (IOException e6) {
                                c0555c = null;
                            }
                        }
                        throw th;
                    }
                } catch (NegativeArraySizeException e7) {
                    e2 = e7;
                    ah.m5058b("%s: %s", c.getAbsolutePath(), e2.toString());
                    m5212b(str);
                    if (c0583j != null) {
                        try {
                            c0583j.close();
                        } catch (IOException e8) {
                            c0555c = null;
                        }
                    }
                    c0555c = null;
                    return c0555c;
                }
            } catch (IOException e9) {
                e = e9;
                c0583j = null;
                ah.m5058b("%s: %s", c.getAbsolutePath(), e.toString());
                m5212b(str);
                if (c0583j != null) {
                    c0583j.close();
                }
                c0555c = null;
                return c0555c;
            } catch (NegativeArraySizeException e10) {
                e2 = e10;
                c0583j = null;
                ah.m5058b("%s: %s", c.getAbsolutePath(), e2.toString());
                m5212b(str);
                if (c0583j != null) {
                    c0583j.close();
                }
                c0555c = null;
                return c0555c;
            } catch (Throwable th3) {
                th = th3;
                c0583j = null;
                if (c0583j != null) {
                    c0583j.close();
                }
                throw th;
            }
        }
        return c0555c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void m5208a() {
        /*
        r9 = this;
        r0 = 0;
        monitor-enter(r9);
        r1 = r9.f3617c;	 Catch:{ all -> 0x006c }
        r1 = r1.exists();	 Catch:{ all -> 0x006c }
        if (r1 != 0) goto L_0x0025;
    L_0x000a:
        r0 = r9.f3617c;	 Catch:{ all -> 0x006c }
        r0 = r0.mkdirs();	 Catch:{ all -> 0x006c }
        if (r0 != 0) goto L_0x0023;
    L_0x0012:
        r0 = "Unable to create cache dir %s";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x006c }
        r2 = 0;
        r3 = r9.f3617c;	 Catch:{ all -> 0x006c }
        r3 = r3.getAbsolutePath();	 Catch:{ all -> 0x006c }
        r1[r2] = r3;	 Catch:{ all -> 0x006c }
        com.android.volley.ah.m5060c(r0, r1);	 Catch:{ all -> 0x006c }
    L_0x0023:
        monitor-exit(r9);
        return;
    L_0x0025:
        r1 = r9.f3617c;	 Catch:{ all -> 0x006c }
        r3 = r1.listFiles();	 Catch:{ all -> 0x006c }
        if (r3 == 0) goto L_0x0023;
    L_0x002d:
        r4 = r3.length;	 Catch:{ all -> 0x006c }
        r2 = r0;
    L_0x002f:
        if (r2 >= r4) goto L_0x0023;
    L_0x0031:
        r5 = r3[r2];	 Catch:{ all -> 0x006c }
        r1 = 0;
        r0 = new java.io.BufferedInputStream;	 Catch:{ IOException -> 0x0056, all -> 0x0065 }
        r6 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x0056, all -> 0x0065 }
        r6.<init>(r5);	 Catch:{ IOException -> 0x0056, all -> 0x0065 }
        r0.<init>(r6);	 Catch:{ IOException -> 0x0056, all -> 0x0065 }
        r1 = com.android.volley.toolbox.C0582i.m5214a(r0);	 Catch:{ IOException -> 0x0078 }
        r6 = r5.length();	 Catch:{ IOException -> 0x0078 }
        r1.f3619a = r6;	 Catch:{ IOException -> 0x0078 }
        r6 = r1.f3620b;	 Catch:{ IOException -> 0x0078 }
        r9.m5198a(r6, r1);	 Catch:{ IOException -> 0x0078 }
        if (r0 == 0) goto L_0x0052;
    L_0x004f:
        r0.close();	 Catch:{ IOException -> 0x006f }
    L_0x0052:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x002f;
    L_0x0056:
        r0 = move-exception;
        r0 = r1;
    L_0x0058:
        if (r5 == 0) goto L_0x005d;
    L_0x005a:
        r5.delete();	 Catch:{ all -> 0x0073 }
    L_0x005d:
        if (r0 == 0) goto L_0x0052;
    L_0x005f:
        r0.close();	 Catch:{ IOException -> 0x0063 }
        goto L_0x0052;
    L_0x0063:
        r0 = move-exception;
        goto L_0x0052;
    L_0x0065:
        r0 = move-exception;
    L_0x0066:
        if (r1 == 0) goto L_0x006b;
    L_0x0068:
        r1.close();	 Catch:{ IOException -> 0x0071 }
    L_0x006b:
        throw r0;	 Catch:{ all -> 0x006c }
    L_0x006c:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
    L_0x006f:
        r0 = move-exception;
        goto L_0x0052;
    L_0x0071:
        r1 = move-exception;
        goto L_0x006b;
    L_0x0073:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x0066;
    L_0x0078:
        r1 = move-exception;
        goto L_0x0058;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.g.a():void");
    }

    public synchronized void m5209a(String str, C0555c c0555c) {
        m5194a(c0555c.f3506a.length);
        File c = m5213c(str);
        try {
            OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(c));
            C0582i c0582i = new C0582i(str, c0555c);
            if (c0582i.m5216a(bufferedOutputStream)) {
                bufferedOutputStream.write(c0555c.f3506a);
                bufferedOutputStream.close();
                m5198a(str, c0582i);
            } else {
                bufferedOutputStream.close();
                ah.m5058b("Failed to write header for %s", c.getAbsolutePath());
                throw new IOException();
            }
        } catch (IOException e) {
            if (!c.delete()) {
                ah.m5058b("Could not clean up file %s", c.getAbsolutePath());
            }
        }
    }

    public synchronized void m5210a(String str, boolean z) {
        C0555c a = m5207a(str);
        if (a != null) {
            a.f3511f = 0;
            if (z) {
                a.f3510e = 0;
            }
            m5209a(str, a);
        }
    }

    public synchronized void m5211b() {
        synchronized (this) {
            File[] listFiles = this.f3617c.listFiles();
            if (listFiles != null) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
            this.f3615a.clear();
            this.f3616b = 0;
            ah.m5058b("Cache cleared.", new Object[0]);
        }
    }

    public synchronized void m5212b(String str) {
        boolean delete = m5213c(str).delete();
        m5206e(str);
        if (!delete) {
            ah.m5058b("Could not delete cache entry for key=%s, filename=%s", str, m5203d(str));
        }
    }

    public File m5213c(String str) {
        return new File(this.f3617c, m5203d(str));
    }
}
