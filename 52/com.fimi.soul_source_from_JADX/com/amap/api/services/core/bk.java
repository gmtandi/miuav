package com.amap.api.services.core;

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

public final class bk implements Closeable {
    static final Pattern f3096a;
    private static final OutputStream f3097q;
    final ThreadPoolExecutor f3098b;
    private final File f3099c;
    private final File f3100d;
    private final File f3101e;
    private final File f3102f;
    private final int f3103g;
    private long f3104h;
    private final int f3105i;
    private long f3106j;
    private Writer f3107k;
    private final LinkedHashMap<String, C0466c> f3108l;
    private int f3109m;
    private bn f3110n;
    private long f3111o;
    private final Callable<Void> f3112p;

    /* renamed from: com.amap.api.services.core.bk.a */
    public final class C0464a {
        final /* synthetic */ bk f3080a;
        private final C0466c f3081b;
        private final boolean[] f3082c;
        private boolean f3083d;
        private boolean f3084e;

        /* renamed from: com.amap.api.services.core.bk.a.a */
        class C0463a extends FilterOutputStream {
            final /* synthetic */ C0464a f3079a;

            private C0463a(C0464a c0464a, OutputStream outputStream) {
                this.f3079a = c0464a;
                super(outputStream);
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    this.f3079a.f3083d = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    this.f3079a.f3083d = true;
                }
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException e) {
                    this.f3079a.f3083d = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException e) {
                    this.f3079a.f3083d = true;
                }
            }
        }

        private C0464a(bk bkVar, C0466c c0466c) {
            this.f3080a = bkVar;
            this.f3081b = c0466c;
            this.f3082c = c0466c.f3093d ? null : new boolean[bkVar.f3105i];
        }

        public OutputStream m4675a(int i) {
            if (i < 0 || i >= this.f3080a.f3105i) {
                throw new IllegalArgumentException("Expected index " + i + " to " + "be greater than 0 and less than the maximum value count " + "of " + this.f3080a.f3105i);
            }
            OutputStream d;
            synchronized (this.f3080a) {
                File b;
                OutputStream fileOutputStream;
                if (this.f3081b.f3094e != this) {
                    throw new IllegalStateException();
                }
                if (!this.f3081b.f3093d) {
                    this.f3082c[i] = true;
                }
                b = this.f3081b.m4692b(i);
                try {
                    fileOutputStream = new FileOutputStream(b);
                } catch (FileNotFoundException e) {
                    this.f3080a.f3099c.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(b);
                    } catch (FileNotFoundException e2) {
                        d = bk.f3097q;
                    }
                }
                d = new C0463a(fileOutputStream, null);
            }
            return d;
        }

        public void m4676a() {
            if (this.f3083d) {
                this.f3080a.m4697a(this, false);
                this.f3080a.m4721c(this.f3081b.f3091b);
            } else {
                this.f3080a.m4697a(this, true);
            }
            this.f3084e = true;
        }

        public void m4677b() {
            this.f3080a.m4697a(this, false);
        }
    }

    /* renamed from: com.amap.api.services.core.bk.b */
    public final class C0465b implements Closeable {
        final /* synthetic */ bk f3085a;
        private final String f3086b;
        private final long f3087c;
        private final InputStream[] f3088d;
        private final long[] f3089e;

        private C0465b(bk bkVar, String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.f3085a = bkVar;
            this.f3086b = str;
            this.f3087c = j;
            this.f3088d = inputStreamArr;
            this.f3089e = jArr;
        }

        public InputStream m4678a(int i) {
            return this.f3088d[i];
        }

        public void close() {
            for (Closeable a : this.f3088d) {
                bp.m4726a(a);
            }
        }
    }

    /* renamed from: com.amap.api.services.core.bk.c */
    final class C0466c {
        final /* synthetic */ bk f3090a;
        private final String f3091b;
        private final long[] f3092c;
        private boolean f3093d;
        private C0464a f3094e;
        private long f3095f;

        private C0466c(bk bkVar, String str) {
            this.f3090a = bkVar;
            this.f3091b = str;
            this.f3092c = new long[bkVar.f3105i];
        }

        private void m4683a(String[] strArr) {
            if (strArr.length != this.f3090a.f3105i) {
                throw m4685b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.f3092c[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw m4685b(strArr);
                }
            }
        }

        private IOException m4685b(String[] strArr) {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public File m4690a(int i) {
            return new File(this.f3090a.f3099c, this.f3091b + "." + i);
        }

        public String m4691a() {
            StringBuilder stringBuilder = new StringBuilder();
            for (long append : this.f3092c) {
                stringBuilder.append(C3022o.f15055c).append(append);
            }
            return stringBuilder.toString();
        }

        public File m4692b(int i) {
            return new File(this.f3090a.f3099c, this.f3091b + "." + i + ".tmp");
        }
    }

    static {
        f3096a = Pattern.compile("[a-z0-9_-]{1,120}");
        f3097q = new bm();
    }

    private bk(File file, int i, int i2, long j) {
        this.f3106j = 0;
        this.f3108l = new LinkedHashMap(0, 0.75f, true);
        this.f3111o = 0;
        this.f3098b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.f3112p = new bl(this);
        this.f3099c = file;
        this.f3103g = i;
        this.f3100d = new File(file, "journal");
        this.f3101e = new File(file, "journal.tmp");
        this.f3102f = new File(file, "journal.bkp");
        this.f3105i = i2;
        this.f3104h = j;
    }

    private synchronized C0464a m4694a(String str, long j) {
        C0464a c0464a;
        m4713i();
        m4708e(str);
        C0466c c0466c = (C0466c) this.f3108l.get(str);
        if (j == -1 || (c0466c != null && c0466c.f3095f == j)) {
            C0466c c0466c2;
            if (c0466c == null) {
                c0466c = new C0466c(str, null);
                this.f3108l.put(str, c0466c);
                c0466c2 = c0466c;
            } else if (c0466c.f3094e != null) {
                c0464a = null;
            } else {
                c0466c2 = c0466c;
            }
            c0464a = new C0464a(c0466c2, null);
            c0466c2.f3094e = c0464a;
            this.f3107k.write("DIRTY " + str + '\n');
            this.f3107k.flush();
        } else {
            c0464a = null;
        }
        return c0464a;
    }

    public static bk m4695a(File file, int i, int i2, long j) {
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
                    m4700a(file2, file3, false);
                }
            }
            bk bkVar = new bk(file, i, i2, j);
            if (bkVar.f3100d.exists()) {
                try {
                    bkVar.m4707e();
                    bkVar.m4710f();
                    bkVar.f3107k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(bkVar.f3100d, true), bp.f3120a));
                    return bkVar;
                } catch (IOException e) {
                    System.out.println("DiskLruCache " + file + " is corrupt: " + e.getMessage() + ", removing");
                    bkVar.m4720c();
                }
            }
            file.mkdirs();
            bkVar = new bk(file, i, i2, j);
            bkVar.m4711g();
            return bkVar;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m4697a(com.amap.api.services.core.bk.C0464a r11, boolean r12) {
        /*
        r10 = this;
        r0 = 0;
        monitor-enter(r10);
        r2 = r11.f3081b;	 Catch:{ all -> 0x0012 }
        r1 = r2.f3094e;	 Catch:{ all -> 0x0012 }
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
        r1 = r2.f3093d;	 Catch:{ all -> 0x0012 }
        if (r1 != 0) goto L_0x0058;
    L_0x001d:
        r1 = r0;
    L_0x001e:
        r3 = r10.f3105i;	 Catch:{ all -> 0x0012 }
        if (r1 >= r3) goto L_0x0058;
    L_0x0022:
        r3 = r11.f3082c;	 Catch:{ all -> 0x0012 }
        r3 = r3[r1];	 Catch:{ all -> 0x0012 }
        if (r3 != 0) goto L_0x0046;
    L_0x002a:
        r11.m4677b();	 Catch:{ all -> 0x0012 }
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
        r3 = r2.m4692b(r1);	 Catch:{ all -> 0x0012 }
        r3 = r3.exists();	 Catch:{ all -> 0x0012 }
        if (r3 != 0) goto L_0x0055;
    L_0x0050:
        r11.m4677b();	 Catch:{ all -> 0x0012 }
    L_0x0053:
        monitor-exit(r10);
        return;
    L_0x0055:
        r1 = r1 + 1;
        goto L_0x001e;
    L_0x0058:
        r1 = r10.f3105i;	 Catch:{ all -> 0x0012 }
        if (r0 >= r1) goto L_0x008d;
    L_0x005c:
        r1 = r2.m4692b(r0);	 Catch:{ all -> 0x0012 }
        if (r12 == 0) goto L_0x0089;
    L_0x0062:
        r3 = r1.exists();	 Catch:{ all -> 0x0012 }
        if (r3 == 0) goto L_0x0086;
    L_0x0068:
        r3 = r2.m4690a(r0);	 Catch:{ all -> 0x0012 }
        r1.renameTo(r3);	 Catch:{ all -> 0x0012 }
        r1 = r2.f3092c;	 Catch:{ all -> 0x0012 }
        r4 = r1[r0];	 Catch:{ all -> 0x0012 }
        r6 = r3.length();	 Catch:{ all -> 0x0012 }
        r1 = r2.f3092c;	 Catch:{ all -> 0x0012 }
        r1[r0] = r6;	 Catch:{ all -> 0x0012 }
        r8 = r10.f3106j;	 Catch:{ all -> 0x0012 }
        r4 = r8 - r4;
        r4 = r4 + r6;
        r10.f3106j = r4;	 Catch:{ all -> 0x0012 }
    L_0x0086:
        r0 = r0 + 1;
        goto L_0x0058;
    L_0x0089:
        m4699a(r1);	 Catch:{ all -> 0x0012 }
        goto L_0x0086;
    L_0x008d:
        r0 = r10.f3109m;	 Catch:{ all -> 0x0012 }
        r0 = r0 + 1;
        r10.f3109m = r0;	 Catch:{ all -> 0x0012 }
        r0 = 0;
        r2.f3094e = r0;	 Catch:{ all -> 0x0012 }
        r0 = r2.f3093d;	 Catch:{ all -> 0x0012 }
        r0 = r0 | r12;
        if (r0 == 0) goto L_0x00f4;
    L_0x009e:
        r0 = 1;
        r2.f3093d = r0;	 Catch:{ all -> 0x0012 }
        r0 = r10.f3107k;	 Catch:{ all -> 0x0012 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0012 }
        r1.<init>();	 Catch:{ all -> 0x0012 }
        r3 = "CLEAN ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r3 = r2.f3091b;	 Catch:{ all -> 0x0012 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r3 = r2.m4691a();	 Catch:{ all -> 0x0012 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r3 = 10;
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r1 = r1.toString();	 Catch:{ all -> 0x0012 }
        r0.write(r1);	 Catch:{ all -> 0x0012 }
        if (r12 == 0) goto L_0x00d8;
    L_0x00ce:
        r0 = r10.f3111o;	 Catch:{ all -> 0x0012 }
        r4 = 1;
        r4 = r4 + r0;
        r10.f3111o = r4;	 Catch:{ all -> 0x0012 }
        r2.f3095f = r0;	 Catch:{ all -> 0x0012 }
    L_0x00d8:
        r0 = r10.f3107k;	 Catch:{ all -> 0x0012 }
        r0.flush();	 Catch:{ all -> 0x0012 }
        r0 = r10.f3106j;	 Catch:{ all -> 0x0012 }
        r2 = r10.f3104h;	 Catch:{ all -> 0x0012 }
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 > 0) goto L_0x00eb;
    L_0x00e5:
        r0 = r10.m4712h();	 Catch:{ all -> 0x0012 }
        if (r0 == 0) goto L_0x0053;
    L_0x00eb:
        r0 = r10.f3098b;	 Catch:{ all -> 0x0012 }
        r1 = r10.f3112p;	 Catch:{ all -> 0x0012 }
        r0.submit(r1);	 Catch:{ all -> 0x0012 }
        goto L_0x0053;
    L_0x00f4:
        r0 = r10.f3108l;	 Catch:{ all -> 0x0012 }
        r1 = r2.f3091b;	 Catch:{ all -> 0x0012 }
        r0.remove(r1);	 Catch:{ all -> 0x0012 }
        r0 = r10.f3107k;	 Catch:{ all -> 0x0012 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0012 }
        r1.<init>();	 Catch:{ all -> 0x0012 }
        r3 = "REMOVE ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r2 = r2.f3091b;	 Catch:{ all -> 0x0012 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0012 }
        r2 = 10;
        r1 = r1.append(r2);	 Catch:{ all -> 0x0012 }
        r1 = r1.toString();	 Catch:{ all -> 0x0012 }
        r0.write(r1);	 Catch:{ all -> 0x0012 }
        goto L_0x00d8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.core.bk.a(com.amap.api.services.core.bk$a, boolean):void");
    }

    private static void m4699a(File file) {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void m4700a(File file, File file2, boolean z) {
        if (z) {
            m4699a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    private void m4705d(String str) {
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
                this.f3108l.remove(substring);
                return;
            }
            str2 = substring;
        } else {
            str2 = str.substring(i, indexOf2);
        }
        C0466c c0466c = (C0466c) this.f3108l.get(str2);
        if (c0466c == null) {
            c0466c = new C0466c(str2, null);
            this.f3108l.put(str2, c0466c);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            c0466c.f3093d = true;
            c0466c.f3094e = null;
            c0466c.m4683a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            c0466c.f3094e = new C0464a(c0466c, null);
        } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void m4707e() {
        Closeable boVar = new bo(new FileInputStream(this.f3100d), bp.f3120a);
        int i;
        try {
            String a = boVar.m4725a();
            String a2 = boVar.m4725a();
            String a3 = boVar.m4725a();
            String a4 = boVar.m4725a();
            String a5 = boVar.m4725a();
            if ("libcore.io.DiskLruCache".equals(a) && Constants.VIA_TO_TYPE_QQ_GROUP.equals(a2) && Integer.toString(this.f3103g).equals(a3) && Integer.toString(this.f3105i).equals(a4) && C2915a.f14760f.equals(a5)) {
                i = 0;
                while (true) {
                    m4705d(boVar.m4725a());
                    i++;
                }
            } else {
                throw new IOException("unexpected journal header: [" + a + ", " + a2 + ", " + a4 + ", " + a5 + "]");
            }
        } catch (EOFException e) {
            this.f3109m = i - this.f3108l.size();
            bp.m4726a(boVar);
        } catch (Throwable th) {
            bp.m4726a(boVar);
        }
    }

    private void m4708e(String str) {
        if (!f3096a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }

    private void m4710f() {
        m4699a(this.f3101e);
        Iterator it = this.f3108l.values().iterator();
        while (it.hasNext()) {
            C0466c c0466c = (C0466c) it.next();
            int i;
            if (c0466c.f3094e == null) {
                for (i = 0; i < this.f3105i; i++) {
                    this.f3106j += c0466c.f3092c[i];
                }
            } else {
                c0466c.f3094e = null;
                for (i = 0; i < this.f3105i; i++) {
                    m4699a(c0466c.m4690a(i));
                    m4699a(c0466c.m4692b(i));
                }
                it.remove();
            }
        }
    }

    private synchronized void m4711g() {
        if (this.f3107k != null) {
            this.f3107k.close();
        }
        Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f3101e), bp.f3120a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write(Constants.VIA_TO_TYPE_QQ_GROUP);
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f3103g));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f3105i));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (C0466c c0466c : this.f3108l.values()) {
                if (c0466c.f3094e != null) {
                    bufferedWriter.write("DIRTY " + c0466c.f3091b + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + c0466c.f3091b + c0466c.m4691a() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.f3100d.exists()) {
                m4700a(this.f3100d, this.f3102f, true);
            }
            m4700a(this.f3101e, this.f3100d, false);
            this.f3102f.delete();
            this.f3107k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f3100d, true), bp.f3120a));
        } catch (Throwable th) {
            bufferedWriter.close();
        }
    }

    private boolean m4712h() {
        return this.f3109m >= BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN && this.f3109m >= this.f3108l.size();
    }

    private void m4713i() {
        if (this.f3107k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    private void m4714j() {
        while (this.f3106j > this.f3104h) {
            String str = (String) ((Entry) this.f3108l.entrySet().iterator().next()).getKey();
            m4721c(str);
            if (this.f3110n != null) {
                this.f3110n.m4617a(str);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.amap.api.services.core.bk.C0465b m4715a(java.lang.String r10) {
        /*
        r9 = this;
        r2 = 0;
        r1 = 0;
        monitor-enter(r9);
        r9.m4713i();	 Catch:{ all -> 0x0086 }
        r9.m4708e(r10);	 Catch:{ all -> 0x0086 }
        r0 = r9.f3108l;	 Catch:{ all -> 0x0086 }
        r0 = r0.get(r10);	 Catch:{ all -> 0x0086 }
        r0 = (com.amap.api.services.core.bk.C0466c) r0;	 Catch:{ all -> 0x0086 }
        if (r0 != 0) goto L_0x0015;
    L_0x0013:
        monitor-exit(r9);
        return r1;
    L_0x0015:
        r3 = r0.f3093d;	 Catch:{ all -> 0x0086 }
        if (r3 == 0) goto L_0x0013;
    L_0x001b:
        r3 = r9.f3105i;	 Catch:{ all -> 0x0086 }
        r6 = new java.io.InputStream[r3];	 Catch:{ all -> 0x0086 }
        r3 = r2;
    L_0x0020:
        r4 = r9.f3105i;	 Catch:{ FileNotFoundException -> 0x0032 }
        if (r3 >= r4) goto L_0x0044;
    L_0x0024:
        r4 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0032 }
        r5 = r0.m4690a(r3);	 Catch:{ FileNotFoundException -> 0x0032 }
        r4.<init>(r5);	 Catch:{ FileNotFoundException -> 0x0032 }
        r6[r3] = r4;	 Catch:{ FileNotFoundException -> 0x0032 }
        r3 = r3 + 1;
        goto L_0x0020;
    L_0x0032:
        r0 = move-exception;
        r0 = r2;
    L_0x0034:
        r2 = r9.f3105i;	 Catch:{ all -> 0x0086 }
        if (r0 >= r2) goto L_0x0013;
    L_0x0038:
        r2 = r6[r0];	 Catch:{ all -> 0x0086 }
        if (r2 == 0) goto L_0x0013;
    L_0x003c:
        r2 = r6[r0];	 Catch:{ all -> 0x0086 }
        com.amap.api.services.core.bp.m4726a(r2);	 Catch:{ all -> 0x0086 }
        r0 = r0 + 1;
        goto L_0x0034;
    L_0x0044:
        r1 = r9.f3109m;	 Catch:{ all -> 0x0086 }
        r1 = r1 + 1;
        r9.f3109m = r1;	 Catch:{ all -> 0x0086 }
        r1 = r9.f3107k;	 Catch:{ all -> 0x0086 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0086 }
        r2.<init>();	 Catch:{ all -> 0x0086 }
        r3 = "READ ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0086 }
        r2 = r2.append(r10);	 Catch:{ all -> 0x0086 }
        r3 = 10;
        r2 = r2.append(r3);	 Catch:{ all -> 0x0086 }
        r2 = r2.toString();	 Catch:{ all -> 0x0086 }
        r1.append(r2);	 Catch:{ all -> 0x0086 }
        r1 = r9.m4712h();	 Catch:{ all -> 0x0086 }
        if (r1 == 0) goto L_0x0075;
    L_0x006e:
        r1 = r9.f3098b;	 Catch:{ all -> 0x0086 }
        r2 = r9.f3112p;	 Catch:{ all -> 0x0086 }
        r1.submit(r2);	 Catch:{ all -> 0x0086 }
    L_0x0075:
        r1 = new com.amap.api.services.core.bk$b;	 Catch:{ all -> 0x0086 }
        r4 = r0.f3095f;	 Catch:{ all -> 0x0086 }
        r7 = r0.f3092c;	 Catch:{ all -> 0x0086 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.core.bk.a(java.lang.String):com.amap.api.services.core.bk$b");
    }

    public void m4716a(bn bnVar) {
        this.f3110n = bnVar;
    }

    public synchronized boolean m4717a() {
        return this.f3107k == null;
    }

    public C0464a m4718b(String str) {
        return m4694a(str, -1);
    }

    public synchronized void m4719b() {
        m4713i();
        m4714j();
        this.f3107k.flush();
    }

    public void m4720c() {
        close();
        bp.m4727a(this.f3099c);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean m4721c(java.lang.String r7) {
        /*
        r6 = this;
        r1 = 0;
        monitor-enter(r6);
        r6.m4713i();	 Catch:{ all -> 0x005d }
        r6.m4708e(r7);	 Catch:{ all -> 0x005d }
        r0 = r6.f3108l;	 Catch:{ all -> 0x005d }
        r0 = r0.get(r7);	 Catch:{ all -> 0x005d }
        r0 = (com.amap.api.services.core.bk.C0466c) r0;	 Catch:{ all -> 0x005d }
        if (r0 == 0) goto L_0x0018;
    L_0x0012:
        r2 = r0.f3094e;	 Catch:{ all -> 0x005d }
        if (r2 == 0) goto L_0x0030;
    L_0x0018:
        r0 = r1;
    L_0x0019:
        monitor-exit(r6);
        return r0;
    L_0x001b:
        r2 = r6.f3106j;	 Catch:{ all -> 0x005d }
        r4 = r0.f3092c;	 Catch:{ all -> 0x005d }
        r4 = r4[r1];	 Catch:{ all -> 0x005d }
        r2 = r2 - r4;
        r6.f3106j = r2;	 Catch:{ all -> 0x005d }
        r2 = r0.f3092c;	 Catch:{ all -> 0x005d }
        r4 = 0;
        r2[r1] = r4;	 Catch:{ all -> 0x005d }
        r1 = r1 + 1;
    L_0x0030:
        r2 = r6.f3105i;	 Catch:{ all -> 0x005d }
        if (r1 >= r2) goto L_0x0060;
    L_0x0034:
        r2 = r0.m4690a(r1);	 Catch:{ all -> 0x005d }
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
        r0 = r6.f3109m;	 Catch:{ all -> 0x005d }
        r0 = r0 + 1;
        r6.f3109m = r0;	 Catch:{ all -> 0x005d }
        r0 = r6.f3107k;	 Catch:{ all -> 0x005d }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x005d }
        r1.<init>();	 Catch:{ all -> 0x005d }
        r2 = "REMOVE ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x005d }
        r1 = r1.append(r7);	 Catch:{ all -> 0x005d }
        r2 = 10;
        r1 = r1.append(r2);	 Catch:{ all -> 0x005d }
        r1 = r1.toString();	 Catch:{ all -> 0x005d }
        r0.append(r1);	 Catch:{ all -> 0x005d }
        r0 = r6.f3108l;	 Catch:{ all -> 0x005d }
        r0.remove(r7);	 Catch:{ all -> 0x005d }
        r0 = r6.m4712h();	 Catch:{ all -> 0x005d }
        if (r0 == 0) goto L_0x0096;
    L_0x008f:
        r0 = r6.f3098b;	 Catch:{ all -> 0x005d }
        r1 = r6.f3112p;	 Catch:{ all -> 0x005d }
        r0.submit(r1);	 Catch:{ all -> 0x005d }
    L_0x0096:
        r0 = 1;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.core.bk.c(java.lang.String):boolean");
    }

    public synchronized void close() {
        if (this.f3107k != null) {
            Iterator it = new ArrayList(this.f3108l.values()).iterator();
            while (it.hasNext()) {
                C0466c c0466c = (C0466c) it.next();
                if (c0466c.f3094e != null) {
                    c0466c.f3094e.m4677b();
                }
            }
            m4714j();
            this.f3107k.close();
            this.f3107k = null;
        }
    }
}
