package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class bn {

    /* renamed from: com.amap.api.mapcore.util.bn.a */
    class C0355a {
        String f2219a;
        String f2220b;
        String f2221c;
        String f2222d;
        String f2223e;
        String f2224f;
        String f2225g;
        String f2226h;
        String f2227i;
        String f2228j;
        String f2229k;
        String f2230l;
        String f2231m;
        String f2232n;
        String f2233o;
        String f2234p;
        String f2235q;
        String f2236r;
        String f2237s;
        String f2238t;

        private C0355a() {
        }
    }

    public static String m3661a() {
        String str;
        Throwable th;
        Throwable th2;
        String str2 = null;
        try {
            str2 = String.valueOf(System.currentTimeMillis());
            try {
                str = Constants.VIA_TO_TYPE_QQ_GROUP;
                int length = str2.length();
                str = str2.substring(0, length - 2) + str + str2.substring(length - 1);
            } catch (Throwable th3) {
                th = th3;
                str = str2;
                th2 = th;
                cb.m3809a(th2, "CInfo", "getTS");
                return str;
            }
        } catch (Throwable th32) {
            th = th32;
            str = str2;
            th2 = th;
            cb.m3809a(th2, "CInfo", "getTS");
            return str;
        }
        return str;
    }

    public static String m3662a(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"key\":\"").append(bl.m3652f(context)).append("\",\"platform\":\"android\",\"diu\":\"").append(bq.m3707q(context)).append("\",\"pkg\":\"").append(bl.m3649c(context)).append("\",\"model\":\"").append(Build.MODEL).append("\",\"appname\":\"").append(bl.m3648b(context)).append("\",\"appversion\":\"").append(bl.m3650d(context)).append("\",\"sysversion\":\"").append(VERSION.RELEASE).append("\",");
        } catch (Throwable th) {
            cb.m3809a(th, "CInfo", "getPublicJSONInfo");
        }
        return stringBuilder.toString();
    }

    private static String m3663a(Context context, C0355a c0355a) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        Throwable th2;
        String str = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                m3669a(byteArrayOutputStream, c0355a.f2219a);
                m3669a(byteArrayOutputStream, c0355a.f2220b);
                m3669a(byteArrayOutputStream, c0355a.f2221c);
                m3669a(byteArrayOutputStream, c0355a.f2222d);
                m3669a(byteArrayOutputStream, c0355a.f2223e);
                m3669a(byteArrayOutputStream, c0355a.f2224f);
                m3669a(byteArrayOutputStream, c0355a.f2225g);
                m3669a(byteArrayOutputStream, c0355a.f2226h);
                m3669a(byteArrayOutputStream, c0355a.f2227i);
                m3669a(byteArrayOutputStream, c0355a.f2228j);
                m3669a(byteArrayOutputStream, c0355a.f2229k);
                m3669a(byteArrayOutputStream, c0355a.f2230l);
                m3669a(byteArrayOutputStream, c0355a.f2231m);
                m3669a(byteArrayOutputStream, c0355a.f2232n);
                m3669a(byteArrayOutputStream, c0355a.f2233o);
                m3669a(byteArrayOutputStream, c0355a.f2234p);
                m3669a(byteArrayOutputStream, c0355a.f2235q);
                m3669a(byteArrayOutputStream, c0355a.f2236r);
                m3669a(byteArrayOutputStream, c0355a.f2237s);
                m3669a(byteArrayOutputStream, c0355a.f2238t);
                str = m3666a(context, byteArrayOutputStream);
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                }
            } catch (Throwable th4) {
                th3 = th4;
                try {
                    cb.m3809a(th3, "CInfo", "InitXInfo");
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th32) {
                            th32.printStackTrace();
                        }
                    }
                    return str;
                } catch (Throwable th5) {
                    th2 = th5;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th322) {
                            th322.printStackTrace();
                        }
                    }
                    throw th2;
                }
            }
        } catch (Throwable th3222) {
            byteArrayOutputStream = str;
            th2 = th3222;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th2;
        }
        return str;
    }

    public static String m3664a(Context context, bv bvVar) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"sim\":\"").append(bq.m3695e(context)).append("\",\"sdkversion\":\"").append(bvVar.f2282a).append("\",\"product\":\"").append(bvVar.f2284c).append("\",\"ed\":\"").append(bvVar.m3767d()).append("\",\"nt\":\"").append(bq.m3693c(context)).append("\",\"np\":\"").append(bq.m3685a(context)).append("\",\"mnc\":\"").append(bq.m3691b(context)).append("\",\"ant\":\"").append(bq.m3694d(context)).append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String m3665a(Context context, bv bvVar, Map<String, String> map, boolean z) {
        try {
            C0355a c0355a = new C0355a();
            c0355a.f2219a = bq.m3707q(context);
            c0355a.f2220b = bq.m3699i(context);
            String f = bq.m3696f(context);
            if (f == null) {
                f = C2915a.f14760f;
            }
            c0355a.f2221c = f;
            c0355a.f2222d = bl.m3649c(context);
            c0355a.f2223e = Build.MODEL;
            c0355a.f2224f = Build.MANUFACTURER;
            c0355a.f2225g = Build.DEVICE;
            c0355a.f2226h = bl.m3648b(context);
            c0355a.f2227i = bl.m3650d(context);
            c0355a.f2228j = String.valueOf(VERSION.SDK_INT);
            c0355a.f2229k = bq.m3708r(context);
            c0355a.f2230l = bq.m3706p(context);
            c0355a.f2231m = bq.m3703m(context) + C2915a.f14760f;
            c0355a.f2232n = bq.m3702l(context) + C2915a.f14760f;
            c0355a.f2233o = bq.m3709s(context);
            c0355a.f2234p = bq.m3701k(context);
            if (z) {
                c0355a.f2235q = C2915a.f14760f;
            } else {
                c0355a.f2235q = bq.m3698h(context);
            }
            if (z) {
                c0355a.f2236r = C2915a.f14760f;
            } else {
                c0355a.f2236r = bq.m3697g(context);
            }
            if (z) {
                c0355a.f2237s = C2915a.f14760f;
                c0355a.f2238t = C2915a.f14760f;
            } else {
                String[] j = bq.m3700j(context);
                c0355a.f2237s = j[0];
                c0355a.f2238t = j[1];
            }
            return m3663a(context, c0355a);
        } catch (Throwable th) {
            cb.m3809a(th, "CInfo", "InitXInfo");
            return null;
        }
    }

    private static String m3666a(Context context, ByteArrayOutputStream byteArrayOutputStream) {
        return br.m3721b(m3670b(context, bx.m3781b(byteArrayOutputStream.toByteArray())));
    }

    public static String m3667a(Context context, String str, String str2) {
        String str3 = null;
        try {
            str3 = bs.m3728b(bl.m3651e(context) + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            cb.m3809a(th, "CInfo", "Scode");
        }
        return str3;
    }

    public static String m3668a(Context context, byte[] bArr) {
        try {
            return m3673e(context, bArr);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return C2915a.f14760f;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return C2915a.f14760f;
        } catch (NoSuchPaddingException e3) {
            e3.printStackTrace();
            return C2915a.f14760f;
        } catch (IllegalBlockSizeException e4) {
            e4.printStackTrace();
            return C2915a.f14760f;
        } catch (BadPaddingException e5) {
            e5.printStackTrace();
            return C2915a.f14760f;
        } catch (InvalidKeySpecException e6) {
            e6.printStackTrace();
            return C2915a.f14760f;
        } catch (CertificateException e7) {
            e7.printStackTrace();
            return C2915a.f14760f;
        } catch (IOException e8) {
            e8.printStackTrace();
            return C2915a.f14760f;
        } catch (Throwable th) {
            th.printStackTrace();
            return C2915a.f14760f;
        }
    }

    private static void m3669a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        if (TextUtils.isEmpty(str)) {
            bx.m3774a(byteArrayOutputStream, (byte) 0, new byte[0]);
        } else {
            bx.m3774a(byteArrayOutputStream, str.getBytes().length > Util.MASK_8BIT ? (byte) -1 : (byte) str.getBytes().length, bx.m3778a(str));
        }
    }

    public static byte[] m3670b(Context context, byte[] bArr) {
        Key a = bx.m3773a(context);
        if (bArr.length <= Opcodes.LNEG) {
            return br.m3719a(bArr, a);
        }
        byte[] bArr2 = new byte[Opcodes.LNEG];
        System.arraycopy(bArr, 0, bArr2, 0, Opcodes.LNEG);
        Object a2 = br.m3719a(bArr2, a);
        Object obj = new byte[((bArr.length + SmileConstants.TOKEN_PREFIX_TINY_UNICODE) - 117)];
        System.arraycopy(a2, 0, obj, 0, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        System.arraycopy(bArr, Opcodes.LNEG, obj, SmileConstants.TOKEN_PREFIX_TINY_UNICODE, bArr.length - 117);
        return obj;
    }

    public static byte[] m3671c(Context context, byte[] bArr) {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        if (instance == null) {
            return null;
        }
        instance.init(Opcodes.ACC_NATIVE);
        byte[] encoded = instance.generateKey().getEncoded();
        Key a = bx.m3773a(context);
        if (a == null) {
            return null;
        }
        Object a2 = br.m3719a(encoded, a);
        Object a3 = br.m3720a(encoded, bArr);
        Object obj = new byte[(a2.length + a3.length)];
        System.arraycopy(a2, 0, obj, 0, a2.length);
        System.arraycopy(a3, 0, obj, a2.length, a3.length);
        return obj;
    }

    public static String m3672d(Context context, byte[] bArr) {
        try {
            return m3673e(context, bArr);
        } catch (Throwable e) {
            cb.m3809a(e, "CInfo", "AESData");
            return C2915a.f14760f;
        } catch (Throwable e2) {
            cb.m3809a(e2, "CInfo", "AESData");
            return C2915a.f14760f;
        } catch (Throwable e22) {
            cb.m3809a(e22, "CInfo", "AESData");
            return C2915a.f14760f;
        } catch (Throwable e222) {
            cb.m3809a(e222, "CInfo", "AESData");
            return C2915a.f14760f;
        } catch (Throwable e2222) {
            cb.m3809a(e2222, "CInfo", "AESData");
            return C2915a.f14760f;
        } catch (Throwable e22222) {
            cb.m3809a(e22222, "CInfo", "AESData");
            return C2915a.f14760f;
        } catch (Throwable e222222) {
            cb.m3809a(e222222, "CInfo", "AESData");
            return C2915a.f14760f;
        } catch (Throwable e2222222) {
            cb.m3809a(e2222222, "CInfo", "AESData");
            return C2915a.f14760f;
        } catch (Throwable e22222222) {
            cb.m3809a(e22222222, "CInfo", "AESData");
            return C2915a.f14760f;
        }
    }

    static String m3673e(Context context, byte[] bArr) {
        byte[] b = bx.m3781b(m3671c(context, bArr));
        return b != null ? br.m3721b(b) : C2915a.f14760f;
    }
}
