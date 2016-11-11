package com.p016a;

import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.connect.common.Constants;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.p122a.p123a.C3004e;

/* renamed from: com.a.t */
public class C0260t extends bp {
    private byte[] f1312d;

    public C0260t(byte[] bArr) {
        this.f1312d = (byte[]) bArr.clone();
    }

    private String m2036g() {
        Object bytes;
        try {
            bytes = gh.f1277a.getBytes(C1142e.f5201a);
        } catch (UnsupportedEncodingException e) {
            bytes = gh.f1277a.getBytes();
        }
        byte[] bArr = new byte[(bytes.length + 50)];
        System.arraycopy(this.f1312d, 0, bArr, 0, 50);
        System.arraycopy(bytes, 0, bArr, 50, bytes.length);
        return fz.m1909a(bArr);
    }

    public Map<String, String> m2037a() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(C3004e.f15031q, "application/zip");
        hashMap.put(C3004e.f15027m, String.valueOf(this.f1312d.length));
        return hashMap;
    }

    public Map<String, String> m2038b() {
        return null;
    }

    public String m2039c() {
        return String.format(gh.f1278b, new Object[]{Constants.VIA_TO_TYPE_QQ_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP, "open", m2036g()});
    }

    public byte[] m2040d() {
        return this.f1312d;
    }
}
