package com.facebook.drawee.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.v4.view.ViewCompat;
import com.tencent.mm.sdk.platformtools.Util;
import javax.annotation.Nullable;

public class DrawableUtils {
    public static void copyProperties(Drawable drawable, Drawable drawable2) {
        if (drawable2 != null && drawable != null && drawable != drawable2) {
            drawable.setBounds(drawable2.getBounds());
            drawable.setChangingConfigurations(drawable2.getChangingConfigurations());
            drawable.setLevel(drawable2.getLevel());
            drawable.setVisible(drawable2.isVisible(), false);
            drawable.setState(drawable2.getState());
        }
    }

    public static int getOpacityFromColor(int i) {
        int i2 = i >>> 24;
        return i2 == Util.MASK_8BIT ? -1 : i2 == 0 ? -2 : -3;
    }

    public static int multiplyColorAlpha(int i, int i2) {
        return i2 == Util.MASK_8BIT ? i : i2 == 0 ? i & ViewCompat.MEASURED_SIZE_MASK : (((((i2 >> 7) + i2) * (i >>> 24)) >> 8) << 24) | (i & ViewCompat.MEASURED_SIZE_MASK);
    }

    public static void setCallbacks(Drawable drawable, @Nullable Callback callback, @Nullable TransformCallback transformCallback) {
        if (drawable != null) {
            drawable.setCallback(callback);
            if (drawable instanceof TransformAwareDrawable) {
                ((TransformAwareDrawable) drawable).setTransformCallback(transformCallback);
            }
        }
    }

    public static void setDrawableProperties(Drawable drawable, DrawableProperties drawableProperties) {
        if (drawable != null && drawableProperties != null) {
            drawableProperties.applyTo(drawable);
        }
    }
}
