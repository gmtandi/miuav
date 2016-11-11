package com.fimi.soul.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.Rect;
import com.fimi.soul.C1205R;
import java.io.InputStream;

/* renamed from: com.fimi.soul.utils.y */
public class C1985y {
    public static Bitmap m12531a(Context context, int i, int i2, int i3) {
        Options options = new Options();
        options.inPreferredConfig = Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        InputStream openRawResource = context.getResources().openRawResource(i);
        Rect rect = new Rect();
        rect.left = 20;
        rect.top = 30;
        rect.right = 10;
        return BitmapFactory.decodeStream(openRawResource, rect, options);
    }

    public static Bitmap m12532a(Bitmap bitmap, float f, float f2) {
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap m12533a(Bitmap bitmap, int i) {
        if (i == 0 || bitmap == null) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate((float) i, (float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2));
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (bitmap != null) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static boolean m12534a(String str, Context context) {
        return be.m12364a(str, context.getResources().getStringArray(C1205R.array.fileEndingImage)) || be.m12364a(str, context.getResources().getStringArray(C1205R.array.fileEndingAudio)) || be.m12364a(str, context.getResources().getStringArray(C1205R.array.fileEndingVideo));
    }

    public static boolean m12535b(String str, Context context) {
        return be.m12364a(str, context.getResources().getStringArray(C1205R.array.fileEndingImage));
    }

    public static boolean m12536c(String str, Context context) {
        return be.m12364a(str, context.getResources().getStringArray(C1205R.array.fileEndingVideo));
    }

    public static boolean m12537d(String str, Context context) {
        return be.m12364a(str, context.getResources().getStringArray(C1205R.array.fileEndingAudio));
    }
}
