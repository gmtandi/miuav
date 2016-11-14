package com.fimi.soul.module.update;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p084e.C1174m;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1313t;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.fimi.soul.biz.camera.p088b.C1213e;
import com.fimi.soul.biz.update.ak;
import com.fimi.soul.entity.FirmwareInfo;
import com.fimi.soul.module.droneui.FlightActivity;
import com.fimi.soul.module.setting.newhand.NewHandActivity;
import com.fimi.soul.module.update.p121a.C1901a;
import com.fimi.soul.utils.C1969i;
import com.fimi.soul.utils.ay;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.aq;
import java.io.File;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
// 发现新的固件版本：立即升级
public class FindNewFirmwareAvtivity extends BaseActivity implements OnClickListener, C1213e<X11RespCmd> {
    public static final String f9699a = "upgrade_firmwares";
    boolean f9700b;
    List<FirmwareInfo> f9701c;
    private Button f9702d;
    private Button f9703e;
    private Context f9704f;
    private ImageView f9705g;
    private int f9706h;
    private TextView f9707i;
    private TextView f9708j;
    private Boolean f9709k;
    private C1313t f9710l;
    private boolean f9711m;
    private boolean f9712n;
    private Handler f9713o;

    public FindNewFirmwareAvtivity() {
        this.f9700b = false;
        this.f9710l = null;
        this.f9701c = null;
        this.f9711m = false;
        this.f9712n = true;
        this.f9713o = new C1916h(this);
    }

