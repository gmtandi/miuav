package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.biz.p098j.C1371l;
import com.fimi.soul.biz.p102i.C1372l;
import com.fimi.soul.entity.PlaneMsg;
import com.fimi.soul.entity.User;
import java.util.HashMap;

/* renamed from: com.fimi.soul.biz.k.ba */
public class ba implements Callback {
    private static ba f6130a = null;
    private static final int f6131b = 0;
    private static final int f6132c = 1;
    private static final int f6133d = 2;
    private static final int f6134e = 3;
    private static final int f6135f = 4;
    private static final int f6136g = 5;
    private static final int f6137h = 6;
    private static final int f6138i = 7;
    private static final int f6139j = 8;
    private static final int f6140k = 9;
    private static final int f6141l = 10;
    private static final int f6142m = 11;
    private static final int f6143n = 12;
    private static final int f6144o = 13;
    private static final int f6145p = 14;
    private static final int f6146q = 15;
    private static final int f6147r = 16;
    private static final int f6148s = 17;
    private static final int f6149t = 18;
    private static final int f6150u = 19;
    private C1371l f6151v;
    private C1388l f6152w;
    private Handler f6153x;
    private HashMap<Integer, C1330i> f6154y;
    private Context f6155z;

    static {
        f6130a = null;
    }

    public ba(Context context) {
        this.f6155z = null;
        this.f6151v = new C1372l(context);
        this.f6152w = C1388l.m9322a(context);
        this.f6153x = new Handler(context.getMainLooper(), this);
        this.f6155z = context;
        this.f6154y = new HashMap();
    }

    public static ba m9259a(Context context) {
        if (f6130a == null) {
            f6130a = new ba(context);
        }
        return f6130a;
    }

    public void m9262a(Context context, C1330i c1330i) {
        this.f6152w.m9326a(new bb(this));
        this.f6154y.put(Integer.valueOf(f6134e), c1330i);
        ah.m8077b(new bc(this, f6134e, f6131b, null));
    }

    public void m9263a(C1330i c1330i) {
        this.f6154y.put(Integer.valueOf(f6131b), c1330i);
        ah.m8077b(new bc(this, f6131b, f6131b, null));
    }

    public void m9264a(User user, int i, C1330i c1330i) {
        this.f6154y.put(Integer.valueOf(f6142m), c1330i);
        ah.m8077b(new bc(this, f6142m, f6131b, user));
    }

    public void m9265a(User user, int i, String str, C1330i c1330i) {
        this.f6154y.put(Integer.valueOf(f6143n), c1330i);
        ah.m8077b(new bc(this, f6143n, i, user, str));
    }

    public void m9266a(User user, C1330i c1330i) {
        this.f6154y.put(Integer.valueOf(f6146q), c1330i);
        ah.m8077b(new bc(this, f6146q, f6131b, user));
    }

    public void m9267a(String str, int i, C1330i c1330i) {
        this.f6154y.put(Integer.valueOf(f6137h), c1330i);
        User user = new User();
        user.setUserID(str);
        ah.m8077b(new bc(this, f6137h, i, user));
    }

    public void m9268a(String str, long j, long j2, String str2, C1330i c1330i) {
        this.f6154y.put(Integer.valueOf(f6148s), c1330i);
        ah.m8077b(new bc(this, f6148s, str, j, j2, str2));
    }

    public void m9269a(String str, C1330i c1330i) {
        this.f6154y.put(Integer.valueOf(f6145p), c1330i);
        User user = new User();
        user.setUserID(C1236a.m8533b(this.f6155z).getUserID());
        user.setDevice(str);
        ah.m8077b(new bc(this, f6145p, f6131b, user));
    }

    public void m9270a(String str, String str2, C1330i c1330i) {
        this.f6154y.put(Integer.valueOf(f6141l), c1330i);
        User user = new User();
        user.setUserID(str);
        user.setCountry(str2);
        ah.m8077b(new bc(this, f6141l, f6131b, user));
    }

    public void m9271b(User user, C1330i c1330i) {
        this.f6154y.put(Integer.valueOf(f6144o), c1330i);
        ah.m8077b(new bc(this, f6144o, f6131b, user));
    }

    public void m9272b(String str, int i, C1330i c1330i) {
        this.f6154y.put(Integer.valueOf(f6147r), c1330i);
        User user = new User();
        user.setUserID(str);
        ah.m8077b(new bc(this, f6147r, i, user));
    }

    public void m9273b(String str, C1330i c1330i) {
        this.f6154y.put(Integer.valueOf(f6149t), c1330i);
        ah.m8077b(new bc(this, f6149t, str));
    }

    public void m9274b(String str, String str2, C1330i c1330i) {
        this.f6154y.put(Integer.valueOf(f6138i), c1330i);
        User user = new User();
        user.setUserID(str);
        user.setCountry(str2);
        ah.m8077b(new bc(this, f6138i, f6131b, user));
    }

    public void m9275c(User user, C1330i c1330i) {
        this.f6154y.put(Integer.valueOf(f6140k), c1330i);
        ah.m8077b(new bc(this, f6140k, f6131b, user));
    }

    public void m9276c(String str, int i, C1330i c1330i) {
        this.f6154y.put(Integer.valueOf(f6139j), c1330i);
        User user = new User();
        user.setNickName(str);
        ah.m8077b(new bc(this, f6139j, i, user));
    }

    public void m9277c(String str, C1330i c1330i) {
        this.f6154y.put(Integer.valueOf(f6150u), c1330i);
        ah.m8077b(new bc(this, f6150u, str));
    }

    public void m9278c(String str, String str2, C1330i c1330i) {
        this.f6154y.put(Integer.valueOf(f6133d), c1330i);
        ah.m8077b(new bc(this, f6133d, f6131b, new User()));
    }

    public void m9279d(User user, C1330i c1330i) {
        this.f6154y.put(Integer.valueOf(f6135f), c1330i);
        ah.m8077b(new bc(this, f6135f, f6131b, user));
    }

    public void m9280e(User user, C1330i c1330i) {
        this.f6154y.put(Integer.valueOf(f6132c), c1330i);
        ah.m8077b(new bc(this, f6132c, f6131b, user));
    }

    public void m9281f(User user, C1330i c1330i) {
        this.f6154y.put(Integer.valueOf(f6136g), c1330i);
        ah.m8077b(new bc(this, f6136g, f6131b, user));
    }

    public boolean handleMessage(Message message) {
        if (message.obj != null) {
            ((C1330i) this.f6154y.get(Integer.valueOf(message.what))).m8951a((PlaneMsg) message.obj);
        }
        return false;
    }
}
