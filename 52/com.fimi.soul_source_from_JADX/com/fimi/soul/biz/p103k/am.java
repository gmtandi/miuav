package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.biz.p102i.C1366i;
import com.fimi.soul.entity.PlaneMsg;
import java.util.HashMap;

/* renamed from: com.fimi.soul.biz.k.am */
public class am implements Callback {
    private static am f6054a = null;
    private static final int f6055b = 0;
    private static final int f6056c = 1;
    private HashMap<Integer, C1330i> f6057d;
    private C1366i f6058e;
    private Context f6059f;
    private Handler f6060g;

    static {
        f6054a = null;
    }

    public am(Context context) {
        this.f6059f = null;
        this.f6058e = new C1366i();
        this.f6060g = new Handler(this);
        this.f6059f = context;
        this.f6057d = new HashMap();
    }

    public static am m9205a(Context context) {
        if (f6054a == null) {
            f6054a = new am(context);
        }
        return f6054a;
    }

    public void m9208a(C1330i c1330i) {
        this.f6057d.put(Integer.valueOf(f6056c), c1330i);
        ah.m8077b(new an(this, f6056c, null, null));
    }

    public void m9209a(String str, String str2, C1330i c1330i) {
        this.f6057d.put(Integer.valueOf(f6055b), c1330i);
        ah.m8077b(new an(this, f6055b, str, str2));
    }

    public boolean handleMessage(Message message) {
        if (message.obj != null) {
            ((C1330i) this.f6057d.get(Integer.valueOf(message.what))).m8951a((PlaneMsg) message.obj);
        }
        return true;
    }
}
