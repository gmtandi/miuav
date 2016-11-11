package com.fimi.soul.utils;

import com.amap.api.maps.model.WeightedLatLng;
import java.util.Locale;

public class ak {
    private double f10026a;

    public ak(double d) {
        m12255a(d);
    }

    public double m12254a() {
        return this.f10026a;
    }

    public void m12255a(double d) {
        this.f10026a = d;
    }

    public boolean equals(Object obj) {
        return (obj instanceof ak) && this.f10026a == ((ak) obj).f10026a;
    }

    public String toString() {
        if (this.f10026a >= 1000.0d) {
            return String.format(Locale.US, "%2.1f km", new Object[]{Double.valueOf(this.f10026a / 1000.0d)});
        } else if (this.f10026a >= WeightedLatLng.DEFAULT_INTENSITY) {
            return String.format(Locale.US, "%2.1f m", new Object[]{Double.valueOf(this.f10026a)});
        } else if (this.f10026a < 0.001d) {
            return this.f10026a + " m";
        } else {
            return String.format(Locale.US, "%2.1f mm", new Object[]{Double.valueOf(this.f10026a * 1000.0d)});
        }
    }
}
