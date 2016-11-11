package com.p054c.p055a.p056a.p057a.p058a.p059a;

import com.fimi.kernel.p076b.p080d.C1142e;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.c.a.a.a.a.a.k */
final class C0868k {
    static final Charset f4698a;
    static final Charset f4699b;

    static {
        f4698a = Charset.forName("US-ASCII");
        f4699b = Charset.forName(C1142e.f5201a);
    }

    private C0868k() {
    }

    static String m7049a(Reader reader) {
        try {
            StringWriter stringWriter = new StringWriter();
            char[] cArr = new char[SmileConstants.MAX_SHARED_STRING_VALUES];
            while (true) {
                int read = reader.read(cArr);
                if (read == -1) {
                    break;
                }
                stringWriter.write(cArr, 0, read);
            }
            String stringWriter2 = stringWriter.toString();
            return stringWriter2;
        } finally {
            reader.close();
        }
    }

    static void m7050a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    static void m7051a(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        int length = listFiles.length;
        int i = 0;
        while (i < length) {
            File file2 = listFiles[i];
            if (file2.isDirectory()) {
                C0868k.m7051a(file2);
            }
            if (file2.delete()) {
                i++;
            } else {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }
}
