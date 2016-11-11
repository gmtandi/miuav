package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;
import java.io.Serializable;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.drone.c.a.a.k */
public class C1448k extends C1437b implements Serializable {
    public static final byte f6847b = (byte) 115;
    public static final byte f6848c = (byte) 1;
    public static final byte f6849d = (byte) 0;
    public static final byte f6850e = (byte) 1;
    public static final byte f6851f = (byte) 2;
    public byte f6852g;
    public byte f6853h;
    public byte f6854i;
    public byte f6855j;
    private final byte f6856k;
    private byte f6857l;

    public C1448k() {
        this.f6856k = SmileConstants.TOKEN_KEY_LONG_STRING;
        this.f6857l = f6849d;
        this.a = 52;
    }

    public C1465c m9768a() {
        C1465c c1465c = new C1465c();
        c1465c.f7000c = 52;
        c1465c.f6999b = 2;
        c1465c.f7001d.m9828b((byte) f6850e);
        c1465c.f7001d.m9828b((byte) f6850e);
        return c1465c;
    }

    public void m9769a(byte b) {
        switch (b) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                this.f6857l = f6849d;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f6857l = f6850e;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f6857l = f6851f;
            default:
        }
    }

    public void m9770a(C1465c c1465c) {
        m9771a(c1465c.f7001d);
    }

    public void m9771a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6852g = c1466d.m9833d();
        this.f6853h = c1466d.m9833d();
        this.f6854i = c1466d.m9833d();
        this.f6855j = c1466d.m9833d();
    }
}
