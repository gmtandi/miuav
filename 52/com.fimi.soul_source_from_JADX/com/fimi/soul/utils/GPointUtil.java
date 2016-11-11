package com.fimi.soul.utils;

public class GPointUtil {
    static {
        System.loadLibrary("chineseshit");
    }

    public static native double[] transformFromGCJToWGS(double d, double d2);

    public static native double[] transformFromWGSToGCJ(double d, double d2);
}
