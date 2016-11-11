package com.fimi.soul.module.calibcompass;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.kernel.p083d.C1160b;
import com.fimi.kernel.view.percent.PercentRelativeLayout.LayoutParams;
import com.fimi.soul.C1205R;
import com.fimi.soul.module.p091a.C1664h;
import com.fimi.soul.utils.be;
import it.p074a.p075a.C2799f;
import org.codehaus.jackson.smile.SmileConstants;

public class CaliCompassErrorFragment extends BaseCaliCompassFragment {
    C1670m f7894f;
    private TextView f7895g;
    private Button f7896h;
    private Button f7897i;
    private Button f7898j;
    private Button f7899k;
    private ImageView f7900l;
    private TextView f7901m;
    private TextView f7902n;
    private boolean f7903o;
    private boolean f7904p;
    private boolean f7905q;
    private Handler f7906r;
    private Runnable f7907s;
    private int f7908t;

    public CaliCompassErrorFragment() {
        this.f7906r = new Handler();
        this.f7907s = new C1678k(this);
    }

    private void m10868a(View view) {
        this.f7900l = (ImageView) view.findViewById(C1205R.id.tagIcon);
        this.f7895g = (TextView) view.findViewById(C1205R.id.heardView).findViewById(C1205R.id.tv_settingTitle);
        this.f7895g.setText(C1205R.string.calicompassesucess);
        this.f7896h = (Button) view.findViewById(C1205R.id.heardView).findViewById(C1205R.id.black_btn);
        this.f7896h.setOnClickListener(this);
        this.f7897i = (Button) view.findViewById(C1205R.id.quit);
        this.f7897i.setOnClickListener(this);
        this.f7898j = (Button) view.findViewById(C1205R.id.again);
        this.f7898j.setOnClickListener(this);
        this.f7899k = (Button) view.findViewById(C1205R.id.sucesscomplete);
        this.f7899k.setOnClickListener(this);
        this.f7901m = (TextView) view.findViewById(C1205R.id.destitle);
        this.f7902n = (TextView) view.findViewById(C1205R.id.calireason);
        m10873a(this.f7897i, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        m10873a(this.f7898j, C2799f.f14256F);
        be.m12359a(getActivity().getAssets(), this.f7895g, this.f7896h, this.f7897i, this.f7898j, this.f7899k, this.f7901m, this.f7902n);
    }

    private void m10869b() {
        this.f7897i.setVisibility(0);
        this.f7898j.setVisibility(0);
        this.f7899k.setVisibility(8);
    }

    private void m10870c() {
        this.f7897i.setVisibility(8);
        this.f7898j.setVisibility(8);
        this.f7899k.setVisibility(0);
    }

    private void m10871d() {
        new C1679l(this).execute(new Void[0]);
    }

    public void m10872a() {
        C1664h.m10813a(this.b).m10816a((byte) 1, (byte) 1, (byte) 4);
        getActivity().finish();
    }

    public void m10873a(Button button, int i) {
        button.setTextColor(button.getTextColors().withAlpha(i));
    }

    public void m10874a(String str, boolean z, boolean z2) {
        this.f7904p = z;
        this.f7905q = z2;
        if (true == z) {
            this.f7901m.setText(C1205R.string.calisucess);
            this.f7902n.setText(str);
            LayoutParams layoutParams = (LayoutParams) this.f7900l.getLayoutParams();
            layoutParams.setMargins(0, (int) (((float) this.f7908t) * 0.1111f), 0, 0);
            this.f7900l.setLayoutParams(layoutParams);
            this.f7900l.setImageResource(C1205R.drawable.icon_calibration_succeed);
            m10870c();
            m10871d();
            return;
        }
        this.f7901m.setText(C1205R.string.califail);
        this.f7902n.setText(str);
        layoutParams = (LayoutParams) this.f7900l.getLayoutParams();
        layoutParams.setMargins(0, (int) (((float) this.f7908t) * 0.12592f), 0, 0);
        this.f7900l.setLayoutParams(layoutParams);
        this.f7900l.setImageResource(C1205R.drawable.icon_calibration_defeat);
        m10869b();
    }

    public void m10875a(boolean z) {
        this.f7903o = z;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f7894f = (C1670m) activity;
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case C1205R.id.quit:
                if (this.f7894f != null) {
                    this.f7894f.m10855d();
                }
            case C1205R.id.again:
                if (this.f7894f != null) {
                    this.f7894f.m10854c();
                }
            case C1205R.id.sucesscomplete:
                this.f7906r.postDelayed(this.f7907s, 300);
            case C1205R.id.black_btn:
                this.f7906r.postDelayed(this.f7907s, 300);
            default:
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.f7908t = displayMetrics.heightPixels;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.caliremotesucess, null);
        m10868a(inflate);
        return inflate;
    }

    public void onDetach() {
        super.onDetach();
        if (this.f7894f != null) {
            this.f7894f = null;
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z && isVisible()) {
            if (this.f7904p) {
                C1160b.m7953b(getActivity()).m7959a(getString(C1205R.string.calisucess));
            } else if (!this.f7905q) {
                C1160b.m7953b(getActivity()).m7959a(getString(C1205R.string.calibration_failure));
            }
        }
    }
}
