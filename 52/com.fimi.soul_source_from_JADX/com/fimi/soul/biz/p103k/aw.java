package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.biz.p098j.C1369k;
import com.fimi.soul.biz.p102i.C1370k;
import com.fimi.soul.entity.PlaneMsg;
import com.fimi.soul.entity.UpdateDroneItem;
import java.util.HashMap;

/* renamed from: com.fimi.soul.biz.k.aw */
public class aw implements Callback {
    private static aw f6097a = null;
    private static final int f6098b = 0;
    private static final int f6099c = 1;
    private static final int f6100d = 2;
    private static final int f6101e = 3;
    private static final int f6102f = 4;
    private static final int f6103g = 5;
    private C1369k f6104h;
    private Handler f6105i;
    private HashMap<Integer, C1330i> f6106j;
    private Context f6107k;

    static {
        f6097a = null;
    }

    public aw(Context context) {
        this.f6107k = null;
        this.f6104h = new C1370k();
        this.f6105i = new Handler(this);
        this.f6107k = context;
        this.f6106j = new HashMap();
    }

    public static aw m9241a(Context context) {
        if (f6097a == null) {
            f6097a = new aw(context);
        }
        return f6097a;
    }

    public void m9244a(UpdateDroneItem updateDroneItem, C1330i c1330i) {
        this.f6106j.put(Integer.valueOf(f6098b), c1330i);
        ah.m8077b(new ax(this, (int) f6098b, updateDroneItem));
    }

    public void m9245a(String str, C1330i c1330i) {
        this.f6106j.put(Integer.valueOf(f6100d), c1330i);
        ah.m8077b(new ax(this, (int) f6100d, str));
    }

    public void m9246a(String str, String str2, int i, C1330i c1330i) {
        this.f6106j.put(Integer.valueOf(f6103g), c1330i);
        ah.m8077b(new ax(this, f6103g, str, i, str2));
    }

    public void m9247b(UpdateDroneItem updateDroneItem, C1330i c1330i) {
        this.f6106j.put(Integer.valueOf(f6099c), c1330i);
        ah.m8077b(new ax(this, (int) f6099c, updateDroneItem));
    }

    public void m9248b(String str, C1330i c1330i) {
        this.f6106j.put(Integer.valueOf(f6101e), c1330i);
        ah.m8077b(new ax(this, (int) f6101e, str));
    }

    public void m9249c(String str, C1330i c1330i) {
        this.f6106j.put(Integer.valueOf(f6102f), c1330i);
        ah.m8077b(new ax(this, (int) f6102f, str));
    }

    public boolean handleMessage(Message message) {
        if (message.obj != null) {
            ((C1330i) this.f6106j.get(Integer.valueOf(message.what))).m8951a((PlaneMsg) message.obj);
        }
        return false;
    }
}
