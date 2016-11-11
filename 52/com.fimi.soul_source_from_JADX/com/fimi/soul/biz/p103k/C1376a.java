package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.biz.p098j.C1351a;
import com.fimi.soul.biz.p102i.C1352a;
import com.fimi.soul.entity.AddPlane;
import com.fimi.soul.entity.PlaneMsg;
import java.util.HashMap;

/* renamed from: com.fimi.soul.biz.k.a */
public class C1376a implements Callback {
    private static C1376a f6009a = null;
    private static final int f6010e = 1;
    private Handler f6011b;
    private C1351a f6012c;
    private HashMap<Integer, C1330i> f6013d;
    private Context f6014f;

    static {
        f6009a = null;
    }

    public C1376a(Context context) {
        this.f6014f = null;
        this.f6012c = new C1352a();
        this.f6011b = new Handler(this);
        this.f6014f = context;
        this.f6013d = new HashMap();
    }

    public static C1376a m9176a(Context context) {
        if (f6009a == null) {
            f6009a = new C1376a(context);
        }
        return f6009a;
    }

    public void m9179a(AddPlane addPlane, C1330i c1330i) {
        this.f6013d.put(Integer.valueOf(f6010e), c1330i);
        ah.m8077b(new C1378b(this, f6010e, addPlane));
    }

    public boolean handleMessage(Message message) {
        if (message.obj != null) {
            ((C1330i) this.f6013d.get(Integer.valueOf(message.what))).m8951a((PlaneMsg) message.obj);
        }
        return false;
    }
}
