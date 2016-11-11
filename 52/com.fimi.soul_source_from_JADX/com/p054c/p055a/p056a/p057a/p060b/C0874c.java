package com.p054c.p055a.p056a.p057a.p060b;

import com.p054c.p055a.p072c.C0958f;
import java.math.BigInteger;
import java.security.MessageDigest;

/* renamed from: com.c.a.a.a.b.c */
public class C0874c implements C0872a {
    private static final String f4713a = "MD5";
    private static final int f4714b = 36;

    private byte[] m7071a(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            MessageDigest instance = MessageDigest.getInstance(f4713a);
            instance.update(bArr);
            bArr2 = instance.digest();
        } catch (Throwable e) {
            C0958f.m7555a(e);
        }
        return bArr2;
    }

    public String m7072a(String str) {
        return new BigInteger(m7071a(str.getBytes())).abs().toString(f4714b);
    }
}
