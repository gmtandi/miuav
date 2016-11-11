package com.fimi.soul.module.droneui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;

/* renamed from: com.fimi.soul.module.droneui.g */
public class C1746g extends Dialog implements OnClickListener {
    Runnable f8603a;
    final Handler f8604b;
    private String f8605c;
    private String f8606d;
    private int f8607e;
    private boolean f8608f;
    private Context f8609g;
    private C1686j f8610h;
    private int f8611i;

    public C1746g(Context context, String str, String str2, int i, int i2, boolean z, C1686j c1686j) {
        super(context, C1205R.style.Dialog_Fullscreen);
        this.f8605c = null;
        this.f8606d = null;
        this.f8607e = 0;
        this.f8608f = false;
        this.f8611i = 0;
        this.f8603a = new C1747h(this);
        this.f8604b = new C1748i(this);
        this.f8609g = context;
        this.f8605c = str;
        this.f8606d = str2;
        this.f8607e = i;
        this.f8608f = z;
        this.f8610h = c1686j;
        this.f8611i = i2;
        this.f8604b.postDelayed(this.f8603a, 1000);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.command_one:
                if (this.f8610h != null) {
                    this.f8610h.m11021b();
                }
            case C1205R.id.command_two:
                if (this.f8610h != null) {
                    this.f8610h.m11022c();
                }
            default:
        }
    }

    protected void onCreate(Bundle bundle) {
        int i = 0;
        super.onCreate(bundle);
        setContentView(C1205R.layout.activity_fault);
        TextView textView = (TextView) findViewById(C1205R.id.fault_describe);
        ImageView imageView = (ImageView) findViewById(C1205R.id.error_icon);
        be.m12359a(this.f8609g.getAssets(), (TextView) findViewById(C1205R.id.fault_title));
        be.m12359a(this.f8609g.getAssets(), textView);
        r0.setText(this.f8605c);
        textView.setText(this.f8606d);
        if (this.f8607e != 0) {
            imageView.setImageBitmap(BitmapFactory.decodeResource(this.f8609g.getResources(), this.f8607e));
        }
        findViewById(C1205R.id.command_one).setVisibility(this.f8608f ? 0 : 8);
        View findViewById = findViewById(C1205R.id.command_two);
        if (!this.f8608f) {
            i = 8;
        }
        findViewById.setVisibility(i);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && this.f8610h != null) {
            this.f8610h.m11020a();
        }
        return super.onKeyDown(i, keyEvent);
    }
}
