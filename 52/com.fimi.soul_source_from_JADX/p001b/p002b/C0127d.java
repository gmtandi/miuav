package p001b.p002b;

/* renamed from: b.b.d */
public class C0127d extends C0125b {
    private static final int f139a = 20;
    private static final long serialVersionUID = 1;
    private String f140b;
    private String f141c;

    public C0127d(String str, String str2, String str3) {
        super(str);
        this.f140b = str2;
        this.f141c = str3;
    }

    public String m209a() {
        return this.f141c;
    }

    public String m210b() {
        return this.f140b;
    }

    public String getMessage() {
        return new C0126c(f139a, this.f140b, this.f141c).m208a(super.getMessage());
    }
}
