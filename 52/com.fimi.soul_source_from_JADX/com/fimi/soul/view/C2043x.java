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

/* renamed from: com.fimi.soul.view.x */
public class C2043x {
    private Context f11088a;
    private String f11089b;
    private String f11090c;
    private String f11091d;
    private int f11092e;
    private int f11093f;
    private OnClickListener f11094g;
    private boolean f11095h;

    public C2043x(Context context) {
        this.f11092e = -1;
        this.f11093f = -1;
        this.f11095h = false;
        this.f11088a = context;
    }

    public C2017n m13041a() {
        LayoutInflater layoutInflater = (LayoutInflater) this.f11088a.getSystemService("layout_inflater");
        C2017n c2017n = new C2017n(this.f11088a, C1205R.style.DropDialog1);
        View inflate = layoutInflater.inflate(C1205R.layout.dialog_pop, null);
        TextView textView = (TextView) inflate.findViewById(C1205R.id.msg_child_tv);
        Button button = (Button) inflate.findViewById(C1205R.id.dialog_btn);
        TextView textView2 = (TextView) inflate.findViewById(C1205R.id.msg_tv);
        if (this.f11089b != null) {
            textView2.setText(this.f11089b);
        }
        if (this.f11091d != null) {
            button.setText(this.f11091d);
        }
        if (this.f11093f != -1) {
            textView2.setMaxEms(this.f11093f);
        }
        if (this.f11090c != null) {
            textView.setVisibility(0);
            textView.setText(this.f11090c);
        } else {
            textView.setVisibility(8);
        }
        if (this.f11094g != null) {
            button.setOnClickListener(new C2044y(this, c2017n));
        }
        be.m12359a(this.f11088a.getAssets(), textView2, button, textView);
        textView2.getPaint().setFakeBoldText(true);
        if (this.f11090c != null) {
            textView.getPaint().setFakeBoldText(true);
        }
        c2017n.setContentView(inflate);
        Window window = c2017n.getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.height = (int) this.f11088a.getResources().getDimension(C1205R.dimen.single_dialog_height);
        attributes.width = (int) this.f11088a.getResources().getDimension(C1205R.dimen.single_dialog_width);
        window.setAttributes(attributes);
        c2017n.getWindow().getDecorView().setBackgroundColor(0);
        return c2017n;
    }

    public C2043x m13042a(int i) {
        this.f11093f = i;
        return this;
    }

    public C2043x m13043a(Context context) {
        this.f11088a = context;
        return this;
    }

    public C2043x m13044a(String str) {
        this.f11089b = str;
        return this;
    }

    public C2043x m13045a(String str, OnClickListener onClickListener) {
        this.f11091d = str;
        this.f11094g = onClickListener;
        return this;
    }

    public C2043x m13046b(String str) {
        this.f11090c = str;
        return this;
    }
}
