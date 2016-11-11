package com.fimi.soul.biz.p096d;

import android.text.TextUtils;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p076b.C1152e;
import com.fimi.kernel.p076b.p079c.C1136d;
import com.fimi.kernel.p076b.p080d.C1140c;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.kernel.p084e.C1184w;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.biz.camera.entity.RelayEntity;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.module.update.C1921n;
import com.fimi.soul.module.update.p121a.C1901a;
import com.fimi.soul.module.update.p121a.C1902b;
import com.fimi.soul.module.update.p121a.C1905e;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.d.k */
public class C1325k implements C1136d {
    private static C1325k f5938a;
    private C1140c f5939b;
    private String f5940c;
    private String f5941d;
    private String f5942e;
    private int f5943f;
    private boolean f5944g;
    private boolean f5945h;
    private JSONObject f5946i;
    private List<C1327m> f5947j;

    static {
        f5938a = new C1325k();
    }

    private C1325k() {
        this.f5944g = false;
        this.f5945h = false;
        this.f5946i = null;
        this.f5947j = new ArrayList();
        this.f5939b = (C1140c) C1189f.m8328a(C1152e.Socket);
        C1142e f = this.f5939b.m7888f();
        f.m7723a(C1543c.f7237l);
        f.m7722a((int) C1314u.f5878e);
        f.m7892b((int) C1142e.f5202b);
        f.m7894b(true);
        f.m7891a(true);
        this.f5939b.m7883b((C1136d) this);
    }

    public static synchronized C1325k m8930a() {
        C1325k c1325k;
        synchronized (C1325k.class) {
            if (f5938a == null) {
                f5938a = new C1325k();
            }
            c1325k = f5938a;
        }
        return c1325k;
    }

    public void m8931a(C1136d c1136d) {
        this.f5939b.m7883b(c1136d);
    }

    public void m8932a(C1327m c1327m) {
        this.f5947j.add(c1327m);
    }

    public void m8933a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f5945h = true;
            RelayEntity relayEntity = new RelayEntity();
            try {
                this.f5946i = new JSONObject(str);
                String jSONObject = this.f5946i.getJSONObject("system").toString();
                String jSONObject2 = this.f5946i.getJSONObject("camera").toString();
                this.f5946i = new JSONObject(jSONObject);
                if (TextUtils.isEmpty(this.f5940c) && !TextUtils.isEmpty(this.f5946i.getString("sw_version"))) {
                    C1901a.m12002a().m12005a(new C1905e(this.f5941d, 11, Integer.valueOf(C1184w.m8293n(this.f5946i.getString("sw_version"))).intValue()));
                    C1901a.m12002a().m12004a(11, Integer.valueOf(C1184w.m8293n(this.f5946i.getString("sw_version"))).intValue());
                }
                this.f5940c = this.f5946i.getString("sw_version");
                this.f5941d = this.f5946i.getString("hw_version");
                this.f5942e = this.f5946i.optString("device_type");
                relayEntity.setDevice_type(this.f5942e);
                relayEntity.setFirmupg_finished(this.f5946i.getInt("firmupg_finished"));
                relayEntity.setHw_version(this.f5941d);
                relayEntity.setSw_version(this.f5940c);
                this.f5946i = new JSONObject(jSONObject2);
                this.f5943f = this.f5946i.getInt("quality");
                this.f5944g = this.f5946i.getBoolean("connected");
                relayEntity.setQuality(this.f5943f);
                relayEntity.setIsConnectCamera(this.f5944g);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (this.f5947j != null && this.f5947j.size() > 0) {
                for (C1327m a : this.f5947j) {
                    a.m8945a(relayEntity);
                }
            }
        }
    }

    public void m8934a(boolean z) {
        this.f5944g = z;
    }

    public void m8935b() {
        this.f5939b.m7889g();
        this.f5939b.m7886d();
    }

    public void m8936b(C1327m c1327m) {
        this.f5947j.remove(c1327m);
    }

    public void m8937b(String str) {
        this.f5940c = str;
    }

    public void m8938c() {
        ah.m8075a(new C1326l(this));
    }

    public String m8939d() {
        if (TextUtils.isEmpty(this.f5940c)) {
            C1902b c1902b = (C1902b) C1189f.m8333c().m7926a(C1921n.f9846k, C1902b.class);
            if (c1902b != null) {
                return c1902b.m12020e() + C2915a.f14760f;
            }
        }
        return this.f5940c;
    }

    public String m8940e() {
        return this.f5941d;
    }

    public int m8941f() {
        return this.f5943f;
    }

    public boolean m8942g() {
        return "4K".equalsIgnoreCase(this.f5942e);
    }

    public boolean m8943h() {
        return this.f5944g;
    }

    public boolean m8944i() {
        return this.f5945h;
    }
}
