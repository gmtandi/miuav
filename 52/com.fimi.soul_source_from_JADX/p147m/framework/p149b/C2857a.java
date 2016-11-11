package p147m.framework.p149b;

import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.connect.common.Constants;
import java.net.URLEncoder;
import java.security.Key;
import java.security.MessageDigest;
import java.util.HashMap;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.p122a.p123a.C2915a;

/* renamed from: m.framework.b.a */
public class C2857a {
    private static final String f14597a = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static C2860d f14598b;

    static {
        f14598b = new C2860d();
    }

    public static String m16446a(long j) {
        String str = j == 0 ? Constants.VIA_RESULT_SUCCESS : C2915a.f14760f;
        while (j > 0) {
            int i = (int) (j % 62);
            j /= 62;
            str = new StringBuilder(String.valueOf(f14597a.charAt(i))).append(str).toString();
        }
        return str;
    }

    public static String m16447a(String str, byte[] bArr) {
        return new String(C2857a.m16453a(str.getBytes(C1142e.f5201a), bArr), C1142e.f5201a);
    }

    public static String m16448a(HashMap<String, Object> hashMap) {
        return f14598b.m16504a((HashMap) hashMap);
    }

    public static String m16449a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append(String.format("%02x", new Object[]{Byte.valueOf(bArr[i])}));
        }
        return stringBuffer.toString();
    }

    public static byte[] m16450a(String str) {
        byte[] bytes = str.getBytes("utf-8");
        MessageDigest instance = MessageDigest.getInstance("SHA-1");
        instance.update(bytes);
        return instance.digest();
    }

    public static byte[] m16451a(String str, String str2) {
        Object bytes = str.getBytes(C1142e.f5201a);
        Object obj = new byte[16];
        System.arraycopy(bytes, 0, obj, 0, Math.min(bytes.length, 16));
        byte[] bytes2 = str2.getBytes(C1142e.f5201a);
        Key secretKeySpec = new SecretKeySpec(obj, "AES");
        Cipher instance = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
        instance.init(1, secretKeySpec);
        byte[] bArr = new byte[instance.getOutputSize(bytes2.length)];
        instance.doFinal(bArr, instance.update(bytes2, 0, bytes2.length, bArr, 0));
        return bArr;
    }

    public static byte[] m16452a(byte[] bArr, String str) {
        byte[] bytes = str.getBytes(C1142e.f5201a);
        Key secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
        instance.init(1, secretKeySpec);
        byte[] bArr2 = new byte[instance.getOutputSize(bytes.length)];
        instance.doFinal(bArr2, instance.update(bytes, 0, bytes.length, bArr2, 0));
        return bArr2;
    }

    public static byte[] m16453a(byte[] bArr, byte[] bArr2) {
        Object obj = new byte[16];
        System.arraycopy(bArr, 0, obj, 0, Math.min(bArr.length, 16));
        Key secretKeySpec = new SecretKeySpec(obj, "AES");
        Cipher instance = Cipher.getInstance("AES/ECB/NoPadding", "BC");
        instance.init(2, secretKeySpec);
        byte[] bArr3 = new byte[instance.getOutputSize(bArr2.length)];
        int update = instance.update(bArr2, 0, bArr2.length, bArr3, 0);
        int doFinal = instance.doFinal(bArr3, update) + update;
        return bArr3;
    }

    public static String m16454b(String str, String str2) {
        return URLEncoder.encode(str, str2).replace("\\+", "%20");
    }

    public static HashMap<String, Object> m16455b(String str) {
        return f14598b.m16505a(str);
    }

    public static String m16456c(String str) {
        if (str == null) {
            return null;
        }
        byte[] d = C2857a.m16457d(str);
        return d != null ? C2859c.m16486a(d) : null;
    }

    public static byte[] m16457d(String str) {
        byte[] bArr = null;
        if (str != null) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(str.getBytes("utf-8"));
                bArr = instance.digest();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bArr;
    }
}
