package com.amap.api.services.core;

import com.fimi.kernel.p076b.p080d.C1142e;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public final class bp {
    public static final Charset f3120a;
    static final Charset f3121b;

    static {
        f3120a = Charset.forName("US-ASCII");
        f3121b = Charset.forName(C1142e.f5201a);
    }

    private bp() {
    }

    static void m4726a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    static void m4727a(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        int length = listFiles.length;
        int i = 0;
        while (i < length) {
            File file2 = listFiles[i];
            if (file2.isDirectory()) {
                m4727a(file2);
            }
            if (file2.delete()) {
                i++;
            } else {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }
}
