package com.p016a;

import org.p122a.p123a.p152c.p153a.C2924b;

/* renamed from: com.a.ge */
public class ge {
    private String f1268a;
    private String f1269b;
    private String f1270c;
    private boolean f1271d;
    private String f1272e;
    private String[] f1273f;

    public ge(String str, String str2, String str3) {
        this.f1271d = true;
        this.f1272e = C2924b.f14791c;
        this.f1273f = null;
        this.f1268a = str2;
        this.f1270c = str3;
        this.f1269b = str;
    }

    public gd m1951a() {
        if (this.f1273f != null) {
            return new gd();
        }
        throw new fm("sdk packages is null");
    }

    public ge m1952a(String str) {
        this.f1272e = str;
        return this;
    }

    public ge m1953a(boolean z) {
        this.f1271d = z;
        return this;
    }

    public ge m1954a(String[] strArr) {
        this.f1273f = (String[]) strArr.clone();
        return this;
    }
}
