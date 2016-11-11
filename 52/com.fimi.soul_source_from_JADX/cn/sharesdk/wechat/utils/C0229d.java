package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.utils.C0205d;
import cn.sharesdk.wechat.utils.WXMediaMessage.C0224a;
import com.p054c.p055a.p072c.C0957d;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: cn.sharesdk.wechat.utils.d */
public class C0229d extends C0225m {
    public WXMediaMessage f448a;
    public int f449b;

    public int m902a() {
        return 2;
    }

    public void m903a(Bundle bundle) {
        super.m891a(bundle);
        bundle.putAll(C0224a.m853a(this.f448a));
        bundle.putInt("_wxapi_sendmessagetowx_req_scene", this.f449b);
    }

    public boolean m904b() {
        if (this.f448a.getType() == 8 && (this.f448a.thumbData == null || this.f448a.thumbData.length <= 0)) {
            C0205d.m752a().m737d("checkArgs fail, thumbData should not be null when send emoji", new Object[0]);
            return false;
        } else if (this.f448a.thumbData != null && this.f448a.thumbData.length > C0957d.f5043a) {
            C0205d.m752a().m737d("checkArgs fail, thumbData is invalid", new Object[0]);
            return false;
        } else if (this.f448a.title == null || this.f448a.title.length() <= Opcodes.ACC_INTERFACE) {
            if (this.f448a.description != null && this.f448a.description.length() > SmileConstants.MAX_SHARED_STRING_VALUES) {
                this.f448a.description = this.f448a.description.substring(0, 1021) + "...";
            }
            if (this.f448a.mediaObject != null) {
                return this.f448a.mediaObject.checkArgs();
            }
            C0205d.m752a().m737d("checkArgs fail, mediaObject is null", new Object[0]);
            return false;
        } else {
            C0205d.m752a().m737d("checkArgs fail, title is invalid", new Object[0]);
            return false;
        }
    }
}
