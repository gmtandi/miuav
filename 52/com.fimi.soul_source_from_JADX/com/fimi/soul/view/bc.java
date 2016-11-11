package com.fimi.soul.view;

import android.graphics.Point;

class bc {
    bc() {
    }

    public static double m12794a(float f, float f2, int i, int i2) {
        int abs = Math.abs((int) (f - ((float) i)));
        int abs2 = Math.abs((int) (f2 - ((float) i2)));
        return (double) Math.round((float) ((Math.asin(((double) abs2) / Math.sqrt((double) ((abs * abs) + (abs2 * abs2)))) / 3.141592653589793d) * 180.0d));
    }

    public static float m12795a(Point point, Point point2) {
        float f = (float) (point2.x - point.x);
        float f2 = (float) (point2.y - point.y);
        return ((float) (point2.y < point.y ? -1 : 1)) * ((float) Math.acos((double) (f / ((float) Math.sqrt((double) ((f2 * f2) + (f * f)))))));
    }

    public static int m12796a(float f, float f2, float f3, float f4) {
        return (int) Math.sqrt(Math.pow((double) (f - f3), 2.0d) + Math.pow((double) (f2 - f4), 2.0d));
    }

    public static Point m12797a(Point point, Point point2, int i) {
        float a = m12795a(point, point2);
        return new Point(point.x + ((int) (((double) i) * Math.cos((double) a))), ((int) (((double) i) * Math.sin((double) a))) + point.x);
    }
}
