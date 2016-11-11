package com.fimi.soul.module.login;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.kernel.p084e.ak;
import com.fimi.kernel.view.progress.ProgressView;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p096d.C1325k;
import com.fimi.soul.biz.update.C1412i;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.droneconnection.connection.p111a.C1497a;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.module.droneui.FlightActivity;
import com.fimi.soul.module.update.C1789m;
import com.fimi.soul.module.update.UpdateConnectDefeaFrgment;
import com.fimi.soul.module.update.UpgradingActivity;
import com.fimi.soul.service.UsbStatus;
import com.fimi.soul.utils.ay;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.BreathLeapView;
import com.fimi.soul.view.TranslationView;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.p054c.p055a.p063b.p068d.C0921a;
import it.p074a.p075a.C2799f;
import java.util.concurrent.Executors;
import org.codehaus.jackson.smile.SmileConstants;

public class LoginActivity extends BaseActivity implements C1234i, C1787p, C1788y, C1789m {
    private static final int f8771A = 8;
    private static final int f8772J = 0;
    private static final int f8773l = 0;
    private static final int f8774m = 1;
    private static final int f8775r = 2;
    private static final int f8776s = 3;
    private static final int f8777t = 0;
    private static final int f8778u = 1;
    private static final int f8779v = 2;
    private static final int f8780w = 3;
    private static final int f8781x = 4;
    private boolean f8782B;
    private boolean f8783C;
    private final int f8784D;
    private int f8785E;
    private ImageView f8786F;
    private ValueAnimator f8787G;
    private C1412i f8788H;
    private int f8789I;
    private final Handler f8790K;
    private Runnable f8791L;
    private Runnable f8792M;
    private Runnable f8793N;
    boolean f8794a;
    boolean f8795b;
    C1325k f8796c;
    private UpdateConnectDefeaFrgment f8797d;
    private LoginFragment f8798e;
    private UsbConnectFragment f8799f;
    private ImageView f8800g;
    private AnimationDrawable f8801h;
    private TranslationView f8802i;
    private BreathLeapView f8803j;
    private int f8804k;
    private ProgressView f8805n;
    private final int f8806o;
    private ValueAnimator f8807p;
    private TextView f8808q;
    private boolean f8809y;
    private int f8810z;

    public LoginActivity() {
        this.f8804k = f8777t;
        this.f8794a = true;
        this.f8806o = C2799f.f14282t;
        this.f8810z = f8777t;
        this.f8782B = false;
        this.f8783C = false;
        this.f8784D = C0921a.f4833b;
        this.f8785E = f8777t;
        this.f8789I = f8777t;
        this.f8790K = new C1792d(this);
        this.f8791L = new C1801m(this);
        this.f8792M = new C1802n(this);
        this.f8793N = new C1793e(this);
    }

    private void m11486a(String str, String str2) {
        boolean g = m11496g();
        if (this.f8797d == null) {
            this.f8797d = new UpdateConnectDefeaFrgment();
        }
        if (!this.f8783C) {
            if (g) {
                getFragmentManager().beginTransaction().show(this.f8797d);
            } else {
                getFragmentManager().beginTransaction().add(C1205R.id.main_layout, this.f8797d).commitAllowingStateLoss();
            }
            this.f8797d.m11951a(str);
            this.f8797d.m11953b(str2);
            this.f8797d.m11955c(getString(C1205R.string.no_connect));
            this.f8797d.m11956c(false);
            this.f8797d.m11952a(false);
            getFragmentManager().beginTransaction().setCustomAnimations(17432576, 17432577);
        }
    }

    private void m11494f() {
        new C1795g(this).executeOnExecutor(Executors.newCachedThreadPool(), new Void[f8777t]);
    }

