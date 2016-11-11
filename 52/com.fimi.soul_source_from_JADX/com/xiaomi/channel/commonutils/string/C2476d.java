package com.xiaomi.channel.commonutils.string;

import com.fimi.kernel.p076b.p080d.C1142e;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.channel.commonutils.string.d */
public class C2476d {
    public static String m14165a(int i) {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".length())));
        }
        return stringBuffer.toString();
    }

    public static String m14166a(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA1");
            instance.update(C2476d.m14169b(str));
            BigInteger bigInteger = new BigInteger(1, instance.digest());
            return String.format("%1$032X", new Object[]{bigInteger});
        } catch (NoSuchAlgorithmException e) {
            return str;
        }
    }

    public static String m14167a(Collection<?> collection, String str) {
        return collection == null ? null : C2476d.m14168a(collection.iterator(), str);
    }

    public static String m14168a(Iterator<?> it, String str) {
        if (it == null) {
            return null;
        }
        if (!it.hasNext()) {
            return C2915a.f14760f;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return next.toString();
        }
        StringBuffer stringBuffer = new StringBuffer(Opcodes.ACC_NATIVE);
        if (next != null) {
            stringBuffer.append(next);
        }
        while (it.hasNext()) {
            if (str != null) {
                stringBuffer.append(str);
            }
            next = it.next();
            if (next != null) {
                stringBuffer.append(next);
            }
        }
        return stringBuffer.toString();
    }

    public static byte[] m14169b(String str) {
        try {
            return str.getBytes(C1142e.f5201a);
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }
}
