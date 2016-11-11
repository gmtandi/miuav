package it.p074a.p075a;

/* renamed from: it.a.a.n */
public class C2803n extends Exception {
    private static final long serialVersionUID = 1;
    private int f14294a;
    private String f14295b;

    public C2803n(int i) {
        this.f14294a = i;
    }

    public C2803n(int i, String str) {
        this.f14294a = i;
        this.f14295b = str;
    }

    public C2803n(C2808t c2808t) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] c = c2808t.m15999c();
        for (int i = 0; i < c.length; i++) {
            if (i > 0) {
                stringBuffer.append(System.getProperty("line.separator"));
            }
            stringBuffer.append(c[i]);
        }
        this.f14294a = c2808t.m15997a();
        this.f14295b = stringBuffer.toString();
    }

    public int m15986a() {
        return this.f14294a;
    }

    public String getMessage() {
        return this.f14295b;
    }

    public String toString() {
        return new StringBuffer().append(getClass().getName()).append(" [code=").append(this.f14294a).append(", message= ").append(this.f14295b).append("]").toString();
    }
}
