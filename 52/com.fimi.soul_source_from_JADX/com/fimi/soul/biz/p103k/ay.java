package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.biz.p098j.C1367n;
import com.fimi.soul.biz.p102i.C1368j;
import com.fimi.soul.entity.PlaneMsg;
import com.fimi.soul.entity.UpgradeResultInfo;
import java.util.HashMap;

/* renamed from: com.fimi.soul.biz.k.ay */
public class ay implements Callback {
    private static ay f6114a = null;
    private static final int f6115b = 0;
    private static final int f6116c = 1;
    private static final int f6117d = 2;
    private static final int f6118e = 3;
    private C1367n f6119f;
    private Handler f6120g;
    private HashMap<Integer, C1330i> f6121h;
    private Context f6122i;

    static {
        f6114a = null;
    }

    public ay(Context context) {
        this.f6122i = null;
        this.f6119f = new C1368j();
        this.f6120g = new Handler(context.getMainLooper(), this);
        this.f6122i = context;
        this.f6121h = new HashMap();
    }

    public static ay m9251a(Context context) {
        if (f6114a == null) {
            f6114a = new ay(context);
        }
        return f6114a;
    }

    public void m9254a(C1330i c1330i) {
        this.f6121h.put(Integer.valueOf(f6115b), c1330i);
        ah.m8077b(new az(this, f6115b));
    }

    public void m9255a(C1330i c1330i, UpgradeResultInfo upgradeResultInfo) {
        this.f6121h.put(Integer.valueOf(f6118e), c1330i);
        ah.m8077b(new az(this, (int) f6118e, upgradeResultInfo));
    }

    public void m9256a(C1330i c1330i, String str) {
        this.f6121h.put(Integer.valueOf(f6116c), c1330i);
        ah.m8077b(new az(this, (int) f6116c, str));
    }

    public void m9257b(C1330i c1330i) {
        this.f6121h.put(Integer.valueOf(f6117d), c1330i);
        ah.m8077b(new az(this, f6117d));
    }

    public boolean handleMessage(Message message) {
        if (message.obj != null) {
            ((C1330i) this.f6121h.get(Integer.valueOf(message.what))).m8951a((PlaneMsg) message.obj);
        }
        return false;
    }
}
