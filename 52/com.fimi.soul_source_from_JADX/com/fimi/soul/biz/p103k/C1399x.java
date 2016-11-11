package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.biz.p098j.C1362h;
import com.fimi.soul.biz.p102i.C1363g;
import com.fimi.soul.entity.Plane;
import com.fimi.soul.entity.PlaneMsg;
import java.util.HashMap;

/* renamed from: com.fimi.soul.biz.k.x */
public class C1399x implements Callback {
    private static C1399x f6288a = null;
    private static final int f6289b = 0;
    private static final int f6290c = 1;
    private static final int f6291d = 2;
    private C1362h f6292e;
    private Handler f6293f;
    private Context f6294g;
    private HashMap<Integer, C1330i> f6295h;

    static {
        f6288a = null;
    }

    public C1399x(Context context) {
        this.f6294g = null;
        this.f6292e = new C1363g();
        this.f6293f = new Handler(this);
        this.f6294g = context;
    }

    public static C1399x m9360a(Context context) {
        if (f6288a == null) {
            f6288a = new C1399x(context);
        }
        return f6288a;
    }

    public void m9363a(C1330i c1330i) {
        this.f6295h.put(Integer.valueOf(f6289b), c1330i);
        ah.m8077b(new C1400y(this, f6289b, null));
    }

    public void m9364a(Plane plane, C1330i c1330i) {
        this.f6295h.put(Integer.valueOf(f6290c), c1330i);
        ah.m8077b(new C1400y(this, f6290c, plane));
    }

    public boolean handleMessage(Message message) {
        if (message.obj != null) {
            ((C1330i) this.f6295h.get(Integer.valueOf(message.what))).m8951a((PlaneMsg) message.obj);
        }
        return false;
    }
}
