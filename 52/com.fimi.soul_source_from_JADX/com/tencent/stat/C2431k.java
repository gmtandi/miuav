package com.tencent.stat;

import android.content.Context;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.stat.common.C2418k;
import com.tencent.stat.common.C2423p;
import com.tencent.stat.p136a.C2394e;
import com.tencent.stat.p136a.C2399f;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.tencent.stat.k */
class C2431k implements Runnable {
    private C2394e f12378a;
    private StatReportStrategy f12379b;
    private C2407c f12380c;

    public C2431k(C2394e c2394e) {
        this.f12379b = null;
        this.f12380c = new C2432l(this);
        this.f12378a = c2394e;
        this.f12379b = StatConfig.getStatSendStrategy();
    }

    private void m14076a() {
        if (C2434n.m14089b().m14100a() > 0) {
            C2434n.m14089b().m14102a(this.f12378a, null);
            C2434n.m14089b().m14101a(-1);
            return;
        }
        m14077a(true);
    }

    private void m14077a(boolean z) {
        C2424d.m14068b().m14070a(this.f12378a, this.f12380c);
    }

    public void run() {
        try {
            if (!StatConfig.isEnableStatService()) {
                return;
            }
            if (this.f12378a.m13934a() == C2399f.ERROR || this.f12378a.m13939d().length() <= StatConfig.getMaxReportEventLength()) {
                if (StatConfig.getMaxSessionStatReportCount() > 0) {
                    if (StatConfig.getCurSessionStatReportCount() >= StatConfig.getMaxSessionStatReportCount()) {
                        StatService.f12248i.m13978e((Object) "Times for reporting events has reached the limit of StatConfig.getMaxSessionStatReportCount() in current session.");
                        return;
                    }
                    StatConfig.m13910c();
                }
                StatService.f12248i.m13979i("Lauch stat task in thread:" + Thread.currentThread().getName());
                Context c = this.f12378a.m13938c();
                if (C2418k.m14034h(c)) {
                    if (StatConfig.isEnableSmartReporting() && this.f12379b != StatReportStrategy.ONLY_WIFI_NO_CACHE && C2418k.m14032g(c)) {
                        this.f12379b = StatReportStrategy.INSTANT;
                    }
                    switch (C2428h.f12374a[this.f12379b.ordinal()]) {
                        case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                            m14076a();
                            return;
                        case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                            if (C2418k.m14028e(c)) {
                                m14076a();
                                return;
                            } else {
                                C2434n.m14082a(c).m14102a(this.f12378a, null);
                                return;
                            }
                        case Type.BYTE /*3*/:
                        case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                            C2434n.m14082a(c).m14102a(this.f12378a, null);
                            return;
                        case Type.INT /*5*/:
                            if (C2434n.m14082a(this.f12378a.m13938c()) != null) {
                                C2434n.m14082a(c).m14102a(this.f12378a, new C2433m(this));
                                return;
                            }
                            return;
                        case Type.FLOAT /*6*/:
                            C2434n.m14082a(c).m14102a(this.f12378a, null);
                            String str = "last_period_ts";
                            Long valueOf = Long.valueOf(C2423p.m14060a(c, str, 0));
                            Long valueOf2 = Long.valueOf(System.currentTimeMillis());
                            if (Long.valueOf(Long.valueOf(valueOf2.longValue() - valueOf.longValue()).longValue() / Util.MILLSECONDS_OF_MINUTE).longValue() > ((long) StatConfig.getSendPeriodMinutes())) {
                                C2434n.m14082a(c).m14101a(-1);
                                C2423p.m14064b(c, str, valueOf2.longValue());
                                return;
                            }
                            return;
                        case Type.LONG /*7*/:
                            if (C2418k.m14028e(c)) {
                                m14077a(false);
                                return;
                            }
                            return;
                        default:
                            StatService.f12248i.error("Invalid stat strategy:" + StatConfig.getStatSendStrategy());
                            return;
                    }
                    StatService.f12248i.m13978e(th);
                }
                C2434n.m14082a(c).m14102a(this.f12378a, null);
                return;
            }
            StatService.f12248i.m13978e("Event length exceed StatConfig.getMaxReportEventLength(): " + StatConfig.getMaxReportEventLength());
        } catch (Exception e) {
            StatService.f12248i.m13977e(e);
        } catch (Object th) {
            StatService.f12248i.m13978e(th);
        }
    }
}
