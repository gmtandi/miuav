package com.p016a;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import org.p122a.p123a.p124f.p125c.C3022o;

/* renamed from: com.a.bd */
final class bd {
    final /* synthetic */ az f616a;
    private final String f617b;
    private final long[] f618c;
    private boolean f619d;
    private ba f620e;
    private long f621f;

    private bd(az azVar, String str) {
        this.f616a = azVar;
        this.f617b = str;
        this.f618c = new long[azVar.f596i];
    }

    private void m1152a(String[] strArr) {
        if (strArr.length != this.f616a.f596i) {
            throw m1154b(strArr);
        }
        int i = 0;
        while (i < strArr.length) {
            try {
                this.f618c[i] = Long.parseLong(strArr[i]);
                i++;
            } catch (NumberFormatException e) {
                throw m1154b(strArr);
            }
        }
    }

    private IOException m1154b(String[] strArr) {
        throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
    }

    public File m1159a(int i) {
        return new File(this.f616a.f590c, this.f617b + "." + i);
    }

    public String m1160a() {
        StringBuilder stringBuilder = new StringBuilder();
        for (long append : this.f618c) {
            stringBuilder.append(C3022o.f15055c).append(append);
        }
        return stringBuilder.toString();
    }

    public File m1161b(int i) {
        return new File(this.f616a.f590c, this.f617b + "." + i + ".tmp");
    }
}
