package com.xiaomi.market.sdk;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FeatureInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.fimi.soul.media.player.IMediaPlayer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.market.sdk.a */
public class C2528a {
    static String COUNTRY;
    static String LANGUAGE;
    static String RELEASE;
    static final int f12787a = 0;
    static int f12788b;
    static int f12789c;
    static String f12790d;
    static int f12791e;
    static int f12792f;
    static String f12793g;
    static ArrayList f12794h;
    static ArrayList f12795i;
    static ArrayList f12796j;
    static int f12797k;
    static String f12798l;
    static String f12799m;
    static String f12800n;
    private static final Object f12801o;

    static {
        f12801o = new Object();
    }

    private static void m14473a() {
        Object b = C2528a.m14475b();
        synchronized (f12801o) {
            f12796j = new ArrayList();
            if (!TextUtils.isEmpty(b)) {
                for (CharSequence charSequence : TextUtils.split(b, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
                    if (!TextUtils.isEmpty(charSequence)) {
                        f12796j.add(charSequence);
                    }
                }
            }
            Collections.sort(f12796j);
        }
    }

    static void m14474a(Context context) {
        C2528a.m14476b(context);
        C2528a.m14477c(context);
        C2528a.m14479d(context);
        C2528a.m14481e(context);
        C2528a.m14473a();
        C2528a.m14482f(context);
        C2528a.m14483g(context);
        C2528a.m14484h(context);
    }

    private static String m14475b() {
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        if (eglGetDisplay == EGL10.EGL_NO_DISPLAY || !egl10.eglInitialize(eglGetDisplay, new int[2])) {
            return null;
        }
        int[] iArr = new int[1];
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (!egl10.eglChooseConfig(eglGetDisplay, new int[]{12339, 1, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12344}, eGLConfigArr, 1, iArr)) {
            return null;
        }
        EGLConfig eGLConfig = iArr[0] > 0 ? eGLConfigArr[0] : null;
        EGLContext eglCreateContext = egl10.eglCreateContext(eglGetDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, null);
        EGLSurface eglCreatePbufferSurface = egl10.eglCreatePbufferSurface(eglGetDisplay, eGLConfig, new int[]{12375, 480, 12374, IMediaPlayer.MEDIA_INFO_BAD_INTERLEAVING, 12344});
        if (eglCreatePbufferSurface == null || eglCreatePbufferSurface == EGL10.EGL_NO_SURFACE) {
            return null;
        }
        egl10.eglMakeCurrent(eglGetDisplay, eglCreatePbufferSurface, eglCreatePbufferSurface, eglCreateContext);
        if (!egl10.eglMakeCurrent(eglGetDisplay, eglCreatePbufferSurface, eglCreatePbufferSurface, eglCreateContext)) {
            return null;
        }
        String glGetString = ((GL10) eglCreateContext.getGL()).glGetString(7939);
        egl10.eglDestroySurface(eglGetDisplay, eglCreatePbufferSurface);
        egl10.eglDestroyContext(eglGetDisplay, eglCreateContext);
        egl10.eglTerminate(eglGetDisplay);
        return glGetString != null ? glGetString.trim() : null;
    }

    private static void m14476b(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        f12789c = displayMetrics.heightPixels;
        f12788b = displayMetrics.widthPixels;
        f12790d = f12789c + "*" + f12788b;
        f12791e = displayMetrics.densityDpi;
    }

    private static void m14477c(Context context) {
        ConfigurationInfo deviceConfigurationInfo = ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo();
        f12792f = deviceConfigurationInfo.reqTouchScreen;
        f12793g = deviceConfigurationInfo.getGlEsVersion();
    }

    static boolean m14478c() {
        return f12797k >= 13;
    }

    private static void m14479d(Context context) {
        FeatureInfo[] systemAvailableFeatures = context.getPackageManager().getSystemAvailableFeatures();
        synchronized (f12801o) {
            f12794h = new ArrayList();
            if (systemAvailableFeatures != null) {
                for (FeatureInfo featureInfo : systemAvailableFeatures) {
                    if (!TextUtils.isEmpty(featureInfo.name)) {
                        f12794h.add(featureInfo.name);
                    }
                }
            }
            Collections.sort(f12794h);
        }
    }

    static boolean m14480d() {
        return f12797k >= 11;
    }

    private static void m14481e(Context context) {
        String[] systemSharedLibraryNames = context.getPackageManager().getSystemSharedLibraryNames();
        synchronized (f12801o) {
            f12795i = new ArrayList();
            if (systemSharedLibraryNames != null) {
                for (CharSequence charSequence : systemSharedLibraryNames) {
                    if (!TextUtils.isEmpty(charSequence)) {
                        f12795i.add(charSequence);
                    }
                }
            }
            Collections.sort(f12795i);
        }
    }

    private static void m14482f(Context context) {
        RELEASE = VERSION.RELEASE;
        f12798l = VERSION.INCREMENTAL;
        f12797k = VERSION.SDK_INT;
    }

    private static void m14483g(Context context) {
        COUNTRY = Locale.getDefault().getCountry();
        LANGUAGE = Locale.getDefault().getLanguage();
    }

    private static void m14484h(Context context) {
        Object obj;
        CharSequence deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            obj = C2915a.f14760f;
        } else {
            CharSequence charSequence = deviceId;
        }
        f12799m = obj;
        Object macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        StringBuffer stringBuffer = new StringBuffer();
        if (!TextUtils.isEmpty(obj)) {
            stringBuffer.append(obj);
        }
        if (!TextUtils.isEmpty(macAddress)) {
            stringBuffer.append("_");
            stringBuffer.append(macAddress);
        }
        f12800n = C2529b.m14487a(stringBuffer.toString());
    }
}
