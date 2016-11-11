package com.p016a;

import java.io.Closeable;
import java.io.InputStream;

/* renamed from: com.a.bc */
public final class bc implements Closeable {
    final /* synthetic */ az f611a;
    private final String f612b;
    private final long f613c;
    private final InputStream[] f614d;
    private final long[] f615e;

    private bc(az azVar, String str, long j, InputStream[] inputStreamArr, long[] jArr) {
        this.f611a = azVar;
        this.f612b = str;
        this.f613c = j;
        this.f614d = inputStreamArr;
        this.f615e = jArr;
    }

    public InputStream m1147a(int i) {
        return this.f614d[i];
    }

    public void close() {
        for (Closeable a : this.f614d) {
            bj.m1167a(a);
        }
    }
}
