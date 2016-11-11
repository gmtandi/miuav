package com.fimi.soul.view;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.kernel.p084e.C1186y;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;

public class ax {
    private ap f10709a;
    private Context f10710b;
    private String f10711c;
    private String f10712d;
    private OnClickListener f10713e;

    public ax(Context context) {
        this.f10710b = context;
        this.f10709a = new ap(this.f10710b, C1205R.style.DropDialog1);
    }

    public ap m12781a() {
        View inflate = ((LayoutInflater) this.f10710b.getSystemService("layout_inflater")).inflate(C1205R.layout.horizon_single_button_dialog, null);
        TextView textView = (TextView) inflate.findViewById(C1205R.id.msg_tv);
        Button button = (Button) inflate.findViewById(C1205R.id.single_btn);
        if (this.f10712d != null) {
            button.setText(this.f10712d);
        }
        if (this.f10711c != null) {
            textView.setText(this.f10711c);
        }
        if (this.f10713e != null) {
            button.setOnClickListener(new ay(this));
        }
        be.m12359a(this.f10710b.getAssets(), textView, button);
        this.f10709a.setContentView(inflate);
        Window window = this.f10709a.getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (((C1186y.m8308b(this.f10710b) > ((float) C1186y.m8298a(this.f10710b)) ? C1186y.m8308b(this.f10710b) : (float) C1186y.m8298a(this.f10710b)) * 1032.0f) / 1920.0f);
        window.setAttributes(attributes);
        this.f10709a.getWindow().getDecorView().setBackgroundColor(0);
        return this.f10709a;
    }

    public ax m12782a(OnClickListener onClickListener) {
        this.f10713e = onClickListener;
        return this;
    }

    public ax m12783a(String str) {
        this.f10711c = str;
        return this;
    }

    public ax m12784a(String str, OnClickListener onClickListener) {
        this.f10713e = onClickListener;
        this.f10712d = str;
        return this;
    }

    public ap m12785b() {
        return this.f10709a;
    }

    public ax m12786b(String str) {
        this.f10712d = str;
        return this;
    }
}
