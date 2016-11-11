package com.autonavi.aps.amapapi.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.amap.api.services.district.DistrictSearchQuery;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.p016a.dn;
import com.p016a.ev;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

@SuppressLint({"NewApi"})
public class AmapLoc implements Parcelable {
    public static final Creator<AmapLoc> CREATOR;
    private String f3734A;
    private String f3735B;
    private int f3736C;
    private String f3737D;
    private String f3738E;
    private String f3739F;
    private boolean f3740G;
    private boolean f3741H;
    private JSONObject f3742I;
    private String f3743a;
    private double f3744b;
    private double f3745c;
    private double f3746d;
    private float f3747e;
    private float f3748f;
    private float f3749g;
    private long f3750h;
    private String f3751i;
    private int f3752j;
    private String f3753k;
    private int f3754l;
    private String f3755m;
    private String f3756n;
    private String f3757o;
    private String f3758p;
    private String f3759q;
    private String f3760r;
    private String f3761s;
    private String f3762t;
    private String f3763u;
    private String f3764v;
    private String f3765w;
    private String f3766x;
    private String f3767y;
    private String f3768z;

    static {
        CREATOR = new C0615a();
    }

    public AmapLoc() {
        this.f3743a = C2915a.f14760f;
        this.f3744b = 0.0d;
        this.f3745c = 0.0d;
        this.f3746d = 0.0d;
        this.f3747e = 0.0f;
        this.f3748f = 0.0f;
        this.f3749g = 0.0f;
        this.f3750h = 0;
        this.f3751i = "new";
        this.f3752j = 0;
        this.f3753k = "success";
        this.f3754l = 0;
        this.f3755m = C2915a.f14760f;
        this.f3756n = C2915a.f14760f;
        this.f3757o = C2915a.f14760f;
        this.f3758p = C2915a.f14760f;
        this.f3759q = C2915a.f14760f;
        this.f3760r = C2915a.f14760f;
        this.f3761s = C2915a.f14760f;
        this.f3762t = C2915a.f14760f;
        this.f3763u = C2915a.f14760f;
        this.f3764v = C2915a.f14760f;
        this.f3765w = C2915a.f14760f;
        this.f3766x = C2915a.f14760f;
        this.f3767y = C2915a.f14760f;
        this.f3768z = null;
        this.f3734A = C2915a.f14760f;
        this.f3735B = C2915a.f14760f;
        this.f3736C = -1;
        this.f3737D = C2915a.f14760f;
        this.f3738E = C2915a.f14760f;
        this.f3739F = C2915a.f14760f;
        this.f3740G = true;
        this.f3741H = true;
        this.f3742I = null;
    }

