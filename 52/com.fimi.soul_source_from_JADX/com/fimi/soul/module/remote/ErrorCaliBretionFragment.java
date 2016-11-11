package com.fimi.soul.module.remote;

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
import com.fimi.soul.utils.ay;
import com.fimi.soul.utils.be;
import it.p074a.p075a.C2799f;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class ErrorCaliBretionFragment extends BaseRemoteFragment {
    ay f8929f;
    Runnable f8930g;
    private TextView f8931h;
    private Button f8932i;
    private Button f8933j;
    private Button f8934k;
    private Button f8935l;
    private TextView f8936m;
    private TextView f8937n;
    private ImageView f8938o;
    private int f8939p;
    private boolean f8940q;

    public ErrorCaliBretionFragment() {
        this.f8930g = new C1822c(this);
    }

    private void m11556a() {
        this.f8933j.setVisibility(0);
        this.f8934k.setVisibility(0);
        this.f8935l.setVisibility(8);
    }

    private void m11557b() {
        this.f8933j.setVisibility(8);
        this.f8934k.setVisibility(8);
        this.f8935l.setVisibility(0);
    }

    public void m11558a(Button button, int i) {
        button.setTextColor(button.getTextColors().withAlpha(i));
    }

    public void m11559a(String str, boolean z) {
        this.f8940q = z;
        LayoutParams layoutParams = (LayoutParams) this.f8938o.getLayoutParams();
        if (true == z) {
            this.f8936m.setText(C1205R.string.calisucess);
            this.f8937n.setText(C1205R.string.caliremotesucess);
            this.f8937n.setVisibility(4);
            layoutParams.setMargins(0, (int) (((double) this.f8939p) * 0.1111d), 0, 0);
            this.f8938o.setLayoutParams(layoutParams);
            this.f8938o.setImageResource(C1205R.drawable.icon_calibration_succeed);
            m11557b();
            return;
        }
        this.f8936m.setText(C1205R.string.califail);
        this.f8937n.setVisibility(0);
        if (str == null || C2915a.f14760f.equals(str)) {
            this.f8937n.setText(C1205R.string.caliremotefail);
        } else {
            this.f8937n.setText(str);
        }
        layoutParams.setMargins(0, (int) (((double) this.f8939p) * 0.12592d), 0, 0);
        this.f8938o.setLayoutParams(layoutParams);
        this.f8938o.setImageResource(C1205R.drawable.icon_calibration_defeat);
        m11556a();
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case C1205R.id.quit:
                this.d.m10838n();
                this.d.m10837m();
                getActivity().finish();
            case C1205R.id.again:
                if (this.e != null) {
                    this.e.m11561a(C1205R.id.errorcalifragment, C1205R.id.remote_roller_fragment);
                }
            case C1205R.id.sucesscomplete:
                getActivity().finish();
                new Handler().post(this.f8930g);
            case C1205R.id.black_btn:
                getActivity().finish();
            default:
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.f8939p = displayMetrics.heightPixels;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.caliremotesucess, null);
        this.f8938o = (ImageView) inflate.findViewById(C1205R.id.tagIcon);
        this.f8931h = (TextView) inflate.findViewById(C1205R.id.heardView).findViewById(C1205R.id.tv_settingTitle);
        this.f8931h.setText(C1205R.string.caliremotesucess);
        this.f8932i = (Button) inflate.findViewById(C1205R.id.heardView).findViewById(C1205R.id.black_btn);
        this.f8932i.setOnClickListener(this);
        this.f8933j = (Button) inflate.findViewById(C1205R.id.quit);
        this.f8933j.setOnClickListener(this);
        this.f8934k = (Button) inflate.findViewById(C1205R.id.again);
        this.f8934k.setOnClickListener(this);
        this.f8935l = (Button) inflate.findViewById(C1205R.id.sucesscomplete);
        this.f8935l.setOnClickListener(this);
        this.f8936m = (TextView) inflate.findViewById(C1205R.id.destitle);
        this.f8937n = (TextView) inflate.findViewById(C1205R.id.calireason);
        this.f8929f = new ay(getActivity());
        be.m12359a(getActivity().getAssets(), this.f8931h, this.f8932i, this.f8933j, this.f8934k, this.f8935l, this.f8936m, this.f8937n);
        m11558a(this.f8933j, (int) SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        m11558a(this.f8934k, (int) C2799f.f14256F);
        return inflate;
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z) {
            if (this.f8940q) {
                C1160b.m7953b(getActivity()).m7959a(getString(C1205R.string.calisucess));
            } else {
                C1160b.m7953b(getActivity()).m7959a(getString(C1205R.string.calibration_failure));
            }
        }
    }
}
