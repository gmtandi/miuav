package org.p122a.p123a.p162e;

import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C2922b;

/* renamed from: org.a.a.e.b */
public class C2996b {
    private int f14965a;
    private int f14966b;
    private Charset f14967c;
    private CodingErrorAction f14968d;
    private CodingErrorAction f14969e;
    private C2998d f14970f;

    C2996b() {
        this.f14966b = -1;
    }

    public C2995a m17016a() {
        Charset charset = this.f14967c;
        if (charset == null && !(this.f14968d == null && this.f14969e == null)) {
            charset = C2922b.f14782f;
        }
        int i = this.f14965a > 0 ? this.f14965a : Opcodes.ACC_ANNOTATION;
        return new C2995a(i, this.f14966b >= 0 ? this.f14966b : i, charset, this.f14968d, this.f14969e, this.f14970f);
    }

    public C2996b m17017a(int i) {
        this.f14965a = i;
        return this;
    }

    public C2996b m17018a(Charset charset) {
        this.f14967c = charset;
        return this;
    }

    public C2996b m17019a(CodingErrorAction codingErrorAction) {
        this.f14968d = codingErrorAction;
        if (codingErrorAction != null && this.f14967c == null) {
            this.f14967c = C2922b.f14782f;
        }
        return this;
    }

    public C2996b m17020a(C2998d c2998d) {
        this.f14970f = c2998d;
        return this;
    }

    public C2996b m17021b(int i) {
        this.f14966b = i;
        return this;
    }

    public C2996b m17022b(CodingErrorAction codingErrorAction) {
        this.f14969e = codingErrorAction;
        if (codingErrorAction != null && this.f14967c == null) {
            this.f14967c = C2922b.f14782f;
        }
        return this;
    }
}
