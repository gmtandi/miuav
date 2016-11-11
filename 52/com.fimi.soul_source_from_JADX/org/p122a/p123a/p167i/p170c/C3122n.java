package org.p122a.p123a.p167i.p170c;

import android.util.Log;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.p122a.p123a.p124f.C3017b;
import org.p122a.p123a.p180o.C3234a;

/* renamed from: org.a.a.i.c.n */
public class C3122n implements C3017b {
    private static final String f15430a = "HttpClient";
    private final Map<String, InetAddress[]> f15431b;

    public C3122n() {
        this.f15431b = new ConcurrentHashMap();
    }

    public void m17587a(String str, InetAddress... inetAddressArr) {
        C3234a.m17886a((Object) str, "Host name");
        C3234a.m17886a((Object) inetAddressArr, "Array of IP addresses");
        this.f15431b.put(str, inetAddressArr);
    }

    public InetAddress[] m17588a(String str) {
        InetAddress[] inetAddressArr = (InetAddress[]) this.f15431b.get(str);
        if (Log.isLoggable(f15430a, 4)) {
            Log.i(f15430a, "Resolving " + str + " to " + Arrays.deepToString(inetAddressArr));
        }
        if (inetAddressArr != null) {
            return inetAddressArr;
        }
        throw new UnknownHostException(str + " cannot be resolved");
    }
}
