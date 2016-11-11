package com.fimi.soul.module.flyplannermedia;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1313t;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.fimi.soul.biz.camera.p088b.C1213e;
import it.p074a.p075a.C2799f;

public class DroneMediaTestActivity extends BaseActivity implements C1213e<X11RespCmd> {
    C1313t f8678a;
    long f8679b;

    public DroneMediaTestActivity() {
        this.f8679b = 0;
    }

    public void m11425a(boolean z, X11RespCmd x11RespCmd) {
        Log.d("Good", x11RespCmd.getErrorMsg());
        switch (x11RespCmd.getMsg_id()) {
            case C2799f.f14258H /*257*/:
                this.f8678a.m8874s().m8778d();
            case C1314u.f5865R /*1283*/:
                getViewManager().m8341a("\u5207\u6362\u76ee\u5f55\u6210\u529f\uff01");
                this.f8678a.m8874s().m8782f();
            default:
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View button = new Button(this);
        button.setText("\u83b7\u53d6IDR");
        button.setOnClickListener(new C1772l(this));
        setContentView(button);
        this.f8678a = (C1313t) C1276b.m8680a().m8699d();
        this.f8678a.m8831a(new C1773m(this));
        if (!this.f8678a.m8848d()) {
            this.f8678a.m8875t().m8790b();
        }
        this.f8678a.m8832a((C1213e) this);
        this.f8678a.m8845c(new C1774n(this));
    }
}
