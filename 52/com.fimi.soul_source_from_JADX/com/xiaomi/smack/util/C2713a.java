package com.xiaomi.smack.util;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.smack.util.a */
public class C2713a extends Reader {
    Reader f13419a;
    List f13420b;

    public C2713a(Reader reader) {
        this.f13419a = null;
        this.f13420b = new ArrayList();
        this.f13419a = reader;
    }

    public void m15340a(C2553f c2553f) {
        if (c2553f != null) {
            synchronized (this.f13420b) {
                if (!this.f13420b.contains(c2553f)) {
                    this.f13420b.add(c2553f);
                }
            }
        }
    }

    public void m15341b(C2553f c2553f) {
        synchronized (this.f13420b) {
            this.f13420b.remove(c2553f);
        }
    }

    public void close() {
        this.f13419a.close();
    }

    public void mark(int i) {
        this.f13419a.mark(i);
    }

    public boolean markSupported() {
        return this.f13419a.markSupported();
    }

    public int read() {
        return this.f13419a.read();
    }

    public int read(char[] cArr) {
        return this.f13419a.read(cArr);
    }

    public int read(char[] cArr, int i, int i2) {
        int read = this.f13419a.read(cArr, i, i2);
        if (read > 0) {
            C2553f[] c2553fArr;
            String str = new String(cArr, i, read);
            synchronized (this.f13420b) {
                c2553fArr = new C2553f[this.f13420b.size()];
                this.f13420b.toArray(c2553fArr);
            }
            for (C2553f a : c2553fArr) {
                a.m14578a(str);
            }
        }
        return read;
    }

    public boolean ready() {
        return this.f13419a.ready();
    }

    public void reset() {
        this.f13419a.reset();
    }

    public long skip(long j) {
        return this.f13419a.skip(j);
    }
}
