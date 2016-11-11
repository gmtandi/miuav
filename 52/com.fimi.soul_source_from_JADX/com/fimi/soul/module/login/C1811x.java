package com.fimi.soul.module.login;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

/* renamed from: com.fimi.soul.module.login.x */
class C1811x implements OnClickListener {
    final /* synthetic */ UsbConnectFragment f8867a;

    C1811x(UsbConnectFragment usbConnectFragment) {
        this.f8867a = usbConnectFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.TetherSettings");
        if (this.f8867a.getActivity() != null) {
            this.f8867a.startActivityForResult(intent, 0);
        }
    }
}
