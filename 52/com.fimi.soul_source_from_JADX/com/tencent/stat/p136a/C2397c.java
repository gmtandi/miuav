package com.tencent.stat.p136a;

import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.Arrays;
import java.util.Properties;
import org.p122a.p123a.C2915a;

/* renamed from: com.tencent.stat.a.c */
public class C2397c {
    String f12265a;
    String[] f12266b;
    Properties f12267c;

    public C2397c() {
        this.f12267c = null;
    }

    public C2397c(String str, String[] strArr, Properties properties) {
        this.f12267c = null;
        this.f12265a = str;
        this.f12266b = strArr;
        this.f12267c = properties;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2397c)) {
            return false;
        }
        C2397c c2397c = (C2397c) obj;
        boolean z = this.f12265a.equals(c2397c.f12265a) && Arrays.equals(this.f12266b, c2397c.f12266b);
        return this.f12267c != null ? z && this.f12267c.equals(c2397c.f12267c) : z && c2397c.f12267c == null;
    }

    public int hashCode() {
        int i = 0;
        if (this.f12265a != null) {
            i = this.f12265a.hashCode();
        }
        if (this.f12266b != null) {
            i ^= Arrays.hashCode(this.f12266b);
        }
        return this.f12267c != null ? i ^ this.f12267c.hashCode() : i;
    }

    public String toString() {
        String str = this.f12265a;
        String str2 = C2915a.f14760f;
        if (this.f12266b != null) {
            String str3 = this.f12266b[0];
            for (int i = 1; i < this.f12266b.length; i++) {
                str3 = str3 + MiPushClient.ACCEPT_TIME_SEPARATOR + this.f12266b[i];
            }
            str2 = "[" + str3 + "]";
        }
        if (this.f12267c != null) {
            str2 = str2 + this.f12267c.toString();
        }
        return str + str2;
    }
}
