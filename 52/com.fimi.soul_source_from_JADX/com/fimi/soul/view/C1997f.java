package com.fimi.soul.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.fimi.kernel.view.dialog.C1198a;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;

/* renamed from: com.fimi.soul.view.f */
class C1997f extends AlertDialog {
    Context f10822a;
    String f10823b;
    C1198a f10824c;
    int f10825d;
    String f10826e;
    String f10827f;
    Boolean f10828g;
    String f10829h;
    final /* synthetic */ C1989c f10830i;

    protected C1997f(C1989c c1989c, Context context, String str, String str2, String str3, Boolean bool, String str4, C1198a c1198a) {
        this.f10830i = c1989c;
        super(context);
        this.f10822a = context;
        this.f10823b = str;
        this.f10824c = c1198a;
        this.f10826e = str2;
        this.f10827f = str3;
        this.f10828g = bool;
        this.f10829h = str4;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.dialog_double_checkbox_button);
        Button button = (Button) findViewById(C1205R.id.left_btn);
        Button button2 = (Button) findViewById(C1205R.id.right_btn);
        ((TextView) findViewById(C1205R.id.check_tv)).setText(this.f10829h);
        CheckBox checkBox = (CheckBox) findViewById(C1205R.id.checkbox);
        checkBox.setChecked(this.f10828g.booleanValue());
        TextView textView = (TextView) findViewById(C1205R.id.msg_tv);
        textView.setText(this.f10823b);
        button.setText(this.f10826e);
        button2.setText(this.f10827f);
        be.m12359a(this.f10822a.getAssets(), button2, button, r2, textView);
        textView.setTextColor(-872415232);
        textView.getPaint().setFakeBoldText(true);
        button.setOnClickListener(new C1998g(this, checkBox));
        button2.setOnClickListener(new C1999h(this, checkBox));
    }
}
