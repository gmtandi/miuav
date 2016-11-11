package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.utils.C0205d;
import cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject;
import com.fimi.kernel.p076b.p080d.C1142e;

public class WXVideoObject implements IMediaObject {
    public String videoLowBandUrl;
    public String videoUrl;

    public boolean checkArgs() {
        if ((this.videoUrl == null || this.videoUrl.length() == 0) && (this.videoLowBandUrl == null || this.videoLowBandUrl.length() == 0)) {
            C0205d.m752a().m737d("both arguments are null", new Object[0]);
            return false;
        } else if (this.videoUrl != null && this.videoUrl.length() > C1142e.f5202b) {
            C0205d.m752a().m737d("checkArgs fail, videoUrl is too long", new Object[0]);
            return false;
        } else if (this.videoLowBandUrl == null || this.videoLowBandUrl.length() <= C1142e.f5202b) {
            return true;
        } else {
            C0205d.m752a().m737d("checkArgs fail, videoLowBandUrl is too long", new Object[0]);
            return false;
        }
    }

    public void serialize(Bundle bundle) {
        bundle.putString("_wxvideoobject_videoUrl", this.videoUrl);
        bundle.putString("_wxvideoobject_videoLowBandUrl", this.videoLowBandUrl);
    }

    public int type() {
        return 4;
    }

    public void unserialize(Bundle bundle) {
        this.videoUrl = bundle.getString("_wxvideoobject_videoUrl");
        this.videoLowBandUrl = bundle.getString("_wxvideoobject_videoLowBandUrl");
    }
}
