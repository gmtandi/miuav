package com.p016a;

import com.tencent.connect.common.Constants;

/* renamed from: com.a.ce */
public class ce {
    public double f736a;
    public double f737b;
    public int f738c;
    public String f739d;
    public int f740e;
    final /* synthetic */ cb f741f;

    public ce(cb cbVar, double d, double d2) {
        this.f741f = cbVar;
        this.f736a = 0.0d;
        this.f737b = 0.0d;
        this.f738c = 0;
        this.f739d = Constants.VIA_RESULT_SUCCESS;
        this.f740e = -1;
        this.f736a = d;
        this.f737b = d2;
    }

    public ce(cb cbVar, double d, double d2, int i, int i2) {
        this.f741f = cbVar;
        this.f736a = 0.0d;
        this.f737b = 0.0d;
        this.f738c = 0;
        this.f739d = Constants.VIA_RESULT_SUCCESS;
        this.f740e = -1;
        this.f736a = d;
        this.f737b = d2;
        this.f738c = i;
        this.f740e = i2;
    }

    public ce(cb cbVar, double d, double d2, int i, String str, double d3, int i2) {
        this.f741f = cbVar;
        this.f736a = 0.0d;
        this.f737b = 0.0d;
        this.f738c = 0;
        this.f739d = Constants.VIA_RESULT_SUCCESS;
        this.f740e = -1;
        this.f736a = d;
        this.f737b = d2;
        this.f739d = str;
        this.f738c = i;
        this.f740e = i2;
    }

    private boolean m1289a(ce ceVar) {
        boolean z = false;
        double b = m1291b(ceVar);
        if (b < 500.0d) {
            return true;
        }
        if ((this.f740e > 0 && ceVar.f740e == 0) || (this.f740e == 0 && ceVar.f740e > 0)) {
            return (this.f740e == 1 || ceVar.f740e == 1) ? b < 3000.0d && (b < ((double) this.f738c) * 1.5d || b < ((double) ceVar.f738c) * 1.5d) : b < 5000.0d;
        } else {
            if (this.f740e > 0 || ceVar.f740e > 0) {
                if (b < 5000.0d || b < ((double) this.f738c) || b < ((double) ceVar.f738c)) {
                    z = true;
                }
                return z;
            }
            if (b < ((double) this.f738c) || b < ((double) ceVar.f738c) || b < 500.0d) {
                z = true;
            }
            return z;
        }
    }

    private double m1291b(ce ceVar) {
        double d = 6356725.0d + ((21412.0d * (90.0d - this.f736a)) / 90.0d);
        double cos = (Math.cos((this.f736a * 3.1415926535898d) / 180.0d) * d) * (((ceVar.f737b - this.f737b) * 3.1415926535898d) / 180.0d);
        d *= ((ceVar.f736a - this.f736a) * 3.1415926535898d) / 180.0d;
        return Math.sqrt((d * d) + (cos * cos));
    }
}
