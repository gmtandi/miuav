package com.p016a;

import com.tencent.connect.common.Constants;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

/* renamed from: com.a.az */
public final class az implements Closeable {
    static final Pattern f587a;
    private static final OutputStream f588q;
    final ThreadPoolExecutor f589b;
    private final File f590c;
    private final File f591d;
    private final File f592e;
    private final File f593f;
    private final int f594g;
    private long f595h;
    private final int f596i;
    private long f597j;
    private Writer f598k;
    private final LinkedHashMap<String, bd> f599l;
    private int f600m;
    private bg f601n;
    private long f602o;
    private final Callable<Void> f603p;

    static {
        f587a = Pattern.compile("[a-z0-9_-]{1,120}");
        f588q = new bf();
    }

    private az(File file, int i, int i2, long j) {
        this.f597j = 0;
        this.f599l = new LinkedHashMap(0, 0.75f, true);
        this.f602o = 0;
        this.f589b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.f603p = new be(this);
        this.f590c = file;
        this.f594g = i;
        this.f591d = new File(file, "journal");
        this.f592e = new File(file, "journal.tmp");
        this.f593f = new File(file, "journal.bkp");
        this.f596i = i2;
        this.f595h = j;
    }

    public static az m1113a(File file, int i, int i2, long j) {
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
                    az.m1119a(file2, file3, false);
                }
            }
            az azVar = new az(file, i, i2, j);
            if (azVar.f591d.exists()) {
                try {
                    azVar.m1126e();
                    azVar.m1129f();
                    azVar.f598k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(azVar.f591d, true), bj.f629a));
                    return azVar;
                } catch (IOException e) {
                    azVar.m1139c();
                }
            }
            file.mkdirs();
            azVar = new az(file, i, i2, j);
            azVar.m1130g();
            return azVar;
        }
    }

    private synchronized ba m1114a(String str, long j) {
        ba baVar;
        m1132i();
        m1127e(str);
        bd bdVar = (bd) this.f599l.get(str);
        if (j == -1 || (bdVar != null && bdVar.f621f == j)) {
            bd bdVar2;
            if (bdVar == null) {
                bdVar = new bd(str, null);
                this.f599l.put(str, bdVar);
                bdVar2 = bdVar;
            } else if (bdVar.f620e != null) {
                baVar = null;
            } else {
                bdVar2 = bdVar;
            }
            baVar = new ba(bdVar2, null);
            bdVar2.f620e = baVar;
            this.f598k.write("DIRTY " + str + '\n');
            this.f598k.flush();
        } else {
            baVar = null;
        }
        return baVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m1117a(com.p016a.ba r11, boolean r12) {
        /*
        r10 = this;
        r0 = 0;
        monitor-enter(r10);
        r2 = r11.f606b;	 Catch:{ all -> 0x0012 }
        r1 = r2.f620e;	 Catch:{ all -> 0x0012 }
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
        r1 = r2.f619d;	 Catch:{ all -> 0x0012 }
        if (r1 != 0) goto L_0x0058;
    L_0x001d:
        r1 = r0;
    L_0x001e:
        r3 = r10.f596i;	 Catch:{ all -> 0x0012 }
        if (r1 >= r3) goto L_0x0058;
    L_0x0022:
        r3 = r11.f607c;	 Catch:{ all -> 0x0012 }
        r3 = r3[r1];	 Catch:{ all -> 0x0012 }
        if (r3 != 0) goto L_0x0046;
    L_0x002a:
        r11.m1146b();	 Catch:{ all -> 0x0012 }
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
        r3 = r2.m1161b(r1);	 Catch:{ all -> 0x0012 }
        r3 = r3.exists();	 Catch:{ all -> 0x0012 }
        if (r3 != 0) goto L_0x0055;
    L_0x0050:
        r11.m1146b();	 Catch:{ all -> 0x0012 }
    L_0x0053:
        monitor-exit(r10);
        return;
    L_0x0055:
        r1 = r1 + 1;
        goto L_0x001e;
    L_0x0058:
        r1 = r10.f596i;	 Catch:{ all -> 0x0012 }
        if (r0 >= r1) goto L_0x008d;
    L_0x005c:
        r1 = r2.m1161b(r0);	 Catch:{ all -> 0x0012 }
        if (r12 == 0) goto L_0x0089;
    L_0x0062:
        r3 = r1.exists();	 Catch:{ all -> 0x0012 }
        if (r3 == 0) goto L_0x0086;
    L_0x0068:
        r3 = r2.m1159a(r0);	 Catch:{ all -> 0x0012 }
        r1.renameTo(r3);	 Catch:{ all -> 0x0012 }
        r1 = r2.f618c;	 Catch:{ all -> 0x0012 }
        r4 = r1[r0];	 Catch:{ all -> 0x0012 }
        r6 = r3.length();	 Catch:{ all -> 0x0012 }
        r1 = r2.f618c;	 Catch:{ all -> 0x0012 }
        r1[r0] = r6;	 Catch:{ all -> 0x0012 }
        r8 = r10.f597j;	 Catch:{ all -> 0x0012 }
        r4 = r8 - r4;
        r4 = r4 + r6;
        r10.f597j = r4;	 Catch:{ all -> 0x0012 }
    L_0x0086:
        r0 = r0 + 1;
        goto L_0x0058;
    L_0x0089:
        com.p016a.az.m1118a(r1);	 Catch:{ all -> 0x0012 }
        goto L_0x0086;
    L_0x008d:
        r0 = r10.f600m;	 Catch:{ all -> 0x0012 }
        r0 = r0 + 1;
        r10.f600m = r0;	 Catch:{ all -> 0x0012 }
        r0 = 0;
        r2.f620e = r0;	 Catch:{ all -> 0x0012 }
        r0 = r2.f619d;	 Catch:{ all -> 0x0012 }
        r0 = r0 | r12;
        if (r0 == 0) goto L_0x00f4;
    L_0x009e:
        r0 = 1;
        r2.f619d = r0;	 Catch:{ all -> 0x0012 }
        r0 = r10.f598k;	 Catch:{ all -> 0x0012 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0012 }
        r1.<init>();	 Catch:{ all -> 0x0012 }
        r3 = "CLEAN ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r3 = r2.f617b;	 Catch:{ all -> 0x0012 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r3 = r2.m1160a();	 Catch:{ all -> 0x0012 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r3 = 10;
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r1 = r1.toString();	 Catch:{ all -> 0x0012 }
        r0.write(r1);	 Catch:{ all -> 0x0012 }
        if (r12 == 0) goto L_0x00d8;
    L_0x00ce:
        r0 = r10.f602o;	 Catch:{ all -> 0x0012 }
        r4 = 1;
        r4 = r4 + r0;
        r10.f602o = r4;	 Catch:{ all -> 0x0012 }
        r2.f621f = r0;	 Catch:{ all -> 0x0012 }
    L_0x00d8:
        r0 = r10.f598k;	 Catch:{ all -> 0x0012 }
        r0.flush();	 Catch:{ all -> 0x0012 }
        r0 = r10.f597j;	 Catch:{ all -> 0x0012 }
        r2 = r10.f595h;	 Catch:{ all -> 0x0012 }
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 > 0) goto L_0x00eb;
    L_0x00e5:
        r0 = r10.m1131h();	 Catch:{ all -> 0x0012 }
        if (r0 == 0) goto L_0x0053;
    L_0x00eb:
        r0 = r10.f589b;	 Catch:{ all -> 0x0012 }
        r1 = r10.f603p;	 Catch:{ all -> 0x0012 }
        r0.submit(r1);	 Catch:{ all -> 0x0012 }
        goto L_0x0053;
    L_0x00f4:
        r0 = r10.f599l;	 Catch:{ all -> 0x0012 }
        r1 = r2.f617b;	 Catch:{ all -> 0x0012 }
        r0.remove(r1);	 Catch:{ all -> 0x0012 }
        r0 = r10.f598k;	 Catch:{ all -> 0x0012 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0012 }
        r1.<init>();	 Catch:{ all -> 0x0012 }
        r3 = "REMOVE ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r2 = r2.f617b;	 Catch:{ all -> 0x0012 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0012 }
        r2 = 10;
        r1 = r1.append(r2);	 Catch:{ all -> 0x0012 }
        r1 = r1.toString();	 Catch:{ all -> 0x0012 }
        r0.write(r1);	 Catch:{ all -> 0x0012 }
        goto L_0x00d8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.az.a(com.a.ba, boolean):void");
    }

    private static void m1118a(File file) {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void m1119a(File file, File file2, boolean z) {
        if (z) {
            az.m1118a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    private void m1124d(String str) {
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
                this.f599l.remove(substring);
                return;
            }
            str2 = substring;
        } else {
            str2 = str.substring(i, indexOf2);
        }
        bd bdVar = (bd) this.f599l.get(str2);
        if (bdVar == null) {
            bdVar = new bd(str2, null);
            this.f599l.put(str2, bdVar);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            bdVar.f619d = true;
            bdVar.f620e = null;
            bdVar.m1152a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            bdVar.f620e = new ba(bdVar, null);
        } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void m1126e() {
        int i;
        Closeable bhVar = new bh(new FileInputStream(this.f591d), bj.f629a);
        try {
            String a = bhVar.m1166a();
            String a2 = bhVar.m1166a();
            String a3 = bhVar.m1166a();
            String a4 = bhVar.m1166a();
            String a5 = bhVar.m1166a();
            if ("libcore.io.DiskLruCache".equals(a) && Constants.VIA_TO_TYPE_QQ_GROUP.equals(a2) && Integer.toString(this.f594g).equals(a3) && Integer.toString(this.f596i).equals(a4) && C2915a.f14760f.equals(a5)) {
                i = 0;
                while (true) {
                    m1124d(bhVar.m1166a());
                    i++;
                }
            } else {
                throw new IOException("unexpected journal header: [" + a + ", " + a2 + ", " + a4 + ", " + a5 + "]");
            }
        } catch (EOFException e) {
            this.f600m = i - this.f599l.size();
            bj.m1167a(bhVar);
        } catch (Throwable th) {
            bj.m1167a(bhVar);
        }
    }

    private void m1127e(String str) {
        if (!f587a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }

    private void m1129f() {
        az.m1118a(this.f592e);
        Iterator it = this.f599l.values().iterator();
        while (it.hasNext()) {
            bd bdVar = (bd) it.next();
            int i;
            if (bdVar.f620e == null) {
                for (i = 0; i < this.f596i; i++) {
                    this.f597j += bdVar.f618c[i];
                }
            } else {
                bdVar.f620e = null;
                for (i = 0; i < this.f596i; i++) {
                    az.m1118a(bdVar.m1159a(i));
                    az.m1118a(bdVar.m1161b(i));
                }
                it.remove();
            }
        }
    }

    private synchronized void m1130g() {
        if (this.f598k != null) {
            this.f598k.close();
        }
        Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f592e), bj.f629a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write(Constants.VIA_TO_TYPE_QQ_GROUP);
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f594g));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f596i));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (bd bdVar : this.f599l.values()) {
                if (bdVar.f620e != null) {
                    bufferedWriter.write("DIRTY " + bdVar.f617b + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + bdVar.f617b + bdVar.m1160a() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.f591d.exists()) {
                az.m1119a(this.f591d, this.f593f, true);
            }
            az.m1119a(this.f592e, this.f591d, false);
            this.f593f.delete();
            this.f598k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f591d, true), bj.f629a));
        } catch (Throwable th) {
            bufferedWriter.close();
        }
    }

    private boolean m1131h() {
        return this.f600m >= BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN && this.f600m >= this.f599l.size();
    }

    private void m1132i() {
        if (this.f598k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    private void m1133j() {
        while (this.f597j > this.f595h) {
            String str = (String) ((Entry) this.f599l.entrySet().iterator().next()).getKey();
            m1140c(str);
            if (this.f601n != null) {
                this.f601n.m1163a(str);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.p016a.bc m1134a(java.lang.String r10) {
        /*
        r9 = this;
        r2 = 0;
        r1 = 0;
        monitor-enter(r9);
        r9.m1132i();	 Catch:{ all -> 0x0086 }
        r9.m1127e(r10);	 Catch:{ all -> 0x0086 }
        r0 = r9.f599l;	 Catch:{ all -> 0x0086 }
        r0 = r0.get(r10);	 Catch:{ all -> 0x0086 }
        r0 = (com.p016a.bd) r0;	 Catch:{ all -> 0x0086 }
        if (r0 != 0) goto L_0x0015;
    L_0x0013:
        monitor-exit(r9);
        return r1;
    L_0x0015:
        r3 = r0.f619d;	 Catch:{ all -> 0x0086 }
        if (r3 == 0) goto L_0x0013;
    L_0x001b:
        r3 = r9.f596i;	 Catch:{ all -> 0x0086 }
        r6 = new java.io.InputStream[r3];	 Catch:{ all -> 0x0086 }
        r3 = r2;
    L_0x0020:
        r4 = r9.f596i;	 Catch:{ FileNotFoundException -> 0x0032 }
        if (r3 >= r4) goto L_0x0044;
    L_0x0024:
        r4 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0032 }
        r5 = r0.m1159a(r3);	 Catch:{ FileNotFoundException -> 0x0032 }
        r4.<init>(r5);	 Catch:{ FileNotFoundException -> 0x0032 }
        r6[r3] = r4;	 Catch:{ FileNotFoundException -> 0x0032 }
        r3 = r3 + 1;
        goto L_0x0020;
    L_0x0032:
        r0 = move-exception;
        r0 = r2;
    L_0x0034:
        r2 = r9.f596i;	 Catch:{ all -> 0x0086 }
        if (r0 >= r2) goto L_0x0013;
    L_0x0038:
        r2 = r6[r0];	 Catch:{ all -> 0x0086 }
        if (r2 == 0) goto L_0x0013;
    L_0x003c:
        r2 = r6[r0];	 Catch:{ all -> 0x0086 }
        com.p016a.bj.m1167a(r2);	 Catch:{ all -> 0x0086 }
        r0 = r0 + 1;
        goto L_0x0034;
    L_0x0044:
        r1 = r9.f600m;	 Catch:{ all -> 0x0086 }
        r1 = r1 + 1;
        r9.f600m = r1;	 Catch:{ all -> 0x0086 }
        r1 = r9.f598k;	 Catch:{ all -> 0x0086 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0086 }
        r2.<init>();	 Catch:{ all -> 0x0086 }
        r3 = "READ ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0086 }
        r2 = r2.append(r10);	 Catch:{ all -> 0x0086 }
        r3 = 10;
        r2 = r2.append(r3);	 Catch:{ all -> 0x0086 }
        r2 = r2.toString();	 Catch:{ all -> 0x0086 }
        r1.append(r2);	 Catch:{ all -> 0x0086 }
        r1 = r9.m1131h();	 Catch:{ all -> 0x0086 }
        if (r1 == 0) goto L_0x0075;
    L_0x006e:
        r1 = r9.f589b;	 Catch:{ all -> 0x0086 }
        r2 = r9.f603p;	 Catch:{ all -> 0x0086 }
        r1.submit(r2);	 Catch:{ all -> 0x0086 }
    L_0x0075:
        r1 = new com.a.bc;	 Catch:{ all -> 0x0086 }
        r4 = r0.f621f;	 Catch:{ all -> 0x0086 }
        r7 = r0.f618c;	 Catch:{ all -> 0x0086 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.a.az.a(java.lang.String):com.a.bc");
    }

    public void m1135a(bg bgVar) {
        this.f601n = bgVar;
    }

    public synchronized boolean m1136a() {
        return this.f598k == null;
    }

    public ba m1137b(String str) {
        return m1114a(str, -1);
    }

    public synchronized void m1138b() {
        m1132i();
        m1133j();
        this.f598k.flush();
    }

    public void m1139c() {
        close();
        bj.m1168a(this.f590c);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean m1140c(java.lang.String r7) {
        /*
        r6 = this;
        r1 = 0;
        monitor-enter(r6);
        r6.m1132i();	 Catch:{ all -> 0x005d }
        r6.m1127e(r7);	 Catch:{ all -> 0x005d }
        r0 = r6.f599l;	 Catch:{ all -> 0x005d }
        r0 = r0.get(r7);	 Catch:{ all -> 0x005d }
        r0 = (com.p016a.bd) r0;	 Catch:{ all -> 0x005d }
        if (r0 == 0) goto L_0x0018;
    L_0x0012:
        r2 = r0.f620e;	 Catch:{ all -> 0x005d }
        if (r2 == 0) goto L_0x0030;
    L_0x0018:
        r0 = r1;
    L_0x0019:
        monitor-exit(r6);
        return r0;
    L_0x001b:
        r2 = r6.f597j;	 Catch:{ all -> 0x005d }
        r4 = r0.f618c;	 Catch:{ all -> 0x005d }
        r4 = r4[r1];	 Catch:{ all -> 0x005d }
        r2 = r2 - r4;
        r6.f597j = r2;	 Catch:{ all -> 0x005d }
        r2 = r0.f618c;	 Catch:{ all -> 0x005d }
        r4 = 0;
        r2[r1] = r4;	 Catch:{ all -> 0x005d }
        r1 = r1 + 1;
    L_0x0030:
        r2 = r6.f596i;	 Catch:{ all -> 0x005d }
        if (r1 >= r2) goto L_0x0060;
    L_0x0034:
        r2 = r0.m1159a(r1);	 Catch:{ all -> 0x005d }
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
        r0 = r6.f600m;	 Catch:{ all -> 0x005d }
        r0 = r0 + 1;
        r6.f600m = r0;	 Catch:{ all -> 0x005d }
        r0 = r6.f598k;	 Catch:{ all -> 0x005d }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x005d }
        r1.<init>();	 Catch:{ all -> 0x005d }
        r2 = "REMOVE ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x005d }
        r1 = r1.append(r7);	 Catch:{ all -> 0x005d }
        r2 = 10;
        r1 = r1.append(r2);	 Catch:{ all -> 0x005d }
        r1 = r1.toString();	 Catch:{ all -> 0x005d }
        r0.append(r1);	 Catch:{ all -> 0x005d }
        r0 = r6.f599l;	 Catch:{ all -> 0x005d }
        r0.remove(r7);	 Catch:{ all -> 0x005d }
        r0 = r6.m1131h();	 Catch:{ all -> 0x005d }
        if (r0 == 0) goto L_0x0096;
    L_0x008f:
        r0 = r6.f589b;	 Catch:{ all -> 0x005d }
        r1 = r6.f603p;	 Catch:{ all -> 0x005d }
        r0.submit(r1);	 Catch:{ all -> 0x005d }
    L_0x0096:
        r0 = 1;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.az.c(java.lang.String):boolean");
    }

    public synchronized void close() {
        if (this.f598k != null) {
            Iterator it = new ArrayList(this.f599l.values()).iterator();
            while (it.hasNext()) {
                bd bdVar = (bd) it.next();
                if (bdVar.f620e != null) {
                    bdVar.f620e.m1146b();
                }
            }
            m1133j();
            this.f598k.close();
            this.f598k = null;
        }
    }
}
