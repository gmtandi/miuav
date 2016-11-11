package com.p054c.p055a.p056a.p057a.p058a.p059a;

import java.io.Closeable;
import java.io.File;
import java.io.InputStream;

/* renamed from: com.c.a.a.a.a.a.g */
public final class C0863g implements Closeable {
    final /* synthetic */ C0857a f4673a;
    private final String f4674b;
    private final long f4675c;
    private File[] f4676d;
    private final InputStream[] f4677e;
    private final long[] f4678f;

    private C0863g(C0857a c0857a, String str, long j, File[] fileArr, InputStream[] inputStreamArr, long[] jArr) {
        this.f4673a = c0857a;
        this.f4674b = str;
        this.f4675c = j;
        this.f4676d = fileArr;
        this.f4677e = inputStreamArr;
        this.f4678f = jArr;
    }

    public C0860d m7022a() {
        return this.f4673a.m6961a(this.f4674b, this.f4675c);
    }

    public File m7023a(int i) {
        return this.f4676d[i];
    }

    public InputStream m7024b(int i) {
        return this.f4677e[i];
    }

    public String m7025c(int i) {
        return C0857a.m6968b(m7024b(i));
    }

    public void close() {
        for (Closeable a : this.f4677e) {
            C0868k.m7050a(a);
        }
    }

    public long m7026d(int i) {
        return this.f4678f[i];
    }
}