    public AmapLoc(Parcel parcel) {
        boolean z = true;
        this.f3743a = C2915a.f14760f;
        this.f3744b = 0.0d;
        this.f3745c = 0.0d;
        this.f3746d = 0.0d;
        this.f3747e = 0.0f;
        this.f3748f = 0.0f;
        this.f3749g = 0.0f;
        this.f3750h = 0;
        this.f3751i = "new";
        this.f3752j = 0;
        this.f3753k = "success";
        this.f3754l = 0;
        this.f3755m = C2915a.f14760f;
        this.f3756n = C2915a.f14760f;
        this.f3757o = C2915a.f14760f;
        this.f3758p = C2915a.f14760f;
        this.f3759q = C2915a.f14760f;
        this.f3760r = C2915a.f14760f;
        this.f3761s = C2915a.f14760f;
        this.f3762t = C2915a.f14760f;
        this.f3763u = C2915a.f14760f;
        this.f3764v = C2915a.f14760f;
        this.f3765w = C2915a.f14760f;
        this.f3766x = C2915a.f14760f;
        this.f3767y = C2915a.f14760f;
        this.f3768z = null;
        this.f3734A = C2915a.f14760f;
        this.f3735B = C2915a.f14760f;
        this.f3736C = -1;
        this.f3737D = C2915a.f14760f;
        this.f3738E = C2915a.f14760f;
        this.f3739F = C2915a.f14760f;
        this.f3740G = true;
        this.f3741H = true;
        this.f3742I = null;
        this.f3743a = parcel.readString();
        this.f3751i = parcel.readString();
        this.f3753k = parcel.readString();
        this.f3752j = parcel.readInt();
        this.f3749g = parcel.readFloat();
        this.f3748f = parcel.readFloat();
        this.f3747e = parcel.readFloat();
        this.f3744b = parcel.readDouble();
        this.f3745c = parcel.readDouble();
        this.f3746d = parcel.readDouble();
        this.f3750h = parcel.readLong();
        this.f3756n = parcel.readString();
        this.f3757o = parcel.readString();
        this.f3758p = parcel.readString();
        this.f3759q = parcel.readString();
        this.f3760r = parcel.readString();
        this.f3761s = parcel.readString();
        this.f3762t = parcel.readString();
        this.f3763u = parcel.readString();
        this.f3764v = parcel.readString();
        this.f3765w = parcel.readString();
        this.f3766x = parcel.readString();
        this.f3767y = parcel.readString();
        this.f3768z = parcel.readString();
        this.f3734A = parcel.readString();
        this.f3735B = parcel.readString();
        this.f3737D = parcel.readString();
        this.f3755m = parcel.readString();
        this.f3736C = parcel.readInt();
        this.f3754l = parcel.readInt();
        this.f3738E = parcel.readString();
        this.f3740G = parcel.readByte() != null;
        if (parcel.readByte() == null) {
            z = false;
        }
        this.f3741H = z;
        this.f3739F = parcel.readString();
    }

