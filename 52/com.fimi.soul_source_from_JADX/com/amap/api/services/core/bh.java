package com.amap.api.services.core;

import com.tencent.connect.common.Constants;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.p122a.p123a.C3004e;

public class bh extends bt {
    private byte[] f3078a;

    public bh(byte[] bArr) {
        this.f3078a = (byte[]) bArr.clone();
    }

    private String m4667f() {
        Object bytes = au.f3029a.getBytes();
        byte[] bArr = new byte[(bytes.length + 50)];
        System.arraycopy(this.f3078a, 0, bArr, 0, 50);
        System.arraycopy(bytes, 0, bArr, 50, bytes.length);
        return ab.m4472a(bArr);
    }

    public String m4668b() {
        return String.format(au.f3030b, new Object[]{Constants.VIA_TO_TYPE_QQ_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP, "open", m4667f()});
    }

    public Map<String, String> c_() {
        return null;
    }

    public Map<String, String> d_() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(C3004e.f15031q, "application/zip");
        hashMap.put(C3004e.f15027m, String.valueOf(this.f3078a.length));
        return hashMap;
    }

    public HttpEntity m4669e() {
        return null;
    }

    public byte[] e_() {
        return this.f3078a;
    }
}
