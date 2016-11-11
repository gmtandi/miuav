package com.p054c.p055a.p056a.p057a.p058a.p059a;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
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

/* renamed from: com.c.a.a.a.a.a.a */
final class C0857a implements Closeable {
    private static final OutputStream f4632B;
    static final String f4633a = "journal";
    static final String f4634b = "journal.tmp";
    static final String f4635c = "journal.bkp";
    static final String f4636d = "libcore.io.DiskLruCache";
    static final String f4637e = "1";
    static final long f4638f = -1;
    static final Pattern f4639g;
    private static final String f4640i = "CLEAN";
    private static final String f4641j = "DIRTY";
    private static final String f4642k = "REMOVE";
    private static final String f4643l = "READ";
    private final Callable<Void> f4644A;
    final ThreadPoolExecutor f4645h;
    private final File f4646m;
    private final File f4647n;
    private final File f4648o;
    private final File f4649p;
    private final int f4650q;
    private long f4651r;
    private int f4652s;
    private final int f4653t;
    private long f4654u;
    private int f4655v;
    private Writer f4656w;
    private final LinkedHashMap<String, C0862f> f4657x;
    private int f4658y;
    private long f4659z;

    static {
        f4639g = Pattern.compile("[a-z0-9_-]{1,64}");
        f4632B = new C0859c();
    }

    private C0857a(File file, int i, int i2, long j, int i3) {
        this.f4654u = 0;
        this.f4655v = 0;
        this.f4657x = new LinkedHashMap(0, 0.75f, true);
        this.f4659z = 0;
        this.f4645h = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.f4644A = new C0858b(this);
        this.f4646m = file;
        this.f4650q = i;
        this.f4647n = new File(file, f4633a);
        this.f4648o = new File(file, f4634b);
        this.f4649p = new File(file, f4635c);
        this.f4653t = i2;
        this.f4651r = j;
        this.f4652s = i3;
    }

