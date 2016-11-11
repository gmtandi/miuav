package com.amap.api.services.core;

import java.util.Date;
import java.util.List;

class bc extends bi {
    private C0459a f3063a;

    /* renamed from: com.amap.api.services.core.bc.a */
    class C0459a implements bn {
        final /* synthetic */ bc f3061a;
        private ak f3062b;

        C0459a(bc bcVar, ak akVar) {
            this.f3061a = bcVar;
            this.f3062b = akVar;
        }

        public void m4649a(String str) {
            try {
                this.f3062b.m4536b(str, this.f3061a.m4650a());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    bc() {
    }

    protected int m4650a() {
        return 0;
    }

    protected bn m4651a(ak akVar) {
        try {
            if (this.f3063a == null) {
                this.f3063a = new C0459a(this, akVar);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.f3063a;
    }

    protected String m4652a(String str) {
        return ab.m4473b(str + bj.m4670a(new Date().getTime()));
    }

    protected String m4653a(List<ad> list) {
        return null;
    }

    protected String m4654b() {
        return bf.f3076c;
    }
}
