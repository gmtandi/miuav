package com.tencent.open.p134b;

import android.os.SystemClock;
import com.tencent.open.utils.Util;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p152c.p156c.C2951i;

/* renamed from: com.tencent.open.b.d */
public class C2341d {
    protected static C2341d f12030a;

    protected C2341d() {
    }

    public static synchronized C2341d m13784a() {
        C2341d c2341d;
        synchronized (C2341d.class) {
            if (f12030a == null) {
                f12030a = new C2341d();
            }
            c2341d = f12030a;
        }
        return c2341d;
    }

    public void m13785a(int i, String str, String str2, String str3, String str4, Long l, int i2, int i3, String str5) {
        long elapsedRealtime = SystemClock.elapsedRealtime() - l.longValue();
        long j = (l.longValue() == 0 || elapsedRealtime < 0) ? 0 : elapsedRealtime;
        StringBuffer stringBuffer = new StringBuffer("http://c.isdspeed.qq.com/code.cgi");
        stringBuffer.append("?domain=mobile.opensdk.com&cgi=opensdk&type=").append(i).append("&code=").append(i2).append("&time=").append(j).append("&rate=").append(i3).append("&uin=").append(str2).append("&data=");
        C2350g.m13795a().m13800a(stringBuffer.toString(), C2951i.f14860a, Util.composeHaboCgiReportParams(String.valueOf(i), String.valueOf(i2), String.valueOf(j), String.valueOf(i3), str, str2, str3, str4, str5), true);
    }

    public void m13786a(String str, String str2, String str3, String str4, String str5, String str6) {
        C2350g.m13795a().m13797a(Util.composeViaReportParams(str, str3, str4, str5, str2, str6), str2, true);
    }

    public void m13787a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        C2350g.m13795a().m13797a(Util.composeViaReportParams(str, str4, str5, str3, str2, str6, C2915a.f14760f, str7, str8, C2915a.f14760f, C2915a.f14760f, C2915a.f14760f), str2, false);
    }

    public void m13788a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        C2350g.m13795a().m13797a(Util.composeViaReportParams(str, str4, str5, str3, str2, str6, str7, C2915a.f14760f, C2915a.f14760f, str8, str9, str10), str2, false);
    }
}