    private boolean m11496g() {
        try {
            this.f8797d = (UpdateConnectDefeaFrgment) getFragmentManager().findFragmentById(C1205R.id.main_layout);
            if (this.f8797d != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void m11497h() {
        C1497a.m9894a(C1543c.f7229d);
        this.drone.m9569P().m9996b();
    }

    private void m11500i() {
        if (this.f8809y) {
            finish();
            return;
        }
        this.f8809y = true;
        ak.m8083a(getApplicationContext(), (int) C1205R.string.again_exit, ak.f5302b);
        this.f8790K.sendEmptyMessageDelayed(f8780w, 2000);
    }

    private void m11502j() {
        startActivity(new Intent(this, FlightActivity.class));
        finish();
    }

    public void m11513a() {
        if (this.f8801h != null) {
            this.f8810z = f8777t;
            this.f8801h.stop();
            this.f8801h.start();
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, C2020f.f10933c});
            ofFloat.addUpdateListener(new C1800l(this));
            ofFloat.setDuration(720);
            ofFloat.start();
            getWindow().setFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            this.f8790K.postDelayed(this.f8791L, 1100);
        }
    }

    public void m11514b() {
        this.f8810z = f8778u;
        if (this.f8800g != null) {
            this.f8800g.setVisibility(f8771A);
        }
        this.f8802i.setVisibility(f8777t);
        this.f8802i.m12660a((int) C2799f.f14263a);
        this.f8790K.sendEmptyMessageDelayed(f8781x, 500);
        this.f8804k = f8777t;
        this.f8786F.setAlpha(0.0f);
        getFragmentManager().beginTransaction().remove(this.f8799f).commitAllowingStateLoss();
    }

    public void m11515c() {
        if (this.f8782B) {
            ay.m12293a((Context) this).edit().putBoolean(C1236a.f5586J, false);
            this.dpa.m8525d();
            return;
        }
        startActivity(new Intent(this, FlightActivity.class));
    }

    public void m11516d() {
        if (this.f8782B) {
            ay.m12293a((Context) this).edit().putBoolean(C1236a.f5586J, false);
            this.dpa.m8525d();
            return;
        }
        startActivity(new Intent(this, FlightActivity.class));
    }

    public void m11517e() {
        this.f8810z = f8777t;
        getFragmentManager().beginTransaction().remove(this.f8797d).commit();
        this.f8794a = true;
        this.f8808q.setVisibility(f8781x);
        this.f8805n.setVisibility(f8781x);
        this.f8803j.setAlpha(0.0f);
        this.f8802i.setVisibility(f8777t);
        this.f8802i.m12659a();
        getFragmentManager().beginTransaction().replace(C1205R.id.main_layout, this.f8799f).commit();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 0) {
            this.f8794a = true;
            this.f8808q.setVisibility(f8781x);
            this.f8805n.setVisibility(f8781x);
            if (this.f8803j != null) {
                this.f8803j.setAlpha(0.0f);
            }
            if (this.f8800g != null) {
                this.f8800g.setVisibility(f8781x);
            }
            this.f8802i.setVisibility(f8777t);
            this.f8802i.m12659a();
            getFragmentManager().beginTransaction().replace(C1205R.id.main_layout, this.f8799f).commit();
        }
        super.onActivityResult(i, i2, intent);
    }

