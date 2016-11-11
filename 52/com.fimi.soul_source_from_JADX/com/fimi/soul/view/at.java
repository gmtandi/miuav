package com.fimi.soul.view;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.fimi.kernel.p084e.C1186y;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;
import it.p074a.p075a.C2799f;

public class at {
    private static final int f10687o = 0;
    private static final int f10688p = 115;
    private Context f10689a;
    private String f10690b;
    private String f10691c;
    private int f10692d;
    private String f10693e;
    private int f10694f;
    private int f10695g;
    private int f10696h;
    private String f10697i;
    private String f10698j;
    private boolean f10699k;
    private OnClickListener f10700l;
    private OnClickListener f10701m;
    private OnSeekBarChangeListener f10702n;

    public at(Context context) {
        this.f10694f = C2799f.f14263a;
        this.f10695g = f10687o;
        this.f10696h = f10687o;
        this.f10699k = false;
        this.f10689a = context;
    }

    private void m12757a(TextView textView) {
        textView.getPaint().setFakeBoldText(true);
    }

    public ap m12765a() {
        LayoutInflater layoutInflater = (LayoutInflater) this.f10689a.getSystemService("layout_inflater");
        ap apVar = new ap(this.f10689a, C1205R.style.DropDialog1);
        View inflate = layoutInflater.inflate(C1205R.layout.vertical_screen_dialog_double_button_progress, null);
        TextView textView = (TextView) inflate.findViewById(C1205R.id.msg_tv);
        Button button = (Button) inflate.findViewById(C1205R.id.right_btn);
        Button button2 = (Button) inflate.findViewById(C1205R.id.left_btn);
        SeekBar seekBar = (SeekBar) inflate.findViewById(C1205R.id.seekbar);
        TextView textView2 = (TextView) inflate.findViewById(C1205R.id.min_progress_tv);
        TextView textView3 = (TextView) inflate.findViewById(C1205R.id.max_progress_tv);
        TextView textView4 = (TextView) inflate.findViewById(C1205R.id.progress_tv);
        TextView textView5 = (TextView) inflate.findViewById(C1205R.id.dialog_unit_tv);
        if (this.f10698j != null) {
            textView5.setText(this.f10698j);
        }
        seekBar.setMax(this.f10695g - this.f10696h);
        textView2.setText(this.f10696h + this.f10697i);
        textView3.setText(this.f10695g + this.f10697i);
        seekBar.setProgress(this.f10694f - this.f10696h);
        if (!this.f10699k) {
            textView4.setText(this.f10694f + this.f10697i);
        } else if (this.f10694f <= 6) {
            textView4.setText(this.f10694f + this.f10689a.getString(C1205R.string.speed_unit_m) + this.f10689a.getString(C1205R.string.setting_speed_low));
        } else if (this.f10694f <= 6 || this.f10694f > 10) {
            textView4.setText(this.f10694f + this.f10689a.getString(C1205R.string.speed_unit_m) + this.f10689a.getString(C1205R.string.setting_speed_hight));
        } else {
            textView4.setText(this.f10694f + this.f10689a.getString(C1205R.string.speed_unit_m) + this.f10689a.getString(C1205R.string.setting_speed_standard));
        }
        seekBar.setOnSeekBarChangeListener(new au(this, textView4));
        if (this.f10691c != null) {
            button.setText(this.f10691c);
        }
        if (this.f10700l != null) {
            button.setOnClickListener(new av(this, apVar));
        }
        if (this.f10692d != -1) {
            button.setTextColor(this.f10692d);
        }
        if (this.f10693e != null) {
            button2.setText(this.f10691c);
        }
        if (this.f10701m != null) {
            button2.setOnClickListener(new aw(this, apVar));
        }
        if (this.f10690b != null) {
            textView.setText(this.f10690b);
        }
        be.m12359a(this.f10689a.getAssets(), textView, button, button2, textView5);
        be.m12368b(this.f10689a.getAssets(), textView2, textView3, textView4);
        m12757a(textView);
        m12757a(textView5);
        m12757a(textView4);
        m12757a(textView2);
        m12757a(textView3);
        apVar.setContentView(inflate);
        Window window = apVar.getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (((C1186y.m8308b(this.f10689a) > ((float) C1186y.m8298a(this.f10689a)) ? C1186y.m8308b(this.f10689a) : (float) C1186y.m8298a(this.f10689a)) * 1048.0f) / 1920.0f);
        window.setAttributes(attributes);
        apVar.getWindow().getDecorView().setBackgroundColor(f10687o);
        return apVar;
    }

    public at m12766a(int i) {
        this.f10696h = i;
        return this;
    }

    public at m12767a(OnSeekBarChangeListener onSeekBarChangeListener) {
        this.f10702n = onSeekBarChangeListener;
        return this;
    }

    public at m12768a(String str) {
        this.f10697i = str;
        return this;
    }

    public at m12769a(String str, OnClickListener onClickListener) {
        this.f10701m = onClickListener;
        this.f10691c = str;
        return this;
    }

    public at m12770a(boolean z) {
        this.f10699k = z;
        return this;
    }

    public at m12771b(int i) {
        this.f10695g = i;
        return this;
    }

    public at m12772b(String str) {
        this.f10698j = str;
        return this;
    }

    public at m12773b(String str, OnClickListener onClickListener) {
        this.f10700l = onClickListener;
        this.f10691c = str;
        return this;
    }

    public at m12774c(int i) {
        this.f10694f = i;
        return this;
    }

    public at m12775c(String str) {
        this.f10690b = str;
        return this;
    }

    public at m12776d(int i) {
        this.f10692d = i;
        return this;
    }

    public at m12777d(String str) {
        this.f10691c = str;
        return this;
    }

    public at m12778e(String str) {
        this.f10693e = str;
        return this;
    }
}