    public AmapLoc(JSONObject jSONObject) {
        this.f3743a = C2915a.f14760f;
        this.f3744b = 0.0d;
        this.f3745c = 0.0d;
        this.f3746d = 0.0d;
        this.f3747e = 0.0f;
        this.f3748f = 0.0f;
        this.f3749g = 0.0f;
        this.f3750h = 0;
        this.f3751i = "new";
        this.f3752j = 0;
        this.f3753k = "success";
        this.f3754l = 0;
        this.f3755m = C2915a.f14760f;
        this.f3756n = C2915a.f14760f;
        this.f3757o = C2915a.f14760f;
        this.f3758p = C2915a.f14760f;
        this.f3759q = C2915a.f14760f;
        this.f3760r = C2915a.f14760f;
        this.f3761s = C2915a.f14760f;
        this.f3762t = C2915a.f14760f;
        this.f3763u = C2915a.f14760f;
        this.f3764v = C2915a.f14760f;
        this.f3765w = C2915a.f14760f;
        this.f3766x = C2915a.f14760f;
        this.f3767y = C2915a.f14760f;
        this.f3768z = null;
        this.f3734A = C2915a.f14760f;
        this.f3735B = C2915a.f14760f;
        this.f3736C = -1;
        this.f3737D = C2915a.f14760f;
        this.f3738E = C2915a.f14760f;
        this.f3739F = C2915a.f14760f;
        this.f3740G = true;
        this.f3741H = true;
        this.f3742I = null;
        if (jSONObject != null) {
            try {
                if (dn.m1514a(jSONObject, "provider")) {
                    m5328c(jSONObject.getString("provider"));
                }
                if (dn.m1514a(jSONObject, "lon")) {
                    m5311a(jSONObject.getDouble("lon"));
                }
                if (dn.m1514a(jSONObject, "lat")) {
                    m5319b(jSONObject.getDouble("lat"));
                }
                if (dn.m1514a(jSONObject, "altitude")) {
                    m5326c(jSONObject.getDouble("altitude"));
                }
                if (dn.m1514a(jSONObject, "acc")) {
                    m5303z(jSONObject.getString("acc"));
                }
                if (dn.m1514a(jSONObject, "accuracy")) {
                    m5312a((float) jSONObject.getLong("accuracy"));
                }
                if (dn.m1514a(jSONObject, "speed")) {
                    m5320b((float) jSONObject.getLong("speed"));
                }
                if (dn.m1514a(jSONObject, "dir")) {
                    m5327c((float) jSONObject.getLong("dir"));
                }
                if (dn.m1514a(jSONObject, "bearing")) {
                    m5327c((float) jSONObject.getLong("bearing"));
                }
                if (dn.m1514a(jSONObject, SocialConstants.PARAM_TYPE)) {
                    m5333f(jSONObject.getString(SocialConstants.PARAM_TYPE));
                }
                if (dn.m1514a(jSONObject, "retype")) {
                    m5336g(jSONObject.getString("retype"));
                }
                if (dn.m1514a(jSONObject, "citycode")) {
                    m5340i(jSONObject.getString("citycode"));
                }
                if (dn.m1514a(jSONObject, SocialConstants.PARAM_APP_DESC)) {
                    m5342j(jSONObject.getString(SocialConstants.PARAM_APP_DESC));
                }
                if (dn.m1514a(jSONObject, "adcode")) {
                    m5344k(jSONObject.getString("adcode"));
                }
                if (dn.m1514a(jSONObject, DistrictSearchQuery.KEYWORDS_COUNTRY)) {
                    m5346l(jSONObject.getString(DistrictSearchQuery.KEYWORDS_COUNTRY));
                }
                if (dn.m1514a(jSONObject, DistrictSearchQuery.KEYWORDS_PROVINCE)) {
                    m5348m(jSONObject.getString(DistrictSearchQuery.KEYWORDS_PROVINCE));
                }
                if (dn.m1514a(jSONObject, DistrictSearchQuery.KEYWORDS_CITY)) {
                    m5350n(jSONObject.getString(DistrictSearchQuery.KEYWORDS_CITY));
                }
                if (dn.m1514a(jSONObject, "road")) {
                    m5354p(jSONObject.getString("road"));
                }
                if (dn.m1514a(jSONObject, "street")) {
                    m5356q(jSONObject.getString("street"));
                }
                if (dn.m1514a(jSONObject, "number")) {
                    m5358r(jSONObject.getString("number"));
                }
                if (dn.m1514a(jSONObject, "poiname")) {
                    m5360s(jSONObject.getString("poiname"));
                }
                if (dn.m1514a(jSONObject, "aoiname")) {
                    m5362t(jSONObject.getString("aoiname"));
                }
                if (dn.m1514a(jSONObject, "errorCode")) {
                    m5321b(jSONObject.getInt("errorCode"));
                }
                if (dn.m1514a(jSONObject, "errorInfo")) {
                    m5315a(jSONObject.getString("errorInfo"));
                }
                if (dn.m1514a(jSONObject, "locationType")) {
                    m5313a(jSONObject.getInt("locationType"));
                }
                if (dn.m1514a(jSONObject, "locationDetail")) {
                    m5322b(jSONObject.getString("locationDetail"));
                }
                if (dn.m1514a(jSONObject, "cens")) {
                    m5364u(jSONObject.getString("cens"));
                }
                if (dn.m1514a(jSONObject, "poiid")) {
                    m5366v(jSONObject.getString("poiid"));
                }
                if (dn.m1514a(jSONObject, "pid")) {
                    m5366v(jSONObject.getString("pid"));
                }
                if (dn.m1514a(jSONObject, "floor")) {
                    m5368w(jSONObject.getString("floor"));
                }
                if (dn.m1514a(jSONObject, "flr")) {
                    m5368w(jSONObject.getString("flr"));
                }
                if (dn.m1514a(jSONObject, "coord")) {
                    m5370x(jSONObject.getString("coord"));
                }
                if (dn.m1514a(jSONObject, "mcell")) {
                    m5372y(jSONObject.getString("mcell"));
                }
                if (dn.m1514a(jSONObject, "time")) {
                    m5314a(jSONObject.getLong("time"));
                }
                if (dn.m1514a(jSONObject, DistrictSearchQuery.KEYWORDS_DISTRICT)) {
                    m5352o(jSONObject.getString(DistrictSearchQuery.KEYWORDS_DISTRICT));
                }
                if (dn.m1514a(jSONObject, "isOffset")) {
                    m5317a(jSONObject.getBoolean("isOffset"));
                }
                if (dn.m1514a(jSONObject, "isReversegeo")) {
                    m5323b(jSONObject.getBoolean("isReversegeo"));
                }
            } catch (Throwable th) {
                ev.m1777a(th, "AmapLoc", "AmapLoc");
            }
        }
    }

