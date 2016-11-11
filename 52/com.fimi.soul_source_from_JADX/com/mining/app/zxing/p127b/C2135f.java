package com.mining.app.zxing.p127b;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.mining.app.zxing.b.f */
public final class C2135f implements OnCancelListener, OnClickListener, Runnable {
    private final Activity f11223a;

    public C2135f(Activity activity) {
        this.f11223a = activity;
    }

    public void onCancel(DialogInterface dialogInterface) {
        run();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        run();
    }

    public void run() {
        this.f11223a.finish();
    }
}
