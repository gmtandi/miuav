package com.amap.api.location;

import android.location.Location;
import android.os.Bundle;
import com.amap.api.services.district.DistrictSearchQuery;
import com.p016a.ev;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

public class AMapLocation extends Location {
    public static final int ERROR_CODE_FAILURE_AUTH = 7;
    public static final int ERROR_CODE_FAILURE_CELL = 11;
    public static final int ERROR_CODE_FAILURE_CONNECTION = 4;
    public static final int ERROR_CODE_FAILURE_INIT = 9;
    public static final int ERROR_CODE_FAILURE_LOCATION = 6;
    public static final int ERROR_CODE_FAILURE_LOCATION_PARAMETER = 3;
    public static final int ERROR_CODE_FAILURE_LOCATION_PERMISSION = 12;
    public static final int ERROR_CODE_FAILURE_PARSER = 5;
    public static final int ERROR_CODE_FAILURE_WIFI_INFO = 2;
    public static final int ERROR_CODE_INVALID_PARAMETER = 1;
    public static final int ERROR_CODE_SERVICE_FAIL = 10;
    public static final int ERROR_CODE_UNKNOWN = 8;
    public static final int LOCATION_SUCCESS = 0;
    public static final int LOCATION_TYPE_AMAP = 7;
    public static final int LOCATION_TYPE_CELL = 6;
    public static final int LOCATION_TYPE_FAST = 3;
    public static final int LOCATION_TYPE_FIX_CACHE = 4;
    public static final int LOCATION_TYPE_GPS = 1;
    public static final int LOCATION_TYPE_OFFLINE = 8;
    public static final int LOCATION_TYPE_SAME_REQ = 2;
    public static final int LOCATION_TYPE_WIFI = 5;
    private String f1349a;
    private String f1350b;
    private String f1351c;
    private String f1352d;
    private String f1353e;
    private String f1354f;
    private String f1355g;
    private String f1356h;
    private String f1357i;
    private String f1358j;
    private String f1359k;
    private boolean f1360l;
    private int f1361m;
    private String f1362n;
    private String f1363o;
    private int f1364p;
    private double f1365q;
    private double f1366r;
    private int f1367s;
    private String f1368t;

    public AMapLocation(Location location) {
        super(location);
        this.f1349a = C2915a.f14760f;
        this.f1350b = C2915a.f14760f;
        this.f1351c = C2915a.f14760f;
        this.f1352d = C2915a.f14760f;
        this.f1353e = C2915a.f14760f;
        this.f1354f = C2915a.f14760f;
        this.f1355g = C2915a.f14760f;
        this.f1356h = C2915a.f14760f;
        this.f1357i = C2915a.f14760f;
        this.f1358j = C2915a.f14760f;
        this.f1359k = C2915a.f14760f;
        this.f1360l = true;
        this.f1361m = LOCATION_SUCCESS;
        this.f1362n = "success";
        this.f1363o = C2915a.f14760f;
        this.f1364p = LOCATION_SUCCESS;
        this.f1365q = 0.0d;
        this.f1366r = 0.0d;
        this.f1367s = LOCATION_SUCCESS;
        this.f1368t = C2915a.f14760f;
        this.f1365q = location.getLatitude();
        this.f1366r = location.getLongitude();
    }

    public AMapLocation(String str) {
        super(str);
        this.f1349a = C2915a.f14760f;
        this.f1350b = C2915a.f14760f;
        this.f1351c = C2915a.f14760f;
        this.f1352d = C2915a.f14760f;
        this.f1353e = C2915a.f14760f;
        this.f1354f = C2915a.f14760f;
        this.f1355g = C2915a.f14760f;
        this.f1356h = C2915a.f14760f;
        this.f1357i = C2915a.f14760f;
        this.f1358j = C2915a.f14760f;
        this.f1359k = C2915a.f14760f;
        this.f1360l = true;
        this.f1361m = LOCATION_SUCCESS;
        this.f1362n = "success";
        this.f1363o = C2915a.f14760f;
        this.f1364p = LOCATION_SUCCESS;
        this.f1365q = 0.0d;
        this.f1366r = 0.0d;
        this.f1367s = LOCATION_SUCCESS;
        this.f1368t = C2915a.f14760f;
    }

    public float getAccuracy() {
        return super.getAccuracy();
    }

    public String getAdCode() {
        return this.f1353e;
    }

    public String getAddress() {
        return this.f1354f;
    }

