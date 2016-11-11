package com.fimi.soul.p087b;

import com.fimi.kernel.p076b.p078b.C1113b;
import com.fimi.kernel.p076b.p078b.C1115d;
import com.fimi.kernel.p076b.p078b.C1119h;
import com.fimi.kernel.p076b.p078b.C1122k;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.b.n */
class C1225n implements C1119h {
    final /* synthetic */ C1224m f5537a;

    C1225n(C1224m c1224m) {
        this.f5537a = c1224m;
    }

    public void m8502a(C1115d c1115d, C1113b c1113b) {
        if (c1113b.m7788j() != null) {
            int intValue = ((Integer) c1113b.m7788j()).intValue();
            C1231t c1231t = (C1231t) this.f5537a.f5533v.get(Integer.valueOf(intValue));
            if (c1231t.f5559l == intValue) {
                C1122k a = C1122k.m7798a(this.f5537a.f5519h);
                if (c1115d != C1115d.Error) {
                    long k = c1113b.m7789k() / (c1113b.m7787i() / 100);
                    String replace = c1231t.f5555h.getText().toString().substring(3).replace("%", C2915a.f14760f);
                    if (!be.m12383e(replace) || replace.equals(C2915a.f14760f)) {
                        c1231t.f5555h.setText(String.format(this.f5537a.f5519h.getString(C1205R.string.downing_media), new Object[]{k + "%"}));
                        c1231t.f5552e.setVisibility(0);
                        c1231t.f5552e.setSweepAngle((float) k);
                        this.f5537a.m8468a(c1115d, c1231t);
                        return;
                    }
                    long parseLong = Long.parseLong(replace);
                    if (c1113b.m7785g() == C1115d.Completed) {
                        this.f5537a.m8468a(c1113b.m7785g(), c1231t);
                        if (c1113b.m7792n() != null) {
                            String replace2 = c1113b.m7786h().replace("file://", C2915a.f14760f);
                            this.f5537a.m8487a(replace2, c1113b.m7792n());
                            this.f5537a.m8467a(c1231t.f5549b, replace2, c1113b.m7792n());
                            if (c1113b.m7773a() != null) {
                                this.f5537a.f5532u.put("getdur_local" + replace2, c1113b.m7773a());
                                c1231t.f5553f.setVisibility(0);
                                c1231t.f5553f.setText(c1113b.m7773a());
                            }
                        }
                        a.m7809a(c1113b);
                        a.m7804a();
                    } else if (parseLong != k) {
                        if (c1113b.m7773a() != null) {
                            c1231t.f5553f.setVisibility(0);
                            c1231t.f5553f.setText(c1113b.m7773a());
                        }
                        c1231t.f5555h.setVisibility(0);
                        c1231t.f5555h.setText(String.format(this.f5537a.f5519h.getString(C1205R.string.downing_media), new Object[]{k + "%"}));
                        c1231t.f5552e.setVisibility(0);
                        c1231t.f5552e.setSweepAngle((float) k);
                        this.f5537a.m8468a(c1115d, c1231t);
                    }
                } else if (c1113b != null) {
                    a.m7811c();
                    this.f5537a.m8468a(c1115d, c1231t);
                }
            }
        }
    }
}
