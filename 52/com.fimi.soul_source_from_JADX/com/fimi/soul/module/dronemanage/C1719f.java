package com.fimi.soul.module.dronemanage;

import android.content.Context;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.module.setting.am;
import com.tencent.mm.sdk.platformtools.Util;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.TokenBuffer.Segment;

/* renamed from: com.fimi.soul.module.dronemanage.f */
public class C1719f {
    private static C1719f f8435F;
    private final int f8436A;
    private final int f8437B;
    private final int f8438C;
    private final int f8439D;
    private Context f8440E;
    private final int f8441a;
    private final int f8442b;
    private final int f8443c;
    private final int f8444d;
    private final int f8445e;
    private final int f8446f;
    private final int f8447g;
    private final int f8448h;
    private final int f8449i;
    private final int f8450j;
    private final int f8451k;
    private final int f8452l;
    private final int f8453m;
    private final int f8454n;
    private final int f8455o;
    private final int f8456p;
    private final int f8457q;
    private final int f8458r;
    private final int f8459s;
    private final int f8460t;
    private final int f8461u;
    private final int f8462v;
    private final int f8463w;
    private final int f8464x;
    private final int f8465y;
    private final int f8466z;

    public C1719f() {
        this.f8441a = 0;
        this.f8442b = 1;
        this.f8443c = 2;
        this.f8444d = 3;
        this.f8445e = 4;
        this.f8446f = 5;
        this.f8447g = 6;
        this.f8448h = 7;
        this.f8449i = 8;
        this.f8450j = 9;
        this.f8451k = 10;
        this.f8452l = 11;
        this.f8453m = 12;
        this.f8454n = 13;
        this.f8455o = 14;
        this.f8456p = 15;
        this.f8457q = 16;
        this.f8458r = 17;
        this.f8459s = 18;
        this.f8460t = 19;
        this.f8461u = 20;
        this.f8462v = 21;
        this.f8463w = 22;
        this.f8464x = 23;
        this.f8465y = 24;
        this.f8466z = 25;
        this.f8436A = 26;
        this.f8437B = 27;
        this.f8438C = 28;
        this.f8439D = 29;
    }

    public static C1719f m11222a() {
        if (f8435F == null) {
            f8435F = new C1719f();
        }
        return f8435F;
    }

    private void m11223a(int i) {
        ak.m8083a(this.f8440E, i, 3000);
    }

    public void m11224a(int i, Context context) {
        this.f8440E = context;
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                m11223a(C1205R.string.Not_IN_AIR);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                m11223a(C1205R.string.AP_RPT_ATTITUDE);
            case Type.BYTE /*3*/:
                m11223a(C1205R.string.LOW_POWER);
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                m11223a(C1205R.string.AP_RPT_LOG_START_FAILED);
            case Type.INT /*5*/:
                m11223a(C1205R.string.AT_RPT_LOGIC);
            case Type.FLOAT /*6*/:
                m11223a(C1205R.string.AP_RPT_MOTOR_LOCK);
            case Type.LONG /*7*/:
                m11223a(C1205R.string.AP_RPT_HAS_TAKEN_OFF);
            case Type.DOUBLE /*8*/:
                m11223a(C1205R.string.AP_RPT_ATT_OVERFLOW);
            case Type.ARRAY /*9*/:
                m11223a(C1205R.string.AP_RPT_NO_WAY_POINT);
            case Type.OBJECT /*10*/:
                m11223a(C1205R.string.AP_RPT_RTH_ING);
            case Opcodes.T_LONG /*11*/:
                m11223a(C1205R.string.AP_RPT_INVALID_CMD);
            case Opcodes.FCONST_1 /*12*/:
                m11223a(C1205R.string.AP_RPT_RTH_KEY_SET);
            case Opcodes.FCONST_2 /*13*/:
                m11223a(C1205R.string.AP_RPT_NON_AUTO_MODE);
            case Opcodes.DCONST_0 /*14*/:
                m11223a(C1205R.string.AP_RPT_RC_LOST);
            case Opcodes.DCONST_1 /*15*/:
                m11223a(C1205R.string.AP_RPT_Vpu);
            case Segment.TOKENS_PER_SEGMENT /*16*/:
                m11223a(C1205R.string.AP_RPT_DATA_INVALID);
            case Opcodes.SIPUSH /*17*/:
                m11223a(C1205R.string.AP_RPT_Home_Not_Set);
            case Opcodes.LDC /*18*/:
                m11223a(C1205R.string.AP_RPT_Is_LANDING);
            case am.f9249v /*19*/:
                m11223a(C1205R.string.AP_RPT_APP_UNCONNECT);
            case Util.MAX_ACCOUNT_LENGTH /*20*/:
                m11223a(C1205R.string.AP_RPT_Compass_Err);
            case Opcodes.ILOAD /*21*/:
                m11223a(C1205R.string.AP_RPT_ON_Calibration);
            case Opcodes.LLOAD /*22*/:
                m11223a(C1205R.string.AP_RPT_LOG_START_FAILED);
            case Opcodes.FLOAD /*23*/:
                m11223a(C1205R.string.AP_RPT_CYRO_ERROR);
            case Opcodes.DLOAD /*24*/:
                m11223a(C1205R.string.AP_RPT_ACCEL_ERR);
            case Opcodes.ALOAD /*25*/:
                m11223a(C1205R.string.AP_RPT_BARO_ERR);
            case am.f9219C /*26*/:
                m11223a(C1205R.string.AP_RPT_GPS_ERR);
            case am.f9220D /*27*/:
                m11223a(C1205R.string.AP_RPT_NO_FLY_ZONE);
            case am.f9221E /*28*/:
                m11223a(C1205R.string.AT_RPT_STICK_NEUTAL);
            default:
        }
    }
}
