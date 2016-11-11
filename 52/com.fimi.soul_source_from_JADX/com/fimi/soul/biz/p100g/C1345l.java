package com.fimi.soul.biz.p100g;

import com.fimi.kernel.C1189f;
import com.fimi.kernel.p083d.C1160b;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p117h.aa;
import com.fimi.soul.entity.ErrorMode;
import com.fimi.soul.utils.bi;
import java.util.Hashtable;
import java.util.Map;

/* renamed from: com.fimi.soul.biz.g.l */
public class C1345l {
    private static C1345l f5988a;
    private static Map<Integer, C1344k> f5989b;

    static {
        f5989b = new Hashtable();
    }

    public static C1345l m9000a() {
        if (f5988a == null) {
            f5988a = new C1345l();
        }
        return f5988a;
    }

    public static Map<Integer, C1344k> m9001b() {
        return f5989b;
    }

    private void m9002d() {
        if (C1345l.m9001b().size() > 0) {
            for (Integer num : C1345l.m9001b().keySet()) {
                C1344k c1344k = (C1344k) C1345l.m9001b().get(num);
                if (!(c1344k == null || c1344k.m8999c())) {
                    C1160b.m7953b(C1189f.m8334d()).m7959a(C1189f.m8334d().getString(c1344k.m8994a()));
                    ((C1344k) C1345l.m9001b().get(num)).m8997b(true);
                    if (((C1344k) C1345l.m9001b().get(num)).m8998b()) {
                        bi.m12400a(C1189f.m8334d(), 2000);
                    }
                }
            }
        }
    }

