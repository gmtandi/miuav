package com.fimi.soul.view.titlebar;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import com.fimi.kernel.p084e.C1186y;
import p147m.framework.ui.widget.asyncview.AsyncImageView;

public class FmBottomBar extends LinearLayout {
    public int f11036a;
    public LayoutInflater f11037b;
    public int f11038c;
    private Activity f11039d;
    private PopupWindow f11040e;
    private WindowManager f11041f;

    public FmBottomBar(Context context) {
        super(context);
        this.f11036a = 2;
        this.f11041f = null;
        this.f11038c = 320;
        m13011a(context);
    }

    public FmBottomBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11036a = 2;
        this.f11041f = null;
        this.f11038c = 320;
        m13011a(context);
    }

    private void m13009a(View view, View view2, boolean z) {
        C1186y.m8302a(view2);
        int measuredWidth = view.getMeasuredWidth();
        if (view2.getMeasuredWidth() > view.getMeasuredWidth()) {
            measuredWidth = view2.getMeasuredWidth();
        }
        int measuredHeight = getMeasuredHeight();
        if (z) {
            this.f11040e = new PopupWindow(view2, measuredWidth, -2, true);
        } else {
            this.f11040e = new PopupWindow(view2, -1, -2, true);
        }
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int left = iArr[0] - view.getLeft();
        measuredWidth = left + measuredWidth >= this.f11038c ? (this.f11038c - measuredWidth) - 2 : left;
        this.f11040e.setFocusable(true);
        this.f11040e.setOutsideTouchable(true);
        this.f11040e.setBackgroundDrawable(new ColorDrawable(AsyncImageView.f14604a));
        this.f11040e.showAtLocation(view, 83, measuredWidth, measuredHeight + 2);
    }

    public void m13011a(Context context) {
        this.f11039d = (Activity) context;
        setOrientation(0);
        setId(this.f11036a);
        setPadding(0, 0, 0, 0);
        this.f11037b = LayoutInflater.from(context);
        this.f11041f = this.f11039d.getWindowManager();
        this.f11038c = this.f11041f.getDefaultDisplay().getWidth();
    }

    public void m13012a(View view, View view2) {
        if (view != null && view2 != null) {
            view.setOnClickListener(new C2036a(this, view, view2));
        }
    }

    public void setBottomBarBackground(int i) {
        setBackgroundResource(i);
    }

    public void setBottomBarBackgroundColor(int i) {
        setBackgroundColor(i);
    }

    public void setBottomBarBackgroundDrawable(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public void setBottomView(int i) {
        setBottomView(this.f11037b.inflate(i, null));
    }

    public void setBottomView(View view) {
        removeAllViews();
        addView(view, new LayoutParams(-1, -2));
    }
}
