package com.fimi.soul.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.kernel.p084e.C1186y;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;

public class az {
    private Context f10715a;
    private String f10716b;
    private String f10717c;
    private int f10718d;
    private OnClickListener f10719e;

    public az(Context context) {
        this.f10718d = 0;
        this.f10715a = context;
    }

    @TargetApi(16)
    public ap m12788a() {
        LayoutInflater layoutInflater = (LayoutInflater) this.f10715a.getSystemService("layout_inflater");
        ap apVar = new ap(this.f10715a, C1205R.style.DropDialog1);
        View inflate = layoutInflater.inflate(C1205R.layout.usb_share_dialog, null);
        TextView textView = (TextView) inflate.findViewById(C1205R.id.msg_tv);
        ImageView imageView = (ImageView) inflate.findViewById(C1205R.id.imageview);
        Button button = (Button) inflate.findViewById(C1205R.id.single_btn);
        if (this.f10718d != 0) {
            imageView.setBackground(this.f10715a.getResources().getDrawable(this.f10718d));
        }
        if (this.f10716b != null) {
            CharSequence spannableStringBuilder = new SpannableStringBuilder(this.f10716b);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(this.f10715a.getResources().getColor(C1205R.color.setting_dialog_force_attitude)), 5, 8, 33);
            textView.setText(spannableStringBuilder);
        }
        if (this.f10717c != null) {
            button.setText(this.f10717c);
        }
        if (this.f10719e != null) {
            button.setOnClickListener(new ba(this, apVar));
        }
        be.m12359a(this.f10715a.getAssets(), textView, button);
        apVar.setContentView(inflate);
        Window window = apVar.getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (((C1186y.m8308b(this.f10715a) > ((float) C1186y.m8298a(this.f10715a)) ? C1186y.m8308b(this.f10715a) : (float) C1186y.m8298a(this.f10715a)) * 1032.0f) / 1920.0f);
        window.setAttributes(attributes);
        apVar.getWindow().getDecorView().setBackgroundColor(0);
        return apVar;
    }

    public az m12789a(int i) {
        this.f10718d = i;
        return this;
    }

    public az m12790a(String str) {
        this.f10716b = str;
        return this;
    }

    public az m12791a(String str, OnClickListener onClickListener) {
        this.f10719e = onClickListener;
        this.f10717c = str;
        return this;
    }

    public az m12792b(String str) {
        this.f10717c = str;
        return this;
    }
}
