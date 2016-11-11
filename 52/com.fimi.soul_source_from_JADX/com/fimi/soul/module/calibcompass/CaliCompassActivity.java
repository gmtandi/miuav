package com.fimi.soul.module.calibcompass;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p117h.C1578u;
import com.fimi.soul.module.p091a.C1664h;
import com.fimi.soul.view.aq;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import org.codehaus.jackson.smile.SmileConstants;

public class CaliCompassActivity extends BaseActivity implements C1667a, C1668d, C1669f, C1670m {
    private CaliComPassFirstFragment f7876a;
    private CaliCompassSecondFragment f7877b;
    private CaliCompassErrorFragment f7878c;
    private CaliComPassThirdFragment f7879d;
    private CaliCompassFourFragment f7880e;
    private CaliCompassErrorVertialFragment f7881f;
    private CaliCompassStatusFragment f7882g;
    private FragmentManager f7883h;
    private boolean f7884i;
    private boolean f7885j;
    private boolean f7886k;
    private boolean f7887l;
    private final int f7888m;
    private final int f7889n;
    private final int f7890o;
    private boolean f7891p;
    private Handler f7892q;
    private boolean f7893r;

    public CaliCompassActivity() {
        this.f7886k = true;
        this.f7888m = 11;
        this.f7889n = 12;
        this.f7890o = 13;
        this.f7892q = new C1674g(this);
        this.f7893r = false;
    }

    private void m10857a(int i, String str, boolean z, boolean z2) {
        if (this.f7886k) {
            for (Fragment fragment : this.f7883h.getFragments()) {
                if (fragment != null && fragment.isVisible()) {
                    Fragment findFragmentByTag = this.f7883h.findFragmentByTag(str);
                    if ((findFragmentByTag instanceof CaliCompassErrorFragment) && findFragmentByTag.isHidden()) {
                        ((CaliCompassErrorFragment) findFragmentByTag).m10874a(getString(i), z, z2);
                        this.f7883h.beginTransaction().hide(fragment).show(findFragmentByTag).commitAllowingStateLoss();
                    }
                }
            }
        }
        this.f7882g.m10884a(false);
    }

    private void m10858a(Bundle bundle) {
        this.f7883h = getSupportFragmentManager();
        if (bundle == null) {
            this.f7882g = (CaliCompassStatusFragment) this.f7883h.findFragmentById(C1205R.id.compass_begin);
            if (this.f7882g == null) {
                this.f7882g = new CaliCompassStatusFragment();
            }
            this.f7883h.beginTransaction().add(C1205R.id.compass_begin, this.f7882g, "begin").commitAllowingStateLoss();
            this.f7876a = (CaliComPassFirstFragment) this.f7883h.findFragmentById(C1205R.id.compass_first);
            if (this.f7876a == null) {
                this.f7876a = new CaliComPassFirstFragment();
            }
            this.f7883h.beginTransaction().add(C1205R.id.compass_first, this.f7876a, "first").hide(this.f7876a).commitAllowingStateLoss();
            this.f7877b = (CaliCompassSecondFragment) this.f7883h.findFragmentById(C1205R.id.compass_second);
            if (this.f7877b == null) {
                this.f7877b = new CaliCompassSecondFragment();
            }
            this.f7883h.beginTransaction().add(C1205R.id.compass_second, this.f7877b, "second").hide(this.f7877b).commitAllowingStateLoss();
            this.f7878c = (CaliCompassErrorFragment) this.f7883h.findFragmentById(C1205R.id.compass_error);
            if (this.f7878c == null) {
                this.f7878c = new CaliCompassErrorFragment();
                this.f7878c.m10875a(this.f7884i);
            }
            this.f7883h.beginTransaction().add(C1205R.id.compass_error, this.f7878c, XiaomiOAuthConstants.EXTRA_ERROR_CODE_2).hide(this.f7878c).commitAllowingStateLoss();
            this.f7879d = (CaliComPassThirdFragment) this.f7883h.findFragmentById(C1205R.id.compass_thrid);
            if (this.f7879d == null) {
                this.f7879d = new CaliComPassThirdFragment();
            }
            this.f7883h.beginTransaction().add(C1205R.id.compass_thrid, this.f7879d, "thrid").hide(this.f7879d).commitAllowingStateLoss();
            this.f7880e = (CaliCompassFourFragment) this.f7883h.findFragmentById(C1205R.id.compass_four);
            if (this.f7880e == null) {
                this.f7880e = new CaliCompassFourFragment();
            }
            this.f7880e.m10880a(this.f7884i);
            this.f7883h.beginTransaction().add(C1205R.id.compass_four, this.f7880e, "four").hide(this.f7880e).commitAllowingStateLoss();
            this.f7881f = (CaliCompassErrorVertialFragment) this.f7883h.findFragmentById(C1205R.id.compass_error_vertail);
            if (this.f7881f == null) {
                this.f7881f = new CaliCompassErrorVertialFragment();
            }
            this.f7883h.beginTransaction().add(C1205R.id.compass_error_vertail, this.f7881f, "errortratil").hide(this.f7881f).commitAllowingStateLoss();
        }
    }

