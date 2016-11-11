package it.sephiroth.android.library.p143a;

import java.util.Random;

/* renamed from: it.sephiroth.android.library.a.a */
public final class C2814a {
    private static final Random f14318a;
    private static final float f14319b = 0.017453292f;
    private static final float f14320c = 57.295784f;

    static {
        f14318a = new Random();
    }

    private C2814a() {
    }

    public static float m16008a(float f) {
        return f > 0.0f ? f : -f;
    }

    public static float m16009a(float f, float f2) {
        return (float) Math.pow((double) f, (double) f2);
    }

    public static float m16010a(float f, float f2, float f3) {
        return f < f2 ? f2 : f > f3 ? f3 : f;
    }

    public static float m16011a(float f, float f2, float f3, float f4) {
        float f5 = f3 - f;
        float f6 = f4 - f2;
        return (float) Math.sqrt((double) ((f5 * f5) + (f6 * f6)));
    }

    public static float m16012a(float f, float f2, float f3, float f4, float f5) {
        return ((f3 - f4) * ((f5 - f) / (f2 - f))) + f3;
    }

    public static float m16013a(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = f4 - f;
        float f8 = f5 - f2;
        float f9 = f6 - f3;
        return (float) Math.sqrt((double) (((f7 * f7) + (f8 * f8)) + (f9 * f9)));
    }

    public static float m16014a(int i, int i2) {
        return i > i2 ? (float) i : (float) i2;
    }

    public static int m16015a(int i) {
        return (int) (f14318a.nextFloat() * ((float) i));
    }

    public static int m16016a(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    public static long m16017a(long j, long j2, long j3) {
        return j < j2 ? j2 : j > j3 ? j3 : j;
    }

    public static void m16018a(long j) {
        f14318a.setSeed(j);
    }

    public static float m16019b(float f) {
        return (float) Math.log((double) f);
    }

    public static float m16020b(float f, float f2) {
        return f > f2 ? f : f2;
    }

    public static float m16021b(float f, float f2, float f3) {
        return f > f2 ? f > f3 ? f : f3 : f2 > f3 ? f2 : f3;
    }

    public static float m16022b(int i, int i2) {
        return i < i2 ? (float) i : (float) i2;
    }

    public static float m16023b(int i, int i2, int i3) {
        if (i > i2) {
            if (i <= i3) {
                i = i3;
            }
            return (float) i;
        }
        if (i2 <= i3) {
            i2 = i3;
        }
        return (float) i2;
    }

    public static float m16024c(float f) {
        return (float) Math.exp((double) f);
    }

    public static float m16025c(float f, float f2) {
        return f < f2 ? f : f2;
    }

    public static float m16026c(float f, float f2, float f3) {
        return f < f2 ? f < f3 ? f : f3 : f2 < f3 ? f2 : f3;
    }

    public static float m16027c(int i, int i2, int i3) {
        if (i < i2) {
            if (i >= i3) {
                i = i3;
            }
            return (float) i;
        }
        if (i2 >= i3) {
            i2 = i3;
        }
        return (float) i2;
    }

    public static int m16028c(int i, int i2) {
        return i >= i2 ? i : (int) ((f14318a.nextFloat() * ((float) (i2 - i))) + ((float) i));
    }

    public static float m16029d(float f) {
        return f * f;
    }

    public static float m16030d(float f, float f2) {
        return (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
    }

    public static float m16031d(float f, float f2, float f3) {
        return (float) Math.sqrt((double) (((f * f) + (f2 * f2)) + (f3 * f3)));
    }

    public static float m16032e(float f) {
        return f14319b * f;
    }

    public static float m16033e(float f, float f2) {
        return (float) Math.atan2((double) f, (double) f2);
    }

    public static float m16034e(float f, float f2, float f3) {
        return ((f2 - f) * f3) + f;
    }

    public static float m16035f(float f) {
        return f14320c * f;
    }

    public static float m16036f(float f, float f2) {
        return f >= f2 ? f : f + (f14318a.nextFloat() * (f2 - f));
    }

    public static float m16037f(float f, float f2, float f3) {
        return (f3 - f) / (f2 - f);
    }

    public static float m16038g(float f) {
        return (float) Math.acos((double) f);
    }

    public static float m16039h(float f) {
        return (float) Math.asin((double) f);
    }

    public static float m16040i(float f) {
        return (float) Math.atan((double) f);
    }

    public static float m16041j(float f) {
        return (float) Math.tan((double) f);
    }

    public static float m16042k(float f) {
        return f14318a.nextFloat() * f;
    }
}
