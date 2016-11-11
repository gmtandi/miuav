package com.fimi.soul.module.setting.GimalCalibration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.kernel.p083d.C1160b;
import com.fimi.kernel.view.percent.PercentRelativeLayout.LayoutParams;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.utils.be;
import it.p074a.p075a.C2799f;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class ResultCalibrationActivity extends BaseActivity implements OnClickListener {
    public static final int f9095a = 0;
    public static final int f9096b = 1;
    public static final String f9097c = "calibration_result";
    public static final String f9098d = "calibration_result_reson";
    private TextView f9099e;
    private Button f9100f;
    private Button f9101g;
    private Button f9102h;
    private Button f9103i;
    private ImageView f9104j;
    private TextView f9105k;
    private TextView f9106l;
    private int f9107m;

    private void m11608a() {
        Intent intent = getIntent();
        if (intent != null) {
            int intExtra = intent.getIntExtra(f9097c, f9095a);
            CharSequence stringExtra = intent.getStringExtra(f9098d);
            LayoutParams layoutParams;
            if (intExtra == f9096b) {
                this.f9105k.setText(C1205R.string.califail);
                C1160b.m7953b((Context) this).m7959a(getString(C1205R.string.calibration_failure));
                if (stringExtra != null) {
                    this.f9106l.setText(stringExtra);
                }
                layoutParams = (LayoutParams) this.f9104j.getLayoutParams();
                layoutParams.setMargins(f9095a, (int) (((float) this.f9107m) * 0.12592f), f9095a, f9095a);
                this.f9104j.setLayoutParams(layoutParams);
                this.f9104j.setImageResource(C1205R.drawable.icon_calibration_defeat);
                m11610c();
                return;
            }
            this.f9105k.setText(getString(C1205R.string.calisucess));
            C1160b.m7953b((Context) this).m7959a(getString(C1205R.string.calisucess));
            if (stringExtra != null) {
                this.f9106l.setText(C2915a.f14760f);
            }
            layoutParams = (LayoutParams) this.f9104j.getLayoutParams();
            layoutParams.setMargins(f9095a, (int) (((float) this.f9107m) * 0.1111f), f9095a, f9095a);
            this.f9104j.setLayoutParams(layoutParams);
            this.f9104j.setImageResource(C1205R.drawable.icon_calibration_succeed);
            m11611d();
        }
    }

    private void m11609b() {
        this.f9104j = (ImageView) findViewById(C1205R.id.tagIcon);
        this.f9099e = (TextView) findViewById(C1205R.id.heardView).findViewById(C1205R.id.tv_settingTitle);
        this.f9099e.setText(C1205R.string.gccalisucess);
        this.f9100f = (Button) findViewById(C1205R.id.heardView).findViewById(C1205R.id.black_btn);
        this.f9100f.setOnClickListener(this);
        this.f9101g = (Button) findViewById(C1205R.id.quit);
        this.f9101g.setOnClickListener(this);
        this.f9102h = (Button) findViewById(C1205R.id.again);
        this.f9102h.setOnClickListener(this);
        this.f9103i = (Button) findViewById(C1205R.id.sucesscomplete);
        this.f9103i.setOnClickListener(this);
        this.f9105k = (TextView) findViewById(C1205R.id.destitle);
        this.f9106l = (TextView) findViewById(C1205R.id.calireason);
        m11612a(this.f9101g, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        m11612a(this.f9102h, C2799f.f14256F);
        be.m12359a(getAssets(), this.f9099e, this.f9100f, this.f9101g, this.f9102h, this.f9103i, this.f9105k, this.f9106l);
    }

    private void m11610c() {
        this.f9101g.setVisibility(f9095a);
        this.f9102h.setVisibility(f9095a);
        this.f9103i.setVisibility(8);
    }

    private void m11611d() {
        this.f9101g.setVisibility(8);
        this.f9102h.setVisibility(8);
        this.f9103i.setVisibility(f9095a);
    }

    public void m11612a(Button button, int i) {
        button.setTextColor(button.getTextColors().withAlpha(i));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.quit:
                finish();
            case C1205R.id.again:
                startActivity(new Intent(this, GimalCalibrationActivity.class));
                finish();
            case C1205R.id.sucesscomplete:
                finish();
            case C1205R.id.black_btn:
                finish();
            default:
        }
    }

    public void onCreate(Bundle bundle) {
        getWindow().setFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        super.onCreate(bundle);
        setContentView(C1205R.layout.caliremotesucess);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.f9107m = displayMetrics.heightPixels;
        m11609b();
        m11608a();
    }
}
