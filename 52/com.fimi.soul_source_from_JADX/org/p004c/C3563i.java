package org.p004c;

/* renamed from: org.c.i */
public class C3563i extends AssertionError {
    private static final int f16109a = 20;
    private static final long serialVersionUID = 1;
    private String f16110b;
    private String f16111c;

    public C3563i(String str, String str2, String str3) {
        super(str);
        this.f16110b = str2;
        this.f16111c = str3;
    }

    public String m19283a() {
        return this.f16111c;
    }

    public String m19284b() {
        return this.f16110b;
    }

    public String getMessage() {
        return new C3565k(f16109a, this.f16110b, this.f16111c).m19292a(super.getMessage());
    }
}
