package p001b.p002b;

import org.p122a.p123a.C2915a;

/* renamed from: b.b.c */
public class C0126c {
    private static final String f131a = "...";
    private static final String f132b = "]";
    private static final String f133c = "[";
    private int f134d;
    private String f135e;
    private String f136f;
    private int f137g;
    private int f138h;

    public C0126c(int i, String str, String str2) {
        this.f134d = i;
        this.f135e = str;
        this.f136f = str2;
    }

    private void m202a() {
        this.f137g = 0;
        int min = Math.min(this.f135e.length(), this.f136f.length());
        while (this.f137g < min && this.f135e.charAt(this.f137g) == this.f136f.charAt(this.f137g)) {
            this.f137g++;
        }
    }

    private String m203b(String str) {
        String str2 = f133c + str.substring(this.f137g, (str.length() - this.f138h) + 1) + f132b;
        if (this.f137g > 0) {
            str2 = m205c() + str2;
        }
        return this.f138h > 0 ? str2 + m206d() : str2;
    }

    private void m204b() {
        int length = this.f135e.length() - 1;
        int length2 = this.f136f.length() - 1;
        while (length2 >= this.f137g && length >= this.f137g && this.f135e.charAt(length) == this.f136f.charAt(length2)) {
            length2--;
            length--;
        }
        this.f138h = this.f135e.length() - length;
    }

    private String m205c() {
        return (this.f137g > this.f134d ? f131a : C2915a.f14760f) + this.f135e.substring(Math.max(0, this.f137g - this.f134d), this.f137g);
    }

    private String m206d() {
        return this.f135e.substring((this.f135e.length() - this.f138h) + 1, Math.min(((this.f135e.length() - this.f138h) + 1) + this.f134d, this.f135e.length())) + ((this.f135e.length() - this.f138h) + 1 < this.f135e.length() - this.f134d ? f131a : C2915a.f14760f);
    }

    private boolean m207e() {
        return this.f135e.equals(this.f136f);
    }

    public String m208a(String str) {
        if (this.f135e == null || this.f136f == null || m207e()) {
            return C0119a.m189f(str, this.f135e, this.f136f);
        }
        m202a();
        m204b();
        return C0119a.m189f(str, m203b(this.f135e), m203b(this.f136f));
    }
}
