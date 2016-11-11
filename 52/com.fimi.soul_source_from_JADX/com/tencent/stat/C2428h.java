package com.tencent.stat;

/* renamed from: com.tencent.stat.h */
/* synthetic */ class C2428h {
    static final /* synthetic */ int[] f12374a;

    static {
        f12374a = new int[StatReportStrategy.values().length];
        try {
            f12374a[StatReportStrategy.INSTANT.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f12374a[StatReportStrategy.ONLY_WIFI.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f12374a[StatReportStrategy.APP_LAUNCH.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            f12374a[StatReportStrategy.DEVELOPER.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            f12374a[StatReportStrategy.BATCH.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            f12374a[StatReportStrategy.PERIOD.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            f12374a[StatReportStrategy.ONLY_WIFI_NO_CACHE.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
    }
}
