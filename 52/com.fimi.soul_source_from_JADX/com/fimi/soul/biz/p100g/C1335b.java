package com.fimi.soul.biz.p100g;

import android.content.Context;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p083d.C1160b;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.module.setting.C1886p;
import com.fimi.soul.module.setting.am;
import com.fimi.soul.module.setting.newhand.C1873o;
import com.fimi.soul.utils.bi;
import com.tencent.mm.sdk.platformtools.Util;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.TokenBuffer.Segment;

/* renamed from: com.fimi.soul.biz.g.b */
public class C1335b {
    public static void m8963a(int i) {
        int i2;
        Context a = C1189f.m8327a();
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                i2 = C1205R.string.Not_IN_AIR;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                i2 = C1205R.string.AP_RPT_ATTITUDE;
                break;
            case Type.BYTE /*3*/:
                i2 = C1205R.string.LOW_POWER;
                break;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                i2 = C1205R.string.AP_RPT_MODE_TRANS_REJECT;
                break;
            case Type.FLOAT /*6*/:
                i2 = C1205R.string.AP_RPT_MOTOR_LOCK;
                break;
            case Type.LONG /*7*/:
                i2 = C1205R.string.AP_RPT_HAS_TAKEN_OFF;
                break;
            case Type.DOUBLE /*8*/:
                i2 = C1205R.string.AP_RPT_ATT_OVERFLOW;
                break;
            case Type.ARRAY /*9*/:
                i2 = C1205R.string.AP_RPT_NO_WAY_POINT;
                break;
            case Type.OBJECT /*10*/:
                i2 = C1205R.string.AP_RPT_RTH_ING;
                break;
            case Opcodes.T_LONG /*11*/:
                i2 = C1205R.string.AP_RPT_INVALID_CMD;
                break;
            case Opcodes.FCONST_1 /*12*/:
                i2 = C1205R.string.AP_RPT_RTH_KEY_SET;
                break;
            case Opcodes.FCONST_2 /*13*/:
                i2 = C1205R.string.AP_RPT_NON_AUTO_MODE;
                break;
            case Opcodes.DCONST_0 /*14*/:
                i2 = C1205R.string.AP_RPT_RC_LOST;
                break;
            case Opcodes.DCONST_1 /*15*/:
                i2 = C1205R.string.AP_RPT_Vpu;
                break;
            case Segment.TOKENS_PER_SEGMENT /*16*/:
                i2 = C1205R.string.AP_RPT_DATA_INVALID;
                break;
            case Opcodes.SIPUSH /*17*/:
                i2 = C1205R.string.AP_RPT_Home_Not_Set;
                break;
            case Opcodes.LDC /*18*/:
                i2 = C1205R.string.AP_RPT_Is_LANDING;
                break;
            case am.f9249v /*19*/:
                i2 = C1205R.string.AP_RPT_APP_UNCONNECT;
                break;
            case Util.MAX_ACCOUNT_LENGTH /*20*/:
                i2 = C1205R.string.AP_RPT_Compass_Err;
                break;
            case Opcodes.ILOAD /*21*/:
                i2 = C1205R.string.AP_RPT_ON_Calibration;
                break;
            case Opcodes.LLOAD /*22*/:
                i2 = C1205R.string.AP_RPT_LOG_START_FAILED;
                break;
            case Opcodes.FLOAD /*23*/:
                i2 = C1205R.string.AP_RPT_CYRO_ERROR;
                break;
            case Opcodes.DLOAD /*24*/:
                i2 = C1205R.string.AP_RPT_ACCEL_ERR;
                break;
            case Opcodes.ALOAD /*25*/:
                i2 = C1205R.string.AP_RPT_BARO_ERR;
                break;
            case am.f9219C /*26*/:
                i2 = C1205R.string.AP_RPT_GPS_ERR;
                break;
            case am.f9220D /*27*/:
                i2 = C1205R.string.AP_RPT_NO_FLY_ZONE;
                break;
            case am.f9221E /*28*/:
                i2 = C1205R.string.AP_RPT_NEED_NEW_COOR;
                break;
            case am.f9222F /*29*/:
                i2 = C1205R.string.AP_RPT_ERR_PILOT_MODE;
                break;
            case C1886p.f9607c /*30*/:
                i2 = C1205R.string.AP_RPT_PARA_ERR;
                break;
            case C1543c.ba /*31*/:
                i2 = C1205R.string.AP_RPT_LOGIC_ERROR;
                break;
            case Opcodes.ACC_SYNCHRONIZED /*32*/:
                i2 = C1205R.string.AP_RPT_STICKY_NEUTRAL;
                break;
            case C1543c.aW /*33*/:
                i2 = C1205R.string.AP_RPT_NOT_IN_AP_MODE;
                break;
            case C1873o.f9538Z /*34*/:
                i2 = C1205R.string.AP_RPT_PHASE_TAKE_OFF;
                break;
            case C1543c.aY /*35*/:
                i2 = C1205R.string.AP_RPT_PHASE_LANDING;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER /*36*/:
                i2 = C1205R.string.AP_RPT_GEO_FENCE;
                break;
            default:
                return;
        }
        if (i2 != 0) {
            if (i2 == C1205R.string.AP_RPT_NO_FLY_ZONE) {
                C1160b.m7953b(a).m7959a(a.getString(i2));
            } else if (i2 == C1205R.string.AP_RPT_DATA_INVALID) {
                C1160b.m7953b(a).m7959a(a.getString(i2));
            } else if (i2 == C1205R.string.LOW_POWER) {
                C1160b.m7953b(a).m7959a(a.getString(i2));
                bi.m12400a(C1189f.m8334d(), 2000);
            }
            ak.m8085a(a, a.getString(i2), 3000);
        }
    }
}
