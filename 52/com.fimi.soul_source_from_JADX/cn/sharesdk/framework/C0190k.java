package cn.sharesdk.framework;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.SslErrorHandler;

/* renamed from: cn.sharesdk.framework.k */
class C0190k implements OnClickListener {
    final /* synthetic */ SslErrorHandler f331a;
    final /* synthetic */ C0154i f332b;

    C0190k(C0154i c0154i, SslErrorHandler sslErrorHandler) {
        this.f332b = c0154i;
        this.f331a = sslErrorHandler;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.f331a.cancel();
    }
}