    public double getAltitude() {
        return super.getAltitude();
    }

    public String getAoiName() {
        return this.f1368t;
    }

    public float getBearing() {
        return super.getBearing();
    }

    public String getCity() {
        return this.f1350b;
    }

    public String getCityCode() {
        return this.f1352d;
    }

    public String getCountry() {
        return this.f1356h;
    }

    public String getDistrict() {
        return this.f1351c;
    }

    public int getErrorCode() {
        return this.f1361m;
    }

    public String getErrorInfo() {
        return this.f1362n;
    }

    public double getLatitude() {
        return this.f1365q;
    }

    public String getLocationDetail() {
        return this.f1363o;
    }

    public int getLocationType() {
        return this.f1364p;
    }

    public double getLongitude() {
        return this.f1366r;
    }

    public String getPoiName() {
        return this.f1355g;
    }

    public String getProvider() {
        return super.getProvider();
    }

    public String getProvince() {
        return this.f1349a;
    }

    public String getRoad() {
        return this.f1357i;
    }

    public int getSatellites() {
        return this.f1367s;
    }

    public float getSpeed() {
        return super.getSpeed();
    }

    public String getStreet() {
        return this.f1358j;
    }

    public String getStreetNum() {
        return this.f1359k;
    }

    public boolean isOffset() {
        return this.f1360l;
    }

    public void setAdCode(String str) {
        this.f1353e = str;
    }

    public void setAddress(String str) {
        this.f1354f = str;
    }

    public void setAoiName(String str) {
        this.f1368t = str;
    }

    public void setCity(String str) {
        this.f1350b = str;
    }

    public void setCityCode(String str) {
        this.f1352d = str;
    }

    public void setCountry(String str) {
        this.f1356h = str;
    }

    public void setDistrict(String str) {
        this.f1351c = str;
    }

    public void setErrorCode(int i) {
        if (this.f1361m == 0) {
            switch (i) {
                case LOCATION_SUCCESS /*0*/:
                    this.f1362n = "success";
                    break;
                case LOCATION_TYPE_GPS /*1*/:
                    this.f1362n = "\u91cd\u8981\u53c2\u6570\u4e3a\u7a7a";
                    break;
                case LOCATION_TYPE_SAME_REQ /*2*/:
                    this.f1362n = "WIFI\u4fe1\u606f\u4e0d\u8db3";
                    break;
                case LOCATION_TYPE_FAST /*3*/:
                    this.f1362n = "\u8bf7\u6c42\u53c2\u6570\u83b7\u53d6\u51fa\u73b0\u5f02\u5e38";
                    break;
                case LOCATION_TYPE_FIX_CACHE /*4*/:
                    this.f1362n = "\u7f51\u7edc\u8fde\u63a5\u5f02\u5e38";
                    break;
                case LOCATION_TYPE_WIFI /*5*/:
                    this.f1362n = "\u89e3\u6790XML\u51fa\u9519";
                    break;
                case LOCATION_TYPE_CELL /*6*/:
                    this.f1362n = "\u5b9a\u4f4d\u7ed3\u679c\u9519\u8bef";
                    break;
                case LOCATION_TYPE_AMAP /*7*/:
                    this.f1362n = "KEY\u9519\u8bef";
                    break;
                case LOCATION_TYPE_OFFLINE /*8*/:
                    this.f1362n = "\u5176\u4ed6\u9519\u8bef";
                    break;
                case ERROR_CODE_FAILURE_INIT /*9*/:
                    this.f1362n = "\u521d\u59cb\u5316\u5f02\u5e38";
                    break;
                case ERROR_CODE_SERVICE_FAIL /*10*/:
                    this.f1362n = "\u5b9a\u4f4d\u670d\u52a1\u542f\u52a8\u5931\u8d25";
                    break;
                case ERROR_CODE_FAILURE_CELL /*11*/:
                    this.f1362n = "\u9519\u8bef\u7684\u57fa\u7ad9\u4fe1\u606f\uff0c\u8bf7\u68c0\u67e5\u662f\u5426\u63d2\u5165SIM\u5361";
                    break;
                case ERROR_CODE_FAILURE_LOCATION_PERMISSION /*12*/:
                    this.f1362n = "\u7f3a\u5c11\u5b9a\u4f4d\u6743\u9650";
                    break;
            }
            this.f1361m = i;
        }
    }

    public void setErrorInfo(String str) {
        this.f1362n = str;
    }

