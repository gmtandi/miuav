package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.biz.p098j.C1353c;
import com.fimi.soul.biz.p102i.C1354b;
import com.fimi.soul.entity.BroadcastMode;
import com.fimi.soul.entity.PlaneMsg;
import java.util.HashMap;

/* renamed from: com.fimi.soul.biz.k.c */
public class C1379c implements Callback {
    private static C1379c f6178a = null;
    private static final int f6179b = 0;
    private static final int f6180c = 3;
    private static final int f6181d = 5;
    private static final int f6182e = 6;
    private static final int f6183f = 7;
    private static final int f6184g = 9;
    private static final int f6185h = 10;
    private static final int f6186i = 8;
    private static final int f6187j = 1;
    private static final int f6188k = 4;
    private static final int f6189l = 2;
    private C1353c f6190m;
    private Handler f6191n;
    private Context f6192o;
    private HashMap<Integer, C1330i> f6193p;

    static {
        f6178a = null;
    }

    public C1379c(Context context) {
        this.f6192o = null;
        this.f6190m = new C1354b();
        this.f6191n = new Handler(this);
        this.f6192o = context;
        this.f6193p = new HashMap();
    }

    public static C1379c m9289a(Context context) {
        if (f6178a == null) {
            f6178a = new C1379c(context);
        }
        return f6178a;
    }

    public void m9292a(C1330i c1330i) {
        this.f6193p.put(Integer.valueOf(f6179b), c1330i);
        ah.m8077b(new C1380d(this, f6179b, null, f6187j, null));
    }

    public void m9293a(BroadcastMode broadcastMode, C1330i c1330i) {
        this.f6193p.put(Integer.valueOf(f6187j), c1330i);
        ah.m8077b(new C1380d(this, f6187j, null, f6187j, broadcastMode));
    }

    public void m9294a(String str, int i, C1381e c1381e, C1330i c1330i) {
        if (c1381e == C1381e.BYUSERID) {
            this.f6193p.put(Integer.valueOf(f6180c), c1330i);
            ah.m8077b(new C1380d(this, f6180c, str, i, null));
        } else if (c1381e == C1381e.FOLLOW) {
            this.f6193p.put(Integer.valueOf(f6183f), c1330i);
            ah.m8077b(new C1380d(this, f6183f, str, i, null));
        } else if (c1381e == C1381e.HOT) {
            this.f6193p.put(Integer.valueOf(f6181d), c1330i);
            ah.m8077b(new C1380d(this, f6181d, str, i, null));
        } else if (c1381e == C1381e.RECENT) {
            this.f6193p.put(Integer.valueOf(f6182e), c1330i);
            ah.m8077b(new C1380d(this, f6182e, str, i, null));
        }
    }

    public void m9295a(String str, C1330i c1330i) {
        BroadcastMode broadcastMode = new BroadcastMode();
        broadcastMode.setBroadcastID(str);
        this.f6193p.put(Integer.valueOf(f6185h), c1330i);
        ah.m8077b(new C1380d(this, f6185h, null, f6187j, broadcastMode));
    }

    public void m9296b(BroadcastMode broadcastMode, C1330i c1330i) {
        this.f6193p.put(Integer.valueOf(f6186i), c1330i);
        ah.m8077b(new C1380d(this, f6186i, null, f6187j, broadcastMode));
    }

    public void m9297c(BroadcastMode broadcastMode, C1330i c1330i) {
        this.f6193p.put(Integer.valueOf(f6184g), c1330i);
        ah.m8077b(new C1380d(this, f6184g, null, f6187j, broadcastMode));
    }

    public boolean handleMessage(Message message) {
        if (message.obj != null) {
            ((C1330i) this.f6193p.get(Integer.valueOf(message.what))).m8951a((PlaneMsg) message.obj);
        }
        return false;
    }
}
