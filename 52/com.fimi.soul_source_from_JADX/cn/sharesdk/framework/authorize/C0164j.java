package cn.sharesdk.framework.authorize;

import android.os.Message;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.utils.C0205d;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.UIHandler;

/* renamed from: cn.sharesdk.framework.authorize.j */
class C0164j extends Thread {
    final /* synthetic */ C0161g f217a;

    C0164j(C0161g c0161g) {
        this.f217a = c0161g;
    }

    public void run() {
        try {
            Message message = new Message();
            message.what = 2;
            if ("none".equals(DeviceHelper.getInstance(this.f217a.activity).getDetailNetworkTypeForStatic())) {
                message.arg1 = 1;
                UIHandler.sendMessage(message, this.f217a);
                return;
            }
            if (ShareSDK.isRemoveCookieOnAuthorize()) {
                CookieSyncManager.createInstance(this.f217a.activity);
                CookieManager.getInstance().removeAllCookie();
            }
            message.obj = this.f217a.a.getAuthorizeUrl();
            UIHandler.sendMessage(message, this.f217a);
        } catch (Throwable th) {
            C0205d.m752a().m750w(th);
        }
    }
}
