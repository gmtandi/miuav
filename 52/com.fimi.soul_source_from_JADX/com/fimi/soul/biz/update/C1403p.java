package com.fimi.soul.biz.update;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import com.fimi.kernel.C1099d;
import com.fimi.kernel.C1189f;
import com.fimi.soul.entity.UpdateVersonBean;
import com.fimi.soul.module.update.C1921n;
import com.fimi.soul.utils.C1969i;
import com.google.gson.Gson;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.update.p */
public abstract class C1403p extends C1099d {
    protected static final int f6308a = 9898;
    public static boolean f6309b = false;
    public static boolean f6310c = false;
    public static final int f6311d = -1;
    public static final int f6312e = -2;
    private ah f6313f;
    private long f6314g;
    private UpdateVersonBean f6315h;
    private UpdateVersonBean f6316i;
    private Gson f6317j;

    static {
        f6309b = false;
        f6310c = false;
    }

    public C1403p() {
        this.f6317j = new Gson();
    }

    private void m9380a(int i, int i2, int i3) {
        Message message = new Message();
        message.what = f6308a;
        message.arg1 = i;
        message.arg2 = i2;
        Bundle bundle = new Bundle();
        bundle.putInt("arg3", i3);
        message.setData(bundle);
        m7685a().sendMessage(message);
    }

    protected void m9382a(Message message) {
        boolean z = true;
        if (message.what == f6308a) {
            if (message.arg1 != 1) {
                z = false;
            }
            this.f6313f.m9419a(z, (long) message.arg2, this.f6314g, message.getData().getInt("arg3"));
        }
    }

    protected void m9383a(UpdateVersonBean updateVersonBean) {
        this.f6316i = updateVersonBean;
    }

    protected void m9384a(UpdateVersonBean updateVersonBean, String str, ah ahVar) {
        this.f6313f = ahVar;
        Log.d("moweiru", "downloadFile");
        m7687a(new C1419q(this, updateVersonBean, str));
    }

    protected void m9385b(UpdateVersonBean updateVersonBean) {
        this.f6315h = updateVersonBean;
    }

    protected void m9386c(UpdateVersonBean updateVersonBean) {
        if (updateVersonBean != null) {
            this.f6315h = updateVersonBean;
            C1189f.m8333c().m7930a(getClass().toString(), (Object) updateVersonBean);
        }
    }

    protected String m9387d(UpdateVersonBean updateVersonBean) {
        String str = C2915a.f14760f;
        int sysid = updateVersonBean.getSysid();
        if (sysid == 0) {
            str = C1921n.f9847l;
        } else if (sysid == 3) {
            str = C1921n.f9850o;
        } else if (sysid == 1) {
            str = C1921n.f9848m;
        } else if (sysid == 2) {
            str = C1921n.f9849n;
        } else if (sysid == 10) {
            str = C1921n.f9851p;
        } else if (sysid == 9) {
            str = C1921n.f9853r;
        } else if (sysid == 6) {
            str = C1921n.f9852q;
        } else if (sysid == 4) {
            str = C1921n.f9854s;
        } else if (sysid == 11) {
            str = ak.m9427a(updateVersonBean.getFileEncode());
        }
        return C1969i.m12493p() + str;
    }

    protected Gson m9388f() {
        return this.f6317j;
    }

    protected UpdateVersonBean m9389g() {
        return this.f6316i;
    }

    protected UpdateVersonBean m9390h() {
        return this.f6315h;
    }

    public UpdateVersonBean m9391i() {
        if (this.f6315h == null) {
            this.f6315h = (UpdateVersonBean) C1189f.m8333c().m7926a(getClass().toString(), UpdateVersonBean.class);
        }
        if (this.f6315h == null) {
            this.f6315h = new UpdateVersonBean();
        }
        return this.f6315h;
    }
}
