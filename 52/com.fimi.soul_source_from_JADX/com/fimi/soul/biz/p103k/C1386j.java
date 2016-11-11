package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.biz.p098j.C1355d;
import com.fimi.soul.biz.p102i.C1356c;
import com.fimi.soul.entity.PlaneMsg;
import java.util.HashMap;

/* renamed from: com.fimi.soul.biz.k.j */
public class C1386j implements Callback {
    private static C1386j f6222a = null;
    private static final int f6223b = 0;
    private static final int f6224c = 1;
    private static final int f6225d = 2;
    private static final int f6226e = 3;
    private C1355d f6227f;
    private Handler f6228g;
    private HashMap<Integer, C1330i> f6229h;
    private Context f6230i;

    static {
        f6222a = null;
    }

    public C1386j(Context context) {
        this.f6230i = null;
        this.f6227f = new C1356c();
        this.f6228g = new Handler(this);
        this.f6230i = context;
        this.f6229h = new HashMap();
    }

    public static C1386j m9314a(Context context) {
        if (f6222a == null) {
            f6222a = new C1386j(context);
        }
        return f6222a;
    }

    public void m9317a(C1330i c1330i) {
        this.f6229h.put(Integer.valueOf(f6223b), c1330i);
        ah.m8077b(new C1387k(this, f6223b));
    }

    public void m9318b(C1330i c1330i) {
        this.f6229h.put(Integer.valueOf(f6224c), c1330i);
        ah.m8077b(new C1387k(this, f6224c));
    }

    public void m9319c(C1330i c1330i) {
        this.f6229h.put(Integer.valueOf(f6225d), c1330i);
        ah.m8077b(new C1387k(this, f6225d));
    }

    public void m9320d(C1330i c1330i) {
        this.f6229h.put(Integer.valueOf(f6226e), c1330i);
        ah.m8077b(new C1387k(this, f6226e));
    }

    public boolean handleMessage(Message message) {
        if (message.obj != null) {
            ((C1330i) this.f6229h.get(Integer.valueOf(message.what))).m8951a((PlaneMsg) message.obj);
        }
        return false;
    }
}
