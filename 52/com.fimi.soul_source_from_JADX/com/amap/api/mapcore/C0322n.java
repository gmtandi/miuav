package com.amap.api.mapcore;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.mm.sdk.platformtools.Util;

/* renamed from: com.amap.api.mapcore.n */
class C0322n extends View {
    private Paint f1965a;

    public C0322n(Context context) {
        super(context);
        this.f1965a = new Paint();
        this.f1965a.setAntiAlias(true);
        this.f1965a.setColor(Color.argb(Util.MASK_8BIT, 235, 235, 235));
    }

    public void m3269a(boolean z) {
        if (z) {
            setVisibility(0);
            return;
        }
        setVisibility(8);
        Animation alphaAnimation = new AlphaAnimation(C2020f.f10933c, 0.0f);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setDuration(200);
        clearAnimation();
        startAnimation(alphaAnimation);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), this.f1965a);
    }
}
