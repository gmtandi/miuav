package com.fimi.soul.view;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;

/* renamed from: com.fimi.soul.view.r */
public class C2031r {
    private Context f10986a;
    private String f10987b;
    private String f10988c;
    private String f10989d;
    private String f10990e;
    private int f10991f;
    private int f10992g;
    private boolean f10993h;
    private OnClickListener f10994i;
    private OnClickListener f10995j;
    private boolean f10996k;

    public C2031r(Context context) {
        this.f10991f = -1;
        this.f10992g = -1;
        this.f10996k = false;
        this.f10986a = context;
    }

    public C2017n m12982a() {
        LayoutInflater layoutInflater = (LayoutInflater) this.f10986a.getSystemService("layout_inflater");
        C2017n c2017n = new C2017n(this.f10986a, C1205R.style.DropDialog1);
        View inflate = layoutInflater.inflate(C1205R.layout.dialog_double_checkbox_button, null);
        Button button = (Button) inflate.findViewById(C1205R.id.left_btn);
        Button button2 = (Button) inflate.findViewById(C1205R.id.right_btn);
        TextView textView = (TextView) inflate.findViewById(C1205R.id.check_tv);
        textView.setText(this.f10990e);
        CheckBox checkBox = (CheckBox) inflate.findViewById(C1205R.id.checkbox);
        checkBox.setChecked(this.f10993h);
        TextView textView2 = (TextView) inflate.findViewById(C1205R.id.msg_tv);
        if (this.f10987b != null) {
            textView2.setText(this.f10987b);
        }
        if (this.f10989d != null) {
            button.setText(this.f10989d);
        }
        if (this.f10988c != null) {
            button2.setText(this.f10988c);
        }
        if (this.f10990e != null) {
            textView.setText(this.f10990e);
        }
        if (this.f10993h) {
            checkBox.setChecked(this.f10993h);
        } else {
            checkBox.setChecked(this.f10993h);
        }
        be.m12359a(this.f10986a.getAssets(), button2, button, textView, textView2);
        textView2.setTextColor(-872415232);
        textView2.getPaint().setFakeBoldText(true);
        if (this.f10994i != null) {
            button2.setOnClickListener(new C2032s(this, c2017n));
            button.setOnClickListener(new C2035t(this, c2017n));
        }
        c2017n.setContentView(inflate);
        Window window = c2017n.getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.width = (int) this.f10986a.getResources().getDimension(C1205R.dimen.double_choose_dialog_width);
        window.setAttributes(attributes);
        c2017n.getWindow().getDecorView().setBackgroundColor(0);
        return c2017n;
    }

    public C2031r m12983a(int i) {
        this.f10991f = i;
        return this;
    }

    public C2031r m12984a(String str) {
        this.f10987b = str;
        return this;
    }

    public C2031r m12985a(String str, OnClickListener onClickListener) {
        this.f10988c = str;
        this.f10994i = onClickListener;
        return this;
    }

    public C2031r m12986a(boolean z) {
        this.f10993h = z;
        return this;
    }

    public C2031r m12987b(int i) {
        this.f10992g = i;
        return this;
    }

    public C2031r m12988b(String str) {
        this.f10990e = str;
        return this;
    }

    public C2031r m12989b(String str, OnClickListener onClickListener) {
        this.f10989d = str;
        this.f10995j = onClickListener;
        return this;
    }
}
