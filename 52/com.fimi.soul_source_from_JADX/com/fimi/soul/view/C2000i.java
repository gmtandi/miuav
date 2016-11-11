package com.fimi.soul.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.kernel.view.dialog.C1198a;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;

/* renamed from: com.fimi.soul.view.i */
class C2000i extends AlertDialog {
    Context f10835a;
    String f10836b;
    String f10837c;
    C1198a f10838d;
    int f10839e;
    String f10840f;
    String f10841g;
    final /* synthetic */ C1989c f10842h;

    protected C2000i(C1989c c1989c, Context context, String str, String str2, int i, String str3, String str4, C1198a c1198a) {
        this.f10842h = c1989c;
        super(context);
        this.f10835a = context;
        this.f10836b = str;
        this.f10837c = str2;
        this.f10838d = c1198a;
        this.f10840f = str3;
        this.f10839e = i;
        this.f10841g = str4;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.dialog_double_button);
        Button button = (Button) findViewById(C1205R.id.left_btn);
        Button button2 = (Button) findViewById(C1205R.id.right_btn);
        TextView textView = (TextView) findViewById(C1205R.id.msg_tv);
        TextView textView2 = (TextView) findViewById(C1205R.id.content_tv);
        if (this.f10837c == null) {
            textView2.setVisibility(8);
        }
        textView2.setText(this.f10837c);
        textView.setText(this.f10836b);
        button2.setTextColor(this.f10839e);
        button.setText(this.f10840f);
        button2.setText(this.f10841g);
        be.m12359a(this.f10835a.getAssets(), button2, button, textView, textView2);
        textView.setTextColor(-872415232);
        textView.getPaint().setFakeBoldText(true);
        button.setOnClickListener(new C2001j(this));
        button2.setOnClickListener(new C2002k(this));
    }
}
