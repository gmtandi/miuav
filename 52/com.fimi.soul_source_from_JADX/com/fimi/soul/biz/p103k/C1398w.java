package com.fimi.soul.biz.p103k;

import android.os.Message;
import com.fimi.soul.entity.User;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.biz.k.w */
class C1398w implements Runnable {
    final /* synthetic */ C1397v f6284a;
    private User f6285b;
    private int f6286c;
    private String f6287d;

    public C1398w(C1397v c1397v, int i, String str, User user) {
        this.f6284a = c1397v;
        this.f6286c = i;
        this.f6285b = user;
        this.f6287d = str;
    }

    public void run() {
        Message obtainMessage = this.f6284a.f6283h.obtainMessage();
        Object obj = null;
        switch (this.f6286c) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                obj = this.f6284a.f6282g.m9080a(this.f6285b, this.f6287d, this.f6284a.f6281f);
                break;
        }
        obtainMessage.what = this.f6286c;
        obtainMessage.obj = obj;
        this.f6284a.f6283h.sendMessage(obtainMessage);
    }
}
