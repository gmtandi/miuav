package com.p016a;

import com.fimi.kernel.p076b.p080d.C1142e;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/* renamed from: com.a.bj */
public final class bj {
    public static final Charset f629a;
    static final Charset f630b;

    static {
        f629a = Charset.forName("US-ASCII");
        f630b = Charset.forName(C1142e.f5201a);
    }

    private bj() {
    }

    static void m1167a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    static void m1168a(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        int length = listFiles.length;
        int i = 0;
        while (i < length) {
            File file2 = listFiles[i];
            if (file2.isDirectory()) {
                bj.m1168a(file2);
            }
            if (file2.delete()) {
                i++;
            } else {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }
}
