package com.fimi.soul.module.setting;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.fimi.soul.module.setting.b */
class C1846b implements OnClickListener {
    final /* synthetic */ AboutActivity f9286a;

    C1846b(AboutActivity aboutActivity) {
        this.f9286a = aboutActivity;
    }

    public void onClick(View view) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + this.f9286a.getPackageName()));
        intent.addFlags(268435456);
        this.f9286a.startActivity(intent);
    }
}
