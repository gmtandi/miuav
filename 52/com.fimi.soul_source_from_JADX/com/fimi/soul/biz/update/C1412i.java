package com.fimi.soul.biz.update;

import android.content.Context;
import com.fimi.kernel.C1189f;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.p103k.ay;
import com.fimi.soul.view.aq;
import com.xiaomi.market.sdk.UpdateResponse;
import com.xiaomi.market.sdk.XiaomiUpdateAgent;

/* renamed from: com.fimi.soul.biz.update.i */
public class C1412i {
    private Context f6358a;
    private C1417n f6359b;
    private C1418o f6360c;
    private ay f6361d;
    private aq f6362e;

    public C1412i(Context context) {
        this.f6361d = ay.m9251a(context);
        this.f6358a = context;
    }

    private void m9456a(UpdateResponse updateResponse, String str) {
        if (C1189f.m8334d() != null) {
            this.f6358a = C1189f.m8334d();
            if (this.f6362e == null) {
                this.f6362e = new aq(this.f6358a);
            }
            this.f6362e.m12753b(this.f6358a.getString(C1205R.string.updates), new C1416m(this, updateResponse)).m12747a(this.f6358a.getResources().getColor(C1205R.color.dialog_update_right_text)).m12748a(String.format(this.f6358a.getString(C1205R.string.findupdate), new Object[]{updateResponse.versionName})).m12749a(this.f6358a.getString(C1205R.string.cancel), new C1415l(this)).m12752b(str);
            if (!this.f6362e.m12750b().isShowing()) {
                this.f6362e.m12746a().show();
            }
        }
    }

    public void m9459a() {
        XiaomiUpdateAgent.setUpdateAutoPopup(false);
        XiaomiUpdateAgent.setUpdateListener(new C1414k(this));
        XiaomiUpdateAgent.update(this.f6358a);
    }

    public void m9460a(int i) {
        XiaomiUpdateAgent.setUpdateAutoPopup(false);
        XiaomiUpdateAgent.setUpdateListener(new C1413j(this, i));
        XiaomiUpdateAgent.update(this.f6358a);
    }

    public void m9461a(C1417n c1417n) {
        this.f6359b = c1417n;
    }

    public void m9462a(C1418o c1418o) {
        this.f6360c = c1418o;
    }

    public void m9463a(UpdateResponse updateResponse) {
        m9456a(updateResponse, updateResponse.updateLog);
    }

    public C1418o m9464b() {
        return this.f6360c;
    }
}
