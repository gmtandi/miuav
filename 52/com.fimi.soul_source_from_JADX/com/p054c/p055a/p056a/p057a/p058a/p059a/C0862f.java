package com.p054c.p055a.p056a.p057a.p058a.p059a;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import org.p122a.p123a.p124f.p125c.C3022o;

/* renamed from: com.c.a.a.a.a.a.f */
final class C0862f {
    final /* synthetic */ C0857a f4667a;
    private final String f4668b;
    private final long[] f4669c;
    private boolean f4670d;
    private C0860d f4671e;
    private long f4672f;

    private C0862f(C0857a c0857a, String str) {
        this.f4667a = c0857a;
        this.f4668b = str;
        this.f4669c = new long[c0857a.f4653t];
    }

    private void m7012a(String[] strArr) {
        if (strArr.length != this.f4667a.f4653t) {
            throw m7014b(strArr);
        }
        int i = 0;
        while (i < strArr.length) {
            try {
                this.f4669c[i] = Long.parseLong(strArr[i]);
                i++;
            } catch (NumberFormatException e) {
                throw m7014b(strArr);
            }
        }
    }

    private IOException m7014b(String[] strArr) {
        throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
    }

    public File m7019a(int i) {
        return new File(this.f4667a.f4646m, this.f4668b + "." + i);
    }

    public String m7020a() {
        StringBuilder stringBuilder = new StringBuilder();
        for (long append : this.f4669c) {
            stringBuilder.append(C3022o.f15055c).append(append);
        }
        return stringBuilder.toString();
    }

    public File m7021b(int i) {
        return new File(this.f4667a.f4646m, this.f4668b + "." + i + ".tmp");
    }
}