    public void setLatitude(double d) {
        this.f1365q = d;
    }

    public void setLocationDetail(String str) {
        this.f1363o = str;
    }

    public void setLocationType(int i) {
        this.f1364p = i;
    }

    public void setLongitude(double d) {
        this.f1366r = d;
    }

    public void setNumber(String str) {
        this.f1359k = str;
    }

    public void setOffset(boolean z) {
        this.f1360l = z;
    }

    public void setPoiName(String str) {
        this.f1355g = str;
    }

    public void setProvince(String str) {
        this.f1349a = str;
    }

    public void setRoad(String str) {
        this.f1357i = str;
    }

    public void setSatellites(int i) {
        this.f1367s = i;
    }

    public void setStreet(String str) {
        this.f1358j = str;
    }

    public String toStr() {
        return toStr(LOCATION_TYPE_GPS);
    }

    public String toStr(int i) {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        try {
            jSONObject = new JSONObject();
            switch (i) {
                case LOCATION_TYPE_GPS /*1*/:
                    jSONObject.put(DistrictSearchQuery.KEYWORDS_COUNTRY, this.f1356h);
                    jSONObject.put(DistrictSearchQuery.KEYWORDS_PROVINCE, this.f1349a);
                    jSONObject.put(DistrictSearchQuery.KEYWORDS_CITY, this.f1350b);
                    jSONObject.put("cityCode", this.f1352d);
                    jSONObject.put(DistrictSearchQuery.KEYWORDS_DISTRICT, this.f1351c);
                    jSONObject.put("adCode", this.f1353e);
                    jSONObject.put("address", this.f1354f);
                    jSONObject.put("road", this.f1357i);
                    jSONObject.put("street", this.f1358j);
                    jSONObject.put("number", this.f1359k);
                    jSONObject.put("poiName", this.f1355g);
                    jSONObject.put("errorCode", this.f1361m);
                    jSONObject.put("errorInfo", this.f1362n);
                    jSONObject.put("locationDetail", this.f1363o);
                    jSONObject.put("altitude", getAltitude());
                    jSONObject.put("bearing", (double) getBearing());
                    jSONObject.put("speed", (double) getSpeed());
                    jSONObject.put("satellites", this.f1367s);
                    jSONObject.put("aoiName", this.f1368t);
                    Bundle extras = getExtras();
                    if (extras != null && extras.containsKey(SocialConstants.PARAM_APP_DESC)) {
                        jSONObject.put(SocialConstants.PARAM_APP_DESC, extras.getString(SocialConstants.PARAM_APP_DESC));
                    }
                case LOCATION_TYPE_SAME_REQ /*2*/:
                    jSONObject.put("time", getTime());
                    break;
                case LOCATION_TYPE_FAST /*3*/:
                    break;
            }
        } catch (Throwable th) {
            ev.m1777a(th, "AMapLocation", "toStr part2");
            jSONObject2 = null;
        }
        jSONObject.put("locationType", this.f1364p);
        jSONObject.put("accuracy", (double) getAccuracy());
        jSONObject.put("latitude", getLatitude());
        jSONObject.put("longitude", getLongitude());
        jSONObject.put("provider", getProvider());
        jSONObject2 = jSONObject;
        return jSONObject2 == null ? null : jSONObject2.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("latitude=" + this.f1365q);
        stringBuffer.append("longitude=" + this.f1366r);
        stringBuffer.append("province=" + this.f1349a + "#");
        stringBuffer.append("city=" + this.f1350b + "#");
        stringBuffer.append("district=" + this.f1351c + "#");
        stringBuffer.append("cityCode=" + this.f1352d + "#");
        stringBuffer.append("adCode=" + this.f1353e + "#");
        stringBuffer.append("address=" + this.f1354f + "#");
        stringBuffer.append("country=" + this.f1356h + "#");
        stringBuffer.append("road=" + this.f1357i + "#");
        stringBuffer.append("poiName=" + this.f1355g + "#");
        stringBuffer.append("street=" + this.f1358j + "#");
        stringBuffer.append("streetNum=" + this.f1359k + "#");
        stringBuffer.append("aoiName=" + this.f1368t + "#");
        stringBuffer.append("errorCode=" + this.f1361m + "#");
        stringBuffer.append("errorInfo=" + this.f1362n + "#");
        stringBuffer.append("locationDetail=" + this.f1363o + "#");
        stringBuffer.append("locationType=" + this.f1364p);
        return stringBuffer.toString();
    }
}
