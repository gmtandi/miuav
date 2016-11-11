package com.fimi.soul.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/* renamed from: com.fimi.soul.utils.z */
public class C1986z {
    private Context f10223a;

    public C1986z(Context context) {
        this.f10223a = context;
    }

    public Toast m12538a(int i) {
        View inflate = LayoutInflater.from(this.f10223a).inflate(i, null);
        Toast toast = new Toast(this.f10223a);
        toast.setGravity(53, 12, 40);
        toast.setDuration(1);
        toast.setView(inflate);
        return toast;
    }

    public Toast m12539a(String str) {
        return Toast.makeText(this.f10223a, str, 0);
    }

    public Toast m12540a(String str, Drawable drawable) {
        Toast makeText = Toast.makeText(this.f10223a, str, 1);
        makeText.setGravity(17, 0, 0);
        LinearLayout linearLayout = (LinearLayout) makeText.getView();
        View imageView = new ImageView(this.f10223a);
        imageView.setBackgroundDrawable(drawable);
        linearLayout.addView(imageView, 0);
        return makeText;
    }
}
