package com.fimi.kernel.p084e;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.fimi.soul.view.photodraweeview.C2020f;

/* renamed from: com.fimi.kernel.e.b */
public class C1163b {
    public static final long f5309a = 1;

    public static void m8089a(View view, float f) {
        if (view != null) {
            view.bringToFront();
            C1163b.m8094c(view, (f / ((float) view.getWidth())) + C2020f.f10933c);
        }
    }

    public static void m8090a(View view, long j, int i, int i2) {
        Animation animationSet = new AnimationSet(true);
        Animation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(j);
        rotateAnimation.setRepeatCount(i);
        rotateAnimation.setRepeatMode(i2);
        rotateAnimation.setInterpolator(view.getContext(), 17432582);
        animationSet.addAnimation(rotateAnimation);
        view.startAnimation(animationSet);
    }

    public static void m8092b(View view, float f) {
        if (view != null) {
            C1163b.m8094c(view, ((f / ((float) view.getWidth())) + C2020f.f10933c) * GroundOverlayOptions.NO_DIMENSION);
        }
    }

    private static void m8094c(View view, float f) {
        if (f != 0.0f) {
            Animation scaleAnimation = f > 0.0f ? new ScaleAnimation(C2020f.f10933c, f, C2020f.f10933c, f, 1, 0.5f, 1, 0.5f) : new ScaleAnimation(GroundOverlayOptions.NO_DIMENSION * f, C2020f.f10933c, GroundOverlayOptions.NO_DIMENSION * f, C2020f.f10933c, 1, 0.5f, 1, 0.5f);
            scaleAnimation.setDuration(f5309a);
            scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
            scaleAnimation.setFillAfter(true);
            view.startAnimation(scaleAnimation);
        }
    }

    private void m8095d(View view, float f) {
        float f2 = -f;
        Animation animationSet = new AnimationSet(true);
        animationSet.addAnimation(new TranslateAnimation(0.0f, 0.0f, 0.0f, f2));
        animationSet.setDuration(300);
        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.setFillAfter(true);
        animationSet.setAnimationListener(new C1164c(this, view, f));
        view.startAnimation(animationSet);
    }

    private void m8096e(View view, float f) {
        float f2 = -f;
        Animation animationSet = new AnimationSet(true);
        animationSet.addAnimation(new TranslateAnimation(0.0f, 0.0f, f2, 0.0f));
        animationSet.setDuration(200);
        animationSet.setInterpolator(new AccelerateInterpolator());
        animationSet.setFillAfter(true);
        animationSet.setAnimationListener(new C1165d(this, view, f));
        view.startAnimation(animationSet);
    }
}
