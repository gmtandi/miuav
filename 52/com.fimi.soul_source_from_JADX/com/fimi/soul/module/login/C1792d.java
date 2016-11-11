package com.fimi.soul.module.login;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.fimi.soul.C1205R;
import com.fimi.soul.module.update.CheckFirmwareActvity;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.module.login.d */
class C1792d extends Handler {
    final /* synthetic */ LoginActivity f8848a;

    C1792d(LoginActivity loginActivity) {
        this.f8848a = loginActivity;
    }

    @TargetApi(17)
    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                this.f8848a.f8790K.removeCallbacks(this.f8848a.f8792M);
                if (this.f8848a.f8810z == 1 && this.f8848a.f8785E <= 0) {
                    this.f8848a.f8810z = 3;
                    this.f8848a.m11486a(this.f8848a.getString(C1205R.string.rc_tip_error), this.f8848a.getString(C1205R.string.unconnetremote));
                }
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                if (this.f8848a.f8810z == 1 && this.f8848a.f8785E <= 0) {
                    this.f8848a.f8796c.m8938c();
                    if (!this.f8848a.drone.m9570Q()) {
                        this.f8848a.m11486a(this.f8848a.getString(C1205R.string.fc_tip_error), this.f8848a.getString(C1205R.string.unconnectdrone));
                    } else if (this.f8848a.drone.aa()) {
                        this.f8848a.m11502j();
                    } else {
                        this.f8848a.f8810z = 0;
                        this.f8848a.dpa.m8522b();
                        this.f8848a.dpa.m8523b(this.f8848a);
                        this.f8848a.startActivityForResult(new Intent(this.f8848a, CheckFirmwareActvity.class), 0);
                        this.f8848a.overridePendingTransition(17432576, 17432577);
                    }
                }
            case Type.BYTE /*3*/:
                this.f8848a.f8809y = false;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                if (this.f8848a.f8810z != 0) {
                    new Thread(this.f8848a.f8793N).start();
                    this.f8848a.f8787G.start();
                    this.f8848a.f8790K.postDelayed(this.f8848a.f8792M, 200);
                    this.f8848a.f8790K.sendEmptyMessageDelayed(0, 20000);
                }
            default:
        }
    }
}
