package com.fimi.soul.module.setting.GimalCalibration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.kernel.p083d.C1160b;
import com.fimi.kernel.view.percent.PercentRelativeLayout;
import com.fimi.kernel.view.progress.ProgressView;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.aq;
import it.p074a.p075a.C2799f;
import org.codehaus.jackson.smile.SmileConstants;

public class GimalCalibrationActivity extends BaseActivity implements OnClickListener, C1839a, C1840f {
    TextView f9084a;
    TextView f9085b;
    Button f9086c;
    Button f9087d;
    ProgressView f9088e;
    C1843d f9089f;
    private ImageView f9090g;
    private GimalCaliIngFragment f9091h;
    private FragmentManager f9092i;
    private PercentRelativeLayout f9093j;
    private boolean f9094k;

    private void m11595a(TextView textView, int i, Button button, int i2, ImageView imageView, int i3) {
        imageView.setImageResource(i3);
        if (!textView.getText().toString().equals(getString(i))) {
            textView.setText(i);
        }
        button.setEnabled(false);
        m11601a(button, i2);
    }

    private void m11596e() {
        this.f9090g = (ImageView) findViewById(C1205R.id.img_calibration_plane);
        this.f9084a = (TextView) findViewById(C1205R.id.tv_tips);
        this.f9086c = (Button) findViewById(C1205R.id.btn_start_calibration);
        this.f9086c.setOnClickListener(this);
        this.f9086c.setEnabled(false);
        this.f9085b = (TextView) findViewById(C1205R.id.heardView).findViewById(C1205R.id.tv_settingTitle);
        this.f9085b.setText(C1205R.string.gimbal_calibration);
        this.f9087d = (Button) findViewById(C1205R.id.heardView).findViewById(C1205R.id.black_btn);
        this.f9087d.setOnClickListener(this);
        this.f9088e = (ProgressView) findViewById(C1205R.id.pro_calibration);
        this.f9088e.setMaxCount((float) C1843d.f9110a);
        be.m12359a(getAssets(), this.f9084a, this.f9086c, this.f9085b);
        this.f9089f = new C1843d(this, this.drone);
        m11597f();
    }

    private void m11597f() {
        if (!this.drone.m9569P().m9995a()) {
            m11595a(this.f9084a, C1205R.string.calidisconremote, this.f9086c, 75, this.f9090g, C1205R.drawable.gimbal_calibration_plane);
        } else if (!this.drone.m9570Q()) {
            m11595a(this.f9084a, C1205R.string.calisiacondrone, this.f9086c, 75, this.f9090g, C1205R.drawable.gimbal_calibration_plane);
        } else if (this.drone.m9570Q() && this.drone.aa()) {
            m11595a(this.f9084a, C1205R.string.caligcremider, this.f9086c, 75, this.f9090g, C1205R.drawable.gimbal_calibration_plane);
        } else if (this.drone.ab().ab()) {
            m11595a(this.f9084a, C1205R.string.calicompasscoming, this.f9086c, 75, this.f9090g, C1205R.drawable.gimbal_calibration_plane);
        } else if (this.drone.ab().m10225u()) {
            m11595a(this.f9084a, C1205R.string.discongc, this.f9086c, 75, this.f9090g, C1205R.drawable.img_no_pan_tilt_calibration_plane);
        } else if (this.drone.ab().m10193R()) {
            m11595a(this.f9084a, C1205R.string.gcpreheat, this.f9086c, 75, this.f9090g, C1205R.drawable.gimbal_calibration_plane);
        } else if (this.drone.ab().m10224t()) {
            m11595a(this.f9084a, C1205R.string.gcunready, this.f9086c, 75, this.f9090g, C1205R.drawable.gimbal_calibration_plane);
        } else if (this.drone.ab().m10180E()) {
            m11595a(this.f9084a, C1205R.string.compasscgerror, this.f9086c, 75, this.f9090g, C1205R.drawable.img_pan_tilt_calibration_jiggly);
        } else {
            if (!this.f9086c.isEnabled()) {
                C1160b.m7953b((Context) this).m7959a(getResources().getString(C1205R.string.reminder_calibration_canStart));
            }
            this.f9084a.setText(m11598g());
            this.f9086c.setEnabled(true);
            m11601a(this.f9086c, C2799f.f14256F);
            this.f9090g.setImageResource(C1205R.drawable.imh_pan_tilt_calibration_plane);
        }
    }

