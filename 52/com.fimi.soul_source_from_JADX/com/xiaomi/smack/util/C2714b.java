package com.xiaomi.smack.util;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.smack.util.b */
public class C2714b extends Writer {
    Writer f13421a;
    List f13422b;

    public C2714b(Writer writer) {
        this.f13421a = null;
        this.f13422b = new ArrayList();
        this.f13421a = writer;
    }

    private void m15342a(String str) {
        synchronized (this.f13422b) {
            C2555j[] c2555jArr = new C2555j[this.f13422b.size()];
            this.f13422b.toArray(c2555jArr);
        }
        for (C2555j a : c2555jArr) {
            a.m14580a(str);
        }
    }

    public void m15343a(C2555j c2555j) {
        if (c2555j != null) {
            synchronized (this.f13422b) {
                if (!this.f13422b.contains(c2555j)) {
                    this.f13422b.add(c2555j);
                }
            }
        }
    }

    public void m15344b(C2555j c2555j) {
        synchronized (this.f13422b) {
            this.f13422b.remove(c2555j);
        }
    }

    public void close() {
        this.f13421a.close();
    }

    public void flush() {
        this.f13421a.flush();
    }

    public void write(int i) {
        this.f13421a.write(i);
    }

    public void write(String str) {
        this.f13421a.write(str);
        m15342a(str);
    }

    public void write(String str, int i, int i2) {
        this.f13421a.write(str, i, i2);
        m15342a(str.substring(i, i + i2));
    }

    public void write(char[] cArr) {
        this.f13421a.write(cArr);
        m15342a(new String(cArr));
    }

    public void write(char[] cArr, int i, int i2) {
        this.f13421a.write(cArr, i, i2);
        m15342a(new String(cArr, i, i2));
    }
}
