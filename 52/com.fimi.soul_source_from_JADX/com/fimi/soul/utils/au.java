package com.fimi.soul.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.soul.C1205R;

public class au extends Dialog {
    Dialog f10047a;
    Runnable f10048b;
    private aw f10049c;
    private Context f10050d;
    private int f10051e;
    private TextView f10052f;
    private TextView f10053g;
    private ImageView f10054h;
    private Handler f10055i;

    public au(Context context, aw awVar, int i) {
        super(context, C1205R.style.Dialog_Fullscreen);
        this.f10055i = new Handler();
        this.f10048b = new av(this);
        this.f10050d = context;
        this.f10049c = awVar;
        this.f10051e = i;
        this.f10055i.post(this.f10048b);
    }

    public void m12288a(int i) {
        if (this.f10054h != null) {
            this.f10054h.setImageResource(i);
        }
    }

    public void m12289a(String str) {
        if (this.f10052f != null) {
            this.f10052f.setText(str);
        }
    }

    public void m12290b(String str) {
        if (this.f10052f != null) {
            this.f10053g.setText(str);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        getWindow().setAttributes(attributes);
        setContentView(C1205R.layout.lostcontroldialog);
        this.f10054h = (ImageView) findViewById(C1205R.id.erroric);
        this.f10052f = (TextView) findViewById(C1205R.id.title);
        this.f10053g = (TextView) findViewById(C1205R.id.desmessage);
        this.f10052f.getPaint().setFakeBoldText(true);
        be.m12359a(this.f10050d.getAssets(), this.f10052f, this.f10053g);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (4 == i && this.f10049c != null) {
            this.f10049c.m8983a();
        }
        return super.onKeyDown(i, keyEvent);
    }
}
