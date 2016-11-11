package com.p016a;

import com.baidu.tts.loopj.AsyncHttpClient;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.zip.GZIPInputStream;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.a.fa */
public final class fa {
    private RandomAccessFile f1173a;
    private ed f1174b;
    private File f1175c;

    protected fa(ed edVar) {
        this.f1175c = null;
        this.f1174b = edVar;
    }

    private static byte m1785a(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            byte[] bArr3 = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = gZIPInputStream.read(bArr3, 0, bArr3.length);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr3, 0, read);
            }
            bArr2 = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            gZIPInputStream.close();
            byteArrayInputStream.close();
        } catch (Exception e) {
        }
        return bArr2[0];
    }

    private static int m1786a(int i, int i2, int i3) {
        int i4 = ((i3 - 1) * AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS) + i;
        while (i4 >= i2) {
            i4 -= 1500;
        }
        return i4;
    }

    private int m1787a(BitSet bitSet) {
        for (int i = 0; i < bitSet.length(); i++) {
            if (bitSet.get(i)) {
                return this.f1174b.m1658a() + ((i * AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS) + 4);
            }
        }
        return 0;
    }

    private ArrayList m1788a(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        while (i <= i2) {
            try {
                this.f1173a.seek((long) i);
                int readInt = this.f1173a.readInt();
                this.f1173a.readLong();
                if (readInt <= 0 || readInt > AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS) {
                    return null;
                }
                byte[] bArr = new byte[readInt];
                this.f1173a.read(bArr);
                byte a = fa.m1785a(bArr);
                if (a != 3 && a != 4 && a != 41) {
                    return null;
                }
                arrayList.add(bArr);
                i += AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS;
            } catch (IOException e) {
            }
        }
        return arrayList;
    }

    private BitSet m1789b() {
        BitSet bitSet = null;
        byte[] bArr = new byte[this.f1174b.m1658a()];
        try {
            this.f1173a.read(bArr);
            bitSet = ed.m1651b(bArr);
        } catch (IOException e) {
        }
        return bitSet;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final int m1790a() {
        /*
        r4 = this;
        r0 = 0;
        monitor-enter(r4);
        r1 = r4.f1174b;	 Catch:{ all -> 0x0064 }
        r1 = r1.m1661b();	 Catch:{ all -> 0x0064 }
        r4.f1175c = r1;	 Catch:{ all -> 0x0064 }
        r1 = r4.f1175c;	 Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0076, NullPointerException -> 0x0083, all -> 0x0059 }
        if (r1 == 0) goto L_0x008e;
    L_0x000e:
        r1 = new java.io.RandomAccessFile;	 Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0076, NullPointerException -> 0x0083, all -> 0x0059 }
        r2 = r4.f1174b;	 Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0076, NullPointerException -> 0x0083, all -> 0x0059 }
        r2 = r2.m1661b();	 Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0076, NullPointerException -> 0x0083, all -> 0x0059 }
        r3 = "rw";
        r1.<init>(r2, r3);	 Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0076, NullPointerException -> 0x0083, all -> 0x0059 }
        r4.f1173a = r1;	 Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0076, NullPointerException -> 0x0083, all -> 0x0059 }
        r1 = r4.f1174b;	 Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0076, NullPointerException -> 0x0083, all -> 0x0059 }
        r1 = r1.m1658a();	 Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0076, NullPointerException -> 0x0083, all -> 0x0059 }
        r1 = new byte[r1];	 Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0076, NullPointerException -> 0x0083, all -> 0x0059 }
        r2 = com.p016a.eg.f1076c;	 Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0076, NullPointerException -> 0x0083, all -> 0x0059 }
        if (r2 == 0) goto L_0x003e;
    L_0x0029:
        r2 = r4.f1173a;	 Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0076, NullPointerException -> 0x0083, all -> 0x0059 }
        if (r2 == 0) goto L_0x003e;
    L_0x002d:
        r2 = r4.f1173a;	 Catch:{ IOException -> 0x003d, FileNotFoundException -> 0x0067, NullPointerException -> 0x0083, all -> 0x0059 }
        r2.close();	 Catch:{ IOException -> 0x003d, FileNotFoundException -> 0x0067, NullPointerException -> 0x0083, all -> 0x0059 }
        r1 = r4.f1173a;	 Catch:{ all -> 0x0064 }
        if (r1 == 0) goto L_0x003b;
    L_0x0036:
        r1 = r4.f1173a;	 Catch:{ IOException -> 0x0098 }
        r1.close();	 Catch:{ IOException -> 0x0098 }
    L_0x003b:
        monitor-exit(r4);	 Catch:{ all -> 0x0064 }
    L_0x003c:
        return r0;
    L_0x003d:
        r2 = move-exception;
    L_0x003e:
        r2 = r4.f1173a;	 Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0076, NullPointerException -> 0x0083, all -> 0x0059 }
        r2.read(r1);	 Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0076, NullPointerException -> 0x0083, all -> 0x0059 }
        r2 = com.p016a.ed.m1651b(r1);	 Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0076, NullPointerException -> 0x0083, all -> 0x0059 }
        r1 = r0;
    L_0x0048:
        r3 = r2.size();	 Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0076, NullPointerException -> 0x0083, all -> 0x0059 }
        if (r1 >= r3) goto L_0x008e;
    L_0x004e:
        r3 = r2.get(r1);	 Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0076, NullPointerException -> 0x0083, all -> 0x0059 }
        if (r3 == 0) goto L_0x0056;
    L_0x0054:
        r0 = r0 + 1;
    L_0x0056:
        r1 = r1 + 1;
        goto L_0x0048;
    L_0x0059:
        r0 = move-exception;
        r1 = r4.f1173a;	 Catch:{ all -> 0x0064 }
        if (r1 == 0) goto L_0x0063;
    L_0x005e:
        r1 = r4.f1173a;	 Catch:{ IOException -> 0x009a }
        r1.close();	 Catch:{ IOException -> 0x009a }
    L_0x0063:
        throw r0;	 Catch:{ all -> 0x0064 }
    L_0x0064:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
    L_0x0067:
        r1 = move-exception;
        r1 = r4.f1173a;	 Catch:{ all -> 0x0064 }
        if (r1 == 0) goto L_0x0071;
    L_0x006c:
        r1 = r4.f1173a;	 Catch:{ IOException -> 0x0081 }
        r1.close();	 Catch:{ IOException -> 0x0081 }
    L_0x0071:
        r1 = 0;
        r4.f1175c = r1;	 Catch:{ all -> 0x0064 }
        monitor-exit(r4);	 Catch:{ all -> 0x0064 }
        goto L_0x003c;
    L_0x0076:
        r1 = move-exception;
        r1 = r4.f1173a;	 Catch:{ all -> 0x0064 }
        if (r1 == 0) goto L_0x0071;
    L_0x007b:
        r1 = r4.f1173a;	 Catch:{ IOException -> 0x0081 }
        r1.close();	 Catch:{ IOException -> 0x0081 }
        goto L_0x0071;
    L_0x0081:
        r1 = move-exception;
        goto L_0x0071;
    L_0x0083:
        r1 = move-exception;
        r1 = r4.f1173a;	 Catch:{ all -> 0x0064 }
        if (r1 == 0) goto L_0x0071;
    L_0x0088:
        r1 = r4.f1173a;	 Catch:{ IOException -> 0x0081 }
        r1.close();	 Catch:{ IOException -> 0x0081 }
        goto L_0x0071;
    L_0x008e:
        r1 = r4.f1173a;	 Catch:{ all -> 0x0064 }
        if (r1 == 0) goto L_0x0071;
    L_0x0092:
        r1 = r4.f1173a;	 Catch:{ IOException -> 0x0081 }
        r1.close();	 Catch:{ IOException -> 0x0081 }
        goto L_0x0071;
    L_0x0098:
        r1 = move-exception;
        goto L_0x003b;
    L_0x009a:
        r1 = move-exception;
        goto L_0x0063;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.fa.a():int");
    }

    protected final synchronized ec m1791a(int i) {
        ec ecVar = null;
        synchronized (this) {
            if (this.f1174b != null) {
                synchronized (this) {
                    this.f1175c = this.f1174b.m1661b();
                    if (this.f1175c == null) {
                    } else {
                        ec ecVar2;
                        try {
                            this.f1173a = new RandomAccessFile(this.f1175c, "rw");
                            if (eg.f1076c && this.f1173a != null) {
                                try {
                                    this.f1173a.close();
                                    if (this.f1173a != null) {
                                        try {
                                            this.f1173a.close();
                                        } catch (Exception e) {
                                        }
                                    }
                                } catch (IOException e2) {
                                }
                            }
                            BitSet b = m1789b();
                            if (b == null) {
                                this.f1175c.delete();
                                if (this.f1173a != null) {
                                    try {
                                        this.f1173a.close();
                                    } catch (Exception e3) {
                                    }
                                }
                            } else {
                                int a = m1787a(b);
                                ArrayList a2 = m1788a(a, fa.m1786a(a, (int) this.f1175c.length(), i));
                                if (a2 == null) {
                                    this.f1175c.delete();
                                    if (this.f1173a != null) {
                                        try {
                                            this.f1173a.close();
                                        } catch (Exception e4) {
                                        }
                                    }
                                } else {
                                    ecVar2 = new ec(this.f1175c, a2, new int[]{((a - this.f1174b.m1658a()) - 4) / AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS, ((r2 - this.f1174b.m1658a()) - 4) / AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS});
                                    if (this.f1173a != null) {
                                        try {
                                            this.f1173a.close();
                                        } catch (Exception e5) {
                                        }
                                    }
                                    if (ecVar2 != null) {
                                        if (ecVar2.m1641c() > 100 && ecVar2.m1641c() < 5242880) {
                                            ecVar = ecVar2;
                                        }
                                    }
                                    this.f1175c.delete();
                                    this.f1175c = null;
                                }
                            }
                        } catch (FileNotFoundException e6) {
                            if (this.f1173a != null) {
                                try {
                                    this.f1173a.close();
                                } catch (Exception e7) {
                                }
                            }
                            ecVar2 = null;
                        } catch (Exception e8) {
                            if (this.f1173a != null) {
                                try {
                                    this.f1173a.close();
                                } catch (Exception e9) {
                                }
                            }
                            ecVar2 = null;
                        } catch (Throwable th) {
                            if (this.f1173a != null) {
                                try {
                                    this.f1173a.close();
                                } catch (Exception e10) {
                                }
                            }
                        }
                    }
                }
            }
        }
        return ecVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final synchronized void m1792a(com.p016a.ec r5) {
        /*
        r4 = this;
        r0 = 0;
        monitor-enter(r4);
        monitor-enter(r4);	 Catch:{ all -> 0x003c }
        r1 = r5.f1051a;	 Catch:{ all -> 0x0039 }
        r4.f1175c = r1;	 Catch:{ all -> 0x0039 }
        r1 = r4.f1175c;	 Catch:{ all -> 0x0039 }
        if (r1 != 0) goto L_0x000e;
    L_0x000b:
        monitor-exit(r4);	 Catch:{ all -> 0x0039 }
    L_0x000c:
        monitor-exit(r4);
        return;
    L_0x000e:
        r1 = new java.io.RandomAccessFile;	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        r2 = r4.f1175c;	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        r3 = "rw";
        r1.<init>(r2, r3);	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        r4.f1173a = r1;	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        r1 = r4.f1174b;	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        r1 = r1.m1658a();	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        r1 = new byte[r1];	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        r2 = com.p016a.eg.f1076c;	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        if (r2 == 0) goto L_0x0040;
    L_0x0025:
        r2 = r4.f1173a;	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        if (r2 == 0) goto L_0x0040;
    L_0x0029:
        r2 = r4.f1173a;	 Catch:{ IOException -> 0x003f, FileNotFoundException -> 0x0096, all -> 0x008b }
        r2.close();	 Catch:{ IOException -> 0x003f, FileNotFoundException -> 0x0096, all -> 0x008b }
        r0 = r4.f1173a;	 Catch:{ all -> 0x0039 }
        if (r0 == 0) goto L_0x0037;
    L_0x0032:
        r0 = r4.f1173a;	 Catch:{ IOException -> 0x00ae }
        r0.close();	 Catch:{ IOException -> 0x00ae }
    L_0x0037:
        monitor-exit(r4);	 Catch:{ all -> 0x0039 }
        goto L_0x000c;
    L_0x0039:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x003c }
        throw r0;	 Catch:{ all -> 0x003c }
    L_0x003c:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
    L_0x003f:
        r2 = move-exception;
    L_0x0040:
        r2 = r4.f1173a;	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        r2.read(r1);	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        r0 = com.p016a.ed.m1651b(r1);	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        r1 = r5.m1640b();	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        if (r1 == 0) goto L_0x0072;
    L_0x004f:
        r1 = r5.f1052b;	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        r2 = 0;
        r1 = r1[r2];	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
    L_0x0054:
        r2 = r5.f1052b;	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        r3 = 1;
        r2 = r2[r3];	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        if (r1 > r2) goto L_0x0062;
    L_0x005b:
        r2 = 0;
        r0.set(r1, r2);	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        r1 = r1 + 1;
        goto L_0x0054;
    L_0x0062:
        r1 = r4.f1173a;	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        r2 = 0;
        r1.seek(r2);	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        r1 = r4.f1173a;	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        r2 = com.p016a.ed.m1647a(r0);	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
        r1.write(r2);	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x00a3, all -> 0x008b }
    L_0x0072:
        r1 = r4.f1173a;	 Catch:{ all -> 0x0039 }
        if (r1 == 0) goto L_0x007b;
    L_0x0076:
        r1 = r4.f1173a;	 Catch:{ IOException -> 0x00a1 }
        r1.close();	 Catch:{ IOException -> 0x00a1 }
    L_0x007b:
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0039 }
        if (r0 == 0) goto L_0x0086;
    L_0x0081:
        r0 = r4.f1175c;	 Catch:{ all -> 0x0039 }
        r0.delete();	 Catch:{ all -> 0x0039 }
    L_0x0086:
        r0 = 0;
        r4.f1175c = r0;	 Catch:{ all -> 0x0039 }
        monitor-exit(r4);	 Catch:{ all -> 0x0039 }
        goto L_0x000c;
    L_0x008b:
        r0 = move-exception;
        r1 = r4.f1173a;	 Catch:{ all -> 0x0039 }
        if (r1 == 0) goto L_0x0095;
    L_0x0090:
        r1 = r4.f1173a;	 Catch:{ IOException -> 0x00b0 }
        r1.close();	 Catch:{ IOException -> 0x00b0 }
    L_0x0095:
        throw r0;	 Catch:{ all -> 0x0039 }
    L_0x0096:
        r1 = move-exception;
        r1 = r4.f1173a;	 Catch:{ all -> 0x0039 }
        if (r1 == 0) goto L_0x007b;
    L_0x009b:
        r1 = r4.f1173a;	 Catch:{ IOException -> 0x00a1 }
        r1.close();	 Catch:{ IOException -> 0x00a1 }
        goto L_0x007b;
    L_0x00a1:
        r1 = move-exception;
        goto L_0x007b;
    L_0x00a3:
        r1 = move-exception;
        r1 = r4.f1173a;	 Catch:{ all -> 0x0039 }
        if (r1 == 0) goto L_0x007b;
    L_0x00a8:
        r1 = r4.f1173a;	 Catch:{ IOException -> 0x00a1 }
        r1.close();	 Catch:{ IOException -> 0x00a1 }
        goto L_0x007b;
    L_0x00ae:
        r0 = move-exception;
        goto L_0x0037;
    L_0x00b0:
        r1 = move-exception;
        goto L_0x0095;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.fa.a(com.a.ec):void");
    }
}
