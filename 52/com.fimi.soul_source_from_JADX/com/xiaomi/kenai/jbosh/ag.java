package com.xiaomi.kenai.jbosh;

public final class ag {
    private final an f12641a;

    private ag(an anVar) {
        this.f12641a = anVar;
    }

    public static ag m14314a(String str) {
        return m14316a("xm", str, null);
    }

    public static ag m14315a(String str, String str2) {
        return m14316a(str, str2, null);
    }

    public static ag m14316a(String str, String str2, String str3) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("URI is required and may not be null/empty");
        } else if (str2 != null && str2.length() != 0) {
            return (str3 == null || str3.length() == 0) ? new ag(new an(str, str2)) : new ag(new an(str, str2, str3));
        } else {
            throw new IllegalArgumentException("Local arg is required and may not be null/empty");
        }
    }

    public String m14317a() {
        return this.f12641a.m14358a();
    }

    boolean m14318a(an anVar) {
        return this.f12641a.equals(anVar);
    }

    public String m14319b() {
        return this.f12641a.m14359b();
    }

    public String m14320c() {
        return this.f12641a.m14360c();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ag)) {
            return false;
        }
        return this.f12641a.equals(((ag) obj).f12641a);
    }

    public int hashCode() {
        return this.f12641a.hashCode();
    }
}
