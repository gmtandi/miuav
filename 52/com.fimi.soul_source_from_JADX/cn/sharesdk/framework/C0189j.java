package cn.sharesdk.framework;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.SslErrorHandler;

/* renamed from: cn.sharesdk.framework.j */
class C0189j implements OnClickListener {
    final /* synthetic */ SslErrorHandler f329a;
    final /* synthetic */ C0154i f330b;

    C0189j(C0154i c0154i, SslErrorHandler sslErrorHandler) {
        this.f330b = c0154i;
        this.f329a = sslErrorHandler;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.f329a.proceed();
    }
}
