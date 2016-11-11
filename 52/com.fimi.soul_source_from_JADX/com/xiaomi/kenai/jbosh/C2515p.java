package com.xiaomi.kenai.jbosh;

/* renamed from: com.xiaomi.kenai.jbosh.p */
final class C2515p extends C2498a<String> implements Comparable {
    private static final C2515p f12707a;
    private final int f12708b;
    private final int f12709c;

    static {
        try {
            f12707a = C2515p.m14396a("1.8");
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    private C2515p(String str) {
        super(str);
        int indexOf = str.indexOf(46);
        if (indexOf <= 0) {
            throw new aa("Illegal ver attribute value (not in major.minor form): " + str);
        }
        String substring = str.substring(0, indexOf);
        try {
            this.f12708b = Integer.parseInt(substring);
            if (this.f12708b < 0) {
                throw new aa("Major version may not be < 0");
            }
            substring = str.substring(indexOf + 1);
            try {
                this.f12709c = Integer.parseInt(substring);
                if (this.f12709c < 0) {
                    throw new aa("Minor version may not be < 0");
                }
            } catch (Throwable e) {
                throw new aa("Could not parse ver attribute value (minor ver): " + substring, e);
            }
        } catch (Throwable e2) {
            throw new aa("Could not parse ver attribute value (major ver): " + substring, e2);
        }
    }

    static C2515p m14396a(String str) {
        return str == null ? null : new C2515p(str);
    }

    static C2515p m14397b() {
        return f12707a;
    }

    public int compareTo(Object obj) {
        if (!(obj instanceof C2515p)) {
            return 0;
        }
        C2515p c2515p = (C2515p) obj;
        return this.f12708b < c2515p.f12708b ? -1 : this.f12708b > c2515p.f12708b ? 1 : this.f12709c >= c2515p.f12709c ? this.f12709c > c2515p.f12709c ? 1 : 0 : -1;
    }
}
