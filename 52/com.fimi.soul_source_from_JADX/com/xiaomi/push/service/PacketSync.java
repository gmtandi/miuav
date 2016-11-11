package com.xiaomi.push.service;

import android.os.Parcelable;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.service.C2669v.C2667b;
import com.xiaomi.push.service.C2669v.C2668c;
import com.xiaomi.smack.C2674k;
import com.xiaomi.smack.C2678j;
import com.xiaomi.smack.C2710s.C2709b;
import com.xiaomi.smack.C2710s.C2709b.C2708a;
import com.xiaomi.smack.C2711t;
import com.xiaomi.smack.packet.C2692a;
import com.xiaomi.smack.packet.C2694d;
import com.xiaomi.smack.packet.C2695b;
import com.xiaomi.smack.packet.C2696c;
import com.xiaomi.smack.packet.C2702h;
import com.xiaomi.stats.C2726a;
import com.xiaomi.stats.C2728b;

public class PacketSync {
    private XMPushService f13060a;

    public interface PacketReceiveHandler extends Parcelable {
    }

    PacketSync(XMPushService xMPushService) {
        this.f13060a = xMPushService;
    }

    private void m14892a(C2692a c2692a) {
        Object c = c2692a.m15221c();
        if (!TextUtils.isEmpty(c)) {
            String[] split = c.split(";");
            Fallback fallbacksByHost = HostManager.getInstance().getFallbacksByHost(C2674k.m15134d());
            if (fallbacksByHost != null && split.length > 0) {
                fallbacksByHost.m14840a(split);
                this.f13060a.m14956b(20, null);
                this.f13060a.m14952a(true);
            }
        }
    }

    public void m14893a(C2694d c2694d) {
        C2667b b;
        if (c2694d instanceof C2709b) {
            C2709b c2709b = (C2709b) c2694d;
            C2708a b2 = c2709b.m15310b();
            String l = c2709b.m15234l();
            String m = c2709b.m15236m();
            if (!TextUtils.isEmpty(l)) {
                b = C2669v.m15106a().m15114b(l, m);
                if (b == null) {
                    return;
                }
                if (b2 == C2708a.f13397a) {
                    b.m15105a(C2668c.binded, 1, 0, null, null);
                    C2463b.m14123a("SMACK: channel bind succeeded, chid=" + l);
                    C2726a.m15380a().m15386a(c2709b.m15311c());
                    C2726a.m15380a().m15383a(c2709b.m15312d());
                    return;
                }
                C2702h p = c2709b.m15243p();
                C2463b.m14123a("SMACK: channel bind failed, error=" + p.m15284d());
                if (p != null) {
                    if ("auth".equals(p.m15282b())) {
                        b.m15105a(C2668c.unbind, 1, 5, p.m15281a(), p.m15282b());
                        C2669v.m15106a().m15113a(l, m);
                    } else if ("cancel".equals(p.m15282b())) {
                        b.m15105a(C2668c.unbind, 1, 7, p.m15281a(), p.m15282b());
                        C2669v.m15106a().m15113a(l, m);
                    } else if ("wait".equals(p.m15282b())) {
                        this.f13060a.m14958b(b);
                        b.m15105a(C2668c.unbind, 1, 7, p.m15281a(), p.m15282b());
                    }
                    C2463b.m14123a("SMACK: channel bind failed, chid=" + l + " reason=" + p.m15281a());
                    return;
                }
                return;
            }
            return;
        }
        String l2 = c2694d.m15234l();
        if (TextUtils.isEmpty(l2)) {
            l2 = Constants.VIA_TO_TYPE_QQ_GROUP;
            c2694d.m15235l(l2);
        }
        if (!l2.equals(Constants.VIA_RESULT_SUCCESS)) {
            C2692a p2;
            if (c2694d instanceof C2695b) {
                p2 = c2694d.m15242p("kick");
                if (p2 != null) {
                    String m2 = c2694d.m15236m();
                    String a = p2.m15218a(SocialConstants.PARAM_TYPE);
                    String a2 = p2.m15218a("reason");
                    C2463b.m14123a("kicked by server, chid=" + l2 + " userid=" + m2 + " type=" + a + " reason=" + a2);
                    if ("wait".equals(a)) {
                        b = C2669v.m15106a().m15114b(l2, m2);
                        if (b != null) {
                            this.f13060a.m14958b(b);
                            b.m15105a(C2668c.unbind, 3, 0, a2, a);
                            return;
                        }
                        return;
                    }
                    this.f13060a.m14950a(l2, m2, 3, a2, a);
                    C2669v.m15106a().m15113a(l2, m2);
                    return;
                }
            } else if (c2694d instanceof C2696c) {
                C2696c c2696c = (C2696c) c2694d;
                if ("redir".equals(c2696c.m15257b())) {
                    p2 = c2696c.m15242p("hosts");
                    if (p2 != null) {
                        m14892a(p2);
                        return;
                    }
                    return;
                }
            }
            this.f13060a.m14963e().m15007a(this.f13060a, l2, c2694d);
        } else if ((c2694d instanceof C2695b) && Constants.VIA_RESULT_SUCCESS.equals(c2694d.m15232k()) && "result".equals(((C2695b) c2694d).m15251b().toString())) {
            C2678j h = this.f13060a.m14966h();
            if (h instanceof C2711t) {
                ((C2711t) h).m15337x();
            }
            C2728b.m15396b();
        }
    }
}
