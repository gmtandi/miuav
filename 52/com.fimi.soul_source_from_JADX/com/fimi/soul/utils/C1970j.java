package com.fimi.soul.utils;

/* renamed from: com.fimi.soul.utils.j */
public class C1970j {
    private static double m12495a(double d) {
        return (3.141592653589793d * d) / 180.0d;
    }

    public static double m12496a(double d, double d2, double d3, double d4) {
        return (d == d3 && d2 == d4) ? 0.0d : C1970j.m12497a(d, d2, d3, d4, 'K');
    }

    public static double m12497a(double d, double d2, double d3, double d4, char c) {
        double b = (C1970j.m12498b(Math.acos((Math.cos(C1970j.m12495a(d2 - d4)) * (Math.cos(C1970j.m12495a(d)) * Math.cos(C1970j.m12495a(d3)))) + (Math.sin(C1970j.m12495a(d)) * Math.sin(C1970j.m12495a(d3))))) * 60.0d) * 1.1515d;
        return c == 'K' ? b * 1.609344d : c == 'N' ? b * 0.8684d : b;
    }

    private static double m12498b(double d) {
        return (180.0d * d) / 3.141592653589793d;
    }
}
