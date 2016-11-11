package com.fimi.soul.view;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import com.fimi.kernel.p084e.C1186y;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.p090b.C1253k;
import com.fimi.soul.utils.be;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class ad {
    private Context f10655a;
    private String f10656b;
    private String f10657c;
    private OnClickListener f10658d;
    private C1253k f10659e;
    private boolean f10660f;

    public ad(Context context) {
        this.f10655a = context;
        this.f10659e = C1253k.m8598a(context);
    }

    public ac m12733a() {
        SharedPreferences a = com.fimi.kernel.p084e.ad.m8019a(this.f10655a);
        LayoutInflater layoutInflater = (LayoutInflater) this.f10655a.getSystemService("layout_inflater");
        ac acVar = new ac(this.f10655a, C1205R.style.DropDialog1);
        View inflate = layoutInflater.inflate(C1205R.layout.flyactiontipdialog, null);
        TextView textView = (TextView) inflate.findViewById(C1205R.id.action_tip);
        TextView textView2 = (TextView) inflate.findViewById(C1205R.id.title);
        Button button = (Button) inflate.findViewById(C1205R.id.single_btn);
        RadioButton radioButton = (RadioButton) inflate.findViewById(C1205R.id.remeber_tip);
        ac.m12728a(radioButton, Opcodes.LAND);
        radioButton.setOnClickListener(new ae(this, radioButton, a));
        if (this.f10657c != null) {
            button.setText(this.f10657c);
        }
        if (this.f10656b != null) {
            textView.setText(this.f10656b);
        }
        if (this.f10658d != null) {
            button.setOnClickListener(new af(this, acVar));
        }
        be.m12359a(this.f10655a.getAssets(), textView, button, radioButton, textView2);
        acVar.setContentView(inflate);
        Window window = acVar.getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (((C1186y.m8308b(this.f10655a) > ((float) C1186y.m8298a(this.f10655a)) ? C1186y.m8308b(this.f10655a) : (float) C1186y.m8298a(this.f10655a)) * 1032.0f) / 1920.0f);
        window.setAttributes(attributes);
        acVar.getWindow().getDecorView().setBackgroundColor(0);
        return acVar;
    }

    public ad m12734a(OnClickListener onClickListener) {
        this.f10658d = onClickListener;
        return this;
    }

    public ad m12735a(String str) {
        this.f10656b = str;
        return this;
    }

    public ad m12736a(String str, OnClickListener onClickListener) {
        this.f10658d = onClickListener;
        this.f10657c = str;
        return this;
    }

    public ad m12737b(String str) {
        this.f10657c = str;
        return this;
    }
}
