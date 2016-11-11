package com.fimi.soul.module.setting.newhand;

import android.content.Context;
import com.fimi.kernel.p084e.ak;
import com.fimi.kernel.view.button.SwitchButton;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.p103k.C1397v;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.entity.Setting;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class ae extends ac {
    public static final int f9473a = 7;
    public static final byte f9474b = (byte) 0;
    public static final byte f9475c = (byte) 1;
    public static final byte f9476d = (byte) 2;
    public static final byte f9477e = (byte) 3;
    public static final byte f9478f = (byte) 85;
    public static final byte f9479g = (byte) -86;
    public static final byte f9480h = (byte) 0;
    public static final byte f9481i = (byte) 1;
    public static final int f9482j = 300;
    C1433a f9483k;
    Context f9484l;

    public ae(C1433a c1433a, Context context) {
        this.f9483k = c1433a;
        this.f9484l = context;
    }

    private C1465c m11838a(byte b) {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f9473a;
        c1465c.f7000c = Opcodes.I2D;
        c1465c.f7001d.m9828b((byte) f9478f);
        c1465c.f7001d.m9828b(b);
        c1465c.f7001d.m9828b((byte) f9480h);
        c1465c.f7001d.m9828b((byte) f9480h);
        c1465c.f7001d.m9828b((byte) f9480h);
        c1465c.f7001d.m9828b((byte) f9480h);
        c1465c.f7001d.m9828b((byte) f9480h);
        return c1465c;
    }

    private void m11839b(byte b) {
        this.f9483k.m9569P().m9993a(m11838a(b));
    }

    private C1465c m11840c(int i) {
        return new C1465c();
    }

    private C1465c m11841e() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f9473a;
        c1465c.f7000c = Opcodes.I2D;
        c1465c.f7001d.m9828b((byte) f9479g);
        c1465c.f7001d.m9828b((byte) f9481i);
        c1465c.f7001d.m9828b((byte) f9480h);
        c1465c.f7001d.m9828b((byte) f9480h);
        c1465c.f7001d.m9828b((byte) f9480h);
        c1465c.f7001d.m9828b((byte) f9480h);
        c1465c.f7001d.m9828b((byte) f9476d);
        return c1465c;
    }

    public void m11842a() {
    }

    public void m11843a(int i) {
    }

    public void m11844a(int i, int i2) {
        this.f9483k.m9569P().m9993a(m11840c(i));
    }

    public void m11845a(int i, ad adVar) {
        ak.m8085a(this.f9484l, this.f9484l.getString(C1205R.string.please_update_the_last_flight_version), 0);
    }

    public void m11846a(SwitchButton switchButton, Setting setting) {
        if (!this.f9483k.m9570Q()) {
            ak.m8083a(this.f9484l, (int) C1205R.string.set_new_hand_fail, ak.f5302b);
        } else if (this.f9483k.aa()) {
            ak.m8085a(this.f9484l, this.f9484l.getString(C1205R.string.not_set_newhand_in_sky), ak.f5302b);
        } else if (setting.getIsOPen().booleanValue() && this.f9483k.m9573T() < 60) {
            ak.m8085a(this.f9484l, this.f9484l.getString(C1205R.string.limite_flight, new Object[]{Long.valueOf(this.f9483k.m9573T())}), ak.f5302b);
        } else if (setting.getIsOPen().booleanValue()) {
            switchButton.m8371a(false, true);
            m11839b((byte) f9481i);
        } else {
            switchButton.m8371a(true, true);
            m11839b((byte) f9480h);
        }
    }

    public void m11847a(List<Setting> list) {
        if (!((Setting) list.get(5)).getIsOPen().booleanValue()) {
            if (this.f9483k.aa()) {
                ak.m8085a(this.f9484l, this.f9484l.getString(C1205R.string.not_set_attitude_in_sky), ak.f5302b);
            } else {
                ak.m8085a(this.f9484l, this.f9484l.getString(C1205R.string.please_update_the_last_flight_version), 0);
            }
        }
    }

    public void m11848a(List<Setting> list, C1397v c1397v) {
        if (((Setting) list.get(5)).getIsOPen().booleanValue() || !((Setting) list.get(8)).getIsOPen().booleanValue()) {
        }
    }

    public void m11849b() {
    }

    public void m11850b(int i) {
    }

    public void m11851b(int i, ad adVar) {
        ak.m8085a(this.f9484l, this.f9484l.getString(C1205R.string.please_update_the_last_flight_version), 0);
    }

    public void m11852c() {
    }

    public void m11853d() {
        this.f9483k.m9569P().m9993a(m11841e());
    }
}
