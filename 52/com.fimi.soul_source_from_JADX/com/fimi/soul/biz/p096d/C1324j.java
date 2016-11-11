package com.fimi.soul.biz.p096d;

/* renamed from: com.fimi.soul.biz.d.j */
class C1324j {
    final /* synthetic */ C1316b f5936a;
    private StringBuilder f5937b;

    public C1324j(C1316b c1316b, String str) {
        this.f5936a = c1316b;
        this.f5937b = new StringBuilder();
        this.f5937b.append(str);
        this.f5937b.append("?");
    }

    public String m8927a() {
        return this.f5937b.toString();
    }

    public void m8928a(String str, String str2) {
        if (str != null && str2 != null && str2.trim().length() > 0) {
            this.f5937b.append(String.format("%s=%s&", new Object[]{str, str2}));
        }
    }
}
