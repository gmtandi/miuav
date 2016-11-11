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
import android.widget.TextView;
import com.fimi.kernel.view.progress.ProgressView;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.p096d.C1325k;
import com.fimi.soul.drone.p107c.p108a.p109a.C1453p;
import com.fimi.soul.utils.be;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class PairResultFragment extends Fragment implements OnClickListener, C1812a {
    RelativeLayout f8887a;
    RelativeLayout f8888b;
    RelativeLayout f8889c;
    public int f8890d;
    TextView f8891e;
    TextView f8892f;
    TextView f8893g;
    Button f8894h;
    Button f8895i;
    ProgressView f8896j;
    PairCodeActivity f8897k;
    View f8898l;
    private final int f8899m;
    private final int f8900n;
    private final int f8901o;
    private final int f8902p;
    private final int f8903q;

    public PairResultFragment() {
        this.f8899m = 2;
        this.f8900n = 3;
        this.f8901o = 4;
        this.f8902p = -1;
        this.f8903q = 0;
        this.f8890d = 0;
    }

    private void m11550a(View view) {
        this.f8888b = (RelativeLayout) view.findViewById(C1205R.id.pair_success);
        this.f8887a = (RelativeLayout) view.findViewById(C1205R.id.pair_fail);
        this.f8889c = (RelativeLayout) view.findViewById(C1205R.id.pair_progress);
        this.f8896j = (ProgressView) this.f8898l.findViewById(C1205R.id.progress_v);
        this.f8896j.setFrontColor(getResources().getColor(C1205R.color.white_half));
        this.f8896j.setMaxCount(100.0f);
        this.f8896j.setCurrentCount(0.0f);
        this.f8891e = (TextView) this.f8898l.findViewById(C1205R.id.connect_des);
        this.f8892f = (TextView) this.f8898l.findViewById(C1205R.id.connect_des_tip);
        be.m12359a(getActivity().getAssets(), this.f8891e, this.f8892f);
        this.f8893g = (TextView) this.f8898l.findViewById(C1205R.id.pair_index);
        be.m12368b(getActivity().getAssets(), this.f8893g);
        this.f8894h = (Button) this.f8898l.findViewById(C1205R.id.exit_btn);
        this.f8895i = (Button) this.f8898l.findViewById(C1205R.id.repair_btn);
        this.f8894h.setOnClickListener(this);
        this.f8895i.setOnClickListener(this);
    }

    private void m11551a(com.fimi.soul.module.paircode.C1819h r5) {
        /* JADX: method processing error */
/*
        Error: java.util.ConcurrentModificationException
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901)
	at java.util.ArrayList$Itr.next(ArrayList.java:851)
	at jadx.core.dex.visitors.ReSugarCode.getEnumMap(ReSugarCode.java:171)
	at jadx.core.dex.visitors.ReSugarCode.processEnumSwitch(ReSugarCode.java:123)
	at jadx.core.dex.visitors.ReSugarCode.process(ReSugarCode.java:68)
	at jadx.core.dex.visitors.ReSugarCode.visit(ReSugarCode.java:52)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
        /*
        r4 = this;
        r3 = 0;
        r2 = 8;
        r0 = r4.f8888b;
        r0.setVisibility(r2);
        r0 = r4.f8887a;
        r0.setVisibility(r2);
        r0 = r4.f8889c;
        r0.setVisibility(r2);
        r0 = com.fimi.soul.module.paircode.C1818g.f8912a;
        r1 = r5.ordinal();
        r0 = r0[r1];
        switch(r0) {
            case 1: goto L_0x001e;
            case 2: goto L_0x006c;
            case 3: goto L_0x0072;
            default: goto L_0x001d;
        };
    L_0x001d:
        return;
    L_0x001e:
        r0 = r4.f8889c;
        r0.setVisibility(r3);
        r0 = r4.f8890d;
        r1 = -1;
        if (r0 == r1) goto L_0x0059;
    L_0x0028:
        r0 = r4.f8890d;
        r1 = 100;
        if (r0 != r1) goto L_0x0045;
    L_0x002e:
        r0 = r4.f8889c;
        r0.setVisibility(r2);
        r0 = r4.f8888b;
        r0.setVisibility(r2);
        r0 = r4.f8887a;
        r0.setVisibility(r3);
        r0 = r4.getActivity();
        r0.finish();
        goto L_0x001d;
    L_0x0045:
        r0 = r4.f8896j;
        r1 = r4.f8890d;
        r1 = (float) r1;
        r0.setCurrentCount(r1);
        r0 = r4.f8893g;
        r1 = r4.f8890d;
        r1 = java.lang.String.valueOf(r1);
        r0.setText(r1);
        goto L_0x001d;
    L_0x0059:
        r0 = r4.getActivity();
        r1 = r4.getResources();
        r2 = 2131558963; // 0x7f0d0233 float:1.8743257E38 double:1.0531300557E-314;
        r1 = r1.getString(r2);
        com.fimi.kernel.p084e.ak.m8084a(r0, r1);
        goto L_0x001d;
    L_0x006c:
        r0 = r4.f8888b;
        r0.setVisibility(r3);
        goto L_0x001d;
    L_0x0072:
        r0 = r4.getActivity();
        r1 = r4.getResources();
        r2 = 2131558958; // 0x7f0d022e float:1.8743246E38 double:1.053130053E-314;
        r1 = r1.getString(r2);
        com.fimi.kernel.p084e.ak.m8084a(r0, r1);
        goto L_0x001d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fimi.soul.module.paircode.PairResultFragment.a(com.fimi.soul.module.paircode.h):void");
    }

    public void m11552a(int i) {
    }

    public void m11553a(int i, int i2) {
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f8890d = i2;
                m11551a(C1819h.success);
            case Type.BYTE /*3*/:
                m11551a(C1819h.fail);
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                m11551a(C1819h.busy);
            default:
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f8897k = (PairCodeActivity) activity;
        this.f8897k.m11542a(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.exit_btn:
                getActivity().finish();
            case C1205R.id.repair_btn:
                C1453p c1453p = new C1453p();
                c1453p.m9785a(C1325k.m8930a().m8942g());
                this.f8897k.drone.m9569P().m9993a(c1453p.m9782a());
            default:
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f8898l = layoutInflater.inflate(C1205R.layout.pair_rt_layout, null);
        m11550a(this.f8898l);
        return this.f8898l;
    }
}
