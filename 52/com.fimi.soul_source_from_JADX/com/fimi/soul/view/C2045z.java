package com.fimi.soul.view;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import java.util.List;

/* renamed from: com.fimi.soul.view.z */
public class C2045z extends PopupWindow {
    final Handler f11098a;
    Runnable f11099b;
    private LinearLayout f11100c;
    private LayoutParams f11101d;
    private Context f11102e;
    private int f11103f;

    public C2045z(Context context, int i, int i2) {
        super(context);
        this.f11101d = null;
        this.f11098a = new Handler();
        this.f11103f = 2;
        this.f11099b = new ab(this);
        this.f11102e = context;
        this.f11101d = new LayoutParams(-2, -2);
        this.f11101d.gravity = 17;
        this.f11101d.topMargin = 10;
        this.f11100c = new LinearLayout(context);
        this.f11100c.setOrientation(1);
        this.f11100c.setGravity(1);
        setContentView(this.f11100c);
        setWidth(-2);
        setHeight(i2);
        setBackgroundDrawable(context.getResources().getDrawable(C1205R.drawable.bubble));
        getContentView().setOnFocusChangeListener(new aa(this));
    }

    public void m13049a(List<Integer> list, View view, int i, int i2) {
        if (isShowing()) {
            dismiss();
            return;
        }
        this.f11100c.removeAllViews();
        View view2 = new View(this.f11102e);
        view2.setLayoutParams(new ViewGroup.LayoutParams(1, 30));
        this.f11100c.addView(view2);
        for (int i3 = 0; i3 < list.size(); i3++) {
            View textView = new TextView(this.f11102e);
            textView.setTextColor(-1);
            textView.setSingleLine(true);
            textView.setText(this.f11102e.getResources().getString(((Integer) list.get(i3)).intValue()));
            this.f11101d.topMargin = 5;
            this.f11100c.addView(textView, this.f11101d);
        }
        setHeight((list.size() * ((int) this.f11102e.getResources().getDimension(C1205R.dimen.popuwindows_h))) + 50);
        setFocusable(true);
        showAsDropDown(view, i, i2);
        this.f11103f = 1;
        this.f11098a.postDelayed(this.f11099b, 1000);
    }
}
