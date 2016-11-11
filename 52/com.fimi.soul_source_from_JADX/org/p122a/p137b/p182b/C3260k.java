package org.p122a.p137b.p182b;

import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p137b.C3258g;

/* renamed from: org.a.b.b.k */
public class C3260k {
    private static int f15764a;

    static {
        f15764a = Integer.MAX_VALUE;
    }

    public static void m18060a(C3249h c3249h, byte b) {
        C3260k.m18061a(c3249h, b, f15764a);
    }

    public static void m18061a(C3249h c3249h, byte b, int i) {
        int i2 = 0;
        if (i <= 0) {
            throw new C3258g("Maximum skip depth exceeded");
        }
        switch (b) {
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                c3249h.m17953q();
            case Type.BYTE /*3*/:
                c3249h.m17954r();
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                c3249h.m17958v();
            case Type.FLOAT /*6*/:
                c3249h.m17955s();
            case Type.DOUBLE /*8*/:
                c3249h.m17956t();
            case Type.OBJECT /*10*/:
                c3249h.m17957u();
            case Opcodes.T_LONG /*11*/:
                c3249h.m17960x();
            case Opcodes.FCONST_1 /*12*/:
                c3249h.m17943g();
                while (true) {
                    C3255e i3 = c3249h.m17945i();
                    if (i3.f15756b == null) {
                        c3249h.m17944h();
                        return;
                    } else {
                        C3260k.m18061a(c3249h, i3.f15756b, i - 1);
                        c3249h.m17946j();
                    }
                }
            case Opcodes.FCONST_2 /*13*/:
                C3257g k = c3249h.m17947k();
                while (i2 < k.f15762c) {
                    C3260k.m18061a(c3249h, k.f15760a, i - 1);
                    C3260k.m18061a(c3249h, k.f15761b, i - 1);
                    i2++;
                }
                c3249h.m17948l();
            case Opcodes.DCONST_0 /*14*/:
                C3261l o = c3249h.m17951o();
                while (i2 < o.f15766b) {
                    C3260k.m18061a(c3249h, o.f15765a, i - 1);
                    i2++;
                }
                c3249h.m17952p();
            case Opcodes.DCONST_1 /*15*/:
                C3256f m = c3249h.m17949m();
                while (i2 < m.f15759b) {
                    C3260k.m18061a(c3249h, m.f15758a, i - 1);
                    i2++;
                }
                c3249h.m17950n();
            default:
        }
    }
}
