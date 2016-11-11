package com.fimi.soul.view;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.fimi.kernel.view.dialog.C1198a;

/* renamed from: com.fimi.soul.view.d */
class C1995d implements OnClickListener {
    final /* synthetic */ C1198a f10818a;
    final /* synthetic */ C1989c f10819b;

    C1995d(C1989c c1989c, C1198a c1198a) {
        this.f10819b = c1989c;
        this.f10818a = c1198a;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.f10818a != null) {
            this.f10818a.m8386a(-1);
        }
    }
}
