package com.amap.api.maps.model;

import android.graphics.Color;
import android.util.Log;
import com.amap.api.maps.AMapException;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.util.HashMap;

public class Gradient {
    private int f2672a;
    private int[] f2673b;
    private float[] f2674c;
    private boolean f2675d;

    /* renamed from: com.amap.api.maps.model.Gradient.a */
    class C0428a {
        final /* synthetic */ Gradient f2668a;
        private final int f2669b;
        private final int f2670c;
        private final float f2671d;

        private C0428a(Gradient gradient, int i, int i2, float f) {
            this.f2668a = gradient;
            this.f2669b = i;
            this.f2670c = i2;
            this.f2671d = f;
        }
    }

    public Gradient(int[] iArr, float[] fArr) {
        this(iArr, fArr, XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
    }

    private Gradient(int[] iArr, float[] fArr, int i) {
        int i2 = 1;
        this.f2675d = true;
        if (iArr == null || fArr == null) {
            try {
                throw new AMapException("colors and startPoints should not be null");
            } catch (AMapException e) {
                this.f2675d = false;
                Log.e(MapTilsCacheAndResManager.AUTONAVI_PATH, e.getErrorMessage());
                e.printStackTrace();
            }
        } else if (iArr.length != fArr.length) {
            throw new AMapException("colors and startPoints should be same length");
        } else if (iArr.length == 0) {
            throw new AMapException("No colors have been defined");
        } else {
            while (i2 < fArr.length) {
                if (fArr[i2] <= fArr[i2 - 1]) {
                    throw new AMapException("startPoints should be in increasing order");
                }
                i2++;
            }
            this.f2672a = i;
            this.f2673b = new int[iArr.length];
            this.f2674c = new float[fArr.length];
            System.arraycopy(iArr, 0, this.f2673b, 0, iArr.length);
            System.arraycopy(fArr, 0, this.f2674c, 0, fArr.length);
            this.f2675d = true;
        }
    }

    static int m4320a(int i, int i2, float f) {
        int i3 = 0;
        int alpha = (int) ((((float) (Color.alpha(i2) - Color.alpha(i))) * f) + ((float) Color.alpha(i)));
        float[] fArr = new float[3];
        Color.RGBToHSV(Color.red(i), Color.green(i), Color.blue(i), fArr);
        float[] fArr2 = new float[3];
        Color.RGBToHSV(Color.red(i2), Color.green(i2), Color.blue(i2), fArr2);
        if (fArr[0] - fArr2[0] > BitmapDescriptorFactory.HUE_CYAN) {
            fArr2[0] = fArr2[0] + 360.0f;
        } else if (fArr2[0] - fArr[0] > BitmapDescriptorFactory.HUE_CYAN) {
            fArr[0] = fArr[0] + 360.0f;
        }
        float[] fArr3 = new float[3];
        while (i3 < 3) {
            fArr3[i3] = ((fArr2[i3] - fArr[i3]) * f) + fArr[i3];
            i3++;
        }
        return Color.HSVToColor(alpha, fArr3);
    }

    private HashMap<Integer, C0428a> m4321a() {
        HashMap<Integer, C0428a> hashMap = new HashMap();
        if (this.f2674c[0] != 0.0f) {
            int argb = Color.argb(0, Color.red(this.f2673b[0]), Color.green(this.f2673b[0]), Color.blue(this.f2673b[0]));
            hashMap.put(Integer.valueOf(0), new C0428a(argb, this.f2673b[0], this.f2674c[0] * ((float) this.f2672a), null));
        }
        for (int i = 1; i < this.f2673b.length; i++) {
            hashMap.put(Integer.valueOf((int) (((float) this.f2672a) * this.f2674c[i - 1])), new C0428a(this.f2673b[i - 1], this.f2673b[i], (this.f2674c[i] - this.f2674c[i - 1]) * ((float) this.f2672a), null));
        }
        if (this.f2674c[this.f2674c.length - 1] != C2020f.f10933c) {
            int length = this.f2674c.length - 1;
            hashMap.put(Integer.valueOf((int) (((float) this.f2672a) * this.f2674c[length])), new C0428a(this.f2673b[length], this.f2673b[length], ((float) this.f2672a) * (C2020f.f10933c - this.f2674c[length]), null));
        }
        return hashMap;
    }

    protected int[] generateColorMap(double d) {
        int i = 0;
        HashMap a = m4321a();
        int[] iArr = new int[this.f2672a];
        int i2 = 0;
        C0428a c0428a = (C0428a) a.get(Integer.valueOf(0));
        int i3 = 0;
        while (i2 < this.f2672a) {
            int i4;
            C0428a c0428a2;
            if (a.containsKey(Integer.valueOf(i2))) {
                i4 = i2;
                c0428a2 = (C0428a) a.get(Integer.valueOf(i2));
            } else {
                c0428a2 = c0428a;
                i4 = i3;
            }
            iArr[i2] = m4320a(c0428a2.f2669b, c0428a2.f2670c, ((float) (i2 - i4)) / c0428a2.f2671d);
            i2++;
            i3 = i4;
            c0428a = c0428a2;
        }
        if (d != WeightedLatLng.DEFAULT_INTENSITY) {
            while (i < this.f2672a) {
                i3 = iArr[i];
                iArr[i] = Color.argb((int) (((double) Color.alpha(i3)) * d), Color.red(i3), Color.green(i3), Color.blue(i3));
                i++;
            }
        }
        return iArr;
    }

    protected boolean isAvailable() {
        return this.f2675d;
    }
}
