package com.fimi.soul.module.update;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.fimi.kernel.view.progress.ProgressView;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1313t;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.fimi.soul.biz.camera.p088b.C1213e;
import com.fimi.soul.biz.p096d.C1325k;
import com.fimi.soul.biz.p096d.C1327m;
import com.fimi.soul.biz.update.C1404a;
import com.fimi.soul.biz.update.C1420r;
import com.fimi.soul.biz.update.C1423u;
import com.fimi.soul.biz.update.ak;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.entity.FirmwareInfo;
import com.fimi.soul.module.update.p121a.C1901a;
import com.fimi.soul.utils.ay;
import com.fimi.soul.utils.be;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.Serializable;
import java.util.List;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class UpgradingActivity extends BaseActivity implements C1213e<X11RespCmd>, C1900q {
    public static final String f9750a = "sp_upgrade_skip";
    private static final int f9751o = 153;
    private static final int f9752p = 256;
    private static final int f9753q = 136;
    private static final int f9754r = 102;
    private static final int f9755s = 50;
    private static int f9756u;
    private C1420r f9757A;
    private String f9758B;
    private aa f9759C;
    private Boolean f9760D;
    private boolean f9761E;
    private StringBuffer f9762F;
    private boolean f9763G;
    private TextView f9764H;
    private Toast f9765I;
    List<FirmwareInfo> f9766b;
    C1404a f9767c;
    C1922o f9768d;
    C1325k f9769e;
    C1327m f9770f;
    long f9771g;
    private ProgressView f9772h;
    private TextView f9773i;
    private ImageView f9774j;
    private C1433a f9775k;
    private TextView f9776l;
    private C1423u f9777m;
    private TextView f9778n;
    private final int f9779t;
    private boolean f9780v;
    private int f9781w;
    private int f9782x;
    private int f9783y;
    private C1313t f9784z;

    static {
        f9756u = 1;
    }

    public UpgradingActivity() {
        this.f9779t = 32;
        this.f9781w = 0;
        this.f9782x = 1;
        this.f9783y = 0;
        this.f9767c = null;
        this.f9784z = null;
        this.f9758B = null;
        this.f9768d = null;
        this.f9762F = null;
        this.f9769e = null;
    }

    private void m11963a(int i, int i2, int i3) {
        if (System.currentTimeMillis() - this.f9771g > 500 || i2 == i3 || i == f9752p) {
            this.f9771g = System.currentTimeMillis();
            Message message = new Message();
            message.what = i;
            message.arg1 = i2;
            message.arg2 = i3;
            getHandler().sendMessageDelayed(message, 50);
        }
    }

    private void m11964a(FirmwareInfo firmwareInfo) {
        if (m11972b(firmwareInfo)) {
            this.f9767c.m9409a(new C1926t(this), this, firmwareInfo);
        } else {
            this.f9777m.m9497a(firmwareInfo, this.f9782x);
        }
    }

    private void m11967a(String str) {
        m11963a(f9751o, (Integer.valueOf(str.substring(str.lastIndexOf("=") + 1, str.length()).trim()).intValue() / 2) + f9755s, 100);
    }

    private void m11968b() {
        this.f9777m.m9504e();
        this.f9766b = C1901a.m12002a().m12009d();
        if (this.f9766b == null || this.f9766b.size() == 0) {
            finish();
            return;
        }
        this.f9759C = m11996a(this.f9766b);
        this.f9783y = this.f9766b.size();
        this.f9781w = this.f9766b.size() * 100;
        this.f9772h.setMaxCount((float) this.f9781w);
        this.f9777m.m9495a(new C1924r(this));
        this.f9777m.m9493a(new C1925s(this));
        FirmwareInfo firmwareInfo = (FirmwareInfo) this.f9766b.get(0);
        if (!m11975c()) {
            return;
        }
        if (firmwareInfo.getSysId() == 4) {
            this.f9768d.m12084a();
            if (firmwareInfo.getSysId() == 4) {
                m11980e();
                return;
            }
            return;
        }
        m11964a(firmwareInfo);
    }

    private void m11969b(int i) {
        m11963a(f9751o, (i / 2) + f9755s, 100);
    }

    private boolean m11972b(FirmwareInfo firmwareInfo) {
        return firmwareInfo.getSysId() == 11;
    }

    private void m11974c(FirmwareInfo firmwareInfo) {
        if (m11972b(firmwareInfo)) {
            this.f9767c.m9409a(new C1931y(this), this, firmwareInfo);
        } else if (m11978d(firmwareInfo)) {
            this.f9777m.m9497a(firmwareInfo, this.f9782x);
        } else if (m11978d(firmwareInfo)) {
            this.f9777m.m9497a(firmwareInfo, this.f9782x);
        } else if (this.f9775k.m9570Q()) {
            this.f9777m.m9497a(firmwareInfo, this.f9782x);
        } else {
            getHandler().sendEmptyMessageDelayed(32, 50);
        }
    }

    private boolean m11975c() {
        if (!this.f9777m.m9501c()) {
            return true;
        }
        m11990l();
        return false;
    }

    private void m11977d() {
        this.f9768d.m12086b();
        this.f9768d.m12084a();
        this.f9767c.m9415e();
        this.f9769e = C1325k.m8930a();
        this.f9770f = new C1927u(this);
        this.f9769e.m8932a(this.f9770f);
    }

    private boolean m11978d(FirmwareInfo firmwareInfo) {
        return firmwareInfo.getSysId() == 1;
    }

    private void m11980e() {
        this.f9758B = C1921n.f9854s;
        new Thread(new C1928v(this)).start();
    }

    private void m11982f() {
        this.f9768d = new C1922o(0, this);
        this.f9762F = new StringBuffer();
        this.f9759C = aa.Others;
        f9756u = 1;
        this.f9767c = new C1404a();
        this.f9757A = new C1420r();
        this.f9784z = (C1313t) C1276b.m8680a().m8699d();
        this.f9763G = false;
        this.f9784z.m8846c(false);
        this.f9784z.m8832a((C1213e) this);
        this.f9784z.m8845c(new C1929w(this));
        this.f9784z.m8874s().m8765a(new C1930x(this));
        ak.m9436d();
        this.f9775k = ((DroidPlannerApp) getApplication()).f5570a;
        this.f9777m = new C1423u(this, this.f9775k);
    }

    private void m11984g() {
        ak.m9434c(C1314u.aW);
        this.f9763G = true;
        ak.m9439e(((FirmwareInfo) this.f9766b.get(0)).getVersion());
        m11986h();
    }

    private void m11986h() {
        ((FirmwareInfo) this.f9766b.get(0)).setIsUpgradeSuccess(Boolean.valueOf(true));
        if (this.f9783y >= 2) {
            FirmwareInfo firmwareInfo = (FirmwareInfo) this.f9766b.get(1);
            this.f9782x++;
            m11964a(firmwareInfo);
            return;
        }
        m11987i();
    }

    private void m11987i() {
        f9756u = 0;
        this.f9768d.m12088d();
        this.f9777m.m9508m();
        if (this.f9761E) {
            this.f9762F.deleteCharAt(this.f9762F.length() - 1);
        }
        if (this.f9759C == aa.OnlyRC) {
            this.f9777m.m9506k();
            m11988j();
            return;
        }
        m11988j();
    }

    private void m11988j() {
        m11993o();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        f9756u = 0;
        m11992n();
    }

    private void m11989k() {
        if (this.f9761E) {
            com.fimi.kernel.p084e.ak.m8084a((Context) this, this.f9762F.toString() + getResources().getString(C1205R.string.updatefail));
        } else {
            com.fimi.kernel.p084e.ak.m8082a((Context) this, (int) C1205R.string.upgrade_firmware_success);
        }
    }

    private void m11990l() {
        this.f9761E = true;
        this.f9763G = true;
        this.f9784z.m8874s().m8770a(true);
        getHandler().sendEmptyMessageDelayed(32, 50);
    }

    private void m11991m() {
        this.f9762F.append(((FirmwareInfo) this.f9766b.get(this.f9782x - 1)).getSysName() + MiPushClient.ACCEPT_TIME_SEPARATOR);
        if (m12001a() && this.f9775k.m9569P().m9995a()) {
            this.f9782x++;
            if (m11975c()) {
                m11974c((FirmwareInfo) this.f9766b.get(this.f9782x - 1));
                return;
            }
            return;
        }
        m11992n();
    }

    private void m11992n() {
        Intent intent = new Intent(this, UpgradeResultActivity.class);
        intent.putExtra(UpgradeResultActivity.f9742a, (Serializable) this.f9766b);
        startActivity(intent);
        finish();
        overridePendingTransition(17432576, 17432577);
    }

    private void m11993o() {
        if (this.f9784z != null) {
            this.f9784z.m8874s().m8770a(true);
            this.f9784z = null;
        }
        this.f9777m.m9504e();
        this.f9777m.m9507l();
        this.f9767c = null;
        this.f9768d.m12088d();
        m11994p();
        getHandler().removeCallbacksAndMessages(null);
        System.gc();
    }

    private void m11994p() {
        if (this.f9769e != null && this.f9770f != null) {
            this.f9769e.m8936b(this.f9770f);
        }
    }

    private void m11995q() {
        if (this.f9780v) {
            this.f9784z.m8874s().m8770a(true);
            new Thread(new C1932z(this)).start();
            this.f9765I.cancel();
            startActivity(new Intent(this, FindNewFirmwareAvtivity.class));
            finish();
            return;
        }
        this.f9780v = true;
        this.f9765I = Toast.makeText(getApplicationContext(), C1205R.string.upgrading_not_disconnect, 0);
        this.f9765I.show();
        getHandler().sendEmptyMessageDelayed(f9753q, 2000);
    }

    public aa m11996a(List<FirmwareInfo> list) {
        aa aaVar = aa.Others;
        aa aaVar2 = aaVar;
        for (FirmwareInfo firmwareInfo : list) {
            if (firmwareInfo.getSysId() == 11) {
                aaVar = aa.OnlyAP;
                aaVar2 = aaVar;
                for (FirmwareInfo firmwareInfo2 : list) {
                    aaVar2 = firmwareInfo2.getSysId() == 1 ? aa.RCAndAP : aaVar2;
                }
                return aaVar2;
            }
            aaVar2 = firmwareInfo2.getSysId() == 1 ? aa.OnlyRC : aaVar2;
        }
        return aaVar2;
    }

    public void m11997a(int i) {
        this.f9782x = i;
    }

    public void m11998a(boolean z) {
        if (z) {
            m11990l();
        }
    }

    public void m11999a(boolean z, X11RespCmd x11RespCmd) {
        if (z && x11RespCmd.isNotification()) {
            if (C1314u.aV.equals(x11RespCmd.getType())) {
                ak.m9434c(C1314u.aV);
                this.f9768d.m12086b();
            }
            if (C1314u.aW.equals(x11RespCmd.getType())) {
                m11984g();
            }
            if (C1314u.aX.equals(x11RespCmd.getType())) {
                ak.m9434c(C1314u.aX);
            }
            if (C1314u.ba.equals(x11RespCmd.getType())) {
                ak.m9434c(C1314u.ba);
            }
            if (x11RespCmd.getType().contains(C1314u.aY) && !this.f9763G) {
                String type = x11RespCmd.getType();
                ak.m9434c(type);
                m11967a(type);
            }
        }
    }

    public boolean m12001a() {
        return this.f9783y > this.f9782x;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.dpa.m8523b((Activity) this);
        this.f9760D = Boolean.valueOf(ay.m12293a((Context) this).getBoolean(C1236a.f5588L, false));
        getWindow().setFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        setContentView(C1205R.layout.activity_upgrading);
        this.f9772h = (ProgressView) findViewById(C1205R.id.progress_view);
        this.f9773i = (TextView) findViewById(C1205R.id.tv_progress);
        be.m12368b(getAssets(), this.f9773i);
        this.f9774j = (ImageView) findViewById(C1205R.id.img_anim);
        this.f9778n = (TextView) findViewById(C1205R.id.upgrade_result);
        this.f9764H = (TextView) findViewById(C1205R.id.upgrading_warning);
        be.m12359a(getAssets(), this.f9778n, this.f9764H);
        this.f9776l = (TextView) findViewById(C1205R.id.tv_percent);
        be.m12368b(getAssets(), this.f9776l);
        this.f9782x = 1;
        m11982f();
        m11968b();
    }

    protected void onDestroy() {
        super.onDestroy();
        m11993o();
    }

    public void onHandleMessage(Message message) {
        if (message.what == f9751o) {
            this.f9768d.m12086b();
            this.f9778n.setText(getResources().getString(C1205R.string.upgrading_firmware) + ((FirmwareInfo) this.f9766b.get(this.f9782x - 1)).getSysName());
            int i = ((message.arg1 * 100) / message.arg2) + ((this.f9782x - 1) * 100);
            this.f9773i.setText(((i * 100) / this.f9781w) + C2915a.f14760f);
            this.f9772h.setCurrentCount((float) i);
        }
        if (message.what == f9752p) {
            this.f9777m.m9505j();
            ((FirmwareInfo) this.f9766b.get(this.f9782x - 1)).setIsUpgradeSuccess(Boolean.valueOf(true));
            this.f9777m.m9496a((FirmwareInfo) this.f9766b.get(this.f9782x - 1));
            if (!m11975c()) {
                return;
            }
            if (m12001a()) {
                this.f9782x++;
                m11964a((FirmwareInfo) this.f9766b.get(this.f9782x - 1));
            } else {
                m11987i();
            }
        }
        if (message.what == f9753q) {
            this.f9780v = false;
        }
        if (message.what == f9754r) {
            m11977d();
        }
        if (message.what == 32) {
            m11991m();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        m11995q();
        return false;
    }

    protected void onResume() {
        super.onResume();
        if (f9756u == 0) {
            f9756u = 0;
            m11992n();
        }
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.f9774j.setBackgroundResource(C1205R.drawable.update_anim);
        ((AnimationDrawable) this.f9774j.getBackground()).start();
    }
}
