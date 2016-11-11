package com.xiaomi.measite.smack;

import com.xiaomi.smack.C2557n;
import com.xiaomi.smack.C2559l;
import com.xiaomi.smack.C2678j;
import com.xiaomi.smack.debugger.C2551a;
import com.xiaomi.smack.util.C2553f;
import com.xiaomi.smack.util.C2555j;
import com.xiaomi.smack.util.C2713a;
import com.xiaomi.smack.util.C2714b;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;

/* renamed from: com.xiaomi.measite.smack.a */
public class C2552a implements C2551a {
    public static boolean f12843a;
    private SimpleDateFormat f12844b;
    private C2678j f12845c;
    private C2557n f12846d;
    private C2559l f12847e;
    private Writer f12848f;
    private Reader f12849g;
    private C2553f f12850h;
    private C2555j f12851i;

    static {
        f12843a = false;
    }

    public C2552a(C2678j c2678j, Writer writer, Reader reader) {
        this.f12844b = new SimpleDateFormat("hh:mm:ss aaa");
        this.f12845c = null;
        this.f12846d = null;
        this.f12847e = null;
        this.f12845c = c2678j;
        this.f12848f = writer;
        this.f12849g = reader;
        m14571e();
    }

    private void m14571e() {
        Reader c2713a = new C2713a(this.f12849g);
        this.f12850h = new C2554b(this);
        c2713a.m15340a(this.f12850h);
        Writer c2714b = new C2714b(this.f12848f);
        this.f12851i = new C2556c(this);
        c2714b.m15343a(this.f12851i);
        this.f12849g = c2713a;
        this.f12848f = c2714b;
        this.f12846d = new C2558d(this);
        this.f12847e = new C2560e(this);
    }

    public Reader m14572a() {
        return this.f12849g;
    }

    public Reader m14573a(Reader reader) {
        ((C2713a) this.f12849g).m15341b(this.f12850h);
        Reader c2713a = new C2713a(reader);
        c2713a.m15340a(this.f12850h);
        this.f12849g = c2713a;
        return this.f12849g;
    }

    public Writer m14574a(Writer writer) {
        ((C2714b) this.f12848f).m15344b(this.f12851i);
        Writer c2714b = new C2714b(writer);
        c2714b.m15343a(this.f12851i);
        this.f12848f = c2714b;
        return this.f12848f;
    }

    public Writer m14575b() {
        return this.f12848f;
    }

    public C2557n m14576c() {
        return this.f12846d;
    }

    public C2557n m14577d() {
        return null;
    }
}
