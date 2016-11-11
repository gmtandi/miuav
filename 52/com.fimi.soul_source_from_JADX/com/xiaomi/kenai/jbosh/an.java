package com.xiaomi.kenai.jbosh;

import java.io.Serializable;
import org.p122a.p123a.C2915a;

public class an implements Serializable {
    private static final String f12666a;
    private String f12667b;
    private String f12668c;
    private String f12669d;

    static {
        f12666a = C2915a.f14760f.intern();
    }

    public an(String str, String str2) {
        this(str, str2, f12666a);
    }

    public an(String str, String str2, String str3) {
        this.f12667b = str == null ? f12666a : str.intern();
        if (str2 == null) {
            throw new IllegalArgumentException("invalid QName local part");
        }
        this.f12668c = str2.intern();
        if (str3 == null) {
            throw new IllegalArgumentException("invalid QName prefix");
        }
        this.f12669d = str3.intern();
    }

    public String m14358a() {
        return this.f12667b;
    }

    public String m14359b() {
        return this.f12668c;
    }

    public String m14360c() {
        return this.f12669d;
    }

    public final boolean equals(Object obj) {
        return obj == this ? true : !(obj instanceof an) ? false : this.f12667b == ((an) obj).f12667b && this.f12668c == ((an) obj).f12668c;
    }

    public final int hashCode() {
        return this.f12667b.hashCode() ^ this.f12668c.hashCode();
    }

    public String toString() {
        return this.f12667b == f12666a ? this.f12668c : '{' + this.f12667b + '}' + this.f12668c;
    }
}
