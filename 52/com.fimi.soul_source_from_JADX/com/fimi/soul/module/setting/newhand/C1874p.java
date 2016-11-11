package com.fimi.soul.module.setting.newhand;

import android.content.Context;
import android.os.Handler;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p084e.ak;
import com.fimi.kernel.view.button.SwitchButton;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.p103k.C1397v;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.entity.Setting;
import com.fimi.soul.view.aq;
import com.fimi.soul.view.at;
import com.tencent.mm.sdk.platformtools.Util;
import it.p074a.p075a.C2799f;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.module.setting.newhand.p */
public class C1874p extends ac {
    public static final int f9565a = 9;
    public static final byte f9566b = (byte) 0;
    public static final byte f9567c = (byte) 1;
    public static final byte f9568d = (byte) 2;
    public static final byte f9569e = (byte) 3;
    public static final byte f9570f = (byte) 85;
    public static final byte f9571g = (byte) -86;
    public static final byte f9572h = (byte) 0;
    public static final byte f9573i = (byte) 1;
    public static final int f9574j = 60;
    private static final int f9575l = 2;
    private static final int f9576m = 3;
    private static final int f9577n = 4;
    private static final int f9578o = 5;
    private static final int f9579p = 6;
    private static final int f9580q = 7;
    private static final int f9581r = 8;
    private static final int f9582s = 9;
    private static final int f9583t = 10;
    private static final int f9584u = 11;
    private static final int f9585y = 3000;
    private static final int f9586z = 1;
    private Handler f9587A;
    C1433a f9588k;
    private int f9589v;
    private int f9590w;
    private Context f9591x;

    public C1874p(C1433a c1433a, Context context) {
        this.f9589v = 0;
        this.f9590w = 0;
        this.f9587A = new C1875q(this);
        this.f9588k = c1433a;
        this.f9591x = context;
    }

    private C1465c m11861a(byte b) {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f9582s;
        c1465c.f7000c = Opcodes.I2D;
        c1465c.f7001d.m9828b((byte) f9570f);
        c1465c.f7001d.m9828b(b);
        c1465c.f7001d.m9828b((byte) f9573i);
        c1465c.f7001d.m9828b((byte) f9572h);
        c1465c.f7001d.m9829b(0);
        c1465c.f7001d.m9828b((byte) f9572h);
        return c1465c;
    }

    private C1465c m11865b(int i, int i2) {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f9582s;
        c1465c.f7000c = Opcodes.I2D;
        c1465c.f7001d.m9828b((byte) f9570f);
        c1465c.f7001d.m9828b((byte) f9572h);
        c1465c.f7001d.m9828b((byte) f9569e);
        c1465c.f7001d.m9828b((byte) i);
        c1465c.f7001d.m9829b((byte) i2);
        c1465c.f7001d.m9828b((byte) f9572h);
        return c1465c;
    }

    private void m11866b(byte b) {
        this.f9588k.m9569P().m9993a(m11861a(b));
    }

    private C1465c m11868c(int i) {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f9582s;
        c1465c.f7000c = Opcodes.I2D;
        c1465c.f7001d.m9828b((byte) f9570f);
        c1465c.f7001d.m9828b((byte) f9572h);
        c1465c.f7001d.m9828b((byte) f9568d);
        c1465c.f7001d.m9828b((byte) 7);
        c1465c.f7001d.m9828b((byte) (i & Util.MASK_8BIT));
        c1465c.f7001d.m9828b((byte) ((i >> f9581r) & Util.MASK_8BIT));
        c1465c.f7001d.m9828b((byte) ((i >> 16) & Util.MASK_8BIT));
        c1465c.f7001d.m9828b((byte) ((i >> 24) & Util.MASK_8BIT));
        c1465c.f7001d.m9828b((byte) f9572h);
        return c1465c;
    }

    private C1465c m11869d(int i) {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f9582s;
        c1465c.f7000c = Opcodes.I2D;
        c1465c.f7001d.m9828b((byte) f9570f);
        c1465c.f7001d.m9828b((byte) f9572h);
        c1465c.f7001d.m9828b((byte) f9568d);
        c1465c.f7001d.m9828b((byte) f9569e);
        c1465c.f7001d.m9828b((byte) (i & Util.MASK_8BIT));
        c1465c.f7001d.m9828b((byte) ((i >> f9581r) & Util.MASK_8BIT));
        c1465c.f7001d.m9828b((byte) ((i >> 16) & Util.MASK_8BIT));
        c1465c.f7001d.m9828b((byte) ((i >> 24) & Util.MASK_8BIT));
        c1465c.f7001d.m9828b((byte) f9572h);
        return c1465c;
    }

