package com.tencent.stat.common;

import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.tencent.stat.common.g */
public class C2414g {
    static final /* synthetic */ boolean f12332a;

    static {
        f12332a = !C2414g.class.desiredAssertionStatus();
    }

    private C2414g() {
    }

    public static byte[] m13996a(byte[] bArr, int i) {
        return C2414g.m13997a(bArr, 0, bArr.length, i);
    }

    public static byte[] m13997a(byte[] bArr, int i, int i2, int i3) {
        C2416i c2416i = new C2416i(i3, new byte[((i2 * 3) / 4)]);
        if (!c2416i.m14000a(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (c2416i.b == c2416i.a.length) {
            return c2416i.a;
        } else {
            Object obj = new byte[c2416i.b];
            System.arraycopy(c2416i.a, 0, obj, 0, c2416i.b);
            return obj;
        }
    }

    public static byte[] m13998b(byte[] bArr, int i) {
        return C2414g.m13999b(bArr, 0, bArr.length, i);
    }

    public static byte[] m13999b(byte[] bArr, int i, int i2, int i3) {
        C2417j c2417j = new C2417j(i3, null);
        int i4 = (i2 / 3) * 4;
        if (!c2417j.f12344d) {
            switch (i2 % 3) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    i4 += 2;
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    i4 += 3;
                    break;
                default:
                    break;
            }
        } else if (i2 % 3 > 0) {
            i4 += 4;
        }
        if (c2417j.f12345e && i2 > 0) {
            i4 += (c2417j.f12346f ? 2 : 1) * (((i2 - 1) / 57) + 1);
        }
        c2417j.a = new byte[i4];
        c2417j.m14001a(bArr, i, i2, true);
        if (f12332a || c2417j.b == i4) {
            return c2417j.a;
        }
        throw new AssertionError();
    }
}
