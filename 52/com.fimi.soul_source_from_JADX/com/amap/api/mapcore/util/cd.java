package com.amap.api.mapcore.util;

import com.tencent.connect.common.Constants;
import java.util.HashMap;
import java.util.Map;
import org.p122a.p123a.C3004e;

public class cd extends dj {
    private byte[] f2320a;
    private String f2321b;

    public cd(byte[] bArr) {
        this.f2321b = Constants.VIA_TO_TYPE_QQ_GROUP;
        this.f2320a = (byte[]) bArr.clone();
    }

    public cd(byte[] bArr, String str) {
        this.f2321b = Constants.VIA_TO_TYPE_QQ_GROUP;
        this.f2320a = (byte[]) bArr.clone();
        this.f2321b = str;
    }

    private String m3820e() {
        Object a = bx.m3778a(ca.f2304a);
        byte[] bArr = new byte[(a.length + 50)];
        System.arraycopy(this.f2320a, 0, bArr, 0, 50);
        System.arraycopy(a, 0, bArr, 50, a.length);
        return bs.m3726a(bArr);
    }

    public String m3821a() {
        return String.format(ca.f2305b, new Object[]{Constants.VIA_TO_TYPE_QQ_GROUP, this.f2321b, Constants.VIA_TO_TYPE_QQ_GROUP, "open", m3820e()});
    }

    public byte[] a_() {
        return this.f2320a;
    }

    public Map<String, String> m3822b() {
        return null;
    }

    public Map<String, String> m3823c() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(C3004e.f15031q, "application/zip");
        hashMap.put(C3004e.f15027m, String.valueOf(this.f2320a.length));
        return hashMap;
    }
}
