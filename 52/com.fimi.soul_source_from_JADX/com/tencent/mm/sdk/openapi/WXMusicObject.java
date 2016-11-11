package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.mm.sdk.openapi.WXMediaMessage.IMediaObject;
import com.tencent.mm.sdk.platformtools.Log;

public class WXMusicObject implements IMediaObject {
    public String musicLowBandUrl;
    public String musicUrl;

    public boolean checkArgs() {
        if ((this.musicUrl == null || this.musicUrl.length() == 0) && (this.musicLowBandUrl == null || this.musicLowBandUrl.length() == 0)) {
            Log.m13541e("MicroMsg.SDK.WXMusicObject", "both arguments are null");
            return false;
        } else if (this.musicUrl != null && this.musicUrl.length() > C1142e.f5202b) {
            Log.m13541e("MicroMsg.SDK.WXMusicObject", "checkArgs fail, musicUrl is too long");
            return false;
        } else if (this.musicLowBandUrl == null || this.musicLowBandUrl.length() <= C1142e.f5202b) {
            return true;
        } else {
            Log.m13541e("MicroMsg.SDK.WXMusicObject", "checkArgs fail, musicLowBandUrl is too long");
            return false;
        }
    }

    public void serialize(Bundle bundle) {
        bundle.putString("_wxmusicobject_musicUrl", this.musicUrl);
        bundle.putString("_wxmusicobject_musicLowBandUrl", this.musicLowBandUrl);
    }

    public int type() {
        return 3;
    }

    public void unserialize(Bundle bundle) {
        this.musicUrl = bundle.getString("_wxmusicobject_musicUrl");
        this.musicLowBandUrl = bundle.getString("_wxmusicobject_musicLowBandUrl");
    }
}
