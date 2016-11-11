package com.fimi.soul.module.droneFragment;

import android.os.Handler;
import android.os.Message;
import com.fimi.soul.C1205R;
import org.codehaus.jackson.smile.SmileConstants;

class ak extends Handler {
    final /* synthetic */ ShowDroneStatusFragment f8179a;

    ak(ShowDroneStatusFragment showDroneStatusFragment) {
        this.f8179a = showDroneStatusFragment;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                this.f8179a.f8028J.setVisibility(8);
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                com.fimi.kernel.p084e.ak.m8083a(this.f8179a.f8022D, (int) C1205R.string.show_drone_status_picture_carlton_toast, 1);
            default:
        }
    }
}
