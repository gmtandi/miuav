package com.fimi.soul.module.calibcompass;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.kernel.p083d.C1160b;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p117h.C1578u;
import com.fimi.soul.module.p091a.C1664h;
import com.fimi.soul.utils.be;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import it.p074a.p075a.C2799f;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class CaliCompassStatusFragment extends BaseCaliCompassFragment {
    private TextView f7924f;
    private TextView f7925g;
    private Button f7926h;
    private Button f7927i;
    private boolean f7928j;

    public void m10882a(int i, boolean z) {
        if (!getString(i).toString().equals(this.f7925g.getText().toString())) {
            this.f7925g.setText(i);
        }
        if (this.f7927i.isEnabled() != z && z) {
            C1160b.m7953b(getActivity()).m7959a(getString(C1205R.string.reminder_calibration_canStart));
        }
        this.f7927i.setEnabled(z);
        if (z) {
            m10883a(this.f7927i, (int) C2799f.f14256F);
        } else {
            m10883a(this.f7927i, 77);
        }
    }

    public void m10883a(Button button, int i) {
        button.setTextColor(button.getTextColors().withAlpha(i));
    }

    public void m10884a(boolean z) {
        this.f7927i.setEnabled(z);
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case C1205R.id.begincalicompss:
                C1664h.m10813a(this.b).m10816a((byte) 1, (byte) 1, (byte) 1);
                this.c.beginTransaction().hide(this).show(this.c.findFragmentByTag("first")).commitAllowingStateLoss();
            case C1205R.id.black_btn:
                getActivity().finish();
            default:
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.begin_calicaompass, null);
        this.f7924f = (TextView) inflate.findViewById(C1205R.id.tv_settingTitle);
        this.f7924f.setText(C1205R.string.compass_calibration);
        this.f7925g = (TextView) inflate.findViewById(C1205R.id.showtitleone);
        this.f7926h = (Button) inflate.findViewById(C1205R.id.black_btn);
        this.f7926h.setOnClickListener(this);
        this.f7927i = (Button) inflate.findViewById(C1205R.id.begincalicompss);
        this.f7927i.setOnClickListener(this);
        this.f7927i.setEnabled(false);
        be.m12359a(getActivity().getAssets(), this.f7926h, this.f7924f, this.f7925g);
        if (!this.b.m9569P().m9995a() || this.b.aa()) {
            if (this.b.aa()) {
                m10882a((int) C1205R.string.plane_start_can_not_calration_compass, false);
            } else {
                m10882a((int) C1205R.string.calidisconremote, false);
            }
        } else if (this.b.ab().ab()) {
            m10882a((int) C1205R.string.calicpmssing, false);
            C1160b.m7953b(getActivity()).m7959a(getString(C1205R.string.calicpmssing));
        } else {
            m10882a((int) C1205R.string.begincalocomremider, true);
        }
        return inflate;
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        super.onDroneEvent(c1584h, c1433a);
        if (isVisible()) {
            switch (C1682p.f7940a[c1584h.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    C1578u h = c1433a.m9605h();
                    if (h.m10605d() != (byte) 2 || h.m10607e() != (byte) 1 || h.m10601b() != (byte) 1) {
                        return;
                    }
                    if (h.m10603c() != (byte) 2 && h.m10603c() != 3) {
                        return;
                    }
                    if (h.m10603c() == (byte) 2) {
                        Fragment findFragmentByTag = this.c.findFragmentByTag(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2);
                        if (findFragmentByTag != null && (findFragmentByTag instanceof CaliCompassErrorFragment) && !findFragmentByTag.isHidden()) {
                            ((CaliCompassErrorFragment) findFragmentByTag).m10874a(getString(C1205R.string.calicomsucess), true, false);
                            this.c.beginTransaction().hide(this).show(findFragmentByTag).commitAllowingStateLoss();
                            return;
                        }
                        return;
                    }
                    this.f7928j = true;
                    m10882a((int) C1205R.string.calicompasscoming, false);
                case Type.BYTE /*3*/:
                    if (!c1433a.m9570Q() && this.f7928j) {
                        this.f7928j = false;
                    }
                    if (!this.f7928j) {
                        if (!c1433a.m9570Q()) {
                            m10882a((int) C1205R.string.calisiacondrone, false);
                        } else if (c1433a.m9570Q() && c1433a.aa()) {
                            m10882a((int) C1205R.string.plane_start_can_not_calration_compass, false);
                        } else if (!c1433a.m9570Q()) {
                        } else {
                            if (c1433a.ab().ab()) {
                                m10882a((int) C1205R.string.calicpmssing, false);
                            } else {
                                m10882a((int) C1205R.string.begincalocomremider, true);
                            }
                        }
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    m10882a((int) C1205R.string.GC_caliFail_discon, false);
                default:
            }
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z) {
            if (this.b.m9569P().m9995a() && !this.b.aa()) {
                return;
            }
            if (this.b.aa()) {
                m10882a((int) C1205R.string.plane_start_can_not_calration_compass, false);
            } else {
                m10882a((int) C1205R.string.calidisconremote, false);
            }
        }
    }
}
