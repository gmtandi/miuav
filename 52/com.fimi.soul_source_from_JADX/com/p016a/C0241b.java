package com.p016a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.a.b */
class C0241b implements OnClickListener {
    final /* synthetic */ C0238a f604a;

    C0241b(C0238a c0238a) {
        this.f604a = c0238a;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f604a.m956g();
        dialogInterface.cancel();
    }
}
