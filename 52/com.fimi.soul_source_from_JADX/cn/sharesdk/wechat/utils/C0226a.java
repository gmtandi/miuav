package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.utils.C0205d;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: cn.sharesdk.wechat.utils.a */
public class C0226a extends C0225m {
    public String f440a;
    public String f441b;

    public int m893a() {
        return 1;
    }

    public void m894a(Bundle bundle) {
        super.m891a(bundle);
        bundle.putString("_wxapi_sendauth_req_scope", this.f440a);
        bundle.putString("_wxapi_sendauth_req_state", this.f441b);
    }

    public boolean m895b() {
        if (this.f440a == null || this.f440a.length() == 0 || this.f440a.length() > SmileConstants.MAX_SHARED_STRING_VALUES) {
            C0205d.m752a().m737d("MicroMsg.SDK.SendAuth.Req", "checkArgs fail, scope is invalid");
            return false;
        } else if (this.f441b == null || this.f441b.length() <= SmileConstants.MAX_SHARED_STRING_VALUES) {
            return true;
        } else {
            C0205d.m752a().m737d("MicroMsg.SDK.SendAuth.Req", "checkArgs fail, state is invalid");
            return false;
        }
    }
}
