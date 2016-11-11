package com.fimi.soul.drone.p117h;

import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.utils.be;

/* renamed from: com.fimi.soul.drone.h.aa */
public class aa extends C1495k {
    private int f7300b;
    private int f7301c;
    private int f7302d;
    private int f7303e;
    private int f7304f;
    private int f7305g;
    private int f7306h;
    private int f7307i;
    private int[] f7308j;
    private int[] f7309k;

    public aa(C1433a c1433a) {
        super(c1433a);
        this.f7302d = 0;
        this.f7308j = new int[]{C1205R.string.self_error_gps, C1205R.string.self_error_nofly, C1205R.string.self_error_compass1, C1205R.string.self_error_compass2, C1205R.string.self_error_accelerometer, C1205R.string.self_error_gyroscope, C1205R.string.self_error_barometer, C1205R.string.self_error_battery, C1205R.string.self_error_battery_copyright, C1205R.string.self_error_unknown, C1205R.string.self_error_Optic_flow, C1205R.string.self_error_Optic_flow_not_found, C1205R.string.self_error_optic_flow_sonar, C1205R.string.self_error_optic_flow_camera, C1205R.string.self_error_gc, C1205R.string.self_error_gc_not_found};
        this.f7309k = new int[]{C1205R.string.fault_gps, C1205R.string.fault_compass, C1205R.string.fault_accelerometer, C1205R.string.fault_level, C1205R.string.fault_gyroscope, C1205R.string.fault_gesture, C1205R.string.fault_back_battery, C1205R.string.fault_low_battery, C1205R.string.fault_power_hightlemp, C1205R.string.fault_ultrasonic_data_invalid, C1205R.string.fault_Optic_flow_invalid, C1205R.string.fault_Optic_flow_bad, C1205R.string.fault_accelerometer_data_invalid, C1205R.string.fault_barometer_data_invalid, C1205R.string.fault_hight_data_invalid, C1205R.string.fault_texture_quality, C1205R.string.fault_gc, C1205R.string.fault_super_heat, C1205R.string.fault_self_fail, C1205R.string.fault_sevo_stal, C1205R.string.fault_gc_heat_error, C1205R.string.fault_ahrs, C1205R.string.fault_short_life_battery, C1205R.string.fault_max_hight, C1205R.string.fault_max_distance, C1205R.string.fault_gc_pre_heart, C1205R.string.fault_bat_pre_heart};
    }

    private int m10175c(int i) {
        for (int i2 = 0; i2 < 32; i2++) {
            if (be.m12349a(i, i2) > 0) {
                return i2;
            }
        }
        return -1;
    }

