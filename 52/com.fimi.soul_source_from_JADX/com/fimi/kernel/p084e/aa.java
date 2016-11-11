package com.fimi.kernel.p084e;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.WeightedLatLng;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;
import p147m.framework.ui.widget.asyncview.AsyncImageView;

/* renamed from: com.fimi.kernel.e.aa */
public class aa {
    public static final int f5262a = 0;
    public static final int f5263b = 1;
    public static final int f5264c = 2;
    public static final int f5265d = 2048;
    public static final int f5266e = 2048;

    private static float m7975a(int i, int i2, int i3, int i4) {
        float f = ((float) i3) / ((float) i);
        float f2 = ((float) i4) / ((float) i2);
        return f > f2 ? f : f2;
    }

    private static int m7976a(double d) {
        int highestOneBit = Integer.highestOneBit((int) Math.floor(d));
        return highestOneBit == 0 ? f5263b : highestOneBit;
    }

    private static int m7977a(int i) {
        return (int) (((((double) ((i >> 8) & Util.MASK_8BIT)) * 0.59d) + (0.3d * ((double) ((i >> 16) & Util.MASK_8BIT)))) + (((double) (i & Util.MASK_8BIT)) * 0.11d));
    }

    public static int m7978a(Bitmap bitmap, CompressFormat compressFormat) {
        Exception e;
        Throwable th;
        int i = f5262a;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                bitmap.compress(compressFormat, 100, byteArrayOutputStream);
                i = byteArrayOutputStream.toByteArray().length;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e22) {
                            e22.printStackTrace();
                        }
                    }
                    return i;
                } catch (Throwable th2) {
                    th = th2;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e222) {
                            e222.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e4) {
            e222 = e4;
            byteArrayOutputStream = null;
            e222.printStackTrace();
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            return i;
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
        return i;
    }

    public static int m7979a(String str, String str2) {
        int i = f5262a;
        int length = str.length();
        for (int i2 = f5262a; i2 < length; i2 += f5263b) {
            if (str.charAt(i2) != str2.charAt(i2)) {
                i += f5263b;
            }
        }
        return i;
    }

    public static Bitmap m7980a(Context context, Bitmap bitmap, int i) {
        int width = bitmap.getWidth() > bitmap.getHeight() ? bitmap.getWidth() : bitmap.getHeight();
        return aa.m7983a(bitmap, (float) (width > i ? ((double) width) / ((double) i) : WeightedLatLng.DEFAULT_INTENSITY));
    }

    public static Bitmap m7981a(Context context, Uri uri, int i) {
        ContentResolver contentResolver = context.getContentResolver();
        InputStream openInputStream = contentResolver.openInputStream(uri);
        Options options = new Options();
        options.inJustDecodeBounds = true;
        options.inDither = true;
        options.inPreferredConfig = Config.ARGB_8888;
        BitmapFactory.decodeStream(openInputStream, null, options);
        openInputStream.close();
        if (options.outWidth == -1 || options.outHeight == -1) {
            return null;
        }
        int i2 = options.outHeight > options.outWidth ? options.outHeight : options.outWidth;
        double d = i2 > i ? ((double) i2) / ((double) i) : WeightedLatLng.DEFAULT_INTENSITY;
        Options options2 = new Options();
        options2.inSampleSize = aa.m7976a(d);
        options2.inDither = true;
        options2.inPreferredConfig = Config.ARGB_8888;
        openInputStream = contentResolver.openInputStream(uri);
        Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream, null, options2);
        openInputStream.close();
        return aa.m7984a(decodeStream, i, i);
    }

    public static Bitmap m7982a(Context context, String str, int i) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        options.inDither = true;
        options.inPreferredConfig = Config.ARGB_8888;
        BitmapFactory.decodeFile(str, options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            return null;
        }
        int i2 = options.outHeight > options.outWidth ? options.outHeight : options.outWidth;
        double d = i2 > i ? ((double) i2) / ((double) i) : WeightedLatLng.DEFAULT_INTENSITY;
        Options options2 = new Options();
        options2.inSampleSize = aa.m7976a(d);
        options2.inDither = true;
        options2.inPreferredConfig = Config.ARGB_8888;
        return aa.m7984a(BitmapFactory.decodeFile(str, options2), i, i);
    }

    public static Bitmap m7983a(Bitmap bitmap, float f) {
        if (!aa.m8014h(bitmap)) {
            return null;
        }
        if (f == C2020f.f10933c) {
            return bitmap;
        }
        Bitmap createBitmap;
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Matrix matrix = new Matrix();
            matrix.postScale(f, f);
            createBitmap = Bitmap.createBitmap(bitmap, f5262a, f5262a, width, height, matrix, true);
            if (createBitmap != bitmap) {
                bitmap.recycle();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (bitmap != null) {
                bitmap.recycle();
                createBitmap = null;
            } else {
                createBitmap = null;
            }
        } catch (Throwable th) {
            if (bitmap != null) {
                bitmap.recycle();
            }
        }
        return createBitmap;
    }

    public static Bitmap m7984a(Bitmap bitmap, int i, int i2) {
        if (!aa.m8014h(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] c = aa.m8009c(width, height, i, i2);
        int i3 = c[f5262a];
        int i4 = c[f5263b];
        Bitmap a = aa.m7983a(bitmap, aa.m7999b(width, height, i3, i4));
        return (a.getWidth() > i3 || a.getHeight() > i4) ? aa.m8001b(a, i3, i4) : a;
    }

    public static Bitmap m7985a(Drawable drawable) {
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(f5262a, f5262a, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public static Bitmap m7986a(ImageView imageView) {
        Bitmap createBitmap;
        Exception e;
        try {
            createBitmap = Bitmap.createBitmap(imageView.getDrawingCache());
            try {
                imageView.setDrawingCacheEnabled(false);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return createBitmap;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            createBitmap = null;
            e = exception;
            e.printStackTrace();
            return createBitmap;
        }
        return createBitmap;
    }

    public static Bitmap m7987a(File file) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(file.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Bitmap m7988a(File file, int i, int i2) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getPath(), options);
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        int[] c = aa.m8009c(i3, i4, i, i2);
        float a = aa.m7975a(i3, i4, c[f5262a], c[f5263b]);
        if (a != 0.0f) {
            i3 = (int) (((float) i3) * a);
            i4 = (int) (((float) i4) * a);
        }
        options.inPreferredConfig = Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        if (((double) a) < 0.25d) {
            options.inSampleSize = f5264c;
        } else if (((double) a) < 0.125d) {
            options.inSampleSize = 4;
        } else {
            options.inSampleSize = f5263b;
        }
        options.outWidth = i3;
        options.outHeight = i4;
        options.inJustDecodeBounds = false;
        options.inDither = false;
        return aa.m7983a(BitmapFactory.decodeFile(file.getPath(), options), a);
    }

    public static Bitmap m7989a(String str, int i, int i2, int i3) {
        InputStream inputStream;
        Exception e;
        Throwable th;
        Bitmap bitmap = null;
        try {
            URLConnection openConnection = new URL(str).openConnection();
            openConnection.setDoInput(true);
            openConnection.connect();
            inputStream = openConnection.getInputStream();
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, null, null);
                bitmap = i == 0 ? aa.m8001b(decodeStream, i2, i3) : i == f5263b ? aa.m7984a(decodeStream, i2, i3) : decodeStream;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                e2 = e3;
                try {
                    C1181t.m8221a(aa.class, C2915a.f14760f + e2.getMessage());
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e22) {
                            e22.printStackTrace();
                        }
                    }
                    return bitmap;
                } catch (Throwable th2) {
                    th = th2;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e222) {
                            e222.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e4) {
            e222 = e4;
            inputStream = bitmap;
            C1181t.m8221a(aa.class, C2915a.f14760f + e222.getMessage());
            if (inputStream != null) {
                inputStream.close();
            }
            return bitmap;
        } catch (Throwable th3) {
            inputStream = bitmap;
            th = th3;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
        return bitmap;
    }

    public static Bitmap m7990a(byte[] bArr) {
        Bitmap bitmap = null;
        try {
            if (bArr.length != 0) {
                bitmap = BitmapFactory.decodeByteArray(bArr, f5262a, bArr.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Drawable m7991a(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        Drawable bitmapDrawable;
        try {
            bitmapDrawable = new BitmapDrawable(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
            bitmapDrawable = null;
        }
        return bitmapDrawable;
    }

    public static Drawable m7992a(View view) {
        try {
            Bitmap b = aa.m8002b(view);
            if (b != null) {
                return new BitmapDrawable(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String m7993a(Context context, String str) {
        long j = 0;
        File file = new File(str);
        if (file.isFile() && file.exists()) {
            try {
                j = (long) MediaPlayer.create(context, Uri.fromFile(file)).getDuration();
            } catch (Exception e) {
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        simpleDateFormat.applyPattern("mm:ss");
        return simpleDateFormat.format(new Date(j));
    }

    public static void m7994a(Bitmap[] bitmapArr) {
        if (bitmapArr != null) {
            try {
                int length = bitmapArr.length;
                for (int i = f5262a; i < length; i += f5263b) {
                    Bitmap bitmap = bitmapArr[i];
                    if (!(bitmap == null || bitmap.isRecycled())) {
                        C1181t.m8221a(aa.class, "Bitmap\u91ca\u653e" + bitmap.toString());
                        bitmap.recycle();
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public static void m7995a(String[] strArr) {
    }

    private static boolean m7996a(int i, int i2) {
        if (i > 0 && i2 > 0) {
            return true;
        }
        C1181t.m8234c(aa.class, "\u8bf7\u6c42Bitmap\u7684\u5bbd\u9ad8\u53c2\u6570\u5fc5\u987b\u5927\u4e8e0");
        return false;
    }

    public static byte[] m7997a(Bitmap bitmap, CompressFormat compressFormat, boolean z) {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] toByteArray;
        Exception e;
        Throwable th;
        Exception exception;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                bitmap.compress(compressFormat, 100, byteArrayOutputStream);
                toByteArray = byteArrayOutputStream.toByteArray();
                if (z) {
                    try {
                        bitmap.recycle();
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            e.printStackTrace();
                            if (byteArrayOutputStream != null) {
                                try {
                                    byteArrayOutputStream.close();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                            return toByteArray;
                        } catch (Throwable th2) {
                            th = th2;
                            if (byteArrayOutputStream != null) {
                                try {
                                    byteArrayOutputStream.close();
                                } catch (Exception e32) {
                                    e32.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e322) {
                        e322.printStackTrace();
                    }
                }
            } catch (Exception e4) {
                exception = e4;
                toByteArray = null;
                e322 = exception;
                e322.printStackTrace();
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                return toByteArray;
            }
        } catch (Exception e42) {
            byteArrayOutputStream = null;
            exception = e42;
            toByteArray = null;
            e322 = exception;
            e322.printStackTrace();
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            return toByteArray;
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
        return toByteArray;
    }

    public static byte[] m7998a(View view, CompressFormat compressFormat) {
        byte[] bArr = null;
        try {
            bArr = aa.m7997a(aa.m8002b(view), compressFormat, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bArr;
    }

    private static float m7999b(int i, int i2, int i3, int i4) {
        float f = ((float) i3) / ((float) i);
        float f2 = ((float) i4) / ((float) i2);
        return f > f2 ? f2 : f;
    }

    public static Bitmap m8000b(Bitmap bitmap, float f) {
        try {
            Matrix matrix = new Matrix();
            matrix.setRotate(f % 360.0f);
            return Bitmap.createBitmap(bitmap, f5262a, f5262a, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap m8001b(Bitmap bitmap, int i, int i2) {
        int i3 = f5262a;
        Bitmap bitmap2 = null;
        if (aa.m8014h(bitmap) && aa.m7996a(i, i2)) {
            try {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                if (width > i) {
                    width = (width - i) / f5264c;
                } else {
                    i = width;
                    width = f5262a;
                }
                if (height > i2) {
                    i3 = (height - i2) / f5264c;
                } else {
                    i2 = height;
                }
                bitmap2 = Bitmap.createBitmap(bitmap, width, i3, i, i2);
                if (bitmap2 != bitmap) {
                    bitmap.recycle();
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (bitmap != null) {
                    bitmap.recycle();
                }
            } catch (Throwable th) {
                if (bitmap != null) {
                    bitmap.recycle();
                }
            }
        }
        return bitmap2;
    }

    public static Bitmap m8002b(View view) {
        Bitmap bitmap = null;
        if (view != null) {
            try {
                view.setDrawingCacheEnabled(true);
                view.measure(MeasureSpec.makeMeasureSpec(f5262a, f5262a), MeasureSpec.makeMeasureSpec(f5262a, f5262a));
                view.layout(f5262a, f5262a, view.getMeasuredWidth(), view.getMeasuredHeight());
                view.buildDrawingCache();
                bitmap = view.getDrawingCache();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    public static Bitmap m8003b(File file, int i, int i2) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getPath(), options);
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        int[] c = aa.m8009c(i3, i4, i, i2);
        int i5 = c[f5262a];
        int i6 = c[f5263b];
        float a = aa.m7975a(i3, i4, i5, i6);
        if (a != C2020f.f10933c) {
            i3 = (int) (((float) i3) * a);
            i4 = (int) (((float) i4) * a);
        }
        options.inPreferredConfig = Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        if (((double) a) < 0.25d) {
            options.inSampleSize = f5264c;
        } else if (((double) a) < 0.125d) {
            options.inSampleSize = 4;
        } else {
            options.inSampleSize = f5263b;
        }
        options.outHeight = i4;
        options.outWidth = i3;
        options.inJustDecodeBounds = false;
        options.inDither = false;
        Bitmap decodeFile = BitmapFactory.decodeFile(file.getPath(), options);
        return decodeFile != null ? aa.m8001b(decodeFile, i5, i6) : null;
    }

    public static TransitionDrawable m8004b(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        TransitionDrawable transitionDrawable;
        try {
            Drawable[] drawableArr = new Drawable[f5264c];
            drawableArr[f5262a] = new ColorDrawable(AsyncImageView.f14604a);
            drawableArr[f5263b] = new BitmapDrawable(bitmap);
            transitionDrawable = new TransitionDrawable(drawableArr);
        } catch (Exception e) {
            e.printStackTrace();
            transitionDrawable = null;
        }
        return transitionDrawable;
    }

    public static TransitionDrawable m8005b(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        TransitionDrawable transitionDrawable;
        try {
            Drawable[] drawableArr = new Drawable[f5264c];
            drawableArr[f5262a] = new ColorDrawable(AsyncImageView.f14604a);
            drawableArr[f5263b] = drawable;
            transitionDrawable = new TransitionDrawable(drawableArr);
        } catch (Exception e) {
            e.printStackTrace();
            transitionDrawable = null;
        }
        return transitionDrawable;
    }

    public static float[] m8006b(File file) {
        float[] fArr = new float[f5264c];
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getPath(), options);
        fArr[f5262a] = (float) options.outWidth;
        fArr[f5263b] = (float) options.outHeight;
        return fArr;
    }

    public static Bitmap m8007c(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= height) {
            f = (float) width;
            f2 = (float) width;
            f3 = (float) width;
            f4 = (float) width;
            f5 = (float) (width / f5264c);
            height = width;
            f6 = 0.0f;
        } else {
            f5 = (float) (height / f5264c);
            f6 = (float) ((width - height) / f5264c);
            f2 = ((float) width) - f6;
            f = (float) height;
            f3 = (float) height;
            f4 = (float) height;
            width = height;
        }
        Bitmap createBitmap = Bitmap.createBitmap(height, width, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect((int) f6, (int) f5262a, (int) f2, (int) f);
        Rect rect2 = new Rect((int) f5262a, (int) f5262a, (int) f3, (int) f4);
        RectF rectF = new RectF(rect2);
        paint.setAntiAlias(true);
        canvas.drawARGB(f5262a, f5262a, f5262a, f5262a);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f5, f5, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        return createBitmap;
    }

    public static Bitmap m8008c(Bitmap bitmap, float f) {
        try {
            int width;
            int height;
            Matrix matrix = new Matrix();
            if ((f / 90.0f) % 2.0f != 0.0f) {
                width = bitmap.getWidth();
                height = bitmap.getHeight();
            } else {
                width = bitmap.getHeight();
                height = bitmap.getWidth();
            }
            width /= f5264c;
            height /= f5264c;
            matrix.preTranslate((float) (-width), (float) (-height));
            matrix.postRotate(f);
            matrix.postTranslate((float) width, (float) height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int[] m8009c(int i, int i2, int i3, int i4) {
        int i5 = f5266e;
        int[] iArr = new int[f5264c];
        int i6 = i3 <= 0 ? i : i3;
        int i7 = i4 <= 0 ? i2 : i4;
        if (i6 > f5266e) {
            i7 = (int) (((float) i7) * (((float) f5266e) / ((float) i)));
            i6 = f5266e;
        }
        if (i7 > f5266e) {
            i7 = (int) ((((float) f5266e) / ((float) i2)) * ((float) i6));
        } else {
            i5 = i7;
            i7 = i6;
        }
        iArr[f5262a] = i7;
        iArr[f5263b] = i5;
        return iArr;
    }

    public static Bitmap m8010d(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Matrix matrix = new Matrix();
            matrix.preScale(C2020f.f10933c, GroundOverlayOptions.NO_DIMENSION);
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, f5262a, height / f5264c, width, height / f5264c, matrix, false);
            Bitmap createBitmap2 = Bitmap.createBitmap(width, (height / f5264c) + height, Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap2);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
            canvas.drawRect(0.0f, (float) height, (float) width, (float) (height + f5263b), new Paint());
            canvas.drawBitmap(createBitmap, 0.0f, (float) (height + f5263b), null);
            Paint paint = new Paint();
            paint.setShader(new LinearGradient(0.0f, (float) bitmap.getHeight(), 0.0f, (float) (createBitmap2.getHeight() + f5263b), 1895825407, ViewCompat.MEASURED_SIZE_MASK, TileMode.CLAMP));
            paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
            canvas.drawRect(0.0f, (float) height, (float) width, (float) (createBitmap2.getHeight() + f5263b), paint);
            return createBitmap2;
        } catch (Exception e) {
            e.printStackTrace();
            return bitmap;
        }
    }

    public static void m8011e(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                if (!bitmap.isRecycled()) {
                    C1181t.m8221a(aa.class, "Bitmap\u91ca\u653e" + bitmap.toString());
                    bitmap.recycle();
                }
            } catch (Exception e) {
            }
        }
    }

    public static String m8012f(Bitmap bitmap) {
        int i;
        int i2;
        int i3 = f5262a;
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, 8, 8, false);
        int width = createScaledBitmap.getWidth();
        int height = createScaledBitmap.getHeight();
        Log.i(LocaleUtil.THAI, "\u5c06\u56fe\u7247\u7f29\u5c0f\u52308x8\u7684\u5c3a\u5bf8:" + width + "*" + height);
        int[] iArr = new int[(width * height)];
        for (i = f5262a; i < width; i += f5263b) {
            for (i2 = f5262a; i2 < height; i2 += f5263b) {
                iArr[(i * height) + i2] = aa.m7977a(createScaledBitmap.getPixel(i, i2));
            }
        }
        aa.m8011e(createScaledBitmap);
        i = C1182u.m8255b(iArr);
        int[] iArr2 = new int[(width * height)];
        for (i2 = f5262a; i2 < iArr2.length; i2 += f5263b) {
            if (iArr[i2] >= i) {
                iArr2[i2] = f5263b;
            } else {
                iArr2[i2] = f5262a;
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (i3 < iArr2.length) {
            stringBuffer.append(C1182u.m8237a((((iArr2[i3] * ((int) Math.pow(2.0d, 3.0d))) + (iArr2[i3 + f5263b] * ((int) Math.pow(2.0d, 2.0d)))) + (iArr2[i3 + f5264c] * ((int) Math.pow(2.0d, WeightedLatLng.DEFAULT_INTENSITY)))) + iArr2[i3 + f5264c]));
            i3 += 4;
        }
        return stringBuffer.toString();
    }

    public static int[] m8013g(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[64];
        for (int i = f5262a; i < width; i += f5263b) {
            for (int i2 = f5262a; i2 < height; i2 += f5263b) {
                int pixel = bitmap.getPixel(i, i2);
                int i3 = (pixel >> 24) & Util.MASK_8BIT;
                i3 = (pixel >> 16) & Util.MASK_8BIT;
                int i4 = (pixel >> 8) & Util.MASK_8BIT;
                pixel &= Util.MASK_8BIT;
                int i5 = i3 >= SmileConstants.TOKEN_PREFIX_SMALL_INT ? 3 : i3 >= SmileConstants.TOKEN_PREFIX_TINY_UNICODE ? f5264c : i3 >= 64 ? f5263b : i3 >= 0 ? f5262a : f5262a;
                i3 = i4 >= SmileConstants.TOKEN_PREFIX_SMALL_INT ? 3 : i4 >= SmileConstants.TOKEN_PREFIX_TINY_UNICODE ? f5264c : i4 >= 64 ? f5263b : i4 >= 0 ? f5262a : f5262a;
                pixel = pixel >= SmileConstants.TOKEN_PREFIX_SMALL_INT ? 3 : pixel >= SmileConstants.TOKEN_PREFIX_TINY_UNICODE ? f5264c : pixel >= 64 ? f5263b : pixel >= 0 ? f5262a : f5262a;
                pixel += (i3 * 4) + (i5 * 16);
                iArr[pixel] = iArr[pixel] + f5263b;
            }
        }
        return iArr;
    }

    private static boolean m8014h(Bitmap bitmap) {
        if (bitmap == null) {
            C1181t.m8234c(aa.class, "\u539f\u56feBitmap\u4e3a\u7a7a\u4e86");
            return false;
        } else if (bitmap.getWidth() > 0 && bitmap.getHeight() > 0) {
            return true;
        } else {
            C1181t.m8234c(aa.class, "\u539f\u56feBitmap\u5927\u5c0f\u4e3a0");
            return false;
        }
    }
}
