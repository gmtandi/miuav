package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.mm.sdk.openapi.WXMediaMessage.IMediaObject;
import com.tencent.mm.sdk.platformtools.Log;

public class WXTextObject implements IMediaObject {
    public String text;

    public WXTextObject() {
        this(null);
    }

    public WXTextObject(String str) {
        this.text = str;
    }

    public boolean checkArgs() {
        if (this.text != null && this.text.length() != 0 && this.text.length() <= C1142e.f5202b) {
            return true;
        }
        Log.m13541e("MicroMsg.SDK.WXTextObject", "checkArgs fail, text is invalid");
        return false;
    }

    public void serialize(Bundle bundle) {
        bundle.putString("_wxtextobject_text", this.text);
    }

    public int type() {
        return 1;
    }

    public void unserialize(Bundle bundle) {
        this.text = bundle.getString("_wxtextobject_text");
    }
}
