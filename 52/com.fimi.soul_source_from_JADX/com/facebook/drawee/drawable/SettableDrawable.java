package com.facebook.drawee.drawable;

import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Preconditions;

public class SettableDrawable extends ForwardingDrawable {
    public SettableDrawable(Drawable drawable) {
        super((Drawable) Preconditions.checkNotNull(drawable));
    }

    public Drawable getDrawable() {
        return getCurrent();
    }

    public void setDrawable(Drawable drawable) {
        Preconditions.checkNotNull(drawable);
        setCurrent(drawable);
    }
}
