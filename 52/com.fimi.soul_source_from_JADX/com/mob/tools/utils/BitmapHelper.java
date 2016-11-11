package com.mob.tools.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import com.fimi.kernel.C1154b;
import com.fimi.soul.module.droneui.DroneMap;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.mob.tools.MobLog;
import com.mob.tools.network.NetworkHelper;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class BitmapHelper {
    public static Bitmap blur(Bitmap bitmap, int i, int i2) {
        int i3 = (int) ((((float) i) / ((float) i2)) + 0.5f);
        Bitmap createBitmap = Bitmap.createBitmap((int) ((((float) bitmap.getWidth()) / ((float) i2)) + 0.5f), (int) ((((float) bitmap.getHeight()) / ((float) i2)) + 0.5f), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.scale(C2020f.f10933c / ((float) i2), C2020f.f10933c / ((float) i2));
        Paint paint = new Paint();
        paint.setFlags(2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        blur(createBitmap, i3, true);
        return createBitmap;
    }

    private static Bitmap blur(Bitmap bitmap, int i, boolean z) {
        Bitmap bitmap2;
        if (z) {
            bitmap2 = bitmap;
        } else {
            bitmap2 = bitmap.copy(bitmap.getConfig(), true);
        }
        if (i < 1) {
            return null;
        }
        int i2;
        int i3;
        int i4;
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        int[] iArr = new int[(width * height)];
        bitmap2.getPixels(iArr, 0, width, 0, 0, width, height);
        int i5 = width - 1;
        int i6 = height - 1;
        int i7 = width * height;
        int i8 = (i + i) + 1;
        int[] iArr2 = new int[i7];
        int[] iArr3 = new int[i7];
        int[] iArr4 = new int[i7];
        int[] iArr5 = new int[Math.max(width, height)];
        i7 = (i8 + 1) >> 1;
        int i9 = i7 * i7;
        int[] iArr6 = new int[(i9 * Opcodes.ACC_NATIVE)];
        for (i7 = 0; i7 < i9 * Opcodes.ACC_NATIVE; i7++) {
            iArr6[i7] = i7 / i9;
        }
        int[][] iArr7 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{i8, 3});
        int i10 = i + 1;
        int i11 = 0;
        int i12 = 0;
        for (i2 = 0; i2 < height; i2++) {
            i9 = 0;
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            int i16 = 0;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            for (i3 = -i; i3 <= i; i3++) {
                i4 = iArr[Math.min(i5, Math.max(i3, 0)) + i12];
                int[] iArr8 = iArr7[i3 + i];
                iArr8[0] = (16711680 & i4) >> 16;
                iArr8[1] = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & i4) >> 8;
                iArr8[2] = i4 & Util.MASK_8BIT;
                i4 = i10 - Math.abs(i3);
                i19 += iArr8[0] * i4;
                i18 += iArr8[1] * i4;
                i17 += i4 * iArr8[2];
                if (i3 > 0) {
                    i13 += iArr8[0];
                    i20 += iArr8[1];
                    i9 += iArr8[2];
                } else {
                    i16 += iArr8[0];
                    i15 += iArr8[1];
                    i14 += iArr8[2];
                }
            }
            i4 = i19;
            i19 = i18;
            i18 = i17;
            i3 = i12;
            i12 = i;
            for (i17 = 0; i17 < width; i17++) {
                iArr2[i3] = iArr6[i4];
                iArr3[i3] = iArr6[i19];
                iArr4[i3] = iArr6[i18];
                i4 -= i16;
                i19 -= i15;
                i18 -= i14;
                iArr8 = iArr7[((i12 - i) + i8) % i8];
                i16 -= iArr8[0];
                i15 -= iArr8[1];
                i14 -= iArr8[2];
                if (i2 == 0) {
                    iArr5[i17] = Math.min((i17 + i) + 1, i5);
                }
                int i21 = iArr[iArr5[i17] + i11];
                iArr8[0] = (16711680 & i21) >> 16;
                iArr8[1] = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & i21) >> 8;
                iArr8[2] = i21 & Util.MASK_8BIT;
                i13 += iArr8[0];
                i20 += iArr8[1];
                i9 += iArr8[2];
                i4 += i13;
                i19 += i20;
                i18 += i9;
                i12 = (i12 + 1) % i8;
                iArr8 = iArr7[i12 % i8];
                i16 += iArr8[0];
                i15 += iArr8[1];
                i14 += iArr8[2];
                i13 -= iArr8[0];
                i20 -= iArr8[1];
                i9 -= iArr8[2];
                i3++;
            }
            i11 += width;
            i12 = i3;
        }
        for (i17 = 0; i17 < width; i17++) {
            i20 = 0;
            i9 = (-i) * width;
            i14 = 0;
            i15 = 0;
            i16 = 0;
            i12 = 0;
            i4 = -i;
            i3 = 0;
            i18 = 0;
            i19 = 0;
            i13 = 0;
            while (i4 <= i) {
                i2 = Math.max(0, i9) + i17;
                int[] iArr9 = iArr7[i4 + i];
                iArr9[0] = iArr2[i2];
                iArr9[1] = iArr3[i2];
                iArr9[2] = iArr4[i2];
                int abs = i10 - Math.abs(i4);
                i11 = (iArr2[i2] * abs) + i19;
                i19 = (iArr3[i2] * abs) + i18;
                i18 = (iArr4[i2] * abs) + i3;
                if (i4 > 0) {
                    i14 += iArr9[0];
                    i13 += iArr9[1];
                    i20 += iArr9[2];
                } else {
                    i12 += iArr9[0];
                    i16 += iArr9[1];
                    i15 += iArr9[2];
                }
                if (i4 < i6) {
                    i9 += width;
                }
                i4++;
                i3 = i18;
                i18 = i19;
                i19 = i11;
            }
            i4 = i18;
            i11 = i19;
            i19 = i3;
            i3 = i17;
            i9 = i20;
            i20 = i13;
            i13 = i14;
            i14 = i15;
            i15 = i16;
            i16 = i12;
            i12 = i;
            for (i18 = 0; i18 < height; i18++) {
                iArr[i3] = (((ViewCompat.MEASURED_STATE_MASK & iArr[i3]) | (iArr6[i11] << 16)) | (iArr6[i4] << 8)) | iArr6[i19];
                i11 -= i16;
                i4 -= i15;
                i19 -= i14;
                int[] iArr10 = iArr7[((i12 - i) + i8) % i8];
                i16 -= iArr10[0];
                i15 -= iArr10[1];
                i14 -= iArr10[2];
                if (i17 == 0) {
                    iArr5[i18] = Math.min(i18 + i10, i6) * width;
                }
                i5 = iArr5[i18] + i17;
                iArr10[0] = iArr2[i5];
                iArr10[1] = iArr3[i5];
                iArr10[2] = iArr4[i5];
                i13 += iArr10[0];
                i20 += iArr10[1];
                i9 += iArr10[2];
                i11 += i13;
                i4 += i20;
                i19 += i9;
                i12 = (i12 + 1) % i8;
                iArr10 = iArr7[i12];
                i16 += iArr10[0];
                i15 += iArr10[1];
                i14 += iArr10[2];
                i13 -= iArr10[0];
                i20 -= iArr10[1];
                i9 -= iArr10[2];
                i3 += width;
            }
        }
        bitmap2.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmap2;
    }

    private static boolean bytesStartWith(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length < bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr2.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static Bitmap captureView(View view, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public static Bitmap cropBitmap(Bitmap bitmap, int i, int i2, int i3, int i4) {
        int width = (bitmap.getWidth() - i) - i3;
        int height = (bitmap.getHeight() - i2) - i4;
        if (width == bitmap.getWidth() && height == bitmap.getHeight()) {
            return bitmap;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(bitmap, (float) (-i), (float) (-i2), new Paint());
        return createBitmap;
    }

    public static String downloadBitmap(Context context, String str) {
        return new NetworkHelper().downloadCache(context, str, C1154b.f5231b, true, null);
    }

    public static int[] fixRect(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[2];
        if (((float) iArr[0]) / ((float) iArr[1]) > ((float) iArr2[0]) / ((float) iArr2[1])) {
            iArr3[0] = iArr2[0];
            iArr3[1] = (int) (((((float) iArr[1]) * ((float) iArr2[0])) / ((float) iArr[0])) + 0.5f);
        } else {
            iArr3[1] = iArr2[1];
            iArr3[0] = (int) (((((float) iArr[0]) * ((float) iArr2[1])) / ((float) iArr[1])) + 0.5f);
        }
        return iArr3;
    }

    public static int[] fixRect_2(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[2];
        if (((float) iArr[0]) / ((float) iArr[1]) > ((float) iArr2[0]) / ((float) iArr2[1])) {
            iArr3[1] = iArr2[1];
            iArr3[0] = (int) (((((float) iArr[0]) * ((float) iArr2[1])) / ((float) iArr[1])) + 0.5f);
        } else {
            iArr3[0] = iArr2[0];
            iArr3[1] = (int) (((((float) iArr[1]) * ((float) iArr2[0])) / ((float) iArr[0])) + 0.5f);
        }
        return iArr3;
    }

    public static Bitmap getBitmap(Context context, String str) {
        return getBitmap(downloadBitmap(context, str));
    }

    public static Bitmap getBitmap(File file, int i) {
        if (file == null || !file.exists()) {
            return null;
        }
        InputStream fileInputStream = new FileInputStream(file);
        Bitmap bitmap = getBitmap(fileInputStream, i);
        fileInputStream.close();
        return bitmap;
    }

    public static Bitmap getBitmap(InputStream inputStream, int i) {
        if (inputStream == null) {
            return null;
        }
        Options options = new Options();
        options.inPreferredConfig = Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = i;
        return BitmapFactory.decodeStream(inputStream, null, options);
    }

    public static Bitmap getBitmap(String str) {
        return getBitmap(str, 1);
    }

    public static Bitmap getBitmap(String str, int i) {
        return TextUtils.isEmpty(str) ? null : getBitmap(new File(str), i);
    }

    public static CompressFormat getBmpFormat(String str) {
        String toLowerCase = str.toLowerCase();
        if (toLowerCase.endsWith("png") || toLowerCase.endsWith("gif")) {
            return CompressFormat.PNG;
        }
        if (toLowerCase.endsWith("jpg") || toLowerCase.endsWith("jpeg") || toLowerCase.endsWith("bmp") || toLowerCase.endsWith("tif")) {
            return CompressFormat.JPEG;
        }
        toLowerCase = getMime(str);
        return (toLowerCase.endsWith("png") || toLowerCase.endsWith("gif")) ? CompressFormat.PNG : CompressFormat.JPEG;
    }

    public static CompressFormat getBmpFormat(byte[] bArr) {
        String mime = getMime(bArr);
        CompressFormat compressFormat = CompressFormat.JPEG;
        return mime != null ? (mime.endsWith("png") || mime.endsWith("gif")) ? CompressFormat.PNG : compressFormat : compressFormat;
    }

    private static String getMime(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[8];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return getMime(bArr);
        } catch (Throwable e) {
            MobLog.getInstance().m750w(e);
            return null;
        }
    }

    private static String getMime(byte[] bArr) {
        byte[] bArr2 = new byte[]{(byte) -1, (byte) -40, (byte) -1, (byte) -31};
        if (bytesStartWith(bArr, new byte[]{(byte) -1, (byte) -40, (byte) -1, (byte) -32}) || bytesStartWith(bArr, bArr2)) {
            return "jpg";
        }
        if (bytesStartWith(bArr, new byte[]{(byte) -119, DroneMap.f8355i, (byte) 78, (byte) 71})) {
            return "png";
        }
        if (bytesStartWith(bArr, "GIF".getBytes())) {
            return "gif";
        }
        if (bytesStartWith(bArr, "BM".getBytes())) {
            return "bmp";
        }
        return (bytesStartWith(bArr, new byte[]{(byte) 73, (byte) 73, (byte) 42}) || bytesStartWith(bArr, new byte[]{(byte) 77, (byte) 77, (byte) 42})) ? "tif" : null;
    }

    public static boolean isBlackBitmap(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return true;
        }
        boolean z;
        int[] iArr = new int[(bitmap.getWidth() * bitmap.getHeight())];
        bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        for (int i : iArr) {
            if ((i & ViewCompat.MEASURED_SIZE_MASK) != 0) {
                z = true;
                break;
            }
        }
        z = false;
        return !z;
    }

    public static int mixAlpha(int i, int i2) {
        int i3 = i >>> 24;
        return ((((255 - i3) * (i2 & Util.MASK_8BIT)) + (i3 * (i & Util.MASK_8BIT))) / Util.MASK_8BIT) | ((((((((16711680 & i) >>> 16) * i3) + (((16711680 & i2) >>> 16) * (255 - i3))) / Util.MASK_8BIT) << 16) | ViewCompat.MEASURED_STATE_MASK) | ((((((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) >>> 8) * i3) + ((255 - i3) * ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i2) >>> 8))) / Util.MASK_8BIT) << 8));
    }

    public static Bitmap roundBitmap(Bitmap bitmap, int i, int i2, float f, float f2, float f3, float f4) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Rect rect = new Rect(0, 0, width, height);
        Bitmap createBitmap = (width == i && height == i2) ? Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888) : Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect2 = new Rect(0, 0, i, i2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        float[] fArr = new float[]{f, f, f2, f2, f3, f3, f4, f4};
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(fArr, new RectF(0.0f, 0.0f, 0.0f, 0.0f), fArr));
        shapeDrawable.setBounds(rect2);
        shapeDrawable.draw(canvas);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        return createBitmap;
    }

    public static String saveBitmap(Context context, Bitmap bitmap) {
        return saveBitmap(context, bitmap, CompressFormat.JPEG, 80);
    }

    public static String saveBitmap(Context context, Bitmap bitmap, CompressFormat compressFormat, int i) {
        String cachePath = C2178R.getCachePath(context, C1154b.f5231b);
        String str = Util.PHOTO_DEFAULT_EXT;
        if (compressFormat == CompressFormat.PNG) {
            str = ".png";
        }
        File file = new File(cachePath, String.valueOf(System.currentTimeMillis()) + str);
        OutputStream fileOutputStream = new FileOutputStream(file);
        bitmap.compress(compressFormat, i, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return file.getAbsolutePath();
    }

    public static String saveViewToImage(View view) {
        if (view == null) {
            return null;
        }
        int width = view.getWidth();
        int height = view.getHeight();
        return (width <= 0 || height <= 0) ? null : saveViewToImage(view, width, height);
    }

    public static String saveViewToImage(View view, int i, int i2) {
        Bitmap captureView = captureView(view, i, i2);
        if (captureView == null || captureView.isRecycled()) {
            return null;
        }
        File file = new File(C2178R.getCachePath(view.getContext(), "screenshot"), String.valueOf(System.currentTimeMillis()) + Util.PHOTO_DEFAULT_EXT);
        OutputStream fileOutputStream = new FileOutputStream(file);
        captureView.compress(CompressFormat.JPEG, 100, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return file.getAbsolutePath();
    }

    public static Bitmap scaleBitmapByHeight(Context context, int i, int i2) {
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), i);
        Object obj = i2 != decodeResource.getHeight() ? 1 : null;
        Bitmap scaleBitmapByHeight = scaleBitmapByHeight(decodeResource, i2);
        if (obj != null) {
            decodeResource.recycle();
        }
        return scaleBitmapByHeight;
    }

    public static Bitmap scaleBitmapByHeight(Bitmap bitmap, int i) {
        return Bitmap.createScaledBitmap(bitmap, (bitmap.getWidth() * i) / bitmap.getHeight(), i, true);
    }
}
