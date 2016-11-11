package org.p122a.p123a.p162e;

import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.e.a */
public class C2995a implements Cloneable {
    public static final C2995a f14958a;
    private final int f14959b;
    private final int f14960c;
    private final Charset f14961d;
    private final CodingErrorAction f14962e;
    private final CodingErrorAction f14963f;
    private final C2998d f14964g;

    static {
        f14958a = new C2996b().m17016a();
    }

    C2995a(int i, int i2, Charset charset, CodingErrorAction codingErrorAction, CodingErrorAction codingErrorAction2, C2998d c2998d) {
        this.f14959b = i;
        this.f14960c = i2;
        this.f14961d = charset;
        this.f14962e = codingErrorAction;
        this.f14963f = codingErrorAction2;
        this.f14964g = c2998d;
    }

    public static C2996b m17007a(C2995a c2995a) {
        C3234a.m17886a((Object) c2995a, "Connection config");
        return new C2996b().m17018a(c2995a.m17011c()).m17019a(c2995a.m17012d()).m17022b(c2995a.m17013e()).m17020a(c2995a.m17014f());
    }

    public static C2996b m17008h() {
        return new C2996b();
    }

    public int m17009a() {
        return this.f14959b;
    }

    public int m17010b() {
        return this.f14960c;
    }

    public Charset m17011c() {
        return this.f14961d;
    }

    protected /* synthetic */ Object clone() {
        return m17015g();
    }

    public CodingErrorAction m17012d() {
        return this.f14962e;
    }

    public CodingErrorAction m17013e() {
        return this.f14963f;
    }

    public C2998d m17014f() {
        return this.f14964g;
    }

    protected C2995a m17015g() {
        return (C2995a) super.clone();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[bufferSize=").append(this.f14959b).append(", fragmentSizeHint=").append(this.f14960c).append(", charset=").append(this.f14961d).append(", malformedInputAction=").append(this.f14962e).append(", unmappableInputAction=").append(this.f14963f).append(", messageConstraints=").append(this.f14964g).append("]");
        return stringBuilder.toString();
    }
}
