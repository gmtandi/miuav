package com.fimi.soul.biz.p090b;

import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.module.calibcompass.C1683q;
import com.fimi.soul.module.p091a.C1258f;
import com.fimi.soul.module.p091a.C1662d;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.biz.b.q */
public class C1259q implements C1234i, C1258f {
    private C1433a f5699a;
    private C1262t f5700b;
    private C1251i f5701c;
    private C1257o f5702d;

    public C1259q(C1433a c1433a) {
        this.f5699a = c1433a;
        c1433a.m9590a((C1234i) this);
        C1662d.m10808a().m10810a((C1258f) this);
        this.f5700b = new C1262t(c1433a);
        this.f5701c = new C1251i(c1433a);
        this.f5702d = new C1257o(c1433a);
    }

    public void m8626a() {
        this.f5699a.m9594b((C1234i) this);
    }

    public void m8627a(int i) {
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        switch (C1260r.f5703a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f5700b.m8640a();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                c1433a.m9589a(C1584h.SHOWOUTTIMEPROBAR);
                this.f5700b.m8643a(C1247f.m8565k().m8578f());
            case Type.BYTE /*3*/:
                this.f5700b.m8644b(1);
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                C1683q.m10886a().m10887a("130");
                this.f5700b.m8642a(c1433a.m9609l());
            case Type.INT /*5*/:
                this.f5701c.m8596a(C1247f.m8565k().m8578f());
            case Type.FLOAT /*6*/:
                this.f5701c.m8595a();
            case Type.LONG /*7*/:
                this.f5702d.m8624a(C1247f.m8565k().m8578f());
            case Type.DOUBLE /*8*/:
                this.f5702d.m8623a();
            default:
        }
    }
}
