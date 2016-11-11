package org.p122a.p123a.p167i.p168a;

import com.tencent.mm.sdk.platformtools.Util;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.impl.auth.NTLMEngine;
import org.apache.http.impl.auth.NTLMEngineException;
import org.apache.http.util.EncodingUtils;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p150a.C2913c;

@C2913c
/* renamed from: org.a.a.i.a.i */
final class C3063i implements NTLMEngine {
    protected static final int f15145a = 1;
    protected static final int f15146b = 4;
    protected static final int f15147c = 16;
    protected static final int f15148d = 32;
    protected static final int f15149e = 128;
    protected static final int f15150f = 512;
    protected static final int f15151g = 4096;
    protected static final int f15152h = 8192;
    protected static final int f15153i = 32768;
    protected static final int f15154j = 524288;
    protected static final int f15155k = 33554432;
    protected static final int f15156l = 8388608;
    protected static final int f15157m = 536870912;
    protected static final int f15158n = 1073741824;
    protected static final int f15159o = Integer.MIN_VALUE;
    static final String f15160p = "ASCII";
    private static final SecureRandom f15161q;
    private static final byte[] f15162s;
    private String f15163r;

    static {
        SecureRandom secureRandom = null;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (Exception e) {
        }
        f15161q = secureRandom;
        Object bytes = EncodingUtils.getBytes("NTLMSSP", f15160p);
        f15162s = new byte[(bytes.length + f15145a)];
        System.arraycopy(bytes, 0, f15162s, 0, bytes.length);
        f15162s[bytes.length] = (byte) 0;
    }

    C3063i() {
        this.f15163r = f15160p;
    }

    static int m17197a(int i, int i2) {
        return (i << i2) | (i >>> (32 - i2));
    }

    static int m17198a(int i, int i2, int i3) {
        return (i & i2) | ((i ^ -1) & i3);
    }

    private static void m17200a(byte[] bArr) {
        for (int i = 0; i < bArr.length; i += f15145a) {
            byte b = bArr[i];
            if (((((b >>> f15145a) ^ ((((((b >>> 7) ^ (b >>> 6)) ^ (b >>> 5)) ^ (b >>> f15146b)) ^ (b >>> 3)) ^ (b >>> 2))) & f15145a) == 0 ? f15145a : null) != null) {
                bArr[i] = (byte) (bArr[i] | f15145a);
            } else {
                bArr[i] = (byte) (bArr[i] & -2);
            }
        }
    }

    static void m17201a(byte[] bArr, int i, int i2) {
        bArr[i2] = (byte) (i & Util.MASK_8BIT);
        bArr[i2 + f15145a] = (byte) ((i >> 8) & Util.MASK_8BIT);
        bArr[i2 + 2] = (byte) ((i >> f15147c) & Util.MASK_8BIT);
        bArr[i2 + 3] = (byte) ((i >> 24) & Util.MASK_8BIT);
    }

    static byte[] m17203a(byte[] bArr, byte[] bArr2) {
        C3065k c3065k = new C3065k(bArr2);
        c3065k.m17263a(bArr);
        return c3065k.m17265a();
    }

