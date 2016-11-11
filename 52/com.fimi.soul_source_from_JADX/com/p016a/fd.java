package com.p016a;

import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.List;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.fd */
public final class fd {
    private boolean f1186a;
    private String f1187b;
    private String f1188c;
    private boolean f1189d;
    private double f1190e;
    private double f1191f;

    protected fd(List list, String str, String str2, String str3) {
        this.f1186a = false;
        this.f1187b = C2915a.f14760f;
        this.f1188c = C2915a.f14760f;
        this.f1189d = false;
        this.f1190e = 0.0d;
        this.f1191f = 0.0d;
        this.f1187b = str;
        this.f1188c = str3;
        m1795d();
    }

    private void m1795d() {
        int i;
        int i2;
        boolean z;
        String[] split;
        int i3 = 0;
        String str = this.f1188c;
        if (str != null && str.length() > 8) {
            i = 0;
            for (i2 = 1; i2 < str.length() - 3; i2++) {
                i ^= str.charAt(i2);
            }
            if (Integer.toHexString(i).equalsIgnoreCase(str.substring(str.length() - 2, str.length()))) {
                z = true;
                if (z) {
                    str = this.f1188c.substring(0, this.f1188c.length() - 3);
                    i = 0;
                    for (i2 = 0; i2 < str.length(); i2++) {
                        if (str.charAt(i2) == ',') {
                            i++;
                        }
                    }
                    split = str.split(MiPushClient.ACCEPT_TIME_SEPARATOR, i + 1);
                    if (split.length < 6) {
                        if (!(split[2].equals(C2915a.f14760f) || split[split.length - 3].equals(C2915a.f14760f) || split[split.length - 2].equals(C2915a.f14760f) || split[split.length - 1].equals(C2915a.f14760f))) {
                            Integer.valueOf(split[2]).intValue();
                            this.f1190e = Double.valueOf(split[split.length - 3]).doubleValue();
                            this.f1191f = Double.valueOf(split[split.length - 2]).doubleValue();
                            this.f1189d = true;
                        }
                    } else {
                        return;
                    }
                }
                if (this.f1187b != null && this.f1187b.length() >= 0 && this.f1187b.contains("GPGGA")) {
                    split = this.f1187b.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                    if (split.length == 15 && split[2] != null && split[2].length() > 0 && split[4] != null && split[4].length() > 0 && Integer.parseInt(split[7]) >= 5 && Double.parseDouble(split[8]) <= 2.7d) {
                        i3 = 1;
                    }
                }
                this.f1186a = this.f1189d & i3;
            }
        }
        z = false;
        if (z) {
            str = this.f1188c.substring(0, this.f1188c.length() - 3);
            i = 0;
            for (i2 = 0; i2 < str.length(); i2++) {
                if (str.charAt(i2) == ',') {
                    i++;
                }
            }
            split = str.split(MiPushClient.ACCEPT_TIME_SEPARATOR, i + 1);
            if (split.length < 6) {
                Integer.valueOf(split[2]).intValue();
                this.f1190e = Double.valueOf(split[split.length - 3]).doubleValue();
                this.f1191f = Double.valueOf(split[split.length - 2]).doubleValue();
                this.f1189d = true;
            } else {
                return;
            }
        }
        try {
            split = this.f1187b.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
            i3 = 1;
        } catch (Exception e) {
        }
        this.f1186a = this.f1189d & i3;
    }

    protected final boolean m1796a() {
        return this.f1186a;
    }

    protected final double m1797b() {
        return this.f1190e;
    }

    protected final double m1798c() {
        return this.f1191f;
    }
}
