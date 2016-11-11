package com.tencent.open.p132c;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;

/* renamed from: com.tencent.open.c.a */
public class C2351a extends RelativeLayout {
    private static final String f12060a;
    private Rect f12061b;
    private boolean f12062c;
    private C2294a f12063d;

    /* renamed from: com.tencent.open.c.a.a */
    public interface C2294a {
        void onKeyboardHidden();

        void onKeyboardShown(int i);
    }

    static {
        f12060a = C2351a.class.getName();
    }

    public C2351a(Context context) {
        super(context);
        this.f12061b = null;
        this.f12062c = false;
        this.f12063d = null;
        if (this.f12061b == null) {
            this.f12061b = new Rect();
        }
    }

    public void m13807a(C2294a c2294a) {
        this.f12063d = c2294a;
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i2);
        Activity activity = (Activity) getContext();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(this.f12061b);
        int height = (activity.getWindowManager().getDefaultDisplay().getHeight() - this.f12061b.top) - size;
        if (!(this.f12063d == null || size == 0)) {
            if (height > 100) {
                this.f12063d.onKeyboardShown((Math.abs(this.f12061b.height()) - getPaddingBottom()) - getPaddingTop());
            } else {
                this.f12063d.onKeyboardHidden();
            }
        }
        super.onMeasure(i, i2);
    }
}
