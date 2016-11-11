package com.p016a;

import com.baidu.tts.loopj.AsyncHttpClient;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.BitSet;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.fb */
public final class fb {
    private RandomAccessFile f1176a;
    private ed f1177b;
    private String f1178c;
    private File f1179d;

    protected fb(ed edVar) {
        this.f1178c = C2915a.f14760f;
        this.f1179d = null;
        this.f1177b = edVar;
    }

    protected final synchronized void m1793a(long j, byte[] bArr) {
        int i = 0;
        synchronized (this) {
            this.f1179d = this.f1177b.m1659a(j);
            if (this.f1179d != null) {
                try {
                    this.f1176a = new RandomAccessFile(this.f1179d, "rw");
                    byte[] bArr2 = new byte[this.f1177b.m1658a()];
                    int readInt = this.f1176a.read(bArr2) == -1 ? 0 : this.f1176a.readInt();
                    BitSet b = ed.m1651b(bArr2);
                    int a = (this.f1177b.m1658a() + 4) + (readInt * AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS);
                    if (readInt < 0 || readInt > (this.f1177b.m1658a() << 3)) {
                        this.f1176a.close();
                        this.f1179d.delete();
                        if (this.f1176a != null) {
                            try {
                                this.f1176a.close();
                            } catch (IOException e) {
                            }
                        }
                    } else {
                        this.f1176a.seek((long) a);
                        byte[] a2 = ed.m1648a(bArr);
                        this.f1176a.writeInt(a2.length);
                        this.f1176a.writeLong(j);
                        this.f1176a.write(a2);
                        b.set(readInt, true);
                        this.f1176a.seek(0);
                        this.f1176a.write(ed.m1647a(b));
                        readInt++;
                        if (readInt != (this.f1177b.m1658a() << 3)) {
                            i = readInt;
                        }
                        this.f1176a.writeInt(i);
                        if (!this.f1178c.equalsIgnoreCase(this.f1179d.getName())) {
                            this.f1178c = this.f1179d.getName();
                        }
                        this.f1179d.length();
                        if (this.f1176a != null) {
                            try {
                                this.f1176a.close();
                            } catch (IOException e2) {
                            }
                        }
                        this.f1179d = null;
                    }
                } catch (FileNotFoundException e3) {
                    if (this.f1176a != null) {
                        this.f1176a.close();
                    }
                } catch (IOException e4) {
                    if (this.f1176a != null) {
                        this.f1176a.close();
                    }
                } catch (Throwable th) {
                    if (this.f1176a != null) {
                        try {
                            this.f1176a.close();
                        } catch (IOException e5) {
                        }
                    }
                }
            }
        }
    }
}
