package com.fimi.soul.module.droneFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.biz.p090b.C1253k;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.drone.p117h.p118a.C1552a;
import com.fimi.soul.view.ad;
import com.tencent.mm.sdk.platformtools.Util;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class DroneTakePhotoFragment extends Fragment implements C1234i {
    private bm f7943a;
    private bj f7944b;
    private C1433a f7945c;
    private SharedPreferences f7946d;
    private Dialog f7947e;
    private int f7948f;
    private double f7949g;

    private void m10889a(String str) {
        if (this.f7947e != null && this.f7947e.isShowing()) {
            this.f7947e.dismiss();
            this.f7947e = null;
        }
        ad adVar = new ad(getActivity());
        adVar.m12735a(str);
        adVar.m12736a(getString(C1205R.string.finish), new C1693g(this));
        this.f7947e = adVar.m12733a();
        this.f7947e.setCanceledOnTouchOutside(false);
        this.f7947e.show();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f7945c = ((DroidPlannerApp) activity.getApplication()).f5570a;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f7946d = com.fimi.kernel.p084e.ad.m8019a(getActivity());
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.drone_takephone, null);
        this.f7943a = new bm(inflate, this.f7945c);
        this.f7944b = new bj(inflate);
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        this.f7945c.m9594b((C1234i) this);
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        switch (C1694h.f8283a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                if (C1253k.m8598a(getActivity().getApplicationContext()).m8599a().get() == 4) {
                    this.f7943a.m11061b();
                }
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                short b = c1433a.m9568O().m10269b();
                if (this.f7948f - b < 0) {
                    this.f7949g = (double) (((255 - b) * 90) / Util.MASK_8BIT);
                } else {
                    this.f7949g = (double) (90 - ((b * 90) / Util.MASK_8BIT));
                }
                this.f7948f = b;
                this.f7943a.m11058a((int) this.f7949g);
            case Type.BYTE /*3*/:
                this.f7943a.m11057a();
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                C1552a an = c1433a.an();
                if (an.m10147j() == 19) {
                    this.f7943a.m11060a(an);
                }
            default:
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!isAdded()) {
            return;
        }
        if (z) {
            if (this.f7947e != null) {
                this.f7947e.dismiss();
            }
            this.f7944b.m11042a();
            this.f7943a.m11065f();
            return;
        }
        if (!this.f7946d.getBoolean(C1543c.bF, false)) {
            m10889a(getString(C1205R.string.fly_take_photo_tip));
        }
        this.f7943a.m11063d();
        this.f7943a.m11064e();
        this.f7943a.m11067h();
        this.f7944b.m11043b();
        this.f7945c.m9589a(C1584h.CLEARMARKERTAKEPHOTO);
        this.f7945c.m9589a(C1584h.CLEARDATA);
    }

    public void onStart() {
        super.onStart();
        this.f7945c.m9590a((C1234i) this);
    }
}
