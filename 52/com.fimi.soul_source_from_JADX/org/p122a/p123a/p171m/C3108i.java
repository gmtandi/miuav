package org.p122a.p123a.p171m;

import com.tencent.mm.sdk.platformtools.MAlarmHandler;
import java.util.concurrent.TimeUnit;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2911a;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p180o.C3234a;

@C2914d
/* renamed from: org.a.a.m.i */
public abstract class C3108i<T, C> {
    private final String f15392a;
    private final T f15393b;
    private final C f15394c;
    private final long f15395d;
    private final long f15396e;
    @C2911a(a = "this")
    private long f15397f;
    @C2911a(a = "this")
    private long f15398g;
    private volatile Object f15399h;

    public C3108i(String str, T t, C c) {
        this(str, t, c, 0, TimeUnit.MILLISECONDS);
    }

    public C3108i(String str, T t, C c, long j, TimeUnit timeUnit) {
        C3234a.m17886a((Object) t, "Route");
        C3234a.m17886a((Object) c, C3004e.f15024j);
        C3234a.m17886a((Object) timeUnit, "Time unit");
        this.f15392a = str;
        this.f15393b = t;
        this.f15394c = c;
        this.f15395d = System.currentTimeMillis();
        if (j > 0) {
            this.f15396e = this.f15395d + timeUnit.toMillis(j);
        } else {
            this.f15396e = MAlarmHandler.NEXT_FIRE_INTERVAL;
        }
        this.f15398g = this.f15396e;
    }

    public synchronized void m17533a(long j, TimeUnit timeUnit) {
        C3234a.m17886a((Object) timeUnit, "Time unit");
        this.f15397f = System.currentTimeMillis();
        this.f15398g = Math.min(j > 0 ? this.f15397f + timeUnit.toMillis(j) : MAlarmHandler.NEXT_FIRE_INTERVAL, this.f15396e);
    }

    public void m17534a(Object obj) {
        this.f15399h = obj;
    }

    public synchronized boolean m17535a(long j) {
        return j >= this.f15398g;
    }

    public abstract boolean m17536e();

    public abstract void m17537f();

    public String m17538g() {
        return this.f15392a;
    }

    public T m17539h() {
        return this.f15393b;
    }

    public C m17540i() {
        return this.f15394c;
    }

    public long m17541j() {
        return this.f15395d;
    }

    public long m17542k() {
        return this.f15396e;
    }

    public Object m17543l() {
        return this.f15399h;
    }

    public synchronized long m17544m() {
        return this.f15397f;
    }

    public synchronized long m17545n() {
        return this.f15398g;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[id:");
        stringBuilder.append(this.f15392a);
        stringBuilder.append("][route:");
        stringBuilder.append(this.f15393b);
        stringBuilder.append("][state:");
        stringBuilder.append(this.f15399h);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
