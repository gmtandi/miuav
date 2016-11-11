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

/* renamed from: com.fimi.soul.view.u */
public class C2040u {
    private Context f11072a;
    private String f11073b;
    private String f11074c;
    private String f11075d;
    private String f11076e;
    private String f11077f;
    private int f11078g;
    private int f11079h;
    private int f11080i;
    private OnClickListener f11081j;
    private OnClickListener f11082k;
    private boolean f11083l;

    public C2040u(Context context) {
        this.f11078g = -1;
        this.f11079h = -1;
        this.f11080i = -1;
        this.f11083l = true;
        this.f11072a = context;
    }

    public C2017n m13030a() {
        LayoutInflater layoutInflater = (LayoutInflater) this.f11072a.getSystemService("layout_inflater");
        C2017n c2017n = new C2017n(this.f11072a, C1205R.style.DropDialog1);
        View inflate = layoutInflater.inflate(C1205R.layout.dialog_double_button_insurance, null);
        Button button = (Button) inflate.findViewById(C1205R.id.left_btn);
        Button button2 = (Button) inflate.findViewById(C1205R.id.right_btn);
        TextView textView = (TextView) inflate.findViewById(C1205R.id.msg_tv);
        TextView textView2 = (TextView) inflate.findViewById(C1205R.id.content_tv);
        be.m12359a(this.f11072a.getAssets(), button2, button, textView, textView2);
        textView.getPaint().setFakeBoldText(true);
        textView.setTextColor(-872415232);
        if (this.f11075d != null) {
            button2.setText(this.f11075d);
        }
        if (this.f11076e != null) {
            button.setText(this.f11076e);
        }
        if (this.f11073b != null) {
            textView.setText(this.f11073b);
        }
        if (this.f11074c != null) {
            textView2.setText(this.f11074c);
        }
        if (this.f11078g != -1) {
            button2.setTextColor(this.f11078g);
        }
        if (this.f11079h != -1) {
            button.setTextColor(this.f11079h);
        }
        if (this.f11080i != -1) {
            textView.setMaxEms(this.f11080i);
        }
        if (this.f11081j != null) {
            button2.setOnClickListener(new C2041v(this, c2017n));
        }
        if (this.f11082k != null) {
            button.setOnClickListener(new C2042w(this, c2017n));
        }
        if (this.f11083l) {
            c2017n.setCanceledOnTouchOutside(true);
        } else {
            c2017n.setCanceledOnTouchOutside(false);
        }
        c2017n.setContentView(inflate);
        Window window = c2017n.getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.width = (int) this.f11072a.getResources().getDimension(C1205R.dimen.double_dialog_width);
        window.setAttributes(attributes);
        c2017n.getWindow().getDecorView().setBackgroundColor(0);
        return c2017n;
    }

    public C2040u m13031a(int i) {
        this.f11078g = i;
        return this;
    }

    public C2040u m13032a(String str) {
        this.f11073b = str;
        return this;
    }

    public C2040u m13033a(String str, OnClickListener onClickListener) {
        this.f11075d = str;
        this.f11081j = onClickListener;
        return this;
    }

    public C2040u m13034a(boolean z) {
        this.f11083l = z;
        return this;
    }

    public C2040u m13035b(int i) {
        this.f11079h = i;
        return this;
    }

    public C2040u m13036b(String str) {
        this.f11074c = str;
        return this;
    }

    public C2040u m13037b(String str, OnClickListener onClickListener) {
        this.f11076e = str;
        this.f11082k = onClickListener;
        return this;
    }

    public C2040u m13038c(int i) {
        this.f11080i = i;
        return this;
    }

    public C2040u m13039c(String str) {
        this.f11077f = str;
        return this;
    }
}
