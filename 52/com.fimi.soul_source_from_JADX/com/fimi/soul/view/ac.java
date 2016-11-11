package com.fimi.soul.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.widget.RadioButton;

public class ac extends Dialog {
    public ac(Context context) {
        super(context);
    }

    public ac(Context context, int i) {
        super(context, i);
    }

    public ac(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    public static void m12728a(RadioButton radioButton, int i) {
        radioButton.setTextColor(radioButton.getTextColors().withAlpha(i));
    }
}
