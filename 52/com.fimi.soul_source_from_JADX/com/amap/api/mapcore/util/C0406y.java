package com.amap.api.mapcore.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.p122a.p123a.C2915a;

/* renamed from: com.amap.api.mapcore.util.y */
class C0406y {
    RandomAccessFile f2545a;

    public C0406y() {
        this(C2915a.f14760f, 0);
    }

    public C0406y(String str, long j) {
        File file = new File(str);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (Throwable e) {
                ce.m3829a(e, "FileAccessI", "create");
                e.printStackTrace();
            }
        }
        this.f2545a = new RandomAccessFile(str, "rw");
        this.f2545a.seek(j);
    }

    public synchronized int m4239a(byte[] bArr) {
        this.f2545a.write(bArr);
        return bArr.length;
    }

    public void m4240a() {
        if (this.f2545a != null) {
            try {
                this.f2545a.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.f2545a = null;
        }
    }
}