    public boolean m10176A() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -1)) > 0;
    }

    public boolean m10177B() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -2)) > 0;
    }

    public boolean m10178C() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -3)) > 0;
    }

    public boolean m10179D() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -4)) > 0;
    }

    public boolean m10180E() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -5)) > 0;
    }

    public boolean m10181F() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -6)) > 0;
    }

    public boolean m10182G() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -7)) > 0;
    }

    public boolean m10183H() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -8)) > 0;
    }

    public boolean m10184I() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -9)) > 0;
    }

    public boolean m10185J() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -10)) > 0;
    }

    public boolean m10186K() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -11)) > 0;
    }

    public boolean m10187L() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -12)) > 0;
    }

    public boolean m10188M() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -13)) > 0;
    }

    public boolean m10189N() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -14)) > 0;
    }

    public boolean m10190O() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -15)) > 0;
    }

    public boolean m10191P() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -16)) > 0;
    }

    public boolean m10192Q() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -17)) > 0;
    }

    public boolean m10193R() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -18)) > 0;
    }

    public boolean m10194S() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -19)) > 0;
    }

    public boolean m10195T() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -20)) > 0;
    }

    public boolean m10196U() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -21)) > 0;
    }

    public boolean m10197V() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -22)) > 0;
    }

    public boolean m10198W() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -23)) > 0;
    }

    public boolean m10199X() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -24)) > 0;
    }

    public boolean m10200Y() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -25)) > 0;
    }

    public boolean m10201Z() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -26)) > 0;
    }

    public int m10202a() {
        return this.f7307i;
    }

    public void m10203a(int i) {
        this.f7303e = i;
    }

    public void m10204a(int i, int i2) {
        this.f7300b = i;
        this.f7301c = i2;
        this.f7305g = m10175c(i);
        this.f7306h = m10175c(i2);
        this.f7307i = this.f7305g;
        if (this.f7305g >= 0 || this.f7306h >= 0) {
            this.f7303e = C1205R.string.self_error_result;
            if (this.f7305g >= 0 && this.f7305g < this.f7308j.length) {
                this.f7304f = this.f7308j[this.f7305g];
            }
            if (this.f7305g >= 0) {
                this.f7307i = this.f7305g;
            }
            if (this.f7306h >= 0 && this.f7306h < this.f7309k.length) {
                this.f7304f = this.f7309k[this.f7306h];
            }
            if (this.f7306h >= 0) {
                this.f7307i = this.f7306h;
            }
        }
        this.a.m9589a(C1584h.ERROR_CODE);
    }

    public boolean aa() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -27)) > 0;
    }

    public boolean ab() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -28)) > 0;
    }

    public boolean ac() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -29)) > 0;
    }

    public boolean ad() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -30)) > 0;
    }

    public boolean ae() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + -31)) > 0;
    }

    public int m10205b() {
        return this.f7305g;
    }

    public void m10206b(int i) {
        this.f7304f = i;
    }

    public int m10207c() {
        return this.f7306h;
    }

    public int m10208d() {
        return this.f7303e;
    }

    public int m10209e() {
        return this.f7304f;
    }

    public boolean m10210f() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + 0)) > 0;
    }

    public boolean m10211g() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + -1)) > 0;
    }

    public boolean m10212h() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + -2)) > 0;
    }

    public boolean m10213i() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + -3)) > 0;
    }

    public boolean m10214j() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + -4)) > 0;
    }

    public boolean m10215k() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + -5)) > 0;
    }

    public boolean m10216l() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + -6)) > 0;
    }

    public boolean m10217m() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + -7)) > 0;
    }

    public boolean m10218n() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + -8)) > 0;
    }

    public boolean m10219o() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + -9)) > 0;
    }

    public boolean m10220p() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + -10)) > 0;
    }

    public boolean m10221q() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + -11)) > 0;
    }

    public boolean m10222r() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + -12)) > 0;
    }

    public boolean m10223s() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + -13)) > 0;
    }

    public boolean m10224t() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + -14)) > 0;
    }

    public String toString() {
        return "selftErrorIndex=" + this.f7305g + "faultErrorIndex=" + this.f7306h + " GPS\u6a21\u5757\u635f\u574f =" + m10210f() + " \u5904\u4e8e\u7981\u98de\u533a=" + m10211g() + " \u7f57\u76d81\u635f\u574f=" + m10212h() + " \u7f57\u76d82\u635f\u574f=" + m10213i() + " \u52a0\u901f\u5ea6\u8ba1\u635f\u574f=" + m10214j() + " \u9640\u87ba\u635f\u574f=" + m10215k() + " \u6c14\u538b\u8ba1\u635f\u574f=" + m10216l() + " \u7535\u6c60\u635f\u574f=" + m10217m() + " \u7535\u6c60\u975e\u6cd5=" + m10218n() + " \u672a\u77e5\u6545\u969c=" + m10219o() + " \u5149\u6d41\u635f\u574f=" + m10220p() + " \u672a\u6302\u5149\u6d41=" + m10221q() + " \u672a\u6302\u4e91\u53f0=" + m10225u() + " GPS\u65e0\u4fe1\u53f7=" + m10230z() + " \u7f57\u76d8\u5f85\u6821\u51c6=" + m10176A() + " \u52a0\u901f\u5ea6\u8ba1\u5f85\u6821\u51c6=" + m10177B() + " \u6c34\u5e73\u6821\u51c6=" + m10178C() + " \u9640\u87ba\u5e26\u6821\u51c6=" + m10179D() + " \u5730\u9762\u59ff\u6001\u504f\u79fb=" + m10180E() + " \u4f4e\u4e8e\u8fd4\u822a\u7535\u91cf=" + m10182G() + " \u4f4e\u7535\u544a\u8b66=" + m10183H() + " \u6e29\u5ea6\u8fc7\u9ad8=" + m10184I() + " \u5355\u8282\u7535\u6c60\u7535\u538b\u5f02\u5e38=" + m10185J() + " \u5371\u9669\u4f4e\u7535\u544a\u8b66\uff0c\u53ef\u80fd\u5bfc\u81f4\u7535\u6c60\u635f\u574f\u6216\u5760\u673a=" + m10186K() + " \u7eb9\u7406\u8d28\u91cf=" + m10191P() + " \u4e91\u53f0\u6545\u969c=" + m10192Q() + " \u8fc7\u70ed=" + m10193R() + " \u81ea\u68c0\u5931\u8d25=" + m10194S() + " \u7535\u673a\u5835\u8f6c=" + m10195T() + " \u91cd\u5fc3\u9519\u8bef=" + m10196U() + " AHRS\u4e0d\u53ef\u7528=" + m10197V() + " \u7535\u6c60\u5bff\u547d\u4e0d\u8db3=" + m10198W() + " faultError =" + Integer.toBinaryString(this.f7301c) + " selfError=" + Integer.toBinaryString(this.f7300b);
    }

    public boolean m10225u() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + -15)) > 0;
    }

    public boolean m10226v() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + -16)) > 0;
    }

    public boolean m10227w() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + -17)) > 0;
    }

    public boolean m10228x() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + -18)) > 0;
    }

    public boolean m10229y() {
        return be.m12349a(this.f7300b, Math.abs(this.f7302d + -19)) > 0;
    }

    public boolean m10230z() {
        return be.m12349a(this.f7301c, Math.abs(this.f7302d + 0)) > 0;
    }
}
