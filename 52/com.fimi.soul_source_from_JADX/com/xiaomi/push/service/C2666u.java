package com.xiaomi.push.service;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.C2463b;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* renamed from: com.xiaomi.push.service.u */
final class C2666u implements Runnable {
    C2666u() {
    }

    public void run() {
        String b = C2664t.m15094c();
        if (TextUtils.isEmpty(b)) {
            C2463b.m14123a("Network Checkup: cannot get gateway");
        } else {
            C2463b.m14123a("Network Checkup: get gateway:" + b);
            C2664t.m15093b(b);
        }
        try {
            InetAddress byName = InetAddress.getByName("www.baidu.com");
            C2463b.m14123a("Network Checkup: get address for www.baidu.com:" + byName.getAddress());
            C2664t.m15093b(byName.getHostAddress());
        } catch (UnknownHostException e) {
            C2463b.m14123a("Network Checkup: cannot resolve the host www.baidu.com");
        } catch (Throwable th) {
            C2463b.m14123a("the checkup failure." + th);
        }
    }
}
