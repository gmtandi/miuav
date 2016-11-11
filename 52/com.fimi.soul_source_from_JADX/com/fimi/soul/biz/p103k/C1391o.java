package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.biz.p098j.C1358f;
import com.fimi.soul.biz.p102i.C1359e;
import com.fimi.soul.entity.HomePage;
import com.fimi.soul.entity.PlaneMsg;
import java.util.HashMap;

/* renamed from: com.fimi.soul.biz.k.o */
public class C1391o implements Callback {
    private static C1391o f6248a = null;
    private static final int f6249b = 10;
    private static final int f6250c = 11;
    private static final int f6251d = 12;
    private Context f6252e;
    private HashMap<Integer, C1330i> f6253f;
    private Handler f6254g;
    private C1358f f6255h;

    static {
        f6248a = null;
    }

    public C1391o(Context context) {
        this.f6252e = null;
        this.f6252e = context;
        this.f6253f = new HashMap();
        this.f6254g = new Handler(this);
        this.f6255h = new C1359e();
    }

    public static C1391o m9333a(Context context) {
        if (f6248a == null) {
            f6248a = new C1391o(context);
        }
        return f6248a;
    }

    public void m9336a(C1330i c1330i) {
        this.f6253f.put(Integer.valueOf(f6249b), c1330i);
        ah.m8077b(new C1392p(this, f6249b, null));
    }

    public void m9337a(HomePage homePage, C1330i c1330i) {
        this.f6253f.put(Integer.valueOf(f6251d), c1330i);
        ah.m8077b(new C1392p(this, f6251d, homePage));
    }

    public void m9338b(HomePage homePage, C1330i c1330i) {
        this.f6253f.put(Integer.valueOf(f6250c), c1330i);
        ah.m8077b(new C1392p(this, f6250c, homePage));
    }

    public boolean handleMessage(Message message) {
        if (message.obj != null) {
            ((C1330i) this.f6253f.get(Integer.valueOf(message.what))).m8951a((PlaneMsg) message.obj);
        }
        return false;
    }
}
