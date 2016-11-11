package com.fimi.soul.utils;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import com.fimi.soul.base.C1236a;

/* renamed from: com.fimi.soul.utils.k */
public class C1971k implements Runnable {
    private Handler f10153a;
    private ProgressDialog f10154b;
    private String f10155c;

    public C1971k(String str, Handler handler) {
        this.f10155c = str;
        this.f10153a = handler;
    }

    public void run() {
        System.out.println("\u4e0b\u8f7d\u7ebf\u7a0b\u5f00\u542f");
        new Message().what = af.m12239a(this.f10155c, C1236a.f5580D, C1969i.m12486i(), this.f10153a);
    }
}
