package com.fimi.soul.biz.p103k;

import android.util.Log;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.utils.NetUtil;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.fimi.soul.biz.k.av */
class av implements Runnable {
    final /* synthetic */ List f6095a;
    final /* synthetic */ at f6096b;

    av(at atVar, List list) {
        this.f6096b = atVar;
        this.f6095a = list;
    }

    public void run() {
        try {
            if ("true".equals(new JSONObject(NetUtil.m12204b(C1236a.f5612j, this.f6095a, this.f6096b.f6090b)).getString("success"))) {
                this.f6096b.m9237c();
            }
        } catch (Exception e) {
            Log.e("good", e.toString());
        }
    }
}
