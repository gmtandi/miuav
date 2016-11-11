package com.fimi.kernel.view.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.fimi.kernel.C1087R;

/* renamed from: com.fimi.kernel.view.dialog.b */
public class C1199b extends ProgressDialog {
    private String f5402a;

    public C1199b(Context context) {
        super(context);
    }

    public C1199b(Context context, int i) {
        super(context, i);
    }

    public void m8388a(String str) {
        this.f5402a = str;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1087R.layout.dialog_easyprogress);
        TextView textView = (TextView) findViewById(C1087R.id.pb_easyMessage);
        if (this.f5402a != null) {
            textView.setText(this.f5402a);
        }
    }
}
