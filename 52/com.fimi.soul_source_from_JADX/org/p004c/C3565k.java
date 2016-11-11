package org.p004c;

/* renamed from: org.c.k */
class C3565k {
    private static final String f16112a = "...";
    private static final String f16113b = "]";
    private static final String f16114c = "[";
    private final int f16115d;
    private final String f16116e;
    private final String f16117f;

    public C3565k(int i, String str, String str2) {
        this.f16115d = i;
        this.f16116e = str;
        this.f16117f = str2;
    }

    private String m19285a() {
        int min = Math.min(this.f16116e.length(), this.f16117f.length());
        for (int i = 0; i < min; i++) {
            if (this.f16116e.charAt(i) != this.f16117f.charAt(i)) {
                return this.f16116e.substring(0, i);
            }
        }
        return this.f16116e.substring(0, min);
    }

    private String m19288b(String str) {
        int i = 0;
        int min = Math.min(this.f16116e.length() - str.length(), this.f16117f.length() - str.length()) - 1;
        while (i <= min && this.f16116e.charAt((this.f16116e.length() - 1) - i) == this.f16117f.charAt((this.f16117f.length() - 1) - i)) {
            i++;
        }
        return this.f16116e.substring(this.f16116e.length() - i);
    }

    public String m19292a(String str) {
        if (this.f16116e == null || this.f16117f == null || this.f16116e.equals(this.f16117f)) {
            return C3459c.m18899e(str, this.f16116e, this.f16117f);
        }
        C3566l c3566l = new C3566l();
        String c = c3566l.m19296c();
        String d = c3566l.m19297d();
        return C3459c.m18899e(str, c + c3566l.m19294a() + d, c + c3566l.m19295b() + d);
    }
}
