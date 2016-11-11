package com.p054c.p055a.p072c;

import android.opengl.GLES10;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.p054c.p055a.p063b.p064a.C0900f;
import com.p054c.p055a.p063b.p064a.C0903i;
import com.p054c.p055a.p063b.p069e.C0925a;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.c.a.c.b */
public final class C0955b {
    private static final int f5040a = 2048;
    private static C0900f f5041b;

    static {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        int max = Math.max(iArr[0], f5040a);
        f5041b = new C0900f(max, max);
    }

    private C0955b() {
    }

    private static int m7542a(int i, int i2, int i3, boolean z) {
        int a = f5041b.m7206a();
        int b = f5041b.m7209b();
        while (true) {
            if (i / i3 <= a && i2 / i3 <= b) {
                return i3;
            }
            i3 = z ? i3 * 2 : i3 + 1;
        }
    }

    public static int m7543a(C0900f c0900f) {
        int a = c0900f.m7206a();
        int b = c0900f.m7209b();
        return Math.max((int) Math.ceil((double) (((float) a) / ((float) f5041b.m7206a()))), (int) Math.ceil((double) (((float) b) / ((float) f5041b.m7209b()))));
    }

    public static int m7544a(C0900f c0900f, C0900f c0900f2, C0903i c0903i, boolean z) {
        int max;
        int i = 1;
        int a = c0900f.m7206a();
        int b = c0900f.m7209b();
        int a2 = c0900f2.m7206a();
        int b2 = c0900f2.m7209b();
        int i2;
        int i3;
        switch (C0956c.f5042a[c0903i.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                if (!z) {
                    max = Math.max(a / a2, b / b2);
                    break;
                }
                i2 = a / 2;
                i3 = b / 2;
                max = 1;
                while (true) {
                    if (i2 / max <= a2 && i3 / max <= b2) {
                        break;
                    }
                    max *= 2;
                }
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (!z) {
                    max = Math.min(a / a2, b / b2);
                    break;
                }
                i2 = a / 2;
                i3 = b / 2;
                max = 1;
                while (i2 / max > a2 && i3 / max > b2) {
                    max *= 2;
                }
                break;
            default:
                max = 1;
                break;
        }
        if (max >= 1) {
            i = max;
        }
        return C0955b.m7542a(a, b, i, z);
    }

    public static C0900f m7545a(C0925a c0925a, C0900f c0900f) {
        int a = c0925a.m7313a();
        if (a <= 0) {
            a = c0900f.m7206a();
        }
        int b = c0925a.m7316b();
        if (b <= 0) {
            b = c0900f.m7209b();
        }
        return new C0900f(a, b);
    }

    public static float m7546b(C0900f c0900f, C0900f c0900f2, C0903i c0903i, boolean z) {
        int i;
        int a = c0900f.m7206a();
        int b = c0900f.m7209b();
        int a2 = c0900f2.m7206a();
        int b2 = c0900f2.m7209b();
        float f = ((float) a) / ((float) a2);
        float f2 = ((float) b) / ((float) b2);
        if ((c0903i != C0903i.FIT_INSIDE || f < f2) && (c0903i != C0903i.CROP || f >= f2)) {
            i = (int) (((float) a) / f2);
            a2 = b2;
        } else {
            i = a2;
            a2 = (int) (((float) b) / f);
        }
        return ((z || i >= a || a2 >= b) && (!z || i == a || a2 == b)) ? C2020f.f10933c : ((float) i) / ((float) a);
    }
}
