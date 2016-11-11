package cn.sharesdk.framework.authorize;

import android.content.Intent;

/* renamed from: cn.sharesdk.framework.authorize.e */
public class C0158e extends C0153a {
    protected SSOListener f205b;
    private C0159f f206c;

    public void m448a(SSOListener sSOListener) {
        this.f205b = sSOListener;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.f206c.m451a(i, i2, intent);
    }

    public void onCreate() {
        this.f206c = this.a.getSSOProcessor(this);
        if (this.f206c == null) {
            finish();
            AuthorizeListener authorizeListener = this.a.getAuthorizeListener();
            if (authorizeListener != null) {
                authorizeListener.onError(new Throwable("Failed to start SSO for " + this.a.getPlatform().getName()));
                return;
            }
            return;
        }
        this.f206c.m450a(32973);
        this.f206c.m449a();
    }

    public void onNewIntent(Intent intent) {
        this.f206c.m452a(intent);
    }
}
