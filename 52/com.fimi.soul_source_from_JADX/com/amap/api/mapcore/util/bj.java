package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amap.api.mapcore.C0330s;
import com.amap.api.mapcore.ab;
import com.amap.api.mapcore.util.bv.C0363a;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.WeightedLatLng;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.facebook.common.util.UriUtil;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.microedition.khronos.opengles.GL10;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

public class bj {
    static final int[] f2204a;
    static final double[] f2205b;
    public static Handler f2206c;

    static {
        f2204a = new int[]{4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        f2205b = new double[]{7453.642d, 3742.9905d, 1873.333d, 936.89026d, 468.472d, 234.239d, 117.12d, 58.56d, 29.28d, 14.64d, 7.32d, 3.66d, 1.829d, 0.915d, 0.4575d, 0.228d, 0.1144d};
        f2206c = new Handler();
    }

    public static double m3599a(double d, double d2) {
        return d2 > 0.0d ? Math.log((1048576.0d * d) / d2) / Math.log(2.0d) : 0.0d;
    }

    public static double m3600a(double d, double d2, double d3, double d4, double d5, double d6) {
        return ((d3 - d) * (d6 - d2)) - ((d5 - d) * (d4 - d2));
    }

    public static float m3601a(double d, double d2, double d3, double d4) {
        if (d <= 0.0d) {
            return d2 > 0.0d ? (float) m3599a(d2, d4) : 0.0f;
        } else {
            float a = (float) m3599a(d, d3);
            return d2 > 0.0d ? (float) Math.min((double) a, m3599a(d2, d4)) : a;
        }
    }

    public static float m3602a(float f) {
        return f > C0330s.f2073f ? C0330s.f2073f : f < C2020f.f10931a ? C2020f.f10931a : f;
    }

    public static float m3603a(float f, float f2) {
        return f > 40.0f ? f2 <= 15.0f ? 40.0f : f2 <= 16.0f ? 50.0f : f2 <= 17.0f ? 54.0f : f2 <= 18.0f ? 57.0f : BitmapDescriptorFactory.HUE_YELLOW : f;
    }

    public static int m3604a(int i) {
        int log = (int) (Math.log((double) i) / Math.log(2.0d));
        return (1 << log) >= i ? 1 << log : 1 << (log + 1);
    }

    public static int m3605a(GL10 gl10, int i, Bitmap bitmap, boolean z) {
        int b = m3632b(gl10, i, bitmap, z);
        if (bitmap != null) {
            bitmap.recycle();
        }
        return b;
    }

    public static int m3606a(GL10 gl10, Bitmap bitmap) {
        return m3607a(gl10, bitmap, false);
    }

    public static int m3607a(GL10 gl10, Bitmap bitmap, boolean z) {
        return m3605a(gl10, 0, bitmap, z);
    }

    public static int m3608a(Object[] objArr) {
        return Arrays.hashCode(objArr);
    }

    public static Bitmap m3609a(Context context, String str) {
        try {
            InputStream open = bh.m3592a(context).open(str);
            Bitmap decodeStream = BitmapFactory.decodeStream(open);
            open.close();
            return decodeStream;
        } catch (Throwable th) {
            ce.m3829a(th, "Util", "fromAsset");
            return null;
        }
    }

    public static Bitmap m3610a(Bitmap bitmap, float f) {
        return bitmap == null ? null : Bitmap.createScaledBitmap(bitmap, (int) (((float) bitmap.getWidth()) * f), (int) (((float) bitmap.getHeight()) * f), true);
    }

    public static Bitmap m3611a(Bitmap bitmap, int i, int i2) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, bitmap.hasAlpha() ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    public static Bitmap m3612a(View view) {
        try {
            m3635b(view);
            view.destroyDrawingCache();
            view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            Bitmap copy = view.getDrawingCache().copy(Config.ARGB_8888, false);
            view.destroyDrawingCache();
            return copy;
        } catch (Throwable th) {
            ce.m3829a(th, "Utils", "getBitmapFromView");
            th.printStackTrace();
            return null;
        }
    }

    public static DPoint m3613a(LatLng latLng) {
        double d = (latLng.longitude / 360.0d) + 0.5d;
        double sin = Math.sin(Math.toRadians(latLng.latitude));
        return new DPoint(d * WeightedLatLng.DEFAULT_INTENSITY, (((Math.log((WeightedLatLng.DEFAULT_INTENSITY + sin) / (WeightedLatLng.DEFAULT_INTENSITY - sin)) * 0.5d) / -6.283185307179586d) + 0.5d) * WeightedLatLng.DEFAULT_INTENSITY);
    }

    private static FPoint m3614a(FPoint fPoint, FPoint fPoint2, FPoint fPoint3, FPoint fPoint4) {
        FPoint fPoint5 = new FPoint(0.0f, 0.0f);
        double d = (double) (((fPoint2.f3694y - fPoint.f3694y) * (fPoint.f3693x - fPoint3.f3693x)) - ((fPoint2.f3693x - fPoint.f3693x) * (fPoint.f3694y - fPoint3.f3694y)));
        double d2 = (double) (((fPoint2.f3694y - fPoint.f3694y) * (fPoint4.f3693x - fPoint3.f3693x)) - ((fPoint2.f3693x - fPoint.f3693x) * (fPoint4.f3694y - fPoint3.f3694y)));
        fPoint5.f3693x = (float) (((double) fPoint3.f3693x) + ((((double) (fPoint4.f3693x - fPoint3.f3693x)) * d) / d2));
        fPoint5.f3694y = (float) (((d * ((double) (fPoint4.f3694y - fPoint3.f3694y))) / d2) + ((double) fPoint3.f3694y));
        return fPoint5;
    }

    public static String m3615a(Context context) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return context.getCacheDir().toString() + File.separator;
        }
        File file = (MapsInitializer.sdcardDir == null || MapsInitializer.sdcardDir.equals(C2915a.f14760f)) ? new File(Environment.getExternalStorageDirectory(), MapTilsCacheAndResManager.AUTONAVI_PATH) : new File(MapsInitializer.sdcardDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, UriUtil.DATA_SCHEME);
        if (!file2.exists()) {
            file2.mkdir();
        }
        return file2.toString() + File.separator;
    }

    public static String m3616a(File file) {
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        Throwable e;
        FileInputStream fileInputStream2;
        BufferedReader bufferedReader2 = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            fileInputStream = new FileInputStream(file);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "utf-8"));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuffer.append(readLine);
                    } catch (FileNotFoundException e2) {
                        e = e2;
                        fileInputStream2 = fileInputStream;
                    } catch (IOException e3) {
                        e = e3;
                        bufferedReader2 = bufferedReader;
                    } catch (Throwable th) {
                        e = th;
                        bufferedReader2 = bufferedReader;
                    }
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e42) {
                                e42.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e422) {
                        e422.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e6) {
                e = e6;
                bufferedReader = null;
                fileInputStream2 = fileInputStream;
                try {
                    ce.m3829a(e, "Util", "readFile fileNotFound");
                    e.printStackTrace();
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e4222) {
                            e4222.printStackTrace();
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e42222) {
                                    e42222.printStackTrace();
                                }
                            }
                        } catch (Throwable th3) {
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e52) {
                                    e52.printStackTrace();
                                }
                            }
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e422222) {
                            e422222.printStackTrace();
                        }
                    }
                    return stringBuffer.toString();
                } catch (Throwable th4) {
                    e = th4;
                    fileInputStream = fileInputStream2;
                    bufferedReader2 = bufferedReader;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e522) {
                            e522.printStackTrace();
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e5222) {
                                    e5222.printStackTrace();
                                }
                            }
                        } catch (Throwable th5) {
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e52222) {
                                    e52222.printStackTrace();
                                }
                            }
                        }
                    }
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e522222) {
                            e522222.printStackTrace();
                        }
                    }
                    throw e;
                }
            } catch (IOException e7) {
                e = e7;
                try {
                    ce.m3829a(e, "Util", "readFile io");
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4222222) {
                            e4222222.printStackTrace();
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e42222222) {
                                    e42222222.printStackTrace();
                                }
                            }
                        } catch (Throwable th6) {
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e5222222) {
                                    e5222222.printStackTrace();
                                }
                            }
                        }
                    }
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e422222222) {
                            e422222222.printStackTrace();
                        }
                    }
                    return stringBuffer.toString();
                } catch (Throwable th7) {
                    e = th7;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    throw e;
                }
            }
        } catch (FileNotFoundException e8) {
            e = e8;
            bufferedReader = null;
            ce.m3829a(e, "Util", "readFile fileNotFound");
            e.printStackTrace();
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return stringBuffer.toString();
        } catch (IOException e9) {
            e = e9;
            fileInputStream = null;
            ce.m3829a(e, "Util", "readFile io");
            e.printStackTrace();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            return stringBuffer.toString();
        } catch (Throwable th8) {
            e = th8;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw e;
        }
        return stringBuffer.toString();
    }

    public static String m3617a(InputStream inputStream) {
        try {
            return new String(m3638b(inputStream), "utf-8");
        } catch (Throwable th) {
            ce.m3829a(th, "Util", "decodeAssetResData");
            th.printStackTrace();
            return null;
        }
    }

    public static String m3618a(String str, Object obj) {
        return str + "=" + String.valueOf(obj);
    }

    public static String m3619a(String... strArr) {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        int length = strArr.length;
        int i2 = 0;
        while (i < length) {
            stringBuilder.append(strArr[i]);
            if (i2 != strArr.length - 1) {
                stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
            }
            i2++;
            i++;
        }
        return stringBuilder.toString();
    }

    public static FloatBuffer m3620a(float[] fArr) {
        try {
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
            allocateDirect.order(ByteOrder.nativeOrder());
            FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
            asFloatBuffer.put(fArr);
            asFloatBuffer.position(0);
            return asFloatBuffer;
        } catch (Throwable th) {
            ce.m3829a(th, "Util", "makeFloatBuffer1");
            th.printStackTrace();
            return null;
        }
    }

    public static FloatBuffer m3621a(float[] fArr, FloatBuffer floatBuffer) {
        try {
            floatBuffer.clear();
            floatBuffer.put(fArr);
            floatBuffer.position(0);
            return floatBuffer;
        } catch (Throwable th) {
            ce.m3829a(th, "Util", "makeFloatBuffer2");
            th.printStackTrace();
            return null;
        }
    }

    public static List<FPoint> m3622a(ab abVar, List<FPoint> list, boolean z) {
        List arrayList = new ArrayList();
        List<FPoint> arrayList2 = new ArrayList(list);
        FPoint[] a = m3631a(abVar);
        int i = 0;
        while (i < 4) {
            arrayList.clear();
            int size = arrayList2.size();
            int i2 = 0;
            while (true) {
                if (i2 >= (z ? size : size - 1)) {
                    break;
                }
                FPoint fPoint = (FPoint) arrayList2.get(i2 % size);
                FPoint fPoint2 = (FPoint) arrayList2.get((i2 + 1) % size);
                if (i2 == 0 && m3629a(fPoint, a[i], a[(i + 1) % a.length])) {
                    arrayList.add(fPoint);
                }
                if (m3629a(fPoint, a[i], a[(i + 1) % a.length])) {
                    if (m3629a(fPoint2, a[i], a[(i + 1) % a.length])) {
                        arrayList.add(fPoint2);
                    } else {
                        arrayList.add(m3614a(a[i], a[(i + 1) % a.length], fPoint, fPoint2));
                    }
                } else if (m3629a(fPoint2, a[i], a[(i + 1) % a.length])) {
                    arrayList.add(m3614a(a[i], a[(i + 1) % a.length], fPoint, fPoint2));
                    arrayList.add(fPoint2);
                }
                i2++;
            }
            arrayList2.clear();
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                arrayList2.add(arrayList.get(i3));
            }
            byte b = (byte) (i + 1);
        }
        return arrayList2;
    }

    public static void m3623a(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public static void m3624a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(RMsgInfo.COL_STATUS)) {
                String string = jSONObject.getString(RMsgInfo.COL_STATUS);
                if (string.equals("E6008")) {
                    throw new AMapException("key\u4e3a\u7a7a");
                } else if (string.equals("E6012")) {
                    throw new AMapException("key\u4e0d\u5b58\u5728");
                } else if (string.equals("E6018")) {
                    throw new AMapException("key\u88ab\u9501\u5b9a");
                }
            }
        } catch (Throwable e) {
            ce.m3829a(e, "Util", "paseAuthFailurJson");
            e.printStackTrace();
        }
    }

    public static void m3625a(GL10 gl10, int i) {
        gl10.glDeleteTextures(1, new int[]{i}, 0);
    }

    public static boolean m3626a() {
        return VERSION.SDK_INT >= 8;
    }

    public static boolean m3627a(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        double d9 = ((d3 - d) * (d8 - d6)) - ((d4 - d2) * (d7 - d5));
        if (d9 == 0.0d) {
            return false;
        }
        double d10 = (((d2 - d6) * (d7 - d5)) - ((d - d5) * (d8 - d6))) / d9;
        d9 = (((d2 - d6) * (d3 - d)) - ((d - d5) * (d4 - d2))) / d9;
        return d10 >= 0.0d && d10 <= WeightedLatLng.DEFAULT_INTENSITY && d9 >= 0.0d && d9 <= WeightedLatLng.DEFAULT_INTENSITY;
    }

    public static boolean m3628a(LatLng latLng, List<LatLng> list) {
        double d = latLng.longitude;
        double d2 = latLng.latitude;
        double d3 = latLng.latitude;
        if (list.size() < 3) {
            return false;
        }
        if (!((LatLng) list.get(0)).equals(list.get(list.size() - 1))) {
            list.add(list.get(0));
        }
        int i = 0;
        int i2 = 0;
        while (i < list.size() - 1) {
            double d4 = ((LatLng) list.get(i)).longitude;
            double d5 = ((LatLng) list.get(i)).latitude;
            double d6 = ((LatLng) list.get(i + 1)).longitude;
            double d7 = ((LatLng) list.get(i + 1)).latitude;
            if (m3637b(d, d2, d4, d5, d6, d7)) {
                return true;
            }
            int i3;
            if (Math.abs(d7 - d5) < 1.0E-9d) {
                i3 = i2;
            } else {
                if (m3637b(d4, d5, d, d2, 180.0d, d3)) {
                    if (d5 > d7) {
                        i3 = i2 + 1;
                    }
                } else if (m3637b(d6, d7, d, d2, 180.0d, d3)) {
                    if (d7 > d5) {
                        i3 = i2 + 1;
                    }
                } else if (m3627a(d4, d5, d6, d7, d, d2, 180.0d, d3)) {
                    i3 = i2 + 1;
                }
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        return i2 % 2 != 0;
    }

    private static boolean m3629a(FPoint fPoint, FPoint fPoint2, FPoint fPoint3) {
        return ((double) (((fPoint3.f3693x - fPoint2.f3693x) * (fPoint.f3694y - fPoint2.f3694y)) - ((fPoint.f3693x - fPoint2.f3693x) * (fPoint3.f3694y - fPoint2.f3694y)))) >= 0.0d;
    }

    public static float[] m3630a(ab abVar, int i, FPoint fPoint, float f, int i2, int i3, float f2, float f3) {
        float f4 = fPoint.f3693x;
        float f5 = fPoint.f3694y;
        float[] fArr = new float[12];
        float a = abVar.m2214B().m2688a(i2);
        float a2 = abVar.m2214B().m2688a(i3);
        float[] fArr2 = new float[16];
        float[] fArr3 = new float[4];
        Matrix.setIdentityM(fArr2, 0);
        if (i == 3) {
            Matrix.translateM(fArr2, 0, f4, f5, 0.0f);
            Matrix.rotateM(fArr2, 0, abVar.m2289c().getMapAngle(), 0.0f, 0.0f, C2020f.f10933c);
            Matrix.translateM(fArr2, 0, -f4, -f5, 0.0f);
            Matrix.translateM(fArr2, 0, f4 - (a / 2.0f), f5 - (a2 / 2.0f), 0.0f);
            Matrix.rotateM(fArr2, 0, -abVar.m2289c().getCameraHeaderAngle(), C2020f.f10933c, 0.0f, 0.0f);
            Matrix.translateM(fArr2, 0, (a / 2.0f) - f4, (a2 / 2.0f) - f5, 0.0f);
        } else if (i == 1) {
            Matrix.translateM(fArr2, 0, f4, f5, 0.0f);
            Matrix.rotateM(fArr2, 0, f, 0.0f, 0.0f, C2020f.f10933c);
            Matrix.translateM(fArr2, 0, -f4, -f5, 0.0f);
        } else {
            Matrix.translateM(fArr2, 0, f4, f5, 0.0f);
            Matrix.rotateM(fArr2, 0, abVar.m2289c().getMapAngle(), 0.0f, 0.0f, C2020f.f10933c);
            Matrix.rotateM(fArr2, 0, -abVar.m2289c().getCameraHeaderAngle(), C2020f.f10933c, 0.0f, 0.0f);
            Matrix.rotateM(fArr2, 0, f, 0.0f, 0.0f, C2020f.f10933c);
            Matrix.translateM(fArr2, 0, -f4, -f5, 0.0f);
        }
        float[] fArr4 = new float[]{f4 - (a * f2), ((C2020f.f10933c - f3) * a2) + f5, 0.0f, C2020f.f10933c};
        Matrix.multiplyMV(fArr4, 0, fArr2, 0, fArr3, 0);
        fArr[0] = fArr4[0];
        fArr[1] = fArr4[1];
        fArr[2] = fArr4[2];
        fArr3[0] = ((C2020f.f10933c - f2) * a) + f4;
        fArr3[1] = ((C2020f.f10933c - f3) * a2) + f5;
        fArr3[2] = 0.0f;
        fArr3[3] = C2020f.f10933c;
        Matrix.multiplyMV(fArr4, 0, fArr2, 0, fArr3, 0);
        fArr[3] = fArr4[0];
        fArr[4] = fArr4[1];
        fArr[5] = fArr4[2];
        fArr3[0] = ((C2020f.f10933c - f2) * a) + f4;
        fArr3[1] = f5 - (a2 * f3);
        fArr3[2] = 0.0f;
        fArr3[3] = C2020f.f10933c;
        Matrix.multiplyMV(fArr4, 0, fArr2, 0, fArr3, 0);
        fArr[6] = fArr4[0];
        fArr[7] = fArr4[1];
        fArr[8] = fArr4[2];
        fArr3[0] = f4 - (a * f2);
        fArr3[1] = f5 - (a2 * f3);
        fArr3[2] = 0.0f;
        fArr3[3] = C2020f.f10933c;
        Matrix.multiplyMV(fArr4, 0, fArr2, 0, fArr3, 0);
        fArr[9] = fArr4[0];
        fArr[10] = fArr4[1];
        fArr[11] = fArr4[2];
        return fArr;
    }

    private static FPoint[] m3631a(ab abVar) {
        FPoint[] fPointArr = new FPoint[4];
        FPoint fPoint = new FPoint();
        abVar.m2249a(-100, -100, fPoint);
        fPointArr[0] = fPoint;
        fPoint = new FPoint();
        abVar.m2249a(abVar.m2311l() + 100, -100, fPoint);
        fPointArr[1] = fPoint;
        fPoint = new FPoint();
        abVar.m2249a(abVar.m2311l() + 100, abVar.m2313m() + 100, fPoint);
        fPointArr[2] = fPoint;
        fPoint = new FPoint();
        abVar.m2249a(-100, abVar.m2313m() + 100, fPoint);
        fPointArr[3] = fPoint;
        return fPointArr;
    }

    public static int m3632b(GL10 gl10, int i, Bitmap bitmap, boolean z) {
        if (bitmap == null || bitmap.isRecycled()) {
            return 0;
        }
        if (i == 0) {
            int[] iArr = new int[]{0};
            gl10.glGenTextures(1, iArr, 0);
            i = iArr[0];
        }
        gl10.glEnable(3553);
        gl10.glBindTexture(3553, i);
        gl10.glTexParameterf(3553, 10241, 9729.0f);
        gl10.glTexParameterf(3553, C1142e.f5202b, 9729.0f);
        if (z) {
            gl10.glTexParameterf(3553, 10242, 10497.0f);
            gl10.glTexParameterf(3553, 10243, 10497.0f);
        } else {
            gl10.glTexParameterf(3553, 10242, 33071.0f);
            gl10.glTexParameterf(3553, 10243, 33071.0f);
        }
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        gl10.glDisable(3553);
        return i;
    }

    public static String m3633b(int i) {
        return i < XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER ? i + "m" : (i / XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) + "km";
    }

    public static String m3634b(Context context) {
        String a = m3615a(context);
        if (a == null) {
            return null;
        }
        File file = new File(a, "VMAP2");
        if (!file.exists()) {
            file.mkdir();
        }
        return file.toString() + File.separator;
    }

    private static void m3635b(View view) {
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                m3635b(((ViewGroup) view).getChildAt(i));
            }
        } else if (view instanceof TextView) {
            ((TextView) view).setHorizontallyScrolling(false);
        }
    }

    public static boolean m3636b() {
        return VERSION.SDK_INT >= 9;
    }

    public static boolean m3637b(double d, double d2, double d3, double d4, double d5, double d6) {
        return Math.abs(m3600a(d, d2, d3, d4, d5, d6)) < 1.0E-9d && (d - d3) * (d - d5) <= 0.0d && (d2 - d4) * (d2 - d6) <= 0.0d;
    }

    public static byte[] m3638b(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[Opcodes.ACC_STRICT];
        while (true) {
            int read = inputStream.read(bArr, 0, Opcodes.ACC_STRICT);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public static boolean m3639c() {
        return VERSION.SDK_INT >= 11;
    }

    public static boolean m3640c(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        State state = activeNetworkInfo.getState();
        return (state == null || state == State.DISCONNECTED || state == State.DISCONNECTING) ? false : true;
    }

    public static boolean m3641d() {
        return VERSION.SDK_INT >= 12;
    }

    public static bv m3642e() {
        try {
            if (C0330s.f2075h != null) {
                return C0330s.f2075h;
            }
            return new C0363a(C0330s.f2069b, "3.3.1", C0330s.f2071d).m3757a(new String[]{"com.amap.api.maps"}).m3758a();
        } catch (Throwable th) {
            return null;
        }
    }
}
