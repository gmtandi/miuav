package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.biz.p098j.C1353c;
import com.fimi.soul.biz.p102i.C1354b;
import com.fimi.soul.entity.BroadcastComment;
import com.fimi.soul.entity.PlaneMsg;
import java.util.HashMap;

/* renamed from: com.fimi.soul.biz.k.f */
public class C1382f implements Callback {
    private static C1382f f6204a = null;
    private static final int f6205b = 1;
    private static final int f6206c = 2;
    private static final int f6207d = 4;
    private static final int f6208e = 3;
    private C1353c f6209f;
    private Handler f6210g;
    private Context f6211h;
    private HashMap<Integer, C1330i> f6212i;

    static {
        f6204a = null;
    }

    public C1382f(Context context) {
        this.f6211h = null;
        this.f6209f = new C1354b();
        this.f6210g = new Handler(this);
        this.f6211h = context;
        this.f6212i = new HashMap();
    }

    public static C1382f m9299a(Context context) {
        if (f6204a == null) {
            f6204a = new C1382f(context);
        }
        return f6204a;
    }

    public void m9302a(BroadcastComment broadcastComment, C1330i c1330i) {
        this.f6212i.put(Integer.valueOf(f6205b), c1330i);
        ah.m8077b(new C1383g(this, f6205b, null, f6205b, broadcastComment));
    }

    public void m9303a(String str, int i, C1330i c1330i) {
        this.f6212i.put(Integer.valueOf(f6208e), c1330i);
        ah.m8077b(new C1383g(this, f6208e, str, i, null));
    }

    public void m9304a(String str, String str2, C1330i c1330i) {
        BroadcastComment broadcastComment = new BroadcastComment();
        broadcastComment.setUserID(str2);
        this.f6212i.put(Integer.valueOf(f6206c), c1330i);
        ah.m8077b(new C1383g(this, f6206c, str, f6205b, broadcastComment));
    }

    public void m9305b(String str, String str2, C1330i c1330i) {
        BroadcastComment broadcastComment = new BroadcastComment();
        broadcastComment.setUserID(str2);
        this.f6212i.put(Integer.valueOf(f6207d), c1330i);
        ah.m8077b(new C1383g(this, f6207d, str, f6205b, broadcastComment));
    }

    public boolean handleMessage(Message message) {
        if (message.obj != null) {
            ((C1330i) this.f6212i.get(Integer.valueOf(message.what))).m8951a((PlaneMsg) message.obj);
        }
        return false;
    }
}
