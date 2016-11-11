package com.amap.api.maps;

import android.content.Context;
import com.amap.api.mapcore.util.C0334a;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.model.LatLng;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class CoordinateConverter {
    private Context f2622a;
    private CoordType f2623b;
    private LatLng f2624c;

    public enum CoordType {
        BAIDU,
        MAPBAR,
        GPS,
        MAPABC,
        SOSOMAP,
        ALIYUN,
        GOOGLE
    }

    public CoordinateConverter(Context context) {
        this.f2623b = null;
        this.f2624c = null;
        this.f2622a = context;
    }

    public LatLng convert() {
        if (this.f2623b == null || this.f2624c == null) {
            return null;
        }
        try {
            switch (C0427a.f2642a[this.f2623b.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    return C0334a.m3349a(this.f2624c);
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    return C0334a.m3355b(this.f2622a, this.f2624c);
                case Type.BYTE /*3*/:
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                case Type.INT /*5*/:
                case Type.FLOAT /*6*/:
                    return this.f2624c;
                case Type.LONG /*7*/:
                    return C0334a.m3348a(this.f2622a, this.f2624c);
                default:
                    return null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            ce.m3829a(th, "CoordinateConverter", "convert");
            return this.f2624c;
        }
    }

    public CoordinateConverter coord(LatLng latLng) {
        this.f2624c = latLng;
        return this;
    }

    public CoordinateConverter from(CoordType coordType) {
        this.f2623b = coordType;
        return this;
    }
}
