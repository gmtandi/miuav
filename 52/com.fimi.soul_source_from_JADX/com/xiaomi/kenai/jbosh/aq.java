package com.xiaomi.kenai.jbosh;

import java.util.HashMap;
import java.util.Map;

final class aq {
    static final aq f12676a;
    static final aq f12677b;
    static final aq f12678c;
    static final aq f12679d;
    static final aq f12680e;
    static final aq f12681f;
    static final aq f12682g;
    static final aq f12683h;
    static final aq f12684i;
    static final aq f12685j;
    static final aq f12686k;
    static final aq f12687l;
    static final aq f12688m;
    private static final Map<String, aq> f12689n;
    private static final Map<Integer, aq> f12690o;
    private final String f12691p;
    private final String f12692q;

    static {
        f12689n = new HashMap();
        f12690o = new HashMap();
        f12676a = m14369a("bad-request", "The format of an HTTP header or binding element received from the client is unacceptable (e.g., syntax error).", Integer.valueOf(400));
        f12677b = m14368a("host-gone", "The target domain specified in the 'to' attribute or the target host or port specified in the 'route' attribute is no longer serviced by the connection manager.");
        f12678c = m14368a("host-unknown", "The target domain specified in the 'to' attribute or the target host or port specified in the 'route' attribute is unknown to the connection manager.");
        f12679d = m14368a("improper-addressing", "The initialization element lacks a 'to' or 'route' attribute (or the attribute has no value) but the connection manager requires one.");
        f12680e = m14368a("internal-server-error", "The connection manager has experienced an internal error that prevents it from servicing the request.");
        f12681f = m14369a("item-not-found", "(1) 'sid' is not valid, (2) 'stream' is not valid, (3) 'rid' is larger than the upper limit of the expected window, (4) connection manager is unable to resend response, (5) 'key' sequence is invalid.", Integer.valueOf(404));
        f12682g = m14368a("other-request", "Another request being processed at the same time as this request caused the session to terminate.");
        f12683h = m14369a("policy-violation", "The client has broken the session rules (polling too frequently, requesting too frequently, sending too many simultaneous requests).", Integer.valueOf(403));
        f12684i = m14368a("remote-connection-failed", "The connection manager was unable to connect to, or unable to connect securely to, or has lost its connection to, the server.");
        f12685j = m14368a("remote-stream-error", "Encapsulated transport protocol error.");
        f12686k = m14368a("see-other-uri", "The connection manager does not operate at this URI (e.g., the connection manager accepts only SSL or TLS connections at some https: URI rather than the http: URI requested by the client).");
        f12687l = m14368a("system-shutdown", "The connection manager is being shut down. All active HTTP sessions are being terminated. No new sessions can be created.");
        f12688m = m14368a("undefined-condition", "Unknown or undefined error condition.");
    }

    private aq(String str, String str2) {
        this.f12691p = str;
        this.f12692q = str2;
    }

    static aq m14366a(int i) {
        return (aq) f12690o.get(Integer.valueOf(i));
    }

    static aq m14367a(String str) {
        return (aq) f12689n.get(str);
    }

    private static aq m14368a(String str, String str2) {
        return m14369a(str, str2, null);
    }

    private static aq m14369a(String str, String str2, Integer num) {
        if (str == null) {
            throw new IllegalArgumentException("condition may not be null");
        } else if (str2 == null) {
            throw new IllegalArgumentException("message may not be null");
        } else if (f12689n.get(str) != null) {
            throw new IllegalStateException("Multiple definitions of condition: " + str);
        } else {
            aq aqVar = new aq(str, str2);
            f12689n.put(str, aqVar);
            if (num != null) {
                if (f12690o.get(num) != null) {
                    throw new IllegalStateException("Multiple definitions of code: " + num);
                }
                f12690o.put(num, aqVar);
            }
            return aqVar;
        }
    }

    String m14370a() {
        return this.f12691p;
    }

    String m14371b() {
        return this.f12692q;
    }
}