    public ErrorMode m9003a(C1433a c1433a) {
        aa ab = c1433a.ab();
        boolean aa = c1433a.aa();
        boolean g = c1433a.m9560G().m10417g();
        boolean h = c1433a.m9560G().m10418h();
        ErrorMode errorMode = new ErrorMode();
        if (!c1433a.m9569P().m9995a()) {
            return errorMode;
        }
        errorMode.setIsMiddleFault(h);
        if (g) {
            if (aa) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_rc_low_battery));
            } else {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_rc_low_battery));
            }
            m9004a(aa, false, C1205R.string.land_rc_low_battery);
        } else {
            m9005a(C1205R.string.land_rc_low_battery);
        }
        if (h) {
            if (aa) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_rc_middle_fault));
            } else {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_rc_middle_fault));
            }
        }
        if (!c1433a.m9570Q()) {
            return errorMode;
        }
        if ((c1433a.ah().m10168c() == (byte) 4 || c1433a.ah().m10165a() == 12) && !aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_RPT_OVERFLOW));
        }
        if ((ab.ac() || c1433a.ah().m10168c() == 5 || c1433a.ah().m10165a() == 8) && !aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_RPT_LOGIC));
        }
        if (c1433a.ah().m10168c() == 6 && !aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_RPT_MOTOR_LOCK));
        }
        if (c1433a.ah().m10168c() == 9 && !aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_RPT_NO_WAY_POINT));
        }
        if ((c1433a.ah().m10168c() == 17 || c1433a.ah().m10165a() == (byte) 4) && !aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_RPT_HOME_NOT_SET));
        }
        if ((ab.ab() || c1433a.ah().m10168c() == 21 || c1433a.ah().m10165a() == 14) && !aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_RPT_NO_CALI));
        }
        if (c1433a.ah().m10168c() == 11 && !aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_RPT_INVALID_CMD));
        }
        errorMode.setIsCompassFault(ab.m10176A());
        if (!ab.m10210f() || c1433a.ah().m10172g().isLightStream()) {
            int[] iArr = new int[1];
            iArr[0] = aa ? C1205R.string.sky_GPS_ERROR : C1205R.string.land_GPS_ERROR;
            m9005a(iArr);
        } else if (aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_GPS_ERROR));
            m9004a(true, false, C1205R.string.sky_GPS_ERROR);
        } else {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_GPS_ERROR));
            m9004a(false, false, C1205R.string.land_GPS_ERROR);
        }
        if (!ab.m10211g() && (c1433a.m9604g() == null || c1433a.m9604g().m10159f() != (byte) 3)) {
            m9005a(C1205R.string.sky_no_fly_error, C1205R.string.land_no_fly_error);
        } else if (aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_no_fly_error));
            m9004a(true, false, C1205R.string.sky_no_fly_error);
        } else {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_no_fly_error));
            m9004a(false, false, C1205R.string.land_no_fly_error);
        }
        if (c1433a.m9604g() != null && c1433a.m9604g().m10159f() == (byte) 1) {
            if (aa) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_no_fly_buffer_error));
            } else {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_no_fly_buffer_error));
            }
        }
        if (ab.m10212h() || ab.m10213i()) {
            if (aa) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_camp_err));
            } else {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_CAMP1_ERROR));
            }
        }
        if (ab.m10214j()) {
            if (aa) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_imuaccel_error));
            } else {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_IMUACCEL_ERROR));
            }
        }
        if (ab.m10215k()) {
            if (aa) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_imugro_error));
            } else {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_IMUGYRO_ERROR));
            }
        }
        if (ab.m10216l()) {
            if (aa) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_BRO_ERROR));
            } else {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_BRO_ERROR));
            }
        }
        if (!ab.m10217m()) {
            m9005a(C1205R.string.sky_BATTERY_ERROR, C1205R.string.land_BATTERY_ERROR);
        } else if (aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_BATTERY_ERROR));
            m9004a(true, false, C1205R.string.sky_BATTERY_ERROR);
        } else {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_BATTERY_ERROR));
            m9004a(false, false, C1205R.string.land_BATTERY_ERROR);
        }
        if (ab.m10218n() && !aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_BATTERY_UNATHORIZED));
        }
        if ((ab.m10219o() || c1433a.ah().m10168c() == 22 || c1433a.ah().m10165a() == 15) && !aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_log_error));
        }
        if (!ab.m10220p() || !c1433a.ah().m10172g().isLightStream()) {
            m9005a(C1205R.string.sky_VPS_ERROR);
        } else if (aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_VPS_ERROR));
            m9004a(true, false, C1205R.string.sky_VPS_ERROR);
        } else {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_VPS_ERROR));
        }
        if (!ab.m10221q() || !c1433a.ah().m10172g().isLightStream()) {
            m9005a(C1205R.string.sky_VPS_LOWACCURATE);
        } else if (aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_VPS_LOWACCURATE));
            m9004a(false, false, C1205R.string.sky_VPS_LOWACCURATE);
        } else {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_VPS_LOWACCURATE));
        }
        if ((ab.m10222r() || c1433a.ah().m10168c() == 12 || c1433a.ah().m10165a() == 6) && !aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_RC_RETURN));
        }
        if (ab.m10223s() && !aa) {
            if (c1433a.al() == 4) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_tf_card_format_ERROR));
            } else {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_NEUTRAL_POINT_ERROR));
            }
        }
        if (ab.m10228x() && !aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_BATTERY_UPDATED_FAIL));
        }
        if (c1433a.ah().m10168c() == 13 || c1433a.ah().m10165a() == (byte) 1) {
            if (aa) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_RPT_NON_AUTO_MODE));
            } else {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_RPT_NON_AUTO_MODE));
            }
        } else if (ab.m10230z() && !c1433a.ah().m10172g().isLightStream()) {
            if (aa) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_GPS_NOSIG));
            } else {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_GPS_NOSIG));
            }
        }
        if (ab.m10176A()) {
            if (aa) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_CAMP_NCAL));
            } else {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_CAMP_NCAL));
            }
        }
        if (ab.m10177B() && !aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_IMUACCEL_NCAL));
        }
        if (ab.m10178C() && !aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_ORTH_NCAL));
        }
        if (ab.m10179D() && !aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_IMUGYRO_NCAL));
        }
        if ((ab.m10180E() || c1433a.ah().m10168c() == (byte) 2 || c1433a.ah().m10165a() == 7) && !aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_G_ATT_INV));
        }
        if (ab.m10183H() || c1433a.ah().m10168c() == (byte) 3 || c1433a.ah().m10165a() == 5) {
            if (aa) {
                if (c1433a.ah().m10170e() == 9) {
                    errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_landing_BAT_LOWPOWER));
                } else {
                    errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_LOWPOWER));
                }
                m9004a(true, false, C1205R.string.sky_LOWPOWER);
            } else {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_LOWPOWER));
                m9004a(false, false, C1205R.string.land_LOWPOWER);
            }
            m9005a(C1205R.string.sky_BAT_light_LOWPOWER, C1205R.string.land_BAT_LOWPOWER, C1205R.string.sky_LP_RETURN);
        } else if (ab.m10181F()) {
            m9005a(C1205R.string.sky_LOWPOWER, C1205R.string.land_LOWPOWER, C1205R.string.sky_LP_RETURN);
            if (aa) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_BAT_light_LOWPOWER));
                m9004a(true, false, C1205R.string.sky_BAT_light_LOWPOWER);
            } else {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_BAT_LOWPOWER));
                m9004a(false, false, C1205R.string.land_BAT_LOWPOWER);
            }
        } else if (ab.m10182G()) {
            m9005a(C1205R.string.sky_LOWPOWER, C1205R.string.land_LOWPOWER, C1205R.string.sky_BAT_light_LOWPOWER, C1205R.string.land_BAT_LOWPOWER);
            if (aa) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_LP_RETURN));
                m9004a(true, false, C1205R.string.sky_LP_RETURN);
            }
        } else {
            m9005a(C1205R.string.sky_BAT_light_LOWPOWER, C1205R.string.sky_LP_RETURN, C1205R.string.land_BAT_LOWPOWER, C1205R.string.sky_landing_BAT_LOWPOWER, C1205R.string.sky_LOWPOWER);
        }
        if (ab.m10184I()) {
            if (aa) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_POWER_HIGHTEMP));
            } else {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_POWER_HIGHTEMP));
            }
            m9004a(false, false, aa ? C1205R.string.sky_POWER_HIGHTEMP : C1205R.string.land_POWER_HIGHTEMP);
        } else {
            iArr = new int[1];
            iArr[0] = aa ? C1205R.string.sky_POWER_HIGHTEMP : C1205R.string.land_POWER_HIGHTEMP;
            m9005a(iArr);
        }
        if (!ab.m10198W()) {
            m9005a(C1205R.string.sky_BATTERY_SHORTLIFE);
        } else if (!aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_BATTERY_SHORTLIFE));
            m9004a(false, false, C1205R.string.sky_BATTERY_SHORTLIFE);
        }
        if (!ab.m10199X()) {
            m9005a(C1205R.string.sky_MAXIMUM_ALTITUDE);
        } else if (aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_MAXIMUM_ALTITUDE));
            m9004a(false, false, C1205R.string.sky_MAXIMUM_ALTITUDE);
        }
        if (!ab.m10200Y()) {
            m9005a(C1205R.string.sky_MAXIMUM_DISTANCE);
        } else if (aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_MAXIMUM_DISTANCE));
            m9004a(false, false, C1205R.string.sky_MAXIMUM_DISTANCE);
        }
        if (ab.m10201Z() && aa) {
        }
        if (ab.aa() && !aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_BAT_PREHEAT));
        }
        if (ab.m10226v()) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_NfzDataInvalid));
            m9004a(false, false, C1205R.string.land_NfzDataInvalid);
        } else {
            m9005a(C1205R.string.land_NfzDataInvalid);
        }
        if (ab.m10185J()) {
            if (aa) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_single_cell_battery));
            } else {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_single_cell_battery));
            }
            m9004a(true, false, aa ? C1205R.string.sky_single_cell_battery : C1205R.string.land_single_cell_battery);
        } else {
            iArr = new int[1];
            iArr[0] = aa ? C1205R.string.sky_single_cell_battery : C1205R.string.land_single_cell_battery;
            m9005a(iArr);
        }
        if (ab.m10186K()) {
            if (aa) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_bat_lowpower_dangerous));
            } else {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_bat_lowpower_dangerous));
            }
            m9004a(true, false, aa ? C1205R.string.sky_bat_lowpower_dangerous : C1205R.string.land_bat_lowpower_dangerous);
        } else {
            iArr = new int[1];
            iArr[0] = aa ? C1205R.string.sky_bat_lowpower_dangerous : C1205R.string.land_bat_lowpower_dangerous;
            m9005a(iArr);
        }
        if (ab.m10227w()) {
            if (aa) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sky_imu_overtemperature));
            } else {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.land_imu_overtemperature));
            }
        }
        if (aa) {
            if (ab.m10187L()) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.stick_move_warning_result));
                m9004a(false, false, C1205R.string.stick_move_warning_des);
            } else {
                m9005a(C1205R.string.stick_move_warning_des);
            }
        }
        if (!c1433a.ah().m10172g().isGps() && ab.m10188M()) {
            if (aa) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sonar_error_sky));
            } else {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.sonar_error));
            }
            if (ab.m10189N()) {
                if (aa) {
                    errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.camera_disconnect_sky));
                } else {
                    errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.camera_disconnect));
                }
            }
            if (ab.m10190O()) {
                if (aa) {
                    errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.imagequality_error_sky));
                } else {
                    errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.imagequality_error));
                }
            }
            if (ab.ad() && !aa) {
                errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.speedconvergen_error));
            }
            if (ab.m10191P()) {
                if (aa) {
                    errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.estimatexy_error_sky));
                } else {
                    errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.estimatexy_error));
                }
            }
        }
        if (!aa && ab.m10229y()) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.str_at_rpt_bat_err));
        }
        if (ab.ae() && aa) {
            errorMode.getLightErrorList().add(Integer.valueOf(C1205R.string.is_min_hight));
        }
        m9002d();
        return errorMode;
    }

    public void m9004a(boolean z, boolean z2, int i) {
        if (!f5989b.containsKey(Integer.valueOf(i))) {
            f5989b.put(Integer.valueOf(i), new C1344k(z, z2, i));
        }
    }

    public void m9005a(int... iArr) {
        for (int i : iArr) {
            if (f5989b.containsKey(Integer.valueOf(i))) {
                f5989b.remove(Integer.valueOf(i));
            }
        }
    }

    public void m9006c() {
        m9005a(C1205R.string.sky_landing_BAT_LOWPOWER, C1205R.string.sky_LP_RETURN);
    }
}
