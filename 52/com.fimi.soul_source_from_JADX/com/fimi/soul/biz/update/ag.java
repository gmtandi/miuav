package com.fimi.soul.biz.update;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ag implements OnClickListener {
    final /* synthetic */ MyDialogFragment f6337a;

    ag(MyDialogFragment myDialogFragment) {
        this.f6337a = myDialogFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (dialogInterface != null) {
            dialogInterface.dismiss();
        }
    }
}
