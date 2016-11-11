package cn.sharesdk.framework;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

/* renamed from: cn.sharesdk.framework.q */
class C0197q extends TextView {
    final /* synthetic */ ImageView f358a;
    final /* synthetic */ TitleLayout f359b;

    C0197q(TitleLayout titleLayout, Context context, ImageView imageView) {
        this.f359b = titleLayout;
        this.f358a = imageView;
        super(context);
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        this.f358a.setVisibility(i);
    }
}
