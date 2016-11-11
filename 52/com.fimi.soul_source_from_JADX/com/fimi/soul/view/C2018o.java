package com.fimi.soul.view;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;

/* renamed from: com.fimi.soul.view.o */
public class C2018o {
    private Context f10917a;
    private String f10918b;
    private String f10919c;
    private String f10920d;
    private String f10921e;
    private String f10922f;
    private int f10923g;
    private int f10924h;
    private int f10925i;
    private OnClickListener f10926j;
    private OnClickListener f10927k;
    private boolean f10928l;

    public C2018o(Context context) {
        this.f10923g = -1;
        this.f10924h = -1;
        this.f10925i = -1;
        this.f10928l = true;
        this.f10917a = context;
    }

    public C2017n m12918a() {
        LayoutInflater layoutInflater = (LayoutInflater) this.f10917a.getSystemService("layout_inflater");
        C2017n c2017n = new C2017n(this.f10917a, C1205R.style.DropDialog1);
        View inflate = layoutInflater.inflate(C1205R.layout.dialog_double_button, null);
        Button button = (Button) inflate.findViewById(C1205R.id.left_btn);
        Button button2 = (Button) inflate.findViewById(C1205R.id.right_btn);
        TextView textView = (TextView) inflate.findViewById(C1205R.id.msg_tv);
        TextView textView2 = (TextView) inflate.findViewById(C1205R.id.content_tv);
        be.m12359a(this.f10917a.getAssets(), button2, button, textView, textView2);
        textView.getPaint().setFakeBoldText(true);
        textView.setTextColor(-872415232);
        if (this.f10920d != null) {
            button2.setText(this.f10920d);
        }
        if (this.f10921e != null) {
            button.setText(this.f10921e);
        }
        if (this.f10919c != null) {
            textView.setText(this.f10919c);
        }
        if (this.f10923g != -1) {
            button2.setTextColor(this.f10923g);
        }
        if (this.f10924h != -1) {
            button.setTextColor(this.f10924h);
        }
        if (this.f10922f != null) {
            textView2.setVisibility(0);
            textView2.setText(this.f10922f);
        } else {
            textView2.setVisibility(8);
        }
        if (this.f10925i != -1) {
            textView.setMaxEms(this.f10925i);
        }
        if (this.f10926j != null) {
            button2.setOnClickListener(new C2019p(this, c2017n));
        }
        if (this.f10927k != null) {
            button.setOnClickListener(new C2030q(this, c2017n));
        }
        if (this.f10928l) {
            c2017n.setCanceledOnTouchOutside(true);
        } else {
            c2017n.setCanceledOnTouchOutside(false);
        }
        c2017n.setContentView(inflate);
        Window window = c2017n.getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.width = (int) this.f10917a.getResources().getDimension(C1205R.dimen.double_dialog_width);
        window.setAttributes(attributes);
        c2017n.getWindow().getDecorView().setBackgroundColor(0);
        return c2017n;
    }

    public C2018o m12919a(int i) {
        this.f10923g = i;
        return this;
    }

    public C2018o m12920a(String str) {
        this.f10918b = str;
        return this;
    }

    public C2018o m12921a(String str, OnClickListener onClickListener) {
        this.f10920d = str;
        this.f10926j = onClickListener;
        return this;
    }

    public C2018o m12922a(boolean z) {
        this.f10928l = z;
        return this;
    }

    public C2018o m12923b(int i) {
        this.f10924h = i;
        return this;
    }

    public C2018o m12924b(String str) {
        this.f10919c = str;
        return this;
    }

    public C2018o m12925b(String str, OnClickListener onClickListener) {
        this.f10921e = str;
        this.f10927k = onClickListener;
        return this;
    }

    public C2018o m12926c(int i) {
        this.f10925i = i;
        return this;
    }

    public C2018o m12927c(String str) {
        this.f10922f = str;
        return this;
    }
}