    private void m5301A(String str) {
        this.f3748f = Float.parseFloat(str);
        if (this.f3748f > 100.0f) {
            this.f3748f = 0.0f;
        }
    }

    private void m5302B(String str) {
        this.f3749g = Float.parseFloat(str);
    }

    private void m5303z(String str) {
        this.f3747e = Float.parseFloat(str);
    }

    public String m5304A() {
        return this.f3734A;
    }

    public int m5305B() {
        return this.f3736C;
    }

    public String m5306C() {
        return this.f3737D;
    }

    public AmapLoc m5307D() {
        Object C = m5306C();
        if (TextUtils.isEmpty(C)) {
            return null;
        }
        String[] split = C.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
        if (split.length != 3) {
            return null;
        }
        AmapLoc amapLoc = new AmapLoc();
        amapLoc.m5328c(m5335g());
        amapLoc.m5330d(split[0]);
        amapLoc.m5331e(split[1]);
        amapLoc.m5312a(Float.parseFloat(split[2]));
        amapLoc.m5340i(m5351o());
        amapLoc.m5344k(m5355q());
        amapLoc.m5346l(m5357r());
        amapLoc.m5348m(m5359s());
        amapLoc.m5350n(m5361t());
        amapLoc.m5314a(m5343k());
        amapLoc.m5333f(m5345l());
        amapLoc.m5370x(String.valueOf(m5305B()));
        return dn.m1512a(amapLoc) ? amapLoc : null;
    }

    public JSONObject m5308E() {
        return this.f3742I;
    }

    public String m5309F() {
        return m5325c(1);
    }

    public int m5310a() {
        return this.f3752j;
    }

    public void m5311a(double d) {
        m5330d(dn.m1505a(Double.valueOf(d), "#.000000"));
    }

    public void m5312a(float f) {
        m5303z(String.valueOf(Math.round(f)));
    }

    public void m5313a(int i) {
        this.f3754l = i;
    }

    public void m5314a(long j) {
        this.f3750h = j;
    }

    public void m5315a(String str) {
        this.f3753k = str;
    }

    public void m5316a(JSONObject jSONObject) {
        this.f3742I = jSONObject;
    }

    public void m5317a(boolean z) {
        this.f3740G = z;
    }

    public int m5318b() {
        return this.f3754l;
    }

    public void m5319b(double d) {
        m5331e(dn.m1505a(Double.valueOf(d), "#.000000"));
    }

    public void m5320b(float f) {
        m5301A(dn.m1505a(Float.valueOf(f), "#.0"));
    }

