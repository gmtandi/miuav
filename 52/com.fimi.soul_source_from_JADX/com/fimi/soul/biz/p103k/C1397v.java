package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.biz.p098j.C1360g;
import com.fimi.soul.biz.p102i.C1361f;
import com.fimi.soul.entity.PlaneMsg;
import com.fimi.soul.entity.User;
import com.tencent.connect.common.Constants;
import java.util.HashMap;

/* renamed from: com.fimi.soul.biz.k.v */
public class C1397v implements Callback {
    public static String f6276a = null;
    public static String f6277b = null;
    private static C1397v f6278d = null;
    private static final int f6279e = 1;
    private HashMap<Integer, C1330i> f6280c;
    private Context f6281f;
    private C1360g f6282g;
    private Handler f6283h;

    static {
        f6278d = null;
        f6276a = Constants.VIA_TO_TYPE_QQ_GROUP;
        f6277b = Constants.VIA_SSO_LOGIN;
    }

    public C1397v(Context context) {
        this.f6281f = null;
        this.f6280c = new HashMap();
        this.f6281f = context;
        this.f6282g = new C1361f();
        this.f6283h = new Handler(this);
    }

    public static C1397v m9355a(Context context) {
        if (f6278d == null) {
            f6278d = new C1397v(context);
        }
        return f6278d;
    }

    public void m9358a(User user, String str, C1330i c1330i) {
        this.f6280c.put(Integer.valueOf(f6279e), c1330i);
        ah.m8077b(new C1398w(this, f6279e, str, user));
    }

    public boolean handleMessage(Message message) {
        if (message.obj != null) {
            ((C1330i) this.f6280c.get(Integer.valueOf(message.what))).m8951a((PlaneMsg) message.obj);
        }
        return false;
    }
}