    static byte[] m17204a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr2);
            instance.update(bArr3);
            byte[] bArr4 = new byte[8];
            System.arraycopy(instance.digest(), 0, bArr4, 0, 8);
            return C3063i.m17223d(bArr, bArr4);
        } catch (Throwable e) {
            if (e instanceof NTLMEngineException) {
                throw ((NTLMEngineException) e);
            }
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    static int m17205b(int i, int i2, int i3) {
        return ((i & i2) | (i & i3)) | (i2 & i3);
    }

    static byte[] m17210b(byte[] bArr, byte[] bArr2) {
        try {
            Cipher instance = Cipher.getInstance("RC4");
            instance.init(f15145a, new SecretKeySpec(bArr2, "RC4"));
            return instance.doFinal(bArr);
        } catch (Throwable e) {
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    static int m17212c(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private static byte[] m17216c(String str, String str2, byte[] bArr) {
        try {
            C3065k c3065k = new C3065k(bArr);
            c3065k.m17263a(str2.toUpperCase(Locale.ENGLISH).getBytes("UnicodeLittleUnmarked"));
            if (str != null) {
                c3065k.m17263a(str.toUpperCase(Locale.ENGLISH).getBytes("UnicodeLittleUnmarked"));
            }
            return c3065k.m17265a();
        } catch (Throwable e) {
            throw new NTLMEngineException("Unicode not supported! " + e.getMessage(), e);
        }
    }

    private static byte[] m17221d(String str, String str2, byte[] bArr) {
        try {
            C3065k c3065k = new C3065k(bArr);
            c3065k.m17263a(str2.toUpperCase(Locale.ENGLISH).getBytes("UnicodeLittleUnmarked"));
            if (str != null) {
                c3065k.m17263a(str.getBytes("UnicodeLittleUnmarked"));
            }
            return c3065k.m17265a();
        } catch (Throwable e) {
            throw new NTLMEngineException("Unicode not supported! " + e.getMessage(), e);
        }
    }

    private static byte[] m17223d(byte[] bArr, byte[] bArr2) {
        try {
            Object obj = new byte[21];
            System.arraycopy(bArr, 0, obj, 0, f15147c);
            Key h = C3063i.m17235h(obj, 0);
            Key h2 = C3063i.m17235h(obj, 7);
            Key h3 = C3063i.m17235h(obj, 14);
            Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
            instance.init(f15145a, h);
            Object doFinal = instance.doFinal(bArr2);
            instance.init(f15145a, h2);
            Object doFinal2 = instance.doFinal(bArr2);
            instance.init(f15145a, h3);
            obj = instance.doFinal(bArr2);
            Object obj2 = new byte[24];
            System.arraycopy(doFinal, 0, obj2, 0, 8);
            System.arraycopy(doFinal2, 0, obj2, 8, 8);
            System.arraycopy(obj, 0, obj2, f15147c, 8);
            return obj2;
        } catch (Throwable e) {
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    private static byte[] m17224d(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        C3065k c3065k = new C3065k(bArr);
        c3065k.m17263a(bArr2);
        c3065k.m17263a(bArr3);
        Object a = c3065k.m17265a();
        Object obj = new byte[(a.length + bArr3.length)];
        System.arraycopy(a, 0, obj, 0, a.length);
        System.arraycopy(bArr3, 0, obj, a.length, bArr3.length);
        return obj;
    }

    private static int m17225e(byte[] bArr, int i) {
        if (bArr.length >= i + f15146b) {
            return (((bArr[i] & Util.MASK_8BIT) | ((bArr[i + f15145a] & Util.MASK_8BIT) << 8)) | ((bArr[i + 2] & Util.MASK_8BIT) << f15147c)) | ((bArr[i + 3] & Util.MASK_8BIT) << 24);
        }
        throw new NTLMEngineException("NTLM authentication - buffer too small for DWORD");
    }

    private static byte[] m17227e() {
        if (f15161q == null) {
            throw new NTLMEngineException("Random generator not available");
        }
        byte[] bArr = new byte[8];
        synchronized (f15161q) {
            f15161q.nextBytes(bArr);
        }
        return bArr;
    }

    private static byte[] m17228e(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        Object obj = new byte[]{(byte) 1, (byte) 1, (byte) 0, (byte) 0};
        Object obj2 = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        Object obj3 = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        Object obj4 = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        Object obj5 = new byte[((((((obj.length + obj2.length) + bArr3.length) + 8) + obj3.length) + bArr2.length) + obj4.length)];
        System.arraycopy(obj, 0, obj5, 0, obj.length);
        int length = obj.length + 0;
        System.arraycopy(obj2, 0, obj5, length, obj2.length);
        length += obj2.length;
        System.arraycopy(bArr3, 0, obj5, length, bArr3.length);
        length += bArr3.length;
        System.arraycopy(bArr, 0, obj5, length, 8);
        length += 8;
        System.arraycopy(obj3, 0, obj5, length, obj3.length);
        length += obj3.length;
        System.arraycopy(bArr2, 0, obj5, length, bArr2.length);
        length += bArr2.length;
        System.arraycopy(obj4, 0, obj5, length, obj4.length);
        length += obj4.length;
        return obj5;
    }

    private static int m17229f(byte[] bArr, int i) {
        if (bArr.length >= i + 2) {
            return (bArr[i] & Util.MASK_8BIT) | ((bArr[i + f15145a] & Util.MASK_8BIT) << 8);
        }
        throw new NTLMEngineException("NTLM authentication - buffer too small for WORD");
    }

    private static String m17230f(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(".");
        return indexOf != -1 ? str.substring(0, indexOf) : str;
    }

    private static byte[] m17231f() {
        if (f15161q == null) {
            throw new NTLMEngineException("Random generator not available");
        }
        byte[] bArr = new byte[f15147c];
        synchronized (f15161q) {
            f15161q.nextBytes(bArr);
        }
        return bArr;
    }

    private static String m17232g(String str) {
        return C3063i.m17230f(str);
    }

    private static byte[] m17233g(byte[] bArr, int i) {
        int f = C3063i.m17229f(bArr, i);
        int e = C3063i.m17225e(bArr, i + f15146b);
        if (bArr.length < e + f) {
            throw new NTLMEngineException("NTLM authentication - buffer too small for data item");
        }
        Object obj = new byte[f];
        System.arraycopy(bArr, e, obj, 0, f);
        return obj;
    }

    private static String m17234h(String str) {
        return C3063i.m17230f(str);
    }

    private static Key m17235h(byte[] bArr, int i) {
        r0 = new byte[7];
        System.arraycopy(bArr, i, r0, 0, 7);
        byte[] bArr2 = new byte[]{r0[0], (byte) ((r0[0] << 7) | ((r0[f15145a] & Util.MASK_8BIT) >>> f15145a)), (byte) ((r0[f15145a] << 6) | ((r0[2] & Util.MASK_8BIT) >>> 2)), (byte) ((r0[2] << 5) | ((r0[3] & Util.MASK_8BIT) >>> 3)), (byte) ((r0[3] << f15146b) | ((r0[f15146b] & Util.MASK_8BIT) >>> f15146b)), (byte) ((r0[f15146b] << 3) | ((r0[5] & Util.MASK_8BIT) >>> 5)), (byte) ((r0[5] << 2) | ((r0[6] & Util.MASK_8BIT) >>> 6)), (byte) (r0[6] << f15145a)};
        C3063i.m17200a(bArr2);
        return new SecretKeySpec(bArr2, "DES");
    }

    private static byte[] m17236i(String str) {
        try {
            Object bytes = str.toUpperCase(Locale.ENGLISH).getBytes("US-ASCII");
            Object obj = new byte[14];
            System.arraycopy(bytes, 0, obj, 0, Math.min(bytes.length, 14));
            Key h = C3063i.m17235h(obj, 0);
            Key h2 = C3063i.m17235h(obj, 7);
            byte[] bytes2 = "KGS!@#$%".getBytes("US-ASCII");
            Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
            instance.init(f15145a, h);
            bytes = instance.doFinal(bytes2);
            instance.init(f15145a, h2);
            Object doFinal = instance.doFinal(bytes2);
            obj = new byte[f15147c];
            System.arraycopy(bytes, 0, obj, 0, 8);
            System.arraycopy(doFinal, 0, obj, 8, 8);
            return obj;
        } catch (Throwable e) {
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    private static byte[] m17237j(String str) {
        try {
            byte[] bytes = str.getBytes("UnicodeLittleUnmarked");
            C3066l c3066l = new C3066l();
            c3066l.m17266a(bytes);
            return c3066l.m17268a();
        } catch (Throwable e) {
            throw new NTLMEngineException("Unicode not supported: " + e.getMessage(), e);
        }
    }

    String m17238a() {
        return this.f15163r;
    }

    String m17239a(String str, String str2) {
        return new C3068n(str2, str).m17285c();
    }

    final String m17240a(String str, String str2, String str3, String str4, String str5) {
        if (str == null || str.trim().equals(C2915a.f14760f)) {
            return m17239a(str4, str5);
        }
        C3069o c3069o = new C3069o(str);
        return m17241a(str2, str3, str4, str5, c3069o.m17286d(), c3069o.m17289g(), c3069o.m17287e(), c3069o.m17288f());
    }

    String m17241a(String str, String str2, String str3, String str4, byte[] bArr, int i, String str5, byte[] bArr2) {
        return new C3070p(str4, str3, str, str2, bArr, i, str5, bArr2).m17290c();
    }

    void m17242a(String str) {
        this.f15163r = str;
    }

    public String generateType1Msg(String str, String str2) {
        return m17239a(str2, str);
    }

    public String generateType3Msg(String str, String str2, String str3, String str4, String str5) {
        C3069o c3069o = new C3069o(str5);
        return m17241a(str, str2, str4, str3, c3069o.m17286d(), c3069o.m17289g(), c3069o.m17287e(), c3069o.m17288f());
    }
}
