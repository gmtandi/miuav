package com.p016a;

import com.fimi.soul.media.player.FimiMediaMeta;
import java.util.Locale;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.cn */
public class cn {
    public String f759a;
    public String f760b;
    public int f761c;
    public int f762d;
    public int f763e;
    public int f764f;
    public int f765g;
    public int f766h;
    public int f767i;
    public int f768j;
    public int f769k;

    protected cn(int i) {
        this.f759a = C2915a.f14760f;
        this.f760b = C2915a.f14760f;
        this.f761c = 0;
        this.f762d = 0;
        this.f763e = 0;
        this.f764f = 0;
        this.f765g = 0;
        this.f766h = 0;
        this.f767i = 0;
        this.f768j = -113;
        this.f769k = 9;
        this.f769k = i;
    }

    public boolean m1324a(cn cnVar) {
        if (cnVar == null) {
            return false;
        }
        switch (cnVar.f769k) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return this.f769k == 1 && cnVar.f761c == this.f761c && cnVar.f762d == this.f762d && cnVar.f760b != null && cnVar.f760b.equals(this.f760b);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return this.f769k == 2 && cnVar.f767i == this.f767i && cnVar.f766h == this.f766h && cnVar.f765g == this.f765g;
            default:
                return false;
        }
    }

    public String toString() {
        String str = FimiMediaMeta.IJKM_VAL_TYPE__UNKNOWN;
        switch (this.f769k) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return String.format(Locale.US, "GSM lac=%d, cid=%d, mnc=%s", new Object[]{Integer.valueOf(this.f761c), Integer.valueOf(this.f762d), this.f760b});
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return String.format(Locale.US, "CDMA bid=%d, nid=%d, sid=%d", new Object[]{Integer.valueOf(this.f767i), Integer.valueOf(this.f766h), Integer.valueOf(this.f765g)});
            default:
                return str;
        }
    }
}
