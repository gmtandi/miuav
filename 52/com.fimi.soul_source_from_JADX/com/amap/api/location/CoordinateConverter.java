package com.amap.api.location;

import android.content.Context;
import com.p016a.ev;
import com.p016a.fi;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class CoordinateConverter {
    DPoint f1390a;
    private Context f1391b;
    private CoordType f1392c;
    private DPoint f1393d;

    public enum CoordType {
        BAIDU,
        MAPBAR,
        MAPABC,
        SOSOMAP,
        ALIYUN,
        GOOGLE,
        GPS
    }

    public CoordinateConverter(Context context) {
        this.f1392c = null;
        this.f1393d = null;
        this.f1390a = null;
        this.f1391b = context;
    }

    public synchronized DPoint convert() {
        if (this.f1392c != null) {
            if (this.f1393d != null) {
                if (this.f1393d.getLongitude() <= 180.0d && this.f1393d.getLongitude() >= -180.0d) {
                    if (this.f1393d.getLatitude() <= 90.0d && this.f1393d.getLatitude() >= -90.0d) {
                        switch (C0267a.f1396a[this.f1392c.ordinal()]) {
                            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                                this.f1390a = fi.m1821a(this.f1393d);
                                break;
                            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                                this.f1390a = fi.m1826b(this.f1391b, this.f1393d);
                                break;
                            case Type.BYTE /*3*/:
                            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                            case Type.INT /*5*/:
                            case Type.FLOAT /*6*/:
                                this.f1390a = this.f1393d;
                                break;
                            case Type.LONG /*7*/:
                                this.f1390a = fi.m1820a(this.f1391b, this.f1393d);
                                break;
                        }
                    }
                    throw new IllegalArgumentException("\u8bf7\u4f20\u5165\u5408\u7406\u7eac\u5ea6");
                }
                throw new IllegalArgumentException("\u8bf7\u4f20\u5165\u5408\u7406\u7ecf\u5ea6");
            }
            throw new IllegalArgumentException("\u8f6c\u6362\u5750\u6807\u6e90\u4e0d\u80fd\u4e3a\u7a7a");
        }
        throw new IllegalArgumentException("\u8f6c\u6362\u5750\u6807\u7c7b\u578b\u4e0d\u80fd\u4e3a\u7a7a");
        return this.f1390a;
    }

    public synchronized CoordinateConverter coord(DPoint dPoint) {
        if (dPoint == null) {
            throw new IllegalArgumentException("\u4f20\u5165\u7ecf\u7eac\u5ea6\u5bf9\u8c61\u4e3a\u7a7a");
        } else if (dPoint.getLongitude() > 180.0d || dPoint.getLongitude() < -180.0d) {
            throw new IllegalArgumentException("\u8bf7\u4f20\u5165\u5408\u7406\u7ecf\u5ea6");
        } else if (dPoint.getLatitude() > 90.0d || dPoint.getLatitude() < -90.0d) {
            throw new IllegalArgumentException("\u8bf7\u4f20\u5165\u5408\u7406\u7eac\u5ea6");
        } else {
            this.f1393d = dPoint;
        }
        return this;
    }

    public synchronized CoordinateConverter from(CoordType coordType) {
        this.f1392c = coordType;
        return this;
    }

    public boolean isAMapDataAvailable(double d, double d2) {
        return ev.m1778a(d, d2);
    }
}