    public static C0857a m6959a(File file, int i, int i2, long j, int i3) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i3 <= 0) {
            throw new IllegalArgumentException("maxFileCount <= 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            File file2 = new File(file, f4635c);
            if (file2.exists()) {
                File file3 = new File(file, f4633a);
                if (file3.exists()) {
                    file2.delete();
                } else {
                    C0857a.m6967a(file2, file3, false);
                }
            }
            C0857a c0857a = new C0857a(file, i, i2, j, i3);
            if (c0857a.f4647n.exists()) {
                try {
                    c0857a.m6978j();
                    c0857a.m6979k();
                    c0857a.f4656w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(c0857a.f4647n, true), C0868k.f4698a));
                    return c0857a;
                } catch (IOException e) {
                    System.out.println("DiskLruCache " + file + " is corrupt: " + e.getMessage() + ", removing");
                    c0857a.m6996h();
                }
            }
            file.mkdirs();
            c0857a = new C0857a(file, i, i2, j, i3);
            c0857a.m6980l();
            return c0857a;
        }
    }

    private synchronized C0860d m6961a(String str, long j) {
        C0860d c0860d;
        m6982n();
        m6974e(str);
        C0862f c0862f = (C0862f) this.f4657x.get(str);
        if (j == f4638f || (c0862f != null && c0862f.f4672f == j)) {
            C0862f c0862f2;
            if (c0862f == null) {
                c0862f = new C0862f(str, null);
                this.f4657x.put(str, c0862f);
                c0862f2 = c0862f;
            } else if (c0862f.f4671e != null) {
                c0860d = null;
            } else {
                c0862f2 = c0862f;
            }
            c0860d = new C0860d(c0862f2, null);
            c0862f2.f4671e = c0860d;
            this.f4656w.write("DIRTY " + str + '\n');
            this.f4656w.flush();
        } else {
            c0860d = null;
        }
        return c0860d;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m6965a(com.p054c.p055a.p056a.p057a.p058a.p059a.C0860d r11, boolean r12) {
        /*
        r10 = this;
        r0 = 0;
        monitor-enter(r10);
        r2 = r11.f4662b;	 Catch:{ all -> 0x0012 }
        r1 = r2.f4671e;	 Catch:{ all -> 0x0012 }
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
        r1 = r2.f4670d;	 Catch:{ all -> 0x0012 }
        if (r1 != 0) goto L_0x0058;
    L_0x001d:
        r1 = r0;
    L_0x001e:
        r3 = r10.f4653t;	 Catch:{ all -> 0x0012 }
        if (r1 >= r3) goto L_0x0058;
    L_0x0022:
        r3 = r11.f4663c;	 Catch:{ all -> 0x0012 }
        r3 = r3[r1];	 Catch:{ all -> 0x0012 }
        if (r3 != 0) goto L_0x0046;
    L_0x002a:
        r11.m7005b();	 Catch:{ all -> 0x0012 }
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
        r3 = r2.m7021b(r1);	 Catch:{ all -> 0x0012 }
        r3 = r3.exists();	 Catch:{ all -> 0x0012 }
        if (r3 != 0) goto L_0x0055;
    L_0x0050:
        r11.m7005b();	 Catch:{ all -> 0x0012 }
    L_0x0053:
        monitor-exit(r10);
        return;
    L_0x0055:
        r1 = r1 + 1;
        goto L_0x001e;
    L_0x0058:
        r1 = r10.f4653t;	 Catch:{ all -> 0x0012 }
        if (r0 >= r1) goto L_0x0093;
    L_0x005c:
        r1 = r2.m7021b(r0);	 Catch:{ all -> 0x0012 }
        if (r12 == 0) goto L_0x008f;
    L_0x0062:
        r3 = r1.exists();	 Catch:{ all -> 0x0012 }
        if (r3 == 0) goto L_0x008c;
    L_0x0068:
        r3 = r2.m7019a(r0);	 Catch:{ all -> 0x0012 }
        r1.renameTo(r3);	 Catch:{ all -> 0x0012 }
        r1 = r2.f4669c;	 Catch:{ all -> 0x0012 }
        r4 = r1[r0];	 Catch:{ all -> 0x0012 }
        r6 = r3.length();	 Catch:{ all -> 0x0012 }
        r1 = r2.f4669c;	 Catch:{ all -> 0x0012 }
        r1[r0] = r6;	 Catch:{ all -> 0x0012 }
        r8 = r10.f4654u;	 Catch:{ all -> 0x0012 }
        r4 = r8 - r4;
        r4 = r4 + r6;
        r10.f4654u = r4;	 Catch:{ all -> 0x0012 }
        r1 = r10.f4655v;	 Catch:{ all -> 0x0012 }
        r1 = r1 + 1;
        r10.f4655v = r1;	 Catch:{ all -> 0x0012 }
    L_0x008c:
        r0 = r0 + 1;
        goto L_0x0058;
    L_0x008f:
        com.p054c.p055a.p056a.p057a.p058a.p059a.C0857a.m6966a(r1);	 Catch:{ all -> 0x0012 }
        goto L_0x008c;
    L_0x0093:
        r0 = r10.f4658y;	 Catch:{ all -> 0x0012 }
        r0 = r0 + 1;
        r10.f4658y = r0;	 Catch:{ all -> 0x0012 }
        r0 = 0;
        r2.f4671e = r0;	 Catch:{ all -> 0x0012 }
        r0 = r2.f4670d;	 Catch:{ all -> 0x0012 }
        r0 = r0 | r12;
        if (r0 == 0) goto L_0x0100;
    L_0x00a4:
        r0 = 1;
        r2.f4670d = r0;	 Catch:{ all -> 0x0012 }
        r0 = r10.f4656w;	 Catch:{ all -> 0x0012 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0012 }
        r1.<init>();	 Catch:{ all -> 0x0012 }
        r3 = "CLEAN ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r3 = r2.f4668b;	 Catch:{ all -> 0x0012 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r3 = r2.m7020a();	 Catch:{ all -> 0x0012 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r3 = 10;
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r1 = r1.toString();	 Catch:{ all -> 0x0012 }
        r0.write(r1);	 Catch:{ all -> 0x0012 }
        if (r12 == 0) goto L_0x00de;
    L_0x00d4:
        r0 = r10.f4659z;	 Catch:{ all -> 0x0012 }
        r4 = 1;
        r4 = r4 + r0;
        r10.f4659z = r4;	 Catch:{ all -> 0x0012 }
        r2.f4672f = r0;	 Catch:{ all -> 0x0012 }
    L_0x00de:
        r0 = r10.f4656w;	 Catch:{ all -> 0x0012 }
        r0.flush();	 Catch:{ all -> 0x0012 }
        r0 = r10.f4654u;	 Catch:{ all -> 0x0012 }
        r2 = r10.f4651r;	 Catch:{ all -> 0x0012 }
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 > 0) goto L_0x00f7;
    L_0x00eb:
        r0 = r10.f4655v;	 Catch:{ all -> 0x0012 }
        r1 = r10.f4652s;	 Catch:{ all -> 0x0012 }
        if (r0 > r1) goto L_0x00f7;
    L_0x00f1:
        r0 = r10.m6981m();	 Catch:{ all -> 0x0012 }
        if (r0 == 0) goto L_0x0053;
    L_0x00f7:
        r0 = r10.f4645h;	 Catch:{ all -> 0x0012 }
        r1 = r10.f4644A;	 Catch:{ all -> 0x0012 }
        r0.submit(r1);	 Catch:{ all -> 0x0012 }
        goto L_0x0053;
    L_0x0100:
        r0 = r10.f4657x;	 Catch:{ all -> 0x0012 }
        r1 = r2.f4668b;	 Catch:{ all -> 0x0012 }
        r0.remove(r1);	 Catch:{ all -> 0x0012 }
        r0 = r10.f4656w;	 Catch:{ all -> 0x0012 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0012 }
        r1.<init>();	 Catch:{ all -> 0x0012 }
        r3 = "REMOVE ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r2 = r2.f4668b;	 Catch:{ all -> 0x0012 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0012 }
        r2 = 10;
        r1 = r1.append(r2);	 Catch:{ all -> 0x0012 }
        r1 = r1.toString();	 Catch:{ all -> 0x0012 }
        r0.write(r1);	 Catch:{ all -> 0x0012 }
        goto L_0x00de;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.c.a.a.a.a.a.a.a(com.c.a.a.a.a.a.d, boolean):void");
    }

    private static void m6966a(File file) {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void m6967a(File file, File file2, boolean z) {
        if (z) {
            C0857a.m6966a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    private static String m6968b(InputStream inputStream) {
        return C0868k.m7049a(new InputStreamReader(inputStream, C0868k.f4699b));
    }

    private void m6971d(String str) {
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        String str2;
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            String substring = str.substring(i);
            if (indexOf == f4642k.length() && str.startsWith(f4642k)) {
                this.f4657x.remove(substring);
                return;
            }
            str2 = substring;
        } else {
            str2 = str.substring(i, indexOf2);
        }
        C0862f c0862f = (C0862f) this.f4657x.get(str2);
        if (c0862f == null) {
            c0862f = new C0862f(str2, null);
            this.f4657x.put(str2, c0862f);
        }
        if (indexOf2 != -1 && indexOf == f4640i.length() && str.startsWith(f4640i)) {
            String[] split = str.substring(indexOf2 + 1).split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            c0862f.f4670d = true;
            c0862f.f4671e = null;
            c0862f.m7012a(split);
        } else if (indexOf2 == -1 && indexOf == f4641j.length() && str.startsWith(f4641j)) {
            c0862f.f4671e = new C0860d(c0862f, null);
        } else if (indexOf2 != -1 || indexOf != f4643l.length() || !str.startsWith(f4643l)) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void m6974e(String str) {
        if (!f4639g.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,64}: \"" + str + "\"");
        }
    }

    private void m6978j() {
        Closeable c0866i = new C0866i(new FileInputStream(this.f4647n), C0868k.f4698a);
        int i;
        try {
            String a = c0866i.m7048a();
            String a2 = c0866i.m7048a();
            String a3 = c0866i.m7048a();
            String a4 = c0866i.m7048a();
            String a5 = c0866i.m7048a();
            if (f4636d.equals(a) && f4637e.equals(a2) && Integer.toString(this.f4650q).equals(a3) && Integer.toString(this.f4653t).equals(a4) && C2915a.f14760f.equals(a5)) {
                i = 0;
                while (true) {
                    m6971d(c0866i.m7048a());
                    i++;
                }
            } else {
                throw new IOException("unexpected journal header: [" + a + ", " + a2 + ", " + a4 + ", " + a5 + "]");
            }
        } catch (EOFException e) {
            this.f4658y = i - this.f4657x.size();
            C0868k.m7050a(c0866i);
        } catch (Throwable th) {
            C0868k.m7050a(c0866i);
        }
    }

    private void m6979k() {
        C0857a.m6966a(this.f4648o);
        Iterator it = this.f4657x.values().iterator();
        while (it.hasNext()) {
            C0862f c0862f = (C0862f) it.next();
            int i;
            if (c0862f.f4671e == null) {
                for (i = 0; i < this.f4653t; i++) {
                    this.f4654u += c0862f.f4669c[i];
                    this.f4655v++;
                }
            } else {
                c0862f.f4671e = null;
                for (i = 0; i < this.f4653t; i++) {
                    C0857a.m6966a(c0862f.m7019a(i));
                    C0857a.m6966a(c0862f.m7021b(i));
                }
                it.remove();
            }
        }
    }

    private synchronized void m6980l() {
        if (this.f4656w != null) {
            this.f4656w.close();
        }
        Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f4648o), C0868k.f4698a));
        try {
            bufferedWriter.write(f4636d);
            bufferedWriter.write("\n");
            bufferedWriter.write(f4637e);
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f4650q));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f4653t));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (C0862f c0862f : this.f4657x.values()) {
                if (c0862f.f4671e != null) {
                    bufferedWriter.write("DIRTY " + c0862f.f4668b + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + c0862f.f4668b + c0862f.m7020a() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.f4647n.exists()) {
                C0857a.m6967a(this.f4647n, this.f4649p, true);
            }
            C0857a.m6967a(this.f4648o, this.f4647n, false);
            this.f4649p.delete();
            this.f4656w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f4647n, true), C0868k.f4698a));
        } catch (Throwable th) {
            bufferedWriter.close();
        }
    }

    private boolean m6981m() {
        return this.f4658y >= BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN && this.f4658y >= this.f4657x.size();
    }

    private void m6982n() {
        if (this.f4656w == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    private void m6983o() {
        while (this.f4654u > this.f4651r) {
            m6991c((String) ((Entry) this.f4657x.entrySet().iterator().next()).getKey());
        }
    }

    private void m6984p() {
        while (this.f4655v > this.f4652s) {
            m6991c((String) ((Entry) this.f4657x.entrySet().iterator().next()).getKey());
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.p054c.p055a.p056a.p057a.p058a.p059a.C0863g m6985a(java.lang.String r11) {
        /*
        r10 = this;
        r2 = 0;
        r1 = 0;
        monitor-enter(r10);
        r10.m6982n();	 Catch:{ all -> 0x008c }
        r10.m6974e(r11);	 Catch:{ all -> 0x008c }
        r0 = r10.f4657x;	 Catch:{ all -> 0x008c }
        r0 = r0.get(r11);	 Catch:{ all -> 0x008c }
        r0 = (com.p054c.p055a.p056a.p057a.p058a.p059a.C0862f) r0;	 Catch:{ all -> 0x008c }
        if (r0 != 0) goto L_0x0015;
    L_0x0013:
        monitor-exit(r10);
        return r1;
    L_0x0015:
        r3 = r0.f4670d;	 Catch:{ all -> 0x008c }
        if (r3 == 0) goto L_0x0013;
    L_0x001b:
        r3 = r10.f4653t;	 Catch:{ all -> 0x008c }
        r6 = new java.io.File[r3];	 Catch:{ all -> 0x008c }
        r3 = r10.f4653t;	 Catch:{ all -> 0x008c }
        r7 = new java.io.InputStream[r3];	 Catch:{ all -> 0x008c }
        r3 = r2;
    L_0x0024:
        r4 = r10.f4653t;	 Catch:{ FileNotFoundException -> 0x0038 }
        if (r3 >= r4) goto L_0x004a;
    L_0x0028:
        r4 = r0.m7019a(r3);	 Catch:{ FileNotFoundException -> 0x0038 }
        r6[r3] = r4;	 Catch:{ FileNotFoundException -> 0x0038 }
        r5 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0038 }
        r5.<init>(r4);	 Catch:{ FileNotFoundException -> 0x0038 }
        r7[r3] = r5;	 Catch:{ FileNotFoundException -> 0x0038 }
        r3 = r3 + 1;
        goto L_0x0024;
    L_0x0038:
        r0 = move-exception;
        r0 = r2;
    L_0x003a:
        r2 = r10.f4653t;	 Catch:{ all -> 0x008c }
        if (r0 >= r2) goto L_0x0013;
    L_0x003e:
        r2 = r7[r0];	 Catch:{ all -> 0x008c }
        if (r2 == 0) goto L_0x0013;
    L_0x0042:
        r2 = r7[r0];	 Catch:{ all -> 0x008c }
        com.p054c.p055a.p056a.p057a.p058a.p059a.C0868k.m7050a(r2);	 Catch:{ all -> 0x008c }
        r0 = r0 + 1;
        goto L_0x003a;
    L_0x004a:
        r1 = r10.f4658y;	 Catch:{ all -> 0x008c }
        r1 = r1 + 1;
        r10.f4658y = r1;	 Catch:{ all -> 0x008c }
        r1 = r10.f4656w;	 Catch:{ all -> 0x008c }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x008c }
        r2.<init>();	 Catch:{ all -> 0x008c }
        r3 = "READ ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x008c }
        r2 = r2.append(r11);	 Catch:{ all -> 0x008c }
        r3 = 10;
        r2 = r2.append(r3);	 Catch:{ all -> 0x008c }
        r2 = r2.toString();	 Catch:{ all -> 0x008c }
        r1.append(r2);	 Catch:{ all -> 0x008c }
        r1 = r10.m6981m();	 Catch:{ all -> 0x008c }
        if (r1 == 0) goto L_0x007b;
    L_0x0074:
        r1 = r10.f4645h;	 Catch:{ all -> 0x008c }
        r2 = r10.f4644A;	 Catch:{ all -> 0x008c }
        r1.submit(r2);	 Catch:{ all -> 0x008c }
    L_0x007b:
        r1 = new com.c.a.a.a.a.a.g;	 Catch:{ all -> 0x008c }
        r4 = r0.f4672f;	 Catch:{ all -> 0x008c }
        r8 = r0.f4669c;	 Catch:{ all -> 0x008c }
        r9 = 0;
        r2 = r10;
        r3 = r11;
        r1.<init>(r3, r4, r6, r7, r8, r9);	 Catch:{ all -> 0x008c }
        goto L_0x0013;
    L_0x008c:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.c.a.a.a.a.a.a.a(java.lang.String):com.c.a.a.a.a.a.g");
    }

    public File m6986a() {
        return this.f4646m;
    }

    public synchronized void m6987a(long j) {
        this.f4651r = j;
        this.f4645h.submit(this.f4644A);
    }

    public synchronized long m6988b() {
        return this.f4651r;
    }

    public C0860d m6989b(String str) {
        return m6961a(str, (long) f4638f);
    }

    public synchronized int m6990c() {
        return this.f4652s;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean m6991c(java.lang.String r7) {
        /*
        r6 = this;
        r1 = 0;
        monitor-enter(r6);
        r6.m6982n();	 Catch:{ all -> 0x0063 }
        r6.m6974e(r7);	 Catch:{ all -> 0x0063 }
        r0 = r6.f4657x;	 Catch:{ all -> 0x0063 }
        r0 = r0.get(r7);	 Catch:{ all -> 0x0063 }
        r0 = (com.p054c.p055a.p056a.p057a.p058a.p059a.C0862f) r0;	 Catch:{ all -> 0x0063 }
        if (r0 == 0) goto L_0x0018;
    L_0x0012:
        r2 = r0.f4671e;	 Catch:{ all -> 0x0063 }
        if (r2 == 0) goto L_0x0036;
    L_0x0018:
        r0 = r1;
    L_0x0019:
        monitor-exit(r6);
        return r0;
    L_0x001b:
        r2 = r6.f4654u;	 Catch:{ all -> 0x0063 }
        r4 = r0.f4669c;	 Catch:{ all -> 0x0063 }
        r4 = r4[r1];	 Catch:{ all -> 0x0063 }
        r2 = r2 - r4;
        r6.f4654u = r2;	 Catch:{ all -> 0x0063 }
        r2 = r6.f4655v;	 Catch:{ all -> 0x0063 }
        r2 = r2 + -1;
        r6.f4655v = r2;	 Catch:{ all -> 0x0063 }
        r2 = r0.f4669c;	 Catch:{ all -> 0x0063 }
        r4 = 0;
        r2[r1] = r4;	 Catch:{ all -> 0x0063 }
        r1 = r1 + 1;
    L_0x0036:
        r2 = r6.f4653t;	 Catch:{ all -> 0x0063 }
        if (r1 >= r2) goto L_0x0066;
    L_0x003a:
        r2 = r0.m7019a(r1);	 Catch:{ all -> 0x0063 }
        r3 = r2.exists();	 Catch:{ all -> 0x0063 }
        if (r3 == 0) goto L_0x001b;
    L_0x0044:
        r3 = r2.delete();	 Catch:{ all -> 0x0063 }
        if (r3 != 0) goto L_0x001b;
    L_0x004a:
        r0 = new java.io.IOException;	 Catch:{ all -> 0x0063 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0063 }
        r1.<init>();	 Catch:{ all -> 0x0063 }
        r3 = "failed to delete ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x0063 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0063 }
        r1 = r1.toString();	 Catch:{ all -> 0x0063 }
        r0.<init>(r1);	 Catch:{ all -> 0x0063 }
        throw r0;	 Catch:{ all -> 0x0063 }
    L_0x0063:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
    L_0x0066:
        r0 = r6.f4658y;	 Catch:{ all -> 0x0063 }
        r0 = r0 + 1;
        r6.f4658y = r0;	 Catch:{ all -> 0x0063 }
        r0 = r6.f4656w;	 Catch:{ all -> 0x0063 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0063 }
        r1.<init>();	 Catch:{ all -> 0x0063 }
        r2 = "REMOVE ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0063 }
        r1 = r1.append(r7);	 Catch:{ all -> 0x0063 }
        r2 = 10;
        r1 = r1.append(r2);	 Catch:{ all -> 0x0063 }
        r1 = r1.toString();	 Catch:{ all -> 0x0063 }
        r0.append(r1);	 Catch:{ all -> 0x0063 }
        r0 = r6.f4657x;	 Catch:{ all -> 0x0063 }
        r0.remove(r7);	 Catch:{ all -> 0x0063 }
        r0 = r6.m6981m();	 Catch:{ all -> 0x0063 }
        if (r0 == 0) goto L_0x009c;
    L_0x0095:
        r0 = r6.f4645h;	 Catch:{ all -> 0x0063 }
        r1 = r6.f4644A;	 Catch:{ all -> 0x0063 }
        r0.submit(r1);	 Catch:{ all -> 0x0063 }
    L_0x009c:
        r0 = 1;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.c.a.a.a.a.a.a.c(java.lang.String):boolean");
    }

    public synchronized void close() {
        if (this.f4656w != null) {
            Iterator it = new ArrayList(this.f4657x.values()).iterator();
            while (it.hasNext()) {
                C0862f c0862f = (C0862f) it.next();
                if (c0862f.f4671e != null) {
                    c0862f.f4671e.m7005b();
                }
            }
            m6983o();
            m6984p();
            this.f4656w.close();
            this.f4656w = null;
        }
    }

    public synchronized long m6992d() {
        return this.f4654u;
    }

    public synchronized long m6993e() {
        return (long) this.f4655v;
    }

    public synchronized boolean m6994f() {
        return this.f4656w == null;
    }

    public synchronized void m6995g() {
        m6982n();
        m6983o();
        m6984p();
        this.f4656w.flush();
    }

    public void m6996h() {
        close();
        C0868k.m7051a(this.f4646m);
    }
}
