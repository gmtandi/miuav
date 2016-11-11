package it.p074a.p075a;

import java.util.Date;

/* renamed from: it.a.a.o */
public class C2804o {
    public static final int f14296a = 0;
    public static final int f14297b = 1;
    public static final int f14298c = 2;
    private String f14299d;
    private String f14300e;
    private Date f14301f;
    private long f14302g;
    private int f14303h;

    public C2804o() {
        this.f14299d = null;
        this.f14300e = null;
        this.f14301f = null;
        this.f14302g = -1;
    }

    public Date m15987a() {
        return this.f14301f;
    }

    public void m15988a(int i) {
        this.f14303h = i;
    }

    public void m15989a(long j) {
        this.f14302g = j;
    }

    public void m15990a(String str) {
        this.f14299d = str;
    }

    public void m15991a(Date date) {
        this.f14301f = date;
    }

    public String m15992b() {
        return this.f14299d;
    }

    public void m15993b(String str) {
        this.f14300e = str;
    }

    public int m15994c() {
        return this.f14303h;
    }

    public long m15995d() {
        return this.f14302g;
    }

    public String m15996e() {
        return this.f14300e;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getClass().getName());
        stringBuffer.append(" [name=");
        stringBuffer.append(this.f14299d);
        stringBuffer.append(", type=");
        if (this.f14303h == 0) {
            stringBuffer.append("FILE");
        } else if (this.f14303h == f14297b) {
            stringBuffer.append("DIRECTORY");
        } else if (this.f14303h == f14298c) {
            stringBuffer.append("LINK");
            stringBuffer.append(", link=");
            stringBuffer.append(this.f14300e);
        } else {
            stringBuffer.append("UNKNOWN");
        }
        stringBuffer.append(", size=");
        stringBuffer.append(this.f14302g);
        stringBuffer.append(", modifiedDate=");
        stringBuffer.append(this.f14301f);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