    private C1465c m11870e() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f9582s;
        c1465c.f7000c = Opcodes.I2D;
        c1465c.f7001d.m9828b((byte) f9571g);
        c1465c.f7001d.m9828b((byte) f9573i);
        c1465c.f7001d.m9828b((byte) f9573i);
        c1465c.f7001d.m9828b((byte) f9572h);
        c1465c.f7001d.m9829b(0);
        c1465c.f7001d.m9828b((byte) f9572h);
        return c1465c;
    }

    private C1465c m11871f() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f9582s;
        c1465c.f7000c = Opcodes.I2D;
        c1465c.f7001d.m9828b((byte) f9571g);
        c1465c.f7001d.m9828b((byte) f9572h);
        c1465c.f7001d.m9828b((byte) f9569e);
        c1465c.f7001d.m9828b((byte) f9572h);
        c1465c.f7001d.m9829b(0);
        c1465c.f7001d.m9828b((byte) f9572h);
        return c1465c;
    }

    private C1465c m11872g() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f9582s;
        c1465c.f7000c = Opcodes.I2D;
        c1465c.f7001d.m9828b((byte) f9571g);
        c1465c.f7001d.m9828b((byte) f9572h);
        c1465c.f7001d.m9828b((byte) f9568d);
        c1465c.f7001d.m9828b((byte) 7);
        c1465c.f7001d.m9829b(0);
        c1465c.f7001d.m9828b((byte) f9572h);
        return c1465c;
    }

    private C1465c m11873h() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f9582s;
        c1465c.f7000c = Opcodes.I2D;
        c1465c.f7001d.m9828b((byte) f9571g);
        c1465c.f7001d.m9828b((byte) f9572h);
        c1465c.f7001d.m9828b((byte) f9568d);
        c1465c.f7001d.m9828b((byte) f9569e);
        c1465c.f7001d.m9829b(0);
        c1465c.f7001d.m9828b((byte) f9572h);
        return c1465c;
    }

    private void m11874i() {
        if (C1189f.m8331b().m8343b() != null && C1189f.m8331b().m8343b().isShowing()) {
            C1189f.m8331b().m8348c();
        }
    }

    public void m11875a() {
        this.f9588k.m9569P().m9993a(m11872g());
    }

    public void m11876a(int i) {
        this.f9588k.m9569P().m9993a(m11868c(i));
    }

    public void m11877a(int i, int i2) {
        this.f9588k.m9569P().m9993a(m11865b(i, i2));
    }

    public void m11878a(int i, ad adVar) {
        this.f9589v = i;
        if (!this.f9588k.m9570Q()) {
            ak.m8083a(this.f9591x, (int) C1205R.string.set_new_hand_fail, ak.f5302b);
        } else if (this.f9588k.aa()) {
            ak.m8085a(this.f9591x, this.f9591x.getString(C1205R.string.not_set_flight_distance_in_sky), ak.f5302b);
        } else if (i == C1873o.ak) {
            m11876a((int) C2799f.f14263a);
            adVar.m11837a(C2799f.f14263a);
        } else {
            new aq(this.f9591x).m12748a(this.f9591x.getString(C1205R.string.setting_dialog_warm_hint)).m12752b(this.f9591x.getString(C1205R.string.more_distance_cue)).m12747a(this.f9591x.getResources().getColor(C1205R.color.dialog_ensure_hot)).m12753b(this.f9591x.getString(C1205R.string.setting_dialog_agree), new C1879u(this, adVar)).m12749a(this.f9591x.getString(C1205R.string.cancel), new C1878t(this)).m12746a().show();
        }
    }

    public void m11879a(SwitchButton switchButton, Setting setting) {
        if (!this.f9588k.m9570Q()) {
            ak.m8083a(this.f9591x, (int) C1205R.string.set_new_hand_fail, ak.f5302b);
        } else if (this.f9588k.aa()) {
            ak.m8085a(this.f9591x, this.f9591x.getString(C1205R.string.not_set_newhand_in_sky), ak.f5302b);
        } else if (setting.getIsOPen().booleanValue() && this.f9588k.m9573T() < 60) {
            Context context = this.f9591x;
            Context context2 = this.f9591x;
            Object[] objArr = new Object[f9586z];
            objArr[0] = Long.valueOf(this.f9588k.m9573T());
            ak.m8085a(context, context2.getString(C1205R.string.limite_flight, objArr), ak.f5302b);
        } else if (setting.getIsOPen().booleanValue()) {
            switchButton.m8371a(false, true);
            m11866b((byte) f9573i);
        } else {
            switchButton.m8371a(true, true);
            m11866b((byte) f9572h);
        }
    }

    public void m11880a(List<Setting> list) {
        if (!((Setting) list.get(f9578o)).getIsOPen().booleanValue()) {
            if (!this.f9588k.m9570Q()) {
                ak.m8083a(this.f9591x, (int) C1205R.string.set_new_hand_fail, ak.f5302b);
            } else if (this.f9588k.aa()) {
                ak.m8085a(this.f9591x, this.f9591x.getString(C1205R.string.not_set_attitude_in_sky), ak.f5302b);
            } else if (((Setting) list.get(f9581r)).getIsOPen().booleanValue()) {
                m11877a((int) f9577n, 0);
            } else {
                new aq(this.f9591x).m12748a(this.f9591x.getString(C1205R.string.setting_dialog_warm_hint)).m12752b(this.f9591x.getString(C1205R.string.force_attiyude_mode_delcare)).m12747a(this.f9591x.getResources().getColor(C1205R.color.dialog_ensure_hot)).m12753b(this.f9591x.getString(C1205R.string.setting_dialog_agree), new C1884z(this)).m12749a(this.f9591x.getString(C1205R.string.cancel), new C1883y(this)).m12746a().show();
            }
        }
    }

    public void m11881a(List<Setting> list, C1397v c1397v) {
        if (!((Setting) list.get(f9578o)).getIsOPen().booleanValue() && !((Setting) list.get(f9581r)).getIsOPen().booleanValue()) {
            if (!this.f9588k.m9570Q()) {
                ak.m8083a(this.f9591x, (int) C1205R.string.set_new_hand_fail, ak.f5302b);
            } else if (this.f9588k.aa()) {
                ak.m8085a(this.f9591x, this.f9591x.getString(C1205R.string.not_set_optical_flow_in_sky), ak.f5302b);
            } else if (((Setting) list.get(f9580q)).getIsOPen().booleanValue()) {
                m11877a((int) f9575l, 0);
            } else {
                new aq(this.f9591x).m12748a(this.f9591x.getString(C1205R.string.setting_dialog_warm_hint)).m12752b(this.f9591x.getString(C1205R.string.assist_the_location_mode_to_open_please_be_careful_to_fly)).m12747a(this.f9591x.getResources().getColor(C1205R.color.dialog_ensure_hot)).m12753b(this.f9591x.getString(C1205R.string.ensure), new C1876r(this, c1397v)).m12749a(this.f9591x.getString(C1205R.string.refuse), new ab(this)).m12746a().show();
            }
        }
    }

    public void m11882b() {
        this.f9588k.m9569P().m9993a(m11873h());
    }

    public void m11883b(int i) {
        this.f9588k.m9569P().m9993a(m11869d(i));
    }

    public void m11884b(int i, ad adVar) {
        this.f9590w = i;
        if (!this.f9588k.m9570Q()) {
            ak.m8083a(this.f9591x, (int) C1205R.string.set_new_hand_fail, ak.f5302b);
        } else if (this.f9588k.aa()) {
            ak.m8085a(this.f9591x, this.f9591x.getString(C1205R.string.not_set_flight_speed_in_sky), ak.f5302b);
        } else {
            new at(this.f9591x).m12775c(this.f9591x.getString(C1205R.string.flight_speed_limit)).m12766a((int) f9576m).m12771b(18).m12776d(this.f9591x.getResources().getColor(C1205R.color.dialog_ensure_hot)).m12768a(this.f9591x.getString(C1205R.string.speed_unit_m)).m12772b(this.f9591x.getString(C1205R.string.dialog_speed_unit)).m12770a(true).m12774c(Integer.parseInt(C1189f.m8335e().m8035f())).m12769a(this.f9591x.getString(C1205R.string.cancel), new C1882x(this)).m12773b(this.f9591x.getString(C1205R.string.ensure), new C1881w(this)).m12767a(new C1880v(this)).m12765a().show();
        }
    }

    public void m11885c() {
        this.f9588k.m9569P().m9993a(m11871f());
    }

    public void m11886d() {
        this.f9588k.m9569P().m9993a(m11870e());
    }
}
