package com.fimi.kernel.p084e;

import com.fimi.soul.module.setting.newhand.C1873o;
import com.tencent.open.GameAppOperation;

/* renamed from: com.fimi.kernel.e.p */
public class C1177p {
    public static String m8205a(String str) {
        if (!C1177p.m8206b(str)) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length());
        for (int i = 0; i <= str.length() - 1; i++) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case C1873o.f9538Z /*34*/:
                    stringBuffer.append("&quot;");
                    break;
                case '&':
                    stringBuffer.append("&amp;");
                    break;
                case GameAppOperation.SHARE_PRIZE_SUMMARY_MAX_LENGTH /*60*/:
                    stringBuffer.append("&lt;");
                    break;
                case '>':
                    stringBuffer.append("&gt;");
                    break;
                default:
                    stringBuffer.append(charAt);
                    break;
            }
        }
        return stringBuffer.toString();
    }

    public static boolean m8206b(String str) {
        int i = 0;
        if (str == null || str.length() <= 0) {
            return false;
        }
        boolean z = false;
        while (i <= str.length() - 1) {
            switch (str.charAt(i)) {
                case C1873o.f9538Z /*34*/:
                    z = true;
                    break;
                case '&':
                    z = true;
                    break;
                case GameAppOperation.SHARE_PRIZE_SUMMARY_MAX_LENGTH /*60*/:
                    z = true;
                    break;
                case '>':
                    z = true;
                    break;
                default:
                    break;
            }
            i++;
        }
        return z;
    }
}