    public void m5321b(int i) {
        if (this.f3752j == 0) {
            switch (i) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    this.f3753k = "success";
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    this.f3753k = "\u91cd\u8981\u53c2\u6570\u4e3a\u7a7a";
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    this.f3753k = "WIFI\u4fe1\u606f\u4e0d\u8db3";
                    break;
                case Type.BYTE /*3*/:
                    this.f3753k = "\u8bf7\u6c42\u53c2\u6570\u83b7\u53d6\u51fa\u73b0\u5f02\u5e38";
                    break;
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    this.f3753k = "\u7f51\u7edc\u8fde\u63a5\u5f02\u5e38";
                    break;
                case Type.INT /*5*/:
                    this.f3753k = "\u89e3\u6790XML\u51fa\u9519";
                    break;
                case Type.FLOAT /*6*/:
                    this.f3753k = "\u5b9a\u4f4d\u7ed3\u679c\u9519\u8bef";
                    break;
                case Type.LONG /*7*/:
                    this.f3753k = "KEY\u9519\u8bef";
                    break;
                case Type.DOUBLE /*8*/:
                    this.f3753k = "\u5176\u4ed6\u9519\u8bef";
                    break;
                case Type.ARRAY /*9*/:
                    this.f3753k = "\u521d\u59cb\u5316\u5f02\u5e38";
                    break;
                case Type.OBJECT /*10*/:
                    this.f3753k = "\u5b9a\u4f4d\u670d\u52a1\u542f\u52a8\u5931\u8d25";
                    break;
                case Opcodes.T_LONG /*11*/:
                    this.f3753k = "\u9519\u8bef\u7684\u57fa\u7ad9\u4fe1\u606f\uff0c\u8bf7\u68c0\u67e5\u662f\u5426\u63d2\u5165SIM\u5361";
                    break;
                case Opcodes.FCONST_1 /*12*/:
                    this.f3753k = "\u7f3a\u5c11\u5b9a\u4f4d\u6743\u9650";
                    break;
            }
            this.f3752j = i;
        }
    }

    public void m5322b(String str) {
        if (this.f3755m == null || this.f3755m.length() == 0) {
            this.f3755m = str;
        }
    }

    public void m5323b(boolean z) {
        this.f3741H = z;
    }

    public String m5324c() {
        return this.f3753k;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String m5325c(int r7) {
        /*
        r6 = this;
        r0 = 0;
        r1 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x0111 }
        r1.<init>();	 Catch:{ Throwable -> 0x0111 }
        switch(r7) {
            case 1: goto L_0x000c;
            case 2: goto L_0x00d6;
            case 3: goto L_0x00dd;
            default: goto L_0x0009;
        };	 Catch:{ Throwable -> 0x0111 }
    L_0x0009:
        if (r1 != 0) goto L_0x011c;
    L_0x000b:
        return r0;
    L_0x000c:
        r2 = "altitude";
        r4 = r6.f3746d;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r4);	 Catch:{ Throwable -> 0x0111 }
        r2 = "speed";
        r3 = r6.f3748f;	 Catch:{ Throwable -> 0x0111 }
        r4 = (double) r3;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r4);	 Catch:{ Throwable -> 0x0111 }
        r2 = "bearing";
        r3 = r6.f3749g;	 Catch:{ Throwable -> 0x0111 }
        r4 = (double) r3;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r4);	 Catch:{ Throwable -> 0x0111 }
        r2 = "retype";
        r3 = r6.f3756n;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "citycode";
        r3 = r6.f3758p;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "desc";
        r3 = r6.f3759q;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "adcode";
        r3 = r6.f3760r;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "country";
        r3 = r6.f3761s;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "province";
        r3 = r6.f3762t;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "city";
        r3 = r6.f3763u;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "district";
        r3 = r6.f3764v;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "road";
        r3 = r6.f3765w;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "street";
        r3 = r6.f3766x;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "number";
        r3 = r6.f3738E;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "poiname";
        r3 = r6.f3767y;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "cens";
        r3 = r6.f3768z;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "poiid";
        r3 = r6.f3734A;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "floor";
        r3 = r6.f3735B;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "coord";
        r3 = r6.f3736C;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "mcell";
        r3 = r6.f3737D;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "errorCode";
        r3 = r6.f3752j;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "errorInfo";
        r3 = r6.f3753k;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "locationType";
        r3 = r6.f3754l;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "locationDetail";
        r3 = r6.f3755m;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "aoiname";
        r3 = r6.f3739F;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = r6.f3742I;	 Catch:{ Throwable -> 0x0111 }
        if (r2 == 0) goto L_0x00d6;
    L_0x00c1:
        r2 = "offpct";
        r2 = com.p016a.dn.m1514a(r1, r2);	 Catch:{ Throwable -> 0x0111 }
        if (r2 == 0) goto L_0x00d6;
    L_0x00c9:
        r2 = "offpct";
        r3 = r6.f3742I;	 Catch:{ Throwable -> 0x0111 }
        r4 = "offpct";
        r3 = r3.getString(r4);	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
    L_0x00d6:
        r2 = "time";
        r4 = r6.f3750h;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r4);	 Catch:{ Throwable -> 0x0111 }
    L_0x00dd:
        r2 = "provider";
        r3 = r6.f3743a;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "lon";
        r4 = r6.f3744b;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r4);	 Catch:{ Throwable -> 0x0111 }
        r2 = "lat";
        r4 = r6.f3745c;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r4);	 Catch:{ Throwable -> 0x0111 }
        r2 = "accuracy";
        r3 = r6.f3747e;	 Catch:{ Throwable -> 0x0111 }
        r4 = (double) r3;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r4);	 Catch:{ Throwable -> 0x0111 }
        r2 = "type";
        r3 = r6.f3751i;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "isOffset";
        r3 = r6.f3740G;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "isReversegeo";
        r3 = r6.f3741H;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        goto L_0x0009;
    L_0x0111:
        r1 = move-exception;
        r2 = "AmapLoc";
        r3 = "toStr";
        com.p016a.ev.m1777a(r1, r2, r3);
        r1 = r0;
        goto L_0x0009;
    L_0x011c:
        r0 = r1.toString();
        goto L_0x000b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.model.AmapLoc.c(int):java.lang.String");
    }

    public void m5326c(double d) {
        this.f3746d = d;
    }

    public void m5327c(float f) {
        m5302B(dn.m1505a(Float.valueOf(f), "#.0"));
    }

    public void m5328c(String str) {
        this.f3743a = str;
    }

    public String m5329d() {
        return this.f3755m;
    }

    public void m5330d(String str) {
        this.f3744b = Double.parseDouble(str);
    }

    public int describeContents() {
        return 0;
    }

    public void m5331e(String str) {
        this.f3745c = Double.parseDouble(str);
    }

    public boolean m5332e() {
        return this.f3740G;
    }

    public void m5333f(String str) {
        this.f3751i = str;
    }

    public boolean m5334f() {
        return this.f3741H;
    }

    public String m5335g() {
        return this.f3743a;
    }

    public void m5336g(String str) {
        this.f3756n = str;
    }

    public double m5337h() {
        return this.f3744b;
    }

    public void m5338h(String str) {
        this.f3757o = str;
    }

    public double m5339i() {
        return this.f3745c;
    }

    public void m5340i(String str) {
        this.f3758p = str;
    }

    public float m5341j() {
        return this.f3747e;
    }

    public void m5342j(String str) {
        this.f3759q = str;
    }

    public long m5343k() {
        return this.f3750h;
    }

    public void m5344k(String str) {
        this.f3760r = str;
    }

    public String m5345l() {
        return this.f3751i;
    }

    public void m5346l(String str) {
        this.f3761s = str;
    }

    public String m5347m() {
        return this.f3756n;
    }

    public void m5348m(String str) {
        this.f3762t = str;
    }

    public String m5349n() {
        return this.f3757o;
    }

    public void m5350n(String str) {
        this.f3763u = str;
    }

    public String m5351o() {
        return this.f3758p;
    }

    public void m5352o(String str) {
        this.f3764v = str;
    }

    public String m5353p() {
        return this.f3759q;
    }

    public void m5354p(String str) {
        this.f3765w = str;
    }

    public String m5355q() {
        return this.f3760r;
    }

    public void m5356q(String str) {
        this.f3766x = str;
    }

    public String m5357r() {
        return this.f3761s;
    }

    public void m5358r(String str) {
        this.f3738E = str;
    }

    public String m5359s() {
        return this.f3762t;
    }

    public void m5360s(String str) {
        this.f3767y = str;
    }

    public String m5361t() {
        return this.f3763u;
    }

    public void m5362t(String str) {
        this.f3739F = str;
    }

    public String m5363u() {
        return this.f3764v;
    }

    public void m5364u(String str) {
        if (!TextUtils.isEmpty(str)) {
            for (Object obj : str.split("\\*")) {
                if (!TextUtils.isEmpty(obj)) {
                    String[] split = obj.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                    m5311a(Double.parseDouble(split[0]));
                    m5319b(Double.parseDouble(split[1]));
                    m5312a((float) Integer.parseInt(split[2]));
                    break;
                }
            }
            this.f3768z = str;
        }
    }

    public String m5365v() {
        return this.f3765w;
    }

    public void m5366v(String str) {
        this.f3734A = str;
    }

    public String m5367w() {
        return this.f3766x;
    }

    public void m5368w(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = str.replace("F", C2915a.f14760f);
            try {
                Integer.parseInt(str);
            } catch (Throwable th) {
                str = null;
                ev.m1777a(th, "AmapLoc", "setFloor");
            }
        }
        this.f3735B = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        byte b = (byte) 1;
        parcel.writeString(this.f3743a);
        parcel.writeString(this.f3751i);
        parcel.writeString(this.f3753k);
        parcel.writeInt(this.f3752j);
        parcel.writeFloat(this.f3749g);
        parcel.writeFloat(this.f3748f);
        parcel.writeFloat(this.f3747e);
        parcel.writeDouble(this.f3744b);
        parcel.writeDouble(this.f3745c);
        parcel.writeDouble(this.f3746d);
        parcel.writeLong(this.f3750h);
        parcel.writeString(this.f3756n);
        parcel.writeString(this.f3757o);
        parcel.writeString(this.f3758p);
        parcel.writeString(this.f3759q);
        parcel.writeString(this.f3760r);
        parcel.writeString(this.f3761s);
        parcel.writeString(this.f3762t);
        parcel.writeString(this.f3763u);
        parcel.writeString(this.f3764v);
        parcel.writeString(this.f3765w);
        parcel.writeString(this.f3766x);
        parcel.writeString(this.f3767y);
        parcel.writeString(this.f3768z);
        parcel.writeString(this.f3734A);
        parcel.writeString(this.f3735B);
        parcel.writeString(this.f3737D);
        parcel.writeString(this.f3755m);
        parcel.writeInt(this.f3736C);
        parcel.writeInt(this.f3754l);
        parcel.writeString(this.f3738E);
        parcel.writeByte(this.f3740G ? (byte) 1 : (byte) 0);
        if (!this.f3741H) {
            b = (byte) 0;
        }
        parcel.writeByte(b);
        parcel.writeString(this.f3739F);
    }

    public String m5369x() {
        return this.f3738E;
    }

    public void m5370x(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f3736C = -1;
        } else if (this.f3743a.equals(GeocodeSearch.GPS)) {
            this.f3736C = 0;
        } else if (str.equals(Constants.VIA_RESULT_SUCCESS)) {
            this.f3736C = 0;
        } else if (str.equals(Constants.VIA_TO_TYPE_QQ_GROUP)) {
            this.f3736C = 1;
        } else {
            this.f3736C = -1;
        }
    }

    public String m5371y() {
        return this.f3767y;
    }

    public void m5372y(String str) {
        this.f3737D = str;
    }

    public String m5373z() {
        return this.f3739F;
    }
}
