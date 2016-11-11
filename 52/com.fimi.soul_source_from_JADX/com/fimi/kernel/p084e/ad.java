package com.fimi.kernel.p084e;

import android.content.Context;
import android.content.SharedPreferences;
import com.tencent.connect.common.Constants;

/* renamed from: com.fimi.kernel.e.ad */
public class ad {
    private static SharedPreferences f5267a = null;
    private static final String f5268b = "miserver_preferen_";
    private static final String f5269c = "bottomstateshow";
    private static final String f5270d = "flight_distance";
    private static final String f5271e = "flight_return_height";
    private static final String f5272f = "flight_h_v_speed";
    private static final String f5273g = "optical_flow_apply";
    private static final String f5274h = "pip_format_dialog";
    private static final String f5275i = "pip_tf_card_fault_dialog";
    private static final String f5276j = "force_attitude_dialog_show_count";

    static {
        f5267a = null;
    }

    public ad(Context context) {
        ad.m8019a(context);
    }

    public static SharedPreferences m8019a(Context context) {
        if (f5267a == null) {
            f5267a = context.getSharedPreferences(f5268b, 0);
        }
        return f5267a;
    }

    public Boolean m8020a() {
        return Boolean.valueOf(false);
    }

    public void m8021a(int i) {
        f5267a.edit().putInt("cameraplaystate", i).commit();
    }

    public void m8022a(Boolean bool) {
        f5267a.edit().putBoolean("camera_auto_download", bool.booleanValue()).commit();
    }

    public void m8023a(String str) {
        f5267a.edit().putString(f5270d, str).commit();
    }

    public void m8024a(boolean z) {
        f5267a.edit().putBoolean(f5269c, z).commit();
    }

    public int m8025b() {
        return f5267a.getInt("cameraplaystate", 0);
    }

    public void m8026b(int i) {
        f5267a.edit().putInt(f5276j, i).commit();
    }

    public void m8027b(String str) {
        f5267a.edit().putString(f5271e, str).commit();
    }

    public void m8028b(boolean z) {
        f5267a.edit().putBoolean(f5273g, z).commit();
    }

    public void m8029c(String str) {
        f5267a.edit().putString(f5272f, str).commit();
    }

    public void m8030c(boolean z) {
        f5267a.edit().putBoolean(f5274h, z).commit();
    }

    public boolean m8031c() {
        return f5267a.getBoolean(f5269c, true);
    }

    public String m8032d() {
        return f5267a.getString(f5270d, "500");
    }

    public void m8033d(boolean z) {
        f5267a.edit().putBoolean(f5275i, z).commit();
    }

    public String m8034e() {
        return f5267a.getString(f5271e, "30");
    }

    public String m8035f() {
        return f5267a.getString(f5272f, Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
    }

    public boolean m8036g() {
        return f5267a.getBoolean(f5273g, false);
    }

    public boolean m8037h() {
        return f5267a.getBoolean(f5274h, true);
    }

    public boolean m8038i() {
        return f5267a.getBoolean(f5275i, true);
    }

    public int m8039j() {
        return f5267a.getInt(f5276j, 0);
    }
}