    private SpannableString m11598g() {
        Object string = getString(C1205R.string.gc_calbration_tips);
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(C1205R.color.gc_calibration_tips)), 0, 11, 33);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(C1205R.color.white)), 11, 15, 33);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(C1205R.color.gc_calibration_tips)), 16, string.length(), 33);
        return spannableString;
    }

    public void m11599a() {
        if (this.f9091h != null && this.f9091h.isVisible()) {
            aq aqVar = new aq(this);
            aqVar.m12748a(getString(C1205R.string.interruptcaliremote));
            aqVar.m12753b(getString(C1205R.string.cancel), new C1842c(this)).m12749a(getString(C1205R.string.ensure), new C1841b(this)).m12746a().show();
        }
    }

    public void m11600a(int i) {
        if (this.f9091h != null) {
            this.f9091h.m11587a(i);
        }
        if (!this.f9094k) {
            this.f9094k = true;
            C1160b.m7953b((Context) this).m7959a(getString(C1205R.string.GC_calibrationing));
        }
    }

    public void m11601a(TextView textView, int i) {
        textView.setTextColor(textView.getTextColors().withAlpha(i));
    }

    public void m11602a(Class cls, int i, String str) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(ResultCalibrationActivity.f9097c, i);
        intent.putExtra(ResultCalibrationActivity.f9098d, str);
        startActivity(intent);
        finish();
        overridePendingTransition(17432576, 17432577);
    }

    public void m11603a(String str) {
        m11602a(ResultCalibrationActivity.class, 1, str);
    }

    public void m11604b() {
        if (this.f9091h != null && !this.f9091h.isVisible()) {
            this.f9092i.beginTransaction().show(this.f9091h).commitAllowingStateLoss();
            this.f9093j.setVisibility(8);
        }
    }

    public void m11605b(int i) {
        m11595a(this.f9084a, i, this.f9086c, 75, this.f9090g, C1205R.drawable.gimbal_calibration_plane);
    }

    public void m11606c() {
        m11602a(ResultCalibrationActivity.class, 0, getString(C1205R.string.GC_caliSucess));
    }

    public void m11607d() {
        m11597f();
    }

    public void onClick(View view) {
        if (view == this.f9086c) {
            this.f9089f.m11616a();
        }
        if (view == this.f9087d) {
            finish();
        }
    }

    public void onCreate(Bundle bundle) {
        getWindow().setFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        super.onCreate(bundle);
        setContentView(C1205R.layout.activity_gc_calibration);
        this.f9093j = (PercentRelativeLayout) findViewById(C1205R.id.activity_PL);
        this.f9092i = getSupportFragmentManager();
        this.f9091h = (GimalCaliIngFragment) this.f9092i.findFragmentById(C1205R.id.caligamble);
        if (this.f9091h == null) {
            this.f9091h = new GimalCaliIngFragment();
        }
        if (this.f9091h.isAdded()) {
            this.f9092i.beginTransaction().hide(this.f9091h).commitAllowingStateLoss();
        } else {
            this.f9092i.beginTransaction().add((int) C1205R.id.caligamble, this.f9091h).hide(this.f9091h).commitAllowingStateLoss();
        }
        m11596e();
        this.f9094k = false;
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f9089f.m11624g();
        this.f9089f.m11623f();
        if (this.f9089f != null) {
            this.f9089f = null;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || this.f9091h == null || !this.f9091h.isVisible()) {
            return super.onKeyDown(i, keyEvent);
        }
        m11599a();
        return true;
    }

    protected void onStart() {
        super.onStart();
        this.f9089f.m11622e();
    }

    protected void onStop() {
        super.onStop();
    }
}
