package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.utils.C0205d;
import cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject;
import com.fimi.kernel.p076b.p080d.C1142e;

public class WXWebpageObject implements IMediaObject {
    public String webpageUrl;

    public WXWebpageObject(String str) {
        this.webpageUrl = str;
    }

    public boolean checkArgs() {
        if (this.webpageUrl != null && this.webpageUrl.length() != 0 && this.webpageUrl.length() <= C1142e.f5202b) {
            return true;
        }
        C0205d.m752a().m737d("checkArgs fail, webpageUrl is invalid", new Object[0]);
        return false;
    }

    public void serialize(Bundle bundle) {
        bundle.putString("_wxwebpageobject_webpageUrl", this.webpageUrl);
    }

    public int type() {
        return 5;
    }

    public void unserialize(Bundle bundle) {
        this.webpageUrl = bundle.getString("_wxwebpageobject_webpageUrl");
    }
}