    public void m10863a() {
        new aq(this).m12748a(getString(C1205R.string.interruptcaliremote)).m12753b(getString(C1205R.string.ensure), new C1676i(this)).m12749a(getString(C1205R.string.cancel), new C1675h(this)).m12746a().show();
    }

    public void m10864a(boolean z) {
        if (!z && this.f7892q.hasMessages(13)) {
            this.f7892q.removeMessages(13);
        }
        this.f7893r = z;
    }

    public void m10865b() {
    }

    public void m10866c() {
        this.f7885j = true;
        this.f7883h.beginTransaction().hide(this.f7878c).show(this.f7883h.findFragmentByTag("begin")).commitAllowingStateLoss();
    }

    public void m10867d() {
        if (this.f7880e != null) {
            this.f7880e.m10879a();
        }
    }

    protected void onCreate(Bundle bundle) {
        getWindow().setFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        super.onCreate(bundle);
        setContentView(C1205R.layout.calicompassmain);
        this.f7884i = getIntent().getBooleanExtra(C1236a.f5594R, false);
        m10858a(bundle);
        this.dpa.m8523b((Activity) this);
        this.f7892q.sendEmptyMessageDelayed(12, 300);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f7893r = false;
        if (this.speakTTs != null) {
            this.speakTTs.m7966e();
        }
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        super.onDroneEvent(c1584h, c1433a);
        switch (C1677j.f7935a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                if (!(c1433a.m9570Q() || this.f7882g == null || this.f7882g.isVisible())) {
                    if (this.f7893r) {
                        if (!this.f7892q.hasMessages(13)) {
                            this.f7892q.sendEmptyMessageDelayed(13, 10000);
                            break;
                        }
                    }
                    m10857a(C1205R.string.GC_caliFail_discon_drone, XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, false, false);
                    break;
                }
                break;
        }
        if (!(c1584h != C1584h.CLEANALLOBJ || this.f7882g == null || this.f7882g.isVisible())) {
            m10857a(C1205R.string.GC_caliFail_discon, XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, false, false);
        }
        if (this.f7882g != null && !this.f7882g.isVisible() && c1584h == C1584h.CaliCompass) {
            C1578u h = c1433a.m9605h();
            if (this.f7891p && ((h.m10605d() == (byte) 1 || h.m10605d() == 2) && h.m10607e() == (byte) 1 && h.m10601b() == (byte) 1 && h.m10603c() == 3)) {
                m10857a(C1205R.string.calierrordata, XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, false, false);
            } else if (this.f7885j && ((h.m10605d() == (byte) 1 || h.m10605d() == null) && h.m10607e() == (byte) 1 && h.m10601b() == (byte) 1 && (h.m10603c() == (byte) 1 || h.m10603c() == null))) {
                this.f7885j = false;
                this.f7886k = true;
                C1664h.m10813a(c1433a).m10816a((byte) 1, (byte) 1, (byte) 1);
            } else if (h.m10603c() == 4) {
                if (this.f7887l) {
                    m10857a(C1205R.string.calierror, XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, false, true);
                } else {
                    m10857a(C1205R.string.calierror, XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, false, false);
                }
            } else if (h.m10603c() == 5) {
                m10857a(0, "errortratil", false, false);
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || this.f7882g == null || this.f7882g.isVisible() || this.f7878c == null || this.f7878c.isVisible()) {
            return super.onKeyDown(i, keyEvent);
        }
        m10863a();
        return true;
    }
}
