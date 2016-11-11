package cn.sharesdk.framework.authorize;

import android.content.Intent;

/* renamed from: cn.sharesdk.framework.authorize.f */
public abstract class C0159f {
    protected C0158e f207a;
    protected int f208b;
    protected SSOListener f209c;

    public C0159f(C0158e c0158e) {
        this.f207a = c0158e;
        this.f209c = c0158e.m446a().getSSOListener();
    }

    public abstract void m449a();

    public void m450a(int i) {
        this.f208b = i;
    }

    public void m451a(int i, int i2, Intent intent) {
    }

    protected void m452a(Intent intent) {
    }
}
