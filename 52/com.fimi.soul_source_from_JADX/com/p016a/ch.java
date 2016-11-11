package com.p016a;

import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;

/* renamed from: com.a.ch */
/* synthetic */ class ch {
    static final /* synthetic */ int[] f747a;

    static {
        f747a = new int[AMapLocationMode.values().length];
        try {
            f747a[AMapLocationMode.Battery_Saving.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f747a[AMapLocationMode.Hight_Accuracy.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f747a[AMapLocationMode.Device_Sensors.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
