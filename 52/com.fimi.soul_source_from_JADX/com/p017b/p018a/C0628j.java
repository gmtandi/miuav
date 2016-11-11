package com.p017b.p018a;

/* renamed from: com.b.a.j */
class C0628j implements C0620b {
    private C0623e f3872a;
    private C0629k f3873b;
    private int f3874c;

    public C0628j(C0623e c0623e, C0629k c0629k, int i) {
        this.f3872a = c0623e;
        this.f3873b = c0629k;
        this.f3874c = i;
    }

    private void m5615e(C0616a c0616a) {
        if (!this.f3872a.f3850b) {
            Object obj;
            int size = this.f3873b.f3877c.size();
            for (int i = 0; i < size; i++) {
                obj = (C0627i) this.f3873b.f3877c.get(i);
                if (obj.f3871d == this.f3874c && obj.f3870c.f3875a == c0616a) {
                    c0616a.m5383b((C0620b) this);
                    break;
                }
            }
            obj = null;
            this.f3873b.f3877c.remove(obj);
            if (this.f3873b.f3877c.size() == 0) {
                this.f3873b.f3875a.m5376a();
                this.f3872a.f3851c.add(this.f3873b.f3875a);
            }
        }
    }

    public void m5616a(C0616a c0616a) {
        if (this.f3874c == 0) {
            m5615e(c0616a);
        }
    }

    public void m5617b(C0616a c0616a) {
        if (this.f3874c == 1) {
            m5615e(c0616a);
        }
    }

    public void m5618c(C0616a c0616a) {
    }

    public void m5619d(C0616a c0616a) {
    }
}
