package com.fimi.soul.biz.p103k;

import android.widget.Toast;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

/* renamed from: com.fimi.soul.biz.k.af */
class af implements IUiListener {
    final /* synthetic */ ac f6041a;

    af(ac acVar) {
        this.f6041a = acVar;
    }

    public void onCancel() {
    }

    public void onComplete(Object obj) {
        Toast.makeText(this.f6041a.f6034g, "Complete", 1).show();
    }

    public void onError(UiError uiError) {
        Toast.makeText(this.f6041a.f6034g, "Error", 1).show();
    }
}