    private void m11943b() {
        int i;
        StringBuffer stringBuffer = new StringBuffer();
        this.f9701c = C1901a.m12002a().m12009d();
        if (this.f9701c == null || this.f9701c.size() <= 0) {
            i = 0;
        } else {
            i = 0;
            for (int i2 = 0; i2 < this.f9701c.size(); i2++) {
                FirmwareInfo firmwareInfo = (FirmwareInfo) this.f9701c.get(i2);
                if (firmwareInfo.getSysId() == 4) {
                    this.f9700b = true;
                }
                if (!TextUtils.isEmpty(firmwareInfo.getUpdcontents())) {
                    stringBuffer.append((i2 + 1) + "\u3001" + firmwareInfo.getUpdcontents());
                }
                i += firmwareInfo.getUpdateTime();
                if (i2 < this.f9701c.size() - 1) {
                    stringBuffer.append("\n");
                }
            }
        }
        this.f9710l = (C1313t) C1276b.m8680a().m8699d();
        this.f9710l.m8832a((C1213e) this);
        this.f9710l.m8873r().m8749c(C1314u.cq);
        if (!this.f9710l.m8848d() && this.drone.m9575V()) {
            this.f9710l.m8875t().m8790b();
        }
        if (this.f9700b && this.f9710l.m8848d()) {
            this.f9710l.m8873r().m8745b();
        }
        if (!TextUtils.isEmpty(stringBuffer.toString())) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(getString(C1205R.string.update_time_tip, new Object[]{ak.m9425a(i)}));
            stringBuffer2.append(stringBuffer.toString());
            this.f9708j.setText(stringBuffer2.toString());
        }
    }

    public void m11946a(boolean z, X11RespCmd x11RespCmd) {
        if (z && x11RespCmd.isNotification()) {
            if (C1314u.aH.equals(x11RespCmd.getType())) {
                this.f9711m = false;
                this.f9712n = true;
                this.f9710l.m8873r().m8749c(C1314u.cq);
            }
            if (C1314u.aI.equals(x11RespCmd.getType())) {
                this.f9711m = false;
                this.f9712n = true;
                this.f9710l.m8873r().m8749c(C1314u.cq);
                if (this.f9700b && this.f9710l.m8848d()) {
                    this.f9710l.m8873r().m8745b();
                }
            }
        }
        switch (x11RespCmd.getMsg_id()) {
            case Type.BYTE /*3*/:
                this.f9712n = false;
                if (C1314u.aL.equals(this.f9710l.m8853i().getSDState()) || C1314u.aM.equals(this.f9710l.m8853i().getSDState())) {
                    new aq(this).m12748a(getString(C1205R.string.check_tf_card_must_format)).m12747a(getResources().getColor(C1205R.color.dialog_ensure_hot)).m12753b(getString(C1205R.string.ensure), new C1918j(this)).m12749a(getString(C1205R.string.cancel), new C1917i(this)).m12746a().show();
                } else if (C1314u.aN.equals(this.f9710l.m8853i().getSDState()) || C1314u.aO.equals(this.f9710l.m8853i().getSDState()) || C1314u.aP.equals(this.f9710l.m8853i().getSDState()) || C1314u.aQ.equals(this.f9710l.m8853i().getSDState())) {
                    this.f9711m = true;
                } else if (C1314u.aR.equals(this.f9710l.m8853i().getSDState())) {
                    com.fimi.kernel.p084e.ak.m8084a((Context) this, getString(C1205R.string.uncheck_tf_card_please_re_insert));
                }
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                if (z) {
                    C1174m.m8185b(new File(C1969i.m12491n()));
                    com.fimi.kernel.p084e.ak.m8085a((Context) this, getString(C1205R.string.format_success), com.fimi.kernel.p084e.ak.f5303c);
                    this.f9710l.m8873r().m8749c(C1314u.cq);
                    this.f9710l.m8839a(true);
                } else {
                    com.fimi.kernel.p084e.ak.m8085a((Context) this, getString(C1205R.string.format_fail), com.fimi.kernel.p084e.ak.f5303c);
                }
                C1189f.m8331b().m8348c();
            default:
        }
    }

    public boolean m11948a() {
        if (!this.f9700b) {
            return false;
        }
        long freeKBSpace = this.f9710l.m8853i().getFreeKBSpace();
        return freeKBSpace > 0 && freeKBSpace > 71680;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.btn_after:
                if (!C1189f.m8333c().m7937d(C1236a.f5621s)) {
                    startActivity(new Intent(this, NewHandActivity.class));
                    finish();
                    overridePendingTransition(17432576, 17432577);
                } else if (this.f9706h == 1) {
                    finish();
                } else if (this.f9709k.booleanValue()) {
                    Editor edit = ay.m12293a((Context) this).edit();
                    edit.putBoolean(C1236a.f5586J, true);
                    edit.commit();
                    this.dpa.m8525d();
                } else {
                    startActivity(new Intent(this, FlightActivity.class));
                }
            case C1205R.id.btn_upgrade:
                if (!this.drone.m9570Q()) {
                    com.fimi.kernel.p084e.ak.m8082a((Context) this, (int) C1205R.string.connect_drone_update);
                } else if (this.drone.m9560G().m10417g()) {
                    com.fimi.kernel.p084e.ak.m8082a((Context) this, (int) C1205R.string.update_low_battery);
                } else if (this.f9701c != null && this.f9701c.size() > 0 && this.drone.aa()) {
                    com.fimi.kernel.p084e.ak.m8082a((Context) this, (int) C1205R.string.motor_start_up);
                } else if (!m11948a() && this.f9700b) {
                    com.fimi.kernel.p084e.ak.m8082a((Context) this, (int) C1205R.string.camera_tf_space_detail);
                } else if (this.f9711m && this.f9700b) {
                    com.fimi.kernel.p084e.ak.m8082a((Context) this, (int) C1205R.string.tf_card_no_use_please_change);
                } else {
                    startActivity(new Intent(this.f9704f, UpgradingActivity.class));
                    finish();
                    overridePendingTransition(17432576, 17432577);
                }
            default:
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.dpa.m8523b((Activity) this);
        this.f9709k = Boolean.valueOf(ay.m12293a((Context) this).getBoolean(C1236a.f5588L, false));
        getWindow().setFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        setContentView(C1205R.layout.activity_find_new_firmware_avtivity);
        this.f9705g = (ImageView) findViewById(C1205R.id.img_an);
        this.f9702d = (Button) findViewById(C1205R.id.btn_after);
        be.m12359a(getAssets(), this.f9702d);
        this.f9702d.setOnClickListener(this);
        this.f9703e = (Button) findViewById(C1205R.id.btn_upgrade);
        be.m12359a(getAssets(), this.f9703e);
        this.f9703e.setOnClickListener(this);
        this.f9704f = this;
        this.f9706h = getIntent().getIntExtra(C1236a.f5583G, 0);
        this.f9707i = (TextView) findViewById(C1205R.id.update_firmware_title);
        this.f9708j = (TextView) findViewById(C1205R.id.update_firmwarename);
        be.m12359a(getAssets(), this.f9707i, this.f9708j);
        m11943b();
        this.f9713o.sendEmptyMessageDelayed(0, 2000);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f9713o != null) {
            this.f9713o.removeCallbacksAndMessages(null);
            this.f9713o = null;
        }
    }

    protected void onStart() {
        super.onStart();
        this.f9712n = false;
        this.f9711m = false;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.f9705g.setBackgroundResource(C1205R.drawable.up_anim);
        ((AnimationDrawable) this.f9705g.getBackground()).start();
    }
}
