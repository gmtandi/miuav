package com.p016a;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

/* renamed from: com.a.fp */
public class fp {
    public static String m1850a() {
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
                C0247g.m1917a(th2, "CInfo", "getTS");
                return str;
            }
        } catch (Throwable th32) {
            th = th32;
            str = str2;
            th2 = th;
            C0247g.m1917a(th2, "CInfo", "getTS");
            return str;
        }
        return str;
    }

    public static String m1851a(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"key\":\"").append(fn.m1842f(context)).append("\",\"platform\":\"android\",\"diu\":\"").append(fw.m1892q(context)).append("\",\"pkg\":\"").append(fn.m1838c(context)).append("\",\"model\":\"").append(Build.MODEL).append("\",\"appname\":\"").append(fn.m1836b(context)).append("\",\"appversion\":\"").append(fn.m1840d(context)).append("\",\"sysversion\":\"").append(VERSION.RELEASE).append("\",");
        } catch (Throwable th) {
            C0247g.m1917a(th, "CInfo", "getPublicJSONInfo");
        }
        return stringBuilder.toString();
    }

    public static String m1852a(Context context, gd gdVar) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"sim\":\"").append(fw.m1880e(context)).append("\",\"sdkversion\":\"").append(gdVar.f1262a).append("\",\"product\":\"").append(gdVar.f1264c).append("\",\"ed\":\"").append(gdVar.m1942d()).append("\",\"nt\":\"").append(fw.m1878c(context)).append("\",\"np\":\"").append(fw.m1871a(context)).append("\",\"mnc\":\"").append(fw.m1876b(context)).append("\",\"ant\":\"").append(fw.m1879d(context)).append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String m1853a(Context context, gd gdVar, Map<String, String> map, boolean z) {
        try {
            byte[] bArr;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            fp.m1857a(byteArrayOutputStream, fw.m1892q(context));
            fp.m1857a(byteArrayOutputStream, fw.m1884i(context));
            String f = fw.m1881f(context);
            if (f == null) {
                f = C2915a.f14760f;
            }
            fp.m1857a(byteArrayOutputStream, f);
            fp.m1857a(byteArrayOutputStream, fn.m1838c(context));
            fp.m1857a(byteArrayOutputStream, Build.MODEL);
            fp.m1857a(byteArrayOutputStream, Build.MANUFACTURER);
            fp.m1857a(byteArrayOutputStream, Build.DEVICE);
            fp.m1857a(byteArrayOutputStream, fn.m1836b(context));
            fp.m1857a(byteArrayOutputStream, fn.m1840d(context));
            fp.m1857a(byteArrayOutputStream, String.valueOf(VERSION.SDK_INT));
            fp.m1857a(byteArrayOutputStream, fw.m1893r(context));
            fp.m1857a(byteArrayOutputStream, fw.m1891p(context));
            fp.m1857a(byteArrayOutputStream, fw.m1888m(context) + C2915a.f14760f);
            fp.m1857a(byteArrayOutputStream, fw.m1887l(context) + C2915a.f14760f);
            fp.m1857a(byteArrayOutputStream, fw.m1894s(context));
            fp.m1857a(byteArrayOutputStream, fw.m1886k(context));
            if (z) {
                fp.m1857a(byteArrayOutputStream, C2915a.f14760f);
            } else {
                fp.m1857a(byteArrayOutputStream, fw.m1883h(context));
            }
            if (z) {
                fp.m1857a(byteArrayOutputStream, C2915a.f14760f);
            } else {
                fp.m1857a(byteArrayOutputStream, fw.m1882g(context));
            }
            if (z) {
                fp.m1857a(byteArrayOutputStream, C2915a.f14760f);
                fp.m1857a(byteArrayOutputStream, C2915a.f14760f);
            } else {
                String[] j = fw.m1885j(context);
                fp.m1857a(byteArrayOutputStream, j[0]);
                fp.m1857a(byteArrayOutputStream, j[1]);
            }
            byte[] a = gf.m1962a(byteArrayOutputStream.toByteArray());
            Key a2 = gf.m1959a(context);
            if (a.length > Opcodes.LNEG) {
                byte[] bArr2 = new byte[Opcodes.LNEG];
                System.arraycopy(a, 0, bArr2, 0, Opcodes.LNEG);
                Object a3 = fy.m1902a(bArr2, a2);
                bArr = new byte[((a.length + SmileConstants.TOKEN_PREFIX_TINY_UNICODE) - 117)];
                System.arraycopy(a3, 0, bArr, 0, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                System.arraycopy(a, Opcodes.LNEG, bArr, SmileConstants.TOKEN_PREFIX_TINY_UNICODE, a.length - 117);
            } else {
                bArr = fy.m1902a(a, a2);
            }
            return fy.m1904b(bArr);
        } catch (Throwable th) {
            C0247g.m1917a(th, "CInfo", "InitXInfo");
            return null;
        }
    }

    public static String m1854a(Context context, String str, String str2) {
        String str3 = null;
        try {
            str3 = fz.m1911b(fn.m1841e(context) + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            C0247g.m1917a(th, "CInfo", "Scode");
        }
        return str3;
    }

    public static String m1855a(Context context, byte[] bArr) {
        try {
            return fp.m1858b(context, bArr);
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

    private static void m1856a(ByteArrayOutputStream byteArrayOutputStream, byte b, byte[] bArr) {
        int i = 1;
        try {
            byteArrayOutputStream.write(new byte[]{b});
            int i2 = b > null ? 1 : 0;
            if ((b & Util.MASK_8BIT) >= Util.MASK_8BIT) {
                i = 0;
            }
            if ((i & i2) != 0) {
                byteArrayOutputStream.write(bArr);
            } else if ((b & Util.MASK_8BIT) == Util.MASK_8BIT) {
                byteArrayOutputStream.write(bArr, 0, Util.MASK_8BIT);
            }
        } catch (Throwable e) {
            C0247g.m1917a(e, "CInfo", "writeField");
        }
    }

    private static void m1857a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        if (TextUtils.isEmpty(str)) {
            fp.m1856a(byteArrayOutputStream, (byte) 0, new byte[0]);
            return;
        }
        byte length = str.getBytes().length > Util.MASK_8BIT ? (byte) -1 : (byte) str.getBytes().length;
        try {
            fp.m1856a(byteArrayOutputStream, length, str.getBytes(C1142e.f5201a));
        } catch (UnsupportedEncodingException e) {
            fp.m1856a(byteArrayOutputStream, length, str.getBytes());
        }
    }

    static String m1858b(Context context, byte[] bArr) {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        if (instance == null) {
            return null;
        }
        instance.init(Opcodes.ACC_NATIVE);
        byte[] encoded = instance.generateKey().getEncoded();
        Key a = gf.m1959a(context);
        if (a == null) {
            return null;
        }
        Object a2 = fy.m1902a(encoded, a);
        Object a3 = fy.m1903a(encoded, bArr);
        byte[] bArr2 = new byte[(a2.length + a3.length)];
        System.arraycopy(a2, 0, bArr2, 0, a2.length);
        System.arraycopy(a3, 0, bArr2, a2.length, a3.length);
        byte[] a4 = gf.m1962a(bArr2);
        return a4 != null ? fy.m1904b(a4) : C2915a.f14760f;
    }

    public static String m1859c(Context context, byte[] bArr) {
        try {
            return fp.m1858b(context, bArr);
        } catch (Throwable e) {
            C0247g.m1917a(e, "CInfo", "AESData");
            return C2915a.f14760f;
        } catch (Throwable e2) {
            C0247g.m1917a(e2, "CInfo", "AESData");
            return C2915a.f14760f;
        } catch (Throwable e22) {
            C0247g.m1917a(e22, "CInfo", "AESData");
            return C2915a.f14760f;
        } catch (Throwable e222) {
            C0247g.m1917a(e222, "CInfo", "AESData");
            return C2915a.f14760f;
        } catch (Throwable e2222) {
            C0247g.m1917a(e2222, "CInfo", "AESData");
            return C2915a.f14760f;
        } catch (Throwable e22222) {
            C0247g.m1917a(e22222, "CInfo", "AESData");
            return C2915a.f14760f;
        } catch (Throwable e222222) {
            C0247g.m1917a(e222222, "CInfo", "AESData");
            return C2915a.f14760f;
        } catch (Throwable e2222222) {
            C0247g.m1917a(e2222222, "CInfo", "AESData");
            return C2915a.f14760f;
        } catch (Throwable e22222222) {
            C0247g.m1917a(e22222222, "CInfo", "AESData");
            return C2915a.f14760f;
        }
    }
}
