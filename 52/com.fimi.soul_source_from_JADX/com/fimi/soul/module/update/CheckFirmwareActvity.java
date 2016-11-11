package com.fimi.soul.module.update;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.fimi.kernel.p082c.C1157c;
import com.fimi.kernel.view.progress.ProgressView;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1313t;
import com.fimi.soul.biz.p096d.C1316b;
import com.fimi.soul.biz.p096d.C1325k;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p117h.aa;
import com.fimi.soul.entity.FirmwareInfo;
import com.fimi.soul.entity.UpdateVersonBean;
import com.fimi.soul.module.calibcompass.CaliCompassActivity;
import com.fimi.soul.module.droneui.C1686j;
import com.fimi.soul.module.droneui.C1746g;
import com.fimi.soul.module.droneui.FlightActivity;
import com.fimi.soul.module.update.p121a.C1901a;
import com.fimi.soul.module.update.p121a.C1907g;
import com.fimi.soul.utils.ay;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.photodraweeview.C2020f;
import it.p074a.p075a.C2799f;
import java.util.List;
import org.codehaus.jackson.smile.SmileConstants;

public class CheckFirmwareActvity extends BaseActivity implements C1234i, C1789m {
    List<FirmwareInfo> f9652a;
    C1313t f9653b;
    C1907g f9654c;
    C1325k f9655d;
    Handler f9656e;
    Runnable f9657f;
    int f9658g;
    private ProgressView f9659h;
    private boolean f9660i;
    private final int f9661j;
    private final int f9662k;
    private final int f9663l;
    private final int f9664m;
    private UpdateConnectDefeaFrgment f9665n;
    private AutoSelfErrorFrgment f9666o;
    private float f9667p;
    private final int f9668q;
    private TextView f9669r;
    private boolean f9670s;
    private C1746g f9671t;
    private C1316b f9672u;
    private C1433a f9673v;
    private boolean f9674w;
    private ProgressBar f9675x;
    private boolean f9676y;

    public CheckFirmwareActvity() {
        this.f9660i = false;
        this.f9661j = C2799f.f14282t;
        this.f9662k = 10;
        this.f9663l = 1;
        this.f9664m = 2;
        this.f9667p = 0.0f;
        this.f9668q = 1536;
        this.f9670s = false;
        this.f9652a = null;
        this.f9672u = null;
        this.f9674w = false;
        this.f9656e = new C1911c(this);
        this.f9657f = new C1912d(this);
        this.f9658g = 0;
    }

    private void m11916a(aa aaVar) {
        String string;
        int i = 5;
        String string2 = getString(C1205R.string.self_error_result);
        if (this.f9673v.ad()) {
        }
        if (aaVar.m10218n()) {
            string = getString(C1205R.string.self_error_battery_copyright);
            i = 1;
        } else if (aaVar.m10217m()) {
            string = getString(C1205R.string.self_error_battery);
            i = 2;
        } else if (aaVar.m10198W()) {
            string = getString(C1205R.string.fault_short_life_battery);
            i = 3;
        } else if (aaVar.m10183H()) {
            string = getString(C1205R.string.fault_low_battery);
            i = 4;
        } else if (aaVar.m10210f()) {
            string = getString(C1205R.string.land_GPS_ERROR);
        } else if (aaVar.m10214j()) {
            string = getString(C1205R.string.land_IMUACCEL_ERROR);
        } else if (aaVar.m10215k()) {
            string = getString(C1205R.string.land_IMUGYRO_ERROR);
        } else if (aaVar.m10216l()) {
            string = getString(C1205R.string.land_BRO_ERROR);
        } else if ((aaVar.m10212h() || aaVar.m10213i()) && !aaVar.m10176A()) {
            string = getString(C1205R.string.land_CAMP1_ERROR);
            i = 6;
        } else if (aaVar.aa()) {
            string = getString(C1205R.string.fault_bat_pre_heart_des);
            i = 7;
        } else if (aaVar.m10176A()) {
            string = getString(C1205R.string.fault_compass);
            i = 8;
            this.f9670s = true;
        } else {
            if (aaVar.m10177B()) {
            }
            if (aaVar.m10178C()) {
            }
            if (aaVar.m10179D()) {
            }
            if (aaVar.m10180E()) {
                string = getString(C1205R.string.fault_gesture);
                i = 12;
            } else if (this.f9673v.m9560G().m10417g()) {
                string = getString(C1205R.string.rc_low_battery);
                i = 13;
            } else if (aaVar.m10220p()) {
                string2 = getString(C1205R.string.fault_vps_tip);
                string = getString(C1205R.string.fault_vps_des);
                i = 14;
            } else if (this.f9660i) {
                getFragmentManager().beginTransaction().remove(this.f9665n).commit();
                m11918a(getString(C1205R.string.self_error_auto_result_title), getString(C1205R.string.self_error_auto_result), false, true);
                this.f9660i = false;
                this.f9658g = 0;
                return;
            } else {
                this.f9660i = false;
                return;
            }
        }
        this.f9660i = true;
        if (this.f9658g < i && this.f9658g != 0) {
            getFragmentManager().beginTransaction().remove(this.f9665n).commit();
            m11918a(getString(C1205R.string.self_error_auto_result_title), getString(C1205R.string.self_error_auto_result), false, true);
            this.f9658g = i;
        }
        if (this.f9658g != i && aaVar.m10202a() >= 0) {
            if (this.f9665n == null || !this.f9665n.isVisible()) {
                this.f9658g = i;
                m11919a(string2, string, true, false, this.f9670s);
            }
        }
    }

