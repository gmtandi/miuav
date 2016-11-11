package com.fimi.soul.module.paircode;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.p096d.C1325k;
import com.fimi.soul.drone.p107c.p108a.p109a.C1453p;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class PairFragment extends Fragment implements OnClickListener, C1812a {
    public static final int f8880a = 2;
    public static final int f8881b = 16;
    View f8882c;
    private RelativeLayout f8883d;
    private RelativeLayout f8884e;
    private RelativeLayout f8885f;
    private PairCodeActivity f8886g;

    private void m11546a() {
        C1453p c1453p = new C1453p();
        c1453p.m9785a(C1325k.m8930a().m8942g());
        this.f8886g.drone.m9569P().m9993a(c1453p.m9782a());
    }

    private void m11547a(C1817f c1817f) {
        this.f8883d.setVisibility(8);
        this.f8884e.setVisibility(8);
        this.f8885f.setVisibility(8);
        switch (C1816e.f8907a[c1817f.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f8883d.setVisibility(0);
            case f8880a /*2*/:
                this.f8884e.setVisibility(0);
            case Type.BYTE /*3*/:
                this.f8885f.setVisibility(0);
                ((Button) this.f8882c.findViewById(C1205R.id.pre_pair_btn)).setOnClickListener(this);
            default:
        }
    }

    public void m11548a(int i) {
        switch (i) {
            case f8881b /*16*/:
                if (this.f8886g.drone.aa()) {
                    m11547a(C1817f.in_sky);
                } else {
                    m11547a(C1817f.prepared);
                }
            default:
        }
    }

    public void m11549a(int i, int i2) {
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f8886g = (PairCodeActivity) activity;
        this.f8886g.m11542a(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.pre_pair_btn:
                m11546a();
            default:
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f8882c = layoutInflater.inflate(C1205R.layout.pair_status_layout, null);
        this.f8883d = (RelativeLayout) this.f8882c.findViewById(C1205R.id.sky_layout);
        this.f8884e = (RelativeLayout) this.f8882c.findViewById(C1205R.id.connect_layout);
        this.f8885f = (RelativeLayout) this.f8882c.findViewById(C1205R.id.prepare_layout);
        return this.f8882c;
    }

    public void onResume() {
        super.onResume();
        if (!this.f8886g.drone.m9569P().m9995a()) {
            m11547a(C1817f.no_connected);
        } else if (this.f8886g.drone.aa()) {
            m11547a(C1817f.in_sky);
        } else {
            m11547a(C1817f.prepared);
        }
    }
}
