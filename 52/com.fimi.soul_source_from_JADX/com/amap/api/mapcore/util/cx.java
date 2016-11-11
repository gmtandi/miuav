package com.amap.api.mapcore.util;

import com.tencent.connect.common.Constants;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.codehaus.jackson.util.BufferRecycler;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p124f.p125c.C3022o;

public final class cx implements Closeable {
    static final Pattern f2376a;
    private static final OutputStream f2377q;
    final ThreadPoolExecutor f2378b;
    private final File f2379c;
    private final File f2380d;
    private final File f2381e;
    private final File f2382f;
    private final int f2383g;
    private long f2384h;
    private final int f2385i;
    private long f2386j;
    private Writer f2387k;
    private final LinkedHashMap<String, C0373c> f2388l;
    private int f2389m;
    private da f2390n;
    private long f2391o;
    private final Callable<Void> f2392p;

    /* renamed from: com.amap.api.mapcore.util.cx.a */
    public final class C0371a {
        final /* synthetic */ cx f2360a;
        private final C0373c f2361b;
        private final boolean[] f2362c;
        private boolean f2363d;
        private boolean f2364e;

        /* renamed from: com.amap.api.mapcore.util.cx.a.a */
        class C0370a extends FilterOutputStream {
            final /* synthetic */ C0371a f2359a;

