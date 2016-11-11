package org.p122a.p123a.p167i.p170c;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.p124f.p125c.C3022o;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.c.z */
public class C3135z {
    private static final String f15459a = "Wire";
    private final String f15460b;

    public C3135z(String str) {
        this.f15460b = str;
    }

    private void m17644a(String str, InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            int read = inputStream.read();
            if (read == -1) {
                break;
            } else if (read == 13) {
                stringBuilder.append("[\\r]");
            } else if (read == 10) {
                stringBuilder.append("[\\n]\"");
                stringBuilder.insert(0, "\"");
                stringBuilder.insert(0, str);
                Log.d(f15459a, this.f15460b + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + stringBuilder.toString());
                stringBuilder.setLength(0);
            } else if (read < 32 || read > Opcodes.LAND) {
                stringBuilder.append("[0x");
                stringBuilder.append(Integer.toHexString(read));
                stringBuilder.append("]");
            } else {
                stringBuilder.append((char) read);
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.append(C3022o.f15057e);
            stringBuilder.insert(0, C3022o.f15057e);
            stringBuilder.insert(0, str);
            Log.d(f15459a, this.f15460b + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + stringBuilder.toString());
        }
    }

    public void m17645a(int i) {
        m17648a(new byte[]{(byte) i});
    }

    public void m17646a(InputStream inputStream) {
        C3234a.m17886a((Object) inputStream, "Output");
        m17644a(">> ", inputStream);
    }

    public void m17647a(String str) {
        C3234a.m17886a((Object) str, "Output");
        m17648a(str.getBytes());
    }

    public void m17648a(byte[] bArr) {
        C3234a.m17886a((Object) bArr, "Output");
        m17644a(">> ", new ByteArrayInputStream(bArr));
    }

    public void m17649a(byte[] bArr, int i, int i2) {
        C3234a.m17886a((Object) bArr, "Output");
        m17644a(">> ", new ByteArrayInputStream(bArr, i, i2));
    }

    public boolean m17650a() {
        return Log.isLoggable(f15459a, 3);
    }

    public void m17651b(int i) {
        m17654b(new byte[]{(byte) i});
    }

    public void m17652b(InputStream inputStream) {
        C3234a.m17886a((Object) inputStream, "Input");
        m17644a("<< ", inputStream);
    }

    public void m17653b(String str) {
        C3234a.m17886a((Object) str, "Input");
        m17654b(str.getBytes());
    }

    public void m17654b(byte[] bArr) {
        C3234a.m17886a((Object) bArr, "Input");
        m17644a("<< ", new ByteArrayInputStream(bArr));
    }

    public void m17655b(byte[] bArr, int i, int i2) {
        C3234a.m17886a((Object) bArr, "Input");
        m17644a("<< ", new ByteArrayInputStream(bArr, i, i2));
    }
}
