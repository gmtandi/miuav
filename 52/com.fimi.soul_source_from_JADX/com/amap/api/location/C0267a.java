package com.amap.api.location;

import com.amap.api.location.CoordinateConverter.CoordType;

/* renamed from: com.amap.api.location.a */
/* synthetic */ class C0267a {
    static final /* synthetic */ int[] f1396a;

    static {
        f1396a = new int[CoordType.values().length];
        try {
            f1396a[CoordType.BAIDU.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f1396a[CoordType.MAPBAR.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f1396a[CoordType.MAPABC.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            f1396a[CoordType.SOSOMAP.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            f1396a[CoordType.ALIYUN.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            f1396a[CoordType.GOOGLE.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            f1396a[CoordType.GPS.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
    }
}