            private C0370a(C0371a c0371a, OutputStream outputStream) {
                this.f2359a = c0371a;
                super(outputStream);
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    this.f2359a.f2363d = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    this.f2359a.f2363d = true;
                }
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException e) {
                    this.f2359a.f2363d = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException e) {
                    this.f2359a.f2363d = true;
                }
            }
        }

        private C0371a(cx cxVar, C0373c c0373c) {
            this.f2360a = cxVar;
            this.f2361b = c0373c;
            this.f2362c = c0373c.f2373d ? null : new boolean[cxVar.f2385i];
        }

        public OutputStream m3941a(int i) {
            if (i < 0 || i >= this.f2360a.f2385i) {
                throw new IllegalArgumentException("Expected index " + i + " to " + "be greater than 0 and less than the maximum value count " + "of " + this.f2360a.f2385i);
            }
            OutputStream d;
            synchronized (this.f2360a) {
                OutputStream fileOutputStream;
                if (this.f2361b.f2374e != this) {
                    throw new IllegalStateException();
                }
                if (!this.f2361b.f2373d) {
                    this.f2362c[i] = true;
                }
                r1 = this.f2361b.m3958b(i);
                try {
                    fileOutputStream = new FileOutputStream(r1);
                } catch (FileNotFoundException e) {
                    this.f2360a.f2379c.mkdirs();
                    try {
                        File b;
                        fileOutputStream = new FileOutputStream(b);
                    } catch (FileNotFoundException e2) {
                        d = cx.f2377q;
                    }
                }
                d = new C0370a(fileOutputStream, null);
            }
            return d;
        }

        public void m3942a() {
            if (this.f2363d) {
                this.f2360a.m3963a(this, false);
                this.f2360a.m3987c(this.f2361b.f2371b);
            } else {
                this.f2360a.m3963a(this, true);
            }
            this.f2364e = true;
        }

        public void m3943b() {
            this.f2360a.m3963a(this, false);
        }
    }

    /* renamed from: com.amap.api.mapcore.util.cx.b */
    public final class C0372b implements Closeable {
        final /* synthetic */ cx f2365a;
        private final String f2366b;
        private final long f2367c;
        private final InputStream[] f2368d;
        private final long[] f2369e;

        private C0372b(cx cxVar, String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.f2365a = cxVar;
            this.f2366b = str;
            this.f2367c = j;
            this.f2368d = inputStreamArr;
            this.f2369e = jArr;
        }

        public InputStream m3944a(int i) {
            return this.f2368d[i];
        }

        public void close() {
            for (Closeable a : this.f2368d) {
                dc.m4001a(a);
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.util.cx.c */
    final class C0373c {
        final /* synthetic */ cx f2370a;
        private final String f2371b;
        private final long[] f2372c;
        private boolean f2373d;
        private C0371a f2374e;
        private long f2375f;

        private C0373c(cx cxVar, String str) {
            this.f2370a = cxVar;
            this.f2371b = str;
            this.f2372c = new long[cxVar.f2385i];
        }

        private void m3949a(String[] strArr) {
            if (strArr.length != this.f2370a.f2385i) {
                throw m3951b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.f2372c[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw m3951b(strArr);
                }
            }
        }

        private IOException m3951b(String[] strArr) {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public File m3956a(int i) {
            return new File(this.f2370a.f2379c, this.f2371b + "." + i);
        }

        public String m3957a() {
            StringBuilder stringBuilder = new StringBuilder();
            for (long append : this.f2372c) {
                stringBuilder.append(C3022o.f15055c).append(append);
            }
            return stringBuilder.toString();
        }

        public File m3958b(int i) {
            return new File(this.f2370a.f2379c, this.f2371b + "." + i + ".tmp");
        }
    }

    static {
        f2376a = Pattern.compile("[a-z0-9_-]{1,120}");
        f2377q = new cz();
    }

    private cx(File file, int i, int i2, long j) {
        this.f2386j = 0;
        this.f2388l = new LinkedHashMap(0, 0.75f, true);
        this.f2391o = 0;
        this.f2378b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.f2392p = new cy(this);
        this.f2379c = file;
        this.f2383g = i;
        this.f2380d = new File(file, "journal");
        this.f2381e = new File(file, "journal.tmp");
        this.f2382f = new File(file, "journal.bkp");
        this.f2385i = i2;
        this.f2384h = j;
    }

    private synchronized C0371a m3960a(String str, long j) {
        C0371a c0371a;
        m3979i();
        m3974e(str);
        C0373c c0373c = (C0373c) this.f2388l.get(str);
        if (j == -1 || (c0373c != null && c0373c.f2375f == j)) {
            C0373c c0373c2;
            if (c0373c == null) {
                c0373c = new C0373c(str, null);
                this.f2388l.put(str, c0373c);
                c0373c2 = c0373c;
            } else if (c0373c.f2374e != null) {
                c0371a = null;
            } else {
                c0373c2 = c0373c;
            }
            c0371a = new C0371a(c0373c2, null);
            c0373c2.f2374e = c0371a;
            this.f2387k.write("DIRTY " + str + '\n');
            this.f2387k.flush();
        } else {
            c0371a = null;
        }
        return c0371a;
    }

    public static cx m3961a(File file, int i, int i2, long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    m3966a(file2, file3, false);
                }
            }
            cx cxVar = new cx(file, i, i2, j);
            if (cxVar.f2380d.exists()) {
                try {
                    cxVar.m3973e();
                    cxVar.m3976f();
                    cxVar.f2387k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cxVar.f2380d, true), dc.f2411a));
                    return cxVar;
                } catch (IOException e) {
                    cxVar.m3986c();
                }
            }
            file.mkdirs();
            cxVar = new cx(file, i, i2, j);
            cxVar.m3977g();
            return cxVar;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m3963a(com.amap.api.mapcore.util.cx.C0371a r11, boolean r12) {
        /*
        r10 = this;
        r0 = 0;
        monitor-enter(r10);
        r2 = r11.f2361b;	 Catch:{ all -> 0x0012 }
        r1 = r2.f2374e;	 Catch:{ all -> 0x0012 }
        if (r1 == r11) goto L_0x0015;
    L_0x000c:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0012 }
        r0.<init>();	 Catch:{ all -> 0x0012 }
        throw r0;	 Catch:{ all -> 0x0012 }
    L_0x0012:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x0015:
        if (r12 == 0) goto L_0x0058;
    L_0x0017:
        r1 = r2.f2373d;	 Catch:{ all -> 0x0012 }
        if (r1 != 0) goto L_0x0058;
    L_0x001d:
        r1 = r0;
    L_0x001e:
        r3 = r10.f2385i;	 Catch:{ all -> 0x0012 }
        if (r1 >= r3) goto L_0x0058;
    L_0x0022:
        r3 = r11.f2362c;	 Catch:{ all -> 0x0012 }
        r3 = r3[r1];	 Catch:{ all -> 0x0012 }
        if (r3 != 0) goto L_0x0046;
    L_0x002a:
        r11.m3943b();	 Catch:{ all -> 0x0012 }
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0012 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0012 }
        r2.<init>();	 Catch:{ all -> 0x0012 }
        r3 = "Newly created entry didn't create value for index ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0012 }
        r1 = r2.append(r1);	 Catch:{ all -> 0x0012 }
        r1 = r1.toString();	 Catch:{ all -> 0x0012 }
        r0.<init>(r1);	 Catch:{ all -> 0x0012 }
        throw r0;	 Catch:{ all -> 0x0012 }
    L_0x0046:
        r3 = r2.m3958b(r1);	 Catch:{ all -> 0x0012 }
        r3 = r3.exists();	 Catch:{ all -> 0x0012 }
        if (r3 != 0) goto L_0x0055;
    L_0x0050:
        r11.m3943b();	 Catch:{ all -> 0x0012 }
    L_0x0053:
        monitor-exit(r10);
        return;
    L_0x0055:
        r1 = r1 + 1;
        goto L_0x001e;
    L_0x0058:
        r1 = r10.f2385i;	 Catch:{ all -> 0x0012 }
        if (r0 >= r1) goto L_0x008d;
    L_0x005c:
        r1 = r2.m3958b(r0);	 Catch:{ all -> 0x0012 }
        if (r12 == 0) goto L_0x0089;
    L_0x0062:
        r3 = r1.exists();	 Catch:{ all -> 0x0012 }
        if (r3 == 0) goto L_0x0086;
    L_0x0068:
        r3 = r2.m3956a(r0);	 Catch:{ all -> 0x0012 }
        r1.renameTo(r3);	 Catch:{ all -> 0x0012 }
        r1 = r2.f2372c;	 Catch:{ all -> 0x0012 }
        r4 = r1[r0];	 Catch:{ all -> 0x0012 }
        r6 = r3.length();	 Catch:{ all -> 0x0012 }
        r1 = r2.f2372c;	 Catch:{ all -> 0x0012 }
        r1[r0] = r6;	 Catch:{ all -> 0x0012 }
        r8 = r10.f2386j;	 Catch:{ all -> 0x0012 }
        r4 = r8 - r4;
        r4 = r4 + r6;
        r10.f2386j = r4;	 Catch:{ all -> 0x0012 }
    L_0x0086:
        r0 = r0 + 1;
        goto L_0x0058;
    L_0x0089:
        m3965a(r1);	 Catch:{ all -> 0x0012 }
        goto L_0x0086;
    L_0x008d:
        r0 = r10.f2389m;	 Catch:{ all -> 0x0012 }
        r0 = r0 + 1;
        r10.f2389m = r0;	 Catch:{ all -> 0x0012 }
        r0 = 0;
        r2.f2374e = r0;	 Catch:{ all -> 0x0012 }
        r0 = r2.f2373d;	 Catch:{ all -> 0x0012 }
        r0 = r0 | r12;
        if (r0 == 0) goto L_0x00f4;
    L_0x009e:
        r0 = 1;
        r2.f2373d = r0;	 Catch:{ all -> 0x0012 }
        r0 = r10.f2387k;	 Catch:{ all -> 0x0012 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0012 }
        r1.<init>();	 Catch:{ all -> 0x0012 }
        r3 = "CLEAN ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r3 = r2.f2371b;	 Catch:{ all -> 0x0012 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r3 = r2.m3957a();	 Catch:{ all -> 0x0012 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r3 = 10;
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r1 = r1.toString();	 Catch:{ all -> 0x0012 }
        r0.write(r1);	 Catch:{ all -> 0x0012 }
        if (r12 == 0) goto L_0x00d8;
    L_0x00ce:
        r0 = r10.f2391o;	 Catch:{ all -> 0x0012 }
        r4 = 1;
        r4 = r4 + r0;
        r10.f2391o = r4;	 Catch:{ all -> 0x0012 }
        r2.f2375f = r0;	 Catch:{ all -> 0x0012 }
    L_0x00d8:
        r0 = r10.f2387k;	 Catch:{ all -> 0x0012 }
        r0.flush();	 Catch:{ all -> 0x0012 }
        r0 = r10.f2386j;	 Catch:{ all -> 0x0012 }
        r2 = r10.f2384h;	 Catch:{ all -> 0x0012 }
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 > 0) goto L_0x00eb;
    L_0x00e5:
        r0 = r10.m3978h();	 Catch:{ all -> 0x0012 }
        if (r0 == 0) goto L_0x0053;
    L_0x00eb:
        r0 = r10.f2378b;	 Catch:{ all -> 0x0012 }
        r1 = r10.f2392p;	 Catch:{ all -> 0x0012 }
        r0.submit(r1);	 Catch:{ all -> 0x0012 }
        goto L_0x0053;
    L_0x00f4:
        r0 = r10.f2388l;	 Catch:{ all -> 0x0012 }
        r1 = r2.f2371b;	 Catch:{ all -> 0x0012 }
        r0.remove(r1);	 Catch:{ all -> 0x0012 }
        r0 = r10.f2387k;	 Catch:{ all -> 0x0012 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0012 }
        r1.<init>();	 Catch:{ all -> 0x0012 }
        r3 = "REMOVE ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r2 = r2.f2371b;	 Catch:{ all -> 0x0012 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0012 }
        r2 = 10;
        r1 = r1.append(r2);	 Catch:{ all -> 0x0012 }
        r1 = r1.toString();	 Catch:{ all -> 0x0012 }
        r0.write(r1);	 Catch:{ all -> 0x0012 }
        goto L_0x00d8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.cx.a(com.amap.api.mapcore.util.cx$a, boolean):void");
    }

    private static void m3965a(File file) {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void m3966a(File file, File file2, boolean z) {
        if (z) {
            m3965a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    private void m3971d(String str) {
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        String str2;
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            String substring = str.substring(i);
            if (indexOf == "REMOVE".length() && str.startsWith("REMOVE")) {
                this.f2388l.remove(substring);
                return;
            }
            str2 = substring;
        } else {
            str2 = str.substring(i, indexOf2);
        }
        C0373c c0373c = (C0373c) this.f2388l.get(str2);
        if (c0373c == null) {
            c0373c = new C0373c(str2, null);
            this.f2388l.put(str2, c0373c);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            c0373c.f2373d = true;
            c0373c.f2374e = null;
            c0373c.m3949a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            c0373c.f2374e = new C0371a(c0373c, null);
        } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void m3973e() {
        Closeable dbVar = new db(new FileInputStream(this.f2380d), dc.f2411a);
        int i;
        try {
            String a = dbVar.m4000a();
            String a2 = dbVar.m4000a();
            String a3 = dbVar.m4000a();
            String a4 = dbVar.m4000a();
            String a5 = dbVar.m4000a();
            if ("libcore.io.DiskLruCache".equals(a) && Constants.VIA_TO_TYPE_QQ_GROUP.equals(a2) && Integer.toString(this.f2383g).equals(a3) && Integer.toString(this.f2385i).equals(a4) && C2915a.f14760f.equals(a5)) {
                i = 0;
                while (true) {
                    m3971d(dbVar.m4000a());
                    i++;
                }
            } else {
                throw new IOException("unexpected journal header: [" + a + ", " + a2 + ", " + a4 + ", " + a5 + "]");
            }
        } catch (EOFException e) {
            this.f2389m = i - this.f2388l.size();
            dc.m4001a(dbVar);
        } catch (Throwable th) {
            dc.m4001a(dbVar);
        }
    }

    private void m3974e(String str) {
        if (!f2376a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }

    private void m3976f() {
        m3965a(this.f2381e);
        Iterator it = this.f2388l.values().iterator();
        while (it.hasNext()) {
            C0373c c0373c = (C0373c) it.next();
            int i;
            if (c0373c.f2374e == null) {
                for (i = 0; i < this.f2385i; i++) {
                    this.f2386j += c0373c.f2372c[i];
                }
            } else {
                c0373c.f2374e = null;
                for (i = 0; i < this.f2385i; i++) {
                    m3965a(c0373c.m3956a(i));
                    m3965a(c0373c.m3958b(i));
                }
                it.remove();
            }
        }
    }

    private synchronized void m3977g() {
        if (this.f2387k != null) {
            this.f2387k.close();
        }
        Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f2381e), dc.f2411a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write(Constants.VIA_TO_TYPE_QQ_GROUP);
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f2383g));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f2385i));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (C0373c c0373c : this.f2388l.values()) {
                if (c0373c.f2374e != null) {
                    bufferedWriter.write("DIRTY " + c0373c.f2371b + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + c0373c.f2371b + c0373c.m3957a() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.f2380d.exists()) {
                m3966a(this.f2380d, this.f2382f, true);
            }
            m3966a(this.f2381e, this.f2380d, false);
            this.f2382f.delete();
            this.f2387k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f2380d, true), dc.f2411a));
        } catch (Throwable th) {
            bufferedWriter.close();
        }
    }

    private boolean m3978h() {
        return this.f2389m >= BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN && this.f2389m >= this.f2388l.size();
    }

    private void m3979i() {
        if (this.f2387k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    private void m3980j() {
        while (this.f2386j > this.f2384h) {
            String str = (String) ((Entry) this.f2388l.entrySet().iterator().next()).getKey();
            m3987c(str);
            if (this.f2390n != null) {
                this.f2390n.m3876a(str);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.amap.api.mapcore.util.cx.C0372b m3981a(java.lang.String r10) {
        /*
        r9 = this;
        r2 = 0;
        r1 = 0;
        monitor-enter(r9);
        r9.m3979i();	 Catch:{ all -> 0x0086 }
        r9.m3974e(r10);	 Catch:{ all -> 0x0086 }
        r0 = r9.f2388l;	 Catch:{ all -> 0x0086 }
        r0 = r0.get(r10);	 Catch:{ all -> 0x0086 }
        r0 = (com.amap.api.mapcore.util.cx.C0373c) r0;	 Catch:{ all -> 0x0086 }
        if (r0 != 0) goto L_0x0015;
    L_0x0013:
        monitor-exit(r9);
        return r1;
    L_0x0015:
        r3 = r0.f2373d;	 Catch:{ all -> 0x0086 }
        if (r3 == 0) goto L_0x0013;
    L_0x001b:
        r3 = r9.f2385i;	 Catch:{ all -> 0x0086 }
        r6 = new java.io.InputStream[r3];	 Catch:{ all -> 0x0086 }
        r3 = r2;
    L_0x0020:
        r4 = r9.f2385i;	 Catch:{ FileNotFoundException -> 0x0032 }
        if (r3 >= r4) goto L_0x0044;
    L_0x0024:
        r4 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0032 }
        r5 = r0.m3956a(r3);	 Catch:{ FileNotFoundException -> 0x0032 }
        r4.<init>(r5);	 Catch:{ FileNotFoundException -> 0x0032 }
        r6[r3] = r4;	 Catch:{ FileNotFoundException -> 0x0032 }
        r3 = r3 + 1;
        goto L_0x0020;
    L_0x0032:
        r0 = move-exception;
        r0 = r2;
    L_0x0034:
        r2 = r9.f2385i;	 Catch:{ all -> 0x0086 }
        if (r0 >= r2) goto L_0x0013;
    L_0x0038:
        r2 = r6[r0];	 Catch:{ all -> 0x0086 }
        if (r2 == 0) goto L_0x0013;
    L_0x003c:
        r2 = r6[r0];	 Catch:{ all -> 0x0086 }
        com.amap.api.mapcore.util.dc.m4001a(r2);	 Catch:{ all -> 0x0086 }
        r0 = r0 + 1;
        goto L_0x0034;
    L_0x0044:
        r1 = r9.f2389m;	 Catch:{ all -> 0x0086 }
        r1 = r1 + 1;
        r9.f2389m = r1;	 Catch:{ all -> 0x0086 }
        r1 = r9.f2387k;	 Catch:{ all -> 0x0086 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0086 }
        r2.<init>();	 Catch:{ all -> 0x0086 }
        r3 = "READ ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0086 }
        r2 = r2.append(r10);	 Catch:{ all -> 0x0086 }
        r3 = 10;
        r2 = r2.append(r3);	 Catch:{ all -> 0x0086 }
        r2 = r2.toString();	 Catch:{ all -> 0x0086 }
        r1.append(r2);	 Catch:{ all -> 0x0086 }
        r1 = r9.m3978h();	 Catch:{ all -> 0x0086 }
        if (r1 == 0) goto L_0x0075;
    L_0x006e:
        r1 = r9.f2378b;	 Catch:{ all -> 0x0086 }
        r2 = r9.f2392p;	 Catch:{ all -> 0x0086 }
        r1.submit(r2);	 Catch:{ all -> 0x0086 }
    L_0x0075:
        r1 = new com.amap.api.mapcore.util.cx$b;	 Catch:{ all -> 0x0086 }
        r4 = r0.f2375f;	 Catch:{ all -> 0x0086 }
        r7 = r0.f2372c;	 Catch:{ all -> 0x0086 }
        r8 = 0;
        r2 = r9;
        r3 = r10;
        r1.<init>(r3, r4, r6, r7, r8);	 Catch:{ all -> 0x0086 }
        goto L_0x0013;
    L_0x0086:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.cx.a(java.lang.String):com.amap.api.mapcore.util.cx$b");
    }

    public void m3982a(da daVar) {
        this.f2390n = daVar;
    }

    public synchronized boolean m3983a() {
        return this.f2387k == null;
    }

    public C0371a m3984b(String str) {
        return m3960a(str, -1);
    }

    public synchronized void m3985b() {
        m3979i();
        m3980j();
        this.f2387k.flush();
    }

    public void m3986c() {
        close();
        dc.m4002a(this.f2379c);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean m3987c(java.lang.String r7) {
        /*
        r6 = this;
        r1 = 0;
        monitor-enter(r6);
        r6.m3979i();	 Catch:{ all -> 0x005d }
        r6.m3974e(r7);	 Catch:{ all -> 0x005d }
        r0 = r6.f2388l;	 Catch:{ all -> 0x005d }
        r0 = r0.get(r7);	 Catch:{ all -> 0x005d }
        r0 = (com.amap.api.mapcore.util.cx.C0373c) r0;	 Catch:{ all -> 0x005d }
        if (r0 == 0) goto L_0x0018;
    L_0x0012:
        r2 = r0.f2374e;	 Catch:{ all -> 0x005d }
        if (r2 == 0) goto L_0x0030;
    L_0x0018:
        r0 = r1;
    L_0x0019:
        monitor-exit(r6);
        return r0;
    L_0x001b:
        r2 = r6.f2386j;	 Catch:{ all -> 0x005d }
        r4 = r0.f2372c;	 Catch:{ all -> 0x005d }
        r4 = r4[r1];	 Catch:{ all -> 0x005d }
        r2 = r2 - r4;
        r6.f2386j = r2;	 Catch:{ all -> 0x005d }
        r2 = r0.f2372c;	 Catch:{ all -> 0x005d }
        r4 = 0;
        r2[r1] = r4;	 Catch:{ all -> 0x005d }
        r1 = r1 + 1;
    L_0x0030:
        r2 = r6.f2385i;	 Catch:{ all -> 0x005d }
        if (r1 >= r2) goto L_0x0060;
    L_0x0034:
        r2 = r0.m3956a(r1);	 Catch:{ all -> 0x005d }
        r3 = r2.exists();	 Catch:{ all -> 0x005d }
        if (r3 == 0) goto L_0x001b;
    L_0x003e:
        r3 = r2.delete();	 Catch:{ all -> 0x005d }
        if (r3 != 0) goto L_0x001b;
    L_0x0044:
        r0 = new java.io.IOException;	 Catch:{ all -> 0x005d }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x005d }
        r1.<init>();	 Catch:{ all -> 0x005d }
        r3 = "failed to delete ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x005d }
        r1 = r1.append(r2);	 Catch:{ all -> 0x005d }
        r1 = r1.toString();	 Catch:{ all -> 0x005d }
        r0.<init>(r1);	 Catch:{ all -> 0x005d }
        throw r0;	 Catch:{ all -> 0x005d }
    L_0x005d:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
    L_0x0060:
        r0 = r6.f2389m;	 Catch:{ all -> 0x005d }
        r0 = r0 + 1;
        r6.f2389m = r0;	 Catch:{ all -> 0x005d }
        r0 = r6.f2387k;	 Catch:{ all -> 0x005d }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x005d }
        r1.<init>();	 Catch:{ all -> 0x005d }
        r2 = "REMOVE ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x005d }
        r1 = r1.append(r7);	 Catch:{ all -> 0x005d }
        r2 = 10;
        r1 = r1.append(r2);	 Catch:{ all -> 0x005d }
        r1 = r1.toString();	 Catch:{ all -> 0x005d }
        r0.append(r1);	 Catch:{ all -> 0x005d }
        r0 = r6.f2388l;	 Catch:{ all -> 0x005d }
        r0.remove(r7);	 Catch:{ all -> 0x005d }
        r0 = r6.m3978h();	 Catch:{ all -> 0x005d }
        if (r0 == 0) goto L_0x0096;
    L_0x008f:
        r0 = r6.f2378b;	 Catch:{ all -> 0x005d }
        r1 = r6.f2392p;	 Catch:{ all -> 0x005d }
        r0.submit(r1);	 Catch:{ all -> 0x005d }
    L_0x0096:
        r0 = 1;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.cx.c(java.lang.String):boolean");
    }

    public synchronized void close() {
        if (this.f2387k != null) {
            Iterator it = new ArrayList(this.f2388l.values()).iterator();
            while (it.hasNext()) {
                C0373c c0373c = (C0373c) it.next();
                if (c0373c.f2374e != null) {
                    c0373c.f2374e.m3943b();
                }
            }
            m3980j();
            this.f2387k.close();
            this.f2387k = null;
        }
    }
}
