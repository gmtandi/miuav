package com.amap.api.services.core;

import java.util.List;

class be extends bi {
    private C0460a f3067a;

    /* renamed from: com.amap.api.services.core.be.a */
    class C0460a implements bn {
        final /* synthetic */ be f3065a;
        private ak f3066b;

        C0460a(be beVar, ak akVar) {
            this.f3065a = beVar;
            this.f3066b = akVar;
        }

        public void m4658a(String str) {
            try {
                this.f3066b.m4536b(str, this.f3065a.m4659a());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    be() {
    }

    protected int m4659a() {
        return 1;
    }

    protected bn m4660a(ak akVar) {
        try {
            if (this.f3067a == null) {
                this.f3067a = new C0460a(this, akVar);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.f3067a;
    }

    protected String m4661a(String str) {
        return ab.m4473b(str);
    }

    protected String m4662a(List<ad> list) {
        return null;
    }

    protected String m4663b() {
        return bf.f3075b;
    }
}
