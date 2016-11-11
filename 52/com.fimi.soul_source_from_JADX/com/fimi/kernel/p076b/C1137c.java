package com.fimi.kernel.p076b;

import com.fimi.kernel.p076b.p077a.C1102c;
import com.fimi.kernel.p076b.p078b.C1129q;
import com.fimi.kernel.p076b.p080d.C1140c;
import com.fimi.kernel.p076b.p081e.C1145b;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.kernel.b.c */
public final class C1137c {
    public static C1100b m7862a(C1152e c1152e) {
        switch (C1143d.f5207a[c1152e.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return C1102c.m7690b();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return new C1145b();
            case Type.BYTE /*3*/:
                return new C1140c();
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return new C1129q();
            default:
                return null;
        }
    }
}
