package com.p054c.p055a.p072c;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: com.c.a.c.d */
public final class C0957d {
    public static final int f5043a = 32768;
    public static final int f5044b = 512000;
    public static final int f5045c = 75;

    private C0957d() {
    }

    public static void m7547a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    public static void m7548a(InputStream inputStream) {
        while (true) {
            try {
                if (inputStream.read(new byte[f5043a], 0, f5043a) == -1) {
                    break;
                }
            } catch (IOException e) {
            } finally {
                C0957d.m7547a((Closeable) inputStream);
            }
        }
    }

    private static boolean m7549a(C0947e c0947e, int i, int i2) {
        return (c0947e == null || c0947e.m7516a(i, i2) || (i * 100) / i2 >= f5045c) ? false : true;
    }

    public static boolean m7550a(InputStream inputStream, OutputStream outputStream, C0947e c0947e) {
        return C0957d.m7551a(inputStream, outputStream, c0947e, f5043a);
    }

    public static boolean m7551a(InputStream inputStream, OutputStream outputStream, C0947e c0947e, int i) {
        int available = inputStream.available();
        if (available <= 0) {
            available = f5044b;
        }
        byte[] bArr = new byte[i];
        if (C0957d.m7549a(c0947e, 0, available)) {
            return false;
        }
        int i2 = 0;
        do {
            int read = inputStream.read(bArr, 0, i);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
                i2 += read;
            } else {
                outputStream.flush();
                return true;
            }
        } while (!C0957d.m7549a(c0947e, i2, available));
        return false;
    }
}
