package com.p016a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.fimi.kernel.p076b.p080d.C1142e;
import org.json.JSONObject;

/* renamed from: com.a.fh */
public class fh {
    private static fh f1202e;
    Context f1203a;
    SharedPreferences f1204b;
    Editor f1205c;
    private String f1206d;

    private fh(Context context) {
        this.f1206d = null;
        this.f1204b = null;
        this.f1205c = null;
        this.f1203a = context;
        if (this.f1206d == null) {
            this.f1206d = cs.m1375a("MD5", this.f1203a.getPackageName());
        }
    }

    public static fh m1812a(Context context) {
        if (f1202e == null) {
            f1202e = new fh(context);
        }
        return f1202e;
    }

    public AMapLocation m1813a() {
        AMapLocation aMapLocation;
        AMapLocation aMapLocation2 = null;
        if (this.f1203a != null) {
            SharedPreferences sharedPreferences = this.f1203a.getSharedPreferences("pref", 0);
            if (sharedPreferences != null) {
                Object str;
                String str2 = "lastfix" + ev.f1147f;
                if (sharedPreferences.contains(str2)) {
                    try {
                        String string = sharedPreferences.getString(str2, null);
                        if (string != null) {
                            str = new String(cs.m1384d(fy.m1905b(string), this.f1206d), C1142e.f5201a);
                        } else {
                            String str3 = string;
                        }
                    } catch (Throwable th) {
                        ev.m1777a(th, "LastLocationManager", "getLastFix part1");
                        sharedPreferences.edit().remove(str2).commit();
                        aMapLocation = aMapLocation2;
                    }
                } else {
                    aMapLocation = aMapLocation2;
                }
                if (!TextUtils.isEmpty(str)) {
                    try {
                        aMapLocation2 = ev.m1774a(new JSONObject(str));
                    } catch (Throwable th2) {
                        ev.m1777a(th2, "LastLocationManager", "getLastFix part2");
                    }
                }
            }
        }
        return aMapLocation2;
    }

    public void m1814a(AMapLocation aMapLocation) {
        Object obj = null;
        if (this.f1203a != null && dn.m1511a(aMapLocation) && aMapLocation.getLocationType() != 2) {
            if (this.f1204b == null) {
                this.f1204b = this.f1203a.getSharedPreferences("pref", 0);
            }
            if (this.f1205c == null) {
                this.f1205c = this.f1204b.edit();
            }
            if (!TextUtils.isEmpty(aMapLocation.toStr())) {
                byte[] c;
                try {
                    c = cs.m1383c(aMapLocation.toStr().getBytes(C1142e.f5201a), this.f1206d);
                } catch (Throwable th) {
                    ev.m1777a(th, "LastLocationManager", "setLastFix");
                }
                obj = fy.m1904b(c);
            }
            if (!TextUtils.isEmpty(obj)) {
                this.f1205c.putString("lastfix" + ev.f1147f, obj);
                dm.m1493a(this.f1205c);
            }
        }
    }
}