    @TargetApi(16)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.activity_login);
        this.dpa.m8523b((Activity) this);
        startService(new Intent(this, UsbStatus.class));
        this.f8786F = (ImageView) findViewById(C1205R.id.login_mask_iv);
        this.f8802i = (TranslationView) findViewById(C1205R.id.translationview);
        this.f8798e = new LoginFragment();
        this.f8799f = new UsbConnectFragment();
        this.f8796c = C1325k.m8930a();
        if (getIntent().getBooleanExtra("islaunchexit", false)) {
            this.f8788H = new C1412i(this);
            this.f8788H.m9460a((int) f8777t);
            this.f8788H.m9462a(new C1796h(this));
        }
        this.f8782B = ay.m12293a((Context) this).getBoolean(C1236a.f5588L, false);
        if (getIntent().getBooleanExtra("login", true)) {
            ((ViewStub) findViewById(C1205R.id.login_animation_vs)).inflate();
            this.f8800g = (ImageView) findViewById(C1205R.id.login_animation_iv);
            this.f8800g.setVisibility(f8777t);
            this.f8810z = f8779v;
            getFragmentManager().beginTransaction().replace(C1205R.id.main_layout, this.f8798e).commit();
            this.f8786F.setAlpha(0.0f);
        } else {
            this.f8810z = f8777t;
            this.f8802i.setVisibility(f8777t);
            this.f8802i.m12659a();
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("isenterLoginFragment", true);
            this.f8799f.setArguments(bundle2);
            getFragmentManager().beginTransaction().replace(C1205R.id.main_layout, this.f8799f).commit();
            getWindow().setFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            this.f8786F.setAlpha(C2020f.f10933c);
        }
        this.f8807p = ValueAnimator.ofFloat(new float[]{0.0f, 200.0f});
        this.f8807p.setDuration(20000);
        this.f8807p.addUpdateListener(new C1797i(this));
        this.f8787G = ValueAnimator.ofFloat(new float[]{0.0f, C2020f.f10933c});
        this.f8787G.setDuration(200);
        this.f8787G.addUpdateListener(new C1798j(this));
        new IntentFilter().addAction("exit");
        this.drone.m9590a((C1234i) this);
        this.f8805n = (ProgressView) findViewById(C1205R.id.checkFirmwareprogress);
        this.f8805n.setMaxCount(200.0f);
        this.f8808q = (TextView) findViewById(C1205R.id.checking_hard_tv);
        AssetManager assets = getAssets();
        View[] viewArr = new View[f8778u];
        viewArr[f8777t] = this.f8808q;
        be.m12359a(assets, viewArr);
        this.f8790K.postDelayed(new C1799k(this), 500);
        this.f8789I = getIntent().getIntExtra(UpgradingActivity.f9750a, f8777t);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f8783C = true;
        if (!(this.f8802i == null || this.f8803j == null)) {
            this.f8802i.m12662c();
            this.f8803j.m12572a();
        }
        this.drone.m9594b((C1234i) this);
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        int i = C1794f.f8850a[c1584h.ordinal()];
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == f8781x) {
            switch (this.f8810z) {
                case f8777t /*0*/:
                    if (this.f8782B) {
                        finish();
                        return false;
                    }
                    m11500i();
                    return false;
                case f8778u /*1*/:
                    if (this.f8787G.isRunning()) {
                        this.f8787G.cancel();
                    }
                    if (this.f8807p.isRunning()) {
                        this.f8807p.cancel();
                    }
                    this.f8810z = f8777t;
                    this.f8794a = true;
                    this.f8790K.removeCallbacks(this.f8792M);
                    this.f8790K.removeCallbacks(this.f8793N);
                    this.f8808q.setVisibility(f8781x);
                    this.f8805n.setVisibility(f8781x);
                    this.f8803j.setAlpha(0.0f);
                    getFragmentManager().beginTransaction().replace(C1205R.id.main_layout, this.f8799f).commit();
                    this.f8802i.setVisibility(f8777t);
                    this.f8802i.m12661b();
                    this.f8802i.m12659a();
                    return false;
                case f8779v /*2*/:
                    if (this.f8782B) {
                        finish();
                        return false;
                    }
                    m11500i();
                    return false;
                case f8780w /*3*/:
                    this.f8810z = f8777t;
                    getFragmentManager().beginTransaction().remove(this.f8797d).commit();
                    this.f8794a = true;
                    this.f8808q.setVisibility(f8781x);
                    this.f8805n.setVisibility(f8781x);
                    this.f8803j.setAlpha(0.0f);
                    this.f8802i.setVisibility(f8777t);
                    this.f8802i.m12659a();
                    getFragmentManager().beginTransaction().replace(C1205R.id.main_layout, this.f8799f).commit();
                    return false;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    protected void onResume() {
        super.onResume();
        if (this.f8789I == 20) {
            m11486a(getString(C1205R.string.phone_unconect_flight), getString(C1205R.string.remote_noconnect_plane));
        }
    }

    protected void onStart() {
        super.onStart();
        this.f8783C = false;
        if (this.f8810z == f8778u) {
            this.f8790K.post(this.f8792M);
        }
    }

    protected void onStop() {
        super.onStop();
        this.f8783C = true;
        this.f8790K.removeCallbacks(this.f8792M);
        this.f8790K.removeCallbacks(this.f8791L);
        if (this.f8788H != null && this.f8788H.m9464b() != null) {
            this.f8788H.m9462a(null);
        }
    }
}
