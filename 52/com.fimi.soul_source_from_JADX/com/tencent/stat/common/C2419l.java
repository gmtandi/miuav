package com.tencent.stat.common;

import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import org.p122a.p123a.C2915a;

/* renamed from: com.tencent.stat.common.l */
class C2419l {
    C2419l() {
    }

    static int m14054a() {
        try {
            return new File("/sys/devices/system/cpu/").listFiles(new C2420m()).length;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    static int m14055b() {
        int i = 0;
        try {
            String str = C2915a.f14760f;
            InputStream inputStream = new ProcessBuilder(new String[]{"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"}).start().getInputStream();
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
            str = str.trim();
            if (str.length() > 0) {
                i = Integer.valueOf(str).intValue();
            }
        } catch (Exception e) {
            C2418k.f12355f.m13977e(e);
        }
        return i * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER;
    }

    static int m14056c() {
        int i = 0;
        try {
            String str = C2915a.f14760f;
            InputStream inputStream = new ProcessBuilder(new String[]{"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"}).start().getInputStream();
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
            str = str.trim();
            if (str.length() > 0) {
                i = Integer.valueOf(str).intValue();
            }
        } catch (Exception e) {
            C2418k.f12355f.m13977e(e);
        }
        return i * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER;
    }

    static String m14057d() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"));
            String[] split = bufferedReader.readLine().split(":\\s+", 2);
            for (int i = 0; i < split.length; i++) {
            }
            bufferedReader.close();
            return split[1];
        } catch (Object th) {
            C2418k.f12355f.m13978e(th);
            return C2915a.f14760f;
        }
    }
}
