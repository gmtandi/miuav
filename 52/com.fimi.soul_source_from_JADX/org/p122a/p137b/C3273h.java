package org.p122a.p137b;

import java.io.ByteArrayOutputStream;
import org.p122a.p137b.p182b.C3249h;
import org.p122a.p137b.p182b.C3251j;
import org.p122a.p137b.p182b.C3252b;
import org.p122a.p137b.p183c.C3266a;

/* renamed from: org.a.b.h */
public class C3273h {
    private final ByteArrayOutputStream f15782a;
    private final C3266a f15783b;
    private C3249h f15784c;

    public C3273h() {
        this(new C3252b());
    }

    public C3273h(C3251j c3251j) {
        this.f15782a = new ByteArrayOutputStream();
        this.f15783b = new C3266a(this.f15782a);
        this.f15784c = c3251j.m18002a(this.f15783b);
    }

    public byte[] m18103a(C2478b c2478b) {
        this.f15782a.reset();
        c2478b.m14172b(this.f15784c);
        return this.f15782a.toByteArray();
    }
}
