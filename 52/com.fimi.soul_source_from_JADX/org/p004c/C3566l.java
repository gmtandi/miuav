package org.p004c;

/* renamed from: org.c.l */
class C3566l {
    final /* synthetic */ C3565k f16118a;
    private final String f16119b;
    private final String f16120c;

    private C3566l(C3565k c3565k) {
        this.f16118a = c3565k;
        this.f16119b = c3565k.m19285a();
        this.f16120c = c3565k.m19288b(this.f16119b);
    }

    private String m19293a(String str) {
        return "[" + str.substring(this.f16119b.length(), str.length() - this.f16120c.length()) + "]";
    }

    public String m19294a() {
        return m19293a(this.f16118a.f16116e);
    }

    public String m19295b() {
        return m19293a(this.f16118a.f16117f);
    }

    public String m19296c() {
        return this.f16119b.length() <= this.f16118a.f16115d ? this.f16119b : "..." + this.f16119b.substring(this.f16119b.length() - this.f16118a.f16115d);
    }

    public String m19297d() {
        return this.f16120c.length() <= this.f16118a.f16115d ? this.f16120c : this.f16120c.substring(0, this.f16118a.f16115d) + "...";
    }
}
