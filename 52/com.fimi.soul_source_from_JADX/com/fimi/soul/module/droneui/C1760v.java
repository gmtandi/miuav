package com.fimi.soul.module.droneui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import com.fimi.soul.C1205R;

/* renamed from: com.fimi.soul.module.droneui.v */
public class C1760v {
    public static ProgressDialog m11369a(Context context, int i) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.onWindowFocusChanged(false);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(1);
        progressDialog.setTitle(context.getResources().getString(C1205R.string.updatetitle));
        progressDialog.setMax(i);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        if (!((Activity) context).isFinishing()) {
            progressDialog.show();
        }
        return progressDialog;
    }

    public static ProgressDialog m11370a(Context context, int i, int i2) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.onWindowFocusChanged(false);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(1);
        progressDialog.setTitle(context.getResources().getString(C1205R.string.updatetitle));
        progressDialog.setMax(i2);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        if (!((Activity) context).isFinishing()) {
            progressDialog.show();
        }
        return progressDialog;
    }
}
