package com.fimi.kernel.p084e;

import java.text.DecimalFormat;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.kernel.e.ab */
public class ab {
    public static double m8015a(double d, int i) {
        if (i != 1) {
            return i == 2 ? Double.valueOf(new DecimalFormat("0.00").format(d)).doubleValue() : i == 7 ? Double.valueOf(new DecimalFormat("0.0000000").format(d)).doubleValue() : d;
        } else {
            try {
                return Double.valueOf(new DecimalFormat("0.0").format(d)).doubleValue();
            } catch (Exception e) {
                e.printStackTrace();
                return d;
            }
        }
    }

    public static String m8016b(double d, int i) {
        return i == 1 ? new DecimalFormat("0.0").format(d) : i == 2 ? new DecimalFormat("0.00").format(d) : i == 7 ? new DecimalFormat("0.0000000").format(d) : d + C2915a.f14760f;
    }
}
