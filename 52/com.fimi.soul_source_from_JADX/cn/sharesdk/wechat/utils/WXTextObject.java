package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.utils.C0205d;
import cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject;
import com.fimi.kernel.p076b.p080d.C1142e;

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
        C0205d.m752a().m737d("checkArgs fail, text is invalid", new Object[0]);
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
