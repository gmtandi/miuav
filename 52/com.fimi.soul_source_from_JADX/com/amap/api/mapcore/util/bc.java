package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import java.io.FileDescriptor;

public class bc extends bd {
    protected int f2191a;
    protected int f2192b;

    public bc(Context context, int i, int i2) {
        super(context);
        m3570a(i, i2);
    }

    public static int m3565a(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            i5 = Math.round(((float) i3) / ((float) i2));
            int round = Math.round(((float) i4) / ((float) i));
            if (i5 >= round) {
                i5 = round;
            }
            float f = (float) (i4 * i3);
            while (f / ((float) (i5 * i5)) > ((float) ((i * i2) * 2))) {
                i5++;
            }
        }
        return i5;
    }

    private Bitmap m3566a(int i) {
        return m3567a(this.d, i, this.f2191a, this.f2192b, m3554a());
    }

    public static Bitmap m3567a(Resources resources, int i, int i2, int i3, ba baVar) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inSampleSize = m3565a(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, i, options);
    }

    public static Bitmap m3568a(FileDescriptor fileDescriptor, int i, int i2, ba baVar) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        options.inSampleSize = m3565a(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
    }

    protected Bitmap m3569a(Object obj) {
        return m3566a(Integer.parseInt(String.valueOf(obj)));
    }

    public void m3570a(int i, int i2) {
        this.f2191a = i;
        this.f2192b = i2;
    }
}
