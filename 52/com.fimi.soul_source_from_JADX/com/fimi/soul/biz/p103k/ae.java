package com.fimi.soul.biz.p103k;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.fimi.soul.C1205R;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.biz.k.ae */
class ae extends Handler {
    final /* synthetic */ ac f6040a;

    ae(ac acVar) {
        this.f6040a = acVar;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                Toast.makeText(this.f6040a.f6034g, C1205R.string.share_success, 1).show();
                break;
            case Type.BYTE /*3*/:
                Toast.makeText(this.f6040a.f6034g, C1205R.string.share_fail, 1).show();
                break;
        }
        super.handleMessage(message);
    }
}
