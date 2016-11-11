package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.mm.sdk.openapi.WXMediaMessage.IMediaObject;
import com.tencent.mm.sdk.platformtools.Log;

public class WXVideoObject implements IMediaObject {
    public String videoLowBandUrl;
    public String videoUrl;

    public boolean checkArgs() {
        if ((this.videoUrl == null || this.videoUrl.length() == 0) && (this.videoLowBandUrl == null || this.videoLowBandUrl.length() == 0)) {
            Log.m13541e("MicroMsg.SDK.WXVideoObject", "both arguments are null");
            return false;
        } else if (this.videoUrl != null && this.videoUrl.length() > C1142e.f5202b) {
            Log.m13541e("MicroMsg.SDK.WXVideoObject", "checkArgs fail, videoUrl is too long");
            return false;
        } else if (this.videoLowBandUrl == null || this.videoLowBandUrl.length() <= C1142e.f5202b) {
            return true;
        } else {
            Log.m13541e("MicroMsg.SDK.WXVideoObject", "checkArgs fail, videoLowBandUrl is too long");
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
