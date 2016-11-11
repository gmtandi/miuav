package com.fimi.soul.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.kernel.view.dialog.C1198a;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;

/* renamed from: com.fimi.soul.view.l */
class C2003l extends AlertDialog {
    Context f10845a;
    String f10846b;
    String f10847c;
    C1198a f10848d;
    final /* synthetic */ C1989c f10849e;

    protected C2003l(C1989c c1989c, Context context, String str, String str2, C1198a c1198a) {
        this.f10849e = c1989c;
        super(context);
        this.f10845a = context;
        this.f10846b = str;
        this.f10847c = str2;
        this.f10848d = c1198a;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.dialog_pop);
        TextView textView = (TextView) findViewById(C1205R.id.msg_child_tv);
        ((Button) findViewById(C1205R.id.dialog_btn)).setOnClickListener(new C2004m(this));
        TextView textView2 = (TextView) findViewById(C1205R.id.msg_tv);
        textView2.setText(this.f10846b);
        textView.setText(this.f10847c);
        be.m12359a(this.f10845a.getAssets(), textView2, r1, textView);
        textView2.setTextColor(-872415232);
        textView2.getPaint().setFakeBoldText(true);
        textView.getPaint().setFakeBoldText(true);
    }
}
