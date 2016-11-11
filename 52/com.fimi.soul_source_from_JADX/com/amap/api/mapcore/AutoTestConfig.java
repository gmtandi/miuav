package com.amap.api.mapcore;

public class AutoTestConfig {
    public static final int CompassViewId;
    public static final int MyLocationViewId;
    public static final int ScaleControlsViewId;
    public static final int ZoomControllerViewId;
    private static int f1549a;

    static {
        f1549a = 900000000;
        int i = f1549a;
        f1549a = i + 1;
        ZoomControllerViewId = i;
        i = f1549a;
        f1549a = i + 1;
        ScaleControlsViewId = i;
        i = f1549a;
        f1549a = i + 1;
        MyLocationViewId = i;
        i = f1549a;
        f1549a = i + 1;
        CompassViewId = i;
    }
}
