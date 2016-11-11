package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.biz.p102i.C1365h;
import com.fimi.soul.entity.PlaneMsg;
import java.util.HashMap;

/* renamed from: com.fimi.soul.biz.k.z */
public class C1401z implements Callback {
    private static C1401z f6299a = null;
    private static final int f6300b = 0;
    private static final int f6301c = 1;
    private static final int f6302d = 2;
    private static final int f6303e = 3;
    private HashMap<Integer, C1330i> f6304f;
    private C1365h f6305g;
    private Handler f6306h;

    static {
        f6299a = null;
    }

    public C1401z(Context context) {
        this.f6305g = new C1365h(context);
        this.f6306h = new Handler(this);
        this.f6304f = new HashMap();
    }

    public static C1401z m9366a(Context context) {
        if (f6299a == null) {
            f6299a = new C1401z(context);
        }
        return f6299a;
    }

    public String m9368a() {
        return this.f6305g.m9095a();
    }

    public void m9369a(C1330i c1330i) {
        this.f6304f.put(Integer.valueOf(f6300b), c1330i);
        ah.m8077b(new aa(this, f6300b, null, null));
    }

    public void m9370a(String str) {
        this.f6305g.m9096a(str);
    }

    public void m9371a(String str, String str2, C1330i c1330i) {
        this.f6304f.put(Integer.valueOf(f6301c), c1330i);
        ah.m8077b(new aa(this, f6301c, str, str2));
    }

    public void m9372b(C1330i c1330i) {
        this.f6304f.put(Integer.valueOf(f6302d), c1330i);
        ah.m8077b(new aa(this, f6302d, null, null));
    }

    public void m9373c(C1330i c1330i) {
        this.f6304f.put(Integer.valueOf(f6303e), c1330i);
        ah.m8077b(new aa(this, f6303e, null, null));
    }

    public boolean handleMessage(Message message) {
        if (message.obj != null) {
            ((C1330i) this.f6304f.get(Integer.valueOf(message.what))).m8951a((PlaneMsg) message.obj);
        }
        return true;
    }
}
