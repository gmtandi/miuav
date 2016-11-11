package com.amap.api.services.core;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.Map.Entry;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C2915a;

/* renamed from: com.amap.api.services.core.y */
public class C0498y {
    public static String m4877a() {
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
                ay.m4590a(th2, "CInfo", "getTS");
                th2.printStackTrace();
                return str;
            }
        } catch (Throwable th32) {
            th = th32;
            str = str2;
            th2 = th;
            ay.m4590a(th2, "CInfo", "getTS");
            th2.printStackTrace();
            return str;
        }
        return str;
    }

    public static String m4878a(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"key\":\"").append(C0496w.m4874f(context)).append("\",\"ct\":\"android\",\"ime\":\"").append(C0500z.m4902m(context)).append("\",\"pkg\":\"").append(C0496w.m4870b(context)).append("\",\"mod\":\"").append(Build.MODEL).append("\",\"apn\":\"").append(C0496w.m4868a(context)).append("\",\"apv\":\"").append(C0496w.m4871c(context)).append("\",\"sv\":\"").append(VERSION.RELEASE).append("\",");
        } catch (Throwable th) {
            ay.m4590a(th, "CInfo", "getPublicJSONInfo");
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String m4879a(Context context, ad adVar) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"sim\":\"").append(C0500z.m4906q(context)).append("\",\"av\":\"").append(adVar.f2973a).append("\",\"pro\":\"").append(adVar.f2975c).append("\",\"ed\":\"").append(adVar.m4497d()).append("\",\"nt\":\"").append(C0500z.m4900k(context)).append("\",\"np\":\"").append(C0500z.m4905p(context)).append("\",\"mnc\":\"").append(C0500z.m4894e(context)).append("\",\"lnt\":\"").append(C0500z.m4895f(context)).append("\",\"ant\":\"").append(C0500z.m4897h(context)).append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String m4880a(Context context, ad adVar, Map<String, String> map) {
        String str = null;
        try {
            String b = C0498y.m4883b(context, adVar, map);
            if (!C2915a.f14760f.equals(b)) {
                str = C0498y.m4884b(context, b.getBytes("utf-8"));
            }
        } catch (Throwable e) {
            ay.m4590a(e, "CInfo", "rsaInfo");
            e.printStackTrace();
        }
        return str;
    }

    public static String m4881a(Context context, String str, String str2) {
        String str3 = null;
        try {
            str3 = ab.m4471a(C0496w.m4872d(context) + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            ay.m4590a(th, "CInfo", "Scode");
            th.printStackTrace();
        }
        return str3;
    }

    public static String m4882a(Context context, byte[] bArr) {
        try {
            return C0498y.m4885c(context, bArr);
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

    private static String m4883b(Context context, ad adVar, Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String m = C0500z.m4902m(context);
            stringBuilder.append("ct=android");
            stringBuilder.append("&ime=").append(m);
            stringBuilder.append("&pkg=").append(C0496w.m4870b(context));
            stringBuilder.append("&mod=");
            stringBuilder.append(Build.MODEL);
            stringBuilder.append("&apn=").append(C0496w.m4868a(context));
            stringBuilder.append("&apv=").append(C0496w.m4871c(context));
            stringBuilder.append("&sv=");
            stringBuilder.append(VERSION.RELEASE);
            stringBuilder.append("&sim=").append(C0500z.m4903n(context));
            stringBuilder.append("&av=").append(adVar.f2973a);
            stringBuilder.append("&pro=").append(adVar.f2975c);
            stringBuilder.append("&cid=").append(C0500z.m4893d(context));
            stringBuilder.append("&dmac=").append(C0500z.m4892c(context));
            stringBuilder.append("&wmac=").append(C0500z.m4891b(context));
            stringBuilder.append("&nt=");
            stringBuilder.append(C0500z.m4901l(context));
            m = C0500z.m4904o(context);
            stringBuilder.append("&np=");
            stringBuilder.append(m);
            stringBuilder.append("&ia=1&");
            m = C0500z.m4886a(context);
            if (m == null) {
                m = C2915a.f14760f;
            }
            stringBuilder.append("utd=").append(m).append("&");
            m = C0496w.m4874f(context);
            if (m != null && m.length() > 0) {
                stringBuilder.append("key=");
                stringBuilder.append(m);
                stringBuilder.append("&");
            }
            stringBuilder.append("ctm=" + System.currentTimeMillis());
            stringBuilder.append("&re=" + C0500z.m4899j(context));
            if (map != null) {
                for (Entry entry : map.entrySet()) {
                    stringBuilder.append("&").append((String) entry.getKey()).append("=").append((String) entry.getValue());
                }
            }
        } catch (Throwable th) {
            ay.m4590a(th, "CInfo", "InitXInfo");
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String m4884b(Context context, byte[] bArr) {
        try {
            return C0498y.m4885c(context, bArr);
        } catch (Throwable e) {
            ay.m4590a(e, "CInfo", "AESData");
            e.printStackTrace();
            return C2915a.f14760f;
        } catch (Throwable e2) {
            ay.m4590a(e2, "CInfo", "AESData");
            e2.printStackTrace();
            return C2915a.f14760f;
        } catch (Throwable e22) {
            ay.m4590a(e22, "CInfo", "AESData");
            e22.printStackTrace();
            return C2915a.f14760f;
        } catch (Throwable e222) {
            ay.m4590a(e222, "CInfo", "AESData");
            e222.printStackTrace();
            return C2915a.f14760f;
        } catch (Throwable e2222) {
            ay.m4590a(e2222, "CInfo", "AESData");
            e2222.printStackTrace();
            return C2915a.f14760f;
        } catch (Throwable e22222) {
            ay.m4590a(e22222, "CInfo", "AESData");
            e22222.printStackTrace();
            return C2915a.f14760f;
        } catch (Throwable e222222) {
            ay.m4590a(e222222, "CInfo", "AESData");
            e222222.printStackTrace();
            return C2915a.f14760f;
        } catch (Throwable e2222222) {
            ay.m4590a(e2222222, "CInfo", "AESData");
            e2222222.printStackTrace();
            return C2915a.f14760f;
        } catch (Throwable e22222222) {
            ay.m4590a(e22222222, "CInfo", "AESData");
            e22222222.printStackTrace();
            return C2915a.f14760f;
        }
    }

    private static String m4885c(Context context, byte[] bArr) {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        if (instance == null) {
            return null;
        }
        instance.init(Opcodes.ACC_NATIVE);
        byte[] encoded = instance.generateKey().getEncoded();
        Key a = ae.m4501a(context);
        if (a == null) {
            return null;
        }
        Object a2 = aa.m4465a(encoded, a);
        Object a3 = aa.m4466a(encoded, bArr);
        byte[] bArr2 = new byte[(a2.length + a3.length)];
        System.arraycopy(a2, 0, bArr2, 0, a2.length);
        System.arraycopy(a3, 0, bArr2, a2.length, a3.length);
        byte[] a4 = ae.m4502a(bArr2);
        return a4 != null ? aa.m4464a(a4) : C2915a.f14760f;
    }
}
