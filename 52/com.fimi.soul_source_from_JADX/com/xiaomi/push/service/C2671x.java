package com.xiaomi.push.service;

import com.fimi.soul.module.setting.am;
import com.tencent.mm.sdk.platformtools.Util;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.TokenBuffer.Segment;

/* renamed from: com.xiaomi.push.service.x */
public abstract class C2671x {
    public static String f13202a;
    public static String f13203b;
    public static String f13204c;
    public static String f13205d;
    public static String f13206e;
    public static String f13207f;
    public static String f13208g;
    public static String f13209h;
    public static String f13210i;
    public static String f13211j;
    public static String f13212k;
    public static String f13213l;
    public static String f13214m;
    public static String f13215n;
    public static String f13216o;
    public static String f13217p;
    public static String f13218q;
    public static String f13219r;
    public static String f13220s;
    public static String f13221t;
    public static String f13222u;
    public static String f13223v;
    public static String f13224w;
    public static String f13225x;
    public static String f13226y;
    public static String f13227z;

    static {
        f13202a = "com.xiaomi.push.OPEN_CHANNEL";
        f13203b = "com.xiaomi.push.SEND_MESSAGE";
        f13204c = "com.xiaomi.push.SEND_IQ";
        f13205d = "com.xiaomi.push.BATCH_SEND_MESSAGE";
        f13206e = "com.xiaomi.push.SEND_PRES";
        f13207f = "com.xiaomi.push.CLOSE_CHANNEL";
        f13208g = "com.xiaomi.push.FORCE_RECONN";
        f13209h = "com.xiaomi.push.RESET_CONN";
        f13210i = "com.xiaomi.push.UPDATE_CHANNEL_INFO";
        f13211j = "com.xiaomi.push.SEND_STATS";
        f13212k = "com.xiaomi.push.CHANGE_HOST";
        f13213l = "com.xiaomi.push.PING_TIMER";
        f13214m = "ext_user_id";
        f13215n = "ext_chid";
        f13216o = "ext_sid";
        f13217p = "ext_token";
        f13218q = "ext_auth_method";
        f13219r = "ext_security";
        f13220s = "ext_kick";
        f13221t = "ext_client_attr";
        f13222u = "ext_cloud_attr";
        f13223v = "ext_pkg_name";
        f13224w = "ext_notify_id";
        f13225x = "ext_notify_type";
        f13226y = "ext_session";
        f13227z = "sig";
    }

    public static String m15122a(int i) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return "ERROR_OK";
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return "ERROR_SERVICE_NOT_INSTALLED";
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return "ERROR_NETWORK_NOT_AVAILABLE";
            case Type.BYTE /*3*/:
                return "ERROR_NETWORK_FAILED";
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return "ERROR_ACCESS_DENIED";
            case Type.INT /*5*/:
                return "ERROR_AUTH_FAILED";
            case Type.FLOAT /*6*/:
                return "ERROR_MULTI_LOGIN";
            case Type.LONG /*7*/:
                return "ERROR_SERVER_ERROR";
            case Type.DOUBLE /*8*/:
                return "ERROR_RECEIVE_TIMEOUT";
            case Type.ARRAY /*9*/:
                return "ERROR_READ_ERROR";
            case Type.OBJECT /*10*/:
                return "ERROR_SEND_ERROR";
            case Opcodes.T_LONG /*11*/:
                return "ERROR_RESET";
            case Opcodes.FCONST_1 /*12*/:
                return "ERROR_NO_CLIENT";
            case Opcodes.FCONST_2 /*13*/:
                return "ERROR_SERVER_STREAM";
            case Opcodes.DCONST_0 /*14*/:
                return "ERROR_THREAD_BLOCK";
            case Opcodes.DCONST_1 /*15*/:
                return "ERROR_SERVICE_DESTROY";
            case Segment.TOKENS_PER_SEGMENT /*16*/:
                return "ERROR_SESSION_CHANGED";
            case Opcodes.SIPUSH /*17*/:
                return "ERROR_READ_TIMEOUT";
            case Opcodes.LDC /*18*/:
                return "ERROR_CONNECTIING_TIMEOUT";
            case am.f9249v /*19*/:
                return "ERROR_USER_BLOCKED";
            case Util.MAX_ACCOUNT_LENGTH /*20*/:
                return "ERROR_REDIRECT";
            case Opcodes.ILOAD /*21*/:
                return "ERROR_BIND_TIMEOUT";
            case Opcodes.LLOAD /*22*/:
                return "ERROR_PING_TIMEOUT";
            default:
                return String.valueOf(i);
        }
    }
}