    private void m11918a(String str, String str2, boolean z, boolean z2) {
        this.f9667p = 0.0f;
        this.f9656e.removeCallbacks(this.f9657f);
        if (this.f9666o == null) {
            this.f9666o = new AutoSelfErrorFrgment();
        }
        this.f9666o.m11910a(str2);
        this.f9666o.m11912b(str);
        this.f9666o.m11913b(z);
        this.f9666o.m11911a(z2);
        getFragmentManager().beginTransaction().setCustomAnimations(17432576, 17432577);
        getFragmentManager().beginTransaction().add(C1205R.id.contrain_layout, this.f9666o).commit();
    }

    private void m11919a(String str, String str2, boolean z, boolean z2, boolean z3) {
        this.f9667p = 0.0f;
        this.f9656e.removeCallbacks(this.f9657f);
        boolean c = m11922c();
        if (this.f9665n == null) {
            this.f9665n = new UpdateConnectDefeaFrgment();
        }
        if (!c) {
            getFragmentManager().beginTransaction().replace(C1205R.id.contrain_layout, this.f9665n).commitAllowingStateLoss();
        } else if (this.f9665n.isAdded()) {
            getFragmentManager().beginTransaction().remove(this.f9665n).commit();
            this.f9665n = new UpdateConnectDefeaFrgment();
            getFragmentManager().beginTransaction().add(C1205R.id.contrain_layout, this.f9665n).commitAllowingStateLoss();
        }
        this.f9665n.m11951a(str2);
        this.f9665n.m11953b(str);
        this.f9665n.m11955c(getString(C1205R.string.no_connect));
        this.f9665n.m11956c(z);
        this.f9665n.m11952a(z2);
        this.f9665n.m11954b(z3);
        getFragmentManager().beginTransaction().setCustomAnimations(17432576, 17432577);
    }

    private boolean m11921b() {
        StringBuffer stringBuffer = new StringBuffer();
        List e = C1901a.m12002a().m12010e();
        if (e == null || e.size() <= 0) {
            this.f9652a = C1901a.m12002a().m12009d();
            if (this.f9652a == null || this.f9652a.size() <= 0) {
                return false;
            }
            this.f9656e.removeCallbacks(this.f9657f);
            Intent intent = new Intent(this, FindNewFirmwareAvtivity.class);
            C1157c.m7938a().m7945a(FindNewFirmwareAvtivity.f9699a, this.f9652a);
            startActivity(intent);
            finish();
            overridePendingTransition(17432576, 17432577);
            return true;
        }
        C1157c.m7938a().m7945a(UpdateVersonBean.SP_NEED_DOWN_FIRMWWARES, e);
        this.f9656e.removeCallbacks(this.f9657f);
        startActivity(new Intent(this, FindOnlineFirmwareAvtivity.class));
        finish();
        overridePendingTransition(17432576, 17432577);
        return true;
    }

    private boolean m11922c() {
        try {
            this.f9665n = (UpdateConnectDefeaFrgment) getFragmentManager().findFragmentById(C1205R.id.main_layout);
            if (this.f9665n != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private C1686j m11927f() {
        return new C1913e(this);
    }

    public void m11928a() {
        this.f9669r = (TextView) findViewById(C1205R.id.tv_check_firmware);
        be.m12359a(getAssets(), this.f9669r);
        this.f9659h = (ProgressView) findViewById(C1205R.id.checkFirmwareprogress);
        this.f9659h.setMaxCount(200.0f);
        C1901a.m12002a().m12008c();
        this.f9673v = ((DroidPlannerApp) getApplication()).f5570a;
        C1325k.m8930a().m8937b(null);
        this.f9653b = (C1313t) C1276b.m8680a().m8699d();
        this.f9653b.m8875t().m8790b();
        if (this.f9653b.m8848d()) {
            this.f9653b.m8873r().m8758k();
        }
        this.f9675x = (ProgressBar) findViewById(C1205R.id.checkFirmwareProgressBar);
        Animation alphaAnimation = new AlphaAnimation(0.0f, C2020f.f10933c);
        alphaAnimation.setDuration(300);
        this.f9675x.setAnimation(alphaAnimation);
        alphaAnimation.start();
    }

    public void m11929d() {
        if (!this.f9673v.m9570Q() || m11921b()) {
            if (!this.f9673v.m9570Q()) {
                startActivity(new Intent(this, FlightActivity.class));
            }
        } else if (this.f9676y) {
            Editor edit = ay.m12293a((Context) this).edit();
            edit.putBoolean(C1236a.f5586J, false);
            edit.commit();
            this.dpa.m8525d();
        } else {
            startActivity(new Intent(this, FlightActivity.class));
        }
    }

    public void m11930e() {
        if (this.f9670s) {
            startActivity(new Intent(this, CaliCompassActivity.class));
            finish();
            overridePendingTransition(17432576, 17432577);
        }
        finish();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.dpa.m8523b((Activity) this);
        this.f9676y = ay.m12293a((Context) this).getBoolean(C1236a.f5588L, false);
        this.f9660i = false;
        getWindow().setFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        setContentView(C1205R.layout.activity_check_firmware_actvity);
        m11928a();
        this.f9654c = new C1907g(this, this.f9673v);
        new Thread(new C1910b(this)).start();
        this.f9656e.postDelayed(this.f9657f, 200);
        this.f9673v.m9590a((C1234i) this);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f9656e.removeMessages(1);
        this.f9673v.m9594b((C1234i) this);
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        switch (C1914f.f9823a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                c1433a.ab();
                if (this.f9667p <= 100.0f && this.f9660i) {
                }
            default:
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.f9667p > 0.0f) {
            this.f9656e.postDelayed(this.f9657f, 10);
        }
    }

    protected void onStop() {
        super.onStop();
        this.f9654c.m12074c();
        this.f9656e.removeCallbacks(this.f9657f);
    }
}
