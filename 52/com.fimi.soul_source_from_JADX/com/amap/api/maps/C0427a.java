package com.amap.api.maps;

import com.amap.api.maps.CoordinateConverter.CoordType;

/* renamed from: com.amap.api.maps.a */
/* synthetic */ class C0427a {
    static final /* synthetic */ int[] f2642a;

    static {
        f2642a = new int[CoordType.values().length];
        try {
            f2642a[CoordType.BAIDU.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f2642a[CoordType.MAPBAR.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f2642a[CoordType.MAPABC.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            f2642a[CoordType.SOSOMAP.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            f2642a[CoordType.ALIYUN.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            f2642a[CoordType.GOOGLE.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            f2642a[CoordType.GPS.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
    }
}
