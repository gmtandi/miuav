package org.p004c.p187f.p192a;

import java.util.concurrent.TimeUnit;

/* renamed from: org.c.f.a.p */
public class C3532p extends Exception {
    private static final long serialVersionUID = 31935685163547539L;
    private final TimeUnit f16081a;
    private final long f16082b;

    public C3532p(long j, TimeUnit timeUnit) {
        super(String.format("test timed out after %d %s", new Object[]{Long.valueOf(j), timeUnit.name().toLowerCase()}));
        this.f16081a = timeUnit;
        this.f16082b = j;
    }

    public long m19228a() {
        return this.f16082b;
    }

    public TimeUnit m19229b() {
        return this.f16081a;
    }
}
