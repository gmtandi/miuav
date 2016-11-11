package com.fimi.soul.view;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.kernel.p084e.C1186y;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;

public class aq {
    private Context f10674a;
    private String f10675b;
    private String f10676c;
    private String f10677d;
    private String f10678e;
    private int f10679f;
    private int f10680g;
    private int f10681h;
    private OnClickListener f10682i;
    private OnClickListener f10683j;
    private ap f10684k;

    public aq(Context context) {
        this.f10681h = -1;
        this.f10674a = context;
        this.f10684k = new ap(this.f10674a, C1205R.style.DropDialog1);
    }

    private void m12743a(TextView textView) {
        textView.getPaint().setFakeBoldText(true);
    }

    public ap m12746a() {
        LayoutInflater layoutInflater = (LayoutInflater) this.f10674a.getSystemService("layout_inflater");
        View inflate = this.f10678e != null ? layoutInflater.inflate(C1205R.layout.vertical_screen_dialog_content_double_button, null) : layoutInflater.inflate(C1205R.layout.vertical_screen_dialog_double_button, null);
        TextView textView = (TextView) inflate.findViewById(C1205R.id.msg_tv);
        Button button = (Button) inflate.findViewById(C1205R.id.right_btn);
        Button button2 = (Button) inflate.findViewById(C1205R.id.left_btn);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        if (this.f10678e != null) {
            TextView textView2 = (TextView) inflate.findViewById(C1205R.id.content_tv);
            textView2.setText(this.f10678e);
            textView2.setMovementMethod(ScrollingMovementMethod.getInstance());
            be.m12359a(this.f10674a.getAssets(), textView2);
        }
        if (this.f10679f != 0) {
            button.setTextColor(this.f10679f);
        }
        if (this.f10680g > 0) {
            button2.setTextColor(this.f10680g);
        }
        if (this.f10676c != null) {
            button.setText(this.f10676c);
        }
        if (this.f10682i != null) {
            button.setOnClickListener(new ar(this));
        }
        if (this.f10677d != null) {
            button2.setText(this.f10677d);
        }
        if (this.f10683j != null) {
            button2.setOnClickListener(new as(this));
        }
        if (this.f10675b != null) {
            textView.setText(this.f10675b);
        }
        if (this.f10681h != -1) {
            textView.setGravity(this.f10681h);
        }
        be.m12359a(this.f10674a.getAssets(), textView, button, button2);
        if (this.f10678e != null) {
            textView.getPaint().setFakeBoldText(true);
        }
        this.f10684k.setContentView(inflate);
        Window window = this.f10684k.getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (((C1186y.m8308b(this.f10674a) > ((float) C1186y.m8298a(this.f10674a)) ? C1186y.m8308b(this.f10674a) : (float) C1186y.m8298a(this.f10674a)) * 1048.0f) / 1920.0f);
        window.setAttributes(attributes);
        this.f10684k.getWindow().getDecorView().setBackgroundColor(0);
        return this.f10684k;
    }

    public aq m12747a(int i) {
        this.f10679f = i;
        return this;
    }

    public aq m12748a(String str) {
        this.f10675b = str;
        return this;
    }

    public aq m12749a(String str, OnClickListener onClickListener) {
        this.f10683j = onClickListener;
        this.f10677d = str;
        return this;
    }

    public ap m12750b() {
        return this.f10684k;
    }

    public aq m12751b(int i) {
        this.f10680g = i;
        return this;
    }

    public aq m12752b(String str) {
        this.f10678e = str;
        return this;
    }

    public aq m12753b(String str, OnClickListener onClickListener) {
        this.f10682i = onClickListener;
        this.f10676c = str;
        return this;
    }

    public aq m12754c(int i) {
        this.f10681h = i;
        return this;
    }

    public aq m12755c(String str) {
        this.f10676c = str;
        return this;
    }

    public aq m12756d(String str) {
        this.f10677d = str;
        return this;
    }
}
